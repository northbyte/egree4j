package egree4j.models.searching;

import java.util.Map;

import egree4j.models.cases.Status;

/**
 * Full text query that allows the user to search for multiple objects matching
 * the query string. Full text querying can only be combined with the Status
 * for filtering.
 * 
 * @author Johan
 *
 */
public class FullTextQuery extends Query {
    private String search;
    
    public FullTextQuery(String query, Status status) {
        this.search = query;
        this.status = status;
    }
    
    public FullTextQuery(String query) {
        this(query, null);
    }
    
    public FullTextQuery(Status status) {
        this(null, status);
    }
    
    public FullTextQuery() {
        this(null, null);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public Map<String, String> getParameters() {
        addCommonProperties();
        if (search != null) {
            parameters.put("Search", search);
        }
        return parameters;
    }

    @Override
    public String toString() {
        return "FullTextQuery [search=" + search + "]";
    }
}
