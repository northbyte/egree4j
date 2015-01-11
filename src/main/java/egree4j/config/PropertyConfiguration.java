package egree4j.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration based on system properties passed/set or configuration file
 * used.
 * 
 * @author Johan
 *
 */
public class PropertyConfiguration extends DefaultConfiguration {
    private static final String HOSTNAME = "egree4j.http.host";
    private static final String REST_BASE_URL = "egree4j.restBaseUrl";
    private static final String TEST_ENABLED = "egree4j.test.enabled";
    
    private static final String PORT = "egree4j.http.port";
    private static final String SCHEME = "egree4j.http.scheme";
    private static final String ENTITY_PARSER_FACTORY = "egree4j.http.entityParserFactory";
    
    private static final String AUTHENTICATION_KEY = "egree4j.auth.key";
    private static final String AUTHENTICATION_SECRET = "egree4j.auth.secret";
    
    private static final String PROPERTY_FILE = "egree4j.properties";
    
    public PropertyConfiguration() {
        initialize(load());
    }
    
    private Properties load() {
        Properties properties = null;
        
        try {
            properties = System.getProperties();
        } catch (SecurityException se) {
            properties = new Properties();
        }
        
        loadPropertyFile(properties, "." + File.separatorChar + PROPERTY_FILE);
        loadPropertyStream(properties, PropertyConfiguration.class.getResourceAsStream("/" + PROPERTY_FILE));
        loadPropertyStream(properties, PropertyConfiguration.class.getResourceAsStream("/WEB-INF/" + PROPERTY_FILE));
        
        return properties;
    }
    
    private void loadPropertyStream(Properties properties, InputStream input) {
        try {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            // Ignore, the file probably doesn't exist
        } catch (SecurityException se) {
            // Ignore, usually applet or Google App Engine
        }
    }
    
    private void loadPropertyFile(Properties properties, String filename) {
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            try (FileInputStream input = new FileInputStream(file)) {
                loadPropertyStream(properties, input);   
            } catch (IOException e) {
                // Ignore, file not found so then we just don't load from this
            } 
        }
    }
    
    private void initialize(Properties properties) {
        if (isSet(properties, HOSTNAME)) {
            setHostname(properties.getProperty(HOSTNAME));
        }
        if (isSet(properties, REST_BASE_URL)) {
            setRestBaseUrl(properties.getProperty(REST_BASE_URL));
        }
        if (isSet(properties, PORT)) {
            setPort(toInt(properties.getProperty(PORT)));
        }
        if (isSet(properties, SCHEME)) {
            setScheme(properties.getProperty(SCHEME));
        }
        if (isSet(properties, ENTITY_PARSER_FACTORY)) {
            setEntityparserFactory(properties.getProperty(ENTITY_PARSER_FACTORY));
        }
        if (isSet(properties, TEST_ENABLED)) {
            setTestEnabled(toBool(properties.getProperty(TEST_ENABLED)));
        }
        if (isSet(properties, AUTHENTICATION_KEY)) {
            setAuthenticationKey(properties.getProperty(AUTHENTICATION_KEY));
        }
        if (isSet(properties, AUTHENTICATION_SECRET)) {
            setAuthenticationPassword(properties.getProperty(AUTHENTICATION_SECRET));
        }
    }

    private boolean isSet(Properties properties, String key) {
        return (properties.containsKey(key) && properties.get(key) != null);
    }
    
    private boolean toBool(String value) {
        return Boolean.valueOf(value);
    }
    
    private int toInt(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public String toString() {
        return "PropertyConfiguration [getHostname()=" + getHostname()
                + ", getRestBaseUrl()=" + getRestBaseUrl() + ", getPort()="
                + getPort() + ", getScheme()=" + getScheme()
                + ", isTestEnabled()=" + isTestEnabled()
                + ", getAuthenticationKey()=***"
                + ", getAuthenticationPassword()=***"
                + ", getEntityParserFactory()="
                + getEntityParserFactory() + "]";
    }
    
    
}
