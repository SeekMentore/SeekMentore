package com.service.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.components.CommonsConstants;
import com.dao.ApplicationDao;
import com.model.ErrorPacket;
import com.model.components.commons.SelectLookup;
import com.model.mail.ApplicationMail;
import com.model.mail.EmailTemplate;
import com.model.mail.MailAttachment;
import com.model.rowmappers.ApplicationMailRowMapper;
import com.model.rowmappers.EmailTemplateRowMapper;
import com.model.rowmappers.MailAttachmentRowMapper;
import com.service.QueryMapperService;
import com.utils.ExceptionUtils;
import com.utils.MailUtils;

@Service(BeanConstants.BEAN_NAME_COMMONS_SERVICE)
public class CommonsService implements CommonsConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient ApplicationLookupDataService applicationLookupDataService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public List<ApplicationMail> getPedingEmailList(final int numberOfRecords) {
		return applicationDao.findAllWithoutParams("SELECT MAIL_ID, MAIL_TYPE, FROM_ADDRESS, TO_ADDRESS, CC_ADDRESS, BCC_ADDRESS, SUBJECT_CONTENT, MESSAGE_CONTENT FROM MAIL_QUEUE WHERE MAIL_SENT = 'N' ORDER BY ENTRY_DATE", new ApplicationMailRowMapper());
	}
	
	public List<MailAttachment> getAttachments(final long mailId) throws IOException, MessagingException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("mailId", mailId);
		final List<MailAttachment> attachments = applicationDao.findAll("SELECT * FROM MAIL_ATTACHMENTS WHERE MAIL_ID = :mailId", paramsMap, new MailAttachmentRowMapper());
		List<MailAttachment> mailAttachments = null;
		if (null != attachments && !attachments.isEmpty()) {
			// Converting DB attachment list in JMailSender Attachment list
			mailAttachments = new ArrayList<MailAttachment>();
			for (final MailAttachment attachment : attachments) {
				mailAttachments.add(new MailAttachment(attachment.getFilename(), attachment.getContent(), FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			}
		}
		return mailAttachments;
	}
	
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		return applicationLookupDataService.getSelectLookupList(selectLookUpTable);
	}
	
	public SelectLookup getSelectLookupItem(final String selectLookUpTable, final String value) {
		return applicationLookupDataService.getSelectLookupItem(selectLookUpTable, value);
	}
	
	public EmailTemplate getEmailTemplateFromLookupValue(final String value) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("emailTemplateLookupValue", value);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("mail", "selectEmailTemplate"));
		query.append(queryMapperService.getQuerySQL("mail", "emailTemplateLookupValueFilter"));
		return applicationDao.find(query.toString(), params, new EmailTemplateRowMapper());
	}
	
	@Transactional
	public void feedErrorRecord(final ErrorPacket errorPacket) {
		try {
			applicationDao.executeUpdateWithQueryMapper("error", "insertErrorPacket", errorPacket);
			MailUtils.sendErrorMessageEmail(errorPacket.getRequestURI() + LINE_BREAK + LINE_BREAK + errorPacket.getErrorTrace(), null);
		} catch (Exception e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
	}
}
