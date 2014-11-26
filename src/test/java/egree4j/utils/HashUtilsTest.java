package egree4j.utils;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Test;

import egree4j.EgreeException;
import egree4j.BaseFileTest;

public class HashUtilsTest extends BaseFileTest {
    
    @Test(expected = EgreeException.class)
    public void testInvalidHash() throws EgreeException {
        String type = "inv";
        HashUtils.hash(getTestFile(), type);
        
        fail("Exception for missing type not thrown");
    }
    
    @Test(expected = EgreeException.class)
    public void testInvalidFile() throws URISyntaxException, EgreeException {
        File failFile = new File("missingDoc.pdf");
        HashUtils.hash(failFile);
        
        fail("Exception for missing file not thrown");
    }
    
    @Test
    public void testHashing() throws EgreeException {
        final String hash = "0a6dfe0ff3f20b3cf09c7ae9fc7e230d88597c9f198d8f2c3"
                + "eb1d6a7f1f750eb86907c42a5fa4300b6449477a50a1316e7af70afdee4"
                + "6e352db6d4084a362839";
        
        String computedHash = HashUtils.hash(getTestFile());
        assertThat(computedHash, equalTo(hash));
    }
}
