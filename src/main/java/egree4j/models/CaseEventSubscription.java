package egree4j.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A subscription to a {@code Case}. The subscription allows a user to define
 * which events should be notified and to where the notification should be
 * sent.
 * 
 * @author Johan
 *
 */
public class CaseEventSubscription {
    private List<CaseEvent> events = new ArrayList<>();
    private String url;
    
    public List<CaseEvent> getEvents() {
        return events;
    }
    
    public void setEvents(List<CaseEvent> events) {
        this.events = events;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "CaseEventSubscription [events=" 
                + events + ", url=" + url + "]";
    }
}
