package egree4j.models.documents;

import egree4j.EgreeException;

/**
 * This is a {@code Document} that is received from the Egree service. It can
 * be any type of document with any kind of settings (Hash, Data etc).
 * 
 * When creating a new document, it is advisable to use the {@link DataDocument}
 * or {@link HashDocument} as they have support methods for properly assigning
 * values.
 * 
 * @author Johan
 *
 */
public final class BaseDocument extends Document {
    
    @Override
    public void processContents() throws EgreeException {
        // do nothing
    }

}
