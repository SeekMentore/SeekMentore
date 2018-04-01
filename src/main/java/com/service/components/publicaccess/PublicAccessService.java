package com.service.components.publicaccess;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.dao.ApplicationDao;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.PublicApplication;
import com.model.mail.MailAttachment;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.components.AdminService;
import com.utils.MailUtils;
import com.utils.VelocityUtils;
import com.utils.context.AppContext;

@Service(BeanConstants.BEAN_NAME_PUBLIC_ACCESS_SERVICE)
public class PublicAccessService implements PublicAccessConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public Map<String, Object> submitApplication(final PublicApplication application) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		if (application instanceof BecomeTutor) {
			response.put("UNKNOWN_PUBLIC_PAGE_REFERENCE", false);
			response.put("PAGE_REFERNCE", "TUTOR_REGISTRATION");
			final BecomeTutor tutorApplication = (BecomeTutor) application;
			feedRecordForTutorApplication(tutorApplication);
			sendNotificationAndConfirmationEmailsToTutor(tutorApplication);
		} else {
			response.put("UNKNOWN_PUBLIC_PAGE_REFERENCE", true);
		}
		return response;
	}
	
	private void sendNotificationAndConfirmationEmailsToTutor(final BecomeTutor tutorApplication) throws Exception {
		// Send Registration notification message to concerned team
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put(BECOME_TUTOR_APPLICATION_VM_OBJECT, tutorApplication.toString());
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getTutorRegistrationSupportMailList(), 
				null,
				null,
				SUBJECT_NEW_TUTOR_REGISTRATION_REQUEST, 
				VelocityUtils.parseTemplate(BECOME_TUTOR_REGISTRATION_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
		// Send Registration confirmation message to Tutor on his provided email Id
		MailUtils.sendMimeMessageEmail( 
				tutorApplication.getEmailId(), 
				null,
				null,
				SUBJECT_NEW_TUTOR_REGISTRATION_CONFIRMATION, 
				VelocityUtils.parseTemplate(BECOME_TUTOR_REGISTRATION_CONFIRMATION_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void feedRecordForTutorApplication(final PublicApplication application) {
		applicationDao.save(application);
	}
	
	public void testEmail() throws IOException, MessagingException, Exception {
		final List<MailAttachment> attachments = new LinkedList<MailAttachment>();
		attachments.add(new MailAttachment("FIRST REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, 
											AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class).downloadReport(""),
											"application/xlsx"));
		attachments.add(new MailAttachment("SECOND REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, 
											AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class).downloadReport(""),
											"application/xlsx"));
		MailUtils.sendSimpleMailMessage( 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"jigs.kcs@gmail.com",
				"partner.seekmentore@gmail.com",
				"Simple Email Subject", 
				"Simple Email Body");
		MailUtils.sendMimeMessageEmail( 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"jigs.kcs@gmail.com",
				"partner.seekmentore@gmail.com",
				"Attachment Email Subject", 
				"<html><body><h1>this is html h1</h1><h3>this is html h3</h3></body></html>",
				attachments);
		final List<MailAttachment> attachments1 = new LinkedList<MailAttachment>();
		MailUtils.sendMimeMessageEmail( 
				"mukherjeeshantanu797@gmail.com;gunjack.mukherjee@gmail.com", 
				"jigs.kcs@gmail.com",
				"partner.seekmentore@gmail.com",
				"Without Attachment Email Subject", 
				"<html><body><h1>Without Attachment this is html h1</h1><h3>Without Attachment this is html h3</h3></body></html>",
				attachments1);
	}
}
