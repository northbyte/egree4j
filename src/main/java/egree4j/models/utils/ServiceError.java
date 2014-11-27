package egree4j.models.utils;

/**
 * Class that represents the error message sent from Egree during HTTP
 * communication. Usually these have error codes and messages, which translates
 * into instances of this class.
 * 
 * @author Johan
 *
 */
public class ServiceError {
    private int code;
    private String errorCode;
    private String message;
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "ServiceError [code=" + code + ", errorCode=" + errorCode
                + ", message=" + message + "]";
    }
}
