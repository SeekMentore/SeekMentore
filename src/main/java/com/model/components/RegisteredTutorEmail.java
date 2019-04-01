package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class RegisteredTutorEmail extends OtherEmail implements Serializable, Cloneable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -3524058676928237906L;
	
	private String registeredTutorEmailIdSerialId;
	private String tutorSerialId;
	
	public RegisteredTutorEmail() {
	}
	
	public String getRegisteredTutorEmailIdSerialId() {
		return registeredTutorEmailIdSerialId;
	}

	public void setRegisteredTutorEmailIdSerialId(String registeredTutorEmailIdSerialId) {
		this.registeredTutorEmailIdSerialId = registeredTutorEmailIdSerialId;
	}

	public String getTutorSerialId() {
		return tutorSerialId;
	}

	public void setTutorSerialId(String tutorSerialId) {
		this.tutorSerialId = tutorSerialId;
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
			case "registeredTutorEmailIdSerialId" : return "REGISTERED_TUTOR_EMAIL_ID_SERIAL_ID";
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public RegisteredTutorEmail clone() throws CloneNotSupportedException {  
		return (RegisteredTutorEmail)super.clone();
	}
}
