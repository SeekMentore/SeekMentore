package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.ErrorPacket;

public class ErrorPacketRowMapper implements RowMapper<ErrorPacket> {

	@Override
	public ErrorPacket mapRow(ResultSet row, int rowNum) throws SQLException {
		final ErrorPacket errorPacket = new ErrorPacket();
		errorPacket.setErrorId(row.getLong("ERROR_ID"));
		errorPacket.setOccuredAt(row.getTimestamp("OCCURED_AT"));
		errorPacket.setRequestURI(row.getString("REQUEST_URI"));
		errorPacket.setErrorTrace(row.getString("ERROR_TRACE"));
		return errorPacket;
	}

}
