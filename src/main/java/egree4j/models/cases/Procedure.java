package egree4j.models.cases;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Procedure type that a {@code Case} can follow.
 * 
 * @author Johan
 *
 */
public enum Procedure {
    DEFAULT("Default"),
    FORM("Form"),
    PAYLOAD("Payload"),
    BATCH("Batch"),
    UNKNOWN("Unknown");
    
    private String apiName;

    private Procedure(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the Procedure. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the Procedure from the given event string.
     * 
     * @param procedure String representing a Procedure.
     * @return The Procedure enum for the given string.
     */
    @JsonCreator
    public static Procedure resolve(String procedure) {
        for (Procedure procedureType : Procedure.values()) {
            if (procedureType.getName().equals(procedure)) {
                return procedureType;
            }
        }
        
        throw new IllegalArgumentException(
                procedure + " is not a valid procedure");
    }
}
