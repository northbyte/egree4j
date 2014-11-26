package egree4j.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Default implementation of {@code HttpClientFactory}.
 * 
 * @author Johan
 *
 */
public class DefaultHttpClientFactory implements HttpClientFactory {
    private CloseableHttpClient client;
    
    @Override
    public CloseableHttpClient getInstance(AuthFactory authFactory) {
        if (client != null) {
            return client;
        }        
        
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultCredentialsProvider(
                authFactory.getCredentialsProvider());
        this.client = builder.build();        
        return client;
    }

}
