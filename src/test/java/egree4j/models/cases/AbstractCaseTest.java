package egree4j.models.cases;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class AbstractCaseTest {
    
    private AbstractCase abstractCase;
    
    @Before
    public void setUp() {
        abstractCase = new TestCase();
    }
    
    @Test
    public void testTimeIsAlwaysUTC() {
        DateTime hereAndNow = new DateTime();
        DateTime utc = hereAndNow.toDateTime(DateTimeZone.UTC);
        
        abstractCase.setExpireOn(hereAndNow);
        
        assertThat(abstractCase.getExpireOn(), is(utc));
    }
    
    // To instantiate 
    private class TestCase extends AbstractCase {}
}
