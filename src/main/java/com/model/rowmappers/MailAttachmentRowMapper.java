package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.MailAttachment;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class MailAttachmentRowMapper implements RowMapper<MailAttachment> {

	@Override
	public MailAttachment mapRow(ResultSet row, int rowNum) throws SQLException {
		final MailAttachment mailAttachmentObject = new MailAttachment();
		mailAttachmentObject.setAttachmentId(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("attachmentId"), Long.class));
		mailAttachmentObject.setMailId(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("mailId"), Long.class));
		mailAttachmentObject.setContent(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("content"), byte[].class));
		mailAttachmentObject.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("filename"), String.class));
		mailAttachmentObject.setApplicationType(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("applicationType"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(mailAttachmentObject, row, rowNum);
		return mailAttachmentObject;
	}
}
