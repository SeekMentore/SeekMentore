package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.PasswordChangeTracker;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class PasswordChangeTrackerRowMapper implements RowMapper<PasswordChangeTracker> {

	@Override
	public PasswordChangeTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final PasswordChangeTracker passwordChangeTracker = new PasswordChangeTracker();
		passwordChangeTracker.setPasswordChangeId(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("passwordChangeId"), Long.class));
		passwordChangeTracker.setChangeTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("changeTimeMillis"), Long.class));
		passwordChangeTracker.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("userType"), String.class));
		passwordChangeTracker.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("userId"), String.class));
		passwordChangeTracker.setEncryptedPasswordOld(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("encryptedPasswordOld"), String.class));
		passwordChangeTracker.setEncryptedPasswordNew(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("encryptedPasswordNew"), String.class));
		passwordChangeTracker.setUserName(ExceptionUtils.exceptionHandlerForRowMapper(row, passwordChangeTracker.resolveColumnNameForMapping("userName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(passwordChangeTracker, row, rowNum);
		return passwordChangeTracker;
	}

}
