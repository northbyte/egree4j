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
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getTargetUrl() {
        return targetUrl;
    }
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    
    @Override
    public String toString() {
        return "SingleSignOn [username=" + username + ", targetUrl="
                + targetUrl + "]";
    }
    
    
}
