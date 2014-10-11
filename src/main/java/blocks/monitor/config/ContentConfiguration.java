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
package blocks.monitor.config;

import blocks.monitor.properties.ContentProperties;
import blocks.monitor.properties.SecurityProperties;
import blocks.monitor.security.ActAsHeaderInterceptor;
import blocks.monitor.security.KeyManagerCabinet;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;

/**
 * @author James Renfro
 */
@Configuration
public class ContentConfiguration {

    private static final Logger LOGGER = Logger.getLogger(ContentConfiguration.class.getName());

    @Bean
    public KeyManagerCabinet keyManagerCabinet(SecurityProperties securityProperties) throws Exception {
        return new KeyManagerCabinet.Builder(securityProperties).build();
    }

    @Bean
    public RestTemplate contentOperations(ContentProperties contentProperties, KeyManagerCabinet cabinet) throws NoSuchAlgorithmException, KeyManagementException {
        LOGGER.debug("Configuring HTTP operations");
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(5);

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        X509HostnameVerifier hostnameVerifier = new StrictHostnameVerifier();

        SSLContext sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.SSL);
        sslContext.init(cabinet.getKeyManagers(), cabinet.getTrustManagers(), new SecureRandom());

        LayeredConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);

        CloseableHttpClient client =
                HttpClients.custom()
                        .setConnectionManager(cm)
                        .setDefaultCredentialsProvider(credentialsProvider)
//                        .setSSLSocketFactory(socketFactory)
//                        .setSslcontext(sslContext)
                        .build();

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
        restTemplate.setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(new ActAsHeaderInterceptor(contentProperties.getActAsUser())));
        return restTemplate;
    }

}
