package egree4j;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;

public abstract class BaseFileTest {
    private final String fileHash = "0a6dfe0ff3f20b3cf09c7ae9fc7e230d88597c9f19"
            + "8d8f2c3eb1d6a7f1f750eb86907c42a5fa4300b6449477a50a1316e7af70afde"
            + "e46e352db6d4084a362839";
    
    
    private File file;
    private File textFile;
    
    @Before
    public void setUp() throws URISyntaxException {
        this.file = new File(
                getClass().getResource("/PdfDoc.pdf").toURI());
        this.textFile = new File(
                getClass().getResource("/TextDoc.txt").toURI());
    }
    
    protected File getTestFile() {
        return file; 
    }
    
    protected String getTestFileHash() {
        return fileHash;
    }
    
    protected File getTestTextFile() {
        return textFile;
    }
}
