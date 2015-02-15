package egree4j.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConfigurationContextTest {

    @Test
    public void testDefaultConfigurationContext() {
        Configuration config = ConfigurationContext.getConfiguration();
        
        assertTrue(config instanceof PropertyConfiguration);
    }
}
