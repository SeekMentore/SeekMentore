package com.model.components;

import java.io.Serializable;
import java.util.Date;

public class Complaint implements Serializable {
	
	private static final long serialVersionUID = -7058576359707327688L;
	
	private Long complaintId;
	private String name;
	private Date complaintFiledDate;
	private Long complaintFiledDateMillis;
	private String complaintStatus;
	private String userId;
	private String complaintDetails;
	private String complaintUser;
	private String complaintResponse;
	private String isContacted;
	private String whoContacted;
	private Date contactedDate;
	private Long contactedDateMillis;
	private String notResolved;
	private String notResolvedReason;
	private String whoNotResolved;
	private Date recordLastUpdated;
	private Long recordLastUpdatedMillis;
	
	public Complaint(Long complaintId) {
		this.complaintId = complaintId;
		this.name = "Shantanu Mukherjee";
		this.complaintFiledDate = new Date();
		this.complaintFiledDateMillis = new Date().getTime();
		this.complaintStatus = "FRESH";
		this.userId = "abc@hg.com";
		this.complaintUser = complaintId%3==1?"01":complaintId%3==2?"02":"03";
		this.complaintDetails = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.isContacted = "N";
		this.whoContacted = "abc";
		this.contactedDate = new Date();
		this.contactedDateMillis = new Date().getTime();
		this.complaintResponse = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.notResolved = "Y";
		this.notResolvedReason = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test "
				+ "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test";
		this.whoNotResolved = "abc";
		this.recordLastUpdated = new Date();
		this.recordLastUpdatedMillis = new Date().getTime();
	}

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

	public Date getComplaintFiledDate() {
		return complaintFiledDate;
	}

	public void setComplaintFiledDate(Date complaintFiledDate) {
		this.complaintFiledDate = complaintFiledDate;
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

	public Date getContactedDate() {
		return contactedDate;
	}

	public void setContactedDate(Date contactedDate) {
		this.contactedDate = contactedDate;
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

	public Date getRecordLastUpdated() {
		return recordLastUpdated;
	}

	public void setRecordLastUpdated(Date recordLastUpdated) {
		this.recordLastUpdated = recordLastUpdated;
	}

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}
}
