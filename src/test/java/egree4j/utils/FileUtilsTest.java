package egree4j.utils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import egree4j.BaseFileTest;
import egree4j.EgreeException;

public class FileUtilsTest extends BaseFileTest {
    
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
    
    @Test
    public void testReadContentType() throws EgreeException {
        String contentType = FileUtils.getContentType(getTestFile());
        assertThat(contentType, equalTo("application/pdf"));
    }
    
    @Test
    public void testReadFileSize() throws EgreeException {
        Long fileSize = FileUtils.getSize(getTestFile());
        assertThat(fileSize, equalTo(620099l));
    }
    
    @Test
    public void testEncode() throws EgreeException {
        String data = FileUtils.encode(getTestFile());
        assertThat(data.length(), equalTo(826800));
        assertThat(data.substring(0, 19), equalTo("JVBERi0xLjYNJeLjz9M"));
        assertThat(data.substring(data.length() - 19), 
                equalTo("TYxOTYwMA0lJUVPRg0="));
    }
    
    @Test
    public void testDecode() throws EgreeException, IOException {
        String fileData = FileUtils.encode(getTestTextFile());
        assertThat(fileData.length(), equalTo(118596));
        
        byte[] data = fileData.getBytes(StandardCharsets.UTF_8);
        File fileToWrite = temp.newFile("file.txt");
        
        FileUtils.decodeAndWrite(data, fileToWrite);
        
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileToWrite))) {
            String line = reader.readLine();
            assertThat(line, startsWith("Lorem ipsum dolor sit amet"));
        }
    }
}
