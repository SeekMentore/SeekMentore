package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class CaptchaParams implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private Boolean switchOffCaptcha;
	private String encryptedApiSecret;
	private String apiVerifyURL;
	
	public String getApiVerifyURL() {
		return apiVerifyURL;
	}
	
	@XmlElement
	public void setApiVerifyURL(String apiVerifyURL) {
		this.apiVerifyURL = apiVerifyURL;
	}
	
	public String getEncryptedApiSecret() {
		return encryptedApiSecret;
	}

	public void setEncryptedApiSecret(String encryptedApiSecret) {
		this.encryptedApiSecret = encryptedApiSecret;
	}

	public Boolean getSwitchOffCaptcha() {
		return switchOffCaptcha;
	}

	@XmlElement
	public void setSwitchOffCaptcha(Boolean switchOffCaptcha) {
		this.switchOffCaptcha = switchOffCaptcha;
	}
}
