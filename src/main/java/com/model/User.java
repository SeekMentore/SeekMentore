package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private String name;
	private String userId;
	private String userType;
	private String encyptedPassword;
	private List<String> pageAccessTypes;
	
	public User getACopy() {
		final User newInstance = new User();
		newInstance.name = name;
		newInstance.userId = userId;
		newInstance.userType = userType;
		newInstance.encyptedPassword = encyptedPassword;
		if (null != pageAccessTypes) {
			newInstance.pageAccessTypes = new ArrayList<String>();
			for (final String pageAccessType : pageAccessTypes) {
				newInstance.pageAccessTypes.add(pageAccessType);
			}
		}
		return newInstance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<String> getPageAccessTypes() {
		return pageAccessTypes;
	}
	public void setPageAccessTypes(List<String> pageAccessTypes) {
		this.pageAccessTypes = pageAccessTypes;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEncyptedPassword() {
		return encyptedPassword;
	}
	public void setEncyptedPassword(String encyptedPassword) {
		this.encyptedPassword = encyptedPassword;
	}
}
