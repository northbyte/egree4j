package egree4j.models.documents;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import egree4j.BaseFileTest;

@RunWith(MockitoJUnitRunner.class)
public class DocumentTest extends BaseFileTest {
    
    @Mock private File mockedFile;
    
    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotExist() {
        when(mockedFile.exists()).thenReturn(false);
        Document test = new BaseDocument();
        
        test.verifyFile(mockedFile);
        fail("No exception thrown");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsDirectory() {
        when(mockedFile.exists()).thenReturn(true);
        when(mockedFile.isFile()).thenReturn(false);
        Document test = new BaseDocument();
        
        test.verifyFile(mockedFile);
        fail("No exception thrown");
    }
    
    @Test
    public void testValidFile() {
        Document test = new BaseDocument();
        test.verifyFile(getTestFile());
    }
}
