package egree4j.models.searching;

import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import egree4j.models.cases.Status;

public class MetadataQueryTest {
    
    private MetadataQuery query;
    
    @Test
    public void testSingleParameterQuery() {
        query = new MetadataQuery();
        query.setAgentUsername("foo");
        
        assertThat(query.getAgentUsername(), is("foo"));
        assertThat(query.getStatus(), is(nullValue()));
        assertThat(query.getSkip(), is(nullValue()));
        assertThat(query.getSort(), is(nullValue()));
        assertThat(query.getTake(), is(nullValue()));
        
        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(1));
        assertThat(params.get("agentusername"), is("foo"));
    }

    @Test
    public void testMultipleParameterQuery() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        query = new MetadataQuery();
        query.setFromDate(new DateTime(20014, 11, 10, 13, 37));
        query.setPartyEmailAddress("foo@bar.com");
        query.setSkip(10);
        query.setSort(new Sort(Sort.Name.CREATED, Sort.Order.ASC));
        query.setTake(10);
        query.setStatus(Status.FINISHED);

        assertThat(query.getFromDate(), is(new DateTime(20014, 11, 10, 13, 37)));
        assertThat(query.getPartyEmailAddress(), is("foo@bar.com"));
        assertThat(query.getStatus(), is(Status.FINISHED));
        assertThat(query.getSkip(), is(10));
        assertThat(query.getSort().getName(), is(Sort.Name.CREATED));
        assertThat(query.getSort().getOrder(), is(Sort.Order.ASC));
        assertThat(query.getTake(), is(10));

        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(6));
        assertThat(params.get("fromdate"), is("20014-11-10T13:37:00.000Z"));
        assertThat(params.get("partyemailaddress"), is("foo@bar.com"));
        assertThat(params.get("status"), is("Finished"));
        assertThat(params.get("skip"), is("10"));
        assertThat(params.get("take"), is("10"));
        assertThat(params.get("sort"), is("Created ASC"));
    }
    
    @Test
    public void testMapParameterQuery() {
        query = new MetadataQuery();
        query.getFormData().put("a", "alpha");
        query.getFormData().put("b", "beta");
        query.getMetadata().put("1", "one");
        query.getMetadata().put("2", "two");
        
        assertThat(query.getFormData().size(), is(2));
        assertThat(query.getFormData(), hasEntry("a", "alpha"));
        assertThat(query.getFormData(), hasEntry("b", "beta"));
        assertThat(query.getMetadata().size(), is(2));
        assertThat(query.getMetadata(), hasEntry("1", "one"));
        assertThat(query.getMetadata(), hasEntry("2", "two"));
        assertThat(query.getStatus(), is(nullValue()));
        assertThat(query.getSkip(), is(nullValue()));
        assertThat(query.getSort(), is(nullValue()));
        assertThat(query.getTake(), is(nullValue()));
        
        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(4));
        assertThat(params.get("formdata[a]"), is("alpha"));
        assertThat(params.get("formdata[b]"), is("beta"));
        assertThat(params.get("metadata[1]"), is("one"));
        assertThat(params.get("metadata[2]"), is("two"));
    }
}
