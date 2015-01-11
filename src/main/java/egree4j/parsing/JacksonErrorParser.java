package egree4j.parsing;

import java.io.IOException;
import java.io.StringReader;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import egree4j.models.utils.ServiceError;

/**
 * Error parser using Jackson libraries.
 * 
 * @author Johan
 *
 */
public class JacksonErrorParser implements ErrorParser {
    private static final Integer NOT_FOUND = 404;

    @Override
    public ServiceError parseError(HttpResponse response) throws IOException {
        ServiceError error = new ServiceError();
        error.setCode(response.getStatusLine().getStatusCode());
        
        // The 404 responds with a web page and not a JSON object.
        if (error.getCode() == NOT_FOUND) {
            error.setMessage("Not found");
            return error;
        }
        
        // Parse the JSON object. Note that this does not use the same property
        // naming scheme as the rest of the Egree services, so we have to parse
        // differently
        JsonNode root = readRootNode(response);
        JsonNode errorNode = root.get("error");
        if (errorNode != null) {
            JsonNode codeNode = errorNode.get("errorCode");
            if (codeNode != null) {
                error.setErrorCode(codeNode.asText());
            }
            
            JsonNode messageNode = errorNode.get("message");
            if (messageNode != null) {
                error.setMessage(messageNode.asText());
            }
        }
        return error;
    }
    
    /*
     * Reads the root json node from the response. This assumes that the
     * Egree service has responded with a proper JSON response.
     */
    private JsonNode readRootNode(HttpResponse response) throws IOException {
        String content = EntityUtils.toString(response.getEntity());
        return new ObjectMapper().readTree(new StringReader(content));
    }

}
