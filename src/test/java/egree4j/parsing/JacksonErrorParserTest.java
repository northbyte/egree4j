package egree4j.parsing;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import egree4j.models.utils.ServiceError;

@RunWith(MockitoJUnitRunner.class)
public class JacksonErrorParserTest {
    @Mock HttpResponse response;
    @Mock StatusLine statusLine;
    
    private StringEntity entity;
    private JacksonErrorParser errorParser;
    
    
    @Before
    public void setUp() {
        errorParser = new JacksonErrorParser();
        when(response.getStatusLine()).thenReturn(statusLine);
    }
    
    @Test
    public void testParseCorrectError() throws IOException {
        entity = new StringEntity("{\"error\":{\"errorCode\":\"E117\", "
                + "\"message\":\"Case can not be made editable\"}}");
        
        when(response.getEntity()).thenReturn(entity);
        when(statusLine.getStatusCode()).thenReturn(500);
        
        ServiceError serviceError = errorParser.parseError(response);
        
        assertThat(serviceError.getCode(), is(500));
        assertThat(serviceError.getErrorCode(), is("E117"));
        assertThat(serviceError.getMessage(), is("Case can not be made editable"));
    }
    
    @Test
    public void testParsePartialError() throws IOException {
        entity = new StringEntity("{\"error\":{\"errorCode\":\"E099\"}}");
        
        when(response.getEntity()).thenReturn(entity);
        when(statusLine.getStatusCode()).thenReturn(500);
        
        ServiceError serviceError = errorParser.parseError(response);
        
        assertThat(serviceError.getCode(), is(500));
        assertThat(serviceError.getErrorCode(), is("E099"));
        assertThat(serviceError.getMessage(), is(nullValue()));
    }
    
    @Test
    public void testParse404() throws IOException {
        entity = new StringEntity("");
        when(response.getEntity()).thenReturn(entity);
        when(statusLine.getStatusCode()).thenReturn(404);
        
        ServiceError serviceError = errorParser.parseError(response);
        
        assertThat(serviceError.getCode(), is(404));
        assertThat(serviceError.getErrorCode(), is(nullValue()));
        assertThat(serviceError.getMessage(), is("Not found"));
    }

    @Test(expected = IOException.class)
    public void  testParseEmptyReturnContent() throws IOException {
        entity = new StringEntity("");
        when(response.getEntity()).thenReturn(entity);
        when(statusLine.getStatusCode()).thenReturn(401);
        
        errorParser.parseError(response);
    }
}
