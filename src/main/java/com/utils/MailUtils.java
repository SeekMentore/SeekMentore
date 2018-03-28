package com.utils;

import java.util.ArrayList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.constants.BeanConstants;
import com.constants.MailConstants;
import com.exception.ApplicationException;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MailService;
import com.utils.context.AppContext;

public class MailUtils implements MailConstants {
	
	private static String[] getSplittedAddresses(final String addressSemicolonSeparated, final boolean mandatory) {
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
	
	private static boolean validateAddress(final String address) {
		return (address.indexOf(WHITESPACE) == -1);
	}
	
	private static String validateAndGetFromAddress(final String fromAddress) throws Exception {
		return (fromAddress == null || EMPTY_STRING.equals(fromAddress.trim())) 
				? SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getEncryptedUsername()) 
						: fromAddress;
	}
	
	public static void sendEmail (
			final String fromAddress,
			final String toAddressSemicolonSeparated,
			final String ccAddressSemicolonSeparated,
			final String bccAddressSemicolonSeparated,
			final String subject,
			final String message
	) throws Exception {
		final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(validateAndGetFromAddress(fromAddress));
		simpleMailMessage.setTo(getSplittedAddresses(toAddressSemicolonSeparated, true));
		simpleMailMessage.setCc(getSplittedAddresses(ccAddressSemicolonSeparated, false));
		simpleMailMessage.setBcc(getSplittedAddresses(bccAddressSemicolonSeparated, false));
		simpleMailMessage.setReplyTo("jigs.kcs@gmail.com");
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
        getMailService().sendEmail(simpleMailMessage);
        LoggerUtils.logOnConsole("Message Send...");
    }
	
	public static void sendEmailWithAttachments (
			final String fromAddress,
			final String toAddress,
			final String subject,
			final String htmlMessage,
			final List<MailAttachment> attachments
	) throws Exception {
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom((fromAddress == null || EMPTY_STRING.equals(fromAddress.trim())) 
										? SecurityUtil.decrypt(getJNDIandControlConfigurationLoadService().getControlConfiguration().getMailConfiguration().getEncryptedUsername()) 
										: fromAddress
									);
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
				mimeMessage.setSubject(subject);
				mimeMessage.setContent(createMimeMultipart1(htmlMessage, attachments));
			} 
        };
        getMailService().sendEmail(mimeMessagePreparator);
        LoggerUtils.logOnConsole("Message Send...");
    }
	
	private static MimeMultipart createMimeMultipart1 (
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
        if (!attachments.isEmpty()) {
        	for (final MailAttachment mailAttachment : attachments) {
        		contentMultipart.addBodyPart(mailAttachment.getAttachment());
    		}
        }
		return contentMultipart;
	}
	
	public static MailService getMailService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_MAIl_SERVICE, MailService.class);
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}