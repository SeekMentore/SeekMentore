package com.constants.components;

import com.constants.ApplicationConstants;
import com.utils.localization.Message;

public interface CommonsConstants extends ApplicationConstants {
	
	String MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON = "spring.components.webserviceCommon";
	
	String VALIDATION_MESSAGE_ACTIVE_USER_ABSENT = "VALIDATION_MESSAGE_ACTIVE_USER_ABSENT";
	String INVALID_TO_EMAIL_ADDRESS = "INVALID_TO_EMAIL_ADDRESS";
	String INVALID_CC_EMAIL_ADDRESS = "INVALID_CC_EMAIL_ADDRESS";
	String INVALID_BCC_EMAIL_ADDRESS = "INVALID_BCC_EMAIL_ADDRESS";
	String INVALID_SUBJECT = "INVALID_SUBJECT";
	String ACCEPTABLE_FILE_EXTENSIONS = "ACCEPTABLE_FILE_EXTENSIONS";
	String INVALID_EXTENSION = "INVALID_EXTENSION";
	String INVALID_SIZE = "INVALID_SIZE";
	String EMAIL_SEND_SUCCESS = "EMAIL_SEND_SUCCESS";
	
	String INPUT_FILE_4 = "inputFile4";
	String INPUT_FILE_3 = "inputFile3";
	String INPUT_FILE_2 = "inputFile2";
	String INPUT_FILE_1 = "inputFile1";
	String ACCESSOPTIONS = "accessoptions";
	String USERNAME = "username";
	String MENU = "menu";
	String LAST_DEPLOYED_VERSION_AND_DATE = "lastDeployedVersionAndDate";
	String DIVERTED_EMAIL_ID = "divertedEmailId";
	String IS_EMAIL_SENDING_ACTIVE = "isEmailSendingActive";
	String SUPPORT_EMAIL = "supportEmail";
	String FILE_SYSTEM_LINKED = "fileSystemLinked";
	String DB_NAME = "dbName";
	String SERVER_NAME = "serverName";
	
	Double MAXIMUM_FILE_SIZE_FOR_EMAIL_DOCUMENTS_IN_MB = 6D;
	String[] ACCEPTABLE_FILE_EXTENSIONS_ARRAY = Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME_WEB_SERVICE_COMMON, ACCEPTABLE_FILE_EXTENSIONS).split(COMMA);
	
	String EMAIL_TEMPLATES = "emailTemplates";
	String EMAIL_TEMPLATE = "emailTemplate";
	String PARAM_TEMPLATE_ID = "templateId";
	String PARAM_ERROR_CODE = "errorCode";
}
