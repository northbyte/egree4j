package egree4j.http;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;

import egree4j.config.Configuration;

/**
 * Authentication provider that provides proper authentication.
 * 
 * @author Johan
 *
 */
public class BasicAuthFactory implements AuthFactory {
    private Configuration conf;
    
    /**
     * Constructs a new BasicAuthFactory. Since BASIC authentication uses the
     * username and password, a configuration needs to be passed with these
     * set.
     * The configuration will also be used for HTTP settings.
     * 
     * @param conf Configuration for username and password.
     */
    public BasicAuthFactory(Configuration conf) {
        this.conf = conf;
    }
    
    @Override
    public AuthCache getAuthCache() {
        AuthCache authCache = new BasicAuthCache();
        HttpHost targetHost = new HttpHost(
                conf.getHostname(), conf.getPort(), conf.getScheme());
        
        AuthScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);
        return authCache;
    }
    
    @Override
    public CredentialsProvider getCredentialsProvider() {
        Credentials credentials = new UsernamePasswordCredentials(
                conf.getAuthenticationKey(), 
                conf.getAuthenticationPassword());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        AuthScope authScope = new AuthScope(
                conf.getHostname(), conf.getPort());
        credsProvider.setCredentials(authScope, credentials);
        
        return credsProvider;
    }
}
