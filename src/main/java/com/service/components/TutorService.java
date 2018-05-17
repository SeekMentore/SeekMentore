package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.dao.ApplicationDao;
import com.model.WorkbookReport;
import com.model.components.RegisteredTutor;
import com.model.components.TutorDocument;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;
import com.model.rowmappers.BecomeTutorRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.TutorDocumentRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
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
	
	public String getFolderPathToUploadTutorDocuments(final String tutorId) {
		return "secured/tutor/documents/" + tutorId;
	}
	
	public Map<String, Object> getTutorRecordWithDocuments(final RegisteredTutor registeredTutorObj) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, "Nothing to be updated.");
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
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
		registeredTutorObj.setRecordLastUpdated(null);
		registeredTutorObj.setUpdatedBy(null);
	}
	
	public void removeUltraSensitiveInformationFromRegisteredTutorObject(final RegisteredTutor registeredTutorObj) {
		registeredTutorObj.setTentativeTutorId(null);
		registeredTutorObj.setEncryptedPassword(null);
	}

	public TutorDocument downloadDocument(final String documentType, final Long tutorId, final String folderPathToUploadDocuments) throws Exception {
		final String filename = getFileNameForDocument(documentType);
		final TutorDocument tutorDocument = getTutorDocument(tutorId, filename);
		removeSensitiveInformationFromTutorDocumentObject(tutorDocument);
		tutorDocument.setContent(FileSystemUtils.readContentFromFileOnApplicationFileSystem(folderPathToUploadDocuments, tutorDocument.getFilename()));
		return tutorDocument;
	}
	
	public void removeSensitiveInformationFromTutorDocumentObject(final TutorDocument tutorDocumentObj) {
		tutorDocumentObj.setWhoActed(null);
		tutorDocumentObj.setActionDate(null);
	}
	
	private String getFileNameForDocument(final String documentType) {
		switch(documentType) {
			case "PROFILE_PHOTO": return "PROFILE_PHOTO.jpg";
			case "PAN_CARD": return "PAN_CARD.pdf";
			case "AADHAAR_CARD": return "AADHAAR_CARD.pdf";
		}
		return null;
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
		final String filename = getFileNameForDocument(documentType);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("whoActed", userId);
		paramsMap.put("tutorId", tutorId);
		paramsMap.put("filename", filename);
		paramsMap.put("remarks", remarks);
		applicationDao.executeUpdate("UPDATE TUTOR_DOCUMENTS SET IS_APPROVED = 'Y', WHO_ACTED = :whoActed, REMARKS = :remarks, ACTION_DATE = SYSDATE() WHERE TUTOR_ID = :tutorId AND FILENAME = :filename", paramsMap);
		return getTutorDocuments(tutorId);
	}
	
	public List<TutorDocument> rejectDocumentFromAdmin(final Long tutorId, final String documentType, final String userId, final String remarks) throws Exception {
		final String filename = getFileNameForDocument(documentType);
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
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		return response;
	}

	public byte[] downloadAdminReportRegisteredTutors() throws DataAccessException, InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport("Admin_Report");
		workbookReport.createSheet("REGISTERED_TUTORS", registeredTutorsList(WHITESPACE+SEMICOLON+WHITESPACE), RegisteredTutor.class);
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
}
