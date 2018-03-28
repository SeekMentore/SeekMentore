package com.model;

import java.io.Serializable;

import com.constants.UserConstants;

public class Credentials implements Serializable, UserConstants {
	
	private static final long serialVersionUID = -6349692224199736678L;
	private boolean isValid;
	
	public Credentials() {
	}
	
	public boolean isValid() {
		return isValid;
	}
}
