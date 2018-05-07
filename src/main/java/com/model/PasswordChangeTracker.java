package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.constants.UserConstants;

public class PasswordChangeTracker implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long passwordChangeId;
	private Timestamp changeTime;
	private String userType;
	private String userId;
	private String encyptedPasswordOld;
	private String encyptedPasswordNew;
	
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

	public Timestamp getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public String getEncyptedPasswordOld() {
		return encyptedPasswordOld;
	}

	public void setEncyptedPasswordOld(String encyptedPasswordOld) {
		this.encyptedPasswordOld = encyptedPasswordOld;
	}

	public String getEncyptedPasswordNew() {
		return encyptedPasswordNew;
	}

	public void setEncyptedPasswordNew(String encyptedPasswordNew) {
		this.encyptedPasswordNew = encyptedPasswordNew;
	}
	
}
