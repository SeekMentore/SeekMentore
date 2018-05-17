package com.model.components.publicaccess;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

public class PublicApplication implements Serializable {

	private static final long serialVersionUID = -2232244327975645054L;
	
	@Transient
	private Boolean flag;
	
	@Transient
	private String isDataMigrated;
	
	@Transient
	private Date whenMigrated;
	
	@Transient
	private String captchaResponse;

	public Boolean isFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	public String getIsDataMigrated() {
		return isDataMigrated;
	}

	public void setIsDataMigrated(String isDataMigrated) {
		this.isDataMigrated = isDataMigrated;
	}

	public Date getWhenMigrated() {
		return whenMigrated;
	}

	public void setWhenMigrated(Date whenMigrated) {
		this.whenMigrated = whenMigrated;
	}

	public Boolean getFlag() {
		return flag;
	}
}
