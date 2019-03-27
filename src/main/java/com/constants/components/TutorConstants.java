package com.constants.components;

import com.constants.VelocityConstants;
import com.utils.localization.Message;

public interface TutorConstants extends ResponseMapConstants {
	
	String MESG_PROPERTY_FILE_NAME = "spring.components.tutor";
	
	String REQUEST_PARAM_TUTOR_ID = "tutorId";
	Double MAXIMUM_FILE_SIZE_FOR_DOCUMENTS_IN_MB = 2D;
	
	String DOCUMENT_TYPE_PAN_CARD = "01";
	String DOCUMENT_TYPE_PROFILE_PHOTO = "02";
	String DOCUMENT_TYPE_AADHAAR_CARD = "03";
	
	String VELOCITY_EMAIL_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/registeredtutor";
	String REGISTERED_TUTOR_DOCUMENT_REJECTED_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/document-rejected.vm";
	String REGISTERED_TUTOR_DOCUMENT_REMINDER_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/document-reminder.vm";
	String PROFILE_CREATION_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/profile-created.vm";
	
	String VELOCITY_PDF_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH = VelocityConstants.VELOCITY_PDF_TEMPLATES_FOLDER_PATH + "/registeredtutor";
	String REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH = VELOCITY_PDF_TEMPLATES_REGISTERED_TUTOR_FOLDER_PATH + "/registered-tutor-profile.vm";

	String VALID_FILE_TYPE_FOR_DOCUMENTS = Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, "VALID_FILE_TYPE_FOR_DOCUMENTS").trim();
	Double MAXIMUM_VALID_FILE_SIZE_FOR_DOCUMENTS_IN_MB = Double.valueOf(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, "MAXIMUM_VALID_FILE_SIZE_FOR_DOCUMENTS_IN_MB").trim());

	// Message Constants
	String INVALID_TUTOR_SERIAL_ID = "INVALID_TUTOR_SERIAL_ID";
}
