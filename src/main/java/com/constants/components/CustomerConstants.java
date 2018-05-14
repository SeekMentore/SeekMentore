package com.constants.components;

import com.constants.ApplicationConstants;
import com.constants.VelocityConstants;

public interface CustomerConstants extends ApplicationConstants {	
	String VALIDATION_MESSAGE_INVALID_CUSTOMER_ID = "Invalid Customer Id.";
	String VALIDATION_MESSAGE_INVALID_NAME = "Invalid Name.";
	String VALIDATION_MESSAGE_REMARKS = "Please provide some remarks.";
	String VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE = "Invalid Document Type.";
	String VALIDATION_MESSAGE_NO_FILES_UPLOADED = "Please upload some files."; 	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";	
	String VELOCITY_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/customer";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH_SUBSCRIBED_CUSTOMER = VELOCITY_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH + "/profile-created.vm";	
}
