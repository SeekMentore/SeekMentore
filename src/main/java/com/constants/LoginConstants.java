package com.constants;

import com.constants.components.ResponseMapConstants;

public interface LoginConstants extends ResponseMapConstants, PageConstants {
	
	String USER_OBJECT = "user";
	String USER_TYPE = "user-type";
	String USER_TYPE_HEADER = "USER-TYPE";
	String USER_AUTH_HEADER = "USER-AUTH";
	String USER_TYPE_PARAM = "user-type";
	String USER_AUTH_PARAM = "user-auth";
	String USER_TYPE_TOKEN = "userTypeToken";
	String USER_AUTH_TOKEN = "userAuthToken";
	
	String USER_TYPE_CUSTOMER = "Customer";
	String USER_TYPE_TUTOR = "Tutor";
	String USER_TYPE_EMPLOYEE = "Employee";
	
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_ID = "Please provide some 'User Id'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_PASSWORD = "Please provide some 'Password'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_VALID_USER_TYPE = "Please select some 'User Type'";
	
	String VALIDATION_MESSAGE_PLEASE_ENTER_AN_OLD_PASSWORD = "Please provide 'Old Password'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_A_NEW_PASSWORD = "Please provide 'New Password'";
	String VALIDATION_MESSAGE_PLEASE_ENTER_RETYPE_NEW_PASSWORD = "Please provide 'Retype New Password'";
	String VALIDATION_MESSAGE_INCORRECT_OLD_PASSWORD = "Incorrect 'Old Password'";
	String VALIDATION_MESSAGE_MISMATCH_NEW_PASSWORD = "'New Password' and 'Retype New Password' do not match.";
	String VALIDATION_MESSAGE_PASSWORD_POLICY_FAILED = "'New Password' does not follow Password Policy.<br/>Please prepare the password as per Password Policy.";
	
	String VELOCITY_EMAIL_TEMPLATES_LOGIN_FOLDER_PATH = VelocityConstants.VELOCITY_EMAIL_TEMPLATES_FOLDER_PATH + "/login";
	String PASSWORD_CHANGE_VELOCITY_TEMPLATE_PATH = VELOCITY_EMAIL_TEMPLATES_LOGIN_FOLDER_PATH + "/password-changed.vm";
	
	String UI_ERROR_PAGE_NOT_ACCESSIBLE = "/public/error?errorCode=101";
}
