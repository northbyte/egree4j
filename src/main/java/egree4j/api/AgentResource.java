package egree4j.api;

import egree4j.EgreeException;
import egree4j.models.agents.Agent;


public interface AgentResource {
    
    void createAgent(Agent agent) throws EgreeException;
    
    String singleSignOn(String username, String targetUrl) 
            throws EgreeException;
}
