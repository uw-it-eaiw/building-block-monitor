package blocks.monitor.checks;

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

import blocks.monitor.Check;
import blocks.monitor.enumeration.Status;
import blocks.monitor.model.Record;
import blocks.monitor.properties.MongoProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Renfro
 */
@Component
public class MongoCheck implements Check {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoProperties mongoProperties;

    @Override
    public List<Record> execute() {
        List<Record> records = new ArrayList<Record>();

        if (StringUtils.isNotEmpty(mongoProperties.getCollectionsRequired())) {
            String[] collectionNames = mongoProperties.getCollectionsRequired().split(",");
            if (collectionNames != null && collectionNames.length > 0) {
                for (String collectionName : collectionNames) {
                    records.add(checkCollection(collectionName));
                }
            }
        }

        return records;
    }

    private Record checkCollection(String collectionName) {
        Record record = new Record();
        record.setKey("mongoCollectionExists-" + collectionName);
        record.setDescription("Collection exists with name " + collectionName);

        long start = System.currentTimeMillis();
        Status status = Status.FAILURE;
        try {
            if (mongoOperations.collectionExists(collectionName))
                status = Status.SUCCESS;
        } catch (Exception e) {
            record.setDescription(e.getMessage());
        }
        record.setStatus(status);
        record.setElapsed(System.currentTimeMillis() - start);

        return record;
    }


}

