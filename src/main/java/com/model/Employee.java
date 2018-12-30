package com.model;

import java.io.Serializable;

import com.utils.ValidationUtils;

public class Employee extends User implements Serializable, Cloneable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private Long employeeId;
	private String emailDomain;
	
	public Employee() {}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "employeeId" : return "EMPLOYEE_ID";
			case "emailDomain" : return "EMAIL_DOMAIN";
		}
		return EMPTY_STRING;
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {  
		return (Employee)super.clone();
	}
}
