package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.TutorConstants;
import com.dao.ApplicationDao;
import com.model.components.RegisteredTutor;
import com.model.components.publicaccess.BecomeTutor;
import com.model.rowmappers.BecomeTutorRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.MailUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_TUTOR_SERVICE)
public class TutorService implements TutorConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void feedDocumentsRecord(final String tutorId, final Map<String, String> uploadedFiles) {
		for (Map.Entry<String, String> entry : uploadedFiles.entrySet()) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorId", tutorId);
			paramsMap.put("fsKey", entry.getValue());
			paramsMap.put("filename", entry.getKey());
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
		applicationDao.executeUpdate("UPDATE BECOME_TUTOR SET IS_DATA_MIGRATED = 'Y' WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId", paramsMap);
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
		paramsMap.put("interestedSubjects", registeredTutorObj.getInterestedSubjects());
		paramsMap.put("comfortableLocations", registeredTutorObj.getComfortableLocations());
		paramsMap.put("additionalDetails", registeredTutorObj.getAdditionalDetails());
		paramsMap.put("encyptedPassword", registeredTutorObj.getEncyptedPassword());
		paramsMap.put("userId", registeredTutorObj.getUserId());
		applicationDao.executeUpdate("INSERT INTO REGISTERED_TUTOR(NAME, CONTACT_NUMBER, EMAIL_ID, TENTATIVE_TUTOR_ID, DATE_OF_BIRTH, GENDER, QUALIFICATION, PRIMARY_PROFESSION, TRANSPORT_MODE, TEACHING_EXP, INTERESTED_STUDENT_GRADES, INTERESTED_SUBJECTS, COMFORTABLE_LOCATIONS, ADDITIONAL_DETAILS, ENCYPTED_PASSWORD, RECORD_LAST_UPDATED, UPDATED_BY, USER_ID) VALUES(:name, :contactNumber, :emailId, :tentativeTutorId, :dateOfBirth, :gender, :qualification, :primaryProfession, :transportMode, :teachingExp, :interestedStudentGrades, :interestedSubjects, :comfortableLocations, :additionalDetails, :encyptedPassword, SYSDATE(), 'SYSTEM_SCHEDULER', :userId)", paramsMap);
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
}
