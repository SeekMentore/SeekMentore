package com.model;

import java.io.Serializable;
import java.util.Date;

import com.constants.UserConstants;

public class LogonTracker implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long logonId;
	private Date loginTime;
	private String loginFrom;
	private String machineIp;
	private String userType;
	private String userId;
	
	public LogonTracker() {}

	public Long getLogonId() {
		return logonId;
	}

	public void setLogonId(Long logonId) {
		this.logonId = logonId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

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
	
}
