package com.service.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.CommonsConstants;
import com.dao.ApplicationDao;
import com.model.ErrorPacket;
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
		applicationDao.updateWithPreparedQueryAndIndividualOrderedParams("INSERT INTO APP_ERROR_REPORT(OCCURED_AT, REQUEST_URI, ERROR_TRACE) VALUES(?, ?, ?)", new Object[] {errorPacket.getOccuredAt(), errorPacket.getRequestURI(), errorPacket.getErrorTrace()});
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
}
