package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.PasswordChangeTracker;
import com.utils.ExceptionUtils;

public class PasswordChangeTrackerRowMapper implements RowMapper<PasswordChangeTracker> {

	@Override
	public PasswordChangeTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final PasswordChangeTracker passwordChangeTracker = new PasswordChangeTracker();
		passwordChangeTracker.setPasswordChangeId(ExceptionUtils.exceptionHandlerForRowMapper(row, "PASSWORD_CHANGE_ID", Long.class));
		passwordChangeTracker.setChangeTime(ExceptionUtils.exceptionHandlerForRowMapper(row, "CHANGE_TIME", Timestamp.class));
		passwordChangeTracker.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_TYPE", String.class));
		passwordChangeTracker.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		passwordChangeTracker.setEncryptedPasswordOld(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD_OLD", String.class));
		passwordChangeTracker.setEncryptedPasswordNew(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD_NEW", String.class));
		return passwordChangeTracker;
	}

}
