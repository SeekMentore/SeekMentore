package com.model.components;

import java.io.Serializable;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class RegisteredTutorContactNumber extends OtherContactNumber implements Serializable, Cloneable, RegisteredTutorConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -8122347075474144999L;
	
	private String registeredTutorContactNumberSerialId;
	private String tutorSerialId;
	
	public RegisteredTutorContactNumber() {
	}
	
	public String getRegisteredTutorContactNumberSerialId() {
		return registeredTutorContactNumberSerialId;
	}

	public void setRegisteredTutorContactNumberSerialId(String registeredTutorContactNumberSerialId) {
		this.registeredTutorContactNumberSerialId = registeredTutorContactNumberSerialId;
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
			case "registeredTutorContactNumberSerialId" : return "REGISTERED_TUTOR_CONTACT_NUMBER_SERIAL_ID";
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public RegisteredTutorContactNumber clone() throws CloneNotSupportedException {  
		return (RegisteredTutorContactNumber)super.clone();
	}
}
