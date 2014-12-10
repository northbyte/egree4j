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
public class FulltextQuery extends Query {
    private String search;
    
    public FulltextQuery(String query, Status status) {
        this.search = query;
        this.status = status;
    }
    
    public FulltextQuery(String query) {
        this(query, null);
    }
    
    public FulltextQuery(Status status) {
        this(null, status);
    }
    
    public FulltextQuery() {
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
            parameters.put("search", search);
        }
        return parameters;
    }

    @Override
    public String toString() {
        return "FulltextQuery [search=" + search + "]";
    }
}
