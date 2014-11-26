package egree4j.models.cases;

import org.joda.time.DateTime;

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
    public DateTime getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(DateTime createdOn) {
        this.createdOn = createdOn;
    }
    public DateTime getRemindOn() {
        return remindOn;
    }
    public void setRemindOn(DateTime remindOn) {
        this.remindOn = remindOn;
    }
    public DateTime getSentOn() {
        return sentOn;
    }
    public void setSentOn(DateTime sentOn) {
        this.sentOn = sentOn;
    }
    public DateTime getReminderSentOn() {
        return reminderSentOn;
    }
    public void setReminderSentOn(DateTime reminderSentOn) {
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
