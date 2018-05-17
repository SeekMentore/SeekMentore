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
import com.model.Employee;
import com.model.ErrorPacket;
import com.model.User;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.commons.SelectLookup;
import com.model.mail.ApplicationMail;
import com.model.mail.MailAttachment;
import com.model.rowmappers.ApplicationMailRowMapper;
import com.model.rowmappers.EmployeeRowMapper;
import com.model.rowmappers.MailAttachmentRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.SelectLookupRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.model.rowmappers.UserRowMapper;
import com.utils.ExceptionUtils;
import com.utils.MailUtils;
import com.utils.ValidationUtils;

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
		applicationDao.executeUpdate("INSERT INTO APP_ERROR_REPORT(OCCURED_AT, REQUEST_URI, ERROR_TRACE) VALUES(:occurredAt, :requestURI, :errorTrace)", paramsMap);
		try {
			MailUtils.sendErrorMessageEmail(errorPacket.getRequestURI() + LINE_BREAK + LINE_BREAK + errorPacket.getErrorTrace(), null);
		} catch (Exception e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
	}
	
	@Transactional
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		return applicationDao.findAllWithoutParams("SELECT * FROM SELECT_LOOKUP_TABLE_NAME WHERE HIDDEN IS NULL ORDER BY ORDER_OF_CATEGORY, ORDER_IN_CATEGORY".replaceAll("SELECT_LOOKUP_TABLE_NAME", selectLookUpTable), new SelectLookupRowMapper());
	}
	
	@Transactional
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
	
	@Transactional
	public SelectLookup getSelectLookupEntry(final String selectLookupTable, final String value) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("value", value);
		return applicationDao.find("SELECT LABEL FROM SELECT_LOOKUP_TABLE where VALUE = :value".replaceAll("SELECT_LOOKUP_TABLE", selectLookupTable), paramsMap, new SelectLookupRowMapper());
	}
	
	@Transactional
	public User getUserFromEmployeeDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("SELECT * FROM EMPLOYEE WHERE LOWER(USER_ID) = :userId", paramsMap, new UserRowMapper());
		}
		return null;
	}
	
	@Transactional
	public Employee getEmployeeFromDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("SELECT * FROM EMPLOYEE WHERE LOWER(USER_ID) = :userId", paramsMap, new EmployeeRowMapper());
		}
		return null;
	}
	
	@Transactional
	public User getUserFromTutorDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("Select R.*, 'Tutor' USER_TYPE from REGISTERED_TUTOR R WHERE USER_ID = :userId", paramsMap, new UserRowMapper());
		}
		return null;
	}
	
	@Transactional
	public RegisteredTutor getTutorFromDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("Select R.*, 'Tutor' USER_TYPE from REGISTERED_TUTOR R WHERE USER_ID = :userId", paramsMap, new RegisteredTutorRowMapper());
		}
		return null;
	}
	
	@Transactional
	public User getUserFromSubscribedCustomerDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("Select R.*, 'Customer' USER_TYPE from SUBSCRIBED_CUSTOMER R WHERE USER_ID = :userId", paramsMap, new UserRowMapper());
		}
		return null;
	}
	
	public SubscribedCustomer getSubscribedCustomerFromDbUsingUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find("Select R.*, 'Customer' USER_TYPE from SUBSCRIBED_CUSTOMER R WHERE USER_ID = :userId", paramsMap, new SubscribedCustomerRowMapper());
		}
		return null;
	}
	
	@Transactional
	public List<SelectLookup> getSelectLookupEntryList(final String selectLookupTable, final Object[] paramlist) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		for (int i = 0; i< paramlist.length; i++) {
			paramsMap.put(String.valueOf(i), paramlist[i]);
		}
		return applicationDao.findAll("SELECT * FROM SELECT_LOOKUP_TABLE where VALUE IN (QUESTION_MARK_PLACE_HOLDER)".replaceAll("SELECT_LOOKUP_TABLE", selectLookupTable).replaceAll("QUESTION_MARK_PLACE_HOLDER", generatePlaceHolderMarkAsPerParamNumber(paramlist)), paramsMap, new SelectLookupRowMapper());
	}
	
	public String getNameOfUserFromUserId(final String userId) throws DataAccessException, InstantiationException, IllegalAccessException {
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(userId)) {
			final User user = getUserFromEmployeeDbUsingUserId(userId);
			if (null != user) {
				return user.getName();
			}
		}
		return EMPTY_STRING;
	}
	
	public String preapreLookupLabelString(final String selectLookupTable, final String value, final boolean multiSelect, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder multiLineString = new StringBuilder(EMPTY_STRING);
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(value)) {
			if (multiSelect) {
				final List<SelectLookup> selectLookupList = getSelectLookupEntryList(selectLookupTable, value.split(SEMICOLON));
				for(final SelectLookup selectLookup : selectLookupList) {
					multiLineString.append(selectLookup.getLabel());
					if (ValidationUtils.validatePlainNotNullAndEmptyTextString(selectLookup.getDescription())) {
						multiLineString.append(WHITESPACE).append(selectLookup.getDescription());
					}
					multiLineString.append(delimiter);
				}
			} else {
				multiLineString.append(getSelectLookupEntry(selectLookupTable, value).getLabel());
			}
		}
		return multiLineString.toString();
	}
	
	private String generatePlaceHolderMarkAsPerParamNumber(final Object[] paramlist) {
		final StringBuilder questionMarks = new StringBuilder(EMPTY_STRING);
		for(int i = 0; i< paramlist.length; i++) {
			if (i < paramlist.length - 1)
				questionMarks.append(":"+i+",");
			else 
				questionMarks.append(":"+i);
		}
		return questionMarks.toString();
	}
}
