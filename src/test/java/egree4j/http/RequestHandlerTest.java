package egree4j.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import egree4j.config.Configuration;
import egree4j.config.DefaultConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class RequestHandlerTest {

    @Mock private CloseableHttpClient client; 
    @Mock private AuthFactory factory;
    private Configuration conf;
    
    private RequestHandler handler;
    
    @Before
    public void setUp() {
        conf = new DefaultConfiguration();
        handler = new RequestHandler(client, factory, conf);
    }
    
    @Test
    public void testCreateGetRequestWithoutParameters() {
        
    }
    
    @Test
    public void testCreateGetRequest() {
        
    }
    
    @Test
    public void testCreatePostRequest() {
        
    }
    
    @Test
    public void testCreatePostParameterEquest() {
        
    }
    
}
