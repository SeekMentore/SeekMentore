package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.MailConstants;
import com.dao.ApplicationDao;
import com.model.mail.ApplicationMail;

@Service(BeanConstants.BEAN_NAME_MAIl_SERVICE)
public class MailService implements MailConstants {
	
	@Autowired
	private transient JavaMailSender mailSender;
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Transactional
	public void insertIntoMailQueue(final ApplicationMail mailObject) {
		applicationDao.updateWithPreparedQueryAndIndividualOrderedParams("INSERT INTO MAIL_QUEUE(MAIL_TYPE, ENTRY_DATE, FROM_ADDRESS, TO_ADDRESS, CC_ADDRESS, BCC_ADDRESS, SUBJECT_CONTENT, MESSAGE_CONTENT, MAIL_SENT) VALUES(?,?,?,?,?,?,?,?,?)", new Object[] {mailObject.getMailType(), mailObject.getEntryDate(), mailObject.getFromAddress(), mailObject.getToAddress(), mailObject.getCcAddress(), mailObject.getBccAddress(), mailObject.getSubjectContent(), mailObject.getMessageContent(), "N"});
		/*if (null != mailObject.getAttachments() && !mailObject.getAttachments().isEmpty()) {
			for(final MailAttachment attachment : mailObject.getAttachments()) {
				
			}
		}*/
	}
	
	public void sendEmail (final SimpleMailMessage mailMessage) {
		mailSender.send(mailMessage);
    }
	
	public void sendEmail (final MimeMessagePreparator mimeMessagePreparator) {
        mailSender.send(mimeMessagePreparator);
    }
}
