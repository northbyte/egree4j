package egree4j.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.apache.commons.codec.binary.Base64;

import egree4j.EgreeException;

/**
 * Utility class for performing simple file actions.
 * 
 * @author Johan
 *
 */
public abstract class FileUtils {
    
    /**
     * Returns the content type of the passed file. The evaluation is
     * done by using the {@code Files.probeContentType}, but since that is known 
     * to not be fully stable on UNIX systems in early JDK7 versions it falls
     * back to {@code URLConnection.guessContentTypeFromName}
     * 
     * @param file File to determine content type.
     * @return The content type, for example application/pdf.
     * @throws EgreeException If a file exception occurs.
     */
    public static String getContentType(File file) throws EgreeException {
        try {
            // Bug in JDK causes probeContentType to sometimes return null
            String contentType = null;
            contentType = Files.probeContentType(file.toPath());
            if (contentType != null) {
                return contentType;
            }
            
            return URLConnection.guessContentTypeFromName(
                    file.getAbsolutePath());
        } catch (IOException e) {
            throw new EgreeException("Failed to determine content type", e);
        }
    }
    
    /**
     * Returns the size of the passed file. 
     * 
     * @param file File to get size of.
     * @return Size in bytes.
     */
    public static long getSize(File file) {
        return file.length();
    }
    
    /**
     * Converts the file to a Base64 encoded data string. The string will be
     * relatively large as it will whole the full content of the file when
     * returned.
     * 
     * @param file File to read.
     * @return String with full data as a Base64 encoded.
     * @throws EgreeException If a file error occurs or encoding fails.
     */
    public static String encode(File file) throws EgreeException {
        String data = null;
        try (FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream out = new ByteArrayOutputStream()){
            int c;
            while ((c = fis.read()) != -1) {
                out.write(c);
            }
            
            data = new String(Base64.encodeBase64(out.toByteArray()), 
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new EgreeException("File to Base64 encode file", e);
        }
        
        return data;
    }
}