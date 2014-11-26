package egree4j.models.documents;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import egree4j.EgreeException;
import egree4j.utils.FileUtils;

/**
 * 
 * 
 * @author Johan
 *
 */
@JsonDeserialize(as = BaseDocument.class)
public abstract class Document {
    private String id;
    private String filename;
    private String data;
    private String contentType;
    private Long size;
    private String hash;
    private DocumentType type;
    private Map<String, Object> formFields = new HashMap<>();
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public DocumentType getType() {
        return type;
    }
    public void setType(DocumentType type) {
        this.type = type;
    }
    public Map<String, Object> getFormFields() {
        return formFields;
    }
    public void setFormFields(Map<String, Object> formFields) {
        this.formFields = formFields;
    }
    
    /**
     * Verify that the file passed is not null and is properly readable.
     * 
     * @param file File to verify.
     */
    protected void verifyFile(File file) {
        if (file == null) {
            throw new NullPointerException("File is null!");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exists.");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Reference is not a file.");
        }
    }
    
    /**
     * If the content type is not set by the user, the library will try to
     * half-automagically resolve which content type the file is.
     * 
     * If the size is not set by the user, the library will try to calculate 
     * the size.
     * 
     * @param file File to check content type.
     * @throws EgreeException If an error occurs with the file.
     */
    protected void updateFileValues(File file) throws EgreeException {
        if (getContentType() == null) {
            setContentType(FileUtils.getContentType(file));
        }
        if (getSize() == null) {
            setSize(FileUtils.getSize(file));
        }
    }
    
    /**
     * Process to data contents of this {@code Document}. The data contents can
     * be either raw data (which in this case will mean the data is read) or
     * a raw hash information.
     * 
     * @throws EgreeException If the processing failed, for example file fail.
     */
    public abstract void processContents() throws EgreeException;
    
    @Override
    public String toString() {
        return "Document [id=" + id + ", filename=" + filename + ", data="
                + data + ", contentType=" + contentType + ", size=" + size
                + ", hash=" + hash + ", type=" + type + "]";
    }
}
