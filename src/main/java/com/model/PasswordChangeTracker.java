package com.model;

import java.io.Serializable;

import com.utils.ValidationUtils;

public class PasswordChangeTracker extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private String passwordChangeSerialId;
	private Long changeTimeMillis;
	private String userType;
	private String userId;
	private String encryptedPasswordOld;
	private String encryptedPasswordNew;
	private String userName;
	
	public PasswordChangeTracker() {}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswordChangeSerialId() {
		return passwordChangeSerialId;
	}

	public void setPasswordChangeSerialId(String passwordChangeSerialId) {
		this.passwordChangeSerialId = passwordChangeSerialId;
	}

	public String getEncryptedPasswordOld() {
		return encryptedPasswordOld;
	}

	public void setEncryptedPasswordOld(String encryptedPasswordOld) {
		this.encryptedPasswordOld = encryptedPasswordOld;
	}

	public String getEncryptedPasswordNew() {
		return encryptedPasswordNew;
	}

	public void setEncryptedPasswordNew(String encryptedPasswordNew) {
		this.encryptedPasswordNew = encryptedPasswordNew;
	}

	public Long getChangeTimeMillis() {
		return changeTimeMillis;
	}

	public void setChangeTimeMillis(Long changeTimeMillis) {
		this.changeTimeMillis = changeTimeMillis;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
			case "passwordChangeSerialId" : return "PASSWORD_CHANGE_SERIAL_ID";
			case "changeTimeMillis" : return "CHANGE_TIME_MILLIS";
			case "userType" : return "USER_TYPE";
			case "userId" : return "USER_ID";
			case "encryptedPasswordOld" : return "ENCRYPTED_PASSWORD_OLD";
			case "encryptedPasswordNew" : return "ENCRYPTED_PASSWORD_NEW";
			case "userName" : return "USER_NAME";
		}
		return EMPTY_STRING;
	}

	@Override
	public PasswordChangeTracker clone() throws CloneNotSupportedException {  
		return (PasswordChangeTracker)super.clone();
	}
}
