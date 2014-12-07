package egree4j.api;

import egree4j.EgreeException;

/**
 * Resource interface for managing Documents inside the Egree service.
 * 
 * @author Johan
 *
 */
public interface DocumentResource {

    /**
     * Retrieves the document with the given ID. The array returned will be
     * the raw byte data fetched from the Egree service. This does not say
     * anything about which type of file it is. 
     * 
     * @param id The id of the document inside the Egree service.
     * @return A byte array containing the full file data.
     * @throws EgreeException If the document doesn't exists, if the user is
     * unauthorized or the connection is faulty.
     */
    byte[] getDocument(String id) throws EgreeException;
}
