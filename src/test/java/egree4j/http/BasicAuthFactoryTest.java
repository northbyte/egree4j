package egree4j.http;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicAuthCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import egree4j.config.Configuration;

@RunWith(MockitoJUnitRunner.class)
public class BasicAuthFactoryTest {

    @Mock Configuration conf;
    private AuthFactory authFactory;
    
    @Before
    public void setUp() {
        when(conf.getPort()).thenReturn(8080);
        when(conf.getHostname()).thenReturn("localhost");
        when(conf.getAuthenticationKey()).thenReturn("key");
        when(conf.getAuthenticationPassword()).thenReturn("secret");
        when(conf.getScheme()).thenReturn("https");
        
        authFactory = new BasicAuthFactory(conf);
    }
    
    @Test
    public void testAuthCache() {
        AuthCache cache = authFactory.getAuthCache();
        assertTrue(cache instanceof BasicAuthCache);        

        HttpHost host = new HttpHost(
                conf.getHostname(), conf.getPort(), conf.getScheme());
        AuthScheme scheme = cache.get(host);
        
        assertThat(scheme, is(not(nullValue())));
        assertThat(scheme.getSchemeName(), is("basic"));
    }
    
    @Test
    public void testCredentialsProvider() {
        CredentialsProvider provider = authFactory.getCredentialsProvider();
        AuthScope scope = new AuthScope(conf.getHostname(), conf.getPort());
        
        Credentials creds = provider.getCredentials(scope);
        assertThat(creds, is(not(nullValue())));
        assertThat(creds.getUserPrincipal().getName(), is("key"));
        assertThat(creds.getPassword(), is("secret"));
    }
}
