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
	
	String VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/admin";
	// Tutor Registration Admin
	String VELOCITY_TEMPLATES_ADMIN_REGISTERED_TUTOR_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/tutorregister";
	String REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_REGISTERED_TUTOR_PATH + "/registered-tutor-profile.vm";
	// Tutor Enquiry Admin
	String VELOCITY_TEMPLATES_ADMIN_ENQUIRED_TUTOR_PATH = VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/tutorenquiry";
	String ENQUIRED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_ADMIN_ENQUIRED_TUTOR_PATH + "/enquired-tutor-profile.vm";
}
