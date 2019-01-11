package com.constants.components;

public interface EnquiryConstants extends SalesConstants {
	
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
	
	String VALIDATION_MESSAGE_ENQUIRY_ID_ABSENT = "No 'enquiryId' found in request, hence cannot load data.";
}
