package egree4j.models.utils;

/**
 * Element that is used for sending a SingleSignOn request to the Egree service
 * when signing on remotely.
 * 
 * @author Johan
 *
 */
public class SingleSignOn {
    private String username;
    private String targetUrl;
    
    public SingleSignOn(String username) {
        this.username = username;
    }
    
    /**
     * Returns the username of the agent to log on. A ticket will be generated
     * for this user. Username is either Agent Id or Agent EmailAddress.
     * 
     * @return Agent username to log on.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username for the agent to log on. Username is either Agent Id 
     * or Agent EmailAddress.
     * 
     * @param username Username to log on.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns the URL that the user will redirected to once logged in through
     * the Egree service. If supplied, user will be redirected to this address 
     * after login.
     * 
     * @return The URL that the user will be redirected to once logged in.
     */
    public String getTargetUrl() {
        return targetUrl;
    }
    
    /**
     * Sets the url that the user should be redirected back to once logged in
     * through the Egree service. If supplied, user will be redirected to this 
     * address after login.
     * 
     * @param targetUrl Url to be redirected to once successfully logged in.
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    
    @Override
    public String toString() {
        return "SingleSignOn [username=" + username + ", targetUrl="
                + targetUrl + "]";
    }
    
    
}
