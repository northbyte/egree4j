package egree4j.models.agents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Roles that an {@code Agent} can assume.
 * 
 * @author Johan
 *
 */
public enum Role {
    LIMITED_AGENT("LimitedAgent"),
    AGENT("Agent"),
    ADMIN("Administrator"),
    MANAGER("Manager"),
    UNKNOWN("Unknown");
    
    private String apiName;
    
    private Role(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the Role. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the Role from the given event string.
     * 
     * @param roleName String representing a Role.
     * @return The Role enum for the given string.
     */
    @JsonCreator
    public static Role forValue(String roleName) {
        for (Role role : Role.values()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        
        throw new IllegalArgumentException(
                roleName + " is not a valid role");
    }
}
