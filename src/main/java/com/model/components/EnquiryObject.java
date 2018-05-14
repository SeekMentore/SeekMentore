package com.model.components;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.CustomerConstants;
import com.model.ApplicationWorkbookObject;

public class EnquiryObject implements Serializable, CustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long enquiryId;
	private Long customerId;
	private String subject;
	private String grade;
	private Integer quotedClientRate ;
	private Integer negotiatedRateWithClient;
	private String clientNegotiationRemarks;
	private String isMapped;
	private Date lastActionDate;
	private String matchStatus;
	private Long tutorId;
	private String adminRemarks;
	private String locationDetails;
	private String addressDetails;
	private String additionalDetails;
	private String whoActed;
	
	public EnquiryObject() {}

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

	public Date getLastActionDate() {
		return lastActionDate;
	}

	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
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

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		// TODO Auto-generated method stub
		return null;
	}

}