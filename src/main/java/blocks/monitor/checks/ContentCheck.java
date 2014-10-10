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

import blocks.monitor.Check;
import blocks.monitor.content.ItemSearchResults;
import blocks.monitor.enumeration.Status;
import blocks.monitor.model.Record;
import blocks.monitor.properties.ContentProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Renfro
 */
@Component
public class ContentCheck implements Check {

    private static final Logger LOGGER = Logger.getLogger(ContentCheck.class.getName());

    @Autowired
    ContentProperties contentProperties;

    @Autowired
    RestOperations contentOperations;

    @Override
    public List<Record> execute() {
        List<Record> records = new ArrayList<Record>();

        Record record = new Record();
        record.setKey("contentItemSearchResults");
        record.setDescription("Able to search for items");

        String uri = contentProperties.getUri() + "/item.json";
        long start = System.currentTimeMillis();

        Status status = Status.FAILURE;
        try {
            ResponseEntity<ItemSearchResults> searchResults =
                    contentOperations.getForEntity(uri, ItemSearchResults.class);

            if (searchResults != null)
                status = Status.SUCCESS;

        } catch (RestClientException e) {
            LOGGER.error("Unable to retrieve item ", e);
        }

        record.setStatus(status);
        record.setElapsed(System.currentTimeMillis() - start);

        records.add(record);
        return records;
    }


}
