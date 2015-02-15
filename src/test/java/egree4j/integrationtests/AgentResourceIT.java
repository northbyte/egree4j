package egree4j.integrationtests;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import egree4j.models.agents.Agent;
@Ignore
public class AgentResourceIT extends BaseIntegrationTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testCreateAgent() throws Exception {
        Agent agent = new Agent();
        agent.setName("Test Tester");
        agent.setEmailAddress("null@deflexus.com");
        
        egree.createAgent(agent);
    }
    
    @Test
    public void testCreateSingleSignOn() throws Exception {
        String url = egree.singleSignOn(
                "null@deflexus.com", "null@deflexus.com/callback");

        assertThat(url, startsWith("https://test.egree.com/"));
    }
}
