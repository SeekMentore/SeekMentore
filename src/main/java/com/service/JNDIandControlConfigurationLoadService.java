package com.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.JNDIandControlConfigurationConstants;
import com.model.control.ControlConfiguration;
import com.utils.ApplicationUtils;

@Service(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE)
public class JNDIandControlConfigurationLoadService implements JNDIandControlConfigurationConstants {
	
	@Autowired
	private Environment environment;
	
	private ControlConfiguration controlConfiguration;
	
	private String encyptionKey;
	private String serverName;
	
	@PostConstruct
	public void parseControlConfigurationFromXML()  throws JAXBException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.controlConfiguration = ApplicationUtils.parseXML(CONFIGURATION_XML_PATH, ControlConfiguration.class);
		setJNDIEnvironmentVariables();
	}
	
	private void setJNDIEnvironmentVariables() {
		this.encyptionKey = environment.getProperty(ENVIRONMENT_VARIABLE_ENCRYPTION_KEY);
		this.serverName = environment.getProperty(ENVIRONMENT_VARIABLE_SERVER_NAME);
		this.controlConfiguration.getMailConfiguration().setEncryptedUsername(environment.getProperty(ENCRYPTED_SUPPORT_MAIL_GROUP_USERNAME));
		this.controlConfiguration.getMailConfiguration().setEncryptedPassword(environment.getProperty(ENCRYPTED_SUPPORT_MAIL_GROUP_PASSWORD));
	}
	
	public ControlConfiguration getControlConfiguration() {
		return controlConfiguration;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getEncyptionKey() {
		return encyptionKey;
	}

	public String getServerName() {
		return serverName;
	}
}
