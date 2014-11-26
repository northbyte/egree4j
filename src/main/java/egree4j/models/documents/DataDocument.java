package egree4j.models.documents;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egree4j.EgreeException;
import egree4j.utils.FileUtils;

/**
 * Document type used when creating a document based on data. This document
 * will carry the data to be stored in Egrees service, which means that it
 * should not be used when the data is secret and needs to be kept locally.
 * 
 * @author Johan
 *
 */
@JsonIgnoreProperties({"fileToRead"})
public class DataDocument extends Document {
    
    private File fileToRead;
    
    /**
     * Creates a new {@code DataDocument} which will process the data contents
     * of the file attached. When sent to the Egree service, the files contents
     * will be sent with the doc.
     * 
     * @param filename Name of the document.
     * @param file File reference to the document.
     */
    public DataDocument(String filename, File file) {
        setFilename(filename);
        this.fileToRead = file;
        verifyFile(file);
    }

    @Override
    public void processContents() throws EgreeException {
        setData(FileUtils.encode(fileToRead));
        updateFileValues(fileToRead);
        setHash(null);
    }

}
