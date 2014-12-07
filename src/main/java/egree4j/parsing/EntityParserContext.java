package egree4j.parsing;

import egree4j.EgreeException;
import egree4j.config.Configuration;

/**
 * Context for creating {@code EntityParserFactory}.
 * 
 * @author Johan
 *
 */
public final class EntityParserContext {
    private static final String DEFAULT_PARSER_FACTORY = 
            "egree4j.parsing.JacksonEntityParserFactory";    
    private static EntityParserFactory FACTORY;
    
    /**
     * Returns a factory instance that produces {@code EntityParser}s. 
     * 
     * @param conf Configuration that defines the factory to use.
     * @return Factory ready to create EntityParsers.
     * @throws EgreeException If an instantiation exception occurs.
     */
    public static EntityParserFactory getFactory(Configuration conf) 
            throws EgreeException {
        if (FACTORY == null) {
            try {
                String factoryClass = null;
                if (conf.getEntityParserFactory() == null 
                        || conf.getEntityParserFactory().isEmpty()) {
                    factoryClass = DEFAULT_PARSER_FACTORY;
                } else {
                    factoryClass = conf.getEntityParserFactory();
                }
                
                FACTORY = (EntityParserFactory) 
                        Class.forName(factoryClass).newInstance();
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                throw new EgreeException(
                        "Failed to create proper EntityParser factory", e);
            }
        }
        
        return FACTORY;
    }

}
