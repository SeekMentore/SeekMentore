package com.model.components;

import com.constants.ApplicationConstants;
import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class EmployeeContactNumber extends OtherContactNumber implements Cloneable, ApplicationConstants, ApplicationWorkbookObject {
	
	private Long employeeContactNumberId;
	private Long employeeId;
	
	public Long getEmployeeContactNumberId() {
		return employeeContactNumberId;
	}

	public void setEmployeeContactNumberId(Long employeeContactNumberId) {
		this.employeeContactNumberId = employeeContactNumberId;
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
			case "employeeContactNumberId" : return "EMPLOYEE_CONTACT_NUMBER_ID";
			case "employeeId" : return "EMPLOYEE_ID";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public EmployeeContactNumber clone() throws CloneNotSupportedException {  
		return (EmployeeContactNumber)super.clone();
	}
}
