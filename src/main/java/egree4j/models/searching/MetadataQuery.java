package egree4j.models.searching;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;

/**
 * Class representing a search on data for certain fields in the database.
 * Only the values that are set will be used in the search query.
 * 
 * @author Johan
 *
 */
public class MetadataQuery extends Query {
    private DateTime            fromDate;
    private DateTime            toDate;
    private String              agentUsername;
    private String              partySocialSecurityNumber;
    private String              partyEmailAdress;
    private String              partyMobilePhone;
    private Map<String, String> metadata = new HashMap<>();
    private Map<String, String> formfields = new HashMap<>();

    
    public DateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    public DateTime getToDate() {
        return toDate;
    }

    public void setToDate(DateTime toDate) {
        this.toDate = toDate;
    }

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }

    public String getPartySocialSecurityNumber() {
        return partySocialSecurityNumber;
    }

    public void setPartySocialSecurityNumber(String partySocialSecurityNumber) {
        this.partySocialSecurityNumber = partySocialSecurityNumber;
    }

    public String getPartyEmailAdress() {
        return partyEmailAdress;
    }

    public void setPartyEmailAdress(String partyEmailAdress) {
        this.partyEmailAdress = partyEmailAdress;
    }

    public String getPartyMobilePhone() {
        return partyMobilePhone;
    }

    public void setPartyMobilePhone(String partyMobilePhone) {
        this.partyMobilePhone = partyMobilePhone;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Map<String, String> getFormfields() {
        return formfields;
    }

    public void setFormfields(Map<String, String> formfields) {
        this.formfields = formfields;
    }

    @Override
    public Map<String, String> getParameters() {
        addCommonProperties();
        
        if (fromDate != null) {
            parameters.put("fromdate", fromDate.toString());
        }
        if (toDate != null) {
            parameters.put("todate", toDate.toString());
        }
        if (agentUsername != null) {
            parameters.put("agentusername", agentUsername);
        }
        if (partySocialSecurityNumber != null) {
            parameters.put("partysocialsecuritynumber", 
                    partySocialSecurityNumber);
        }
        if (partyEmailAdress != null) {
            parameters.put("partyemailadress", partyEmailAdress);
        }
        if (partyMobilePhone != null) {
            parameters.put("partymobilephone", partyMobilePhone);
        }        
        if (!metadata.isEmpty()) {
            processMap("metadata", metadata, parameters);
        }
        if (!formfields.isEmpty()) {
            processMap("formdata", formfields, parameters);
        }
        return parameters;
    }
    
    /*
     * Constructs a GET parameter map by mapping all the passed values into
     * the parameters map. Each value will have the form {key[val], value}
     * put into the parameters. 
     * 
     * For example, calling processMap(Metadata, {foo=bar, sna=fu}, {}) will
     * put the Metadata into the empty map like so:
     * 
     * {Metadata[foo]=bar, Metadata[sna]=fu}
     * 
     */
    private void processMap(String key, Map<String, String> values, 
            Map<String, String> parameters) {
        Iterator<Entry<String, String>> iterator = values.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> value = iterator.next();
            String paramKey = key + "[" + value.getKey() + "]";
            String paramValue = value.getValue();
            
            parameters.put(paramKey, paramValue);
        }
    }

    @Override
    public String toString() {
        return "MetadataQuery [fromDate=" + fromDate + ", toDate=" + toDate
                + ", agentUsername=" + agentUsername
                + ", partySocialSecurityNumber=" + partySocialSecurityNumber
                + ", partyEmailAdress=" + partyEmailAdress
                + ", partyMobilePhone=" + partyMobilePhone + ", metadata="
                + metadata + ", formfields=" + formfields + "]";
    }

}
