package com.constants.components;

import com.constants.VelocityConstants;

public interface CustomerConstants extends ResponseMapConstants {	
	String VALIDATION_MESSAGE_INVALID_CUSTOMER_ID = "Invalid Customer Id.";
	String VALIDATION_MESSAGE_INVALID_NAME = "Invalid Name.";
	String VALIDATION_MESSAGE_REMARKS = "Please provide some remarks.";
	String VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE = "Invalid Document Type.";
	String VALIDATION_MESSAGE_NO_FILES_UPLOADED = "Please upload some files."; 	
	String VELOCITY_EMAIL_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/subscribedcustomer";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH_SUBSCRIBED_CUSTOMER = VELOCITY_EMAIL_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH + "/profile-created.vm";	
	
	String VALIDATION_MESSAGE_CUSTOMER_ID_ABSENT = "No 'customerId' found in request, hence cannot load data.";
	
	String FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM = "This contact number already exists in the system.";
	String FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM = "This email id already exists in the system.";
	
	String VELOCITY_PDF_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH = VelocityConstants.VELOCITY_PDF_TEMPLATES_FOLDER_PATH + "/subscribedcustomer";
	String SUBSCRIBED_CUSTOMER_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_SUBSCRIBED_CUSTOMER_FOLDER_PATH + "/subscribed-customer-profile.vm";
}
