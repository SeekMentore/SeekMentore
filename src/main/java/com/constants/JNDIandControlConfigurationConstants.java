package com.constants;

public interface JNDIandControlConfigurationConstants extends ApplicationConstants {
	
	String CONFIGURATION_XML_PATH = "config/control/controlConfiguration.xml";
	String ENVIRONMENT_VARIABLE_ENCRYPTION_KEY = "encryptionKey";
	String ENVIRONMENT_VARIABLE_SERVER_NAME = "serverName";
	String ENCRYPTED_SUPPORT_MAIL_GROUP_PASSWORD = "encryptedSupportMailGroupPassword";
	String ENCRYPTED_SUPPORT_MAIL_GROUP_USERNAME = "encryptedSupportMailGroupUsername";
	String ENCRYPTED_CAPTCHA_SECRET = "encryptedCaptchaSecret";
	
	String SERVER_NAME_PROD = "Prod";
	String SERVER_NAME_LOCAL = "Local";
}
