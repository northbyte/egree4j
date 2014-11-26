package egree4j.parsing;

/**
 * Factory class for creating {@code JacksonEntityParser}.
 * 
 * @author Johan
 *
 */
public class JacksonEntityParserFactory implements EntityParserFactory {
    private static final JacksonEntityParser INSTANCE = 
            new JacksonEntityParser();

    @Override
    public EntityParser getInstance() {
        return INSTANCE;
    }

}
