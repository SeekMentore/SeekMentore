package com.constants.components;

public interface EnquiryConstants extends ResponseMapConstants {
	
	String MATCH_STATUS_PENDING = "01";
	String MATCH_STATUS_TO_BE_MAPPED = "02";
	String MATCH_STATUS_COMPLETED = "03";
	String MATCH_STATUS_ABORTED = "04";
	
	String MAPPING_STATUS_PENDING = "01";
	String MAPPING_STATUS_DEMO_READY = "02";
	String MAPPING_STATUS_DEMO_SCHEDULED = "03";
	
	String VALIDATION_MESSAGE_INVALID_CUSTOMER_ID = "Invalid Customer Id.";
	String VALIDATION_MESSAGE_INVALID_ENQUIRY_ID = "Invalid Enquiry Id.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID = "Invalid Tutor Mapper Id.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_ID_LIST = "Invalid Tutor Id List.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID_LIST = "Invalid Tutor Mapper Id List.";
	String VALIDATION_MESSAGE_INVALID_SCHEDULE_TIME = "Invalid Schedule Date & Time..";
	
	String VELOCITY_TEMPLATES_ENQUIRIES_PATH = AdminConstants.VELOCITY_TEMPLATES_ADMIN_FOLDER_PATH + "/enquiries";
	String VELOCITY_TEMPLATES_DEMO_SCHEDULED_TUTOR_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-scheduled-tutor-email.vm";
	String VELOCITY_TEMPLATES_DEMO_SCHEDULED_CLIENT_EMAIL_PATH = VELOCITY_TEMPLATES_ENQUIRIES_PATH + "/demo-scheduled-client-email.vm";
	
	String VALIDATION_MESSAGE_ENQUIRY_ID_ABSENT = "No 'enquiryId' found in request, hence cannot load data.";
}
