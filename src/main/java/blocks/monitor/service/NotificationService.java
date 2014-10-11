/*
 *  Copyright 2014 University of Washington Licensed under the
 *	Educational Community License, Version 2.0 (the "License"); you may
 *	not use this file except in compliance with the License. You may
 *	obtain a copy of the License at
 *
 *  http://www.osedu.org/licenses/ECL-2.0
 *
 *	Unless required by applicable law or agreed to in writing,
 *	software distributed under the License is distributed on an "AS IS"
 *	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *	or implied. See the License for the specific language governing
 *	permissions and limitations under the License.
 */
package blocks.monitor.service;

import blocks.monitor.model.Record;
import blocks.monitor.properties.NotificationProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author James Renfro
 */
@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    @Autowired
    private NotificationProperties notificationProperties;

    public boolean notify(List<Record> records) {
        if (records != null && !records.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            boolean hasFailure = false;
            for (Record record : records) {
                String message = record.toString();
                switch (record.getStatus()) {
                    case SUCCESS:
                        LOGGER.debug(message);
                        break;
                    case WARNING:
                        LOGGER.warn(message);
                        break;
                    case FAILURE:
                        LOGGER.error(message);
                        builder.append(message).append(System.lineSeparator());
                        hasFailure = true;
                        break;
                }
            }

            if (hasFailure) {
                String subject = "Alert - Potential Problem with Content, WebCenter, or Workflow";
                try {
                    SimpleEmail email = new SimpleEmail();

                    if (StringUtils.isNotEmpty(notificationProperties.getMailToAdministrator())) {

                        String[] addresses = notificationProperties.getMailToAdministrator().split(",");

                        if (addresses != null && addresses.length > 0) {

                            for (String address : addresses) {
                                email.addTo(StringUtils.chomp(address));
                            }
                            email.setFrom(notificationProperties.getMailFromAddress(), notificationProperties.getMailFromLabel());
                            email.setSubject(subject);

                            String msg = builder.toString();

                            email.setMsg(msg);

                            return dispatch(email);
                        }
                    }
                } catch (EmailException e) {
                    LOGGER.error("Unable to send email with subject " + subject);
                }
            }

            return true;
        }
        return false;
    }

    private boolean dispatch(SimpleEmail email) {
        try {
            email.setHostName(notificationProperties.getMailServerHost());
            email.setSmtpPort(notificationProperties.getMailServerPort());

            if (notificationProperties.isMailServerTls()) {
                email.setAuthentication(notificationProperties.getMailServerUsername(), notificationProperties.getMailServerPassword());
                email.setStartTLSEnabled(true);
            }

            LOGGER.info("Subject: " + email.getSubject());
            LOGGER.info(email.getMimeMessage());

            email.send();
            return true;
        } catch (EmailException e) {
            LOGGER.error("Unable to send email with subject " + email.getSubject());
        }
        return false;
    }

}
