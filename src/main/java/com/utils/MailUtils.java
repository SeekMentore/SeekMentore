package com.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.constants.BeanConstants;
import com.constants.JNDIandControlConfigurationConstants;
import com.constants.MailConstants;
import com.exception.ApplicationException;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MailService;
import com.utils.context.AppContext;

public class MailUtils implements MailConstants {
	
	/*
	 * Public Mail sending functions
	 * To send out different mail as per scenarios
	 * Modify or add function as per need
	 * And call the appropriate Private function to 
	 * send out the email
	 */
	public static void sendCustomisedFromAddressSimpleMailMessage (
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		// TODO this section will alter the From Address than the default address with which the System Mailer Logged in
    }
	
	public static void sendCustomisedFromAddressMimeMessageEmail (
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String htmlMessage,
			final List<MailAttachment> attachments
	) throws Exception {
		// TODO this section will alter the From Address than the default address with which the System Mailer Logged in
    }
	
	public static void sendSimpleMailMessage (
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		sendingSimpleMailMessage(
				null,
				toAddressSemicolonSeparated,
				ccAddressSemicolonSeparated,
				bccAddressSemicolonSeparated,
				subject,
				message);
    }
	
	public static void sendMimeMessageEmail (
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String htmlMessage,
			final List<MailAttachment> attachments
	) throws Exception {
		MailUtils.sendingCustomisedFromAddressMimeMessageEmail(
				null, 
				toAddressSemicolonSeparated, 
				ccAddressSemicolonSeparated,
				bccAddressSemicolonSeparated,
				subject, 
				htmlMessage,
				attachments);
    }
	
	public static void sendErrorMessageEmail (
			final String htmlMessage,
			final List<MailAttachment> attachments
	) throws Exception {
		MailUtils.sendingCustomisedFromAddressMimeMessageEmail(
				null, 
				getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getErrorHandlingMailList(), 
				null,
				null,
				SUBJECT_EXCEPTION_OCCURED_IN + getJNDIandControlConfigurationLoadService().getServerName() + SUBJECT_FOR_EXCEPTION_SERVER_AT + (new Date()).toString(), 
				htmlMessage,
				attachments);
    }
	
	/**
	 * Private Mail Sending functions to 
	 * Send out Simple Emails
	 * MimeMails
	 * 
	 * Do not modify the code in them
	 * @throws Exception 
	 */
	
	private static Map<String, Object> checkAllMailControlSettingAndReformatMailMessageAccordinglyAndLogInDatabase(
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		/*
		 * Store the incoming parameters
		 */
		final Map<String, Object> mailParams =  new HashMap<String, Object>();
		mailParams.put(PARAM_FROM_ADDRESS, validateAndGetFromAddress(fromAddress));
		mailParams.put(PARAM_TO_ADDRESS_SEMICOLON_SEPARATED, toAddressSemicolonSeparated);
		mailParams.put(PARAM_CC_ADDRESS_SEMICOLON_SEPARATED, ccAddressSemicolonSeparated);
		mailParams.put(PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED, bccAddressSemicolonSeparated);
		mailParams.put(PARAM_REPLY_TO_ADDRESS, getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemReplyToAddress());
		mailParams.put(PARAM_SUBJECT, subject);
		mailParams.put(PARAM_MESSAGE, message);
		
		boolean sendEmail = false;
		final StringBuilder addressInfo =  new StringBuilder(EMPTY_STRING);
		addressInfo.append(MAIL_LINE_INFO_FROM).append(mailParams.get(PARAM_FROM_ADDRESS)).append(NEW_LINE).append(LINE_BREAK);
		addressInfo.append(MAIL_LINE_INFO_TO).append(mailParams.get(PARAM_TO_ADDRESS_SEMICOLON_SEPARATED)).append(NEW_LINE).append(LINE_BREAK);
		addressInfo.append(MAIL_LINE_INFO_CC).append(mailParams.get(PARAM_CC_ADDRESS_SEMICOLON_SEPARATED)).append(NEW_LINE).append(LINE_BREAK);
		addressInfo.append(MAIL_LINE_INFO_BCC).append(mailParams.get(PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED)).append(NEW_LINE).append(LINE_BREAK);
		addressInfo.append(MAIL_LINE_INFO_REPLY_TO).append(mailParams.get(PARAM_REPLY_TO_ADDRESS)).append(NEW_LINE).append(LINE_BREAK);
		addressInfo.append(MAIL_LINE_INFO_SUBJECT).append(mailParams.get(PARAM_SUBJECT));
		final String newContent = addressInfo.toString() + NEW_LINE + LINE_BREAK + NEW_LINE + LINE_BREAK + NEW_LINE + LINE_BREAK + mailParams.get(PARAM_MESSAGE);
		
		if (getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_PROD)) {
			// If Server is PROD directly send out actual E-mails to actual recipients
			sendEmail = true;
		} else if (getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmails()) {
			if (getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL)) {
				if (getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmailsButDivertThemToSomeOtherRecipient()) {
					// Reset the values in Mail Params
					mailParams.put(PARAM_TO_ADDRESS_SEMICOLON_SEPARATED, getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().getDivertedRecipeintEmailId());
					mailParams.put(PARAM_CC_ADDRESS_SEMICOLON_SEPARATED, null);
					mailParams.put(PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED, null);
					mailParams.put(PARAM_SUBJECT, SUBJECT_DIVERTED_MAIL_FROM + getJNDIandControlConfigurationLoadService().getServerName() + WHITESPACE + COLON + WHITESPACE + mailParams.get(PARAM_SUBJECT));
					mailParams.put(PARAM_MESSAGE, newContent);
					// Send this email with Diverted settings
					sendEmail = true;
					// If Server is not local send out email with appended subject
				} 
			} else {
				mailParams.put(PARAM_SUBJECT, getJNDIandControlConfigurationLoadService().getServerName() + WHITESPACE + COLON + WHITESPACE + mailParams.get(PARAM_SUBJECT));
				// Send this email appended subject
				sendEmail = true;
			}
		} 
		// If Console Logger is enabled log it on console
		if(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isShowOnConsoleWhatEmailWillBeSent()) {
			LoggerUtils.logOnConsole(newContent);
		}
		mailParams.put(PARAM_SEND_EMAIL, sendEmail);
		return mailParams;
	}
	
	private static void sendingSimpleMailMessage(
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		final Map<String, Object> mailParams = checkAllMailControlSettingAndReformatMailMessageAccordinglyAndLogInDatabase(
																										fromAddress,
																										toAddressSemicolonSeparated,
																										ccAddressSemicolonSeparated,
																										bccAddressSemicolonSeparated,
																										subject,
																										message
																								);
		if ((Boolean)mailParams.get(PARAM_SEND_EMAIL)) {
			final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom((String)mailParams.get(PARAM_FROM_ADDRESS));
			simpleMailMessage.setTo(getSplittedAddressesForSimpleMailMessage((String)mailParams.get(PARAM_TO_ADDRESS_SEMICOLON_SEPARATED), true));
			simpleMailMessage.setCc(getSplittedAddressesForSimpleMailMessage((String)mailParams.get(PARAM_CC_ADDRESS_SEMICOLON_SEPARATED), false));
			simpleMailMessage.setBcc(getSplittedAddressesForSimpleMailMessage((String)mailParams.get(PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED), false));
			simpleMailMessage.setReplyTo((String)mailParams.get(PARAM_REPLY_TO_ADDRESS));
			simpleMailMessage.setSubject((String)mailParams.get(PARAM_SUBJECT));
			simpleMailMessage.setText((String)mailParams.get(PARAM_MESSAGE));
			getMailService().sendEmail(simpleMailMessage);
			LoggerUtils.logOnConsole(MESSAGE_SEND);
		}
	}
	
	private static void sendingCustomisedFromAddressMimeMessageEmail (
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String htmlMessage,
			final List<MailAttachment> attachments
	) throws Exception {
		final Map<String, Object> mailParams = checkAllMailControlSettingAndReformatMailMessageAccordinglyAndLogInDatabase(
																										fromAddress,
																										toAddressSemicolonSeparated,
																										ccAddressSemicolonSeparated,
																										bccAddressSemicolonSeparated,
																										subject,
																										htmlMessage
																								);
		if ((Boolean)mailParams.get(PARAM_SEND_EMAIL)) {
			final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setFrom((String)mailParams.get(PARAM_FROM_ADDRESS));
					mimeMessage.setRecipients(Message.RecipientType.TO, getSplittedAddressesForMimeMessage((String)mailParams.get(PARAM_TO_ADDRESS_SEMICOLON_SEPARATED), true));
					mimeMessage.setRecipients(Message.RecipientType.CC, getSplittedAddressesForMimeMessage((String)mailParams.get(PARAM_CC_ADDRESS_SEMICOLON_SEPARATED), false));
					mimeMessage.setRecipients(Message.RecipientType.BCC, getSplittedAddressesForMimeMessage((String)mailParams.get(PARAM_BCC_ADDRESS_SEMICOLON_SEPARATED), false));
					mimeMessage.setReplyTo(getSplittedAddressesForMimeMessage((String)mailParams.get(PARAM_REPLY_TO_ADDRESS), true));
					mimeMessage.setSubject((String)mailParams.get(PARAM_SUBJECT));
					mimeMessage.setContent(createMimeMultipart((String)mailParams.get(PARAM_MESSAGE), attachments));
				} 
			};
			getMailService().sendEmail(mimeMessagePreparator);
			LoggerUtils.logOnConsole(MESSAGE_SEND);
		}
    }
	
	private static MimeMultipart createMimeMultipart (
			final String htmlMessage,
			final List<MailAttachment> attachments 
	) throws MessagingException {
		// contentPart is the content to be sent. It is divided in bodyContent and attachmentContent
        final MimeMultipart contentMultipart = new MimeMultipart(MULTIPART_MIXED);

        // Message body in txt and html format
        final MimeMultipart bodyMultipart = new MimeMultipart(MULTIPART_ALTERNATIVE);
        
        // Creates text message, this retains the html content along with the attachments
        final BodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent("DUMMY CONTENT PLACE HOLDER", BODYPART_TEXT_HTML);
        // Creates html message
        final BodyPart htmlBodyPart = new MimeBodyPart();
        htmlBodyPart.setContent(htmlMessage, BODYPART_TEXT_HTML);
        bodyMultipart.addBodyPart(textBodyPart);
        bodyMultipart.addBodyPart(htmlBodyPart);

        // Wrapper for bodyTxt and bodyHtml
        final MimeBodyPart contentBodypart = new MimeBodyPart();
        contentBodypart.setContent(bodyMultipart);

        // At this point, contentPart contains bodyTxt and bodyHtml wrapped in a multipart/alternative
        contentMultipart.addBodyPart(contentBodypart);

        // Adds attachments to contentPart
        if (null != attachments && !attachments.isEmpty()) {
        	for (final MailAttachment mailAttachment : attachments) {
        		contentMultipart.addBodyPart(mailAttachment.getAttachment());
    		}
        }
		return contentMultipart;
	}
	
	private static String[] getSplittedAddressesForSimpleMailMessage(final String addressSemicolonSeparated, final boolean mandatory) {
		if (null == addressSemicolonSeparated || EMPTY_STRING.equals(addressSemicolonSeparated.trim())) {
			if (mandatory) {
				throw new ApplicationException("Mandatory Email Address cannot be NULL/Blank");
			} else {
				return null;
			}
		}
		final String[] inputAddressArray = addressSemicolonSeparated.split(SEMICOLON);
		final List<String> splittedAddressesList = new ArrayList<String>();
		for (int i = 0 ; i < inputAddressArray.length ; i++) {
			final String address = inputAddressArray[i].trim();
			if (validateAddress(address)) {
				splittedAddressesList.add(address);
			} else {
				LoggerUtils.logOnConsole("Invalid Email Address : " + address);
			}
		}
		if (splittedAddressesList.isEmpty()) {
			if (mandatory) {
				throw new ApplicationException("Mandatory Email Address cannot be NULL/Blank");
			} else {
				return null;
			}
		} 
		return splittedAddressesList.toArray(new String[splittedAddressesList.size()]);
	}
	
	private static InternetAddress[] getSplittedAddressesForMimeMessage(final String addressSemicolonSeparated, final boolean mandatory) throws AddressException {
		if (null == addressSemicolonSeparated || EMPTY_STRING.equals(addressSemicolonSeparated.trim())) {
			if (mandatory) {
				throw new ApplicationException("Mandatory Email Address cannot be NULL/Blank");
			} else {
				return null;
			}
		}
		final String[] inputAddressArray = addressSemicolonSeparated.split(SEMICOLON);
		final List<InternetAddress> splittedAddressesList = new ArrayList<InternetAddress>();
		for (int i = 0 ; i < inputAddressArray.length ; i++) {
			final String address = inputAddressArray[i].trim();
			if (validateAddress(address)) {
				splittedAddressesList.add(new InternetAddress(address));
			} else {
				LoggerUtils.logOnConsole("Invalid Email Address : " + address);
			}
		}
		if (splittedAddressesList.isEmpty()) {
			if (mandatory) {
				throw new ApplicationException("Mandatory Email Address cannot be NULL/Blank");
			} else {
				return null;
			}
		} 
		return splittedAddressesList.toArray(new InternetAddress[splittedAddressesList.size()]);
	}
	
	private static boolean validateAddress(final String address) {
		return (address.indexOf(WHITESPACE) == -1);
	}
	
	private static String validateAndGetFromAddress(final String fromAddress) throws Exception {
		return (fromAddress == null || EMPTY_STRING.equals(fromAddress.trim())) 
				? SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getEncryptedUsername()) 
						: fromAddress;
	}
	
	public static MailService getMailService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_MAIl_SERVICE, MailService.class);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}