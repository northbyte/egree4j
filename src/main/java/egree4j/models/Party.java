package egree4j.models;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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
    
    /**
     * Returns the date when the party signed. This is always UTC.
     * 
     * @return UTC time when signed.
     */
    public DateTime getSignedOn() {
        return signedOn;
    }
    
    /** 
     * Sets the when the party was signed on. This will always be in UTC and 
     * will be converted to UTC when set.
     * 
     * @param signedOn Time when signed on.
     */
    public void setSignedOn(DateTime signedOn) {
        this.signedOn = (signedOn != null) 
                ? signedOn.toDateTime(DateTimeZone.UTC) : signedOn;
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
