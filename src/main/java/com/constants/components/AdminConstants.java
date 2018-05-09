package com.constants.components;

import com.constants.ApplicationConstants;
import com.constants.VelocityConstants;

public interface AdminConstants extends ApplicationConstants {
	
	String BUTTON_ACTION_RECONTACTED = "recontacted";
	String BUTTON_ACTION_SELECT = "select";
	String BUTTON_ACTION_FAILVERIFY = "failverify";
	String BUTTON_ACTION_REVERIFY = "reverify";
	String BUTTON_ACTION_VERIFY = "verify";
	String BUTTON_ACTION_REJECT = "reject";
	String BUTTON_ACTION_RECONTACT = "recontact";
	String BUTTON_ACTION_CONTACTED = "contacted";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
	
	String VALIDATION_MESSAGE_INVALID_GRID_REFERENCE_ACCESS = "Invalid Grid Reference Access.";
	String VALIDATION_MESSAGE_INVALID_BUTTON_ACTION = "Invalid Button Action.";
	String VALIDATION_MESSAGE_INVALID_UNIQUE_ID = "Invalid Unique {Tutor Id / Enquiry Id / Subscription Id} Id.";
	String VALIDATION_MESSAGE_PLEASE_ENTER_REMARKS = "Please provide remarks for this action.";
	String VALIDATION_MESSAGE_INVALID_RECEPIENT_ADDRESS = "Invalid Recepient Email Address.";
	String VALIDATION_MESSAGE_INVALID_SALUTATION = "Invalid Salutation Name.";
	
	String VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/admin";
	String ADMIN_EMAIL_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/admin-email.vm";
	// Tutor Registration Admin
	String VELOCITY_TEMPLATES_ADMIN_TUTOR_REGISTER_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/tutorregister";
	String TUTOR_REGISTER_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_TUTOR_REGISTER_PATH + "/tutor-register-profile.vm";
	// Tutor Enquiry Admin
	String VELOCITY_TEMPLATES_ADMIN_TUTOR_ENQUIRY_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/tutorenquiry";
	String TUTOR_ENQUIRY_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_TUTOR_ENQUIRY_PATH + "/tutor-enquiry-profile.vm";
	// Subscribe With Us Admin
	String VELOCITY_TEMPLATES_ADMIN_SUBSCRIBE_WITH_US_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/subscribewithus";
	String SUBSCRIBE_WITH_US_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_SUBSCRIBE_WITH_US_PATH + "/subscribe-with-us-profile.vm";
	// Tutor Registration Admin
	String VELOCITY_TEMPLATES_ADMIN_REGISTERED_TUTOR_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/registeredtutor";
	String REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_TUTOR_REGISTER_PATH + "/tutor-register-profile.vm";

}
