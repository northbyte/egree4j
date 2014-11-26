package egree4j;

/**
 * Base exception class for exceptions within the Egree library.
 * 
 * @author Johan
 *
 */
public class EgreeException extends Exception {
    private static final long serialVersionUID = 8729633060636676576L;
    
    /**
     * Constructs a new EgreeException with the given message as cause message.
     * 
     * @param message Cause of exception.
     */
    public EgreeException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new EgreeException with the given message as cause message
     * and the given throwable to allow proper tracing.
     * 
     * @param message Cause of exception.
     * @param throwable Exception source.
     */
    public EgreeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
