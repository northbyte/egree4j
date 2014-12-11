package egree4j.models.cases;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class CaseTest {

    private Case aCase;
    
    @Before
    public void setUp() {
        aCase = new Case();
    }
    
    /*
     * Note that for the CreatedOn, SentOn and ReminderSentOn these are values
     * that the client shouldn't set. They are set by the service.
     */
    @Test
    public void testTimeIsAlwaysUTC() {
        DateTime remindOn = new DateTime();
        DateTime remindOnUtc = remindOn.toDateTime(DateTimeZone.UTC);
        
        aCase.setRemindOn(remindOn);
        
        assertThat(aCase.getRemindOn(), is(remindOnUtc));
    }
}
