package com.constants;

public interface RestMethodConstants extends RestParamsConstants {
	
	String REST_METHOD_NAME_AUTHETICATE = "/authenticate";
	
	String REST_METHOD_NAME_DOWNLOAD_REPORT = "/downloadReport";
	
	// Public Access REST methods
	// REST methods to submit data
	String REST_METHOD_NAME_TO_BECOME_TUTOR = "/becomeTutor";
	String REST_METHOD_NAME_TO_FIND_TUTOR = "/findTutor";
	String REST_METHOD_NAME_TO_SUBMIT_QUERY = "/submitQuery";
	String REST_METHOD_NAME_TO_SUBSCRIBE = "/subscribe";
	// REST methods to receive data
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_BECOME_TUTOR = "/getDropdownListDataBecomeTutor";
	String REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FIND_TUTOR = "/getDropdownListDataFindTutor";
}
