package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class SubscribedCustomerEmail extends OtherEmail implements Serializable, Cloneable, SubscribedCustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -8353009972804850085L;
	
	private String subscribedCustomerEmailIdSerialId;
	private String customerSerialId;
	
	public SubscribedCustomerEmail() {
	}
	
	public String getSubscribedCustomerEmailIdSerialId() {
		return subscribedCustomerEmailIdSerialId;
	}

	public void setSubscribedCustomerEmailIdSerialId(String subscribedCustomerEmailIdSerialId) {
		this.subscribedCustomerEmailIdSerialId = subscribedCustomerEmailIdSerialId;
	}

	public String getCustomerSerialId() {
		return customerSerialId;
	}

	public void setCustomerSerialId(String customerSerialId) {
		this.customerSerialId = customerSerialId;
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
			case "subscribedCustomerEmailIdSerialId" : return "SUBSCRIBED_CUSTOMER_EMAIL_ID_SERIAL_ID";
			case "customerSerialId" : return "CUSTOMER_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubscribedCustomerEmail clone() throws CloneNotSupportedException {  
		return (SubscribedCustomerEmail)super.clone();
	}
}
