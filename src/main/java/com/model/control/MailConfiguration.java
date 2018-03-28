package com.model.control;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class MailConfiguration implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String host;
	private int port;
	private String encryptedUsername;
	private String encryptedPassword;
	
	public String getHost() {
		return host;
	}
	
	@XmlElement
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	@XmlElement
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	@XmlElement
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEncryptedUsername() {
		return encryptedUsername;
	}

	@XmlElement
	public void setEncryptedUsername(String encryptedUsername) {
		this.encryptedUsername = encryptedUsername;
	}
}
