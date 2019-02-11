package com.model;

import java.io.Serializable;

import com.utils.ValidationUtils;

public class ForgotPasswordToken extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long forgotPasswordTokenId;
	private String userId;
	private String userName;
	private String userType;
	private String token;
	private Long issueDateMillis;
	private Long expiryDateMillis;
	private String isValid;
	
	public ForgotPasswordToken() {}

	public Long getForgotPasswordTokenId() {
		return forgotPasswordTokenId;
	}


	public void setForgotPasswordTokenId(Long forgotPasswordTokenId) {
		this.forgotPasswordTokenId = forgotPasswordTokenId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Long getIssueDateMillis() {
		return issueDateMillis;
	}


	public void setIssueDateMillis(Long issueDateMillis) {
		this.issueDateMillis = issueDateMillis;
	}


	public Long getExpiryDateMillis() {
		return expiryDateMillis;
	}


	public void setExpiryDateMillis(Long expiryDateMillis) {
		this.expiryDateMillis = expiryDateMillis;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		return null;
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		return null;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "forgotPasswordTokenId" : return "FORGOT_PASSWORD_TOKEN_ID";
			case "userId" : return "USER_ID";
			case "userName" : return "USER_NAME";
			case "userType" : return "USER_TYPE";
			case "token" : return "TOKEN";
			case "issueDateMillis" : return "ISSUE_DATE_MILLIS";
			case "expiryDateMillis" : return "EXPIRY_DATE_MILLIS";
			case "isValid" : return "IS_VALID";
		}
		return EMPTY_STRING;
	}

	@Override
	public ForgotPasswordToken clone() throws CloneNotSupportedException {  
		return (ForgotPasswordToken)super.clone();
	}
}
