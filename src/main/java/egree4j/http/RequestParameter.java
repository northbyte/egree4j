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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestParameter other = (RequestParameter) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
