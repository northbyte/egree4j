package egree4j;

/**
 * Instances of this exception is directly source from an unexpected/faulty
 * response from Egree. Egree has a set of error response codes depending on
 * what action was performed. These are directly mapped into this.
 * 
 * @author Johan
 *
 */
public class EgreeServiceException extends EgreeException {
    private static final long serialVersionUID = 2019664418801256237L;
    
    private String errorCode;
    private Integer returnCode;
    
    /**
     * Creates a new Egree Service Exception with the given errorCode sent from
     * Egree and its associated errorMessage. The return code should be the
     * HTTP code sent from the server.
     * 
     * @param errorCode Egree error code.
     * @param errorMessage Egree error message (usually associated with error
     * code)
     * @param returnCode The HTTP return code from the Egree server.
     */
    public EgreeServiceException(String errorCode, String errorMessage, 
            Integer returnCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.returnCode = returnCode;
    }

    /**
     * Returns the Egree error code returned from the Egree service.
     * 
     * @return Egree service error code.
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Returns the HTTP Response code returned by the Egree service (404, 500,
     * 502 etc).
     * 
     * @return HTTP response code returned by Egree.
     */
    public Integer getReturnCode() {
        return returnCode;
    }
    
    /**
     * Returns the error message associated with the Egree error code (if
     * applicable).
     * 
     * @return Egree service error message.
     */
    public String getErrorMessage() {
        return getMessage();
    }
}
