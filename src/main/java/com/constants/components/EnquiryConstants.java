package com.constants.components;

import com.constants.ApplicationConstants;

public interface EnquiryConstants extends ApplicationConstants {
	
	String MATCH_STATUS_ABANDONED = "ABANDONED";
	String MATCH_STATUS_MAPPED = "MAPPED";
	String MATCH_STATUS_PENDING = "PENDING";
	
	String VALIDATION_MESSAGE_INVALID_CUSTOMER_ID = "Invalid Customer Id.";
	String VALIDATION_MESSAGE_INVALID_ENQUIRY_ID = "Invalid Enquiry Id.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_ID_LIST = "Invalid Tutor Id List.";
	String VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID_LIST = "Invalid Tutor Mapper Id List.";
	
	String RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE = "FAILURE_MESSAGE";
	String RESPONSE_MAP_ATTRIBUTE_FAILURE = "FAILURE";
}
