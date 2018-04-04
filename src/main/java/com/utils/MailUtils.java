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
				"Exception occured in " + getJNDIandControlConfigurationLoadService().getServerName() + " Server at " + (new Date()).toString(), 
				htmlMessage,
				attachments);
    }
	
	/**
	 * Private Mail Sending functions to 
	 * Send out Simple Emails
	 * MimeMails
	 * 
	 * Do not modify the code in them
	 */
	
	private static void checkAllMailControlSettingAndReformatMailMessageAccordingly(final Map<String, String> mailParams) {
		/*
		 * Check Mail Diversions and Appropriately Set values
		 */
		boolean sendEmail = false;
		final StringBuilder addressInfo =  new StringBuilder(EMPTY_STRING);
		addressInfo.append("From :").append(mailParams.get("fromAddress")).append(NEW_LINE);
		addressInfo.append("To :").append(mailParams.get("toAddressSemicolonSeparated")).append(NEW_LINE);
		addressInfo.append("Cc :").append(mailParams.get("ccAddressSemicolonSeparated")).append(NEW_LINE);
		addressInfo.append("Bcc :").append(mailParams.get("bccAddressSemicolonSeparated")).append(NEW_LINE);
		addressInfo.append("Reply To :").append(mailParams.get("replyToAddress")).append(NEW_LINE);
		addressInfo.append("Subject :").append(mailParams.get("subject"));
		final String newContent = addressInfo.toString() + NEW_LINE + NEW_LINE + NEW_LINE + mailParams.get("message");
		
		if (getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_PROD)) {
			// If Server is PROD directly send out actual E-mails
			sendEmail = true;
		} else if (getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmails()) {
			if (getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isSendOutActualEmailsButDivertThemToSomeOtherRecipient()) {
				// Reset the values in mailMessage
				mailParams.put("toAddressSemicolonSeparated", getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().getDivertedRecipeintEmailId());
				mailParams.put("ccAddressSemicolonSeparated", null);
				mailParams.put("bccAddressSemicolonSeparated", null);
				mailParams.put("subject", "Diverted Mail from " + getJNDIandControlConfigurationLoadService().getServerName() + " : " + mailParams.get("subject"));
				mailParams.put("message", newContent);
				// Send this email with Diverted settings
				sendEmail = true;
			}
		} else if(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getMailingDuringDevelopmentAndTestingFeatures().isShowOnConsoleWhatEmailWillBeSent()) {
			LoggerUtils.logOnConsole(newContent);
		}
		mailParams.put("sendEmail", String.valueOf(sendEmail));
	}
	
	private static void sendingSimpleMailMessage(
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		final Map<String, String> mailParams =  new HashMap<String, String>();
		mailParams.put("fromAddress", fromAddress);
		mailParams.put("toAddressSemicolonSeparated", toAddressSemicolonSeparated);
		mailParams.put("ccAddressSemicolonSeparated", ccAddressSemicolonSeparated);
		mailParams.put("bccAddressSemicolonSeparated", bccAddressSemicolonSeparated);
		mailParams.put("replyToAddress", getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemReplyToAddress());
		mailParams.put("subject", subject);
		mailParams.put("message", message);
		checkAllMailControlSettingAndReformatMailMessageAccordingly(mailParams);
		
		/*
		 * Check Mail Diversions and Appropriately Set values
		 */
		if (Boolean.valueOf(mailParams.get("sendEmail"))) {
			final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(validateAndGetFromAddress(mailParams.get("fromAddress")));
			simpleMailMessage.setTo(getSplittedAddressesForSimpleMailMessage(mailParams.get("toAddressSemicolonSeparated"), true));
			simpleMailMessage.setCc(getSplittedAddressesForSimpleMailMessage(mailParams.get("ccAddressSemicolonSeparated"), false));
			simpleMailMessage.setBcc(getSplittedAddressesForSimpleMailMessage(mailParams.get("bccAddressSemicolonSeparated"), false));
			simpleMailMessage.setReplyTo(mailParams.get("replyToAddress"));
			simpleMailMessage.setSubject(mailParams.get("subject"));
			simpleMailMessage.setText(mailParams.get("message"));
			getMailService().sendEmail(simpleMailMessage);
			LoggerUtils.logOnConsole("Message Send...");
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
		final Map<String, String> mailParams =  new HashMap<String, String>();
		mailParams.put("fromAddress", fromAddress);
		mailParams.put("toAddressSemicolonSeparated", toAddressSemicolonSeparated);
		mailParams.put("ccAddressSemicolonSeparated", ccAddressSemicolonSeparated);
		mailParams.put("bccAddressSemicolonSeparated", bccAddressSemicolonSeparated);
		mailParams.put("replyToAddress", getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemReplyToAddress());
		mailParams.put("subject", subject);
		mailParams.put("message", htmlMessage);
		checkAllMailControlSettingAndReformatMailMessageAccordingly(mailParams);
		
		/*
		 * Check Mail Diversions and Appropriately Set values
		 */
		if (Boolean.valueOf(mailParams.get("sendEmail"))) {
			final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setFrom(validateAndGetFromAddress(mailParams.get("fromAddress")));
					mimeMessage.setRecipients(Message.RecipientType.TO, getSplittedAddressesForMimeMessage(mailParams.get("toAddressSemicolonSeparated"), true));
					mimeMessage.setRecipients(Message.RecipientType.CC, getSplittedAddressesForMimeMessage(mailParams.get("ccAddressSemicolonSeparated"), false));
					mimeMessage.setRecipients(Message.RecipientType.BCC, getSplittedAddressesForMimeMessage(mailParams.get("bccAddressSemicolonSeparated"), false));
					mimeMessage.setReplyTo(getSplittedAddressesForMimeMessage(mailParams.get("replyToAddress"), true));
					mimeMessage.setSubject(mailParams.get("subject"));
					mimeMessage.setContent(createMimeMultipart(mailParams.get("message"), attachments));
				} 
			};
			getMailService().sendEmail(mimeMessagePreparator);
			LoggerUtils.logOnConsole("Message Send...");
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