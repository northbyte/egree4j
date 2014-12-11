package egree4j.parsing;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import egree4j.BaseFileTest;
import egree4j.EgreeException;
import egree4j.models.CaseEvent;
import egree4j.models.CaseEventSubscription;
import egree4j.models.Party;
import egree4j.models.agents.Agent;
import egree4j.models.agents.Role;
import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.cases.Procedure;
import egree4j.models.cases.SignatureType;
import egree4j.models.cases.Status;
import egree4j.models.documents.BaseDocument;
import egree4j.models.documents.DataDocument;
import egree4j.models.documents.Document;
import egree4j.models.documents.DocumentType;
import egree4j.models.documents.HashDocument;

public class JacksonEntityParserTest extends BaseFileTest {
    
    private EntityParser parser;
    
    @Before
    public void setUp() throws URISyntaxException {
        super.setUp();
        parser = new JacksonEntityParser();
    }
    
    @Test
    public void testPartyToContent() throws EgreeException {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        Party party = new Party();
        party.setCulture("en-US");
        party.setEmailAddress("foo@bar.com");
        party.setName("John Doe");
        party.setAnyoneCanSign(false);
        party.setSignedOn(new DateTime(2014,11,1,15,0));
        
        String content = parser.toContent(party);

        assertThat(content, containsString("\"EmailAddress\":\"foo@bar.com\""));
        assertThat(content, containsString("\"Name\":\"John Doe\""));
        assertThat(content, containsString("\"AnyoneCanSign\":false"));
        assertThat(content, containsString("\"SignedOn\":\"2014-11-01T15:00:00.000Z\""));
    }
    
    @Test
    public void testContentToParty() throws EgreeException, IOException {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        String json  = readFile("/json/party.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Party party = parser.parseEntity(Party.class, EntityUtils.toByteArray(entity));
        DateTime correctTime = new DateTime(2014,11,2,12,0);
        
        assertThat(party.getName(), equalTo("Michael J. Fox"));
        assertThat(party.getEmailAddress(), equalTo("michael.j.fox@example.com"));
        assertThat(party.getCulture(), is(nullValue()));
        assertThat(party.getAnyoneCanSign(), is(false));
        assertThat(party.getSignedOn(), equalTo(correctTime));
    }
    
    @Test
    public void testCaseEventSubscriptionToContent() throws EgreeException {
        CaseEventSubscription sub = new CaseEventSubscription();
        sub.setUrl("http://example.com");
        sub.getEvents().add(CaseEvent.CREATED);
        sub.getEvents().add(CaseEvent.EXPIRED);
        sub.getEvents().add(CaseEvent.SIGNATURE_ADDED);
        
        String content = parser.toContent(sub);
        
        assertThat(content, containsString("\"Url\":\"http://example.com\""));
        assertThat(content, containsString("\"Events\":[\"Created\",\"Expired\",\"SignatureAdded\"]"));
    }
    
    @Test
    public void testContentToCaseEventSubscription() throws EgreeException, IOException {
        String json  = readFile("/json/caseeventsubscription.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        CaseEventSubscription sub = parser.parseEntity(CaseEventSubscription.class, 
                EntityUtils.toByteArray(entity));
        
        assertThat(sub.getUrl(), is("http://example.com"));
        assertThat(sub.getEvents().size(), is(2));
        assertThat(sub.getEvents(), contains(CaseEvent.SIGNATURE_ADDED, CaseEvent.CREATED));
    }
    
    @Test
    public void testAgentToContent() throws EgreeException {
        Agent agent = new Agent();
        agent.setCulture("en-US");
        agent.setName("Jane Doe");
        agent.setRole(Role.LIMITED_AGENT);
        
        String content = parser.toContent(agent);
        
        assertThat(content, containsString("\"Name\":\"Jane Doe\""));
        assertThat(content, containsString("\"Culture\":\"en-US\""));
        assertThat(content, containsString("\"Role\":\"LimitedAgent\""));
    }
    
    @Test
    public void testContentToAgent() throws EgreeException, IOException {
        String json  = readFile("/json/agent.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Agent agent = parser.parseEntity(Agent.class, EntityUtils.toByteArray(entity));
        
        assertThat(agent.getName(), is("John Smith"));
        assertThat(agent.getRole(), is(Role.LIMITED_AGENT));
        assertThat(agent.getId(), is("cipe-237"));
        assertThat(agent.getPhoneNumber(), is("+46708837292"));
        assertThat(agent.getEmailAddress(), is("john.smith@example.com"));
    }
    
    @Test
    public void testHashDocumentToContent() throws EgreeException {
        Document doc = new HashDocument("testDocument", getTestFile());
        doc.processContents();
        doc.getFormFields().put("expiry", "true");
        
        String content = parser.toContent(doc);
        
        assertThat(content, containsString("\"ContentType\":\"application/pdf\""));
        assertThat(content, containsString("\"Size\":620099"));
        assertThat(content, containsString("\"Filename\":\"testDocument\""));
        assertThat(content, containsString("\"Type\":\"Hash\""));
        assertThat(content, containsString("\"FormFields\":{\"expiry\":\"true\"}"));
        assertThat(content, containsString("\"Data\":null"));
    }
    
    @Test
    public void testContentToHashDocument() throws EgreeException, IOException {
        String json  = readFile("/json/hashdocument.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Document doc = parser.parseEntity(BaseDocument.class, EntityUtils.toByteArray(entity));
        
        assertThat(doc.getData(), is(nullValue()));
        assertThat(doc.getHash(), equalTo("0a6dfe0ff3f20b3cf09c7ae9fc7e230d88597c9f198d8f2c3eb1d6a7f1f750eb86907c42a5fa4300b6449477a50a1316e7af70afdee46e352db6d4084a362839"));
        assertThat(doc.getFilename(), is("agreement.pdf"));
        assertThat(doc.getContentType(), is("application/pdf"));
    }
    
    @Test
    public void testDataDocumentToContent() throws EgreeException {
        Document doc = new DataDocument("testDocument.pdf", getTestFile());
        doc.setType(DocumentType.ORIGINAL);
        doc.processContents();
        
        String content = parser.toContent(doc);
        
        assertThat(content, containsString("\"ContentType\":\"application/pdf\""));
        assertThat(content, containsString("\"Size\":620099"));
        assertThat(content, containsString("\"Filename\":\"testDocument.pdf\""));
        assertThat(content, containsString("\"Type\":\"Original\""));
        assertThat(content, containsString("\"FormFields\":{}"));
        assertThat(content, containsString("\"Data\":\"JVBERi0x"));
    }
    
    @Test
    public void testContentToDataDocument() throws EgreeException, IOException {
        String json  = readFile("/json/datadocument.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Document doc = parser.parseEntity(BaseDocument.class, EntityUtils.toByteArray(entity));
        
        assertThat(doc.getHash(), equalTo("0a6dfe0ff3f20b3cf09c7ae9fc7e230d88597c9f198d8f2c3eb1d6a7f1f750eb86907c42a5fa4300b6449477a50a1316e7af70afdee46e352db6d4084a362839"));
        assertThat(doc.getData(), is("1lbmRvYmoNc3RhcnR4cmVmDTYxOTYwMA0lJUVPRg0="));
        assertThat(doc.getContentType(), is("application/pdf"));
        assertThat(doc.getSize(), is(new Long(620099)));
        assertThat(doc.getType(), is(DocumentType.BATCH_FILE));
    }
    
    @Test
    public void testDraftToContent() throws EgreeException {
        Draft draft = new Draft("Foo");
        draft.setAgentUsername("Agent");
        draft.setRemindAfterDays(2);
        draft.getAllowedSignatureTypes().clear();
        draft.getAllowedSignatureTypes().add(SignatureType.SMS);
        draft.setDescription("A description");
        draft.getMetadata().put("data", "true");
        
        Document doc = new HashDocument("file.pdf", getTestFile());
        doc.processContents();
        draft.getDocuments().add(doc);
        draft.getParties().add(new Party());
        
        CaseEventSubscription callback = new CaseEventSubscription();
        callback.setUrl("http://example.com");
        callback.getEvents().add(CaseEvent.FINISHED);
        draft.setEventCallback(callback);
        
        String content = parser.toContent(draft);
        
        assertThat(content, containsString("\"AllowedSignatureTypes\":[\"Sms\"]"));
        assertThat(content, containsString("\"Metadata\":{\"data\":\"true\"}"));
        assertThat(content, containsString("\"Description\":\"A description\""));
        assertThat(content, containsString("\"AgentUsername\":\"Agent\""));
        assertThat(content, containsString("\"EventCallback\":{\"Events\":[\"Finished\"],\"Url\":\"http://example.com\"}"));
    }
    
    @Test
    public void testContentToDraft() throws EgreeException, IOException {
        String json  = readFile("/json/draftcase.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        
        // Normally this would be a Case, since JSON -> Draft never will
        // be of use in the library. But we test it anyway.
        Draft draft = parser.parseEntity(Draft.class, EntityUtils.toByteArray(entity));
        
        assertThat(draft.getParties().size(), is(1));
        assertThat(draft.getParties().get(0).getName(), is("Michael J. Fox"));
        assertThat(draft.getParties().get(0).getId(), is("major-party"));
        assertThat(draft.getDocuments().size(), is(1));
        assertThat(draft.getDocuments().get(0).getFilename(), is("agreement.pdf"));
        assertThat(draft.getAllowedSignatureTypes().size(), is(1));
        assertThat(draft.getAllowedSignatureTypes(), contains(SignatureType.ELECTRONIC_ID));
        assertThat(draft.getCulture(), is("en-US"));
        assertThat(draft.getSendFinishEmailToParties(), is(true));
        assertThat(draft.getName(), is("Emplyoment agreement 4132"));
        assertThat(draft.getId(), is("5a0e0866-252e-4b79-8a9b-466ea5cca5ce"));
        assertThat(draft.getAgentUsername(), is("Hulk Hogan"));
    }
    
    @Test
    public void testCaseToContent() throws EgreeException {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        Case current = new Case();
        current.setProcedure(Procedure.PAYLOAD);
        current.setExpireOn(new DateTime(2020,12,10,20,20));
        current.setStatus(Status.REJECTED);
        current.setCulture("en-US");
        current.setDescription("A description");
        
        Document doc = new HashDocument("file.pdf", getTestFile());
        doc.processContents();
        current.getDocuments().add(doc);
        current.getParties().add(new Party());
        
        String content = parser.toContent(current);
        assertThat(content, containsString("\"Description\":\"A description\""));
        assertThat(content, containsString("\"Culture\":\"en-US\""));
        assertThat(content, containsString("\"Filename\":\"file.pdf\""));
        assertThat(content, containsString("\"ExpireOn\":\"2020-12-10T20:20:00.000Z\""));
        assertThat(content, containsString("\"Procedure\":\"Payload\""));
        assertThat(content, containsString("\"Status\":\"Rejected\""));
    }
    
    @Test
    public void testContentToCase() throws EgreeException, IOException {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        String json  = readFile("/json/case.json");
        HttpEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        Case current = parser.parseEntity(Case.class, EntityUtils.toByteArray(entity));
        
        DateTime createdOn = new DateTime(2048,12,12,13,37,00);
        assertThat(current.getCreatedOn(), equalTo(createdOn));
        assertThat(current.getStatus(), is(Status.SENT));
        assertThat(current.getProcedure(), is(Procedure.DEFAULT));
        assertThat(current.getParties().size(), is(1));
        assertThat(current.getDocuments().size(), is(1));
        assertThat(current.getId(), is("5a0e0866-252e-4b79-8a9b-466ea5cca5ce"));
    }
    
    /*
     * Read the JSON files in the test environment to verify that the
     * json is converted properly.
     */
    private String readFile(String file) throws EgreeException {
        try (BufferedReader br = new BufferedReader(new FileReader(
                new File(getClass().getResource(file).toURI())))) {
            String contents = "";
            String line;
            while ((line = br.readLine()) != null) {
                contents += line;
            }
            return contents;
            
        } catch (URISyntaxException | IOException e) {
            throw new EgreeException("Fail", e);
        }
    }
}
