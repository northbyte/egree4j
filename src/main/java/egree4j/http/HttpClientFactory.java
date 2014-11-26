package egree4j.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Factory interface for constructing {@code HttpClient}s. These are the main
 * contact points towards the API, which means only one per {@code Egree} 
 * instance should probably be initialized.
 * 
 * @author Johan
 *
 */
public interface HttpClientFactory {
    
    /**
     * Returns a {@code HttpClient} to use.
     * 
     * @param authFactory Authentication factory for credentials.
     * @return A configured client.
     */
    CloseableHttpClient getInstance(AuthFactory authFactory);
}
