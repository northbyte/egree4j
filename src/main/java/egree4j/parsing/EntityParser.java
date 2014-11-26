package egree4j.parsing;

import org.apache.http.HttpEntity;

import egree4j.EgreeException;

/**
 * An entity parser is responsible for parsing a HTTP result from the
 * responses from the Egree services. Depending on how it is implemented
 * different parsers can be used (and different libraries).
 * 
 * @author Johan
 *
 */
public interface EntityParser {

    /**
     * Parses the content of the {@code HttpEntity} and returns it as a POJO
     * that can be used.
     * 
     * @param returnType Class of object that is expected to be returned.
     * @param entity Entity retrieved from the HTTP response.
     * @return Object mapped from the given entity.
     * @throws EgreeException
     */
    public <T> T parseEntity(Class<T> returnType, HttpEntity entity) 
            throws EgreeException;
    
    /**
     * Converts the given entity into a raw String valid to use as a HTTP POST
     * request content. In practice converts it to a JSON representation.
     * 
     * @param obj Object to be converted.
     * @return String (JSON) of the object.
     * @throws EgreeException If the conversion failed.
     */
    public String toContent(Object obj) throws EgreeException;
    
}
