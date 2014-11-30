package egree4j.models;

/**
 * Represents a stakeholder in a case. Stakeholders are able to access the 
 * signed document.
 * 
 * @author Johan
 *
 */
public class Stakeholder {
    private String id;
    private String name;
    private String emailAddress;
    private String mobilePhone;
    private String nationalId;
    private String culture;
    private String stakeholderUrl;
    
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
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getNationalId() {
        return nationalId;
    }
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    public String getCulture() {
        return culture;
    }
    public void setCulture(String culture) {
        this.culture = culture;
    }
    public String getStakeholderUrl() {
        return stakeholderUrl;
    }
    public void setStakeholderUrl(String stakeholderUrl) {
        this.stakeholderUrl = stakeholderUrl;
    }
    
    @Override
    public String toString() {
        return "Stakeholder [id=" + id + ", name=" + name + ", emailAddress="
                + emailAddress + ", mobilePhone=" + mobilePhone
                + ", nationalId=" + nationalId + ", culture=" + culture
                + ", stakeholderUrl=" + stakeholderUrl + "]";
    }
    
    
}
