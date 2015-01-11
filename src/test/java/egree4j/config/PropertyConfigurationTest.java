package egree4j.config;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PropertyConfigurationTest {

    @Test
    public void testSystemPropertyConfiguration()  {
        System.setProperty("egree4j.http.host", "test.egree.com");
        System.setProperty("egree4j.auth.key", "myKey");
        System.setProperty("egree4j.auth.secret", "mySecret");
        
        PropertyConfigurationFactory factory =
                new PropertyConfigurationFactory();
        Configuration config = factory.getInstance();
        
        assertThat(config.getHostname(), is("test.egree.com"));
        assertThat(config.getAuthenticationKey(), is("myKey"));
        assertThat(config.getAuthenticationPassword(), is("mySecret"));
        assertThat(config.getPort(), is(443)); // Default
    }
    
    
}
