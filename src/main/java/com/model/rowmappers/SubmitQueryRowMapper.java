package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubmitQueryConstants;
import com.model.components.publicaccess.SubmitQuery;
import com.utils.ExceptionUtils;

public class SubmitQueryRowMapper implements RowMapper<SubmitQuery>, SubmitQueryConstants {

	@Override
	public SubmitQuery mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubmitQuery submitQuery = new SubmitQuery();
		submitQuery.setQueryId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUERY_ID, Long.class));
		submitQuery.setQueryRequestedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUERY_REQUESTED_DATE, Timestamp.class));
		submitQuery.setQueryStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUERY_STATUS, String.class));
		submitQuery.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_EMAIL_ID, String.class));
		submitQuery.setQueryDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUERY_DETAILS, String.class));
		submitQuery.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_CONTACTED, String.class));
		submitQuery.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_CONTACTED, String.class));
		submitQuery.setContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_DATE, Timestamp.class));
		submitQuery.setQueryResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUERY_RESPONSE, String.class));
		submitQuery.setNotAnswered(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_NOT_ANSWERED, String.class));
		submitQuery.setNotAnsweredReason(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_NOT_ANSWERED_REASON, String.class));
		submitQuery.setWhoNotAnswered(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_NOT_ANSWERED, String.class));
		submitQuery.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECORD_LAST_UPDATED, Timestamp.class));
		return submitQuery;
	}

}
