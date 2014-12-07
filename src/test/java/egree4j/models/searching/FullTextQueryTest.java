package egree4j.models.searching;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

import egree4j.models.cases.Status;

public class FullTextQueryTest {
    
    private FullTextQuery query;
    
    @Test
    public void testSingleParameterQueries() {
        query = new FullTextQuery("query");
        
        assertThat(query.getSearch(), is("query"));
        assertThat(query.getStatus(), is(nullValue()));
        assertThat(query.getSkip(), is(nullValue()));
        assertThat(query.getSort(), is(nullValue()));
        assertThat(query.getTake(), is(nullValue()));
        
        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(1));
        assertThat(params.get("search"), is("query"));
    }
    
    @Test
    public void testMultiParameterQuery() {
        query = new FullTextQuery("anotherQuery", Status.SENT);
        
        assertThat(query.getSearch(), is("anotherQuery"));
        assertThat(query.getStatus(), is(Status.SENT));
        assertThat(query.getSkip(), is(nullValue()));
        assertThat(query.getSort(), is(nullValue()));
        assertThat(query.getTake(), is(nullValue()));
        
        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(2));
        assertThat(params.get("search"), is("anotherQuery"));
        assertThat(params.get("status"), is("Sent"));
    }
    
    @Test
    public void testQueryWithPageParameters() {
        query = new FullTextQuery("newQuery", Status.FINISHED);
        query.setSkip(10);
        query.setSort(new Sort(Sort.Name.CREATED, Sort.Order.ASC));
        query.setTake(10);
        
        assertThat(query.getSearch(), is("newQuery"));
        assertThat(query.getStatus(), is(Status.FINISHED));
        assertThat(query.getSkip(), is(10));
        assertThat(query.getSort().getName(), is(Sort.Name.CREATED));
        assertThat(query.getSort().getOrder(), is(Sort.Order.ASC));
        assertThat(query.getTake(), is(10));
        
        Map<String, String> params = query.getParameters();
        assertThat(params.size(), is(5));
        assertThat(params.get("search"), is("newQuery"));
        assertThat(params.get("status"), is("Finished"));
        assertThat(params.get("skip"), is("10"));
        assertThat(params.get("take"), is("10"));
        assertThat(params.get("sort"), is("Created ASC"));
    }

}
