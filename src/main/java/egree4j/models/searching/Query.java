package egree4j.models.searching;

import java.util.HashMap;
import java.util.Map;

import egree4j.models.cases.Status;

/**
 * Base query class used for searching. This is used when searching after Cases
 * in the Egree service.
 * 
 * @author Johan
 *
 */
public abstract class Query {
    protected Map<String, String> parameters = new HashMap<>();
    
    // Common properties across all searches
    protected Status    status;
    private Integer     skip;
    private Integer     take;
    private Sort        sort;
    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
    
    /**
     * Adds common search properties set to the parameter map of fields that
     * the user wants included into the search.
     */
    protected final void addCommonProperties() {
        if (status != null) {
            parameters.put("status", status.getName());
        }
        if (skip != null) {
            parameters.put("skip", String.valueOf(skip));
        }
        if (take != null) {
            parameters.put("take", String.valueOf(take));
        }
        if (sort != null) {
            parameters.put("sort", sort.toQuery());
        }
    }
    
    /**
     * Returns all the parameters that will be used when sending a query
     * for searching as key-value pairs. 
     * 
     * @return Key-value pairs of keywords to search for.
     */
    public abstract Map<String, String> getParameters();

    @Override
    public String toString() {
        return "Query [status=" + status + ", skip=" + skip + ", take=" + take
                + ", sort=" + sort + "]";
    }
}
