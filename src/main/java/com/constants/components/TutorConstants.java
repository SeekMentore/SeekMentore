package com.constants.components;

import com.constants.ApplicationConstants;
import com.constants.VelocityConstants;

public interface TutorConstants extends ApplicationConstants {
	
	String VALIDATION_MESSAGE_INVALID_FILENAME_PAN = "Invalid file for PAN card.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_PHOTOGRAPH = "Invalid file for Photograpgh.<br/>Please Upload only '.jpg' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_AADHAAR_CARD = "Invalid file for Aadhaar card.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_ID = "Invalid User Id.";
	String VALIDATION_MESSAGE_INVALID_NAME = "Invalid Name.";
	String VALIDATION_MESSAGE_REMARKS = "Please provide some remarks.";
	String VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE = "Invalid Document Type.";
	String VALIDATION_MESSAGE_NO_FILES_UPLOADED = "Please upload some files.";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
	
	String VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/tutor";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/profile-created.vm";
}
