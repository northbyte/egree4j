package egree4j;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;

public abstract class BaseFileTest {
    
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
    
    protected File getTestTextFile() {
        return textFile;
    }
}
