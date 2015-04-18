package egree4j.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import egree4j.EgreeException;

/**
 * Utility class to process hashing of documents.
 * 
 * @author Johan
 *
 */
public abstract class HashUtils {
    public static final String HASH_IMPL = "SHA-512";
    
    /**
     * Creates a hash of the given file using the passed hash type (SHA-256,
     * SHA-512, etc).
     * 
     * @param file File to hash.
     * @param hashType Type of hash to use.
     * @return A hash of the file.
     * @throws EgreeException If the hashing fails.
     */
    public static String hash(File file, String hashType) 
            throws EgreeException {
        return hashFile(file, getDigest(hashType));
    }

    /**
     * Creates a hash of the given file. The generated hash will use
     * the recommended implementation of the Egree service.
     * 
     * @param file File reference to be hashed.
     * @return A hash of the file.
     * @throws EgreeException If the hashing fails.
     */
    public static String hash(File file) throws EgreeException {
        return hashFile(file, getDigest(HashUtils.HASH_IMPL));
    }
    
    /*
     * Hashes the given file using the given message digest.
     */
    private static String hashFile(File file, MessageDigest messageDigest)
            throws EgreeException {
        try (DigestInputStream dis = new DigestInputStream(
                new FileInputStream(file), messageDigest)) {
            // We just want to read the stream
            while (dis.read() != -1);
            return Hex.encodeHexString(messageDigest.digest());
        } catch (IOException ioe) {
            throw new EgreeException("Failed to process file hash", ioe);
        }
    }

    /*
     * Get the digest we want to use or throw an exception if the
     * type is invalid.
     */
    private static MessageDigest getDigest(String type) throws EgreeException {
        try {
            return MessageDigest.getInstance(type);
        } catch (NoSuchAlgorithmException e) {
            throw new EgreeException("Invalid hash", e);
        }
    }
}
