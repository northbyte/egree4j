package egree4j.models.documents;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import egree4j.BaseFileTest;
import egree4j.EgreeException;

public class DataDocumentTest extends BaseFileTest {

    private DataDocument doc;
    
    @Before
    public void setUp() throws URISyntaxException {
        super.setUp();
        doc = new DataDocument("File", getTestFile());
    }
    
    @Test
    public void testProcessContents() throws EgreeException {
        assertThat(doc.getData(), is(nullValue()));
        assertThat(doc.getHash(), is(nullValue()));
        assertThat(doc.getContentType(), is(nullValue()));
        assertThat(doc.getSize(), is(nullValue()));
        
        doc.processContents();
        
        assertThat(doc.getData(), startsWith("JVBERi0xLjYNJeLjz9MNCjUzIDAgb2JqD"));
        assertThat(doc.getHash(), is(nullValue()));
        assertThat(doc.getContentType(), is("application/pdf"));
        assertThat(doc.getSize(), is(620099l));
    }
    
    @Test
    public void testSetValuesOnProcessContents() throws EgreeException {
        doc.setContentType("text/plain");
        doc.setData("a3gfvADVAsTcvsWWFc");
        doc.setHash("safasffsaff");
        doc.setSize(100000l);
        
        doc.processContents();
        
        assertThat(doc.getData(), startsWith("JVBERi0xLjYNJeLjz9MNCjUzIDAgb2JqD"));
        assertThat(doc.getHash(), is(nullValue()));
        assertThat(doc.getContentType(), is("text/plain"));
        assertThat(doc.getSize(), is(100000l));
    }
    
}
