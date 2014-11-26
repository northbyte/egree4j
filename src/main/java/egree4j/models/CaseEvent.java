package egree4j.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Possible events that can be subscribed to within the 
 * {@link CaseEventSubscription}.
 * 
 * @author Johan
 *
 */
public enum CaseEvent {
    CREATED("Created"),
    SENT("Sent"),
    RECALLED("Recalled"),
    FINISHED("Finished"),
    EXPIRED("Expired"),
    DELETED("Deleted"),
    SIGNATURE_ADDED("SignatureAdded");
    
    private String apiName;
    
    private CaseEvent(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the CaseEvent. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the CaseEvent from the given event string.
     * 
     * @param value String representing a CaseEvent.
     * @return The CaseEvent enum for the given string.
     */
    @JsonCreator
    public static CaseEvent forValue(String value) {
        for (CaseEvent caseEvent : CaseEvent.values()) {
            if (caseEvent.getName().equals(value)) {
                return caseEvent;
            }
        }
        
        throw new IllegalArgumentException(value + " is not a valid event");
    }
}
