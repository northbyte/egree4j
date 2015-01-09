package egree4j.http;

import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class that creates HTTP URI requests. Requests created are built
 * using the UriBuilder, which means that values passed directly affects how
 * the finished URL will look like.
 * 
 * @author Johan
 *
 */
public class RequestFactory {
    private static final Logger logger = LoggerFactory.getLogger(
            RequestFactory.class);
    
    /**
     * Creates a new HTTP GET request. The request will use the passed scheme,
     * host, port and path and create a proper URL. The parameters passed will
     * only be used if there are any set.
     * 
     * <p>If the port passed is null, then the port will be based on the scheme
     * default port.</p>
     * 
     * @param scheme Scheme for the request (http, https etc).
     * @param host Target host name.
     * @param port Target port. Can be null which will be resolved by scheme.
     * @param path URI path to the resource.
     * @param parameters Parameters to be used as part of the request.
     * @return A created HTTP GET request ready to be used.
     * @throws URISyntaxException If the path and/or host is incorrect.
     */
    public HttpGet createGet(String scheme, String host, Integer port, 
            String path, NameValuePair... parameters) 
                    throws URISyntaxException {
        URIBuilder builder = buildURI(scheme, host, port, path);
        if (parameters.length > 0) {
            builder.setParameters(parameters);
        }
        
        HttpGet get = new HttpGet(builder.build());
        debug(get);
        
        return get;
    }
    
    /**
     * Creates a new HTTP POST request. The request will use the passed scheme,
     * host, port and path and create a proper URL. The passed parameters will
     * be put into the request body if there are any set.
     * 
     * <p>If the port passed is null, then the port will be based on the scheme
     * default port.</p>
     * 
     * <p>Use this method and passing no parameters to create a POST request 
     * that requires no parameters.</p>
     * 
     * @param scheme Scheme for the request (http, https etc).
     * @param host Target host name.
     * @param port Target port. Can be null which will be resolved by scheme.
     * @param path URI path to the resource.
     * @param parameters Parameters to be used as part of the request.
     * @return A created HTTP POST request ready to be used.
     * @throws URISyntaxException If the path and/or host is incorrect.
     */
    public HttpPost createPost(String scheme, String host, Integer port, 
            String path, NameValuePair... parameters) 
                    throws URISyntaxException {
        URIBuilder builder = buildURI(scheme, host, port, path);
        HttpPost post = new HttpPost(builder.build());
        if (parameters.length > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                    Arrays.asList(parameters), Consts.UTF_8);
            post.setEntity(entity);
        }
        
        debug(post);
        return post;
    }
    
    /**
     * Creates a new HTTP POST request. The request will use the passed scheme,
     * host, port and path and create a proper URL. The passed body will be
     * set as the body content of the request.
     * 
     * <p>If the port passed is null, then the port will be based on the scheme
     * default port.</p>
     * 
     * @param scheme Scheme for the request (http, https etc).
     * @param host Target host name.
     * @param port Target port.
     * @param path URI path to the resource.
     * @param body The entire body to be set in the HTTP POST.
     * @return A created HTTP POST request ready to be used.
     * @throws URISyntaxException If the path and/or host is incorrect.
     */
    public HttpPost createPost(String scheme, String host, Integer port, 
            String path, HttpEntity body) throws URISyntaxException {
        URIBuilder builder = buildURI(scheme, host, port, path);
        HttpPost post = new HttpPost(builder.build());
        post.setEntity(body);

        debug(post);
        return post;
    }
    
    /*
     * DRY method.
     * 
     * Constructs a URIBuilder with the settings passed. This will set the
     * port if given, or not if it is null.  
     */
    private URIBuilder buildURI(String scheme, String host, Integer port, 
            String path) {
        URIBuilder builder = new URIBuilder();
        builder.setScheme(scheme)
            .setHost(host)
            .setPath(path);

        if (port != null) {
            builder.setPort(port);
        }

        return builder;
    }

    /*
     * Debugs a request if debug is activated.
     */
    private void debug(HttpRequestBase request) {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating " + request.getMethod() + " request to" 
                    + " Scheme: " + request.getURI().getScheme()
                    + " Host: " + request.getURI().getHost()
                    + " Port: " + request.getURI().getPort()
                    + " Path: " + request.getURI().getPath());
            logger.debug("Full request: " + request.getRequestLine().getUri());
        }
    }
}
