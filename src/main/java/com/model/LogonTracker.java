package com.model;

import java.io.Serializable;

import com.utils.ValidationUtils;

public class LogonTracker extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long logonId;
	private Long loginTimeMillis;
	private String loginFrom;
	private String machineIp;
	private String userType;
	private String userId;
	private String userName;
	
	public LogonTracker() {}

	public Long getLogonId() {
		return logonId;
	}

	public void setLogonId(Long logonId) {
		this.logonId = logonId;
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

	public Long getLoginTimeMillis() {
		return loginTimeMillis;
	}

	public void setLoginTimeMillis(Long loginTimeMillis) {
		this.loginTimeMillis = loginTimeMillis;
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
			case "logonId" : return "LOGON_ID";
			case "loginTimeMillis" : return "LOGIN_TIME_MILLIS";
			case "loginFrom" : return "LOGIN_FROM";
			case "machineIp" : return "MACHINE_IP";
			case "userType" : return "USER_TYPE";
			case "userId" : return "USER_ID";
			case "userName" : return "USER_NAME";
		}
		return EMPTY_STRING;
	}

	@Override
	public LogonTracker clone() throws CloneNotSupportedException {  
		return (LogonTracker)super.clone();
	}
}
