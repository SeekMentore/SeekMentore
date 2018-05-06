package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class ImportantCompanyMailIdsAndLists implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String officeAdminId;
	private String systemSupportMailList;

	private String systemReplyToAddress;
	private String prm;
	private String managingPartner;
	private String techlead;
	private String managingDirector;
	private String founder;
	
	private String querySupportMailList;
	private String customerRegistrationSupportMailList;
	private String customerRetentionSupportMailList;
	private String tutorRegistrationSupportMailList;
	private String tutorRetentionSupportMailList;
	
	private String errorHandlingMailList;
	private String salesDeptMailList;
	private String financeDeptMailList;
	private String hrDeptMailList;
	private String accountsDeptMailList;
	
	private String testingGroupMailList;
	
	
	public String getPrm() {
		return prm;
	}
	
	@XmlElement
	public void setPrm(String prm) {
		this.prm = prm;
	}
	
	public String getManagingPartner() {
		return managingPartner;
	}
	
	@XmlElement
	public void setManagingPartner(String managingPartner) {
		this.managingPartner = managingPartner;
	}
	
	public String getTechlead() {
		return techlead;
	}
	
	@XmlElement
	public void setTechlead(String techlead) {
		this.techlead = techlead;
	}
	
	public String getManagingDirector() {
		return managingDirector;
	}
	
	@XmlElement
	public void setManagingDirector(String managingDirector) {
		this.managingDirector = managingDirector;
	}
	
	public String getFounder() {
		return founder;
	}
	
	@XmlElement
	public void setFounder(String founder) {
		this.founder = founder;
	}
	
	public String getOfficeAdminId() {
		return officeAdminId;
	}
	
	@XmlElement
	public void setOfficeAdminId(String officeAdminId) {
		this.officeAdminId = officeAdminId;
	}
	
	public String getErrorHandlingMailList() {
		return errorHandlingMailList;
	}
	
	@XmlElement
	public void setErrorHandlingMailList(String errorHandlingMailList) {
		this.errorHandlingMailList = errorHandlingMailList;
	}
	
	public String getSalesDeptMailList() {
		return salesDeptMailList;
	}

	@XmlElement
	public void setSalesDeptMailList(String salesDeptMailList) {
		this.salesDeptMailList = salesDeptMailList;
	}

	public String getFinanceDeptMailList() {
		return financeDeptMailList;
	}

	@XmlElement
	public void setFinanceDeptMailList(String financeDeptMailList) {
		this.financeDeptMailList = financeDeptMailList;
	}

	public String getHrDeptMailList() {
		return hrDeptMailList;
	}

	@XmlElement
	public void setHrDeptMailList(String hrDeptMailList) {
		this.hrDeptMailList = hrDeptMailList;
	}

	public String getSystemSupportMailList() {
		return systemSupportMailList;
	}

	@XmlElement
	public void setSystemSupportMailList(String systemSupportMailList) {
		this.systemSupportMailList = systemSupportMailList;
	}

	public String getCustomerRegistrationSupportMailList() {
		return customerRegistrationSupportMailList;
	}

	@XmlElement
	public void setCustomerRegistrationSupportMailList(String customerRegistrationSupportMailList) {
		this.customerRegistrationSupportMailList = customerRegistrationSupportMailList;
	}

	public String getCustomerRetentionSupportMailList() {
		return customerRetentionSupportMailList;
	}

	@XmlElement
	public void setCustomerRetentionSupportMailList(String customerRetentionSupportMailList) {
		this.customerRetentionSupportMailList = customerRetentionSupportMailList;
	}

	public String getTutorRegistrationSupportMailList() {
		return tutorRegistrationSupportMailList;
	}

	@XmlElement
	public void setTutorRegistrationSupportMailList(String tutorRegistrationSupportMailList) {
		this.tutorRegistrationSupportMailList = tutorRegistrationSupportMailList;
	}

	public String getTutorRetentionSupportMailList() {
		return tutorRetentionSupportMailList;
	}

	@XmlElement
	public void setTutorRetentionSupportMailList(String tutorRetentionSupportMailList) {
		this.tutorRetentionSupportMailList = tutorRetentionSupportMailList;
	}

	public String getAccountsDeptMailList() {
		return accountsDeptMailList;
	}

	@XmlElement
	public void setAccountsDeptMailList(String accountsDeptMailList) {
		this.accountsDeptMailList = accountsDeptMailList;
	}

	public String getSystemReplyToAddress() {
		return systemReplyToAddress;
	}

	@XmlElement
	public void setSystemReplyToAddress(String systemReplyToAddress) {
		this.systemReplyToAddress = systemReplyToAddress;
	}

	public String getQuerySupportMailList() {
		return querySupportMailList;
	}

	@XmlElement
	public void setQuerySupportMailList(String querySupportMailList) {
		this.querySupportMailList = querySupportMailList;
	}

	public String getTestingGroupMailList() {
		return testingGroupMailList;
	}

	@XmlElement
	public void setTestingGroupMailList(String testingGroupMailList) {
		this.testingGroupMailList = testingGroupMailList;
	}
	
}
