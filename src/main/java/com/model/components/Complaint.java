package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class Complaint extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -7058576359707327688L;
	
	private Long complaintId;
	private String name;
	private Long complaintFiledDateMillis;
	private String complaintStatus;
	private String userId;
	private String complaintDetails;
	private String complaintUser;
	private String complaintResponse;
	private String isContacted;
	private String whoContacted;
	private Long contactedDateMillis;
	private String resolved;
	private String notResolved;
	private String notResolvedReason;
	private String whoNotResolved;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String whoContactedName;
	private String whoNotResolvedName;
	private String updatedByName;
	
	public Complaint() {}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getComplaintFiledDateMillis() {
		return complaintFiledDateMillis;
	}

	public void setComplaintFiledDateMillis(Long complaintFiledDateMillis) {
		this.complaintFiledDateMillis = complaintFiledDateMillis;
	}

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	public String getComplaintUser() {
		return complaintUser;
	}

	public void setComplaintUser(String complaintUser) {
		this.complaintUser = complaintUser;
	}

	public String getComplaintResponse() {
		return complaintResponse;
	}

	public void setComplaintResponse(String complaintResponse) {
		this.complaintResponse = complaintResponse;
	}

	public String getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(String isContacted) {
		this.isContacted = isContacted;
	}

	public String getWhoContacted() {
		return whoContacted;
	}

	public void setWhoContacted(String whoContacted) {
		this.whoContacted = whoContacted;
	}

	public Long getContactedDateMillis() {
		return contactedDateMillis;
	}

	public void setContactedDateMillis(Long contactedDateMillis) {
		this.contactedDateMillis = contactedDateMillis;
	}

	public String getNotResolved() {
		return notResolved;
	}

	public void setNotResolved(String notResolved) {
		this.notResolved = notResolved;
	}

	public String getNotResolvedReason() {
		return notResolvedReason;
	}

	public void setNotResolvedReason(String notResolvedReason) {
		this.notResolvedReason = notResolvedReason;
	}

	public String getWhoNotResolved() {
		return whoNotResolved;
	}

	public void setWhoNotResolved(String whoNotResolved) {
		this.whoNotResolved = whoNotResolved;
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
	
	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getWhoContactedName() {
		return whoContactedName;
	}

	public void setWhoContactedName(String whoContactedName) {
		this.whoContactedName = whoContactedName;
	}

	public String getWhoNotResolvedName() {
		return whoNotResolvedName;
	}

	public void setWhoNotResolvedName(String whoNotResolvedName) {
		this.whoNotResolvedName = whoNotResolvedName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
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
			case "complaintId" : return "COMPLAINT_ID";
			case "name" : return "NAME";
			case "complaintFiledDateMillis" : return "COMPLAINT_FILED_DATE_MILLIS";
			case "complaintStatus" : return "COMPLAINT_STATUS";
			case "userId" : return "USER_ID";
			case "complaintDetails" : return "COMPLAINT_DETAILS";
			case "complaintUser" : return "COMPLAINT_USER";
			case "complaintResponse" : return "COMPLAINT_RESPONSE";
			case "isContacted" : return "IS_CONTACTED";
			case "whoContacted" : return "WHO_CONTACTED";
			case "contactedDateMillis" : return "CONTACTED_DATE_MILLIS";
			case "resolved" : return "RESOLVED";
			case "notResolved" : return "NOT_RESOLVED";
			case "notResolvedReason" : return "NOT_RESOLVED_REASON";
			case "whoNotResolved" : return "WHO_NOT_RESOLVED";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "whoContactedName" : return "WHO_CONTACTED_NAME";
			case "whoNotResolvedName" : return "WHO_NOT_RESOLVED_NAME";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public Complaint clone() throws CloneNotSupportedException {  
		return (Complaint)super.clone();
	}
}
