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
import com.model.rowmapper.ApplicationMailRowMapper;
import com.model.rowmapper.MailAttachmentRowMapper;
import com.utils.ExceptionUtils;
import com.utils.FileSystemUtils;
import com.utils.GridQueryUtils;
import com.utils.LoggerUtils;
import com.utils.UUIDGeneratorUtils;
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
		final List<MailAttachment> mailAttachmentListWithFsKey = new LinkedList<MailAttachment>();
		final List<MailAttachment> mailAttachmentListWithoutFsKey = new LinkedList<MailAttachment>();
		applicationMail.setMailSent(NO);
		applicationMail.setMailSerialId(UUIDGeneratorUtils.generateSerialGUID());
		applicationDao.executeUpdateWithQueryMapper("mail", "insertApplicationMail", applicationMail);
		if (ValidationUtils.checkNonEmptyList(applicationMail.getAttachments())) {
			for(final MailAttachment mailAttachment : applicationMail.getAttachments()) {
				mailAttachment.setAttachmentSerialId(UUIDGeneratorUtils.generateSerialGUID());
				mailAttachment.setMailSerialId(applicationMail.getMailSerialId());
				mailAttachment.setApplicationType(FileConstants.APPLICATION_TYPE_OCTET_STEAM);
				if (mailAttachment.getIsFileStoredInFileSystem()) {
					mailAttachmentListWithFsKey.add(mailAttachment);
				} else {
					mailAttachmentListWithoutFsKey.add(mailAttachment);
				}
			}
		}
		if (ValidationUtils.checkNonEmptyList(mailAttachmentListWithFsKey)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertMailAttachmentWithFSKey", mailAttachmentListWithFsKey);
		}
		if (ValidationUtils.checkNonEmptyList(mailAttachmentListWithoutFsKey)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertMailAttachment", mailAttachmentListWithoutFsKey);
		}
	}
	
	@Transactional
	public void insertListIntoMailQueue(final List<ApplicationMail> mpplicationMailList) throws Exception {
		final List<MailAttachment> mailAttachmentListWithFsKey = new LinkedList<MailAttachment>();
		final List<MailAttachment> mailAttachmentListWithoutFsKey = new LinkedList<MailAttachment>();
		for (final ApplicationMail applicationMail : mpplicationMailList) {
			applicationMail.setMailSent(NO);
			applicationMail.setMailSerialId(UUIDGeneratorUtils.generateSerialGUID());
			if (ValidationUtils.checkNonEmptyList(applicationMail.getAttachments())) {
				for(final MailAttachment mailAttachment : applicationMail.getAttachments()) {
					mailAttachment.setAttachmentSerialId(UUIDGeneratorUtils.generateSerialGUID());
					mailAttachment.setMailSerialId(applicationMail.getMailSerialId());
					mailAttachment.setApplicationType(FileConstants.APPLICATION_TYPE_OCTET_STEAM);
					if (mailAttachment.getIsFileStoredInFileSystem()) {
						mailAttachmentListWithFsKey.add(mailAttachment);
					} else {
						mailAttachmentListWithoutFsKey.add(mailAttachment);
					}
				}
			} 
		}
		if (ValidationUtils.checkNonEmptyList(mpplicationMailList)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertApplicationMail", mpplicationMailList);
		}
		if (ValidationUtils.checkNonEmptyList(mailAttachmentListWithFsKey)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertMailAttachmentWithFSKey", mailAttachmentListWithFsKey);
		}
		if (ValidationUtils.checkNonEmptyList(mailAttachmentListWithoutFsKey)) {
			applicationDao.executeBatchUpdateWithQueryMapper("mail", "insertMailAttachment", mailAttachmentListWithoutFsKey);
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
	
	public List<MailAttachment> getMailAttachmentList(final String mailSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("mailSerialId", mailSerialId);
		final List<MailAttachment> dbMailAttachmentList = applicationDao.findAll(queryMapperService.getQuerySQL("mail", "selectMailAttachment")
																				+ queryMapperService.getQuerySQL("mail", "mailAttachmentMailIdFilter"), paramsMap, new MailAttachmentRowMapper());
		final List<MailAttachment> mailAttachmentList = new LinkedList<MailAttachment>();
		if (ValidationUtils.checkNonEmptyList(dbMailAttachmentList)) {
			for (final MailAttachment mailAttachment : dbMailAttachmentList) {
				try {
					if (mailAttachment.getIsFileStoredInFileSystem()) {
						mailAttachment.setContent(FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(mailAttachment.getFsKey()));
					}
					// Converting DB attachment list in JMailSender Attachment list
					mailAttachment.createMimeAttachment();
					mailAttachmentList.add(mailAttachment);
				} catch (Exception e) {
					LoggerUtils.logOnConsole(ExceptionUtils.generateErrorLog(e));
					LoggerUtils.logOnConsole("Exception occurred while fetching Document (attachmentId) " + mailAttachment.getAttachmentSerialId());
				}
			}
		}
		return ValidationUtils.checkNonEmptyList(mailAttachmentList) ? mailAttachmentList : null;
	}
}
