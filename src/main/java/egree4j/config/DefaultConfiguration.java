package egree4j.config;


/**
 * Configuration with default values set. It is recommended to use this as 
 * a base class for any other configuration.
 * 
 * @author Johan
 *
 */
public class DefaultConfiguration implements Configuration {
    private String  hostname = "app.egree.com";
    private String  restBaseUrl = "/api/v2";
    
    private String  scheme = "https";
    private Integer port = 443;
    
    private Boolean testEnabled = false;
    
    private String  authenticationKey = "";
    private String  authenticationPassword = "";
    
    private String  entityParserFactory = "egree4j.parsing.JacksonEntityParserFactory";

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    @Override
    public String getHostname() {
        return hostname;
    }
    
    public void setRestBaseUrl(String restBaseUrl) {
        this.restBaseUrl = restBaseUrl;
    }
    
    @Override
    public String getRestBaseUrl() {
        return restBaseUrl;
    }
    
    public void setPort(Integer port) {
        this.port = port;
    }
    
    @Override
    public Integer getPort() {
        return port;
    }
    
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
    
    @Override
    public String getScheme() {
        return scheme;
    }

    public void setTestEnabled(Boolean testEnabled) {
        this.testEnabled = testEnabled;
    }
    
    @Override
    public Boolean isTestEnabled() {
        return testEnabled;
    }
    
    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    @Override
    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationPassword(String authenticationPassword) {
        this.authenticationPassword = authenticationPassword;
    }
    
    @Override
    public String getAuthenticationPassword() {
        return authenticationPassword;
    }
    
    public void setEntityparserFactory(String entityParserFactory) {
        this.entityParserFactory = entityParserFactory;
    }
    
    @Override
    public String getEntityParserFactory() {
        return entityParserFactory;
    }
}
