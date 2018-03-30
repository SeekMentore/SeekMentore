package com.model.components.publicaccess;

import java.io.Serializable;

import javax.persistence.Transient;

public class PublicApplication implements Serializable {

	private static final long serialVersionUID = -2232244327975645054L;
	
	@Transient
	private boolean flag;
	
	@Transient
	private String captchaResponse;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}
}
