package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.MailConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.User;
import com.model.components.BankDetail;
import com.model.components.RegisteredTutor;
import com.model.components.TutorDocument;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.BankDetailRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.TutorDocumentRowMapper;
import com.model.workbook.WorkbookReport;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.QueryMapperService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.GridQueryUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.SecurityUtil;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_TUTOR_SERVICE)
public class TutorService implements TutorConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private transient AdminService adminService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void feedDocumentsRecord(final Long tutorId, final Map<String, String> uploadedFiles) {
		for (Map.Entry<String, String> entry : uploadedFiles.entrySet()) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorId", tutorId);
			paramsMap.put("fsKey", entry.getValue());
			paramsMap.put("filename", entry.getKey());
			applicationDao.executeUpdate("DELETE FROM TUTOR_DOCUMENTS WHERE TUTOR_ID = :tutorId AND FS_KEY = :fsKey", paramsMap);
			applicationDao.executeUpdate("INSERT INTO TUTOR_DOCUMENTS(TUTOR_ID, FS_KEY, FILENAME) VALUES(:tutorId, :fsKey, :filename)", paramsMap);
		}
	}
	
	
	public Map<String, Object> getTutorRecordWithDocuments(final RegisteredTutor registeredTutorObj) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final List<TutorDocument> tutorDocumentList = getTutorDocuments(registeredTutorObj.getTutorId());
		for (final TutorDocument tutorDocument : tutorDocumentList) {
			removeSensitiveInformationFromTutorDocumentObject(tutorDocument);
		}
		response.put("tutorDocuments", tutorDocumentList);
		removeAllSensitiveInformationFromRegisteredTutorObject(registeredTutorObj);
		response.put("tutorObj", registeredTutorObj);
		return response;
	}
	
	@Transactional
	public List<TutorDocument> getTutorDocuments(final Long tutorId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		return applicationDao.findAll("SELECT * FROM TUTOR_DOCUMENTS WHERE TUTOR_ID = :tutorId", paramsMap, new TutorDocumentRowMapper());
	}
	
	@Transactional
	public TutorDocument getTutorDocument(final Long tutorId, final String filename) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		paramsMap.put("filename", filename);
		return applicationDao.find("SELECT * FROM TUTOR_DOCUMENTS WHERE TUTOR_ID = :tutorId AND FILENAME = :filename", paramsMap, new TutorDocumentRowMapper());
	}
	
	public Map<String, List<SelectLookup>> getDropdownListData() {
		final Map<String, List<SelectLookup>> mapListSelectLookup = new HashMap<String, List<SelectLookup>>();
		mapListSelectLookup.put("qualificationLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP));
		mapListSelectLookup.put("professionLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP));
		mapListSelectLookup.put("transportModeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP));
		mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
		mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
		mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		mapListSelectLookup.put("preferredTeachingTypeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP));
		return mapListSelectLookup;
	}
	
	@Transactional
	public Map<String, Object> updateDetails(final RegisteredTutor registeredTutorObj) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE REGISTERED_TUTOR SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getQualification(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "QUALIFICATION = :qualification";
			updatedPropertiesParams.put("qualification", registeredTutorObj.getQualification());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getPrimaryProfession(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "PRIMARY_PROFESSION = :primaryProfession";
			updatedPropertiesParams.put("primaryProfession", registeredTutorObj.getPrimaryProfession());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getTransportMode(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TRANSPORT_MODE = :transportMode";
			updatedPropertiesParams.put("transportMode", registeredTutorObj.getTransportMode());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getInterestedStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "INTERESTED_STUDENT_GRADES = :interestedStudentGrades";
			updatedPropertiesParams.put("interestedStudentGrades", registeredTutorObj.getInterestedStudentGrades());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "INTERESTED_SUBJECTS = :interestedSubjects";
			updatedPropertiesParams.put("interestedSubjects", registeredTutorObj.getInterestedSubjects());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getComfortableLocations(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "COMFORTABLE_LOCATIONS = :comfortableLocations";
			updatedPropertiesParams.put("comfortableLocations", registeredTutorObj.getComfortableLocations());
		}
		if (ValidationUtils.validateNumber(registeredTutorObj.getTeachingExp(), true, 99, false, 0)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TEACHING_EXP = :teachingExp";
			updatedPropertiesParams.put("teachingExp", registeredTutorObj.getTeachingExp());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(registeredTutorObj.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "PREFERRED_TEACHING_TYPE = :preferredTeachingType";
			updatedPropertiesParams.put("preferredTeachingType", registeredTutorObj.getPreferredTeachingType());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(registeredTutorObj.getAdditionalDetails())) {
			if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDITIONAL_DETAILS = :additionalDetails";
			updatedPropertiesParams.put("additionalDetails", registeredTutorObj.getAdditionalDetails());
		}
		if (!"UPDATE REGISTERED_TUTOR SET ".equals(updateQuery)) {
			updatedPropertiesParams.put("updatedBy", "SELF");
			updateQuery += " ,RECORD_LAST_UPDATED = SYSDATE(), UPDATED_BY = :updatedBy WHERE TUTOR_ID = :tutorId";
			updatedPropertiesParams.put("tutorId", registeredTutorObj.getTutorId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}
	
	private void replaceNullWithBlankRemarksInRegisteredTutorObject(final RegisteredTutor registeredTutorObj) {
		registeredTutorObj.setAdditionalDetails(ApplicationUtils.returnBlankIfStringNull(registeredTutorObj.getAdditionalDetails()));
	}
	
	public void removeAllSensitiveInformationFromRegisteredTutorObject(final RegisteredTutor registeredTutorObj) {
		registeredTutorObj.setTutorId(null);
		registeredTutorObj.setTentativeTutorId(null);
		registeredTutorObj.setEncryptedPassword(null);
		registeredTutorObj.setUserId(null);
		registeredTutorObj.setRecordLastUpdatedMillis(null);
		registeredTutorObj.setUpdatedBy(null);
	}
	
	public void removeUltraSensitiveInformationFromRegisteredTutorObject(final RegisteredTutor registeredTutorObj) {
		registeredTutorObj.setTentativeTutorId(null);
		registeredTutorObj.setEncryptedPassword(null);
	}

	public TutorDocument downloadDocument(final String documentType, final Long tutorId, final String folderPathToUploadDocuments) throws Exception {
		final String filename = null;
		final TutorDocument tutorDocument = getTutorDocument(tutorId, filename);
		removeSensitiveInformationFromTutorDocumentObject(tutorDocument);
		tutorDocument.setContent(FileSystemUtils.readContentFromFileOnApplicationFileSystem(folderPathToUploadDocuments, tutorDocument.getFilename()));
		return tutorDocument;
	}
	
	public void removeSensitiveInformationFromTutorDocumentObject(final TutorDocument tutorDocumentObj) {
		tutorDocumentObj.setWhoActed(null);
		tutorDocumentObj.setActionDateMillis(null);
	}
	
	/*
	 * Admin Functions
	 */
	public List<RegisteredTutor> registeredTutorsList(final String delimiter) throws Exception {
		final List<RegisteredTutor> registeredTutorList = applicationDao.findAllWithoutParams("SELECT * FROM REGISTERED_TUTOR", new RegisteredTutorRowMapper());
		for (final RegisteredTutor registeredTutorObject : registeredTutorList) {
			// Get all lookup data and user ids back to original label and values
			registeredTutorObject.setDocuments(getTutorDocuments(registeredTutorObject.getTutorId()));
			removeUltraSensitiveInformationFromRegisteredTutorObject(registeredTutorObject);
		}
		return registeredTutorList;
	}

	public List<TutorDocument> aprroveDocumentFromAdmin(final Long tutorId, final String documentType, final String userId, final String remarks) throws Exception {
		final String filename = null;
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("whoActed", userId);
		paramsMap.put("tutorId", tutorId);
		paramsMap.put("filename", filename);
		paramsMap.put("remarks", remarks);
		applicationDao.executeUpdate("UPDATE TUTOR_DOCUMENTS SET IS_APPROVED = 'Y', WHO_ACTED = :whoActed, REMARKS = :remarks, ACTION_DATE = SYSDATE() WHERE TUTOR_ID = :tutorId AND FILENAME = :filename", paramsMap);
		return getTutorDocuments(tutorId);
	}
	
	public List<TutorDocument> rejectDocumentFromAdmin(final Long tutorId, final String documentType, final String userId, final String remarks) throws Exception {
		final String filename = null;
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("whoActed", userId);
		paramsMap.put("tutorId", tutorId);
		paramsMap.put("filename", filename);
		paramsMap.put("remarks", remarks);
		applicationDao.executeUpdate("UPDATE TUTOR_DOCUMENTS SET IS_APPROVED = 'N', WHO_ACTED = :whoActed, REMARKS = :remarks, ACTION_DATE = SYSDATE() WHERE TUTOR_ID = :tutorId AND FILENAME = :filename", paramsMap);
		sendDocumentRejectionEmailToTutor(tutorId, documentType, remarks);
		return getTutorDocuments(tutorId);
	}
	
	public void sendDocumentRejectionEmailToTutor(final Long tutorId, final String documentType, final String remarks) throws Exception {
		final RegisteredTutor registeredTutorObj = getRegisteredTutorObject(tutorId);
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", registeredTutorObj.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("documentType", documentType);
		attributes.put("remarks", remarks);
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				null,
				"Your " + documentType + " file has been asked for Re-upload", 
				VelocityUtils.parseTemplate(AdminConstants.REGISTERED_TUTOR_DOCUMENT_REJECTED_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	public RegisteredTutor getRegisteredTutorObject(final Long tutorId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		return applicationDao.find("SELECT * FROM REGISTERED_TUTOR WHERE TUTOR_ID = :tutorId", paramsMap, new RegisteredTutorRowMapper());
	}
	
	public Map<String, Object> sendDocumentReminderEmailToTutor(final Long tutorId, final String documentType) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		final RegisteredTutor registeredTutorObj = getRegisteredTutorObject(tutorId);
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", registeredTutorObj.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("documentType", documentType);
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				null,
				"Reminder: Your " + documentType + " is missing", 
				VelocityUtils.parseTemplate(AdminConstants.REGISTERED_TUTOR_DOCUMENT_REMINDER_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		return response;
	}

	public byte[] downloadAdminReportRegisteredTutors() throws DataAccessException, InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		//workbookReport.createSheet("REGISTERED_TUTORS", registeredTutorsList(WHITESPACE+SEMICOLON+WHITESPACE), RegisteredTutor.class);
		return WorkbookUtils.createWorkbook(workbookReport);
	}

	public byte[] downloadAdminIndividualRegisteredTutorProfilePdf(final Long tutorId) throws JAXBException, URISyntaxException, Exception {
		final RegisteredTutor registeredTutorObj = getRegisteredTutorObject(tutorId);
		if (null != registeredTutorObj) {
			replaceNullWithBlankRemarksInRegisteredTutorObject(registeredTutorObj);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("registeredTutorObj", registeredTutorObj);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(AdminConstants.REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}
	
	/***********************************************************************************************************************************/
	public List<RegisteredTutor> getRegisteredTutorList(final GridComponent gridComponent) throws Exception {
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor"), null, null, gridComponent), new RegisteredTutorRowMapper());
	}
	
	public List<RegisteredTutor> getRegisteredTutorListWithParams(final GridComponent gridComponent, final Map<String, Object> paramsMap) throws Exception {
		return applicationDao.findAll(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor"), null, null, gridComponent), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public byte[] downloadAdminReportRegisteredTutorList(final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("REGISTERED_TUTORS", getRegisteredTutorList(gridComponent), RegisteredTutor.class, AdminConstants.ADMIN_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithEmailId(final String emailId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
								+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorEmailFilter"), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithContactNumber(final String contactNumber) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
								+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorContactNumberFilter"), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public List<TutorDocument> getTutorDocumentList(final Long tutorId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		return applicationDao.findAll(
				GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument"), 
											queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentExistingFilter"), 
											queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentExistingSorter"), 
											gridComponent), paramsMap, new TutorDocumentRowMapper());
	}
	
	public TutorDocument getTutorDocument(final Long documentId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("documentId", documentId);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument"));
		query.append(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentDocumentIdFilter"));
		final TutorDocument tutorDocument = applicationDao.find(query.toString(), paramsMap, new TutorDocumentRowMapper());
		tutorDocument.setContent(FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(tutorDocument.getFsKey()));
		return tutorDocument;
	}
	
	public List<BankDetail> getBankDetailList(final Long tutorId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		return applicationDao.findAll(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "selectBankDetail"), 
																	queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankdetailTutorIdFilter"), 
																	queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankdetailExistingSorter"), gridComponent), paramsMap, new BankDetailRowMapper());
	}
	
	@Transactional
	public void blacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<RegisteredTutor> paramObjectList = new LinkedList<RegisteredTutor>();
		for (final String tutorId : idList) {
			final RegisteredTutor registeredTutor = new RegisteredTutor();
			registeredTutor.setIsBlacklisted(YES);
			registeredTutor.setBlacklistedRemarks(comments);
			registeredTutor.setBlacklistedDateMillis(currentTimestamp.getTime());
			registeredTutor.setWhoBlacklisted(activeUser.getUserId());
			registeredTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			registeredTutor.setUpdatedBy(activeUser.getUserId());
			registeredTutor.setTutorId(Long.valueOf(tutorId));
			paramObjectList.add(registeredTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor", "updateRegisteredTutorBlacklist", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<RegisteredTutor> paramObjectList = new LinkedList<RegisteredTutor>();
		for (final String tutorId : idList) {
			final RegisteredTutor registeredTutor = new RegisteredTutor();
			registeredTutor.setIsBlacklisted(NO);
			registeredTutor.setUnblacklistedRemarks(comments);
			registeredTutor.setUnblacklistedDateMillis(currentTimestamp.getTime());
			registeredTutor.setWhoUnBlacklisted(activeUser.getUserId());
			registeredTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			registeredTutor.setUpdatedBy(activeUser.getUserId());
			registeredTutor.setTutorId(Long.valueOf(tutorId));
			paramObjectList.add(registeredTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor", "updateRegisteredTutorUnBlacklist", paramObjectList);
	}
	
	@Transactional
	public void aprroveTutorDocumentList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<TutorDocument> paramObjectList = new LinkedList<TutorDocument>();
		for (final String documentId : idList) {
			final TutorDocument tutorDocument = new TutorDocument();
			tutorDocument.setIsApproved(YES);
			tutorDocument.setWhoActed(activeUser.getUserId());
			tutorDocument.setRemarks(comments);
			tutorDocument.setActionDateMillis(currentTimestamp.getTime());
			tutorDocument.setDocumentId(Long.valueOf(documentId));
			paramObjectList.add(tutorDocument);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "updateTakeActionTutorDocument", paramObjectList);
	}
	
	@Transactional
	public void rejectTutorDocumentList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<TutorDocument> paramObjectList = new LinkedList<TutorDocument>();
		for (final String documentId : idList) {
			final TutorDocument tutorDocument = new TutorDocument();
			tutorDocument.setIsApproved(NO);
			tutorDocument.setWhoActed(activeUser.getUserId());
			tutorDocument.setRemarks(comments);
			tutorDocument.setActionDateMillis(currentTimestamp.getTime());
			tutorDocument.setDocumentId(Long.valueOf(documentId));
			paramObjectList.add(tutorDocument);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "updateTakeActionTutorDocument", paramObjectList);
		sendTutorDocumentListRejectionEmails(idList, tutorId, comments, activeUser);
	}
	
	public void sendTutorDocumentListRejectionEmails(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		// @ TODO - Email functionality 
		/*final RegisteredTutor registeredTutorObj = getRegisteredTutorObject(tutorId);
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", registeredTutorObj.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("documentType", documentType);
		attributes.put("remarks", remarks);
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				null,
				"Your " + documentType + " file has been asked for Re-upload", 
				VelocityUtils.parseTemplate(AdminConstants.REGISTERED_TUTOR_DOCUMENT_REJECTED_VELOCITY_TEMPLATE_PATH, attributes),
				null);*/
	}
	
	public void sendTutorDocumentListReminderEmails(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		// @ TODO - Email functionality 
	}
	
	@Transactional
	public void aprroveBankAccountList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BankDetail> paramObjectList = new LinkedList<BankDetail>();
		for (final String bankAccountId : idList) {
			final BankDetail bankDetail = new BankDetail();
			bankDetail.setIsApproved(YES);
			bankDetail.setWhoActed(activeUser.getUserId());
			bankDetail.setRemarks(comments);
			bankDetail.setActionDateMillis(currentTimestamp.getTime());
			bankDetail.setBankAccountId(Long.valueOf(bankAccountId));
			paramObjectList.add(bankDetail);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateTakeActionBankDetail", paramObjectList);
	}
	
	@Transactional
	public void makeDefaultBankAccount(final Long bankAccountId, final Long tutorId, final String comments, final User activeUser) throws Exception {
		resetPreviousDefaultBankAccount(tutorId);
		final Date currentTimestamp = new Date();
		final BankDetail bankDetail = new BankDetail();
		bankDetail.setIsDefault(YES);;
		bankDetail.setWhoActed(activeUser.getUserId());
		bankDetail.setRemarks(comments);
		bankDetail.setActionDateMillis(currentTimestamp.getTime());
		bankDetail.setBankAccountId(Long.valueOf(bankAccountId));
		applicationDao.executeUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateMakeDefaultBankDetail", bankDetail);
	}
	
	private void resetPreviousDefaultBankAccount(final Long tutorId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		applicationDao.executeUpdate(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "resetPreviousDefaultBankAccount"), paramsMap);
	}
	
	@Transactional
	public void rejectBankAccountList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BankDetail> paramObjectList = new LinkedList<BankDetail>();
		for (final String bankAccountId : idList) {
			final BankDetail bankDetail = new BankDetail();
			bankDetail.setIsApproved(NO);
			bankDetail.setWhoActed(activeUser.getUserId());
			bankDetail.setRemarks(comments);
			bankDetail.setActionDateMillis(currentTimestamp.getTime());
			bankDetail.setBankAccountId(Long.valueOf(bankAccountId));
			paramObjectList.add(bankDetail);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateTakeActionBankDetail", paramObjectList);
		sendBankAccountListRejectionEmails(idList, tutorId, comments, activeUser);
	}
	
	public void sendBankAccountListRejectionEmails(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		// @ TODO - Email functionality 
	}
	
	public void sendProfileGenerationEmailToRegisteredTutorList(final List<RegisteredTutor> registeredTutorList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final RegisteredTutor registeredTutorObj : registeredTutorList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("addressName", registeredTutorObj.getName());
			attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
			attributes.put("userId", registeredTutorObj.getUserId());
			attributes.put("temporaryPassword", SecurityUtil.decrypt(registeredTutorObj.getEncryptedPassword()));
			final Map<String, Object> mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, registeredTutorObj.getName());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your Seek Mentore tutor profile is created");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseTemplate(PROFILE_CREATION_VELOCITY_TEMPLATE_PATH, attributes));
			mailParamList.add(mailParams);
		}
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public List<BecomeTutor> getBecomeTutorListForApplicationStatusSelected(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, BecomeTutor.class);
		} else {
			gridComponent = new GridComponent(BecomeTutor.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("public-application", "becomeTutorNonMigratedFilter"));
		return adminService.getBecomeTutorList(RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST, gridComponent);
	}
	
	public String getFolderPathToUploadTutorDocuments(final String tutorId) {
		return "secured/tutor/documents/" + tutorId;
	}
	
	@Transactional
	public void uploadTutorDocuments(final List<TutorDocument> documents, final Long tutorId, final Boolean isAdminOverride, final User activeUser) throws Exception {
		if (ValidationUtils.checkNonEmptyList(documents)) {
			final Date currentTimeStamp = new Date();
			String queryIdInsert = EMPTY_STRING;
			if (isAdminOverride) {
				queryIdInsert = "insertTutorDocumentFromAdmin";
			} else {
				queryIdInsert = "insertTutorDocumentFromTutor";
			}
			final String folderPathToUploadDocuments = getFolderPathToUploadTutorDocuments(String.valueOf(tutorId));
			final List<String> documentTypes = new ArrayList<String>();
			for (final TutorDocument document : documents) {
				documentTypes.add(APOSTROPHE + document.getDocumentType() + APOSTROPHE);
			}
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("tutorId", tutorId);
			final String queryToSelectOlderFileName = queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocumentFileNameAndFSKey") + 
														queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentMultiDocumentTypeFilter") + 
														"( " + String.join(COMMA, documentTypes) + ")";
			final List<Map<String, Object>> documentsToBeDeletedFromFileSystem = applicationDao.findAll(queryToSelectOlderFileName, params);
			for (final Map<String, Object> document : documentsToBeDeletedFromFileSystem) {
				FileSystemUtils.deleteFileInFolderOnApplicationFileSystemUsingKey(String.valueOf(document.get("FS_KEY")), activeUser);
			}
			for (final TutorDocument document : documents) {
				document.setTutorId(tutorId);
				document.setFilename(generateFileNameForDocumentTypeAndIncomingFile(document));
				document.setFsKey(FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, document.getFilename(), document.getContent()));
				if (isAdminOverride) {
					document.setIsApproved(YES);
					document.setWhoActed(activeUser.getUserId());
					document.setActionDateMillis(currentTimeStamp.getTime());
					document.setRemarks("Admin override");
				}
			}
			applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "deleteTutorDocumentForTutorIdDocumentType", documents);
			applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", queryIdInsert, documents);
		}
	}
	
	private String generateFileNameForDocumentTypeAndIncomingFile(final TutorDocument tutorDocument) {
		final String fileExtension = tutorDocument.getFilename().substring(tutorDocument.getFilename().lastIndexOf(PERIOD) + 1);
		switch(tutorDocument.getDocumentType()) {
			case DOCUMENT_TYPE_PROFILE_PHOTO : return "Profile_Photo." + fileExtension;
			case DOCUMENT_TYPE_PAN_CARD : return "PAN_Card." + fileExtension;
			case DOCUMENT_TYPE_AADHAAR_CARD : return "Aadhaar_Card." + fileExtension;
		}
		throw new ApplicationException("Invalid Document Type");
	}
	
	/*private void sendEmailAboutEmailAndUserIdChange(final Long tutorId, final String newEmailId) {
		// TODO - Email
	}
	
	private void sendEmailAboutContactNumberChange(final Long tutorId, final String newContactNumber) {
		// TODO - Email
	}*/
	
	@Transactional
	public void updateTutorRecord(final RegisteredTutor tutor, final List<String> changedAttributes, final User activeUser) throws Exception {
		final String baseQuery = "UPDATE REGISTERED_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE TUTOR_ID = :tutorId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", tutor.getName());
						break;
					}
					case "contactNumber" : {
						//updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						//sendEmailAboutContactNumberChange(tutor.getTutorId(), tutor.getContactNumber());
						paramsMap.put("contactNumber", tutor.getContactNumber());
						break;
					}
					case "emailId" : {
						//updateAttributesQuery.add("EMAIL_ID = :emailId");
						// If emailId is changed also change the userId
						//updateAttributesQuery.add("USER_ID = :emailId");
						//sendEmailAboutEmailAndUserIdChange(tutor.getTutorId(), tutor.getEmailId());
						paramsMap.put("emailId", tutor.getEmailId());
						break;
					}
					case "dateOfBirth" : {
						updateAttributesQuery.add("DATE_OF_BIRTH = :dateOfBirth");
						paramsMap.put("dateOfBirth", tutor.getDateOfBirth());
						break;
					}
					case "gender" : {
						updateAttributesQuery.add("GENDER = :gender");
						paramsMap.put("gender", tutor.getGender());
						break;
					}
					case "qualification" : {
						updateAttributesQuery.add("QUALIFICATION = :qualification");
						paramsMap.put("qualification", tutor.getQualification());
						break;
					}
					case "primaryProfession" : {
						updateAttributesQuery.add("PRIMARY_PROFESSION = :primaryProfession");
						paramsMap.put("primaryProfession", tutor.getPrimaryProfession());
						break;
					}
					case "transportMode" : {
						updateAttributesQuery.add("TRANSPORT_MODE = :transportMode");
						paramsMap.put("transportMode", tutor.getTransportMode());
						break;
					}
					case "interestedStudentGrades" : {
						updateAttributesQuery.add("INTERESTED_STUDENT_GRADES = :interestedStudentGrades");
						paramsMap.put("interestedStudentGrades", tutor.getInterestedStudentGrades());
						break;
					}
					case "interestedSubjects" : {
						updateAttributesQuery.add("INTERESTED_SUBJECTS = :interestedSubjects");
						paramsMap.put("interestedSubjects", tutor.getInterestedSubjects());
						break;
					}
					case "comfortableLocations" : {
						updateAttributesQuery.add("COMFORTABLE_LOCATIONS = :comfortableLocations");
						paramsMap.put("comfortableLocations", tutor.getComfortableLocations());
						break;
					}
					case "preferredTeachingType" : {
						updateAttributesQuery.add("PREFERRED_TEACHING_TYPE = :preferredTeachingType");
						paramsMap.put("preferredTeachingType", tutor.getPreferredTeachingType());
						break;
					}
					case "teachingExp" : {
						updateAttributesQuery.add("TEACHING_EXP = :teachingExp");
						paramsMap.put("teachingExp", tutor.getTeachingExp());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", tutor.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", tutor.getAddressDetails());
						break;
					}
					case "documents" : {
						if (ValidationUtils.checkNonEmptyList(tutor.getDocuments())) {
							uploadTutorDocuments(tutor.getDocuments(), tutor.getTutorId(), true, activeUser);
						}
						break;
					}
				}
			}
		}
		paramsMap.put("tutorId", tutor.getTutorId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public RegisteredTutor getRegisteredTutorFromDbUsingUserId(final String userId) throws Exception {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
									+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorUserIdFilter"), paramsMap, new RegisteredTutorRowMapper());
		}
		return null;
	}
}
