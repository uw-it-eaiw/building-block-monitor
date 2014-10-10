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
package blocks.monitor.checks;

import com.fasterxml.jackson.databind.ObjectMapper;
import blocks.monitor.checks.config.ContentTestConfiguration;
import blocks.monitor.content.ItemSearchResults;
import blocks.monitor.enumeration.Status;
import blocks.monitor.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ContentTestConfiguration.class})
public class ContentCheckTest {

    @Autowired
    private ContentCheck contentCheck;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void verifyExecute() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ItemSearchResults searchResults = null;
        mockServer.expect(requestTo("http://somehost/content/v1/item.json")).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(mapper.writeValueAsString(searchResults), MediaType.APPLICATION_JSON));
        List<Record> records = contentCheck.execute();

        assertEquals(1, records.size());

        Record record = records.iterator().next();
        assertEquals(Status.SUCCESS, record.getStatus());

        mockServer.verify();
    }

}