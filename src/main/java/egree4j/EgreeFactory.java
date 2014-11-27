package egree4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egree4j.config.Configuration;
import egree4j.config.ConfigurationContext;
import egree4j.http.AuthFactory;
import egree4j.http.BasicAuthFactory;

/**
 * Factory class that produces Egree implementations.
 * 
 * @author Johan
 *
 */
public final class EgreeFactory {
    private static final Logger logger = 
            LoggerFactory.getLogger(EgreeFactory.class);
    
    private static Egree INSTANCE;
    private static final Configuration CONFIGURATION;
    private static final AuthFactory AUTH;
    
    static {
        CONFIGURATION = ConfigurationContext.getConfiguration();
        AUTH = new BasicAuthFactory(CONFIGURATION);
    }
    
    /**
     * Returns an instance of an implementation of the {@code Egree} interface.
     * This implementation will already be configured using configurations
     * specified in the context.
     * 
     * The object returned will only be initialized once during the lifetime
     * of the jvm.
     * 
     * @return Configure Egree implementation.
     * @throws EgreeException If there is an error during configuration or setup.
     */
    public static Egree getInstance() throws EgreeException {
        if (INSTANCE == null) {
            INSTANCE = new EgreeImpl(CONFIGURATION, AUTH);
            if (logger.isDebugEnabled()) {
                logger.debug(CONFIGURATION.toString());
            }
        }
        return INSTANCE;
    }
}
