package egree4j.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egree4j.EgreeException;
import egree4j.EgreeServiceException;
import egree4j.config.Configuration;
import egree4j.models.utils.ServiceError;
import egree4j.parsing.ErrorParser;

/**
 * Request handler that simplifies the GET and POST requests. It will utilize
 * the HttpClient passed.
 * 
 * @author Johan
 *
 */
public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(
            RequestHandler.class);
    private static final Integer HTTP_OK = 200;
    
    private CloseableHttpClient client;
    private AuthFactory auth;
    private ErrorParser errorParser;
    
    private String  scheme;     // https
    private String  host;       // app.egree.com
    private String  baseUrl;    // /app/v2
    private Integer port;       // 443
    
    
    public RequestHandler(CloseableHttpClient client, ErrorParser errorParser,
            AuthFactory authFactory, Configuration conf) {
        this.client = client;
        this.auth = authFactory;
        this.errorParser = errorParser;
        
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
     * @return Entity content as raw byte data.
     * @throws EgreeServiceException If the Egree service sent a readable 
     * invalid response, indicating something is incorrect.
     * @throws EgreeException If a connection or http error is occurred.
     */
    public byte[] get(String path, NameValuePair... parameters) 
            throws EgreeServiceException, EgreeException {
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
            debug(httpGet);
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpGet, getContext());
            
            return checkResponse(response);
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
     * @return Entity content as raw byte data.
     * @throws EgreeServiceException If the Egree service sent a readable 
     * invalid response, indicating something is incorrect.
     * @throws EgreeException If a connection or http error is occured.
     */
    public byte[] post(String path, HttpEntity body) 
            throws EgreeServiceException, EgreeException {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme)
                .setHost(host)
                .setPort(port)
                .setPath(processPath(path));
            
            HttpPost httpPost = new HttpPost(builder.build());
            httpPost.setEntity(body);
            debug(httpPost);
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpPost, getContext());
            
            return checkResponse(response);
        } catch (IOException | URISyntaxException e) {
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
     * @return Entity content as raw byte data.
     * @throws EgreeServiceException If the Egree service sent a readable 
     * invalid response, indicating something is incorrect.
     * @throws EgreeException If a connection or http error is occured.
     */
    public byte[] post(String path, NameValuePair... parameters) 
            throws EgreeServiceException, EgreeException {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme)
                .setHost(host)
                .setPort(port)
                .setPath(processPath(path));
            
            HttpPost httpPost = new HttpPost(builder.build());
            if (parameters.length > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        Arrays.asList(parameters), Consts.UTF_8);
                httpPost.setEntity(entity);
            }
            
            debug(httpPost);
            CloseableHttpResponse response = client.execute(
                    new HttpHost(host, port, scheme), httpPost, getContext());
            
            return checkResponse(response);
        } catch (IOException | URISyntaxException e) {
            throw new EgreeException(
                    "Unable to send request to Egree service", e);
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
    
    /*
     * Reads the response from the Egree service. This will validate that the
     * response code is valid before it tries to read the entity data. If this
     * failed, an exception will be thrown indicating what error was seen
     * on the Egree service side.
     * 
     * This will consume the entity and close the response connection.
     */
    private byte[] checkResponse(CloseableHttpResponse response)
            throws EgreeServiceException, IOException {
        try {
            int returnCode = response.getStatusLine().getStatusCode();
            logger.debug("Return code is " + returnCode);

            if (returnCode != HTTP_OK) {
                ServiceError error = errorParser.parseError(response);
                throw new EgreeServiceException(error.getErrorCode(), 
                        error.getMessage(), error.getCode());
            }
            
            byte[] data = EntityUtils.toByteArray(response.getEntity());
            EntityUtils.consume(response.getEntity());  
            return data;
        } finally {
            response.close();
        }
        
    }
    
    /*
     * Debugs a request if debug is activated.
     */
    private void debug(HttpRequestBase request) {
        if (logger.isDebugEnabled()) {
            logger.debug("Sending " + request.getMethod() + " request to" 
                    + " Scheme: " + request.getURI().getScheme()
                    + " Host: " + request.getURI().getHost()
                    + " Port: " + request.getURI().getPort()
                    + " Path: " + request.getURI().getPath()
                    + " as request " + request.getRequestLine().getUri());
        }
    }
}
