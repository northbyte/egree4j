package egree4j.api;

import java.util.List;

import egree4j.EgreeException;
import egree4j.models.cases.Case;
import egree4j.models.cases.PendingCase;
import egree4j.models.searching.MetadataQuery;
import egree4j.models.searching.Query;

public interface CaseResource {

    void createCase(PendingCase pendingCase) throws EgreeException;
    
    void sendCase(Case caseToSend) throws EgreeException;
    
    void sendCase(String id) throws EgreeException;
    
    void deleteCase(Case caseToDelete) throws EgreeException;
    
    void deleteCase(String id) throws EgreeException;
    
    Case getCase(String id) throws EgreeException;
    
    List<Case> searchCases(Query query) throws EgreeException;
    
    List<Case> searchTemplates(MetadataQuery query) throws EgreeException;
}
