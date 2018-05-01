package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.constants.ApplicationConstants;

public class CompanyAdminContactDetails implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String salutationText;
	private String mobile;
	private String email;
	private String website;
	
	public String getMobile() {
		return mobile;
	}
	
	@XmlElement
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWebsite() {
		return website;
	}
	
	@XmlElement
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getContactDetailsInEmbeddedFormat() {
		return ApplicationConstants.LINE_BREAK + this.salutationText + ApplicationConstants.LINE_BREAK + "Mobile: -" 
					+ this.mobile + ApplicationConstants.LINE_BREAK + "Mail to: -" 
					+ this.email + ApplicationConstants.LINE_BREAK;
	}

	public String getSalutationText() {
		return salutationText;
	}

	@XmlElement
	public void setSalutationText(String salutationText) {
		this.salutationText = salutationText;
	}
	
}
