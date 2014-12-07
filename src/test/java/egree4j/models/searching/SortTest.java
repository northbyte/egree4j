package egree4j.models.searching;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SortTest {
    
    @Test
    public void testSortWithNameOnly() {
        Sort sort = new Sort(Sort.Name.NAME);
        
        assertThat(sort.getOrder(), is(nullValue()));
        assertThat(sort.getName(), is(Sort.Name.NAME));
        assertThat(sort.toQuery(), is("Name"));
    }
    
    @Test
    public void testSortWithAllValues() {
        Sort sort = new Sort(Sort.Name.STATUS, Sort.Order.DESC);
        
        assertThat(sort.getOrder(), is(Sort.Order.DESC));
        assertThat(sort.getName(), is(Sort.Name.STATUS));
        assertThat(sort.toQuery(), is("Status DESC"));
    }
}
