package egree4j.models.documents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Types of documents that is supported within the API. Used within a 
 * {@code Document}.
 * 
 * @author Johan
 *
 */
public enum DocumentType {
    ORIGINAL("Original"),
    RECEIPT("Receipt"),
    HASH("Hash"),
    BATCH_FILE("BatchFile"),
    UNKNOWN("Unknown");
    
    private String apiName;
    
    private DocumentType(String name) {
        this.apiName = name;
    }
    
    /**
     * Returns the name of the DocumentType. This is the string
     * used in the API.
     * 
     * @return String representation of the enumeration.
     */
    @JsonValue
    public String getName() {
        return apiName;
    }
    
    /**
     * Resolves the DocumentType from the given event string.
     * 
     * @param type String representing a DocumentType.
     * @return The DocumentType enum for the given string.
     */
    @JsonCreator
    public static DocumentType resolve(String type) {
        for (DocumentType documentType : DocumentType.values()) {
            if (documentType.getName().equals(type)) {
                return documentType;
            }
        }
        
        throw new IllegalArgumentException(
                type + " is not a valid document type");
    }
}

