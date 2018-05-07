package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.SubscribedCustomerConstants;

public class SubscribedCustomer implements Serializable, SubscribedCustomerConstants {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private Long customerId;
	private String name;
	private String contactNumber;
	private String emailId;
	
	public SubscribedCustomer() {}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
