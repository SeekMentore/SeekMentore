package com.model.control;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class SupportContactDetails implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String fromText;
	private ArrayList<String> mobile;
	private ArrayList<String> email;
	private ArrayList<String> website;
	
	public ArrayList<String> getMobile() {
		return mobile;
	}
	
	@XmlElement
	public void setMobile(ArrayList<String> mobile) {
		this.mobile = mobile;
	}
	
	public ArrayList<String> getEmail() {
		return email;
	}
	
	@XmlElement
	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}
	
	public ArrayList<String> getWebsite() {
		return website;
	}
	
	@XmlElement
	public void setWebsite(ArrayList<String> website) {
		this.website = website;
	}

	public String getFromText() {
		return fromText;
	}

	@XmlElement
	public void setFromText(String fromText) {
		this.fromText = fromText;
	}
}
