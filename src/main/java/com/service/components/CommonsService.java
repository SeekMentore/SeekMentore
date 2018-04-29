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
import com.model.User;
import com.model.components.commons.SelectLookup;
import com.model.mail.ApplicationMail;
import com.utils.ExceptionUtils;
import com.utils.MailUtils;

@Service(BeanConstants.BEAN_NAME_COMMONS_SERVICE)
public class CommonsService implements CommonsConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void feedErrorRecord(final ErrorPacket errorPacket) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("occurredAt", errorPacket.getOccuredAt());
		paramsMap.put("requestURI", errorPacket.getRequestURI());
		paramsMap.put("errorTrace", errorPacket.getErrorTrace());
		applicationDao.insertOrUpdateWithParams("INSERT INTO APP_ERROR_REPORT(OCCURED_AT, REQUEST_URI, ERROR_TRACE) VALUES(:occurredAt, :requestURI, :errorTrace)", paramsMap);
		try {
			MailUtils.sendErrorMessageEmail(errorPacket.getRequestURI() + LINE_BREAK + LINE_BREAK + errorPacket.getErrorTrace(), null);
		} catch (Exception e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
	}
	
	@Transactional
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		return applicationDao.findAllWithoutParams("SELECT * FROM SELECT_LOOKUP_TABLE_NAME WHERE HIDDEN IS NULL ORDER BY ORDER_OF_CATEGORY, ORDER_IN_CATEGORY".replaceAll("SELECT_LOOKUP_TABLE_NAME", selectLookUpTable), SelectLookup.class);
	}
	
	@Transactional
	public List<ApplicationMail> getPedingEmailList(final int numberOfRecords) {
		return applicationDao.findAllWithoutParams("SELECT MAIL_ID, MAIL_TYPE, FROM_ADDRESS, TO_ADDRESS, CC_ADDRESS, BCC_ADDRESS, SUBJECT_CONTENT, MESSAGE_CONTENT FROM MAIL_QUEUE WHERE MAIL_SENT = 'N' ORDER BY ENTRY_DATE", ApplicationMail.class);
	}
	
	@Transactional
	public SelectLookup getSelectLookupEntry(final String selectLookupTable, final String value) {
		return applicationDao.find("SELECT LABEL FROM SELECT_LOOKUP_TABLE where VALUE = ?".replaceAll("SELECT_LOOKUP_TABLE", selectLookupTable), new Object[] {value}, SelectLookup.class);
	}
	
	@Transactional
	public User getUserFromDbUsingUserId(final String userId) {
		if (null != userId) {
			return applicationDao.find("SELECT * FROM EMPLOYEE WHERE LOWER(USER_ID) = ?", new Object[] {userId.toLowerCase()}, User.class);
		}
		return null;
	}
	
	@Transactional
	public List<SelectLookup> getSelectLookupEntryList(final String selectLookupTable, final Object[] paramlist) {
		return applicationDao.findAll("SELECT * FROM SELECT_LOOKUP_TABLE where VALUE IN (QUESTION_MARK_PLACE_HOLDER)".replaceAll("SELECT_LOOKUP_TABLE", selectLookupTable).replaceAll("QUESTION_MARK_PLACE_HOLDER", generateQuestionMarksAsPerParamNumber(paramlist)), paramlist, SelectLookup.class);
	}
	
	private String generateQuestionMarksAsPerParamNumber(final Object[] paramlist) {
		final StringBuilder questionMarks = new StringBuilder(EMPTY_STRING);
		for(int i = 0; i< paramlist.length; i++) {
			if (i < paramlist.length - 1)
				questionMarks.append("?,");
			else 
				questionMarks.append("?");
		}
		return questionMarks.toString();
	}

	public String testNamedParamter(String query, final Object[] params, final Map<String, Object> paramsMap, String switcher) {
		return null;
	}
}
