package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.MailAttachment;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class MailAttachmentRowMapper implements RowMapper<MailAttachment> {

	@Override
	public MailAttachment mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final MailAttachment mailAttachmentObject = new MailAttachment();
		mailAttachmentObject.setAttachmentSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("attachmentSerialId"), String.class));
		mailAttachmentObject.setMailSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("mailSerialId"), String.class));
		mailAttachmentObject.setContent(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("content"), byte[].class));
		mailAttachmentObject.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("filename"), String.class));
		mailAttachmentObject.setApplicationType(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("applicationType"), String.class));
		mailAttachmentObject.setIsFSStored(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("isFSStored"), String.class));
		mailAttachmentObject.setFsKey(ExceptionUtils.exceptionHandlerForRowMapper(row, mailAttachmentObject.resolveColumnNameForMapping("fsKey"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(mailAttachmentObject, row, rowNum);
		return mailAttachmentObject;
	}
}
