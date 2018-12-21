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
import com.model.rowmappers.BecomeTutorRowMapper;
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
	
	@Transactional
	public List<BecomeTutor> getSelectedTutorRegistrations(final int numberOfRecords) {
		return applicationDao.findAllWithoutParams("SELECT * FROM BECOME_TUTOR WHERE IS_SELECTED = 'Y' AND IS_DATA_MIGRATED IS NULL", new BecomeTutorRowMapper());
	}
	
	@Transactional
	public void updateBecomeTutorForDataMigrated(final Long tentativeTutorId) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tentativeTutorId", tentativeTutorId);
		applicationDao.executeUpdate("UPDATE BECOME_TUTOR SET IS_DATA_MIGRATED = 'Y', WHEN_MIGRATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId", paramsMap);
	}
	
	@Transactional
	public void feedRegisteredTutorRecords(final RegisteredTutor registeredTutorObj) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("name", registeredTutorObj.getName());
		paramsMap.put("contactNumber", registeredTutorObj.getContactNumber());
		paramsMap.put("emailId", registeredTutorObj.getEmailId());
		paramsMap.put("tentativeTutorId", registeredTutorObj.getTentativeTutorId());
		paramsMap.put("dateOfBirth", registeredTutorObj.getDateOfBirth());
		paramsMap.put("gender", registeredTutorObj.getGender());
		paramsMap.put("qualification", registeredTutorObj.getQualification());
		paramsMap.put("primaryProfession", registeredTutorObj.getPrimaryProfession());
		paramsMap.put("transportMode", registeredTutorObj.getTransportMode());
		paramsMap.put("teachingExp", registeredTutorObj.getTeachingExp());
		paramsMap.put("interestedStudentGrades", registeredTutorObj.getInterestedStudentGrades());
		paramsMap.put("preferredTeachingType", registeredTutorObj.getPreferredTeachingType());
		paramsMap.put("interestedSubjects", registeredTutorObj.getInterestedSubjects());
		paramsMap.put("comfortableLocations", registeredTutorObj.getComfortableLocations());
		paramsMap.put("additionalDetails", registeredTutorObj.getAdditionalDetails());
		paramsMap.put("encryptedPassword", registeredTutorObj.getEncryptedPassword());
		paramsMap.put("userId", registeredTutorObj.getUserId());
		applicationDao.executeUpdate("INSERT INTO REGISTERED_TUTOR(NAME, CONTACT_NUMBER, EMAIL_ID, TENTATIVE_TUTOR_ID, DATE_OF_BIRTH, GENDER, QUALIFICATION, PRIMARY_PROFESSION, TRANSPORT_MODE, TEACHING_EXP, INTERESTED_STUDENT_GRADES, INTERESTED_SUBJECTS, COMFORTABLE_LOCATIONS, ADDITIONAL_DETAILS, ENCRYPTED_PASSWORD, RECORD_LAST_UPDATED, UPDATED_BY, USER_ID, PREFERRED_TEACHING_TYPE) VALUES(:name, :contactNumber, :emailId, :tentativeTutorId, :dateOfBirth, :gender, :qualification, :primaryProfession, :transportMode, :teachingExp, :interestedStudentGrades, :interestedSubjects, :comfortableLocations, :additionalDetails, :encryptedPassword, SYSDATE(), 'SYSTEM_SCHEDULER', :userId, :preferredTeachingType)", paramsMap);
	}
	
	public void sendProfileGenerationEmailToTutor(final RegisteredTutor registeredTutorObj, final String temporaryPassword) throws Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", registeredTutorObj.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("userId", registeredTutorObj.getUserId());
		attributes.put("temporaryPassword", temporaryPassword);
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				null,
				"Your Seek Mentore tutor profile is created", 
				VelocityUtils.parseTemplate(PROFILE_CREATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	public Map<String, Object> getTutorRecordWithDocuments(final RegisteredTutor registeredTutorObj) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final List<TutorDocument> tutorDocumentList = getTutorDocuments(registeredTutorObj.getTutorId());
		for (final TutorDocument tutorDocument : tutorDocumentList) {
			removeSensitiveInformationFromTutorDocumentObject(tutorDocument);
		}
		response.put("tutorDocuments", tutorDocumentList);
		replacePlaceHolderAndIdsFromRegisteredTutorObject(registeredTutorObj, LINE_BREAK);
		removeAllSensitiveInformationFromRegisteredTutorObject(registeredTutorObj);
		response.put("tutorObj", registeredTutorObj);
		return response;
	}
	
	@Transactional
	public List<TutorDocument> getTutorDocuments(final Long tutorId) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		return applicationDao.findAll("SELECT * FROM TUTOR_DOCUMENTS WHERE TUTOR_ID = :tutorId", paramsMap, new TutorDocumentRowMapper());
	}
	
	@Transactional
	public TutorDocument getTutorDocument(final Long tutorId, final String filename) throws DataAccessException, InstantiationException, IllegalAccessException {
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
	
	public void replacePlaceHolderAndIdsFromRegisteredTutorObject(final RegisteredTutor registeredTutorObj, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		registeredTutorObj.setGender(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP,registeredTutorObj.getGender(), false, delimiter));
		registeredTutorObj.setQualification(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP,registeredTutorObj.getQualification(), false, delimiter));
		registeredTutorObj.setPrimaryProfession(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP,registeredTutorObj.getPrimaryProfession(), false, delimiter));
		registeredTutorObj.setTransportMode(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP,registeredTutorObj.getTransportMode(), false, delimiter));
		registeredTutorObj.setInterestedStudentGrades(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, registeredTutorObj.getInterestedStudentGrades(), true, delimiter));
		registeredTutorObj.setInterestedSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, registeredTutorObj.getInterestedSubjects(), true, delimiter));
		registeredTutorObj.setComfortableLocations(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, registeredTutorObj.getComfortableLocations(), true, delimiter));
		registeredTutorObj.setPreferredTeachingType(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, registeredTutorObj.getPreferredTeachingType(), true, delimiter));
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
	public List<RegisteredTutor> registeredTutorsList(final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final List<RegisteredTutor> registeredTutorList = applicationDao.findAllWithoutParams("SELECT * FROM REGISTERED_TUTOR", new RegisteredTutorRowMapper());
		for (final RegisteredTutor registeredTutorObject : registeredTutorList) {
			// Get all lookup data and user ids back to original label and values
			registeredTutorObject.setDocuments(getTutorDocuments(registeredTutorObject.getTutorId()));
			removeUltraSensitiveInformationFromRegisteredTutorObject(registeredTutorObject);
			replacePlaceHolderAndIdsFromRegisteredTutorObject(registeredTutorObject, delimiter);
		}
		return registeredTutorList;
	}

	public List<TutorDocument> aprroveDocumentFromAdmin(final Long tutorId, final String documentType, final String userId, final String remarks) {
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
	
	public RegisteredTutor getRegisteredTutorObject(final Long tutorId) throws DataAccessException, InstantiationException, IllegalAccessException {
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
			replacePlaceHolderAndIdsFromRegisteredTutorObject(registeredTutorObj, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInRegisteredTutorObject(registeredTutorObj);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("registeredTutorObj", registeredTutorObj);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(AdminConstants.REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}
	
	/***********************************************************************************************************************************/
	public List<RegisteredTutor> getRegisteredTutorList(final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "R.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = R.UPDATED_BY), R.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM REGISTERED_TUTOR R";
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, null, null, gridComponent), new RegisteredTutorRowMapper());
	}
	
	public byte[] downloadAdminReportRegisteredTutorList(final GridComponent gridComponent) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("REGISTERED_TUTORS", getRegisteredTutorList(gridComponent), RegisteredTutor.class, AdminConstants.ADMIN_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithEmailId(final String emailId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find("SELECT * FROM REGISTERED_TUTOR WHERE EMAIL_ID = :emailId", paramsMap, new RegisteredTutorRowMapper());
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithContactNumber(final String contactNumber) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find("SELECT * FROM REGISTERED_TUTOR WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new RegisteredTutorRowMapper());
	}
	
	public List<TutorDocument> getTutorDocumentList(final Long tutorId, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException {
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
	
	public List<BankDetail> getBankDetailList(final Long tutorId, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		final String baseQuery = "SELECT "
				+ "B.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_ACTED), B.WHO_ACTED) AS WHO_ACTED_NAME "
				+ "FROM BANK_DETAIL B";
		
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, "WHERE TUTOR_ID = :tutorId", "ORDER BY BANK_NAME", gridComponent), paramsMap, new BankDetailRowMapper());
	}
	
	@Transactional
	public void blacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE REGISTERED_TUTOR SET "
				+ "IS_BLACKLISTED = 'Y', "
				+ "BLACKLISTED_REMARKS = :comments, "
				+ "BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TUTOR_ID = :tutorId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tutorId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorId", tutorId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void unBlacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE REGISTERED_TUTOR SET "
				+ "IS_BLACKLISTED = 'N', "
				+ "UN_BLACKLISTED_REMARKS = :comments, "
				+ "UN_BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_UN_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TUTOR_ID = :tutorId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tutorId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorId", tutorId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void aprroveTutorDocumentList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE TUTOR_DOCUMENT SET "
				+ "IS_APPROVED = 'Y', "
				+ "WHO_ACTED = :userId, "
				+ "REMARKS = :comments, "
				+ "ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) "
				+ "WHERE DOCUMENT_ID = :documentId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String documentId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("comments", comments);
			paramsMap.put("documentId", documentId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void rejectTutorDocumentList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final String baseQuery = "UPDATE TUTOR_DOCUMENT SET "
				+ "IS_APPROVED = 'N', "
				+ "WHO_ACTED = :userId, "
				+ "REMARKS = :comments, "
				+ "ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) "
				+ "WHERE DOCUMENT_ID = :documentId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String documentId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("comments", comments);
			paramsMap.put("documentId", documentId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
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
	public void aprroveBankAccountList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE BANK_DETAIL SET "
				+ "IS_APPROVED = 'Y', "
				+ "WHO_ACTED = :userId, "
				+ "REMARKS = :comments, "
				+ "ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) "
				+ "WHERE BANK_ACCOUNT_ID = :bankAccountId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String bankAccountId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("comments", comments);
			paramsMap.put("bankAccountId", bankAccountId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void makeDefaultBankAccount(final Long bankAccountId, final Long tutorId, final String comments, final User activeUser) {
		resetPreviousDefaultBankAccount(tutorId);
		final String baseQuery = "UPDATE BANK_DETAIL SET "
				+ "IS_DEFAULT = 'Y', "
				+ "WHO_ACTED = :userId, "
				+ "REMARKS = :comments, "
				+ "ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) "
				+ "WHERE BANK_ACCOUNT_ID = :bankAccountId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", activeUser.getUserId());
		paramsMap.put("comments", comments);
		paramsMap.put("bankAccountId", bankAccountId);
		applicationDao.executeUpdate(baseQuery, paramsMap);
	}
	
	private void resetPreviousDefaultBankAccount(final Long tutorId) {
		final String baseQuery = "UPDATE BANK_DETAIL SET IS_DEFAULT = 'N' WHERE TUTOR_ID = :tutorId AND IS_DEFAULT = 'Y'";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		applicationDao.executeUpdate(baseQuery, paramsMap);
	}
	
	@Transactional
	public void rejectBankAccountList(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		final String baseQuery = "UPDATE BANK_DETAIL SET "
				+ "IS_APPROVED = 'N', "
				+ "WHO_ACTED = :userId, "
				+ "REMARKS = :comments, "
				+ "ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) "
				+ "WHERE BANK_ACCOUNT_ID = :bankAccountId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String bankAccountId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("comments", comments);
			paramsMap.put("bankAccountId", bankAccountId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
		sendBankAccountListRejectionEmails(idList, tutorId, comments, activeUser);
	}
	
	public void sendBankAccountListRejectionEmails(final List<String> idList, final Long tutorId, final String comments, final User activeUser) throws Exception {
		// @ TODO - Email functionality 
	}
	
	@Transactional
	public void feedRegisteredTutorList(final List<RegisteredTutor> registeredTutorList) throws Exception {
		final String baseQueryInsertRegisteredTutor = "INSERT INTO REGISTERED_TUTOR(NAME, CONTACT_NUMBER, EMAIL_ID, TENTATIVE_TUTOR_ID, DATE_OF_BIRTH, GENDER, QUALIFICATION, PRIMARY_PROFESSION, TRANSPORT_MODE, TEACHING_EXP, INTERESTED_STUDENT_GRADES, INTERESTED_SUBJECTS, COMFORTABLE_LOCATIONS, ADDITIONAL_DETAILS, ADDRESS_DETAILS, ENCRYPTED_PASSWORD, RECORD_LAST_UPDATED_MILLIS, UPDATED_BY, USER_ID, PREFERRED_TEACHING_TYPE) VALUES(:name, :contactNumber, :emailId, :tentativeTutorId, :dateOfBirth, :gender, :qualification, :primaryProfession, :transportMode, :teachingExp, :interestedStudentGrades, :interestedSubjects, :comfortableLocations, :additionalDetails, :addressDetails, :encryptedPassword, (UNIX_TIMESTAMP(SYSDATE()) * 1000), 'SYSTEM_SCHEDULER', :userId, :preferredTeachingType)";
		final String baseQueryUpdateBecomeTutor = "UPDATE BECOME_TUTOR SET IS_DATA_MIGRATED = 'Y', WHEN_MIGRATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000) WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final RegisteredTutor registeredTutorObj : registeredTutorList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("name", registeredTutorObj.getName());
			paramsMap.put("contactNumber", registeredTutorObj.getContactNumber());
			paramsMap.put("emailId", registeredTutorObj.getEmailId());
			paramsMap.put("tentativeTutorId", registeredTutorObj.getTentativeTutorId());
			paramsMap.put("dateOfBirth", registeredTutorObj.getDateOfBirth());
			paramsMap.put("gender", registeredTutorObj.getGender());
			paramsMap.put("qualification", registeredTutorObj.getQualification());
			paramsMap.put("primaryProfession", registeredTutorObj.getPrimaryProfession());
			paramsMap.put("transportMode", registeredTutorObj.getTransportMode());
			paramsMap.put("teachingExp", registeredTutorObj.getTeachingExp());
			paramsMap.put("interestedStudentGrades", registeredTutorObj.getInterestedStudentGrades());
			paramsMap.put("preferredTeachingType", registeredTutorObj.getPreferredTeachingType());
			paramsMap.put("interestedSubjects", registeredTutorObj.getInterestedSubjects());
			paramsMap.put("comfortableLocations", registeredTutorObj.getComfortableLocations());
			paramsMap.put("additionalDetails", registeredTutorObj.getAdditionalDetails());
			paramsMap.put("addressDetails", registeredTutorObj.getAddressDetails());
			paramsMap.put("encryptedPassword", registeredTutorObj.getEncryptedPassword());
			paramsMap.put("userId", registeredTutorObj.getUserId());
			paramsList.add(paramsMap);
			sendProfileGenerationEmailToRegisteredTutorList(registeredTutorList);
		}
		applicationDao.executeBatchUpdate(baseQueryInsertRegisteredTutor, paramsList);
		applicationDao.executeBatchUpdate(baseQueryUpdateBecomeTutor, paramsList);
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
	
	public List<BecomeTutor> getBecomeTutorListForApplicationStatusSelected(final Boolean limitRecords, final Integer limit) throws DataAccessException, InstantiationException, IllegalAccessException {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, BecomeTutor.class);
		} else {
			gridComponent = new GridComponent(BecomeTutor.class);
		}
		gridComponent.setAdditionalFilterQueryString("WHERE (IS_DATA_MIGRATED IS NULL OR IS_DATA_MIGRATED <> 'Y')");
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
	
	private void sendEmailAboutEmailAndUserIdChange(final Long tutorId, final String newEmailId) {
		// TODO - Email
	}
	
	private void sendEmailAboutContactNumberChange(final Long tutorId, final String newContactNumber) {
		// TODO - Email
	}
	
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
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						sendEmailAboutContactNumberChange(tutor.getTutorId(), tutor.getContactNumber());
						paramsMap.put("contactNumber", tutor.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						// If emailId is changed also change the userId
						updateAttributesQuery.add("USER_ID = :emailId");
						sendEmailAboutEmailAndUserIdChange(tutor.getTutorId(), tutor.getEmailId());
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
}
