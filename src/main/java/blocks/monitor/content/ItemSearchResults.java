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
package blocks.monitor.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author James Renfro
 */
@XmlRootElement(name = "ItemSearchResults")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ItemSearchResultsType", namespace = "http://webservices.washington.edu/content/")
public class ItemSearchResults {

    @XmlElement(name="Items")
    @JsonProperty("Items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
