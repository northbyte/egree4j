package egree4j.models.cases;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Cases that has been stored in the Egree service are of this instance type.
 * This is the most common {@code Case}  type operated on and is the
 * {@code Case} type that will be returned from the Egree service at any time.
 * 
 * @author Johan
 *
 */
public class Case extends AbstractCase {
    private Status status;
    private Procedure procedure;
    private Boolean identityCheck;
    private DateTime createdOn;
    private DateTime remindOn;
    private DateTime sentOn;
    private DateTime reminderSentOn;
    private String hash;
    private String agentUrl;
    private String publicUrl;
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Procedure getProcedure() {
        return procedure;
    }
    
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
    
    public Boolean getIdentityCheck() {
        return identityCheck;
    }
    
    public void setIdentityCheck(Boolean identityCheck) {
        this.identityCheck = identityCheck;
    }
    
    /**
     * Returns the date time when the case was created. This is always in UTC.
     * 
     * @return UTC date time when the case was created.
     */
    public DateTime getCreatedOn() {
        return createdOn;
    }
    
    /*
     * INTERNAL. Only used for data setter. A user cannot set this.
     */
    protected void setCreatedOn(DateTime createdOn) {
        this.createdOn = createdOn;
    }
    
    /**
     * Returns the date time when a reminder will be sent to the parties.
     * This is always in UTC.
     * 
     * @return UTC date time when a reminder will be sent.
     */
    public DateTime getRemindOn() {
        return remindOn;
    }
    
    /**
     * Sets the date when a reminder should be sent to all parties. This will 
     * always be in UTC and will be converted to UTC when set.
     * 
     * @param remindOn Date when to send a reminder.
     */
    public void setRemindOn(DateTime remindOn) {
        this.remindOn = (remindOn != null) 
                ? remindOn.toDateTime(DateTimeZone.UTC) : remindOn;
    }
    
    /**
     * Returns the date when this case was sent in UTC.
     * 
     * @return UTC date when this case was sent.
     */
    public DateTime getSentOn() {
        return sentOn;
    }
    
    /*
     * INTERNAL. Only used for data setter. A user cannot set this.
     */
    protected void setSentOn(DateTime sentOn) {
        this.sentOn = sentOn;
    }

    /**
     * Returns the date when a reminder was sent out (in UTC). Is null if
     * no reminder has been sent.
     * 
     * @return Date when reminder sent.
     */
    public DateTime getReminderSentOn() {
        return reminderSentOn;
    }
    
    /*
     * INTERNAL. Only used for data setter. A user cannot set this.
     */
    protected void setReminderSentOn(DateTime reminderSentOn) {
        this.reminderSentOn = reminderSentOn;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getAgentUrl() {
        return agentUrl;
    }
    
    public void setAgentUrl(String agentUrl) {
        this.agentUrl = agentUrl;
    }
    
    public String getPublicUrl() {
        return publicUrl;
    }
    
    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }
    
    @Override
    public String toString() {
        return "Case [status=" + status + ", procedure=" + procedure
                + ", identityCheck=" + identityCheck + ", createdOn="
                + createdOn + ", remindOn=" + remindOn + ", sentOn=" + sentOn
                + ", reminderSentOn=" + reminderSentOn + ", hash=" + hash
                + ", agentUrl=" + agentUrl + ", publicUrl=" + publicUrl
                + ", toString()=" + super.toString() + "]";
    }
}
