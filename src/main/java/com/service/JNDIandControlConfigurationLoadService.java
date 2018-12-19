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
import com.model.control.AWSParams;
import com.model.control.ControlConfiguration;
import com.model.control.MailingDuringDevelopmentAndTestingFeatures;
import com.model.control.S3ClientParams;
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
		this.controlConfiguration.getCaptchaParams().setEncryptedApiSecret(environment.getProperty(ENCRYPTED_CAPTCHA_SECRET));
		this.controlConfiguration.getMailConfiguration().setMailingDuringDevelopmentAndTestingFeatures(new MailingDuringDevelopmentAndTestingFeatures());
		this.controlConfiguration.setAwsParams(new AWSParams());
		this.controlConfiguration.getAwsParams().setS3ClientParams(new S3ClientParams());
		this.controlConfiguration.getAwsParams().getS3ClientParams().setAccessKeyEncypted(environment.getProperty(ACCESS_KEY_ID_ENCRYPTED));
		this.controlConfiguration.getAwsParams().getS3ClientParams().setSecretAccessEncypted(environment.getProperty(SECRET_ACCESS_ENCRYPTED));
		this.controlConfiguration.getAwsParams().getS3ClientParams().setBucketNameEncypted(environment.getProperty(BUCKET_NAME_ENCYPTED));
		if (!SERVER_NAME_LOCAL.equals(this.serverName)) {
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setSendOutActualEmails(true);
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setShowOnConsoleWhatEmailWillBeSent(false);
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setSendOutActualEmailsButDivertThemToSomeOtherRecipient(false);
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setDivertedRecipeintEmailId(null);
		} else {
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setSendOutActualEmails(Boolean.valueOf(environment.getProperty(ENVIRONMENT_VARIABLE_SEND_OUT_ACTUAL_EMAILS)));
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setShowOnConsoleWhatEmailWillBeSent(Boolean.valueOf(environment.getProperty(ENVIRONMENT_VARIABLE_SHOW_ON_CONSOLE_WHAT_EMAIL_WILL_BE_SENT)));
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setSendOutActualEmailsButDivertThemToSomeOtherRecipient(Boolean.valueOf(environment.getProperty(ENVIRONMENT_VARIABLE_SEND_OUT_ACTUAL_EMAILS_BUT_DIVERT_THEM_TO_SOME_OTHER_RECIPIENT)));
			this.controlConfiguration.getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().setDivertedRecipeintEmailId(environment.getProperty(ENVIRONMENT_VARIABLE_DIVERTED_RECIPEINT_EMAIL_ID));
		}
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
