package com.model.components;

import java.io.Serializable;

import com.constants.components.AdminConstants;
import com.constants.components.CustomerConstants;
import com.constants.components.SelectLookupConstants;
import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.ValidationUtils;

public class SubscribedCustomer extends GridComponentObject implements Serializable, CustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long customerId;
	private String name;
	private String contactNumber;
	private String emailId;
	private Long findTutorId ;
	private String studentGrades;
	private String interestedSubjects;
	private String location;
	private String additionalDetails;
	private String addressDetails;
	private String encryptedPassword;
	private String userId;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private String isBlacklisted;
	private String blacklistedRemarks;
	private Long blacklistedDateMillis;
	private String whoBlacklisted;
	private String whoBlacklistedName;
	private String unblacklistedRemarks;
	private Long unblacklistedDateMillis;
	private String whoUnBlacklisted;
	private String whoUnBlacklistedName;
	
	public SubscribedCustomer() {}
	
	public SubscribedCustomer getACopy() {
		final SubscribedCustomer newInstance = new SubscribedCustomer();
		newInstance.customerId = customerId;
		newInstance.name = name;
		newInstance.contactNumber = contactNumber;
		newInstance.emailId = emailId;
		newInstance.findTutorId = findTutorId;
		newInstance.studentGrades = studentGrades;
		newInstance.interestedSubjects = interestedSubjects;
		newInstance.location = location;
		newInstance.additionalDetails = additionalDetails;
		newInstance.addressDetails = addressDetails;
		newInstance.encryptedPassword = encryptedPassword;
		newInstance.userId = userId;
		newInstance.recordLastUpdatedMillis = recordLastUpdatedMillis;
		newInstance.updatedBy = updatedBy;
		newInstance.isBlacklisted = this.isBlacklisted;
		newInstance.blacklistedRemarks = this.blacklistedRemarks;
		newInstance.blacklistedDateMillis = this.blacklistedDateMillis;
		newInstance.whoBlacklisted = this.whoBlacklisted;
		newInstance.whoBlacklistedName = this.whoBlacklistedName;
		newInstance.unblacklistedRemarks = this.unblacklistedRemarks;
		newInstance.unblacklistedDateMillis = this.unblacklistedDateMillis;
		newInstance.whoUnBlacklisted = this.whoUnBlacklisted;
		newInstance.whoUnBlacklistedName = this.whoUnBlacklistedName;
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

	public Long getFindTutorId() {
		return findTutorId;
	}

	public void setFindTutorId(Long findTutorId) {
		this.findTutorId = findTutorId;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	
	public String getIsBlacklisted() {
		return isBlacklisted;
	}

	public void setIsBlacklisted(String isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}

	public String getBlacklistedRemarks() {
		return blacklistedRemarks;
	}

	public void setBlacklistedRemarks(String blacklistedRemarks) {
		this.blacklistedRemarks = blacklistedRemarks;
	}

	public Long getBlacklistedDateMillis() {
		return blacklistedDateMillis;
	}

	public void setBlacklistedDateMillis(Long blacklistedDateMillis) {
		this.blacklistedDateMillis = blacklistedDateMillis;
	}

	public String getWhoBlacklisted() {
		return whoBlacklisted;
	}

	public void setWhoBlacklisted(String whoBlacklisted) {
		this.whoBlacklisted = whoBlacklisted;
	}

	public String getWhoBlacklistedName() {
		return whoBlacklistedName;
	}

	public void setWhoBlacklistedName(String whoBlacklistedName) {
		this.whoBlacklistedName = whoBlacklistedName;
	}

	public String getUnblacklistedRemarks() {
		return unblacklistedRemarks;
	}

	public void setUnblacklistedRemarks(String unblacklistedRemarks) {
		this.unblacklistedRemarks = unblacklistedRemarks;
	}

	public Long getUnblacklistedDateMillis() {
		return unblacklistedDateMillis;
	}

	public void setUnblacklistedDateMillis(Long unblacklistedDateMillis) {
		this.unblacklistedDateMillis = unblacklistedDateMillis;
	}

	public String getWhoUnBlacklisted() {
		return whoUnBlacklisted;
	}

	public void setWhoUnBlacklisted(String whoUnBlacklisted) {
		this.whoUnBlacklisted = whoUnBlacklisted;
	}

	public String getWhoUnBlacklistedName() {
		return whoUnBlacklistedName;
	}

	public void setWhoUnBlacklistedName(String whoUnBlacklistedName) {
		this.whoUnBlacklistedName = whoUnBlacklistedName;
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.ADMIN_REPORT : {
				return new Object[] {
						"CUSTOMER_ID",
						"FIND_TUTOR_ID",
						"USER_ID",
						"NAME",
						"CONTACT_NUMBER",
						"EMAIL_ID",
						"STUDENT_GRADES",
						"SUBJECTS",
						"LOCATIONS",
						"ADDITIONAL_DETAILS",
						"ADDRESS_DETAILS",
						"IS_BLACKLISTED",
						"BLACKLISTED_REMARKS",
						"BLACKLISTED_DATE_MILLIS",
						"WHO_BLACKLISTED",
						"UN_BLACKLISTED_DATE_MILLIS",
						"WHO_UN_BLACKLISTED_NAME",
						"RECORD_LAST_UPDATED",
						"UPDATED_BY"
					};
			}
		}
		return new Object[] {};
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		switch (reportSwitch) {
			case AdminConstants.ADMIN_REPORT : {
				return new Object[] {
						this.customerId,
						this.findTutorId,
						this.userId,
						this.name,
						this.contactNumber,
						this.emailId,
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, this.studentGrades, null, null),
						ApplicationUtils.getSelectLookupItemListConcatenatedLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, this.interestedSubjects, null, null),
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, this.location),
						this.additionalDetails,
						this.addressDetails,
						ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_YES_NO_LOOKUP, this.isBlacklisted),
						this.blacklistedRemarks,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.blacklistedDateMillis),
						this.whoBlacklistedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.unblacklistedDateMillis),
						this.whoUnBlacklistedName,
						DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(this.recordLastUpdatedMillis),
						this.updatedByName
					};
			}
		}
		return new Object[] {};
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "customerId" : return "CUSTOMER_ID";
			case "name" : return "NAME";
			case "contactNumber" : return "CONTACT_NUMBER";
			case "emailId" : return "EMAIL_ID";
			case "findTutorId" : return "FIND_TUTOR_ID";
			case "studentGrades" : return "STUDENT_GRADE";
			case "interestedSubjects" : return "SUBJECTS";
			case "location" : return "LOCATION";
			case "additionalDetails" : return "ADDITIONAL_DETAILS";
			case "addressDetails" : return "ADDRESS_DETAILS";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "userId" : return "USER_ID";
			case "encryptedPassword" : return "ENCRYPTED_PASSWORD";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
			case "isBlacklisted" : return "IS_BLACKLISTED";
			case "blacklistedRemarks" : return "BLACKLISTED_REMARKS";
			case "blacklistedDateMillis" : return "BLACKLISTED_DATE_MILLIS";
			case "whoBlacklisted" : return "WHO_BLACKLISTED";
			case "whoBlacklistedName" : return "WHO_BLACKLISTED_NAME";
			case "unblacklistedRemarks" : return "UN_BLACKLISTED_REMARKS";
			case "unblacklistedDateMillis" : return "UN_BLACKLISTED_DATE_MILLIS";
			case "whoUnBlacklisted" : return "WHO_UN_BLACKLISTED";
			case "whoUnBlacklistedName" : return "WHO_UN_BLACKLISTED_NAME";
		}
		return EMPTY_STRING;
	}
}
