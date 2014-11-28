package egree4j.models.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import egree4j.models.CaseEventSubscription;

/**
 * A pending case is a case that has not yet been pushed to the Egree service.
 * This is only used whenever a client wants to create a case.
 * 
 * @author Johan
 *
 */
public class PendingCase extends AbstractCase {
    private String agentUsername;
    private CaseEventSubscription eventCallback;
    
    protected PendingCase() {
        
    }
    
    /**
     * Constructs a new pending case with the given name as the case name. The
     * case will have a generated ID and the allowed signature type will be set
     * to the default of TOUCH and SMS.
     * 
     * @param name Name of the case.
     */
    public PendingCase(String name) {
        this(name, new ArrayList<SignatureType>(
                Arrays.asList(SignatureType.TOUCH, SignatureType.SMS)));
    }
    
    /**
     * Constructs a new pending case with the given name as the case name. The
     * case will have a generated ID and the allowed signature types will be set
     * to the given signatures.
     * 
     * @param name Name of the case.
     * @param allowedSignatureTypes Types of signatures allowed for the case.
     */
    public PendingCase(String name, List<SignatureType> allowedSignatureTypes) {
        setName(name);
        setId(UUID.randomUUID().toString());
        setAllowedSignatureTypes(allowedSignatureTypes);
    }
    
    /**
     * Returns the agent username responsible for the case (if set).
     * 
     * @return Username of agent responsible of the case or null.
     */
    public String getAgentUsername() {
        return agentUsername;
    }
    
    /**
     * Optionally specify an agent responsible for this case. When omitted, 
     * agent will be the authenticated user. Username is either your 
     * ExternalId or the Agents EmailAdress.
     * 
     * @param agentUsername The agent responsible of the case.
     */
    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }
    
    /**
     * Returns the subscription of the events to be notified whenever the
     * states change to specified {@code CaseEvent}s.
     * 
     * @return Subscription to be used on the case.
     */
    public CaseEventSubscription getEventCallback() {
        return eventCallback;
    }
    
    /**
     * Server side callback when one of your subscribed events occur.
     * 
     * @param eventCallback The callback to use whenever one of the requested
     * events occur.
     */
    public void setEventCallback(CaseEventSubscription eventCallback) {
        this.eventCallback = eventCallback;
    }

    @Override
    public String toString() {
        return "PendingCase [agentUsername=" + agentUsername
                + ", eventCallback=" + eventCallback + ", toString()="
                + super.toString() + "]";
    }
}
