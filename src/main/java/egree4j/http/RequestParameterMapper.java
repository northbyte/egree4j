package egree4j.http;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;

/**
 * Class that unwraps a map into usable lists to be sent as request headers
 * or URL parameters in a GET or POST.
 * 
 * @author Johan
 *
 */
public class RequestParameterMapper {
    
    /**
     * Unwraps the given Map of key-value pairs into an array of NameValuePairs
     * that can be sent in a GET or POST.
     * 
     * @param map Map with key value pairs.
     * @return Array of parameters extracted from the map.
     */
    public static NameValuePair[] unwrap(Map<String, String> map) {
        if (map == null) {
            return new NameValuePair[0];
        }
        
        NameValuePair[] parameters = new NameValuePair[map.size()];
        int idx = 0;
        
        Iterator<Entry<String, String>> values = map.entrySet().iterator();
        while (values.hasNext()) {
            Entry<String, String> value = values.next();
            parameters[idx++] = new RequestParameter(
                    value.getKey(), value.getValue());
        }
        
        return parameters;
    }
}
