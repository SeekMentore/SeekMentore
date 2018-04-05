package com.constants;

public interface MailConstants extends ApplicationConstants {
	
	String MAIL_PROPERTIES_FILE = "mail/mail.properties";
	
	String BODYPART_TEXT_HTML = "text/html";
	String MULTIPART_ALTERNATIVE = "alternative";
	String MULTIPART_MIXED = "mixed";
	
	String MESSAGE_SEND = "Message Send...";
	String SUBJECT_FOR_EXCEPTION_SERVER_AT = " Server at ";
	String SUBJECT_EXCEPTION_OCCURED_IN = "Exception occured in ";
	String SUBJECT_DIVERTED_MAIL_FROM = "Diverted Mail from ";
	String MAIL_LINE_INFO_SUBJECT = "Subject :";
	String MAIL_LINE_INFO_REPLY_TO = "Reply To :";
	String MAIL_LINE_INFO_BCC = "Bcc :";
	String MAIL_LINE_INFO_CC = "Cc :";
	String MAIL_LINE_INFO_TO = "To :";
	String MAIL_LINE_INFO_FROM = "From :";
	String PARAM_SEND_EMAIL = "sendEmail";
	String PARAM_MESSAGE = "message";
	String PARAM_SUBJECT = "subject";
	String PARAM_REPLY_TO_ADDRESS = "replyToAddress";
	String PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED = "bccAddressSemicolonSeparated";
	String PARAM_CC_ADDRESS_SEMICOLON_SEPARATED = "ccAddressSemicolonSeparated";
	String PARAM_TO_ADDRESS_SEMICOLON_SEPARATED = "toAddressSemicolonSeparated";
	String PARAM_FROM_ADDRESS = "fromAddress";
	
}
