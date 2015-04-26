package egree4j.parsing;

import egree4j.config.Configuration;

/**
 * Interface for Factory classes that return Entity Parsers.
 * 
 * @author Johan
 *
 */
public interface EntityParserFactory {

    /**
     * Returns the {@code EntityParser} from the implementing factory. The
     * parser is responsible for parsing HTTP Entities into usable java objects.
     * 
     * @param configuration Configuration with optional settings for the parser.
     * @return EntityParser to convert HttpEntities to usable objects.
     */
    public EntityParser getInstance(Configuration configuration);
}
