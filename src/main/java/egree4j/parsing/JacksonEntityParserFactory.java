package egree4j.parsing;

import egree4j.config.Configuration;

/**
 * Factory class for creating {@code JacksonEntityParser}.
 * 
 * @author Johan
 *
 */
public class JacksonEntityParserFactory implements EntityParserFactory {
    private static JacksonEntityParser INSTANCE;
    
    @Override
    public EntityParser getInstance(Configuration configuration) {
        if (INSTANCE == null) {
            INSTANCE = new JacksonEntityParser();
            INSTANCE.setFailOnUnknownProperties(
                    configuration.getFailOnUnknownProperties());
        }
        return INSTANCE;
    }

}
