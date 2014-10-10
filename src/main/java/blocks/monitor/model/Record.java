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
package blocks.monitor.model;

import blocks.monitor.enumeration.Status;

import java.util.Date;

/**
 * @author James Renfro
 */
public class Record {

    private String key;
    private Status status;
    private String description;
    private Date timestamp;
    private Long elapsed;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getElapsed() {
        return elapsed;
    }

    public void setElapsed(Long elapsed) {
        this.elapsed = elapsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (description != null ? !description.equals(record.description) : record.description != null) return false;
        if (elapsed != null ? !elapsed.equals(record.elapsed) : record.elapsed != null) return false;
        if (key != null ? !key.equals(record.key) : record.key != null) return false;
        if (status != record.status) return false;
        if (timestamp != null ? !timestamp.equals(record.timestamp) : record.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (elapsed != null ? elapsed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "key='" + key + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", elapsed=" + elapsed +
                '}';
    }

}
