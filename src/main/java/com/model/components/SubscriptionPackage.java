package com.model.components;

import java.io.Serializable;

import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class SubscriptionPackage extends GridComponentObject implements Serializable {

	private static final long serialVersionUID = -171799632331459916L;
	
	private Long subscriptionPackageId;
	private Long customerId;
	private String customerName;
	private Long tutorId;
	private String tutorName;
	private Integer totalHours;
	private Long startDateMillis;
	private Integer completedHours;
	private Integer completedMinutes;
	private Long endDateMillis;
	private String whoActed;
	private String adminRemarks;
	private String customerRemarks;
	private String tutorRemarks;
	private Long actionDateMillis;
	private String isCustomerGrieved;
	private String isTutorGrieved;
	private String customerHappinessIndex;
	private String tutorHappinessIndex;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	
	public SubscriptionPackage() {}

	public Long getSubscriptionPackageId() {
		return subscriptionPackageId;
	}

	public void setSubscriptionPackageId(Long subscriptionPackageId) {
		this.subscriptionPackageId = subscriptionPackageId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public Long getStartDateMillis() {
		return startDateMillis;
	}

	public void setStartDateMillis(Long startDateMillis) {
		this.startDateMillis = startDateMillis;
	}

	public Integer getCompletedHours() {
		return completedHours;
	}

	public void setCompletedHours(Integer completedHours) {
		this.completedHours = completedHours;
	}

	public Long getEndDateMillis() {
		return endDateMillis;
	}

	public void setEndDateMillis(Long endDateMillis) {
		this.endDateMillis = endDateMillis;
	}

	public Integer getCompletedMinutes() {
		return completedMinutes;
	}

	public void setCompletedMinutes(Integer completedMinutes) {
		this.completedMinutes = completedMinutes;
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
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "subscriptionPackageId" : return "SUBSCRIPTION_PACKAGE_ID";
			case "customerId" : return "CUSTOMER_ID";
			case "customerName" : return "CUSTOMER_NAME";
			case "tutorId" : return "TUTOR_ID";
			case "tutorName" : return "TUTOR_NAME";
			case "totalHours" : return "TOTAL_HOURS";
			case "startDateMillis" : return "START_DATE_MILLIS";
			case "completedHours" : return "COMPLETED_HOURS";
			case "completedMinutes" : return "COMPLETED_MINUTES";
			case "endDateMillis" : return "END_DATE_MILLIS";
			case "whoActed" : return "WHO_ACTED";
			case "adminRemarks" : return "ADMIN_REMARKS";
			case "customerRemarks" : return "CUSTOMER_REMARKS";
			case "tutorRemarks" : return "TUTOR_REMARKS";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "isCustomerGrieved" : return "IS_CUSTOMER_GRIEVED";
			case "isTutorGrieved" : return "IS_TUTOR_GRIEVED";
			case "customerHappinessIndex" : return "CUSTOMER_HAPPINESS_INDEX";
			case "tutorHappinessIndex" : return "TUTOR_HAPPINESS_INDEX";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
		}
		return EMPTY_STRING;
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
}
