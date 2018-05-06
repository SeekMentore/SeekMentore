package com.constants.components;

import com.constants.ApplicationConstants;
import com.constants.VelocityConstants;

public interface TutorConstants extends ApplicationConstants {
	
	String VALIDATION_MESSAGE_INVALID_FILENAME_PAN = "Invalid file for PAN CARD.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_DRIVING_LICENSE = "Invalid file for DRIVING LICENSE.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_AADHAAR_CARD = "Invalid file for AADHAAR CARD.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_ID = "Invalid User Id.";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
	
	String VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/tutor";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/profile-created.vm";
}