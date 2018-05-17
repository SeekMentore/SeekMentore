package com.model.components;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.CustomerConstants;
import com.model.ApplicationWorkbookObject;

public class SubscribedCustomer implements Serializable, CustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long customerId;
	private String name;
	private String contactNumber;
	private String emailId;
	private Long enquiryID ;
	private String studentGrades;
	private String interestedSubjects;
	private String location;
	private String additionalDetails;
	private String addressDetails;
	private String encryptedPassword;
	private String userId;
	private Date recordLastUpdated;
	private String updatedBy;
	
	public SubscribedCustomer() {}

	public SubscribedCustomer getACopy() {
		final SubscribedCustomer newInstance = new SubscribedCustomer();
		newInstance.customerId = customerId;
		newInstance.name = name;
		newInstance.contactNumber = contactNumber;
		newInstance.emailId = emailId;
		newInstance.enquiryID = enquiryID;
		newInstance.studentGrades = studentGrades;
		newInstance.interestedSubjects = interestedSubjects;
		newInstance.location = location;
		newInstance.additionalDetails = additionalDetails;
		newInstance.addressDetails = addressDetails;
		newInstance.encryptedPassword = encryptedPassword;
		newInstance.userId = userId;
		newInstance.recordLastUpdated = recordLastUpdated;
		newInstance.updatedBy = updatedBy;
		return newInstance;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getEnquiryID() {
		return enquiryID;
	}

	public void setEnquiryID(Long enquiryID) {
		this.enquiryID = enquiryID;
	}

	public String getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(String studentGrades) {
		this.studentGrades = studentGrades;
	}

	public String getInterestedSubjects() {
		return interestedSubjects;
	}

	public void setInterestedSubjects(String interestedSubjects) {
		this.interestedSubjects = interestedSubjects;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getRecordLastUpdated() {
		return recordLastUpdated;
	}

	public void setRecordLastUpdated(Date recordLastUpdated) {
		this.recordLastUpdated = recordLastUpdated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
			case "Admin_Report" : {
				return new Object[] {
						"NAME",
						"CONTACT_NUMBER",
						"EMAIL_ID",
						"STUDENT_GRADES",
						"SUBJECTS",
						"LOCATIONS",
						"ADDRESS_DETAILS",
						"ADDITIONAL_DETAILS",
						"RECORD_LAST_UPDATED",
						"UPDATED_BY",
						"USER_ID"
					};
			}
		}
		return new Object[] {};
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		switch (reportSwitch) {
			case "Admin_Report" : {
				return new Object[] {
						this.name,
						this.contactNumber,
						this.emailId,
						this.studentGrades,
						this.interestedSubjects,
						this.location,
						this.addressDetails,
						this.additionalDetails,
						this.recordLastUpdated,
						this.updatedBy,
						this.userId
					};
			}
		}
		return new Object[] {};
	}
}
