package egree4j.models.cases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible statuses that a {@code Case} can have.
 * 
 * @author Johan
 *
 */
public enum Status {
    PENDING("Pending"),
    DRAFT("Draft"),
    SENT("Sent"),
    FINISHED("Finished"),
    EXPIRED("Expired"),
    REJECTED("Rejected");
    
    private String apiName;
    
    private Status(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the Status. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the Status from the given event string.
     * 
     * @param status String representing a Status.
     * @return The Status enum for the given string.
     */
    @JsonCreator
    public static Status resolve(String status) {
        for (Status statusType : Status.values()) {
            if (statusType.getName().equals(status)) {
                return statusType;
            }
        }
        
        throw new IllegalArgumentException(
                status + " is not a valid status");
    }
}
