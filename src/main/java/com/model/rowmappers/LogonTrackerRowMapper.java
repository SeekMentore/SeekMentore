package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.LogonTracker;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class LogonTrackerRowMapper implements RowMapper<LogonTracker> {

	@Override
	public LogonTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final LogonTracker logonTracker = new LogonTracker();
		logonTracker.setLogonId(ExceptionUtils.exceptionHandlerForRowMapper(row, logonTracker.resolveColumnNameForMapping("logonId"), Long.class));
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
