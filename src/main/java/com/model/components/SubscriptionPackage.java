package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class SubscriptionPackage extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -171799632331459916L;
	
	private String subscriptionPackageSerialId;
	private Long customerId;
	private String customerName;
	private String customerEmail;
	private String customerContactNumber;
	private Long tutorId;
	private String tutorName;
	private String tutorEmail;
	private String tutorContactNumber;
	private String tutorMapperSerialId;
	private Integer tutorMapperQuotedTutorRate;
	private Integer tutorMapperNegotiatedRateWithTutor;
	private String tutorMapperTutorNegotiationRemarks;
	private Long enquiryId;
	private String enquirySubject;
	private String enquiryGrade;
	private String enquiryLocation;
	private String enquiryEmail;
	private String enquiryContactNumber;
	private String enquiryAddressDetails;
	private String enquiryAdditionalDetails;
	private String enquiryPreferredTeachingType;
	private Integer enquiryQuotedClientRate ;
	private Integer enquiryNegotiatedRateWithClient;
	private String enquiryClientNegotiationRemarks;
	private String demoSerialId;
	private String demoClientRemarks;
	private String demoTutorRemarks;
	private String demoClientSatisfiedFromTutor;
	private String demoTutorSatisfiedWithClient;
	private String demoAdminSatisfiedFromTutor;
	private String demoAdminSatisfiedWithClient;
	private String demoNeedPriceNegotiationWithClient;
	private String demoClientNegotiationRemarks;
	private String demoNeedPriceNegotiationWithTutor;
	private String demoTutorNegotiationRemarks;
	private String demoAdminRemarks;
	private Integer demoNegotiatedOverrideRateWithClient;
	private Integer demoNegotiatedOverrideRateWithTutor;
	private String demoAdminFinalizingRemarks;
	private Long createdMillis;
	private Long startDateMillis;
	private Integer finalizedRateForClient;
	private Integer finalizedRateForTutor;
	private String packageBillingType;
	private Long endDateMillis;
	private String isCustomerGrieved;
	private String customerHappinessIndex;
	private String customerRemarks;
	private String isTutorGrieved;
	private String tutorHappinessIndex;
	private String tutorRemarks;
	private String adminRemarks;
	private String additionalDetailsClient;
	private String additionalDetailsTutor;
	private String activatingRemarks;
	private String terminatingRemarks;
	private Long actionDateMillis;
	private String whoActed;
	private String whoActedName;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	private String contractSerialId;
	private Contract contract;
	
	public SubscriptionPackage() {}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getStartDateMillis() {
		return startDateMillis;
	}

	public void setStartDateMillis(Long startDateMillis) {
		this.startDateMillis = startDateMillis;
	}

	public Long getEndDateMillis() {
		return endDateMillis;
	}

	public void setEndDateMillis(Long endDateMillis) {
		this.endDateMillis = endDateMillis;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getAdminRemarks() {
		return adminRemarks;
	}

	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
	}

	public String getTutorRemarks() {
		return tutorRemarks;
	}

	public void setTutorRemarks(String tutorRemarks) {
		this.tutorRemarks = tutorRemarks;
	}

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}

	public String getIsCustomerGrieved() {
		return isCustomerGrieved;
	}

	public void setIsCustomerGrieved(String isCustomerGrieved) {
		this.isCustomerGrieved = isCustomerGrieved;
	}

	public String getIsTutorGrieved() {
		return isTutorGrieved;
	}

	public void setIsTutorGrieved(String isTutorGrieved) {
		this.isTutorGrieved = isTutorGrieved;
	}

	public String getCustomerHappinessIndex() {
		return customerHappinessIndex;
	}

	public void setCustomerHappinessIndex(String customerHappinessIndex) {
		this.customerHappinessIndex = customerHappinessIndex;
	}

	public String getTutorHappinessIndex() {
		return tutorHappinessIndex;
	}

	public void setTutorHappinessIndex(String tutorHappinessIndex) {
		this.tutorHappinessIndex = tutorHappinessIndex;
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
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	
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

	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
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

	public String getEnquiryPreferredTeachingType() {
		return enquiryPreferredTeachingType;
	}

	public void setEnquiryPreferredTeachingType(String enquiryPreferredTeachingType) {
		this.enquiryPreferredTeachingType = enquiryPreferredTeachingType;
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

	public Long getCreatedMillis() {
		return createdMillis;
	}

	public void setCreatedMillis(Long createdMillis) {
		this.createdMillis = createdMillis;
	}

	public String getDemoClientRemarks() {
		return demoClientRemarks;
	}

	public void setDemoClientRemarks(String demoClientRemarks) {
		this.demoClientRemarks = demoClientRemarks;
	}

	public String getDemoTutorRemarks() {
		return demoTutorRemarks;
	}

	public void setDemoTutorRemarks(String demoTutorRemarks) {
		this.demoTutorRemarks = demoTutorRemarks;
	}

	public String getDemoClientSatisfiedFromTutor() {
		return demoClientSatisfiedFromTutor;
	}

	public void setDemoClientSatisfiedFromTutor(String demoClientSatisfiedFromTutor) {
		this.demoClientSatisfiedFromTutor = demoClientSatisfiedFromTutor;
	}

	public String getDemoTutorSatisfiedWithClient() {
		return demoTutorSatisfiedWithClient;
	}

	public void setDemoTutorSatisfiedWithClient(String demoTutorSatisfiedWithClient) {
		this.demoTutorSatisfiedWithClient = demoTutorSatisfiedWithClient;
	}

	public String getDemoAdminSatisfiedFromTutor() {
		return demoAdminSatisfiedFromTutor;
	}

	public void setDemoAdminSatisfiedFromTutor(String demoAdminSatisfiedFromTutor) {
		this.demoAdminSatisfiedFromTutor = demoAdminSatisfiedFromTutor;
	}

	public String getDemoAdminSatisfiedWithClient() {
		return demoAdminSatisfiedWithClient;
	}

	public void setDemoAdminSatisfiedWithClient(String demoAdminSatisfiedWithClient) {
		this.demoAdminSatisfiedWithClient = demoAdminSatisfiedWithClient;
	}

	public String getDemoNeedPriceNegotiationWithClient() {
		return demoNeedPriceNegotiationWithClient;
	}

	public void setDemoNeedPriceNegotiationWithClient(String demoNeedPriceNegotiationWithClient) {
		this.demoNeedPriceNegotiationWithClient = demoNeedPriceNegotiationWithClient;
	}

	public String getDemoClientNegotiationRemarks() {
		return demoClientNegotiationRemarks;
	}

	public void setDemoClientNegotiationRemarks(String demoClientNegotiationRemarks) {
		this.demoClientNegotiationRemarks = demoClientNegotiationRemarks;
	}

	public String getDemoNeedPriceNegotiationWithTutor() {
		return demoNeedPriceNegotiationWithTutor;
	}

	public void setDemoNeedPriceNegotiationWithTutor(String demoNeedPriceNegotiationWithTutor) {
		this.demoNeedPriceNegotiationWithTutor = demoNeedPriceNegotiationWithTutor;
	}

	public String getDemoTutorNegotiationRemarks() {
		return demoTutorNegotiationRemarks;
	}

	public void setDemoTutorNegotiationRemarks(String demoTutorNegotiationRemarks) {
		this.demoTutorNegotiationRemarks = demoTutorNegotiationRemarks;
	}

	public String getDemoAdminRemarks() {
		return demoAdminRemarks;
	}

	public void setDemoAdminRemarks(String demoAdminRemarks) {
		this.demoAdminRemarks = demoAdminRemarks;
	}

	public Integer getDemoNegotiatedOverrideRateWithClient() {
		return demoNegotiatedOverrideRateWithClient;
	}

	public void setDemoNegotiatedOverrideRateWithClient(Integer demoNegotiatedOverrideRateWithClient) {
		this.demoNegotiatedOverrideRateWithClient = demoNegotiatedOverrideRateWithClient;
	}

	public Integer getDemoNegotiatedOverrideRateWithTutor() {
		return demoNegotiatedOverrideRateWithTutor;
	}

	public void setDemoNegotiatedOverrideRateWithTutor(Integer demoNegotiatedOverrideRateWithTutor) {
		this.demoNegotiatedOverrideRateWithTutor = demoNegotiatedOverrideRateWithTutor;
	}

	public String getDemoAdminFinalizingRemarks() {
		return demoAdminFinalizingRemarks;
	}

	public void setDemoAdminFinalizingRemarks(String demoAdminFinalizingRemarks) {
		this.demoAdminFinalizingRemarks = demoAdminFinalizingRemarks;
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
	
	public String getPackageBillingType() {
		return packageBillingType;
	}

	public void setPackageBillingType(String packageBillingType) {
		this.packageBillingType = packageBillingType;
	}

	public Integer getFinalizedRateForClient() {
		return finalizedRateForClient;
	}

	public void setFinalizedRateForClient(Integer finalizedRateForClient) {
		this.finalizedRateForClient = finalizedRateForClient;
	}
	
	public Integer getFinalizedRateForTutor() {
		return finalizedRateForTutor;
	}

	public void setFinalizedRateForTutor(Integer finalizedRateForTutor) {
		this.finalizedRateForTutor = finalizedRateForTutor;
	}

	public String getAdditionalDetailsClient() {
		return additionalDetailsClient;
	}

	public void setAdditionalDetailsClient(String additionalDetailsClient) {
		this.additionalDetailsClient = additionalDetailsClient;
	}

	public String getActivatingRemarks() {
		return activatingRemarks;
	}

	public void setActivatingRemarks(String activatingRemarks) {
		this.activatingRemarks = activatingRemarks;
	}

	public String getTerminatingRemarks() {
		return terminatingRemarks;
	}

	public void setTerminatingRemarks(String terminatingRemarks) {
		this.terminatingRemarks = terminatingRemarks;
	}
	
	public String getAdditionalDetailsTutor() {
		return additionalDetailsTutor;
	}

	public void setAdditionalDetailsTutor(String additionalDetailsTutor) {
		this.additionalDetailsTutor = additionalDetailsTutor;
	}
	
	public String getSubscriptionPackageSerialId() {
		return subscriptionPackageSerialId;
	}

	public void setSubscriptionPackageSerialId(String subscriptionPackageSerialId) {
		this.subscriptionPackageSerialId = subscriptionPackageSerialId;
	}
	
	public String getContractSerialId() {
		return contractSerialId;
	}

	public void setContractSerialId(String contractSerialId) {
		this.contractSerialId = contractSerialId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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
			case "subscriptionPackageSerialId" : return "SUBSCRIPTION_PACKAGE_SERIAL_ID";
			case "customerId" : return "CUSTOMER_ID";
			case "customerName" : return "CUSTOMER_NAME";
			case "customerEmail" : return "CUSTOMER_EMAIL";
			case "customerContactNumber" : return "CUSTOMER_CONTACT_NUMBER";
			case "tutorId" : return "TUTOR_ID";
			case "tutorName" : return "TUTOR_NAME";
			case "tutorEmail" : return "TUTOR_EMAIL";
			case "tutorContactNumber" : return "TUTOR_CONTACT_NUMBER";
			case "enquiryId" : return "ENQUIRY_ID";
			case "enquirySubject" : return "ENQUIRY_SUBJECT";
			case "enquiryGrade" : return "ENQUIRY_GRADE";
			case "enquiryLocation" : return "ENQUIRY_LOCATION";
			case "enquiryEmail" : return "ENQUIRY_EMAIL";
			case "enquiryContactNumber" : return "ENQUIRY_CONTACT_NUMBER";
			case "enquiryAddressDetails" : return "ENQUIRY_ADDRESS_DETAILS";
			case "enquiryAdditionalDetails" : return "ENQUIRY_ADDITIONAL_DETAILS";
			case "enquiryPreferredTeachingType" : return "ENQUIRY_PREFERRED_TEACHING_TYPE";
			case "enquiryQuotedClientRate" : return "ENQUIRY_QUOTED_CLIENT_RATE";
			case "enquiryNegotiatedRateWithClient" : return "ENQUIRY_NEGOTIATED_RATE_WITH_CLIENT";
			case "enquiryClientNegotiationRemarks" : return "ENQUIRY_CLIENT_NEGOTIATION_REMARKS";
			case "tutorMapperSerialId" : return "TUTOR_MAPPER_SERIAL_ID";
			case "tutorMapperQuotedTutorRate" : return "TUTOR_MAPPER_QUOTED_TUTOR_RATE";
			case "tutorMapperNegotiatedRateWithTutor" : return "TUTOR_MAPPER_NEGOTIATED_RATE_WITH_TUTOR";
			case "tutorMapperTutorNegotiationRemarks" : return "TUTOR_MAPPER_TUTOR_NEGOTIATION_REMARKS";
			case "demoSerialId" : return "DEMO_SERIAL_ID";
			case "demoClientRemarks" : return "DEMO_CLIENT_REMARKS";
			case "demoTutorRemarks" : return "DEMO_TUTOR_REMARKS";
			case "demoClientSatisfiedFromTutor" : return "DEMO_CLIENT_SATISFIED_FROM_TUTOR";
			case "demoTutorSatisfiedWithClient" : return "DEMO_TUTOR_SATISFIED_WITH_CLIENT";
			case "demoAdminSatisfiedFromTutor" : return "DEMO_ADMIN_SATISFIED_FROM_TUTOR";
			case "demoAdminSatisfiedWithClient" : return "DEMO_ADMIN_SATISFIED_WITH_CLIENT";
			case "demoNeedPriceNegotiationWithClient" : return "DEMO_NEED_PRICE_NEGOTIATION_WITH_CLIENT";
			case "demoClientNegotiationRemarks" : return "DEMO_CLIENT_NEGOTIATION_REMARKS";
			case "demoNeedPriceNegotiationWithTutor" : return "DEMO_NEED_PRICE_NEGOTIATION_WITH_TUTOR";
			case "demoTutorNegotiationRemarks" : return "DEMO_TUTOR_NEGOTIATION_REMARKS";
			case "demoAdminRemarks" : return "DEMO_ADMIN_REMARKS";
			case "demoNegotiatedOverrideRateWithClient" : return "DEMO_NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT";
			case "demoNegotiatedOverrideRateWithTutor" : return "DEMO_NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR";
			case "demoAdminFinalizingRemarks" : return "DEMO_ADMIN_FINALIZING_REMARKS";
			case "createdMillis" : return "CREATED_MILLIS";
			case "startDateMillis" : return "START_DATE_MILLIS";
			case "packageBillingType" : return "PACKAGE_BILLING_TYPE";
			case "finalizedRateForClient" : return "FINALIZED_RATE_CLIENT";
			case "finalizedRateForTutor" : return "FINALIZED_RATE_TUTOR";
			case "endDateMillis" : return "END_DATE_MILLIS";
			case "customerHappinessIndex" : return "CUSTOMER_HAPPINESS_INDEX";
			case "isCustomerGrieved" : return "IS_CUSTOMER_GRIEVED";
			case "customerRemarks" : return "CUSTOMER_REMARKS";
			case "isTutorGrieved" : return "IS_TUTOR_GRIEVED";
			case "tutorHappinessIndex" : return "TUTOR_HAPPINESS_INDEX";
			case "tutorRemarks" : return "TUTOR_REMARKS";
			case "adminRemarks" : return "ADMIN_REMARKS";
			case "additionalDetailsClient" : return "ADDITIONAL_DETAILS_CLIENT";
			case "additionalDetailsTutor" : return "ADDITIONAL_DETAILS_TUTOR";
			case "activatingRemarks" : return "ACTIVATING_REMARKS";
			case "terminatingRemarks" : return "TERMINATING_REMARKS";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
			case "contractSerialId" : return "CONTRACT_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubscriptionPackage clone() throws CloneNotSupportedException {  
		return (SubscriptionPackage)super.clone();
	}
}
