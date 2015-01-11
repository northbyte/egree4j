package egree4j.parsing;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UrlCallbackFilterTest {

    @Test
    public void testFilterRawUrl() {
        String url = "some.url.com/home&run=yes";
        
        String filtered = UrlCallbackFilter.filter(url.getBytes());
        assertThat(filtered, is(url));
    }
    
    @Test
    public void testFilterQuotedUrl() {
        String url = "\"some.url.com/home&run=yes\"";
        
        String filtered = UrlCallbackFilter.filter(url.getBytes());
        assertThat(filtered, is("some.url.com/home&run=yes"));
    }
}
