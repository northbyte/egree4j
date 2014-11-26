package egree4j.api;

import egree4j.EgreeException;

public interface DocumentResource {

    byte[] getDocument(String id) throws EgreeException;
}
