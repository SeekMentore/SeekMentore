package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.CommonsConstants;
import com.dao.ApplicationDao;
import com.model.ErrorPacket;
import com.model.components.commons.SelectLookup;
import com.model.mail.EmailTemplate;
import com.model.rowmappers.EmailTemplateRowMapper;
import com.service.QueryMapperService;
import com.utils.ExceptionUtils;
import com.utils.MailUtils;
import com.utils.VelocityUtils;

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
	
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		return applicationLookupDataService.getSelectLookupList(selectLookUpTable);
	}
	
	public SelectLookup getSelectLookupItem(final String selectLookUpTable, final String value) {
		return applicationLookupDataService.getSelectLookupItem(selectLookUpTable, value);
	}
	
	public EmailTemplate getEmailTemplateFromLookupValue(final String value) throws Exception {
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
			MailUtils.sendErrorMessageEmail(VelocityUtils.createEmailFromHTMLContent(errorPacket.getRequestURI() + LINE_BREAK + LINE_BREAK + errorPacket.getErrorTrace()), null);
		} catch (Exception e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
	}
}
