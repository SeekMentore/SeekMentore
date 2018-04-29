package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.ApplicationMail;
import com.utils.ExceptionUtils;

public class ApplicationMailRowMapper implements RowMapper<ApplicationMail> {

	@Override
	public ApplicationMail mapRow(ResultSet row, int rowNum) throws SQLException {
		final ApplicationMail mailObject = new ApplicationMail();
		mailObject.setMailId(ExceptionUtils.exceptionHandlerForRowMapper(row, "MAIL_ID", Long.class));
		mailObject.setEntryDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENTRY_DATE", Timestamp.class));
		mailObject.setFromAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, "FROM_ADDRESS", String.class));
		mailObject.setToAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, "TO_ADDRESS", String.class));
		mailObject.setCcAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, "CC_ADDRESS", String.class));
		mailObject.setBccAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, "BCC_ADDRESS", String.class));
		mailObject.setSubjectContent(ExceptionUtils.exceptionHandlerForRowMapper(row, "SUBJECT_CONTENT", String.class));
		mailObject.setMessageContent(ExceptionUtils.exceptionHandlerForRowMapper(row, "MESSAGE_CONTENT", String.class));
		mailObject.setSendDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "SEND_DATE", Timestamp.class));
		mailObject.setMailType(ExceptionUtils.exceptionHandlerForRowMapper(row, "MAIL_TYPE", String.class));
		mailObject.setMailSent(ExceptionUtils.exceptionHandlerForRowMapper(row, "MAIL_SENT", String.class));
		mailObject.setErrorDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "ERROR_DATE", Timestamp.class));
		mailObject.setErrorOccuredWhileSending(ExceptionUtils.exceptionHandlerForRowMapper(row, "ERROR_OCCURED_WHILE_SENDING", String.class));
		mailObject.setErrorTrace(ExceptionUtils.exceptionHandlerForRowMapper(row, "ERROR_TRACE", String.class));
		return mailObject;
	}
}
