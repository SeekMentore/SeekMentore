package com.model;

import java.io.Serializable;
import java.util.Date;

import com.constants.UserConstants;

public class PasswordChangeTracker implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long passwordChangeId;
	private Date changeTime;
	private String userType;
	private String userId;
	private String encryptedPasswordOld;
	private String encryptedPasswordNew;
	
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

	public Long getPasswordChangeId() {
		return passwordChangeId;
	}

	public void setPasswordChangeId(Long passwordChangeId) {
		this.passwordChangeId = passwordChangeId;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
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
	
}
