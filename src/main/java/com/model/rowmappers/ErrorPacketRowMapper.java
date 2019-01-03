package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.ErrorPacket;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class ErrorPacketRowMapper implements RowMapper<ErrorPacket> {

	@Override
	public ErrorPacket mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final ErrorPacket errorPacket = new ErrorPacket();
		errorPacket.setErrorId(ExceptionUtils.exceptionHandlerForRowMapper(row, errorPacket.resolveColumnNameForMapping("errorId"), Long.class));
		errorPacket.setOccuredAtMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, errorPacket.resolveColumnNameForMapping("occuredAtMillis"), Long.class));
		errorPacket.setRequestURI(ExceptionUtils.exceptionHandlerForRowMapper(row, errorPacket.resolveColumnNameForMapping("requestURI"), String.class));
		errorPacket.setErrorTrace(ExceptionUtils.exceptionHandlerForRowMapper(row, errorPacket.resolveColumnNameForMapping("errorTrace"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(errorPacket, row, rowNum);
		return errorPacket;
	}

}
