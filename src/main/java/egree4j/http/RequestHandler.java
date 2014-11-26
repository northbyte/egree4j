package egree4j.http;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import egree4j.EgreeException;
import egree4j.config.Configuration;

/**
 * Request handler that simplifies the GET and POST requests. It will utilize
 * the HttpClient passed.
 * 
 * @author Johan
 *
 */
public class RequestHandler {
    private CloseableHttpClient client;
    private AuthFactory auth;
    
    private String  scheme;     // https
    private String  host;       // app.egree.com
    private String  baseUrl;    // /app/v2
    private Integer port;       // 443
    
    
    public RequestHandler(CloseableHttpClient client, AuthFactory authFactory, 
            Configuration conf) {
        this.client = client;
        this.auth = authFactory;
        
        this.host = conf.getHostname();
        this.baseUrl = conf.getRestBaseUrl();
        this.port = conf.getPort();
        this.scheme = conf.getScheme();
    }

    /**
     * Performs a GET request to the given path. The base hostname and URL
     * has already been set up, which means that this should be the unique
     * URI of the resource to be accessed.
     * 
     * @param path Path to resource, for example /getcase.
     * @param parameters Optional parameters.
     * @return An http entity that has been received from the get.
     * @throws EgreeException If a connection or http error is occurred.
     */
    public HttpEntity get(String path, NameValuePair... parameters) 
            throws EgreeException {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme)
                .setHost(host)
                .setPort(port)
                .setPath(processPath(path));
            if (parameters.length > 0) {
                builder.setParameters(parameters);
            }
            
            HttpGet httpGet = new HttpGet(builder.build());
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpGet, getContext());
            
            return getEntity(response);
        } catch (IOException | URISyntaxException e) {
            throw new EgreeException(
                    "Unable to send request to Egree service", e);
        }
    }
    
    /**
     * Performs a POST request to the given path. The base hostname and URL
     * has already been set up, which means that this should be the unique
     * URI of the resource to be accessed.
     * 
     * @param path Path to resource, for example /addcase.
     * @param body The body of the HTTP post to send.
     * @return An http entity that has been received from the post.
     * @throws EgreeException If a connection or http error is occured.
     */
    public HttpEntity post(String path, HttpEntity body) throws EgreeException {
        try {
            HttpPost httpPost = new HttpPost(processPath(path));
            httpPost.setEntity(body);
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpPost, getContext());
            
            return getEntity(response);
        } catch (IOException e) {
            throw new EgreeException(
                    "Unable to post request to Egree service", e);
        }
    }
    
    /**
     * Performs a POST request to the given path. The base hostname and URL
     * has already been set up, which means that this should be the unique
     * URI of the resource to be accessed.
     * 
     * @param path Path to resource, for example /addcase.
     * @param parameters Post parameters that should be attached directly to the
     * HTTP Post content body.
     * @return An http entity that has been received from the post.
     * @throws EgreeException If a connection or http error is occured.
     */
    public HttpEntity post(String path, NameValuePair... parameters) 
            throws EgreeException {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme)
                .setHost(host)
                .setPort(port)
                .setPath(processPath(path));
            if (parameters.length > 0) {
                builder.setParameters(parameters);
            }
            HttpPost httpPost = new HttpPost(builder.build());
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpPost, getContext());
            
            return getEntity(response);
        } catch (IOException | URISyntaxException e) {
            throw new EgreeException(
                    "Unable to send request to Egree service", e);
        }
    }
    
    /*
     * DRY method.
     * Closes the response and returns the HTTP Entity from it.
     * 
     * Will throw an exception if the closing failed.
     */
    private HttpEntity getEntity(CloseableHttpResponse response) 
            throws IOException {
        try {
            return response.getEntity();
        } finally {
            response.close();
        }
    }
    
    /*
     * DRY method.
     * Creates a HTTP Context for each request using the authentication and 
     * credentials set.
     */
    private HttpClientContext getContext() {
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(auth.getCredentialsProvider());
        context.setAuthCache(auth.getAuthCache());
        return context;
    }
    
    /*
     * Ensure that the path is properly created and combined with the base URL
     * of the service.
     */
    private String processPath(String path) {
        if (!path.startsWith("/")) {
            return baseUrl + "/" + path;
        }
        return baseUrl + path;
    }    
}
