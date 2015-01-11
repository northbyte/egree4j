package egree4j.integrationtests;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;

public class DocumentResourceIT extends BaseIntegrationTest {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testDownloadDocument() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);
        
        String b64data = draft.getDocuments().get(0).getData();
        String localDocument = new String(
                Base64.decodeBase64(b64data), StandardCharsets.UTF_8);
        
        Case c = egree.getCase(id);
        byte[] bytes = egree.getDocument(id, c.getDocuments().get(0).getId());
        String downloadedDocument = new String(bytes, StandardCharsets.UTF_8);
        
        assertThat(downloadedDocument, is(localDocument));
    }
}
