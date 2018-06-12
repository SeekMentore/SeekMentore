package com.model.components;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.CustomerConstants;
import com.model.ApplicationWorkbookObject;

public class TutorMapper implements Serializable, CustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long tutorMapperId;
	private Long enquiryId;
	private Long tutorId;
	private String tutorName;
	private String tutorEmail;
	private String tutorContactNumber;
	private Integer quotedTutorRate;
	private Integer negotiatedRateWithTutor ;
	private String tutorNegotiationRemarks;
	private String isTutorContacted;
	private Date tutorContactedDate;
	private String isTutorAgreed;
	private String isTutorRejectionValid;
	private String adminTutorRejectionValidityResponse;
	private String tutorResponse;
	private String adminRemarksForTutor;
	private String isClientContacted;
	private Date clientContactedDate;
	private String isClientAgreed;
	private String clientResponse;
	private String isClientRejectionValid;
	private String adminClientRejectionValidityResponse;
	private String adminRemarksForClient;
	private Date adminActionDate;
	private String adminActionRemarks;
	private String whoActed;
	private String isDemoScheduled;
	private String mappingStatus;
	
	public TutorMapper() {}

	public Long getTutorMapperId() {
		return tutorMapperId;
	}

	public void setTutorMapperId(Long tutorMapperId) {
		this.tutorMapperId = tutorMapperId;
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

	public Date getTutorContactedDate() {
		return tutorContactedDate;
	}

	public void setTutorContactedDate(Date tutorContactedDate) {
		this.tutorContactedDate = tutorContactedDate;
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

	public Date getClientContactedDate() {
		return clientContactedDate;
	}

	public void setClientContactedDate(Date clientContactedDate) {
		this.clientContactedDate = clientContactedDate;
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

	public Date getAdminActionDate() {
		return adminActionDate;
	}

	public void setAdminActionDate(Date adminActionDate) {
		this.adminActionDate = adminActionDate;
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
