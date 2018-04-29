package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.MailConstants;
import com.dao.ApplicationDao;
import com.model.mail.ApplicationMail;
import com.model.mail.MailAttachment;

@Service(BeanConstants.BEAN_NAME_MAIl_SERVICE)
public class MailService implements MailConstants {
	
	@Autowired
	private transient JavaMailSender mailSender;
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Transactional
	public void insertIntoMailQueue(final ApplicationMail mailObject) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("mailType", mailObject.getMailType());
		paramsMap.put("entryDate", mailObject.getEntryDate());
		paramsMap.put("fromAddress", mailObject.getFromAddress());
		paramsMap.put("toAddress", mailObject.getToAddress());
		paramsMap.put("ccAddress", mailObject.getCcAddress());
		paramsMap.put("bccAddress", mailObject.getBccAddress());
		paramsMap.put("subjectContent", mailObject.getSubjectContent());
		paramsMap.put("messageContent", mailObject.getMessageContent());
		paramsMap.put("mailSent", NO);
		final long mailId = applicationDao.insertAndReturnGeneratedKey("INSERT INTO MAIL_QUEUE(MAIL_TYPE, ENTRY_DATE, FROM_ADDRESS, TO_ADDRESS, CC_ADDRESS, BCC_ADDRESS, SUBJECT_CONTENT, MESSAGE_CONTENT, MAIL_SENT) VALUES(:mailType,:entryDate,:fromAddress,:toAddress,:ccAddress,:bccAddress,:subjectContent,:messageContent,:mailSent)", paramsMap);
		if (null != mailObject.getAttachments() && !mailObject.getAttachments().isEmpty()) {
			for(final MailAttachment attachment : mailObject.getAttachments()) {
				final Map<String, Object> attachmentsPramsMap = new HashMap<String, Object>();
				attachmentsPramsMap.put("mailId", mailId);
				attachmentsPramsMap.put("content", attachment.getContent());
				attachmentsPramsMap.put("filename", attachment.getFilename());
				attachmentsPramsMap.put("applicationType", FileConstants.APPLICATION_TYPE_OCTET_STEAM);
				applicationDao.executeUpdate("INSERT INTO MAIL_ATTACHMENTS(MAIL_ID, CONTENT, FILENAME, APPLICATION_TYPE) VALUES(:mailId, :content, :filename, :applicationType)", attachmentsPramsMap);
			}
		}
	}
	
	public void sendEmail (final SimpleMailMessage mailMessage) {
		mailSender.send(mailMessage);
    }
	
	public void sendEmail (final MimeMessagePreparator mimeMessagePreparator) {
        mailSender.send(mimeMessagePreparator);
    }
}
