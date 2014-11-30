package egree4j;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egree4j.config.Configuration;
import egree4j.http.AuthFactory;
import egree4j.http.DefaultHttpClientFactory;
import egree4j.http.HttpClientFactory;
import egree4j.http.RequestHandler;
import egree4j.http.RequestParameter;
import egree4j.models.agents.Agent;
import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.documents.Document;
import egree4j.models.searching.MetadataQuery;
import egree4j.models.searching.Query;
import egree4j.models.utils.SingleSignOn;
import egree4j.parsing.EntityParser;
import egree4j.parsing.EntityParserContext;
import egree4j.parsing.ErrorParser;

/**
 * Implementation of the Egree interface.
 * 
 * @author Johan
 *
 */
public class EgreeImpl implements Egree {
    private static final Logger logger = LoggerFactory.getLogger(EgreeImpl.class);
    private static final ContentType CONTENT_TYPE = ContentType.create("application/json", "UTF-8");
    
    private Configuration       config;
    private AuthFactory         authentication;
    private EntityParser        parser;
    private ErrorParser         errorParser;
    private HttpClientFactory   httpFactory;
    private RequestHandler      handler;
    
    public EgreeImpl(Configuration config, AuthFactory auth) 
            throws EgreeException {
        this.authentication = auth;
        this.config = config;
        
        init();
    }
    
    @Override
    public void createCase(Draft draft) throws EgreeException {
        for (Document doc : draft.getDocuments()) {
            doc.processContents();
        }

        handler.post("/createcase", 
                new StringEntity(parser.toContent(draft),
                        CONTENT_TYPE));
    }

    @Override
    public void sendCase(Case caseToSend) throws EgreeException {
        sendCase(caseToSend.getId());
    }

    @Override
    public void sendCase(String id) throws EgreeException {
        handler.post("/sendcase", new RequestParameter("id", id));
    }

    @Override
    public void deleteCase(Case caseToDelete) throws EgreeException {
        deleteCase(caseToDelete.getId());
    }

    @Override
    public void deleteCase(String id) throws EgreeException {
        handler.post("/deletecase", new RequestParameter("id", id));
    }

    @Override
    public Case getCase(String id) throws EgreeException {
        return parser.parseEntity(Case.class, handler.get("/getcase", 
                new RequestParameter("id", id)));
    }

    @Override
    public List<Case> searchCases(Query query) throws EgreeException {
        Map<String, String> params = query.getParameters();
        NameValuePair[] parameters = new NameValuePair[params.size()];
        
        int idx = 0;
        Iterator<Entry<String, String>> values = params.entrySet().iterator();
        while (values.hasNext()) {
            Entry<String, String> value = values.next();
            parameters[idx] = new RequestParameter(value.getKey(), 
                    value.getValue());
            idx++;
        }
        
        HttpEntity result = handler.get("/findcases", parameters);
        return Arrays.asList(parser.parseEntity(Case[].class, result));
    }
    
    @Override
    public List<Case> searchTemplates(MetadataQuery query) 
        throws EgreeException {
        Map<String, String> params = query.getParameters();
        NameValuePair[] parameters = new NameValuePair[params.size()];
        
        int idx = 0;
        Iterator<Entry<String, String>> values = params.entrySet().iterator();
        while (values.hasNext()) {
            Entry<String, String> value = values.next();
            parameters[idx] = new RequestParameter(value.getKey(), 
                    value.getValue());
            idx++;
        }
        
        HttpEntity result = handler.get("/findtemplates", parameters);
        return Arrays.asList(parser.parseEntity(Case[].class, result));
    }

    @Override
    public void createAgent(Agent agent) throws EgreeException {        
        handler.post("/createagent", 
                new StringEntity(parser.toContent(agent),
                        CONTENT_TYPE));
    }

    @Override
    public String singleSignOn(String username, String targetUrl)
            throws EgreeException {
        SingleSignOn sso = new SingleSignOn(username);
        sso.setTargetUrl(targetUrl);
        
        try {
            return EntityUtils.toString(handler.post("/createssoticket", 
                    new StringEntity(parser.toContent(sso), CONTENT_TYPE)));
        } catch (UnsupportedCharsetException | ParseException | IOException e) {
            throw new EgreeException("Unexpected return data from service", e);
        }
    }

    @Override
    public byte[] getDocument(String id) throws EgreeException {
        HttpEntity entity = handler.get("/getdocumentdata", 
                new RequestParameter("documentId", id));
        if (entity != null) {
            logger.debug("Reading document from response entity "
                    + entity.toString());
            try {
                return EntityUtils.toByteArray(entity);
            } catch (IOException e) {
                throw new EgreeException("Download of document failed", e);
            }
        }
        throw new EgreeException("No data received!");
    }
    

    /*
     * Initialize the instance and create proper fields from the different
     * configurations.
     */
    private void init() throws EgreeException {
        this.parser = EntityParserContext.getFactory(config).getInstance();
        this.errorParser = parser.getErrorParser();
        this.httpFactory = new DefaultHttpClientFactory();
        this.handler = new RequestHandler(
                httpFactory.getInstance(authentication), 
                errorParser,
                authentication,
                config);
    }

}
