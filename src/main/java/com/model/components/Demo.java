package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class Demo extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private String demoSerialId;
	private String tutorMapperSerialId;
	private Long demoDateAndTimeMillis;
	private String demoOccurred;
	private String demoStatus;
	private String clientRemarks;
	private String tutorRemarks;
	private String clientSatisfiedFromTutor;
	private String tutorSatisfiedWithClient;
	private String adminSatisfiedFromTutor;
	private String adminSatisfiedWithClient;
	private String whoActed;
	private String whoActedName;
	private String isDemoSuccess;
	private String needPriceNegotiationWithClient;
	private String clientNegotiationRemarks;
	private String needPriceNegotiationWithTutor;
	private String tutorNegotiationRemarks;
	private String adminRemarks;
	private Integer negotiatedOverrideRateWithClient;
	private Integer negotiatedOverrideRateWithTutor;
	private Long adminActionDateMillis;
	private String customerSerialId;
	private String customerName;
	private String customerEmail;
	private String customerContactNumber;
	private String tutorSerialId;
	private String tutorName;
	private String tutorEmail;
	private String tutorContactNumber;
	private String enquirySerialId;
	private String enquirySubject;
	private String enquiryGrade;
	private String enquiryLocation;
	private String enquiryAddressDetails;
	private String enquiryAdditionalDetails;
	private String enquiryPreferredTeachingType;
	private Integer enquiryQuotedClientRate ;
	private Integer enquiryNegotiatedRateWithClient;
	private String enquiryClientNegotiationRemarks;
	private Integer tutorMapperQuotedTutorRate;
	private Integer tutorMapperNegotiatedRateWithTutor;
	private String tutorMapperTutorNegotiationRemarks;
	private String adminFinalizingRemarks;
	private String reschedulingRemarks;
	private Integer reScheduleCount;
	private String rescheduledFromDemoSerialId;
	private Long entryDateMillis;
	private String isSubscriptionCreated;
	private Long subscriptionCreatedMillis;
	private String isEnquiryClosed;
	private Long enquiryClosedMillis;
	private String enquiryEmail;
	private String enquiryContactNumber;
	
	public Demo() {}
	
	public String getDemoSerialId() {
		return demoSerialId;
	}

	public void setDemoSerialId(String demoSerialId) {
		this.demoSerialId = demoSerialId;
	}
	
	public String getTutorMapperSerialId() {
		return tutorMapperSerialId;
	}

	public void setTutorMapperSerialId(String tutorMapperSerialId) {
		this.tutorMapperSerialId = tutorMapperSerialId;
	}

	public String getDemoOccurred() {
		return demoOccurred;
	}

	public void setDemoOccurred(String demoOccurred) {
		this.demoOccurred = demoOccurred;
	}

	public String getDemoStatus() {
		return demoStatus;
	}

	public void setDemoStatus(String demoStatus) {
		this.demoStatus = demoStatus;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public String getTutorRemarks() {
		return tutorRemarks;
	}

	public void setTutorRemarks(String tutorRemarks) {
		this.tutorRemarks = tutorRemarks;
	}

	public String getClientSatisfiedFromTutor() {
		return clientSatisfiedFromTutor;
	}

	public void setClientSatisfiedFromTutor(String clientSatisfiedFromTutor) {
		this.clientSatisfiedFromTutor = clientSatisfiedFromTutor;
	}

	public String getTutorSatisfiedWithClient() {
		return tutorSatisfiedWithClient;
	}

	public void setTutorSatisfiedWithClient(String tutorSatisfiedWithClient) {
		this.tutorSatisfiedWithClient = tutorSatisfiedWithClient;
	}

	public String getAdminSatisfiedFromTutor() {
		return adminSatisfiedFromTutor;
	}

	public void setAdminSatisfiedFromTutor(String adminSatisfiedFromTutor) {
		this.adminSatisfiedFromTutor = adminSatisfiedFromTutor;
	}

	public String getAdminSatisfiedWithClient() {
		return adminSatisfiedWithClient;
	}

	public void setAdminSatisfiedWithClient(String adminSatisfiedWithClient) {
		this.adminSatisfiedWithClient = adminSatisfiedWithClient;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getIsDemoSuccess() {
		return isDemoSuccess;
	}

	public void setIsDemoSuccess(String isDemoSuccess) {
		this.isDemoSuccess = isDemoSuccess;
	}

	public String getNeedPriceNegotiationWithClient() {
		return needPriceNegotiationWithClient;
	}

	public void setNeedPriceNegotiationWithClient(String needPriceNegotiationWithClient) {
		this.needPriceNegotiationWithClient = needPriceNegotiationWithClient;
	}

	public String getClientNegotiationRemarks() {
		return clientNegotiationRemarks;
	}

	public void setClientNegotiationRemarks(String clientNegotiationRemarks) {
		this.clientNegotiationRemarks = clientNegotiationRemarks;
	}

	public String getNeedPriceNegotiationWithTutor() {
		return needPriceNegotiationWithTutor;
	}

	public void setNeedPriceNegotiationWithTutor(String needPriceNegotiationWithTutor) {
		this.needPriceNegotiationWithTutor = needPriceNegotiationWithTutor;
	}

	public String getTutorNegotiationRemarks() {
		return tutorNegotiationRemarks;
	}

	public void setTutorNegotiationRemarks(String tutorNegotiationRemarks) {
		this.tutorNegotiationRemarks = tutorNegotiationRemarks;
	}

	public String getAdminRemarks() {
		return adminRemarks;
	}

	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}
	
	public Integer getNegotiatedOverrideRateWithClient() {
		return negotiatedOverrideRateWithClient;
	}

	public void setNegotiatedOverrideRateWithClient(Integer negotiatedOverrideRateWithClient) {
		this.negotiatedOverrideRateWithClient = negotiatedOverrideRateWithClient;
	}

	public Integer getNegotiatedOverrideRateWithTutor() {
		return negotiatedOverrideRateWithTutor;
	}

	public void setNegotiatedOverrideRateWithTutor(Integer negotiatedOverrideRateWithTutor) {
		this.negotiatedOverrideRateWithTutor = negotiatedOverrideRateWithTutor;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public String getAdminFinalizingRemarks() {
		return adminFinalizingRemarks;
	}

	public void setAdminFinalizingRemarks(String adminFinalizingRemarks) {
		this.adminFinalizingRemarks = adminFinalizingRemarks;
	}
	
	public String getReschedulingRemarks() {
		return reschedulingRemarks;
	}

	public void setReschedulingRemarks(String reschedulingRemarks) {
		this.reschedulingRemarks = reschedulingRemarks;
	}
	
	public Long getDemoDateAndTimeMillis() {
		return demoDateAndTimeMillis;
	}

	public void setDemoDateAndTimeMillis(Long demoDateAndTimeMillis) {
		this.demoDateAndTimeMillis = demoDateAndTimeMillis;
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
	
	public Long getEntryDateMillis() {
		return entryDateMillis;
	}

	public void setEntryDateMillis(Long entryDateMillis) {
		this.entryDateMillis = entryDateMillis;
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
	
	public Integer getTutorMapperQuotedTutorRate() {
		return tutorMapperQuotedTutorRate;
	}

	public void setTutorMapperQuotedTutorRate(Integer tutorMapperQuotedTutorRate) {
		this.tutorMapperQuotedTutorRate = tutorMapperQuotedTutorRate;
	}

	public Integer getTutorMapperNegotiatedRateWithTutor() {
		return tutorMapperNegotiatedRateWithTutor;
	}

	public void setTutorMapperNegotiatedRateWithTutor(Integer tutorMapperNegotiatedRateWithTutor) {
		this.tutorMapperNegotiatedRateWithTutor = tutorMapperNegotiatedRateWithTutor;
	}

	public String getTutorMapperTutorNegotiationRemarks() {
		return tutorMapperTutorNegotiationRemarks;
	}

	public void setTutorMapperTutorNegotiationRemarks(String tutorMapperTutorNegotiationRemarks) {
		this.tutorMapperTutorNegotiationRemarks = tutorMapperTutorNegotiationRemarks;
	}

	public String getCustomerSerialId() {
		return customerSerialId;
	}

	public void setCustomerSerialId(String customerSerialId) {
		this.customerSerialId = customerSerialId;
	}

	public String getTutorSerialId() {
		return tutorSerialId;
	}

	public void setTutorSerialId(String tutorSerialId) {
		this.tutorSerialId = tutorSerialId;
	}

	public String getEnquirySerialId() {
		return enquirySerialId;
	}

	public void setEnquirySerialId(String enquirySerialId) {
		this.enquirySerialId = enquirySerialId;
	}
	
	public Integer getReScheduleCount() {
		return reScheduleCount;
	}

	public void setReScheduleCount(Integer reScheduleCount) {
		this.reScheduleCount = reScheduleCount;
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

	public String getIsSubscriptionCreated() {
		return isSubscriptionCreated;
	}

	public void setIsSubscriptionCreated(String isSubscriptionCreated) {
		this.isSubscriptionCreated = isSubscriptionCreated;
	}

	public Long getSubscriptionCreatedMillis() {
		return subscriptionCreatedMillis;
	}

	public void setSubscriptionCreatedMillis(Long subscriptionCreatedMillis) {
		this.subscriptionCreatedMillis = subscriptionCreatedMillis;
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
	
	public String getRescheduledFromDemoSerialId() {
		return rescheduledFromDemoSerialId;
	}

	public void setRescheduledFromDemoSerialId(String rescheduledFromDemoSerialId) {
		this.rescheduledFromDemoSerialId = rescheduledFromDemoSerialId;
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
			case "demoSerialId" : return "DEMO_SERIAL_ID";
			case "tutorMapperSerialId" : return "TUTOR_MAPPER_SERIAL_ID";
			case "demoDateAndTimeMillis" : return "DEMO_DATE_AND_TIME_MILLIS";
			case "demoOccurred" : return "DEMO_OCCURRED";
			case "demoStatus" : return "DEMO_STATUS";
			case "clientRemarks" : return "CLIENT_REMARKS";
			case "tutorRemarks" : return "TUTOR_REMARKS";
			case "clientSatisfiedFromTutor" : return "CLIENT_SATISFIED_FROM_TUTOR";
			case "tutorSatisfiedWithClient" : return "TUTOR_SATISFIED_WITH_CLIENT";
			case "adminSatisfiedFromTutor" : return "ADMIN_SATISFIED_FROM_TUTOR";
			case "adminSatisfiedWithClient" : return "ADMIN_SATISFIED_WITH_CLIENT";
			case "isDemoSuccess" : return "IS_DEMO_SUCCESS";
			case "needPriceNegotiationWithClient" : return "NEED_PRICE_NEGOTIATION_WITH_CLIENT";
			case "clientNegotiationRemarks" : return "CLIENT_NEGOTIATION_REMARKS";
			case "needPriceNegotiationWithTutor" : return "NEED_PRICE_NEGOTIATION_WITH_TUTOR";
			case "tutorNegotiationRemarks" : return "TUTOR_NEGOTIATION_REMARKS";
			case "adminRemarks" : return "ADMIN_REMARKS";
			case "negotiatedOverrideRateWithClient" : return "NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT";
			case "negotiatedOverrideRateWithTutor" : return "NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR";
			case "adminActionDateMillis" : return "ADMIN_ACTION_DATE_MILLIS";
			case "adminFinalizingRemarks" : return "ADMIN_FINALIZING_REMARKS";
			case "reschedulingRemarks" : return "RESCHEDULING_REMARKS";
			case "reScheduleCount" : return "RE_SCHEDULE_COUNT";
			case "rescheduledFromDemoSerialId" : return "RESCHEDULED_FROM_DEMO_SERIAL_ID";
			case "entryDateMillis" : return "ENTRY_DATE_MILLIS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "enquirySerialId" : return "ENQUIRY_SERIAL_ID";
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
			case "tutorMapperQuotedTutorRate" : return "TUTOR_MAPPER_QUOTED_TUTOR_RATE";
			case "tutorMapperNegotiatedRateWithTutor" : return "TUTOR_MAPPER_NEGOTIATED_RATE_WITH_TUTOR";
			case "tutorMapperTutorNegotiationRemarks" : return "TUTOR_MAPPER_TUTOR_NEGOTIATION_REMARKS";
			case "customerSerialId" : return "CUSTOMER_SERIAL_ID";
			case "customerName" : return "CUSTOMER_NAME";
			case "customerEmail" : return "CUSTOMER_EMAIL";
			case "customerContactNumber" : return "CUSTOMER_CONTACT_NUMBER";
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
			case "tutorName" : return "TUTOR_NAME";
			case "tutorEmail" : return "TUTOR_EMAIL";
			case "tutorContactNumber" : return "TUTOR_CONTACT_NUMBER";
			case "isSubscriptionCreated" : return "IS_SUBSCRIPTION_CREATED";
			case "subscriptionCreatedMillis" : return "SUBSCRIPTION_CREATED_MILLIS";
			case "isEnquiryClosed" : return "IS_ENQUIRY_CLOSED";
			case "enquiryClosedMillis" : return "ENQUIRY_CLOSED_MILLIS";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public Demo clone() throws CloneNotSupportedException {  
		return (Demo)super.clone();
	}
}
