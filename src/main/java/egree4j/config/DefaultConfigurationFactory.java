package egree4j.config;

/**
 * Factory class that creates default configurations. 
 * 
 * @author Johan
 *
 */
public class DefaultConfigurationFactory implements ConfigurationFactory {

    @Override
    public Configuration getInstance() {
        return new DefaultConfiguration();
    }
}
