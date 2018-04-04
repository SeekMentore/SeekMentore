package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.MailConstants;

@Service(BeanConstants.BEAN_NAME_MAIl_SERVICE)
public class MailService implements MailConstants {
	
	@Autowired
	private transient JavaMailSender mailSender;
	
	public void sendEmail (final SimpleMailMessage mailMessage) {
		mailSender.send(mailMessage);
    }
	
	public void sendEmail (final MimeMessagePreparator mimeMessagePreparator) {
        mailSender.send(mimeMessagePreparator);
    }
}
