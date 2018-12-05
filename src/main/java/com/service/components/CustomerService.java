package com.service.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.CustomerConstants;
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.SubscribedCustomer;
import com.model.components.TutorDocument;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.FindTutor;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.FindTutorRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.GridQueryUtils;
import com.utils.MailUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE)
	public class CustomerService implements CustomerConstants {

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	public Map<String, List<SelectLookup>> getDropdownListData() {
		final Map<String, List<SelectLookup>> mapListSelectLookup = new HashMap<String, List<SelectLookup>>();
		mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
		mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
		mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		return mapListSelectLookup;
	}
	
	@Transactional
	public Map<String, Object> updateDetails(final SubscribedCustomer subscriberCustomerObj) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE SUBSCRIBED_CUSTOMER SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();

		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "STUDENT_GRADE = :studentGrades";
			updatedPropertiesParams.put("studentGrades", subscriberCustomerObj.getStudentGrades());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "SUBJECTS = :interestedSubjects";
			updatedPropertiesParams.put("interestedSubjects", subscriberCustomerObj.getInterestedSubjects());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "LOCATION = :Location";
			updatedPropertiesParams.put("Location", subscriberCustomerObj.getLocation());
		}
		
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(subscriberCustomerObj.getAddressDetails())) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDRESS_DETAILS = :addressDetails";
			updatedPropertiesParams.put("addressDetails", subscriberCustomerObj.getAddressDetails());
		}
		
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(subscriberCustomerObj.getAdditionalDetails())) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDITIONAL_DETAILS = :additionalDetails";
			updatedPropertiesParams.put("additionalDetails", subscriberCustomerObj.getAdditionalDetails());
		}
		if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
			updatedPropertiesParams.put("updatedBy", "SELF");
			updateQuery += " ,RECORD_LAST_UPDATED = SYSDATE(), UPDATED_BY = :updatedBy WHERE CUSTOMER_ID = :customerId";
			updatedPropertiesParams.put("customerId", subscriberCustomerObj.getCustomerId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}
	
	public void replacePlaceHolderAndIdsFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		subscriberCustomerObj.setStudentGrades(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, subscriberCustomerObj.getStudentGrades(), true, delimiter));
		subscriberCustomerObj.setInterestedSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, subscriberCustomerObj.getInterestedSubjects(), true, delimiter));
		subscriberCustomerObj.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, subscriberCustomerObj.getLocation(), true, delimiter));
	}
	
	public void removeAllSensitiveInformationFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setCustomerId(null);
		subscriberCustomerObj.setEnquiryID(null);
		subscriberCustomerObj.setEncryptedPassword(null);
		subscriberCustomerObj.setUserId(null);
		subscriberCustomerObj.setRecordLastUpdatedMillis(null);
		subscriberCustomerObj.setUpdatedBy(null);
	}
	
	public void removeUltraSensitiveInformationFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setCustomerId(null);
		subscriberCustomerObj.setEncryptedPassword(null);
		subscriberCustomerObj.setEnquiryID(null);
	}
	
	public void removePasswordFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setEncryptedPassword(null);
	}

	public void removeSensitiveInformationFromTutorDocumentObject(final TutorDocument tutorDocumentObj) {
		tutorDocumentObj.setWhoActed(null);
		tutorDocumentObj.setActionDateMillis(null);
	}
	@Transactional
	public List<FindTutor> getNonSubscribedCustomer(final int numberOfRecords) {
		return applicationDao.findAllWithoutParams("SELECT * FROM FIND_TUTOR WHERE IS_SELECTED = 'Y' AND IS_DATA_MIGRATED IS NULL", new FindTutorRowMapper());
	}
	@Transactional
	public void updateFindTutorForDataMigrated(final Long enquiryId) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		applicationDao.executeUpdate("UPDATE FIND_TUTOR SET IS_DATA_MIGRATED = 'Y', WHEN_MIGRATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId", paramsMap);
	}
	
	/*
	 * Admin Functions
	 */
	public List<SubscribedCustomer> subscribedCustomersList(final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final List<SubscribedCustomer> subscribedCustomerList = applicationDao.findAllWithoutParams("SELECT * FROM SUBSCRIBED_CUSTOMER", new SubscribedCustomerRowMapper());
		for (final SubscribedCustomer subscribedCustomerObject : subscribedCustomerList) {
			// Get all lookup data and user ids back to original label and values
			removeUltraSensitiveInformationFromSubscribedCustomerObject(subscribedCustomerObject);
			replacePlaceHolderAndIdsFromSubscribedCustomerObject(subscribedCustomerObject, delimiter);
		}
		return subscribedCustomerList;
	}
	
	public SubscribedCustomer getSubscribedCustomerObject(final Long customerId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", customerId);
		return applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE CUSTOMER_ID = :customerId", paramsMap, new SubscribedCustomerRowMapper());
	}
	
	public Map<String, Object> getSubscribedCustomer(final SubscribedCustomer subscribedCustomerObj) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		replacePlaceHolderAndIdsFromSubscribedCustomerObject(subscribedCustomerObj, LINE_BREAK);
		removeAllSensitiveInformationFromSubscribedCustomerObject(subscribedCustomerObj);
		response.put("subscribedCustomerObj", subscribedCustomerObj);
		return response;
	}
	
	@Transactional
	public List<FindTutor> getSelectedTutorRegistrations(final int numberOfRecords) {
		return applicationDao.findAllWithoutParams("SELECT * FROM FIND_TUTOR WHERE IS_SELECTED = 'Y' AND IS_DATA_MIGRATED IS NULL", new FindTutorRowMapper());
	}
	

	@Transactional
	public Long feedSubscribedCustomerRecords(final SubscribedCustomer subscribedCustomerObj) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("name", subscribedCustomerObj.getName());
		paramsMap.put("contactNumber", subscribedCustomerObj.getContactNumber());
		paramsMap.put("emailId", subscribedCustomerObj.getEmailId());
		paramsMap.put("enquiryId", subscribedCustomerObj.getEnquiryID());
		paramsMap.put("studentGrades", subscribedCustomerObj.getStudentGrades());
		paramsMap.put("subjects", subscribedCustomerObj.getInterestedSubjects());
		paramsMap.put("location", subscribedCustomerObj.getLocation());
		paramsMap.put("additionalDetails", subscribedCustomerObj.getAdditionalDetails());
		paramsMap.put("addressDetails", subscribedCustomerObj.getAddressDetails());
		paramsMap.put("encryptedPassword", subscribedCustomerObj.getEncryptedPassword());
		paramsMap.put("userId", subscribedCustomerObj.getUserId());
		return applicationDao.insertAndReturnGeneratedKey("INSERT INTO SUBSCRIBED_CUSTOMER(NAME, CONTACT_NUMBER, EMAIL_ID, ENQUIRY_ID, STUDENT_GRADE, SUBJECTS, LOCATION, ADDRESS_DETAILS, ADDITIONAL_DETAILS, ENCRYPTED_PASSWORD, RECORD_LAST_UPDATED, UPDATED_BY, USER_ID) VALUES(:name, :contactNumber, :emailId, :enquiryId, :studentGrades, :subjects, :location, :addressDetails, :additionalDetails, :encryptedPassword, SYSDATE(), 'SYSTEM_SCHEDULER', :userId)", paramsMap);
	}
	
	public void sendProfileGenerationEmailToCustomer(final SubscribedCustomer subscribedCustomerObj, final String temporaryPassword) throws Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", subscribedCustomerObj.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("userId", subscribedCustomerObj.getUserId());
		attributes.put("temporaryPassword", temporaryPassword);
		MailUtils.sendMimeMessageEmail( 
				subscribedCustomerObj.getEmailId(), 
				null,
				null,
				"Your Seek Mentore Customer profile is created", 
				VelocityUtils.parseTemplate(PROFILE_CREATION_VELOCITY_TEMPLATE_PATH_SUBSCRIBED_CUSTOMER, attributes),
				null);
	}
	
	/************************************************************************************************************/
	public List<SubscribedCustomer> getSubscribedCustomersList(final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "C.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = C.UPDATED_BY), C.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM SUBSCRIBED_CUSTOMER C";
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, null, null, gridComponent), new SubscribedCustomerRowMapper());
	}
	
	public SubscribedCustomer getSubscribedCustomerInDatabaseWithEmailId(final String emailId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE EMAIL_ID = :emailId", paramsMap, new SubscribedCustomerRowMapper());
	}
	
	public SubscribedCustomer getSubscribedCustomerInDatabaseWithContactNumber(final String contactNumber) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new SubscribedCustomerRowMapper());
	}
	
	@Transactional
	public void blacklistSubscribedCustomerList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBED_CUSTOMER SET "
				+ "IS_BLACKLISTED = 'Y', "
				+ "BLACKLISTED_REMARKS = :comments, "
				+ "BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE CUSTOMER_ID = :customerId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String customerId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("customerId", customerId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void unBlacklistSubscribedCustomerList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBED_CUSTOMER SET "
				+ "IS_BLACKLISTED = 'N', "
				+ "UN_BLACKLISTED_REMARKS = :comments, "
				+ "UN_BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_UN_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE CUSTOMER_ID = :customerId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String customerId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("customerId", customerId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	private void sendEmailAboutEmailAndUserIdChange(final Long customerId, final String newEmailId) {
		// TODO - Email
	}
	
	private void sendEmailAboutContactNumberChange(final Long customerId, final String newContactNumber) {
		// TODO - Email
	}

	@Transactional
	public void updateCustomerRecord(final SubscribedCustomer customer, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBED_CUSTOMER SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE CUSTOMER_ID = :customerId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", customer.getName());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						sendEmailAboutContactNumberChange(customer.getCustomerId(), customer.getContactNumber());
						paramsMap.put("contactNumber", customer.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						// If emailId is changed also change the userId
						updateAttributesQuery.add("USER_ID = :emailId");
						sendEmailAboutEmailAndUserIdChange(customer.getCustomerId(), customer.getEmailId());
						paramsMap.put("emailId", customer.getEmailId());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrades");
						paramsMap.put("studentGrades", customer.getStudentGrades());
						break;
					}
					case "interestedSubjects" : {
						updateAttributesQuery.add("SUBJECTS = :interestedSubjects");
						paramsMap.put("interestedSubjects", customer.getInterestedSubjects());
						break;
					}
					case "location" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", customer.getLocation());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", customer.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", customer.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("customerId", customer.getCustomerId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
}
