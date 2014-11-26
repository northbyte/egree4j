package egree4j.config;

/**
 * Configuration factory class that creates a Configuration.
 * 
 * @author Johan
 *
 */
public interface ConfigurationFactory {
    
    /**
     * Returns an instance of a {@code Configuration} that is the Factory is
     * producing. 
     * 
     * @return A configuration instance.
     */
    Configuration getInstance();
}
