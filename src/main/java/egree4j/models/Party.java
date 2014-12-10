package egree4j.models;

import org.joda.time.DateTime;

public class Party {
    private String id;
    private String name;
    private String groupName;
    private String emailAddress;
    private String socialSecurityNumber;
    private DateTime signedOn;
    private String signatureData;
    private String partyUrl;
    private String mobilePhone;
    private String culture;
    private Boolean anyoneCanSign;
    
    // Legacy
    private String signatureUrl;
    
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
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public DateTime getSignedOn() {
        return signedOn;
    }
    public void setSignedOn(DateTime signedOn) {
        this.signedOn = signedOn;
    }
    public String getSignatureData() {
        return signatureData;
    }
    public void setSignatureData(String signatureData) {
        this.signatureData = signatureData;
    }
    public String getPartyUrl() {
        return partyUrl;
    }
    public void setPartyUrl(String partyUrl) {
        this.partyUrl = partyUrl;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getCulture() {
        return culture;
    }
    public void setCulture(String culture) {
        this.culture = culture;
    }
    public Boolean getAnyoneCanSign() {
        return anyoneCanSign;
    }
    public void setAnyoneCanSign(Boolean anyoneCanSign) {
        this.anyoneCanSign = anyoneCanSign;
    }
    public String getSignatureUrl() {
        return signatureUrl;
    }
    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }
    @Override
    public String toString() {
        return "Party [id=" + id + ", name=" + name + ", groupName="
                + groupName + ", emailAddress=" + emailAddress
                + ", socialSecurityNumber=" + socialSecurityNumber
                + ", signedOn=" + signedOn + ", signatureData=" + signatureData
                + ", partyUrl=" + partyUrl + ", mobilePhone=" + mobilePhone
                + ", culture=" + culture + ", anyoneCanSign=" + anyoneCanSign
                + "]";
    }
}
