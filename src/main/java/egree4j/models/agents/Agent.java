package egree4j.models.agents;


/**
 * An Agent is a client that uses the API. An Egree Account can contain multiple
 * Agents where each Agent have different roles. This model is currently only 
 * used when creating new Agents.
 * 
 * @author Johan
 *
 */
public class Agent {
    private String id;
    private String name;
    private String emailAddress;
    private Role role;
    private String phoneNumber;
    private String externalId;
    private String culture;
    
    /**
     * Returns the ID of the Agent. This can either be set or automatically
     * created by the service.
     * 
     * @return Agent id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of this agent. Unique within the account to which the agents 
     * belongs. You may provide an Id, or one will be generated.
     * 
     * @param id Of the Agent.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the Agent. 
     * 
     * @return Name of Agent.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Agent. John Smith as an example.
     * 
     * @param name Full name of the Agent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address for this Agent, which is used for notifications
     * or as a login if no id is set.
     * 
     * @return The email address of the Agent.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the Email address of the Agent. Notification will be sent to this 
     * address. Used as login if Id is omitted.
     * 
     * @param emailAddress Email address.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the Role which the current Agent has. 
     * 
     * @return List of Roles that the Agent has.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the Role that the Agent has. This determines which permission the
     * agent will have.
     * 
     * @param role Role assigned to the Agent.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the Phone number of the Agent.
     * 
     * @return Phone number of Agent.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the Phone number of the Agent.
     * 
     * @param phoneNumber Phone number of the Agent.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the external id of the Agent.
     * 
     * @return Agents external ID.
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the external ID of the Agent, which is the ID that the Agent can
     * be referred to in an external system.
     * 
     * @param externalId Id externally for the Agent.
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Returns the Culture of the Agent. This is a RFC4646 valid definition of
     * the locale that the Agent has. Since Microsoft defines Culture slightly
     * different from Locale they are not the same.
     * 
     * @return The culture of the Agent.
     */
    public String getCulture() {
        return culture;
    }

    /**
     * Sets the culture (locale) of the Agent. For valid cultures to set please
     * see <a href="http://msdn.microsoft.com/en-us/library/ee825488(v=cs.20).aspx">
     * http://msdn.microsoft.com/en-us/library/ee825488(v=cs.20).aspx</a>.
     * 
     * @param culture Culture of the Agent.
     */
    public void setCulture(String culture) {
        this.culture = culture;
    }

    @Override
    public String toString() {
        return "Agent [id=" + id + ", name=" + name + ", emailAddress="
                + emailAddress + ", roles=" + role + ", phoneNumber="
                + phoneNumber + ", culture=" + culture + "]";
    }
}
