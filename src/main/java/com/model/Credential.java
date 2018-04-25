package com.model;

import java.io.Serializable;

import com.constants.UserConstants;
import com.utils.PrintFormatterUtils;

public class Credential implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private String userId;
	private String clientSideEncypytedPassword;
	private String userType;
	
	public Credential() {}
	
	public Credential(String userId, String userType, String clientSideEncypytedPassword) {
		this.userId = userId;
		this.clientSideEncypytedPassword = clientSideEncypytedPassword;
		this.userType = userType;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getClientSideEncypytedPassword() {
		return clientSideEncypytedPassword;
	}

	public void setClientSideEncypytedPassword(String clientSideEncypytedPassword) {
		this.clientSideEncypytedPassword = clientSideEncypytedPassword;
	}
	
	@Override
	public String toString() {
		final StringBuilder credential = new StringBuilder(EMPTY_STRING);
		credential.append(PrintFormatterUtils.startATable());
		credential.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns("userId", userId));
		credential.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns("userType", userType));
		credential.append(PrintFormatterUtils.printALabelAndDataInRowWithTwoColumns("clientSideEncypytedPassword", clientSideEncypytedPassword));
		credential.append(PrintFormatterUtils.endATable());
		return credential.toString();
	}
}
