package egree4j.http;

import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;

/**
 * Factory interface for producing proper authentication methods in a Http
 * client context.
 * 
 * @author Johan
 *
 */
public interface AuthFactory {
    
    /**
     * Returns a {@code AuthCache} object usable for caching authentication
     * methods in a {@code HttpClient}.
     * 
     * @return Constructed auth cache that can be used in a HttpClient.
     */
    public AuthCache getAuthCache();
    
    /**
     * Returns a {@code CredentialsProvider} that provides proper credentials
     * to authenticate with the implementing auth scheme.
     * 
     * @return CredentialsProvider based on the implementation.
     */
    public CredentialsProvider getCredentialsProvider();
}
