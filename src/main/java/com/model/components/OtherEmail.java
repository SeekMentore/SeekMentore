package com.model.components;

import com.constants.ApplicationConstants;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public abstract class OtherEmail extends GridComponentObject implements ApplicationConstants {
	
	private String emailId;
	private String isPrimary;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
			case "emailId" : return "EMAIL_ID";
			case "isPrimary" : return "IS_PRIMARY";
		}
		return EMPTY_STRING;
	}
}
