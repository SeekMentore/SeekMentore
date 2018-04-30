package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.MailAttachment;
import com.utils.ExceptionUtils;

public class MailAttachmentRowMapper implements RowMapper<MailAttachment> {

	@Override
	public MailAttachment mapRow(ResultSet row, int rowNum) throws SQLException {
		final MailAttachment mailAttachmentObject = new MailAttachment();
		mailAttachmentObject.setAttachmentId(ExceptionUtils.exceptionHandlerForRowMapper(row, "ATTACHMENT_ID", Long.class));
		mailAttachmentObject.setMailId(ExceptionUtils.exceptionHandlerForRowMapper(row, "MAIL_ID", Long.class));
		mailAttachmentObject.setContent(ExceptionUtils.exceptionHandlerForRowMapper(row, "CONTENT", byte[].class));
		mailAttachmentObject.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, "FILENAME", String.class));
		mailAttachmentObject.setApplicationType(ExceptionUtils.exceptionHandlerForRowMapper(row, "APPLICATION_TYPE", String.class));
		return mailAttachmentObject;
	}

}
