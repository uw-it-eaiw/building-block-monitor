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
@XmlRootElement(name = "Item")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ItemType", namespace = "http://webservices.washington.edu/content/")
public class Item {

    @XmlElement(name="ItemUri")
    @JsonProperty("ItemUri")
    private ItemLink itemUri;

    @XmlElement(name="ItemId")
    @JsonProperty("ItemId")
    private String itemId;

    @XmlElement(name="Filer")
    @JsonProperty("Filer")
    private String filer;

    @XmlElement(name="FileExtension")
    @JsonProperty("FileExtension")
    private String fileExtension;

    @XmlElement(name="FileSize")
    @JsonProperty("FileSize")
    private String fileSize;

    @XmlElement(name="FileName")
    @JsonProperty("FileName")
    private String fileName;

    @XmlElement(name="MimeType")
    @JsonProperty("MimeType")
    private String mimeType;

    @XmlElement(name="ProfileId")
    @JsonProperty("ProfileId")
    private String profileId;

//    @XmlElement(name="ProfileInformationOnlyFields")
//    @JsonProperty("ProfileInformationOnlyFields")
//    private Map<String, String> profileInformationOnlyFields;
//
//    @XmlElement(name="ProfileOptionalFields")
//    @JsonProperty("ProfileOptionalFields")
//    private Map<String, String> profileOptionalFields;
//
//    @XmlElement(name="ProfileRequiredFields")
//    @JsonProperty("ProfileRequiredFields")
//    private Map<String, String> profileRequiredFields;

    @XmlElementWrapper(name="Renditions")
    @XmlElement(name="Rendition")
    @JsonProperty("Renditions")
    private List<ItemLink> renditions;

    @XmlElement(name="RevisionId")
    @JsonProperty("RevisionId")
    private String revisionId;

    @XmlElementWrapper(name="Revisions")
    @XmlElement(name="Revision")
    @JsonProperty("Revisions")
    private List<ItemLink> revisions;

    @XmlElement(name="Status")
    @JsonProperty("Status")
    private ItemLink status;

    @XmlElement(name="Title")
    @JsonProperty("Title")
    private String title;


    public Item() {

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<ItemLink> getRenditions() {
        return renditions;
    }

    public void setRenditions(List<ItemLink> renditions) {
        this.renditions = renditions;
    }

    public List<ItemLink> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<ItemLink> revisions) {
        this.revisions = revisions;
    }

    public ItemLink getItemUri() {
        return itemUri;
    }

    public void setItemUri(ItemLink itemUri) {
        this.itemUri = itemUri;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

//    public Map<String, String> getProfileInformationOnlyFields() {
//        return profileInformationOnlyFields;
//    }
//
//    public void setProfileInformationOnlyFields(Map<String, String> profileInformationOnlyFields) {
//        this.profileInformationOnlyFields = profileInformationOnlyFields;
//    }
//
//    public Map<String, String> getProfileOptionalFields() {
//        return profileOptionalFields;
//    }
//
//    public void setProfileOptionalFields(Map<String, String> profileOptionalFields) {
//        this.profileOptionalFields = profileOptionalFields;
//    }
//
//    public Map<String, String> getProfileRequiredFields() {
//        return profileRequiredFields;
//    }
//
//    public void setProfileRequiredFields(Map<String, String> profileRequiredFields) {
//        this.profileRequiredFields = profileRequiredFields;
//    }

    public String getFiler() {
        return filer;
    }

    public void setFiler(String filer) {
        this.filer = filer;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public ItemLink getStatus() {
        return status;
    }

    public void setStatus(ItemLink status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
