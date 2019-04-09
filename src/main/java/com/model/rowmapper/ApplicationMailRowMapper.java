package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.ApplicationMail;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class ApplicationMailRowMapper implements RowMapper<ApplicationMail> {

	@Override
	public ApplicationMail mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final ApplicationMail mailObject = new ApplicationMail();
		mailObject.setMailSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("mailSerialId"), String.class));
		mailObject.setMailType(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("mailType"), String.class));
		mailObject.setEntryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("entryDateMillis"), Long.class));
		mailObject.setFromAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("fromAddress"), String.class));
		mailObject.setToAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("toAddress"), String.class));
		mailObject.setCcAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("ccAddress"), String.class));
		mailObject.setBccAddress(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("bccAddress"), String.class));
		mailObject.setSubjectContent(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("subjectContent"), String.class));
		mailObject.setMessageContent(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("messageContent"), String.class));
		mailObject.setMailSent(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("mailSent"), String.class));
		mailObject.setSendDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("sendDateMillis"), Long.class));
		mailObject.setErrorOccuredWhileSending(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("errorOccuredWhileSending"), String.class));
		mailObject.setErrorDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("errorDateMillis"), Long.class));
		mailObject.setErrorTrace(ExceptionUtils.exceptionHandlerForRowMapper(row, mailObject.resolveColumnNameForMapping("errorTrace"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(mailObject, row, rowNum);
		return mailObject;
	}
}
