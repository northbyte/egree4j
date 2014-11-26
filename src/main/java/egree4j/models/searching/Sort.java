package egree4j.models.searching;

/**
 * Class that defines sorting methods within a metadata search. 
 * 
 * @author Johan
 *
 */
public class Sort {
    private Name name;
    private Order order;
    
    public Sort(Name name, Order order) {
        this.name = name;
        this.order = order;
    }
    
    public Name getName() {
        return name;
    }
    
    public Order getOrder() {
        return order;
    }
    
    /**
     * Returns the string of the Sort that is valid to send as a parameter for
     * querying.
     * 
     * @return Valid query parameter string.
     */
    public String toQuery() {
        return name.toQueryString() + " " + order.name();
    }
    
    /**
     * Name enum of valid ordering columns in Egree.
     */
    public enum Name {
        NAME,
        STATUS,
        CREATED;
        
        protected String toQueryString() {
            return Character.toUpperCase(this.name().charAt(0)) 
                + this.name().substring(1);
        }
    }
    
    /**
     * Ordering directions.
     */
    public enum Order {
        ASC,
        DESC;
    }
}
