package egree4j.http;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.junit.Test;

public class RequestParameterMapperTest {

    @Test
    public void testEmptyMapUnwrap() {
        NameValuePair[] pairs = RequestParameterMapper.unwrap(null);
        NameValuePair[] morePairs = RequestParameterMapper.unwrap(
                new HashMap<String, String>());
        
        assertThat(pairs, is(not(nullValue())));
        assertThat(pairs.length, is(0));
        assertThat(morePairs, is(not(nullValue())));
        assertThat(morePairs.length, is(0));
    }
    
    @Test
    public void testUnwrap() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "myKey");
        params.put("secret", "mySecret");
        
        NameValuePair[] expected = new NameValuePair[2];
        expected[0] = new RequestParameter("key", "myKey");
        expected[1] = new RequestParameter("secret", "mySecret");

        NameValuePair[] pairs = RequestParameterMapper.unwrap(params);
        
        assertThat(pairs, is(not(nullValue())));
        assertThat(pairs.length, is(2));
        assertThat(pairs, arrayContainingInAnyOrder(expected));
    }
}
