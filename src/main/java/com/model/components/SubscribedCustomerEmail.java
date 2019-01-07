package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class SubscribedCustomerEmail extends OtherEmail implements Serializable, Cloneable, SubscribedCustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -8353009972804850085L;
	
	private Long subscribedCustomerEmailIdId;
	private Long customerId;
	
	public Long getSubscribedCustomerEmailIdId() {
		return subscribedCustomerEmailIdId;
	}

	public void setSubscribedCustomerEmailIdId(Long subscribedCustomerEmailIdId) {
		this.subscribedCustomerEmailIdId = subscribedCustomerEmailIdId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
			case "subscribedCustomerEmailIdId" : return "SUBSCRIBED_CUSTOMER_EMAIL_ID_ID";
			case "customerId" : return "CUSTOMER_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubscribedCustomerEmail clone() throws CloneNotSupportedException {  
		return (SubscribedCustomerEmail)super.clone();
	}
}
