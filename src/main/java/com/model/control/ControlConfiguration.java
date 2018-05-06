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
	private String remoteConnectionAcceptedLanguage;
	private MailConfiguration mailConfiguration;
	private CaptchaParams captchaParams;
	private CompanyContactDetails companyContactDetails;
	private AWSParams awsParams;

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

	public CaptchaParams getCaptchaParams() {
		return captchaParams;
	}

	@XmlElement
	public void setCaptchaParams(CaptchaParams captchaParams) {
		this.captchaParams = captchaParams;
	}

	public String getRemoteConnectionAcceptedLanguage() {
		return remoteConnectionAcceptedLanguage;
	}

	@XmlElement
	public void setRemoteConnectionAcceptedLanguage(String remoteConnectionAcceptedLanguage) {
		this.remoteConnectionAcceptedLanguage = remoteConnectionAcceptedLanguage;
	}

	public CompanyContactDetails getCompanyContactDetails() {
		return companyContactDetails;
	}

	@XmlElement
	public void setCompanyContactDetails(CompanyContactDetails companyContactDetails) {
		this.companyContactDetails = companyContactDetails;
	}

	public AWSParams getAwsParams() {
		return awsParams;
	}

	public void setAwsParams(AWSParams awsParams) {
		this.awsParams = awsParams;
	}
}
