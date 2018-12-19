package com.model;

import java.io.Serializable;

public class UserAccessOptions implements Serializable {
	
	private static final long serialVersionUID = -7085840019992938165L;
	
	private Boolean impersonationaccess = false;
	private Boolean emailformaccess = false;
	
	public UserAccessOptions() {}
	
	public UserAccessOptions getACopy() {
		final UserAccessOptions newInstance = new UserAccessOptions();
		newInstance.impersonationaccess = impersonationaccess;
		newInstance.emailformaccess = emailformaccess;
		return newInstance;
	}
	
	public Boolean getImpersonationaccess() {
		return impersonationaccess;
	}
	
	public void setImpersonationaccess(Boolean impersonationaccess) {
		this.impersonationaccess = impersonationaccess;
	}
	
	public Boolean getEmailformaccess() {
		return emailformaccess;
	}
	
	public void setEmailformaccess(Boolean emailformaccess) {
		this.emailformaccess = emailformaccess;
	}

}
