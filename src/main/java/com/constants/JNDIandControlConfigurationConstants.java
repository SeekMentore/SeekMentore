package com.constants;

public interface JNDIandControlConfigurationConstants extends ApplicationConstants {
	
	String CONFIGURATION_XML_PATH = "config/control/controlConfiguration.xml";
	String ENVIRONMENT_VARIABLE_ENCRYPTION_KEY = "encryptionKey";
	String ENVIRONMENT_VARIABLE_SERVER_NAME = "serverName";
	String ENVIRONMENT_VARIABLE_DIVERTED_RECIPEINT_EMAIL_ID = "divertedRecipeintEmailId";
	String ENVIRONMENT_VARIABLE_SEND_OUT_ACTUAL_EMAILS_BUT_DIVERT_THEM_TO_SOME_OTHER_RECIPIENT = "sendOutActualEmailsButDivertThemToSomeOtherRecipient";
	String ENVIRONMENT_VARIABLE_SHOW_ON_CONSOLE_WHAT_EMAIL_WILL_BE_SENT = "showOnConsoleWhatEmailWillBeSent";
	String ENVIRONMENT_VARIABLE_SEND_OUT_ACTUAL_EMAILS = "sendOutActualEmails";
	String ENCRYPTED_SUPPORT_MAIL_GROUP_PASSWORD = "encryptedSupportMailGroupPassword";
	String ENCRYPTED_SUPPORT_MAIL_GROUP_USERNAME = "encryptedSupportMailGroupUsername";
	String ENCRYPTED_CAPTCHA_SECRET = "encryptedCaptchaSecret";
	String BUCKET_NAME_ENCYPTED = "bucketNameEncypted";
	String SECRET_ACCESS_ENCRYPTED = "secretAccessEncrypted";
	String ACCESS_KEY_ID_ENCRYPTED = "accessKeyIDEncrypted";
	
	String SERVER_NAME_PROD = "Prod";
	String SERVER_NAME_LOCAL = "Local";
}
