package egree4j.models.documents;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egree4j.EgreeException;
import egree4j.utils.HashUtils;

/**
 * A {@code HashDocument} is a {@link Document} that is only stored as a hash
 * within the Egree service. This prevents the document data from being stored
 * in another environment (due to security or similar) but still allows it to
 * be signed as that hash of the actual file will be allowed to be signed.
 * 
 * <p>The hash used is a SHA2-512 hash in accordance to the Egree API.</p>
 * 
 * @author Johan
 *
 */
@JsonIgnoreProperties({"fileToHash"})
public class HashDocument extends Document {
    
    private File fileToHash;
    
    /**
     * Constructs a new {@code HashDocument} that will hash the contents of
     * the passed file. Actual hashing will be performed when generating
     * all the content data. The file reference CANNOT be null.
     * 
     * @param filename Name of the file.
     * @param fileToHash File reference to hash.
     */
    public HashDocument(String filename, File fileToHash) {
        setFilename(filename);
        this.fileToHash = fileToHash;
        verifyFile(fileToHash);
    }

    @Override
    public void processContents() throws EgreeException {
        setHash(HashUtils.hash(fileToHash));
        updateFileValues(fileToHash);
        setData(null);
        
        if (getType() == null) {
            setType(DocumentType.HASH);
        }
    }

}
