package egree4j.integrationtests;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import egree4j.models.cases.Case;

/**
 * Class container matchers for repeated testing in IntegrationTests.
 * 
 * @author Johan
 *
 */
public abstract class EgreeMatchers {

    /**
     * Matches the given ID with the cases in the passed case list. If any
     * of the cases contains a case with the given ID this will assert to
     * true. Otherwise false.
     * 
     * @param cases Cases to lookup inside.
     * @param idToMatch ID to match with.
     */
    public static void assertCaseListContains(List<Case> cases, 
            String idToMatch) {
        assertThat(caseListContains(cases, idToMatch), is(true));
    }

    /**
     * Matches the given ID with the cases in the passed case list. If any
     * of the cases contains a case with the given ID this will assert to
     * false. Otherwise true. This is the opposite of assertCaseListContains.
     * 
     * @param cases Cases to lookup inside.
     * @param idToMatch ID to match with.
     */
    public static void assertCaseListNotContains(List<Case> cases,
            String idToMatch) {
        assertThat(caseListContains(cases, idToMatch), is(false));
    }

    /*
     * DRY method.
     * 
     * Checks if a list of cases contains the ID.
     */
    private static Boolean caseListContains(List<Case> cases, String id) {
        Boolean found = false;
        for (Case c : cases) {
            if (c.getId().equals(id)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
