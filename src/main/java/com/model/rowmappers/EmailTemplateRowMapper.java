package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.EmailTemplate;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class EmailTemplateRowMapper implements RowMapper<EmailTemplate> {

	@Override
	public EmailTemplate mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setEmailTemplateId(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("emailTemplateId"), Long.class));
		emailTemplate.setEmailTemplateLookupValue(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("emailTemplateLookupValue"), String.class));
		emailTemplate.setTo(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("to"), String.class));
		emailTemplate.setCc(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("cc"), String.class));
		emailTemplate.setBcc(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("bcc"), String.class));
		emailTemplate.setSubject(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("subject"), String.class));
		emailTemplate.setBody(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("body"), String.class));
		emailTemplate.setAddedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("addedBy"), String.class));
		emailTemplate.setAddedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("addedMillis"), Long.class));
		emailTemplate.setLastUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("lastUpdatedBy"), String.class));
		emailTemplate.setLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, emailTemplate.resolveColumnNameForMapping("lastUpdatedMillis"), Long.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(emailTemplate, row, rowNum);
		return emailTemplate;
	}
}
