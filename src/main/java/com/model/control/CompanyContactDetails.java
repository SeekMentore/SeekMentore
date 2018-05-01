package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class CompanyContactDetails implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private CompanyAdminContactDetails companyAdminContactDetails;

	public CompanyAdminContactDetails getCompanyAdminContactDetails() {
		return companyAdminContactDetails;
	}

	@XmlElement
	public void setCompanyAdminContactDetails(CompanyAdminContactDetails companyAdminContactDetails) {
		this.companyAdminContactDetails = companyAdminContactDetails;
	}
	
}
