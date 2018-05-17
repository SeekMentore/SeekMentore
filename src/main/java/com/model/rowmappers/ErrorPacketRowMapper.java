package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.ErrorPacket;
import com.utils.ExceptionUtils;

public class ErrorPacketRowMapper implements RowMapper<ErrorPacket> {

	@Override
	public ErrorPacket mapRow(ResultSet row, int rowNum) throws SQLException {
		final ErrorPacket errorPacket = new ErrorPacket();
		errorPacket.setErrorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "ERROR_ID", Long.class));
		errorPacket.setOccuredAt(ExceptionUtils.exceptionHandlerForRowMapper(row, "OCCURED_AT", Timestamp.class));
		errorPacket.setRequestURI(ExceptionUtils.exceptionHandlerForRowMapper(row, "REQUEST_URI", String.class));
		errorPacket.setErrorTrace(ExceptionUtils.exceptionHandlerForRowMapper(row, "ERROR_TRACE", String.class));
		return errorPacket;
	}

}
