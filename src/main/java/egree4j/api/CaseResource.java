package egree4j.api;

import java.util.List;

import egree4j.EgreeException;
import egree4j.EgreeServiceException;
import egree4j.models.cases.Case;
import egree4j.models.cases.Draft;
import egree4j.models.searching.MetadataQuery;
import egree4j.models.searching.Query;

/**
 * Resource interface for managing Cases in the Egree service.
 * 
 * @author Johan
 *
 */
public interface CaseResource {

    /**
     * Creates a new case from the given Draft. This will create a new Case in
     * the Egree service if all the required values are filled in. Once the
     * case has been created, it is no longer a Draft but is then a full Case.
     * 
     * <p>If the Egree service is missing information, an EgreeServiceException
     * will be thrown with the feedback on the issue.</p>
     * 
     * @param draft The local Draft created with values to use for a new Case.
     * @throws EgreeException If there are missing required values, a user is
     * unauthorized or there is an error in the Egree service.
     */
    void createCase(Draft draft) throws EgreeException;
    
    /**
     * Creates a case based on a template. Set agentUsername to either id or 
     * email adress if you want the agent of the case should be an agent other 
     * than the one making the call.
     * 
     * @param templateId Id of the template to create the Case from.
     * @param caseId Id of the new Case. If this is null one will be generated.
     * @param agentUsername Id or email address of the agent to be set as caller
     * @throws EgreeServiceException
     * @throws EgreeException
     */
    void createCaseFromTemplate(String templateId, String caseId, 
            String agentUsername) throws EgreeServiceException, EgreeException;
    
    /**
     * Updates properties and collections of a case. Collections: missing items
     * will be removed, others updated or added. Documents collection: Only 
     * filename and formfields can be changed. To modify size, hash or data, 
     * the document must be removed first and a new document (with a new id) 
     * must be added.
     * 
     * @param caseToUpdate The Case to update.
     * @throws EgreeServiceException If an error occurred that Egree can
     * notify the user about.
     * @throws EgreeException If a connection error occurred.
     */
    void updateCase(Case caseToUpdate) 
            throws EgreeServiceException, EgreeException;
    
    /**
     * Sends a case. This will make the status from DRAFT to SENT and all the
     * associated users will receive their respective information. Once this
     * is called, all the involved users will be able to sign the documents.
     * 
     * @param caseToSend The case to be sent.
     * @throws EgreeException If an error occurs in the Egree service.
     */
    void sendCase(Case caseToSend) throws EgreeException;
    
    /**
     * Sends a case. This will make the status from DRAFT to SENT and all the
     * associated users will receive their respective information. Once this
     * is called, all the involved users will be able to sign the documents.
     * 
     * @param id Id of the case to be sent.
     * @throws EgreeException If an error occurs in the Egree service.
     */
    void sendCase(String id) throws EgreeException;
    
    /**
     * Deletes a Case from the Egree service. Note that this can only be done
     * to cases in DRAFT or SENT states.
     * 
     * @param caseToDelete Case to delete.
     * @throws EgreeException If the case is not allowed to be deleted or an
     * error occurred in the Egree service.
     */
    void deleteCase(Case caseToDelete) throws EgreeException;
    
    /**
     * Deletes a Case from the Egree service. Note that this can only be done
     * to cases in DRAFT or SENT states.
     * 
     * @param id The id of the Case to delete.
     * @throws EgreeException If the case is not allowed to be deleted or an
     * error occurred in the Egree service.
     */
    void deleteCase(String id) throws EgreeException;
    
    /**
     * Returns a Case with the given id. Will throw an exception if the Case is
     * not found or the caller is not allowed to see the Case.
     * 
     * @param id Id of the case to fetch.
     * @return The Case from the Egree service.
     * @throws EgreeException If the caller is not allowed to see the Case or
     * the Case is not found.
     */
    Case getCase(String id) throws EgreeException;
    
    /**
     * Searches cases based on the passed query. The Cases returned will be the
     * cases that are matched by the Egree service and that the caller is
     * authorized to see.
     * 
     * @param query Query to use to search for cases.
     * @return List of cases matching the query.
     * @throws EgreeException If an error occurred in the Egree service.
     */
    List<Case> searchCases(Query query) throws EgreeException;
    
    /**
     * Searches templates based on the passed query. The templates returned 
     * will be the templates that are matched by the Egree service and that the 
     * caller is authorized to see.
     * 
     * @param query Query to use to search for templates.
     * @return List of templates matching the query.
     * @throws EgreeException If an error occurred in the Egree service.
     */
    List<Case> searchTemplates(MetadataQuery query) throws EgreeException;
}
