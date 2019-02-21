package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class PackageAssignment extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -171799632331459916L;
	
	private String packageAssignmentSerialId;
	private String subscriptionPackageSerialId;
	private Long createdMillis;
	private Long startDateMillis;
	private Integer totalHours;
	private Integer completedHours;
	private Integer completedMinutes;
	private Long endDateMillis;
	private String isCustomerGrieved;
	private String customerHappinessIndex;
	private String customerRemarks;
	private String isTutorGrieved;
	private String tutorHappinessIndex;
	private String tutorRemarks;
	private String adminRemarks;
	private Long actionDateMillis;
	private String whoActed;
	private String whoActedName;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	
	public PackageAssignment() {}
	
	public String getSubscriptionPackageSerialId() {
		return subscriptionPackageSerialId;
	}

	public void setSubscriptionPackageSerialId(String subscriptionPackageSerialId) {
		this.subscriptionPackageSerialId = subscriptionPackageSerialId;
	}
	
	public Long getCreatedMillis() {
		return createdMillis;
	}

	public void setCreatedMillis(Long createdMillis) {
		this.createdMillis = createdMillis;
	}

	public String getPackageAssignmentSerialId() {
		return packageAssignmentSerialId;
	}

	public void setPackageAssignmentSerialId(String packageAssignmentSerialId) {
		this.packageAssignmentSerialId = packageAssignmentSerialId;
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

	public String getIsCustomerGrieved() {
		return isCustomerGrieved;
	}

	public void setIsCustomerGrieved(String isCustomerGrieved) {
		this.isCustomerGrieved = isCustomerGrieved;
	}

	public String getCustomerHappinessIndex() {
		return customerHappinessIndex;
	}

	public void setCustomerHappinessIndex(String customerHappinessIndex) {
		this.customerHappinessIndex = customerHappinessIndex;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
	}

	public String getIsTutorGrieved() {
		return isTutorGrieved;
	}

	public void setIsTutorGrieved(String isTutorGrieved) {
		this.isTutorGrieved = isTutorGrieved;
	}

	public String getTutorHappinessIndex() {
		return tutorHappinessIndex;
	}

	public void setTutorHappinessIndex(String tutorHappinessIndex) {
		this.tutorHappinessIndex = tutorHappinessIndex;
	}

	public String getTutorRemarks() {
		return tutorRemarks;
	}

	public void setTutorRemarks(String tutorRemarks) {
		this.tutorRemarks = tutorRemarks;
	}

	public String getAdminRemarks() {
		return adminRemarks;
	}

	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
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

	public Integer getCompletedHours() {
		return completedHours;
	}

	public void setCompletedHours(Integer completedHours) {
		this.completedHours = completedHours;
	}

	public Integer getCompletedMinutes() {
		return completedMinutes;
	}

	public void setCompletedMinutes(Integer completedMinutes) {
		this.completedMinutes = completedMinutes;
	}
	
	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
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
			case "packageAssignmentSerialId" : return "PACKAGE_ASSIGNMENT_SERIAL_ID";
			case "subscriptionPackageSerialId" : return "SUBSCRIPTION_PACKAGE_SERIAL_ID";
			case "createdMillis" : return "CREATED_MILLIS";
			case "startDateMillis" : return "START_DATE_MILLIS";
			case "totalHours" : return "TOTAL_HOURS";
			case "completedHours" : return "COMPLETED_HOURS";
			case "completedMinutes" : return "COMPLETED_MINUTES";
			case "endDateMillis" : return "END_DATE_MILLIS";
			case "customerHappinessIndex" : return "CUSTOMER_HAPPINESS_INDEX";
			case "isCustomerGrieved" : return "IS_CUSTOMER_GRIEVED";
			case "customerRemarks" : return "CUSTOMER_REMARKS";
			case "isTutorGrieved" : return "IS_TUTOR_GRIEVED";
			case "tutorHappinessIndex" : return "TUTOR_HAPPINESS_INDEX";
			case "tutorRemarks" : return "TUTOR_REMARKS";
			case "adminRemarks" : return "ADMIN_REMARKS";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public PackageAssignment clone() throws CloneNotSupportedException {  
		return (PackageAssignment)super.clone();
	}
}
