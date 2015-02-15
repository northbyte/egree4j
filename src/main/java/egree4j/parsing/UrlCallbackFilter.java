package egree4j.parsing;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter class that can be used to filter out a proper URL from a response.
 * 
 * @author Johan
 *
 */
public class UrlCallbackFilter {
    private static final Logger logger = LoggerFactory.getLogger(
            UrlCallbackFilter.class);

    /**
     * Filters out the URL in the data. This assumes that the data passed is
     * a string containing an URL. Useful for 
     * {@link egree4j.api.AgentResource#singleSignOn(String, String)}.
     * 
     * @param data Data containing a URL.
     * @return Filtered URL that is ready to use a proper URL.
     */
    public static String filter(byte[] data) {
        String result = new String(data, StandardCharsets.UTF_8);
        logger.debug("Filtering URL " + result);
        
        if (result.startsWith("\"")) {
            result = result.substring(1);
        }
        
        if (result.endsWith("\"")) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
}
