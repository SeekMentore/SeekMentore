package com.model.components;

import java.io.Serializable;

import com.constants.ApplicationConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class EmployeeContactNumber extends OtherContactNumber implements Serializable, Cloneable, ApplicationConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6545113536464760606L;
	
	private String employeeContactNumberSerialId;
	private String employeeSerialId;
	
	public EmployeeContactNumber() {
	}
	
	public String getEmployeeContactNumberSerialId() {
		return employeeContactNumberSerialId;
	}

	public void setEmployeeContactNumberSerialId(String employeeContactNumberSerialId) {
		this.employeeContactNumberSerialId = employeeContactNumberSerialId;
	}

	public String getEmployeeSerialId() {
		return employeeSerialId;
	}

	public void setEmployeeSerialId(String employeeSerialId) {
		this.employeeSerialId = employeeSerialId;
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
			case "employeeContactNumberSerialId" : return "EMPLOYEE_CONTACT_NUMBER_SERIAL_ID";
			case "employeeSerialId" : return "EMPLOYEE_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public EmployeeContactNumber clone() throws CloneNotSupportedException {  
		return (EmployeeContactNumber)super.clone();
	}
}
