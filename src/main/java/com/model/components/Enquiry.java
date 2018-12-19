package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class Enquiry extends GridComponentObject implements Serializable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private Long enquiryId;
	private Long customerId;
	private String customerName;
	private String customerEmail;
	private String customerContactNumber;
	private String subject;
	private String grade;
	private Integer quotedClientRate ;
	private Integer negotiatedRateWithClient;
	private String clientNegotiationRemarks;
	private String isMapped;
	private Long lastActionDateMillis;
	private Long entryDateMillis;
	private String matchStatus;
	private Long tutorId;
	private String tutorName;
	private String tutorEmail;
	private String tutorContactNumber;
	private String adminRemarks;
	private String locationDetails;
	private String addressDetails;
	private String additionalDetails;
	private String whoActed;
	private String whoActedName;
	private String preferredTeachingType;
	
	public Enquiry() {}
	
	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getQuotedClientRate() {
		return quotedClientRate;
	}

	public void setQuotedClientRate(Integer quotedClientRate) {
		this.quotedClientRate = quotedClientRate;
	}

	public Integer getNegotiatedRateWithClient() {
		return negotiatedRateWithClient;
	}

	public void setNegotiatedRateWithClient(Integer negotiatedRateWithClient) {
		this.negotiatedRateWithClient = negotiatedRateWithClient;
	}

	public String getClientNegotiationRemarks() {
		return clientNegotiationRemarks;
	}

	public void setClientNegotiationRemarks(String clientNegotiationRemarks) {
		this.clientNegotiationRemarks = clientNegotiationRemarks;
	}

	public String getIsMapped() {
		return isMapped;
	}

	public void setIsMapped(String isMapped) {
		this.isMapped = isMapped;
	}

	public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getAdminRemarks() {
		return adminRemarks;
	}

	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}
	
	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	
	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public String getPreferredTeachingType() {
		return preferredTeachingType;
	}

	public void setPreferredTeachingType(String preferredTeachingType) {
		this.preferredTeachingType = preferredTeachingType;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public String getTutorEmail() {
		return tutorEmail;
	}

	public void setTutorEmail(String tutorEmail) {
		this.tutorEmail = tutorEmail;
	}

	public String getTutorContactNumber() {
		return tutorContactNumber;
	}

	public void setTutorContactNumber(String tutorContactNumber) {
		this.tutorContactNumber = tutorContactNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public Long getLastActionDateMillis() {
		return lastActionDateMillis;
	}

	public void setLastActionDateMillis(Long lastActionDateMillis) {
		this.lastActionDateMillis = lastActionDateMillis;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
	}
	
	public Long getEntryDateMillis() {
		return entryDateMillis;
	}

	public void setEntryDateMillis(Long entryDateMillis) {
		this.entryDateMillis = entryDateMillis;
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
			case "enquiryId" : return "ENQUIRY_ID";
			case "customerId" : return "CUSTOMER_ID";
			case "customerName" : return "CUSTOMER_NAME";
			case "customerEmail" : return "CUSTOMER_EMAIL";
			case "customerContactNumber" : return "CUSTOMER_CONTACT_NUMBER";
			case "subject" : return "SUBJECT";
			case "grade" : return "GRADE";
			case "quotedClientRate" : return "QUOTED_CLIENT_RATE";
			case "negotiatedRateWithClient" : return "NEGOTIATED_RATE_WITH_CLIENT";
			case "clientNegotiationRemarks" : return "CLIENT_NEGOTIATION_REMARKS";
			case "isMapped" : return "IS_MAPPED";
			case "lastActionDateMillis" : return "LAST_ACTION_DATE_MILLIS";
			case "matchStatus" : return "MATCH_STATUS";
			case "tutorId" : return "TUTOR_ID";
			case "tutorName" : return "TUTOR_NAME";
			case "tutorEmail" : return "TUTOR_EMAIL";
			case "tutorContactNumber" : return "TUTOR_CONTACT_NUMBER";
			case "adminRemarks" : return "ADMIN_REMARKS";
			case "locationDetails" : return "LOCATION_DETAILS";
			case "addressDetails" : return "ADDRESS_DETAILS";
			case "additionalDetails" : return "ADDITIONAL_DETAILS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "preferredTeachingType" : return "PREFERRED_TEACHING_TYPE";
			case "entryDateMillis" : return "ENTRY_DATE_MILLIS";
		}
		return EMPTY_STRING;
	}
}
