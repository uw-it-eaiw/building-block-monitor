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
package blocks.monitor.checks.config;

import blocks.monitor.checks.ContentCheck;
import blocks.monitor.properties.ContentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author James Renfro
 */
@Configuration
public class ContentTestConfiguration {

    @Bean
    public ContentProperties contentProperties() {
        ContentProperties contentProperties = new ContentProperties();
        contentProperties.setUri("http://somehost/content/v1");
        return contentProperties;
    }

    @Bean
    public ContentCheck contentCheck() {
        return new ContentCheck();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
