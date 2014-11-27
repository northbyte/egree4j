package egree4j.parsing;

import java.io.IOException;

import org.apache.http.HttpResponse;

import egree4j.models.utils.ServiceError;

/**
 * Instances of this interface is responsible to process errors from Egree
 * and turn them into a valid class that the user can read and act on. 
 * 
 * @author Johan
 *
 */
public interface ErrorParser {

    /**
     * Parses the error sent from Egree from the given response. Will return
     * a service error.
     * 
     * @param response Response from Egree.
     * @return A parsed Service Error.
     * @throws IOException If the response cannot be parsed properly.
     */
    public ServiceError parseError(HttpResponse response) throws IOException;
}
