package com.constants.components;

import com.constants.VelocityConstants;

public interface SupportConstants extends ResponseMapConstants {
	
	String VELOCITY_PDF_TEMPLATES_SUPPORT_FOLDER_PATH = VelocityConstants.VELOCITY_PDF_TEMPLATES_FOLDER_PATH + "/support";
	// Tutor Registration Admin
	String VELOCITY_PDF_TEMPLATES_BECOME_TUTOR_PATH = VELOCITY_PDF_TEMPLATES_SUPPORT_FOLDER_PATH + "/becometutor";
	String BECOME_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_BECOME_TUTOR_PATH + "/become-tutor-profile.vm";
	// Tutor Enquiry Admin
	String VELOCITY_PDF_TEMPLATES_FIND_TUTOR_PATH = VELOCITY_PDF_TEMPLATES_SUPPORT_FOLDER_PATH + "/findtutor";
	String FIND_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_FIND_TUTOR_PATH + "/find-tutor-profile.vm";
	// Subscribe With Us Admin
	String VELOCITY_PDF_TEMPLATES_SUBSCRIBE_WITH_US_PATH = VELOCITY_PDF_TEMPLATES_SUPPORT_FOLDER_PATH + "/subscribewithus";
	String SUBSCRIBE_WITH_US_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_SUBSCRIBE_WITH_US_PATH + "/subscribe-with-us-profile.vm";
	
	String BUTTON_ACTION_RECONTACTED = "recontacted";
	String BUTTON_ACTION_SELECT = "select";
	String BUTTON_ACTION_FAIL_VERIFY = "failverify";
	String BUTTON_ACTION_REVERIFY = "reverify";
	String BUTTON_ACTION_VERIFY = "verify";
	String BUTTON_ACTION_REJECT = "reject";
	String BUTTON_ACTION_RECONTACT = "recontact";
	String BUTTON_ACTION_CONTACTED = "contacted";
	
	String BUTTON_ACTION_RESPOND = "respond";
	String BUTTON_ACTION_PUT_ON_HOLD = "hold";
	
	String EXTRA_PARAM_SELECTED_GRID = "grid";
}
