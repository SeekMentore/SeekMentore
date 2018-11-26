package com.model.components;

import java.io.Serializable;
import java.util.Date;

public class SubscriptionPackage implements Serializable {

	private static final long serialVersionUID = -171799632331459916L;
	
	private Long subscriptionPackageId;
	private String customerName;
	private Integer totalHours;
	private Date startDate;
	private Long startDateMillis;
	private Integer completedHours;
	private Date endDate;
	private Long endDateMillis;
	
	public SubscriptionPackage(Long subscriptionPackageId) {
		this.subscriptionPackageId = subscriptionPackageId;
		customerName = "Parag Agarwal";
		totalHours = 25;
		startDate = new Date();
		startDateMillis = startDate.getTime();
		completedHours = 23;
		endDate = new Date();
		endDateMillis = endDate.getTime();
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getEndDateMillis() {
		return endDateMillis;
	}

	public void setEndDateMillis(Long endDateMillis) {
		this.endDateMillis = endDateMillis;
	}
	
}
