package com.model;

import java.io.Serializable;
import java.util.List;

import com.model.components.EmployeeContactNumber;
import com.model.components.EmployeeEmail;
import com.utils.ValidationUtils;

public class Employee extends User implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private String employeeSerialId;
	private String emailDomain;
	private List<EmployeeEmail> employeeEmails;
	private List<EmployeeContactNumber> employeeContactNumbers;
	
	public Employee() {}
	
	public String getEmployeeSerialId() {
		return employeeSerialId;
	}

	public void setEmployeeSerialId(String employeeSerialId) {
		this.employeeSerialId = employeeSerialId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	
	public List<EmployeeEmail> getEmployeeEmails() {
		return employeeEmails;
	}

	public void setEmployeeEmails(List<EmployeeEmail> employeeEmails) {
		this.employeeEmails = employeeEmails;
	}

	public List<EmployeeContactNumber> getEmployeeContactNumbers() {
		return employeeContactNumbers;
	}

	public void setEmployeeContactNumbers(List<EmployeeContactNumber> employeeContactNumbers) {
		this.employeeContactNumbers = employeeContactNumbers;
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
			case "employeeSerialId" : return "EMPLOYEE_SERIAL_ID";
			case "emailDomain" : return "EMAIL_DOMAIN";
		}
		return EMPTY_STRING;
	}

	@Override
	public Employee clone() throws CloneNotSupportedException {  
		return (Employee)super.clone();
	}
}
