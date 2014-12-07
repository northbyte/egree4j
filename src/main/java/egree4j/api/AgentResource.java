package egree4j.api;

import egree4j.EgreeException;
import egree4j.models.agents.Agent;

/**
 * Resource interface for Agent management.
 * 
 * @author Johan
 *
 */
public interface AgentResource {
    
    /**
     * Creates a new Agent in the Egree service. This action is safe to repeat.
     * If the agent already exists, nothing happens.
     * 
     * @param agent Agent to be created.
     * @throws EgreeException If there are missing values or a connection 
     * problem occurred.
     */
    void createAgent(Agent agent) throws EgreeException;
    
    /**
     * Creates a single sign on ticket for an agent. Redirect your user to the 
     * returned url to log them in. Ticket is valid for 3 minutes and can only 
     * be used once.
     * 
     * @param username The username to create the login page for.
     * @param targetUrl The target URL to be returned to.
     * @return An URL that the username can user to log on to and then be
     * redirected back to the targetUrl.
     * @throws EgreeException If an error occurred in the Egree service.
     */
    String singleSignOn(String username, String targetUrl) 
            throws EgreeException;
}
