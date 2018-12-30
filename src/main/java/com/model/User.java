package com.model;

import java.io.Serializable;
import java.util.List;

import com.utils.ValidationUtils;

public class User extends GridComponentObject implements Serializable, Cloneable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private String name;
	private String emailId;
	private String contactNumber;
	private String userId;
	private String encryptedPassword;
	private String userType;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private List<String> pageAccessTypes;
	private UserAccessOptions accessOptions;
	
	public User() {}
	
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

	public UserAccessOptions getAccessOptions() {
		return accessOptions;
	}

	public void setAccessOptions(UserAccessOptions accessOptions) {
		this.accessOptions = accessOptions;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "name" : return "NAME";
			case "emailId" : return "EMAIL_ID";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "userId" : return "USER_ID";
			case "encryptedPassword" : return "ENCRYPTED_PASSWORD";
			case "userType" : return "USER_TYPE";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}

	@Override
	public User clone() throws CloneNotSupportedException {  
		return (User)super.clone();
	}
}
