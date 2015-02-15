package egree4j.integrationtests;

import static egree4j.integrationtests.EgreeMatchers.assertCaseListContains;
import static egree4j.integrationtests.EgreeMatchers.assertCaseListNotContains;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.cases.Status;
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
        processedCases.add(id);
        egree.createCase(draft);

        List<Case> postCaseList = egree.searchCases(new FulltextQuery(""));

        assertThat(postCaseList.size(), is(preCaseList.size() + 1));
        assertCaseListContains(postCaseList, id);
    }

    @Test
    public void testGetCase() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);

        Case c = egree.getCase(id);

        assertThat(c.getId(), is(draft.getId()));
        assertThat(c.getParties().size(), is(1));
        assertThat(c.getDocuments().size(), is(1));
        assertThat(c.getParties().get(0).getName(), 
                is(draft.getParties().get(0).getName()));
        assertThat(c.getDocuments().get(0).getFilename(), 
                is(draft.getDocuments().get(0).getFilename()));
    }

    @Test
    public void testDeleteCase() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);

        // FIXME: Fix once DRAFT deletion works in Egree
        egree.sendCase(id);

        egree.deleteCase(id);
        try {
            egree.getCase(id);
            fail("Case has not been deleted!");
        } catch (Exception ee) {
            // Pass
        }
    }

    @Test
    public void testSendCase() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);

        Case c = egree.getCase(id);
        assertThat(c.getStatus(), is(Status.DRAFT));

        egree.sendCase(c);
        c = egree.getCase(id);
        assertThat(c.getStatus(), is(Status.SENT));
    }

    @Test
    public void testUpdateCase() throws Exception {
        Draft draft = createNewCase();
        draft.setDescription("Some random description");
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);

        Case c = egree.getCase(id);
        c.setDescription("New description");
        egree.updateCase(c);
        c = egree.getCase(id);

        assertThat(c.getDescription(), is("New description"));
    }

    @Test
    public void testSearchCaseOnMetadata() throws Exception {
        Draft draft = createNewCase();
        String id = draft.getId();
        processedCases.add(id);
        egree.createCase(draft);

        MetadataQuery query = new MetadataQuery();
        query.getMetadata().put("foo", "bar");
        List<Case> result = egree.searchCases(query);

        query.getMetadata().remove("foo");
        query.getMetadata().put("bar", "foo");
        List<Case> anotherResult = egree.searchCases(query);

        assertThat(result.size(), is(greaterThanOrEqualTo(1)));
        assertCaseListContains(result, id);
        assertCaseListNotContains(anotherResult, id);
    }
}
