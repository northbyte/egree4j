package egree4j.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DefaultConfigurationFactoryTest {

    @Test
    public void testReturnsDefaultConfiguration() {
        DefaultConfigurationFactory factory = new DefaultConfigurationFactory();
        Configuration config = factory.getInstance();
        
        assertTrue(config instanceof DefaultConfiguration);
    }
}
