package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.LogonTracker;
import com.utils.ExceptionUtils;

public class LogonTrackerRowMapper implements RowMapper<LogonTracker> {

	@Override
	public LogonTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final LogonTracker logonTracker = new LogonTracker();
		logonTracker.setLogonId(ExceptionUtils.exceptionHandlerForRowMapper(row, "LOGON_ID", Long.class));
		logonTracker.setLoginTime(ExceptionUtils.exceptionHandlerForRowMapper(row, "LOGIN_TIME", Timestamp.class));
		logonTracker.setLoginFrom(ExceptionUtils.exceptionHandlerForRowMapper(row, "LOGIN_FROM", String.class));
		logonTracker.setMachineIp(ExceptionUtils.exceptionHandlerForRowMapper(row, "MACHINE_IP", String.class));
		logonTracker.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_TYPE", String.class));
		logonTracker.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		return logonTracker;
	}

}
