package egree4j.integrationtests;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egree4j.Egree;
import egree4j.EgreeException;
import egree4j.EgreeFactory;
import egree4j.models.Party;
import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.cases.Status;
import egree4j.models.documents.DataDocument;
import egree4j.models.documents.Document;
import egree4j.models.documents.DocumentType;
import egree4j.models.searching.FulltextQuery;

/**
 * Base class for Integration Tests.
 * 
 * @author Johan
 *
 */
public abstract class BaseIntegrationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(
            BaseIntegrationTest.class);
    
    protected Egree egree;
    
    @Before
    public void setUp() throws Exception {
        egree = EgreeFactory.getInstance();
    }
    
    @After
    public void tearDown() throws Exception {
        for (Case c : egree.searchCases(new FulltextQuery(""))) {
            try {                
                // FIXME: Egree returns 500 on cases in draft, so we have to
                // send them first.
                if (c.getStatus() == Status.DRAFT) {
                    egree.sendCase(c);
                }
                
                egree.deleteCase(c);
            } catch (EgreeException ee) {
                logger.error("Failed to delete case", ee);
            }
        }
    }
    
    /**
     * Constructs a new Draft with a document attached to it. Will have a single
     * party and document inside it as well as a meta data value.
     * 
     * @return A new Draft case.
     * @throws Exception If failure occurs when reading the test file.
     */
    protected Draft createNewCase() throws Exception {
        Party party = new Party();
        party.setEmailAddress("user@example.com");
        party.setName("Dr. Test");

        Document doc = new DataDocument("TextDoc.txt", getTestFile());
        doc.setType(DocumentType.ORIGINAL);
        doc.processContents();

        Draft draft = new Draft("TestCase");
        draft.getMetadata().put("foo", "bar");
        draft.getParties().add(party);
        draft.getDocuments().add(doc);

        return draft;
    }
    
    
    private File getTestFile() throws URISyntaxException {
        return new File(getClass().getResource("/TextDoc.txt").toURI());
    }
}
