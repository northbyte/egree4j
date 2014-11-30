package egree4j.models.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import egree4j.models.Party;
import egree4j.models.Stakeholder;
import egree4j.models.documents.Document;

/**
 * Egree differs a bit between cases has already been stored and those that has 
 * not yet been created. This class is the base of both, and has the information
 * that both the cases carries. 
 * 
 * <p>To create a new case, use the {@link Draft}. Once a case has been
 * created, Egree will return {@link Case}s that can be modified and updated.
 * </p> 
 * 
 * @author Johan
 *
 */
public abstract class AbstractCase {
    private String id;
    private String name;
    private List<Party> parties = new ArrayList<>();
    private List<Document> documents = new ArrayList<>();
    private List<SignatureType> allowedSignatureTypes = new ArrayList<>();
    private String description;
    private String culture;
    private List<Stakeholder> stakeholders = new ArrayList<>();
    private Map<String, Object> metadata = new HashMap<>();
    
    // Continuation of the case
    private String continueUrl;
    private String continueName;
    private Boolean continueAuto;
    
    // Information, who and when to send updates
    private Boolean sendSignRequestEmailToParties;

    private Boolean sendFinishEmailToCreator;
    private Boolean sendFinishEmailToParties;
    private Boolean sendRecallEmailToParties;
    private Boolean signInSequence;
    private Boolean isEditable;
    private Boolean mergeOnSend;
    
    // Expiration and reminders
    private Long expireAfterDays;
    private DateTime expireOn;
    private Integer remindAfterDays;
    private String templateId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<SignatureType> getAllowedSignatureTypes() {
        return allowedSignatureTypes;
    }

    public void setAllowedSignatureTypes(List<SignatureType> allowedSignatureTypes) {
        this.allowedSignatureTypes = allowedSignatureTypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public List<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(List<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metaData) {
        this.metadata = metaData;
    }

    public String getContinueUrl() {
        return continueUrl;
    }

    public void setContinueUrl(String continueUrl) {
        this.continueUrl = continueUrl;
    }

    public String getContinueName() {
        return continueName;
    }

    public void setContinueName(String continueName) {
        this.continueName = continueName;
    }

    public Boolean getContinueAuto() {
        return continueAuto;
    }

    public void setContinueAuto(Boolean continueAuto) {
        this.continueAuto = continueAuto;
    }

    public Boolean getSendSignRequestEmailToParties() {
        return sendSignRequestEmailToParties;
    }

    public void setSendSignRequestEmailToParties(
            Boolean sendSignRequestEmailToParties) {
        this.sendSignRequestEmailToParties = sendSignRequestEmailToParties;
    }

    public Boolean getSendFinishEmailToCreator() {
        return sendFinishEmailToCreator;
    }

    public void setSendFinishEmailToCreator(Boolean sendFinishEmailToCreator) {
        this.sendFinishEmailToCreator = sendFinishEmailToCreator;
    }

    public Boolean getSendFinishEmailToParties() {
        return sendFinishEmailToParties;
    }

    public void setSendFinishEmailToParties(Boolean sendFinishEmailToParties) {
        this.sendFinishEmailToParties = sendFinishEmailToParties;
    }

    public Boolean getSendRecallEmailToParties() {
        return sendRecallEmailToParties;
    }

    public void setSendRecallEmailToParties(Boolean sendRecallEmailToParties) {
        this.sendRecallEmailToParties = sendRecallEmailToParties;
    }

    public Boolean getSignInSequence() {
        return signInSequence;
    }

    public void setSignInSequence(Boolean signInSequence) {
        this.signInSequence = signInSequence;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean editable) {
        this.isEditable = editable;
    }

    public Boolean getMergeOnSend() {
        return mergeOnSend;
    }

    public void setMergeOnSend(Boolean mergeOnSend) {
        this.mergeOnSend = mergeOnSend;
    }

    public Long getExpireAfterDays() {
        return expireAfterDays;
    }

    public void setExpireAfterDays(Long expireAfterDays) {
        this.expireAfterDays = expireAfterDays;
    }

    public DateTime getExpireOn() {
        return expireOn;
    }

    public void setExpireOn(DateTime expireOn) {
        this.expireOn = expireOn;
    }

    public Integer getRemindAfterDays() {
        return remindAfterDays;
    }

    public void setRemindAfterDays(Integer remindAfterDays) {
        this.remindAfterDays = remindAfterDays;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    

    @Override
    public String toString() {
        return "AbstractCase [id=" + id + ", name=" + name + ", parties="
                + parties + ", documents=" + documents
                + ", allowedSignatureTypes=" + allowedSignatureTypes
                + ", description=" + description + ", culture=" + culture
                + ", stakeholders=" + stakeholders + ", metadata=" + metadata
                + ", continueUrl=" + continueUrl + ", continueName="
                + continueName + ", continueAuto=" + continueAuto
                + ", sendSignRequestEmailToParties="
                + sendSignRequestEmailToParties + ", sendFinishEmailToCreator="
                + sendFinishEmailToCreator + ", sendFinishEmailToParties="
                + sendFinishEmailToParties + ", sendRecallEmailToParties="
                + sendRecallEmailToParties + ", signInSequence="
                + signInSequence + ", isEditable=" + isEditable
                + ", mergeOnSend=" + mergeOnSend + ", expireAfterDays="
                + expireAfterDays + ", expireOn=" + expireOn
                + ", remindAfterDays=" + remindAfterDays + ", templateId="
                + templateId + "]";
    }
    
}
