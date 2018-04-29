package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.MailAttachment;

public class MailAttachmentRowMapper implements RowMapper<MailAttachment> {

	@Override
	public MailAttachment mapRow(ResultSet row, int rowNum) throws SQLException {
		final MailAttachment mailAttachmentObject = new MailAttachment();
		mailAttachmentObject.setAttachmentId(row.getLong("ATTACHMENT_ID"));
		mailAttachmentObject.setMailId(row.getLong("MAIL_ID"));
		mailAttachmentObject.setContent(row.getBytes("CONTENT"));
		mailAttachmentObject.setFilename(row.getString("FILENAME"));
		mailAttachmentObject.setApplicationType(row.getString("APPLICATION_TYPE"));
		return mailAttachmentObject;
	}

}
