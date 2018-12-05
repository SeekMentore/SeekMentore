package com.constants.components;

import com.constants.VelocityConstants;

public interface TutorConstants extends ResponseMapConstants {
	
	String VALIDATION_MESSAGE_INVALID_FILENAME_PAN = "Invalid file for PAN card.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_PHOTOGRAPH = "Invalid file for Photograpgh.<br/>Please Upload only '.jpg' files.";
	String VALIDATION_MESSAGE_INVALID_FILENAME_AADHAAR_CARD = "Invalid file for Aadhaar card.<br/>Please Upload only '.pdf' files.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_ID = "Invalid User Id.";
	String VALIDATION_MESSAGE_INVALID_NAME = "Invalid Name.";
	String VALIDATION_MESSAGE_REMARKS = "Please provide some remarks.";
	String VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE = "Invalid Document Type.";
	String VALIDATION_MESSAGE_NO_FILES_UPLOADED = "Please upload some files.";
	
	String VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH = VelocityConstants.VELOCITY_TEMPLATES_FOLDER_PATH + "/tutor";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH = VELOCITY_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/profile-created.vm";
	
	String VALIDATION_MESSAGE_TUTOR_ID_ABSENT = "No 'tutorId' found in request, hence cannot take action.";
	String VALIDATION_MESSAGE_BANK_ACCOUNT_ID_ABSENT = "No 'bankAccountId' found in request, hence cannot take action.";
	
	String REQUEST_PARAM_TUTOR_ID = "tutorId";
	String VALIDATION_MESSAGE_INVALID_FILE_TYPE_FOR_DOCUMENTS = "Only PDF/JPEG/JPG/PNG files are allowed for documents";
	String VALIDATION_MESSAGE_INVALID_FILE_SIZE_FOR_DOCUMENTS = "Size of each document should not exceed 2MB";
	
	Double MAXIMUM_FILE_SIZE_FOR_DOCUMENTS_IN_MB = 2D;
	
	String FAILURE_MESSAGE_THIS_CONTACT_NUMBER_ALREADY_EXISTS_IN_THE_SYSTEM = "This contact number already exists in the system.";
	String FAILURE_MESSAGE_THIS_EMAIL_ID_ALREADY_EXISTS_IN_THE_SYSTEM = "This email id already exists in the system.";
}
