package com.model.components.publicaccess;

import java.io.Serializable;

import com.constants.components.publicaccess.RegisteredTutorConstants;

public class RegisteredTutor implements Serializable, RegisteredTutorConstants {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private String tutorId;
	private String name;
	private String contactNumber;
	private String emailId;
	
	public RegisteredTutor() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getTutorId() {
		return tutorId;
	}

	public void setTutorId(String tutorId) {
		this.tutorId = tutorId;
	}
}
