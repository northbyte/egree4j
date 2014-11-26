package egree4j.config;

/**
 * Configuration settings for egree4j.
 * 
 * @author Johan
 *
 */
public interface Configuration {
    
    /**
     * Hostname of the production server. This is used for the authentication
     * scheme to verify that the auth is sent to the correct host.
     * 
     * @return Egree host name.
     */
    String getHostname();    
    
    /**
     * Base URL for the production Egree REST server.
     * 
     * @return The base URL to the Egree server.
     */
    String getRestBaseUrl();
    
    /**
     * Port that the service is listening to.
     * 
     * @return Egree service port.
     */
    Integer getPort();
    
    /**
     * Returns the type of scheme to use in the request. This is usually
     * HTTPS, but if there are other services then this should be configured
     * accordingly.
     * 
     * @return Scheme to use in the request.
     */
    String getScheme();
    
    /**
     * If the configuration is setup for testing instead of production this
     * will return true. This allows the system to take proper actions.
     * 
     * @return True if testing is enabled, false otherwise.
     */
    Boolean isTestEnabled();
    
    /**
     * Returns the key used in the Basic authentication scheme that Egree uses.
     * 
     * @return Auth key to authenticate connections.
     */
    String getAuthenticationKey();
    
    /**
     * Returns the password/secret used in conjunction with the auth key to
     * authenticate with the Egree servers.
     * 
     * @return Auth password/secret to authenticate connections.
     */
    String getAuthenticationPassword();
    
    /**
     * Returns the name of the factory class that produces entity parsers;
     * which are responsible for processing the response entities from a 
     * HTTP request.
     * 
     * @return Name of factory class.
     */
    String getEntityParserFactory();
    
}
