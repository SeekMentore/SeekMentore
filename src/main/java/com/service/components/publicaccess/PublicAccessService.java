package com.service.components.publicaccess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.dao.ApplicationDao;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.rowmappers.BecomeTutorRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.CommonsService;
import com.utils.ApplicationUtils;
import com.utils.MailUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE)
public class PublicAccessService implements PublicAccessConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public Map<String, Object> submitApplication(final PublicApplication application) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		final Date currentTimestamp = new Date();
		if (application instanceof BecomeTutor) {
			handleBecomeTutorApplication(application, response, currentTimestamp);
		} else if (application instanceof FindTutor) {
			handleFindTutorApplication(application, response, currentTimestamp);
		} else if (application instanceof SubscribeWithUs) {
			handleSubscribeWithUsApplication(application, response, currentTimestamp);
		} else if (application instanceof SubmitQuery) {
			handleSubmitQueryApplication(application, response, currentTimestamp);
		} else {
			response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, true);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
			return response;
		}
		if (!(Boolean)response.get(RESPONSE_MAP_ATTRIBUTE_FAILURE)) {
			feedRecordForApplication(application);
			if (application instanceof BecomeTutor) {
				sendNotificationAndConfirmationEmailsToTutor((BecomeTutor) application);
			} else if (application instanceof FindTutor) {
				sendNotificationAndConfirmationEmailsToParentForTutorEnquiry((FindTutor) application);
			} else if (application instanceof SubscribeWithUs) {
				sendNotificationAndConfirmationEmailsToParentForSubscribingWithUs((SubscribeWithUs) application);
			} else if (application instanceof SubmitQuery) {
				sendNotificationAndConfirmationEmailsForQueryEnquiry((SubmitQuery) application);
			} 
		}
		// Append contact information if Failure occurred
		if ((Boolean)response.get(RESPONSE_MAP_ATTRIBUTE_FAILURE)) {
			ApplicationUtils.appendMessageInMapAttribute(response, 
														FAILURE_CONTACT_INFO,
														RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
		}
		return response;
	}
	
	public Map<String, List<SelectLookup>> getDropdownListData(final String page) {
		final Map<String, List<SelectLookup>> mapListSelectLookup = new HashMap<String, List<SelectLookup>>();
		if (PAGE_REFERENCE_TUTOR_REGISTRATION.equals(page)) {
			mapListSelectLookup.put("genderLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP));
			mapListSelectLookup.put("qualificationLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP));
			mapListSelectLookup.put("professionLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP));
			mapListSelectLookup.put("transportModeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP));
			mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
			mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
			mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
			mapListSelectLookup.put("preferredTimeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP));
			mapListSelectLookup.put("referenceLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP));
			mapListSelectLookup.put("preferredTeachingTypeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP));
		} else if (PAGE_REFERENCE_TUTOR_ENQUIRY.equals(page)) {
			mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
			mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
			mapListSelectLookup.put("preferredTimeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP));
			mapListSelectLookup.put("referenceLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP));
			mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		} else if (PAGE_REFERENCE_SUBSCRIBE_WITH_US.equals(page)) {
			mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
			mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
			mapListSelectLookup.put("preferredTimeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP));
			mapListSelectLookup.put("referenceLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP));
			mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		}
		return mapListSelectLookup;
	}
	
	private void handleBecomeTutorApplication (
		final PublicApplication application, 
		final Map<String, Object> response, 
		final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_TUTOR_REGISTRATION);
		final BecomeTutor becomeTutorApplication = (BecomeTutor) application;
		becomeTutorApplication.setRecordLastUpdated(currentTimestamp);
		// Check email Id in system
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", becomeTutorApplication.getEmailId());
		final BecomeTutor becomeTutorApplicationInDatabaseWithEmailId = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE EMAIL_ID = :emailId", paramsMap, new BecomeTutorRowMapper());
		// Check contact number in system
		paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", becomeTutorApplication.getContactNumber());
		final BecomeTutor becomeTutorApplicationInDatabaseWithContactNumber = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new BecomeTutorRowMapper());
		if (null != becomeTutorApplicationInDatabaseWithEmailId) {
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
			ApplicationUtils.appendMessageInMapAttribute(
					response, 
					FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
		} 
		if (null != becomeTutorApplicationInDatabaseWithContactNumber) {
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
			ApplicationUtils.appendMessageInMapAttribute(
					response, 
					FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
		}
		if (!(Boolean)response.get(RESPONSE_MAP_ATTRIBUTE_FAILURE)) {
			// Fresh Application Status
			becomeTutorApplication.setApplicationDate(currentTimestamp);
			becomeTutorApplication.setApplicationStatus(APPLICATION_STATUS_FRESH);
			becomeTutorApplication.setIsContacted(NO);
			becomeTutorApplication.setWhoContacted(null);
			becomeTutorApplication.setContactedDate(null);
			becomeTutorApplication.setContactedRemarks(null);
			becomeTutorApplication.setIsAuthenticationVerified(null);
			becomeTutorApplication.setWhoVerified(null);
			becomeTutorApplication.setVerificationDate(null);
			becomeTutorApplication.setVerificationRemarks(null);
			becomeTutorApplication.setIsToBeRecontacted(null);
			becomeTutorApplication.setWhoSuggestedForRecontact(null);
			becomeTutorApplication.setSuggestionDate(null);
			becomeTutorApplication.setSuggestionRemarks(null);
			becomeTutorApplication.setWhoRecontacted(null);
			becomeTutorApplication.setRecontactedDate(null);
			becomeTutorApplication.setRecontactedRemarks(null);
			becomeTutorApplication.setIsSelected(null);
			becomeTutorApplication.setWhoSelected(null);
			becomeTutorApplication.setSelectionDate(null);
			becomeTutorApplication.setSelectionRemarks(null);
			becomeTutorApplication.setIsRejected(null);
			becomeTutorApplication.setWhoRejected(null);
			becomeTutorApplication.setRejectionDate(null);
			becomeTutorApplication.setRejectionRemarks(null);
			becomeTutorApplication.setRejectionCount(0);
			becomeTutorApplication.setReApplied(null);
			becomeTutorApplication.setPreviousApplicationDate(null);
		}
	}
	
	private void handleFindTutorApplication (
			final PublicApplication application, 
			final Map<String, Object> response, 
			final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_TUTOR_ENQUIRY);
		final FindTutor findTutorApplication = (FindTutor) application;
		findTutorApplication.setRecordLastUpdated(currentTimestamp);
		// Check email Id in system for Subscribed Customer
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", findTutorApplication.getEmailId());
		final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE EMAIL_ID = :emailId", paramsMap, new SubscribedCustomerRowMapper());
		// Check contact number in system for Subscribed Customer
		paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", findTutorApplication.getContactNumber());
		final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new SubscribedCustomerRowMapper());
		if (null != subscribedCustomerInDatabaseWithEmailId || null != subscribedCustomerInDatabaseWithContactNumber) {
			findTutorApplication.setSubscribedCustomer(YES);
		} else {
			findTutorApplication.setSubscribedCustomer(NO);
		}
		findTutorApplication.setEnquiryDate(currentTimestamp);
		findTutorApplication.setEnquiryStatus(APPLICATION_STATUS_FRESH);
		findTutorApplication.setIsContacted(NO);
		findTutorApplication.setWhoContacted(null);
		findTutorApplication.setContactedDate(null);
		findTutorApplication.setContactedRemarks(null);
		findTutorApplication.setIsAuthenticationVerified(null);
		findTutorApplication.setWhoVerified(null);
		findTutorApplication.setVerificationDate(null);
		findTutorApplication.setVerificationRemarks(null);
		findTutorApplication.setIsToBeRecontacted(null);
		findTutorApplication.setWhoSuggestedForRecontact(null);
		findTutorApplication.setSuggestionDate(null);
		findTutorApplication.setSuggestionRemarks(null);
		findTutorApplication.setWhoRecontacted(null);
		findTutorApplication.setRecontactedDate(null);
		findTutorApplication.setRecontactedRemarks(null);
		findTutorApplication.setIsSelected(null);
		findTutorApplication.setWhoSelected(null);
		findTutorApplication.setSelectionDate(null);
		findTutorApplication.setSelectionRemarks(null);
		findTutorApplication.setIsRejected(null);
		findTutorApplication.setWhoRejected(null);
		findTutorApplication.setRejectionDate(null);
		findTutorApplication.setRejectionRemarks(null);
	}
	
	private void handleSubscribeWithUsApplication (
			final PublicApplication application, 
			final Map<String, Object> response, 
			final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_SUBSCRIBE_WITH_US);
		final SubscribeWithUs subscribeWithUsApplication = (SubscribeWithUs) application;
		subscribeWithUsApplication.setRecordLastUpdated(currentTimestamp);
		// Check email Id in system for Subscribed Customer
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", subscribeWithUsApplication.getEmailId());
		final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE EMAIL_ID = :emailId", paramsMap, new SubscribedCustomerRowMapper());
		// Check contact number in system for Subscribed Customer
		paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", subscribeWithUsApplication.getContactNumber());
		final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new SubscribedCustomerRowMapper());
		if (null != subscribedCustomerInDatabaseWithEmailId || null != subscribedCustomerInDatabaseWithContactNumber) {
			subscribeWithUsApplication.setSubscribedCustomer(YES);
		} else {
			subscribeWithUsApplication.setSubscribedCustomer(NO);
		}
		subscribeWithUsApplication.setApplicationDate(currentTimestamp);
		subscribeWithUsApplication.setApplicationStatus(APPLICATION_STATUS_FRESH);
		subscribeWithUsApplication.setIsContacted(NO);
		subscribeWithUsApplication.setWhoContacted(null);
		subscribeWithUsApplication.setContactedDate(null);
		subscribeWithUsApplication.setContactedRemarks(null);
		subscribeWithUsApplication.setIsAuthenticationVerified(null);
		subscribeWithUsApplication.setWhoVerified(null);
		subscribeWithUsApplication.setVerificationDate(null);
		subscribeWithUsApplication.setVerificationRemarks(null);
		subscribeWithUsApplication.setIsToBeRecontacted(null);
		subscribeWithUsApplication.setWhoSuggestedForRecontact(null);
		subscribeWithUsApplication.setSuggestionDate(null);
		subscribeWithUsApplication.setSuggestionRemarks(null);
		subscribeWithUsApplication.setWhoRecontacted(null);
		subscribeWithUsApplication.setRecontactedDate(null);
		subscribeWithUsApplication.setRecontactedRemarks(null);
		subscribeWithUsApplication.setIsSelected(null);
		subscribeWithUsApplication.setWhoSelected(null);
		subscribeWithUsApplication.setSelectionDate(null);
		subscribeWithUsApplication.setSelectionRemarks(null);
		subscribeWithUsApplication.setIsRejected(null);
		subscribeWithUsApplication.setWhoRejected(null);
		subscribeWithUsApplication.setRejectionDate(null);
		subscribeWithUsApplication.setRejectionRemarks(null);
	}
	
	private void handleSubmitQueryApplication (
			final PublicApplication application, 
			final Map<String, Object> response, 
			final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_SUBMIT_QUERY);
		final SubmitQuery submitQueryApplication = (SubmitQuery) application;
		submitQueryApplication.setRecordLastUpdated(currentTimestamp);
		// Check contact number in system for Registered Tutor
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", submitQueryApplication.getEmailId());
		final RegisteredTutor registeredTutorInDatabaseWithEmailId = applicationDao.find("SELECT * FROM REGISTERED_TUTOR WHERE EMAIL_ID = :emailId", paramsMap, new RegisteredTutorRowMapper());
		// Check email Id in system for Subscribed Customer
		paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", submitQueryApplication.getEmailId());
		final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = applicationDao.find("SELECT * FROM SUBSCRIBED_CUSTOMER WHERE EMAIL_ID = :emailId", paramsMap, new SubscribedCustomerRowMapper());
		if (null != registeredTutorInDatabaseWithEmailId) {
			submitQueryApplication.setRegisteredTutor(YES);
		} else {
			submitQueryApplication.setRegisteredTutor(NO);
		}
		if (null != subscribedCustomerInDatabaseWithEmailId) {
			submitQueryApplication.setSubscribedCustomer(YES);
		} else {
			submitQueryApplication.setSubscribedCustomer(NO);
		}
		submitQueryApplication.setQueryRequestedDate(currentTimestamp);
		submitQueryApplication.setQueryStatus(APPLICATION_STATUS_FRESH);
		submitQueryApplication.setIsContacted(NO);
		submitQueryApplication.setWhoContacted(null);
		submitQueryApplication.setContactedDate(null);
		submitQueryApplication.setQueryResponse(null);
		submitQueryApplication.setNotAnswered(null);
		submitQueryApplication.setNotAnsweredReason(null);
		submitQueryApplication.setWhoNotAnswered(null);
	}
	
	private void sendNotificationAndConfirmationEmailsToTutor(final BecomeTutor becomeTutorApplication) throws Exception {
		// Send Registration notification message to concerned team
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, becomeTutorApplication.getFirstName() + WHITESPACE + becomeTutorApplication.getLastName());
		attributes.put(BECOME_TUTOR_APPLICATION_VM_OBJECT, becomeTutorApplication.toString());
		attributes.put(SUPPORT_MAIL_LIST_ID_VM_OBJECT, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getTutorRegistrationSupportMailList(), 
				null,
				null,
				SUBJECT_NEW_TUTOR_REGISTRATION_REQUEST, 
				VelocityUtils.parseTemplate(BECOME_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		// Send Registration confirmation message to Tutor on his provided email Id
		MailUtils.sendMimeMessageEmail( 
				becomeTutorApplication.getEmailId(), 
				null,
				null,
				SUBJECT_NEW_TUTOR_REGISTRATION_CONFIRMATION, 
				VelocityUtils.parseTemplate(BECOME_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void sendNotificationAndConfirmationEmailsToParentForTutorEnquiry(final FindTutor findTutorApplication) throws Exception {
		// Send Registration notification message to concerned team
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, findTutorApplication.getName());
		attributes.put(FIND_TUTOR_APPLICATION_VM_OBJECT, findTutorApplication.toString());
		attributes.put(SUPPORT_MAIL_LIST_ID_VM_OBJECT, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getCustomerRegistrationSupportMailList(), 
				null,
				null,
				SUBJECT_TUTOR_ENQUIRY_REQUEST, 
				VelocityUtils.parseTemplate(FIND_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		// Send Registration confirmation message to Tutor on his provided email Id
		MailUtils.sendMimeMessageEmail( 
				findTutorApplication.getEmailId(), 
				null,
				null,
				SUBJECT_TUTOR_ENQUIRY_REGISTRATION_CONFIRMATION, 
				VelocityUtils.parseTemplate(FIND_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void sendNotificationAndConfirmationEmailsToParentForSubscribingWithUs(final SubscribeWithUs subscribeWithUsApplication) throws Exception {
		// Send Registration notification message to concerned team
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, subscribeWithUsApplication.getFirstName() + WHITESPACE + subscribeWithUsApplication.getLastName());
		attributes.put(SUBSCRIBE_WITH_US_APPLICATION_VM_OBJECT, subscribeWithUsApplication.toString());
		attributes.put(SUPPORT_MAIL_LIST_ID_VM_OBJECT, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getCustomerRegistrationSupportMailList(), 
				null,
				null,
				SUBJECT_SUBSCRIBE_WITH_US_REQUEST, 
				VelocityUtils.parseTemplate(SUBSCRIBE_WITH_US_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		// Send Registration confirmation message to Tutor on his provided email Id
		MailUtils.sendMimeMessageEmail( 
				subscribeWithUsApplication.getEmailId(), 
				null,
				null,
				SUBJECT_SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION, 
				VelocityUtils.parseTemplate(SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void sendNotificationAndConfirmationEmailsForQueryEnquiry(final SubmitQuery submitQueryApplication) throws Exception {
		// Send Registration notification message to concerned team
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(SUBMIT_QUERY_APPLICATION_VM_OBJECT, submitQueryApplication.toString());
		attributes.put(SUPPORT_MAIL_LIST_ID_VM_OBJECT, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getQuerySupportMailList(), 
				null,
				null,
				SUBJECT_SUBMIT_QUERY_REQUEST, 
				VelocityUtils.parseTemplate(SUBMIT_QUERY_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		// Send Registration confirmation message to Tutor on his provided email Id
		MailUtils.sendMimeMessageEmail( 
				submitQueryApplication.getEmailId(), 
				null,
				null,
				SUBJECT_SUBMIT_QUERY_REGISTRATION_CONFIRMATION, 
				VelocityUtils.parseTemplate(SUBMIT_QUERY_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void feedRecordForApplication(final PublicApplication application) {
		applicationDao.saveOrUpdate(application);
	}
}
