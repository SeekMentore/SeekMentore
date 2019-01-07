package com.model.components;

import java.io.Serializable;

import com.constants.ApplicationConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class EmployeeEmail extends OtherEmail implements Serializable, Cloneable, ApplicationConstants, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = 5224262790290205742L;
	
	private Long employeeEmailIdId;
	private Long employeeId;
	
	public Long getEmployeeEmailIdId() {
		return employeeEmailIdId;
	}

	public void setEmployeeEmailIdId(Long employeeEmailIdId) {
		this.employeeEmailIdId = employeeEmailIdId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
			case "employeeEmailIdId" : return "EMPLOYEE_EMAIL_ID_ID";
			case "employeeId" : return "EMPLOYEE_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public EmployeeEmail clone() throws CloneNotSupportedException {  
		return (EmployeeEmail)super.clone();
	}
}
