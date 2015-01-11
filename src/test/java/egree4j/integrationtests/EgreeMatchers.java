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
     * of the cases contains a case with the given ID thise will assert to
     * true. Otherwise false.
     * 
     * @param cases Cases to lookup inside.
     * @param idToMatch ID to match with.
     */
    public static void assertCaseListContains(List<Case> cases, 
            String idToMatch) {
        Boolean found = false;
        for (Case c : cases) {
            if (c.getId().equals(idToMatch)) {
                found = true;
                break;
            }
        }
        assertThat(found, is(true));
    }
}
