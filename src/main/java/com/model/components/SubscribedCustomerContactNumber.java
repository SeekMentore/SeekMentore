package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class SubscribedCustomerContactNumber extends OtherContactNumber implements Serializable, Cloneable, SubscribedCustomerConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = 1168913907463884239L;
	
	private Long subscribedCustomerContactNumberId;
	private Long customerId;
	
	public Long getSubscribedCustomerContactNumberId() {
		return subscribedCustomerContactNumberId;
	}

	public void setSubscribedCustomerContactNumberId(Long subscribedCustomerContactNumberId) {
		this.subscribedCustomerContactNumberId = subscribedCustomerContactNumberId;
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
			case "subscribedCustomerContactNumberId" : return "SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID";
			case "customerId" : return "CUSTOMER_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SubscribedCustomerContactNumber clone() throws CloneNotSupportedException {  
		return (SubscribedCustomerContactNumber)super.clone();
	}
}
