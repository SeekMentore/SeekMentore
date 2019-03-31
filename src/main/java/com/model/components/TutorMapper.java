package com.model.components;

import java.io.Serializable;

import com.constants.components.CustomerConstants;
import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class TutorMapper extends GridComponentObject implements Serializable, CustomerConstants, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private String tutorMapperSerialId;
	private Long enquiryId;
	private String enquirySubject;
	private String enquiryGrade;
	private String enquiryLocation;
	private String enquiryAddressDetails;
	private String enquiryAdditionalDetails;
	private String enquiryPreferredTeachingType;
	private Integer enquiryQuotedClientRate ;
	private Integer enquiryNegotiatedRateWithClient;
	private String enquiryClientNegotiationRemarks;
	private Long customerId;
	private String customerName;
	private String customerEmail;
	private String customerContactNumber;
	private Long tutorId;
	private String tutorName;
	private String tutorEmail;
	private String tutorContactNumber;
	private Integer quotedTutorRate;
	private Integer negotiatedRateWithTutor;
	private String tutorNegotiationRemarks;
	private String isTutorContacted;
	private Long tutorContactedDateMillis;
	private String isTutorAgreed;
	private String isTutorRejectionValid;
	private String adminTutorRejectionValidityResponse;
	private String tutorResponse;
	private String adminRemarksForTutor;
	private String isClientContacted;
	private Long clientContactedDateMillis;
	private String isClientAgreed;
	private String clientResponse;
	private String isClientRejectionValid;
	private String adminClientRejectionValidityResponse;
	private String adminRemarksForClient;
	private Long adminActionDateMillis;
	private String adminActionRemarks;
	private String whoActed;
	private String whoActedName;
	private String isDemoScheduled;
	private Long demoDateAndTimeMillis;
	private String mappingStatus;
	private Long entryDateMillis;
	private String isEnquiryClosed;
	private Long enquiryClosedMillis;
	private String enquiryEmail;
	private String enquiryContactNumber;
	// Action Button Security Properties
	private Boolean showUnMap = false;
	private Boolean enableUnMap = false;
	
	public TutorMapper() {}
	
	public String getTutorMapperSerialId() {
		return tutorMapperSerialId;
	}

	public void setTutorMapperSerialId(String tutorMapperSerialId) {
		this.tutorMapperSerialId = tutorMapperSerialId;
	}

	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public Integer getQuotedTutorRate() {
		return quotedTutorRate;
	}

	public void setQuotedTutorRate(Integer quotedTutorRate) {
		this.quotedTutorRate = quotedTutorRate;
	}

	public Integer getNegotiatedRateWithTutor() {
		return negotiatedRateWithTutor;
	}

	public void setNegotiatedRateWithTutor(Integer negotiatedRateWithTutor) {
		this.negotiatedRateWithTutor = negotiatedRateWithTutor;
	}

	public String getTutorNegotiationRemarks() {
		return tutorNegotiationRemarks;
	}

	public void setTutorNegotiationRemarks(String tutorNegotiationRemarks) {
		this.tutorNegotiationRemarks = tutorNegotiationRemarks;
	}

	public String getIsTutorContacted() {
		return isTutorContacted;
	}

	public void setIsTutorContacted(String isTutorContacted) {
		this.isTutorContacted = isTutorContacted;
	}

	public String getIsTutorAgreed() {
		return isTutorAgreed;
	}

	public void setIsTutorAgreed(String isTutorAgreed) {
		this.isTutorAgreed = isTutorAgreed;
	}

	public String getIsTutorRejectionValid() {
		return isTutorRejectionValid;
	}

	public void setIsTutorRejectionValid(String isTutorRejectionValid) {
		this.isTutorRejectionValid = isTutorRejectionValid;
	}

	public String getAdminTutorRejectionValidityResponse() {
		return adminTutorRejectionValidityResponse;
	}

	public void setAdminTutorRejectionValidityResponse(String adminTutorRejectionValidityResponse) {
		this.adminTutorRejectionValidityResponse = adminTutorRejectionValidityResponse;
	}

	public String getTutorResponse() {
		return tutorResponse;
	}

	public void setTutorResponse(String tutorResponse) {
		this.tutorResponse = tutorResponse;
	}

	public String getAdminRemarksForTutor() {
		return adminRemarksForTutor;
	}

	public void setAdminRemarksForTutor(String adminRemarksForTutor) {
		this.adminRemarksForTutor = adminRemarksForTutor;
	}

	public String getIsClientContacted() {
		return isClientContacted;
	}

	public void setIsClientContacted(String isClientContacted) {
		this.isClientContacted = isClientContacted;
	}

	public String getIsClientAgreed() {
		return isClientAgreed;
	}

	public void setIsClientAgreed(String isClientAgreed) {
		this.isClientAgreed = isClientAgreed;
	}

	public String getClientResponse() {
		return clientResponse;
	}

	public void setClientResponse(String clientResponse) {
		this.clientResponse = clientResponse;
	}

	public String getIsClientRejectionValid() {
		return isClientRejectionValid;
	}

	public void setIsClientRejectionValid(String isClientRejectionValid) {
		this.isClientRejectionValid = isClientRejectionValid;
	}

	public String getAdminClientRejectionValidityResponse() {
		return adminClientRejectionValidityResponse;
	}

	public void setAdminClientRejectionValidityResponse(String adminClientRejectionValidityResponse) {
		this.adminClientRejectionValidityResponse = adminClientRejectionValidityResponse;
	}

	public String getAdminRemarksForClient() {
		return adminRemarksForClient;
	}

	public void setAdminRemarksForClient(String adminRemarksForClient) {
		this.adminRemarksForClient = adminRemarksForClient;
	}

	public String getAdminActionRemarks() {
		return adminActionRemarks;
	}

	public void setAdminActionRemarks(String adminActionRemarks) {
		this.adminActionRemarks = adminActionRemarks;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getIsDemoScheduled() {
		return isDemoScheduled;
	}

	public void setIsDemoScheduled(String isDemoScheduled) {
		this.isDemoScheduled = isDemoScheduled;
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
	
	public String getMappingStatus() {
		return mappingStatus;
	}

	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}
	
	public Long getTutorContactedDateMillis() {
		return tutorContactedDateMillis;
	}

	public void setTutorContactedDateMillis(Long tutorContactedDateMillis) {
		this.tutorContactedDateMillis = tutorContactedDateMillis;
	}

	public Long getClientContactedDateMillis() {
		return clientContactedDateMillis;
	}

	public void setClientContactedDateMillis(Long clientContactedDateMillis) {
		this.clientContactedDateMillis = clientContactedDateMillis;
	}

	public Long getAdminActionDateMillis() {
		return adminActionDateMillis;
	}

	public void setAdminActionDateMillis(Long adminActionDateMillis) {
		this.adminActionDateMillis = adminActionDateMillis;
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

	public String getEnquirySubject() {
		return enquirySubject;
	}

	public void setEnquirySubject(String enquirySubject) {
		this.enquirySubject = enquirySubject;
	}

	public String getEnquiryGrade() {
		return enquiryGrade;
	}

	public void setEnquiryGrade(String enquiryGrade) {
		this.enquiryGrade = enquiryGrade;
	}

	public String getEnquiryLocation() {
		return enquiryLocation;
	}

	public void setEnquiryLocation(String enquiryLocation) {
		this.enquiryLocation = enquiryLocation;
	}

	public String getEnquiryPreferredTeachingType() {
		return enquiryPreferredTeachingType;
	}

	public void setEnquiryPreferredTeachingType(String enquiryPreferredTeachingType) {
		this.enquiryPreferredTeachingType = enquiryPreferredTeachingType;
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

	public Integer getEnquiryQuotedClientRate() {
		return enquiryQuotedClientRate;
	}

	public void setEnquiryQuotedClientRate(Integer enquiryQuotedClientRate) {
		this.enquiryQuotedClientRate = enquiryQuotedClientRate;
	}

	public Integer getEnquiryNegotiatedRateWithClient() {
		return enquiryNegotiatedRateWithClient;
	}

	public void setEnquiryNegotiatedRateWithClient(Integer enquiryNegotiatedRateWithClient) {
		this.enquiryNegotiatedRateWithClient = enquiryNegotiatedRateWithClient;
	}

	public String getEnquiryClientNegotiationRemarks() {
		return enquiryClientNegotiationRemarks;
	}

	public void setEnquiryClientNegotiationRemarks(String enquiryClientNegotiationRemarks) {
		this.enquiryClientNegotiationRemarks = enquiryClientNegotiationRemarks;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getDemoDateAndTimeMillis() {
		return demoDateAndTimeMillis;
	}

	public void setDemoDateAndTimeMillis(Long demoDateAndTimeMillis) {
		this.demoDateAndTimeMillis = demoDateAndTimeMillis;
	}
	
	public String getEnquiryAddressDetails() {
		return enquiryAddressDetails;
	}

	public void setEnquiryAddressDetails(String enquiryAddressDetails) {
		this.enquiryAddressDetails = enquiryAddressDetails;
	}

	public String getEnquiryAdditionalDetails() {
		return enquiryAdditionalDetails;
	}

	public void setEnquiryAdditionalDetails(String enquiryAdditionalDetails) {
		this.enquiryAdditionalDetails = enquiryAdditionalDetails;
	}

	public String getIsEnquiryClosed() {
		return isEnquiryClosed;
	}

	public void setIsEnquiryClosed(String isEnquiryClosed) {
		this.isEnquiryClosed = isEnquiryClosed;
	}

	public Long getEnquiryClosedMillis() {
		return enquiryClosedMillis;
	}

	public void setEnquiryClosedMillis(Long enquiryClosedMillis) {
		this.enquiryClosedMillis = enquiryClosedMillis;
	}
	
	public String getEnquiryEmail() {
		return enquiryEmail;
	}

	public void setEnquiryEmail(String enquiryEmail) {
		this.enquiryEmail = enquiryEmail;
	}

	public String getEnquiryContactNumber() {
		return enquiryContactNumber;
	}

	public void setEnquiryContactNumber(String enquiryContactNumber) {
		this.enquiryContactNumber = enquiryContactNumber;
	}
	
	public Boolean getShowUnMap() {
		return showUnMap;
	}

	public void setShowUnMap(Boolean showUnMap) {
		this.showUnMap = showUnMap;
	}
	
	public Boolean getEnableUnMap() {
		return enableUnMap;
	}

	public void setEnableUnMap(Boolean enableUnMap) {
		this.enableUnMap = enableUnMap;
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
			case "tutorMapperSerialId" : return "TUTOR_MAPPER_SERIAL_ID";
			case "enquiryId" : return "ENQUIRY_ID";
			case "enquirySubject" : return "ENQUIRY_SUBJECT";
			case "enquiryGrade" : return "ENQUIRY_GRADE";
			case "enquiryLocation" : return "ENQUIRY_LOCATION";
			case "enquiryAddressDetails" : return "ENQUIRY_ADDRESS_DETAILS";
			case "enquiryAdditionalDetails" : return "ENQUIRY_ADDITIONAL_DETAILS";
			case "enquiryPreferredTeachingType" : return "ENQUIRY_PREFERRED_TEACHING_TYPE";
			case "enquiryQuotedClientRate" : return "ENQUIRY_QUOTED_CLIENT_RATE";
			case "enquiryNegotiatedRateWithClient" : return "ENQUIRY_NEGOTIATED_RATE_WITH_CLIENT";
			case "enquiryClientNegotiationRemarks" : return "ENQUIRY_CLIENT_NEGOTIATION_REMARKS";
			case "enquiryEmail" : return "ENQUIRY_EMAIL";
			case "enquiryContactNumber" : return "ENQUIRY_CONTACT_NUMBER";
			case "customerId" : return "CUSTOMER_ID";
			case "customerName" : return "CUSTOMER_NAME";
			case "customerEmail" : return "CUSTOMER_EMAIL";
			case "customerContactNumber" : return "CUSTOMER_CONTACT_NUMBER";
			case "tutorId" : return "TUTOR_ID";
			case "tutorName" : return "TUTOR_NAME";
			case "tutorEmail" : return "TUTOR_EMAIL";
			case "tutorContactNumber" : return "TUTOR_CONTACT_NUMBER";
			case "quotedTutorRate" : return "QUOTED_TUTOR_RATE";
			case "negotiatedRateWithTutor" : return "NEGOTIATED_RATE_WITH_TUTOR";
			case "tutorNegotiationRemarks" : return "TUTOR_NEGOTIATION_REMARKS";
			case "isTutorContacted" : return "IS_TUTOR_CONTACTED";
			case "tutorContactedDateMillis" : return "TUTOR_CONTACTED_DATE_MILLIS";
			case "isTutorAgreed" : return "IS_TUTOR_AGREED";
			case "isTutorRejectionValid" : return "IS_TUTOR_REJECTION_VALID";
			case "adminTutorRejectionValidityResponse" : return "ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE";
			case "tutorResponse" : return "TUTOR_RESPONSE";
			case "adminRemarksForTutor" : return "ADMIN_REMARKS_FOR_TUTOR";
			case "isClientContacted" : return "IS_CLIENT_CONTACTED";
			case "clientContactedDateMillis" : return "CLIENT_CONTACTED_DATE_MILLIS";
			case "isClientAgreed" : return "IS_CLIENT_AGREED";
			case "clientResponse" : return "CLIENT_RESPONSE";
			case "isClientRejectionValid" : return "IS_CLIENT_REJECTION_VALID";
			case "adminClientRejectionValidityResponse" : return "ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE";
			case "adminRemarksForClient" : return "ADMIN_REMARKS_FOR_CLIENT";
			case "adminActionDateMillis" : return "ADMIN_ACTION_DATE_MILLIS";
			case "adminActionRemarks" : return "ADMIN_ACTION_REMARKS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "isDemoScheduled" : return "IS_DEMO_SCHEDULED";
			case "mappingStatus" : return "MAPPING_STATUS";
			case "entryDateMillis" : return "ENTRY_DATE_MILLIS";
			case "isEnquiryClosed" : return "IS_ENQUIRY_CLOSED";
			case "enquiryClosedMillis" : return "ENQUIRY_CLOSED_MILLIS";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public TutorMapper clone() throws CloneNotSupportedException {  
		return (TutorMapper)super.clone();
	}
}
