package com.model.components;

import com.constants.ApplicationConstants;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public abstract class OtherContactNumber extends GridComponentObject implements ApplicationConstants {
	
	private String contactNumber;
	private String isPrimary;
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "contactNumber" : return "CONTACT_NUMBER";
			case "isPrimary" : return "IS_PRIMARY";
		}
		return EMPTY_STRING;
	}
}
