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
package blocks.monitor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author James Renfro
 */
@ConfigurationProperties(prefix = "notification")
public class NotificationProperties {

    private String applicationName;
    private String mailServerHost;
    private int mailServerPort = 22;
    private String mailServerUsername;
    private String mailServerPassword;
    private boolean mailServerTls;
    private String mailFromAddress;
    private String mailFromLabel;
    private String mailToOverride;
    private String mailToAdministrator;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public int getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(int mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getMailServerUsername() {
        return mailServerUsername;
    }

    public void setMailServerUsername(String mailServerUsername) {
        this.mailServerUsername = mailServerUsername;
    }

    public String getMailServerPassword() {
        return mailServerPassword;
    }

    public void setMailServerPassword(String mailServerPassword) {
        this.mailServerPassword = mailServerPassword;
    }

    public boolean isMailServerTls() {
        return mailServerTls;
    }

    public void setMailServerTls(boolean mailServerTls) {
        this.mailServerTls = mailServerTls;
    }

    public String getMailFromAddress() {
        return mailFromAddress;
    }

    public void setMailFromAddress(String mailFromAddress) {
        this.mailFromAddress = mailFromAddress;
    }

    public String getMailFromLabel() {
        return mailFromLabel;
    }

    public void setMailFromLabel(String mailFromLabel) {
        this.mailFromLabel = mailFromLabel;
    }

    public String getMailToOverride() {
        return mailToOverride;
    }

    public void setMailToOverride(String mailToOverride) {
        this.mailToOverride = mailToOverride;
    }

    public String getMailToAdministrator() {
        return mailToAdministrator;
    }

    public void setMailToAdministrator(String mailToAdministrator) {
        this.mailToAdministrator = mailToAdministrator;
    }
}
