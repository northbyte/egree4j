package egree4j.http;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;


public class RequestFactoryTest {

    private RequestFactory requestFactory;

    @Before
    public void setUp() {
        requestFactory = new RequestFactory();
    }
    
    @BeforeClass
    public static void setUpOnce() {
        Logger logger = (Logger)LoggerFactory.getLogger(RequestFactory.class);
        logger.setLevel(Level.INFO);
    }
    
    @AfterClass
    public static void tearDownOnce() {
        Logger logger = (Logger)LoggerFactory.getLogger(RequestFactory.class);
        logger.setLevel(Level.DEBUG);
    }
    
    @Test
    public void testCreateSimpleGet() throws URISyntaxException {
        HttpGet get = requestFactory.createGet(
                "http", "localhost", 80, "/example");
        
        assertThat(get.getMethod(), is("GET"));
        assertThat(get.getURI().getScheme(), is("http"));
        assertThat(get.getURI().getHost(), is("localhost"));
        assertThat(get.getURI().getPort(), is(80));
        assertThat(get.getURI().getPath(), is("/example"));
        assertThat(get.getRequestLine().getUri(), 
                is("http://localhost:80/example"));
    }
    
    @Test
    public void testCreateGetWithoutPort() throws URISyntaxException {
        HttpGet get = requestFactory.createGet(
                "http", "localhost", null, "/example");
        
        assertThat(get.getURI().getPort(), is(-1));
        assertThat(get.getRequestLine().getUri(), 
                is("http://localhost/example"));
    }
    
    @Test
    public void testCreateGetWithParameters() throws URISyntaxException {
        NameValuePair nameParam = new RequestParameter("name", "donald");
        NameValuePair fooParam = new RequestParameter("foo", "bar");
        HttpGet get = requestFactory.createGet(
                "http", "localhost", null, "/example", nameParam, fooParam);
        
        assertThat(get.getRequestLine().getUri(), 
                is("http://localhost/example?name=donald&foo=bar"));
    }
    
    @Test
    public void testCreateSimplePost() throws URISyntaxException {
        HttpPost post = requestFactory.createPost(
                "http", "localhost", 80, "/example");
        
        assertThat(post.getMethod(), is("POST"));
        assertThat(post.getURI().getScheme(), is("http"));
        assertThat(post.getURI().getHost(), is("localhost"));
        assertThat(post.getURI().getPort(), is(80));
        assertThat(post.getURI().getPath(), is("/example"));
        assertThat(post.getRequestLine().getUri(), 
                is("http://localhost:80/example"));
    }
    
    @Test
    public void testCreatePostWithoutPort() throws URISyntaxException {
        HttpPost post = requestFactory.createPost(
                "https", "localhost", null, "/example");
        
        assertThat(post.getURI().getPort(), is(-1));
        assertThat(post.getRequestLine().getUri(), 
                is("https://localhost/example"));
    }
    
    @Test
    public void testCreatePostWithParameters() 
            throws URISyntaxException, IOException {
        NameValuePair nameParam = new RequestParameter("name", "donald");
        NameValuePair fooParam = new RequestParameter("foo", "bar");
        HttpPost post = requestFactory.createPost(
                "http", "localhost", null, "/example", nameParam, fooParam);
        
        String content = EntityUtils.toString(post.getEntity());
        
        assertThat(post.getRequestLine().getUri(), 
                is("http://localhost/example"));
        assertThat(content, is("name=donald&foo=bar"));
    }
    
    @Test
    public void testCreatePostWithEntity() 
            throws URISyntaxException, IOException {
        HttpEntity body = new StringEntity("{foo: 'bar', name: 'donald'}");
        HttpPost post = requestFactory.createPost(
                "https", "localhost", null, "/example", body);
        
        String content = EntityUtils.toString(post.getEntity());
        
        assertThat(post.getMethod(), is("POST"));
        assertThat(post.getRequestLine().getUri(), 
                is("https://localhost/example"));
        assertThat(content, is("{foo: 'bar', name: 'donald'}"));
    }
}
