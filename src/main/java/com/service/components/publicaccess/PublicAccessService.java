package com.service.components.publicaccess;

import java.util.Date;
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
	
	private static final String RESPONSE_MAP_ATTRIBUTE_SEND_NOTIFICATION_AND_CONFIRMATION = "SEND_NOTIFICATION_AND_CONFIRMATION";

	private static final String RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD = "UPDATE_RECORD";

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public Map<String, Object> submitApplication(final PublicApplication application) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_SEND_NOTIFICATION_AND_CONFIRMATION, true);
		final Date currentTimestamp = new Date();
		if (application instanceof BecomeTutor) {
			handleBecomeTutorApplication(application, response, currentTimestamp);
		}
		if ((Boolean)response.get(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD)) {
			feedRecordForApplication(application);
		}
		if (application instanceof BecomeTutor) {
			if ((Boolean)response.get(RESPONSE_MAP_ATTRIBUTE_SEND_NOTIFICATION_AND_CONFIRMATION)) {
				final BecomeTutor becomeTutorApplication = (BecomeTutor) application;
				sendNotificationAndConfirmationEmailsToTutor(becomeTutorApplication);
			}
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
		// Remove unnecessary map attributes
		response.remove(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD);
		response.remove(RESPONSE_MAP_ATTRIBUTE_SEND_NOTIFICATION_AND_CONFIRMATION);
		return response;
	}
	
	private void handleBecomeTutorApplication (
		final PublicApplication application, 
		final Map<String, Object> response, 
		final Date currentTimestamp
	) {
		response.put(RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE, PAGE_REFERENCE_TUTOR_REGISTRATION);
		final BecomeTutor becomeTutorApplication = (BecomeTutor) application;
		becomeTutorApplication.setRecordLastUpdated(currentTimestamp);
		final BecomeTutor becomeTutorApplicationInDatabase = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE EMAIL_ID = ? OR CONTACT_NUMBER = ?", new Object[] {becomeTutorApplication.getEmailId(), becomeTutorApplication.getContactNumber()}, BecomeTutor.class);
		if (null != becomeTutorApplicationInDatabase) {
			response.put(RESPONSE_MAP_ATTRIBUTE_SEND_NOTIFICATION_AND_CONFIRMATION, false);
			becomeTutorApplication.setTentativeTutorId(becomeTutorApplicationInDatabase.getTentativeTutorId());
			response.put(APPLICATION_STATUS, becomeTutorApplicationInDatabase.getApplicationStatus());
			if (
					APPLICATION_STATUS_FRESH.equals(becomeTutorApplicationInDatabase.getApplicationStatus())
					|| APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED.equals(becomeTutorApplicationInDatabase.getApplicationStatus())
					|| APPLICATION_STATUS_RE_APPLIED.equals(becomeTutorApplicationInDatabase.getApplicationStatus())
			) {
				// Send Application Status New Email and update the records
				becomeTutorApplication.setApplicationDate(becomeTutorApplicationInDatabase.getApplicationDate());
				becomeTutorApplication.setApplicationStatus(becomeTutorApplicationInDatabase.getApplicationStatus());
				becomeTutorApplication.setIsContacted(becomeTutorApplicationInDatabase.getIsContacted());
				becomeTutorApplication.setWhoContacted(becomeTutorApplicationInDatabase.getWhoContacted());
				becomeTutorApplication.setContactedDate(becomeTutorApplicationInDatabase.getContactedDate());
				becomeTutorApplication.setContactedRemarks(becomeTutorApplicationInDatabase.getContactedRemarks());
				becomeTutorApplication.setIsAuthenticationVerified(becomeTutorApplicationInDatabase.getIsAuthenticationVerified());
				becomeTutorApplication.setWhoVerified(becomeTutorApplicationInDatabase.getWhoVerified());
				becomeTutorApplication.setVerificationDate(becomeTutorApplicationInDatabase.getVerificationDate());
				becomeTutorApplication.setVerificationRemarks(becomeTutorApplicationInDatabase.getVerificationRemarks());
				becomeTutorApplication.setIsToBeRecontacted(becomeTutorApplicationInDatabase.getIsToBeRecontacted());
				becomeTutorApplication.setWhoSuggestedForRecontact(becomeTutorApplicationInDatabase.getWhoSuggestedForRecontact());
				becomeTutorApplication.setSuggestionDate(becomeTutorApplicationInDatabase.getSuggestionDate());
				becomeTutorApplication.setSuggestionRemarks(becomeTutorApplicationInDatabase.getSuggestionRemarks());
				becomeTutorApplication.setWhoRecontacted(becomeTutorApplicationInDatabase.getWhoRecontacted());
				becomeTutorApplication.setRecontactedDate(becomeTutorApplicationInDatabase.getRecontactedDate());
				becomeTutorApplication.setRecontactedRemarks(becomeTutorApplicationInDatabase.getRecontactedRemarks());
				becomeTutorApplication.setIsSelected(becomeTutorApplicationInDatabase.getIsSelected());
				becomeTutorApplication.setWhoSelected(becomeTutorApplicationInDatabase.getWhoSelected());
				becomeTutorApplication.setSelectionDate(becomeTutorApplicationInDatabase.getSelectionDate());
				becomeTutorApplication.setSelectionRemarks(becomeTutorApplicationInDatabase.getSelectionRemarks());
				becomeTutorApplication.setIsRejected(becomeTutorApplicationInDatabase.getIsRejected());
				becomeTutorApplication.setWhoRejected(becomeTutorApplicationInDatabase.getWhoRejected());
				becomeTutorApplication.setRejectionDate(becomeTutorApplicationInDatabase.getRejectionDate());
				becomeTutorApplication.setRejectionRemarks(becomeTutorApplicationInDatabase.getRejectionRemarks());
				becomeTutorApplication.setRejectionCount(becomeTutorApplicationInDatabase.getRejectionCount());
				becomeTutorApplication.setReApplied(becomeTutorApplicationInDatabase.getReApplied());
				becomeTutorApplication.setPreviousApplicationDate(becomeTutorApplicationInDatabase.getApplicationDate());
			} else if (
					APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING.equals(becomeTutorApplicationInDatabase.getApplicationStatus())
					|| APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING.equals(becomeTutorApplicationInDatabase.getApplicationStatus())) {
				response.put(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD, false);
				// Send Application status is Verification Pending and cannot update the records
			} else if (APPLICATION_STATUS_VERIFICATION_SUCCESSFUL.equals(becomeTutorApplicationInDatabase.getApplicationStatus())) {
				response.put(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD, false);
				// Send Application status is Verification Successful and cannot update the records
			} else if (APPLICATION_STATUS_VERIFICATION_FAILED.equals(becomeTutorApplicationInDatabase.getApplicationStatus())) {
				response.put(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD, false);
				// Send Application status is Verification Successful and cannot update the records
			} else if (APPLICATION_STATUS_SELECTED.equals(becomeTutorApplicationInDatabase.getApplicationStatus())) {
				response.put(RESPONSE_MAP_ATTRIBUTE_UPDATE_RECORD, false);
				// Send Application status is Selected and cannot update the records
			} else if (APPLICATION_STATUS_REJECTED.equals(becomeTutorApplicationInDatabase.getApplicationStatus())) {
				// Send Application status is Renewed and update the records
				becomeTutorApplication.setApplicationDate(currentTimestamp);
				becomeTutorApplication.setApplicationStatus(APPLICATION_STATUS_RE_APPLIED);
				becomeTutorApplication.setIsContacted("N");
				becomeTutorApplication.setWhoContacted(null);
				becomeTutorApplication.setContactedDate(null);
				becomeTutorApplication.setContactedRemarks(null);
				becomeTutorApplication.setIsAuthenticationVerified(becomeTutorApplicationInDatabase.getIsAuthenticationVerified());
				becomeTutorApplication.setWhoVerified(becomeTutorApplicationInDatabase.getWhoVerified());
				becomeTutorApplication.setVerificationDate(becomeTutorApplicationInDatabase.getVerificationDate());
				becomeTutorApplication.setVerificationRemarks(becomeTutorApplicationInDatabase.getVerificationRemarks());
				becomeTutorApplication.setIsToBeRecontacted(becomeTutorApplicationInDatabase.getIsToBeRecontacted());
				becomeTutorApplication.setWhoSuggestedForRecontact(becomeTutorApplicationInDatabase.getWhoSuggestedForRecontact());
				becomeTutorApplication.setSuggestionDate(becomeTutorApplicationInDatabase.getSuggestionDate());
				becomeTutorApplication.setSuggestionRemarks(becomeTutorApplicationInDatabase.getSuggestionRemarks());
				becomeTutorApplication.setWhoRecontacted(becomeTutorApplicationInDatabase.getWhoRecontacted());
				becomeTutorApplication.setRecontactedDate(becomeTutorApplicationInDatabase.getRecontactedDate());
				becomeTutorApplication.setRecontactedRemarks(becomeTutorApplicationInDatabase.getRecontactedRemarks());
				becomeTutorApplication.setIsSelected(null);
				becomeTutorApplication.setWhoSelected(null);
				becomeTutorApplication.setSelectionDate(null);
				becomeTutorApplication.setSelectionRemarks(null);
				becomeTutorApplication.setIsRejected(becomeTutorApplicationInDatabase.getIsRejected());
				becomeTutorApplication.setWhoRejected(becomeTutorApplicationInDatabase.getWhoRejected());
				becomeTutorApplication.setRejectionDate(becomeTutorApplicationInDatabase.getRejectionDate());
				becomeTutorApplication.setRejectionRemarks(becomeTutorApplicationInDatabase.getRejectionRemarks());
				becomeTutorApplication.setRejectionCount(becomeTutorApplicationInDatabase.getRejectionCount());
				becomeTutorApplication.setReApplied("Y");
				becomeTutorApplication.setPreviousApplicationDate(becomeTutorApplicationInDatabase.getApplicationDate());
			}
		} else {
			// Fresh Application Status
			response.put(APPLICATION_STATUS, APPLICATION_STATUS_FRESH);
			becomeTutorApplication.setApplicationDate(currentTimestamp);
			becomeTutorApplication.setApplicationStatus(APPLICATION_STATUS_FRESH);
			becomeTutorApplication.setIsContacted("N");
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
		applicationDao.saveOrUpdate(application);
	}
}
