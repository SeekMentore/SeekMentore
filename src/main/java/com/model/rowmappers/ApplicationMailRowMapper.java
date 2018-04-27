package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.mail.ApplicationMail;

public class ApplicationMailRowMapper implements RowMapper<ApplicationMail> {

	@Override
	public ApplicationMail mapRow(ResultSet row, int rowNum) throws SQLException {
		final ApplicationMail mailObject = new ApplicationMail();
		mailObject.setMailId(row.getLong("MAIL_ID"));
		mailObject.setEntryDate(row.getTimestamp("ENTRY_DATE"));
		mailObject.setFromAddress(row.getString("FROM_ADDRESS"));
		mailObject.setToAddress(row.getString("TO_ADDRESS"));
		mailObject.setCcAddress(row.getString("CC_ADDRESS"));
		mailObject.setBccAddress(row.getString("BCC_ADDRESS"));
		mailObject.setSubjectContent(row.getString("SUBJECT_CONTENT"));
		mailObject.setMessageContent(row.getString("MESSAGE_CONTENT"));
		mailObject.setSendDate(row.getTimestamp("SEND_DATE"));
		mailObject.setMailType(row.getString("MAIL_TYPE"));
		mailObject.setMailSent(row.getString("MAIL_SENT"));
		mailObject.setErrorDate(row.getTimestamp("ERROR_DATE"));
		mailObject.setErrorOccuredWhileSending(row.getString("ERROR_OCCURED_WHILE_SENDING"));
		mailObject.setErrorTrace(row.getString("ERROR_TRACE"));
		return mailObject;
	}

}
