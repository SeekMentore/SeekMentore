package com.model;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private String employeeId;
	private String name;
	private String userId;
	private String emailDomain;
	private String encyptedPassword;
	private String userType;
	
	public Employee getACopy() {
		final Employee newInstance = new Employee();
		newInstance.employeeId = employeeId;
		newInstance.name = name;
		newInstance.userId = userId;
		newInstance.emailDomain = emailDomain;
		newInstance.userType = userType;
		return newInstance;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEncyptedPassword() {
		return encyptedPassword;
	}

	public void setEncyptedPassword(String encyptedPassword) {
		this.encyptedPassword = encyptedPassword;
	}
	
}
