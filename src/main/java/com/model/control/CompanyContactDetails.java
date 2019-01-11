package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class CompanyContactDetails implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private SupportContactDetails supportContactDetails;

	public SupportContactDetails getSupportContactDetails() {
		return supportContactDetails;
	}

	@XmlElement
	public void setSupportContactDetails(SupportContactDetails supportContactDetails) {
		this.supportContactDetails = supportContactDetails;
	}
	
}
