package egree4j.parsing;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import egree4j.EgreeException;

/**
 * Entity parser that uses Jackson to parse the JSON in the content from the
 * HTTP entity. 
 * 
 * @author Johan
 *
 */
public class JacksonEntityParser implements EntityParser {    
    private ObjectMapper mapper;
    
    public JacksonEntityParser() {
        mapper = new ObjectMapper();
        // Egree uses Pascal cases since it is C#
        mapper.setPropertyNamingStrategy(
                new PropertyNamingStrategy.PascalCaseStrategy());
        mapper.registerModule(new JodaModule());
        mapper.setDateFormat(new ISO8601DateFormat());        
    }

    @Override
    public <T> T parseEntity(Class<T> returnType, HttpEntity entity) 
            throws EgreeException {
        try {
            EntityUtils.consume(entity);
            return mapper.readValue(EntityUtils.toByteArray(entity),
                    returnType);
        } catch (IOException e) {
            throw new EgreeException("Failed to read response content", e);
        }
    }

    @Override
    public String toContent(Object obj) throws EgreeException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new EgreeException("Failed to convert object to JSON", e);
        }
    }
}
