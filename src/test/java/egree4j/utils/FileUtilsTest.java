package egree4j.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import egree4j.EgreeException;
import egree4j.BaseFileTest;

public class FileUtilsTest extends BaseFileTest {
    
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
}
