package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="controlConfiguration")
public class ControlConfiguration implements Serializable {

	private static final long serialVersionUID = 3465118502989868696L;
	
	private String appEncyptionAlgorithm;
	private String appEncodingStandard;
	private Boolean applyFilterToApplication;
	private MailConfiguration mailConfiguration;

	public String getAppEncyptionAlgorithm() {
		return appEncyptionAlgorithm;
	}

	@XmlElement
	public void setAppEncyptionAlgorithm(String appEncyptionAlgorithm) {
		this.appEncyptionAlgorithm = appEncyptionAlgorithm;
	}

	public String getAppEncodingStandard() {
		return appEncodingStandard;
	}

	@XmlElement
	public void setAppEncodingStandard(String appEncodingStandard) {
		this.appEncodingStandard = appEncodingStandard;
	}
	
	public Boolean getApplyFilterToApplication() {
		return applyFilterToApplication;
	}

	@XmlElement
	public void setApplyFilterToApplication(Boolean applyFilterToApplication) {
		this.applyFilterToApplication = applyFilterToApplication;
	}

	public MailConfiguration getMailConfiguration() {
		return mailConfiguration;
	}

	@XmlElement
	public void setMailConfiguration(MailConfiguration mailConfiguration) {
		this.mailConfiguration = mailConfiguration;
	}
}
