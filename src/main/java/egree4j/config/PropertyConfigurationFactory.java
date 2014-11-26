package egree4j.config;

/**
 * Factory class that produces a {@code PropertyConfiguration}. This is a 
 * single instance that is kept in a static context.
 * 
 * @author Johan
 *
 */
public class PropertyConfigurationFactory implements ConfigurationFactory {
    private static final Configuration CONFIGURATION = new PropertyConfiguration();
    
    @Override
    public Configuration getInstance() {
        return CONFIGURATION;
    }

}
