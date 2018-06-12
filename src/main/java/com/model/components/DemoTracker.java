package com.model.components;

import java.io.Serializable;
import java.util.Date;

import com.constants.components.CustomerConstants;
import com.model.ApplicationWorkbookObject;

public class DemoTracker implements Serializable, CustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long demoTrackerId;
	private Long tutorMapperId;
	private Date demoDateAndTime;
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
	private String isDemoSuccess;
	private String needPriceNegotiationWithClient;
	private String clientNegotiationRemarks;
	private String needPriceNegotiationWithTutor;
	private String tutorNegotiationRemarks;
	private String adminRemarks;
	private Integer negotiatedOverrideRateWithClient;
	private Integer negotiatedOverrideRateWithTutor;
	private Date adminActionDate;
	private String customerName;
	private String tutorName;
	private String adminFinalizingRemarks;
	private String reschedulingRemarks;
	
	public DemoTracker() {}

	public Long getDemoTrackerId() {
		return demoTrackerId;
	}

	public void setDemoTrackerId(Long demoTrackerId) {
		this.demoTrackerId = demoTrackerId;
	}

	public Long getTutorMapperId() {
		return tutorMapperId;
	}

	public void setTutorMapperId(Long tutorMapperId) {
		this.tutorMapperId = tutorMapperId;
	}

	public Date getDemoDateAndTime() {
		return demoDateAndTime;
	}

	public void setDemoDateAndTime(Date demoDateAndTime) {
		this.demoDateAndTime = demoDateAndTime;
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

	public Date getAdminActionDate() {
		return adminActionDate;
	}

	public void setAdminActionDate(Date adminActionDate) {
		this.adminActionDate = adminActionDate;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the tutorName
	 */
	public String getTutorName() {
		return tutorName;
	}

	/**
	 * @param tutorName the tutorName to set
	 */
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	/**
	 * @return the adminFinalizingRemarks
	 */
	public String getAdminFinalizingRemarks() {
		return adminFinalizingRemarks;
	}

	/**
	 * @param adminFinalizingRemarks the adminFinalizingRemarks to set
	 */
	public void setAdminFinalizingRemarks(String adminFinalizingRemarks) {
		this.adminFinalizingRemarks = adminFinalizingRemarks;
	}
	
	/**
	 * @return the reschedulingRemarks
	 */
	public String getReschedulingRemarks() {
		return reschedulingRemarks;
	}

	/**
	 * @param reschedulingRemarks the reschedulingRemarks to set
	 */
	public void setReschedulingRemarks(String reschedulingRemarks) {
		this.reschedulingRemarks = reschedulingRemarks;
	}
	
	/**
	 * @return the demoDateAndTimeMillis
	 */
	public Long getDemoDateAndTimeMillis() {
		return demoDateAndTimeMillis;
	}

	/**
	 * @param demoDateAndTimeMillis the demoDateAndTimeMillis to set
	 */
	public void setDemoDateAndTimeMillis(Long demoDateAndTimeMillis) {
		this.demoDateAndTimeMillis = demoDateAndTimeMillis;
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
