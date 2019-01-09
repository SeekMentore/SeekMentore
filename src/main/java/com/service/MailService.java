package com.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.model.gridcomponent.GridComponent;
import com.model.mail.ApplicationMail;
import com.model.mail.MailAttachment;
import com.model.rowmappers.ApplicationMailRowMapper;
import com.model.rowmappers.MailAttachmentRowMapper;
import com.utils.GridQueryUtils;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_MAIl_SERVICE)
public class MailService implements MailConstants {
	
	@Autowired
	private transient JavaMailSender mailSender;
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	public void sendEmail (final SimpleMailMessage mailMessage) {
		mailSender.send(mailMessage);
    }
	
	public void sendEmail (final MimeMessagePreparator mimeMessagePreparator) {
        mailSender.send(mimeMessagePreparator);
    }
	
	@Transactional
	public void insertIntoMailQueue(final ApplicationMail applicationMail) throws Exception {
		applicationMail.setMailSent(NO);
		final Long mailId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("mail", "insertApplicationMail", applicationMail);
		if (ValidationUtils.checkNonEmptyList(applicationMail.getAttachments())) {
			for(final MailAttachment mailAttachment : applicationMail.getAttachments()) {
				mailAttachment.setMailId(mailId);
				mailAttachment.setApplicationType(FileConstants.APPLICATION_TYPE_OCTET_STEAM);
				applicationDao.executeUpdateWithQueryMapper("mail", "insertMailAttachment", mailAttachment);
			}
		}
	}
	
	@Transactional
	public void insertListIntoMailQueue(final List<ApplicationMail> mpplicationMailList) throws Exception {
		final List<ApplicationMail> mailListWithAttachments = new LinkedList<ApplicationMail>();
		final List<ApplicationMail> paramObjectList = new LinkedList<ApplicationMail>();
		for (final ApplicationMail applicationMail : mpplicationMailList) {
			applicationMail.setMailSent(NO);
			if (ValidationUtils.checkNonEmptyList(applicationMail.getAttachments())) {
				mailListWithAttachments.add(applicationMail);
			} else {
				paramObjectList.add(applicationMail);
			}
		}
		if (ValidationUtils.checkNonEmptyList(paramObjectList)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertApplicationMail", paramObjectList);
		}
		if (ValidationUtils.checkNonEmptyList(mailListWithAttachments)) {
			for (final ApplicationMail applicationMail : mailListWithAttachments) {
				final Long mailId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("mail", "insertApplicationMail", applicationMail);
				if (ValidationUtils.checkNonEmptyList(applicationMail.getAttachments())) {
					for(final MailAttachment mailAttachment : applicationMail.getAttachments()) {
						mailAttachment.setMailId(mailId);
						mailAttachment.setApplicationType(FileConstants.APPLICATION_TYPE_OCTET_STEAM);
						applicationDao.executeUpdateWithQueryMapper("mail", "insertMailAttachment", mailAttachment);
					}
				}
			}
		}
	}
	
	public List<ApplicationMail> getApplicationMailList(final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("mail", "selectApplicationMail");
		final String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("mail", "applicationMailSorter");
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new ApplicationMailRowMapper());
	}
	
	public List<ApplicationMail> getPedingApplicationMailList(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, ApplicationMail.class);
		} else {
			gridComponent = new GridComponent(ApplicationMail.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("mail", "applicationMailPendingEmailFilter"));
		return getApplicationMailList(gridComponent);
	}
	
	public List<MailAttachment> getMailAttachmentList(final Long mailId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("mailId", mailId);
		final List<MailAttachment> dbMailAttachmentList = applicationDao.findAll(queryMapperService.getQuerySQL("mail", "selectMailAttachment")
																				+ queryMapperService.getQuerySQL("mail", "mailAttachmentMailIdFilter"), paramsMap, new MailAttachmentRowMapper());
		final List<MailAttachment> mailAttachmentList = new LinkedList<MailAttachment>();
		if (ValidationUtils.checkNonEmptyList(dbMailAttachmentList)) {
			// Converting DB attachment list in JMailSender Attachment list
			for (final MailAttachment mailAttachment : dbMailAttachmentList) {
				mailAttachmentList.add(new MailAttachment(mailAttachment.getFilename(), mailAttachment.getContent(), mailAttachment.getApplicationType()));
			}
		}
		return ValidationUtils.checkNonEmptyList(mailAttachmentList) ? mailAttachmentList : null;
	}
}
