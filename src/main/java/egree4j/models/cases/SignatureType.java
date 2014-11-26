package egree4j.models.cases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of valid ways of signing a {@code Case}.
 * 
 * @author Johan
 *
 */
public enum SignatureType {
    ELECTRONIC_ID("ElectronicId"),
    TOUCH("Touch"),
    SMS("Sms"),
    UNKNOWN("Unknown");
    
    private String apiName;
    
    private SignatureType(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the SignatureType. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the SignatureType from the given event string.
     * 
     * @param signature String representing a Signature.
     * @return The SignatureType enum for the given string.
     */
    @JsonCreator
    public static SignatureType resolve(String signature) {
        for (SignatureType signatureType : SignatureType.values()) {
            if (signatureType.getName().equals(signature)) {
                return signatureType;
            }
        }
        
        throw new IllegalArgumentException(
                signature + " is not a valid signature type");
    }
}
