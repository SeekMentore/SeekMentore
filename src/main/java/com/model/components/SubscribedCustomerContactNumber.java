package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class SubscribedCustomerContactNumber extends OtherContactNumber implements Serializable, Cloneable, SubscribedCustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = 1168913907463884239L;
	
	private String subscribedCustomerContactNumberSerialId;
	private String customerSerialId;
	
	public String getSubscribedCustomerContactNumberSerialId() {
		return subscribedCustomerContactNumberSerialId;
	}

	public void setSubscribedCustomerContactNumberSerialId(String subscribedCustomerContactNumberSerialId) {
		this.subscribedCustomerContactNumberSerialId = subscribedCustomerContactNumberSerialId;
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
			case "subscribedCustomerContactNumberSerialId" : return "SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_SERIAL_ID";
			case "customerSerialId" : return "CUSTOMER_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubscribedCustomerContactNumber clone() throws CloneNotSupportedException {  
		return (SubscribedCustomerContactNumber)super.clone();
	}
}
