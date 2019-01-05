package com.model.components;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class RegisteredTutorEmail extends OtherEmail implements Cloneable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private Long registeredTutorEmailIdId;
	private Long tutorId;
	
	public Long getRegisteredTutorEmailIdId() {
		return registeredTutorEmailIdId;
	}

	public void setRegisteredTutorEmailIdId(Long registeredTutorEmailIdId) {
		this.registeredTutorEmailIdId = registeredTutorEmailIdId;
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
			case "registeredTutorEmailIdId" : return "REGISTERED_TUTOR_EMAIL_ID_ID";
			case "tutorId" : return "TUTOR_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public RegisteredTutorEmail clone() throws CloneNotSupportedException {  
		return (RegisteredTutorEmail)super.clone();
	}
}
