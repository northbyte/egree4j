package egree4j.models;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import egree4j.utils.Culture;

public class PartyTest {

    private Party party;
    
    @Before
    public void setUp() {
        party = new Party();
    }
    
    @Test
    public void testPartyTimeIsAlwaysUTC() {
        DateTime hereAndNow = new DateTime();
        DateTime utc = hereAndNow.toDateTime(DateTimeZone.UTC);
        
        party.setSignedOn(hereAndNow);
        
        assertThat(party.getSignedOn(), is(utc));
    }
    
    @Test
    public void testCultures() {
        party.setCulture(Culture.EN);
        assertThat(party.getCulture(), is("en-US"));
        
        party.setCulture(Culture.SV);
        assertThat(party.getCulture(), is("sv-SE"));
        
        party.setCulture(Culture.FI);
        assertThat(party.getCulture(), is("fi-FI"));
    }
}
