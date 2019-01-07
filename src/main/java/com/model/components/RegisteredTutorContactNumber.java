package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class RegisteredTutorContactNumber extends OtherContactNumber implements Serializable, Cloneable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -8122347075474144999L;
	
	private Long registeredTutorContactNumberId;
	private Long tutorId;
	
	public Long getRegisteredTutorContactNumberId() {
		return registeredTutorContactNumberId;
	}

	public void setRegisteredTutorContactNumberId(Long registeredTutorContactNumberId) {
		this.registeredTutorContactNumberId = registeredTutorContactNumberId;
	}

	public Long getTutorId() {
		return tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
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
			case "registeredTutorContactNumberId" : return "REGISTERED_TUTOR_CONTACT_NUMBER_ID";
			case "tutorId" : return "TUTOR_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public RegisteredTutorContactNumber clone() throws CloneNotSupportedException {  
		return (RegisteredTutorContactNumber)super.clone();
	}
}
