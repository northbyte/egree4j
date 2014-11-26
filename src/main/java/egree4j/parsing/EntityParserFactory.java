package egree4j.parsing;

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
     * @return EntityParser to convert HttpEntities to usable objects.
     */
    public EntityParser getInstance();
}
