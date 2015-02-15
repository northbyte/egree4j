package egree4j.config;

/**
 * Context that defines the {@code Configuration} to load. This can be modified
 * by passing a system property {@code -Degree4j.configurationFactory}, but will
 * by default load the [@code PropertyConfiguration}.
 * 
 * @author Johan
 *
 */
public final class ConfigurationContext {
    private static final String DEFAULT_CONFIG_FACTORY = "egree4j.config.PropertyConfigurationFactory";
    private static final String CONFIG_PROP_KEY = "egree4j.configurationFactory";
    
    private static final ConfigurationFactory configurationFactory;
    
    static {
        try {
            String factory = System.getProperty(CONFIG_PROP_KEY, DEFAULT_CONFIG_FACTORY);
            configurationFactory = (ConfigurationFactory) Class.forName(factory).newInstance();
        } catch (InstantiationException 
                | IllegalAccessException
                | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    /**
     * Loads the configuration using a proper configuration factory.
     * 
     * @return Configuration loaded into the context.
     */
    public static Configuration getConfiguration() {
        return configurationFactory.getInstance();
    }
    

}
