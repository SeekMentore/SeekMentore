package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.LoginTracker;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class LoginTrackerRowMapper implements RowMapper<LoginTracker> {

	@Override
	public LoginTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final LoginTracker logonTracker = new LoginTracker();
		logonTracker.setLoginSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("loginSerialId"), String.class));
		logonTracker.setLoginTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("loginTimeMillis"), Long.class));
		logonTracker.setLoginFrom(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("loginFrom"), String.class));
		logonTracker.setMachineIp(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("machineIp"), String.class));
		logonTracker.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("userType"), String.class));
		logonTracker.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("userId"), String.class));
		logonTracker.setUserName(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("userName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(logonTracker, row, rowNum);
		return logonTracker;
	}

}
