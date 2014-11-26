package egree4j.http;

import org.apache.http.NameValuePair;

/**
 * Request parameter to be used whenever a GET requests needs parameters
 * to be passed. 
 * 
 * @author Johan
 *
 */
public class RequestParameter implements NameValuePair {
    
    private String name;
    private String value;
    
    /**
     * Creates a new request parameter with the given name and value.
     * 
     * @param name Name of the parameter.
     * @param value Value of the parameter.
     */
    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

}
