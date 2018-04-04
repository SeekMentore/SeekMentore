package com.service.components.publicaccess;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.dao.ApplicationDao;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.components.publicaccess.SubmitQuery;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.MailUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE)
public class PublicAccessService implements PublicAccessConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public Map<String, Object> submitApplication(final PublicApplication application) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		feedRecordForApplication(application);
		if (application instanceof BecomeTutor) {
			response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_TUTOR_REGISTRATION);
			final BecomeTutor becomeTutorApplication = (BecomeTutor) application;
			sendNotificationAndConfirmationEmailsToTutor(becomeTutorApplication);
		} else if (application instanceof FindTutor) {
			response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_TUTOR_ENQUIRY);
			final FindTutor findTutorApplication = (FindTutor) application;
			sendNotificationAndConfirmationEmailsToParentForTutorEnquiry(findTutorApplication);
		} else if (application instanceof SubmitQuery) {
			response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_SUBMIT_QUERY);
			final SubmitQuery submitQueryApplication = (SubmitQuery) application;
			sendNotificationAndConfirmationEmailsForQueryEnquiry(submitQueryApplication);
		} else {
			response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, true);
		}
		return response;
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
		applicationDao.save(application);
	}
}
