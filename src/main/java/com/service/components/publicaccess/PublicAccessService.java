package com.service.components.publicaccess;

import java.util.ArrayList;
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
import com.constants.FileConstants;
import com.constants.MailConstants;
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
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.TutorService;
import com.utils.MailUtils;
import com.utils.UUIDGeneratorUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE)
public class PublicAccessService implements PublicAccessConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private TutorService tutorService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void submitApplication(final PublicApplication application) throws Exception {
		final Date currentTimestamp = new Date();
		final String namespaceName = "public-application";
		String queryId = EMPTY_STRING;
		if (application instanceof BecomeTutor) {
			prepareBecomeTutorApplication((BecomeTutor) application, currentTimestamp);
			queryId = "insertBecomeTutor";
		} else if (application instanceof FindTutor) {
			prepareFindTutorApplication((FindTutor) application, currentTimestamp);
			queryId = "insertFindTutor";
		} else if (application instanceof SubscribeWithUs) {
			prepareSubscribeWithUsApplication((SubscribeWithUs) application, currentTimestamp);
			queryId = "insertSubscribeWithUs";
		} else if (application instanceof SubmitQuery) {
			prepareSubmitQueryApplication((SubmitQuery) application, currentTimestamp);
			queryId = "insertSubmitQuery";
		} 
		applicationDao.executeUpdateWithQueryMapper(namespaceName, queryId, application);
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
	
	private void prepareBecomeTutorApplication (
			final BecomeTutor becomeTutorApplication, 
		final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		becomeTutorApplication.setBecomeTutorSerialId(UUIDGeneratorUtils.generateSerialGUID());
		becomeTutorApplication.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		becomeTutorApplication.setApplicationDateMillis(currentTimestamp.getTime());
		becomeTutorApplication.setApplicationStatus(STATUS_FRESH);
		becomeTutorApplication.setIsContacted(NO);
		becomeTutorApplication.setUpdatedBy(FRESH_ENTRY);
	}
	
	private void prepareFindTutorApplication (
			final FindTutor findTutorApplication, 
			final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		findTutorApplication.setFindTutorSerialId(UUIDGeneratorUtils.generateSerialGUID());
		findTutorApplication.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		findTutorApplication.setApplicationDateMillis(currentTimestamp.getTime());
		findTutorApplication.setApplicationStatus(STATUS_FRESH);
		findTutorApplication.setIsContacted(NO);
		findTutorApplication.setUpdatedBy(FRESH_ENTRY);
	}
	
	private void prepareSubscribeWithUsApplication (
			final SubscribeWithUs subscribeWithUsApplication, 
			final Date currentTimestamp
	) throws DataAccessException, InstantiationException, IllegalAccessException {
		subscribeWithUsApplication.setSubscribeWithUsSerialId(UUIDGeneratorUtils.generateSerialGUID());
		subscribeWithUsApplication.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		subscribeWithUsApplication.setApplicationDateMillis(currentTimestamp.getTime());
		subscribeWithUsApplication.setApplicationStatus(STATUS_FRESH);
		subscribeWithUsApplication.setIsContacted(NO);
		subscribeWithUsApplication.setUpdatedBy(FRESH_ENTRY);
	}
	
	private void prepareSubmitQueryApplication (
			final SubmitQuery submitQueryApplication, 
			final Date currentTimestamp
	) throws Exception {
		submitQueryApplication.setQuerySerialId(UUIDGeneratorUtils.generateSerialGUID());
		// Check contact number in system for Registered Tutor
		final RegisteredTutor registeredTutorInDatabaseWithEmailId = tutorService.getRegisteredTutorInDatabaseWithEmailId(submitQueryApplication.getEmailId());
		// Check email Id in system for Subscribed Customer
		final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = customerService.getSubscribedCustomerInDatabaseWithEmailId(submitQueryApplication.getEmailId());
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
		submitQueryApplication.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		submitQueryApplication.setQueryRequestedDateMillis(currentTimestamp.getTime());
		submitQueryApplication.setQueryStatus(STATUS_FRESH);
		submitQueryApplication.setIsContacted(NO);
		submitQueryApplication.setUpdatedBy(FRESH_ENTRY);
	}
	
	private void sendNotificationAndConfirmationEmailsToTutor(final BecomeTutor becomeTutorApplication) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, becomeTutorApplication.getFirstName() + WHITESPACE + becomeTutorApplication.getLastName());
		attributes.put(BECOME_TUTOR_APPLICATION_VM_OBJECT, becomeTutorApplication);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Send Registration notification message to concerned team
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getTutorRegistrationSupportMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_NEW_TUTOR_REGISTRATION_REQUEST);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(BECOME_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		// Send Registration confirmation message to Tutor on his provided email Id
		mailParams.put(MailConstants.MAIL_PARAM_TO, becomeTutorApplication.getEmailId());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_NEW_TUTOR_REGISTRATION_CONFIRMATION);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(BECOME_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes));
		attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	private void sendNotificationAndConfirmationEmailsToParentForTutorEnquiry(final FindTutor findTutorApplication) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, findTutorApplication.getName());
		attributes.put(FIND_TUTOR_APPLICATION_VM_OBJECT, findTutorApplication);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Send Registration notification message to concerned team
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getCustomerRegistrationSupportMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_TUTOR_ENQUIRY_REQUEST);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(FIND_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		// Send Registration confirmation message to Tutor on his provided email Id
		mailParams.put(MailConstants.MAIL_PARAM_TO, findTutorApplication.getEmailId());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_TUTOR_ENQUIRY_REGISTRATION_CONFIRMATION);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(FIND_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes));
		attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	private void sendNotificationAndConfirmationEmailsToParentForSubscribingWithUs(final SubscribeWithUs subscribeWithUsApplication) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(ADDRESS_NAME_VM_OBJECT, subscribeWithUsApplication.getFirstName() + WHITESPACE + subscribeWithUsApplication.getLastName());
		attributes.put(SUBSCRIBE_WITH_US_APPLICATION_VM_OBJECT, subscribeWithUsApplication.getFormattedApplicationForPrinting());
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Send Registration notification message to concerned team
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getCustomerRegistrationSupportMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_SUBSCRIBE_WITH_US_REQUEST);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(SUBSCRIBE_WITH_US_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		// Send Registration confirmation message to Tutor on his provided email Id
		mailParams.put(MailConstants.MAIL_PARAM_TO, subscribeWithUsApplication.getEmailId());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes));
		attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	private void sendNotificationAndConfirmationEmailsForQueryEnquiry(final SubmitQuery submitQueryApplication) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(SUBMIT_QUERY_APPLICATION_VM_OBJECT, submitQueryApplication);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Send Registration notification message to concerned team
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getQuerySupportMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_SUBMIT_QUERY_REQUEST);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(SUBMIT_QUERY_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		// Send Registration confirmation message to Tutor on his provided email Id
		mailParams.put(MailConstants.MAIL_PARAM_TO, submitQueryApplication.getEmailId());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, SUBJECT_SUBMIT_QUERY_REGISTRATION_CONFIRMATION);
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(SUBMIT_QUERY_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes));
		attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
}
