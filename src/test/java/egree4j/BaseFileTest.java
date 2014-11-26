package egree4j;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;

public abstract class BaseFileTest {
    
    private File file;
    
    @Before
    public void setUp() throws URISyntaxException {
        this.file = new File(
                getClass().getResource("/testDocument.pdf").toURI());
    }
    
    protected File getTestFile() {
        return file; 
    }
}
