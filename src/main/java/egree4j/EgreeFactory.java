package egree4j;

import egree4j.config.Configuration;
import egree4j.config.ConfigurationContext;
import egree4j.http.AuthFactory;
import egree4j.http.BasicAuthFactory;

public final class EgreeFactory {
    private static Egree INSTANCE;
    private static final Configuration CONFIGURATION;
    private static final AuthFactory AUTH;
    
    static {
        CONFIGURATION = ConfigurationContext.getConfiguration();
        AUTH = new BasicAuthFactory(CONFIGURATION);
    }
    
    public static Egree getInstance() throws EgreeException {
        if (INSTANCE == null) {
            new EgreeImpl(CONFIGURATION, AUTH);
        }
        return INSTANCE;
    }
}
