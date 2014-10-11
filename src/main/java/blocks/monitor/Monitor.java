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

package blocks.monitor;

import blocks.monitor.config.ContentConfiguration;
import blocks.monitor.config.MongoConfiguration;
import blocks.monitor.model.Record;
import blocks.monitor.properties.ContentProperties;
import blocks.monitor.properties.MongoProperties;
import blocks.monitor.properties.NotificationProperties;
import blocks.monitor.properties.SecurityProperties;
import blocks.monitor.service.NotificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author James Renfro
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = {"file:/etc/building-block-monitor/monitor.properties", "file:/data/building-blocks/etc/monitor.properties"}, ignoreResourceNotFound = true)
@EnableConfigurationProperties({ContentProperties.class, MongoProperties.class, NotificationProperties.class, SecurityProperties.class})
@Import({ContentConfiguration.class, MongoConfiguration.class})
public class Monitor implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(Monitor.class.getName());

    @Autowired
    private Check[] checks;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void run(String... args) {
        LOGGER.info("Running checks");
        if (checks != null) {
            for (Check check : checks) {
                List<Record> records = check.execute();
                notificationService.notify(records);
            }
        }
        LOGGER.info("All checks completed");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Monitor.class, args);
    }

}
