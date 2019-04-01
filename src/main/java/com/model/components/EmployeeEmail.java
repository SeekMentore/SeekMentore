package com.model.components;

import java.io.Serializable;

import com.constants.ApplicationConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class EmployeeEmail extends OtherEmail implements Serializable, Cloneable, ApplicationConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = 5224262790290205742L;
	
	private String employeeEmailIdSerialId;
	private String employeeSerialId;
	
	public EmployeeEmail() {
	}
	
	public String getEmployeeEmailIdSerialId() {
		return employeeEmailIdSerialId;
	}

	public void setEmployeeEmailIdSerialId(String employeeEmailIdSerialId) {
		this.employeeEmailIdSerialId = employeeEmailIdSerialId;
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
			case "employeeEmailIdSerialId" : return "EMPLOYEE_EMAIL_ID_SERIAL_ID";
			case "employeeSerialId" : return "EMPLOYEE_SERIAL_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public EmployeeEmail clone() throws CloneNotSupportedException {  
		return (EmployeeEmail)super.clone();
	}
}
