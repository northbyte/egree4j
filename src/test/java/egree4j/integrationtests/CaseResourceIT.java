package egree4j.integrationtests;

import static egree4j.integrationtests.EgreeMatchers.assertCaseListContains;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.searching.FulltextQuery;
import egree4j.models.searching.MetadataQuery;

public class CaseResourceIT extends BaseIntegrationTest {
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCreateCase() throws Exception {
        List<Case> preCaseList = egree.searchCases(new FulltextQuery(""));
        
        Draft draft = createNewCase();
        String id = draft.getId();
        egree.createCase(draft);
        
        List<Case> postCaseList = egree.searchCases(new FulltextQuery(""));
        
        assertThat(postCaseList.size(), is(preCaseList.size() + 1));
        assertCaseListContains(postCaseList, id);
    }
    
    @Test
    public void testSearchCaseOnMetadata() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        egree.createCase(draft);
        
        MetadataQuery query = new MetadataQuery();
        query.getMetadata().put("foo", "bar");
        List<Case> result = egree.searchCases(query);
        
        assertThat(result.size(), is(greaterThanOrEqualTo(1)));
        assertCaseListContains(result, id);
    }
}
