package com.constants.components.publicaccess;

import com.constants.VelocityConstants;
import com.constants.components.ResponseMapConstants;

public interface PublicAccessConstants extends ResponseMapConstants {
	
	String APPLICATION_STATUS = "APPLICATION_STATUS";
	
	String FAILURE_CONTACT_INFO = "If you believe you are receiving this error wrong.<br/>Please contact 'Seek Mentore' Support team from our 'Connect with us' section on home page.";
	String FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM = "This contact number already exists in the system.";
	String FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM = "This email id already exists in the system.";
	
	String PAGE_REFERENCE_SUBMIT_QUERY = "SUBMIT_QUERY";
	String PAGE_REFERENCE_TUTOR_ENQUIRY = "TUTOR_ENQUIRY";
	String PAGE_REFERENCE_SUBSCRIBE_WITH_US = "SUBSCRIBE_WITH_US";
	String PAGE_REFERENCE_TUTOR_REGISTRATION = "TUTOR_REGISTRATION";
	String RESPONSE_MAP_ATTRIBUTE_PAGE_REFERNCE = "PAGE_REFERNCE";
	String RESPONSE_MAP_ATTRIBUTE_UNKNOWN_PUBLIC_PAGE_REFERENCE = "Unknown Object passed in request";
	String RESPONSE_MAP_ATTRIBUTE_MISMATCH_PUBLIC_PAGE_REFERENCE = "The function accessed and the Object passed in request do not match";	
	
	String VELOCITY_EMAIL_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/publicaccess";
	String SUPPORT_MAIL_LIST_ID_VM_OBJECT = "supportMailListId";
	String ADDRESS_NAME_VM_OBJECT = "addressName";

	
	String VELOCITY_EMAIL_TEMPLATES_BECOME_TUTOR_PATH = VELOCITY_EMAIL_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH + "/becometutor";
	
	String BECOME_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_BECOME_TUTOR_PATH + "/registration-notification.vm";
	String BECOME_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_BECOME_TUTOR_PATH + "/registration-confirmation.vm";
	
	String SUBJECT_NEW_TUTOR_REGISTRATION_REQUEST = "New Tutor Registration request arrived";
	String BECOME_TUTOR_APPLICATION_VM_OBJECT = "becomeTutorApplication";
	String SUBJECT_NEW_TUTOR_REGISTRATION_CONFIRMATION = "Congratulations !! Your Registration is successful with " + COMPANY_NAME_IN_QUOTES;
	
	
	String VELOCITY_EMAIL_TEMPLATES_FIND_TUTOR_PATH = VELOCITY_EMAIL_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH + "/findtutor";
	
	String FIND_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_FIND_TUTOR_PATH + "/registration-notification.vm";
	String FIND_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_FIND_TUTOR_PATH + "/registration-confirmation.vm";
	
	String SUBJECT_TUTOR_ENQUIRY_REQUEST = "New Tutor enquiry request arrived";
	String FIND_TUTOR_APPLICATION_VM_OBJECT = "findTutorApplication";
	String SUBJECT_TUTOR_ENQUIRY_REGISTRATION_CONFIRMATION = "Your Tutor enquiry is registered with " + COMPANY_NAME_IN_QUOTES;
	
	String VELOCITY_EMAIL_TEMPLATES_SUBSCRIBE_WITH_US_PATH = VELOCITY_EMAIL_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH + "/subscribe";
	
	String SUBSCRIBE_WITH_US_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIBE_WITH_US_PATH + "/registration-notification.vm";
	String SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_SUBSCRIBE_WITH_US_PATH + "/registration-confirmation.vm";
	
	String SUBJECT_SUBSCRIBE_WITH_US_REQUEST = "New Subscription request arrived";
	String SUBSCRIBE_WITH_US_APPLICATION_VM_OBJECT = "subscribeWithUsApplication";
	String SUBJECT_SUBSCRIBE_WITH_US_REGISTRATION_CONFIRMATION = "Your Subscription is registered with " + COMPANY_NAME_IN_QUOTES;
	
	
	String VELOCITY_EMAIL_TEMPLATES_SUBMIT_QUERY_PATH = VELOCITY_EMAIL_TEMPLATES_PUBLIC_ACCESS_FOLDER_PATH + "/submitquery";
	
	String SUBMIT_QUERY_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_SUBMIT_QUERY_PATH + "/registration-notification.vm";
	String SUBMIT_QUERY_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_SUBMIT_QUERY_PATH + "/registration-confirmation.vm";
	
	String SUBJECT_SUBMIT_QUERY_REQUEST = "New Query has been requested";
	String SUBMIT_QUERY_APPLICATION_VM_OBJECT = "submitQueryApplication";
	String SUBJECT_SUBMIT_QUERY_REGISTRATION_CONFIRMATION = "Your Query has been registered with " + COMPANY_NAME_IN_QUOTES;
	
	String VALIDATION_MESSAGE_CAPTCHA_INVALIDATED_PLEASE_SELECT_AGAIN = "Captcha Invalidated, please click again.";
	
	String CAPTCHA_RESPONSE_SUCCESS = "success";
	String CAPTCHA_PROPERTY_SECRET = "secret";
	String CAPTCHA_PROPERTY_RESPONSE = "response";
	String CAPTCHA_PROPERTY_REMOTEIP = "remoteip";
	
	String PUBLIC_APPLICATION_RECEIVE_SUCCESS_MESSAGE = "Your entry and has been received and will be contacted shortly.";
	String RESPONSE_MAP_DROPDOWN_LIST = "dropdownList";
	String FRESH_ENTRY = "FRESH_ENTRY";
	
	String STATUS_FRESH = "01";
	String STATUS_CONTACTED_VERIFICATION_PENDING = "02";
	String STATUS_RECONTACTED_VERIFICATION_PENDING = "03";
	String STATUS_VERIFICATION_SUCCESSFUL = "04";
	String STATUS_VERIFICATION_FAILED = "05";
	String STATUS_SUGGESTED_TO_BE_RECONTACTED = "06";
	String STATUS_SELECTED = "07";
	String STATUS_REJECTED = "08";
	
	String STATUS_RESPONDED = "02";
	String STATUS_PUT_ON_HOLD = "03";
}
