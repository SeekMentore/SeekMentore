package com.constants;

public interface RestMethodConstants extends RestParamsConstants {
	
	String REST_METHOD_NAME_AUTHETICATE = "/authenticate";
	
	String REST_METHOD_NAME_READ_FORM = "/getForm";
	String REST_METHOD_NAME_SAVE_OR_UPDATE_FORM = "/saveForm";
	String REST_METHOD_NAME_DOWNLOAD_FORM_PDF = "/downloadForm";
	
	String REST_METHOD_NAME_DOWNLOAD_REPORT = "/downloadReport";
	
	String REST_METHOD_NAME_GET_COUNTRY_LIST = "/getCountryList";
	String REST_METHOD_NAME_GET_STATE_LIST = "/getStateList";
	
	String REST_METHOD_NAME_APPLY_TO_BECOME_TUTOR = "/becomeTutor";
}
