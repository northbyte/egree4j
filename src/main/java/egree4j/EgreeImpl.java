package egree4j;

import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egree4j.config.Configuration;
import egree4j.http.AuthFactory;
import egree4j.http.DefaultHttpClientFactory;
import egree4j.http.HttpClientFactory;
import egree4j.http.RequestHandler;
import egree4j.http.RequestParameter;
import egree4j.http.RequestParameterMapper;
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
    private static final ContentType CONTENT_TYPE = ContentType.create("application/json", StandardCharsets.UTF_8);
    
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
                new StringEntity(parser.toContent(draft), CONTENT_TYPE));
    }
    
    @Override
    public void createCaseFromTemplate(String templateId, String caseId, 
            String agentUsername) throws EgreeServiceException, EgreeException {
        caseId = (caseId == null) ? UUID.randomUUID().toString() : caseId;
        handler.post("/createcasefromtemplate", 
                new RequestParameter("templateId", caseId),
                new RequestParameter("caseId", templateId),
                new RequestParameter("agentUsername", agentUsername));
    }
    
    @Override
    public void updateCase(Case caseToUpdate) 
            throws EgreeServiceException, EgreeException {
        handler.post("/updatecase", 
                new StringEntity(parser.toContent(caseToUpdate), CONTENT_TYPE));
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
        NameValuePair[] parameters = RequestParameterMapper.unwrap(
                query.getParameters());
        
        byte[] result = handler.get("/findcases", parameters);
        return Arrays.asList(parser.parseEntity(Case[].class, result));
    }
    
    @Override
    public List<Case> searchTemplates(MetadataQuery query) 
        throws EgreeException {
        NameValuePair[] parameters = RequestParameterMapper.unwrap(
                query.getParameters());
        
        byte[] result = handler.get("/findtemplates", parameters);
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
            byte[] result = handler.post("/createssoticket", 
                    new StringEntity(parser.toContent(sso), CONTENT_TYPE));
            return new String(result, StandardCharsets.UTF_8);
        } catch (UnsupportedCharsetException | ParseException e) {
            throw new EgreeException("Unexpected return data from service", e);
        }
    }

    @Override
    public byte[] getDocument(String id) throws EgreeException {
        logger.debug("Reading document from response entity ");
        return handler.get("/getdocumentdata", 
                new RequestParameter("documentId", id));
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
