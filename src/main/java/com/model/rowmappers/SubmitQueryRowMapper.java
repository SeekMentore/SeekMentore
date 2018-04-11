package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubmitQueryConstants;
import com.model.components.publicaccess.SubmitQuery;

public class SubmitQueryRowMapper implements RowMapper<SubmitQuery>, SubmitQueryConstants {

	@Override
	public SubmitQuery mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubmitQuery submitQuery = new SubmitQuery();
		submitQuery.setQueryId(row.getLong(COLUMN_NAME_QUERY_ID));
		submitQuery.setQueryRequestedDate(row.getDate(COLUMN_NAME_QUERY_REQUESTED_DATE));
		submitQuery.setQueryStatus(row.getString(COLUMN_NAME_QUERY_STATUS));
		submitQuery.setEmailId(row.getString(COLUMN_NAME_EMAIL_ID));
		submitQuery.setQueryDetails(row.getString(COLUMN_NAME_QUERY_DETAILS));
		submitQuery.setIsContacted(row.getString(COLUMN_NAME_IS_CONTACTED));
		submitQuery.setWhoContacted(row.getString(COLUMN_NAME_WHO_CONTACTED));
		submitQuery.setContactedDate(row.getDate(COLUMN_NAME_CONTACTED_DATE));
		submitQuery.setQueryResponse(row.getString(COLUMN_NAME_QUERY_RESPONSE));
		submitQuery.setNotAnswered(row.getString(COLUMN_NAME_NOT_ANSWERED));
		submitQuery.setNotAnsweredReason(row.getString(COLUMN_NAME_NOT_ANSWERED_REASON));
		submitQuery.setWhoNotAnswered(row.getString(COLUMN_NAME_WHO_NOT_ANSWERED));
		submitQuery.setRecordLastUpdated(row.getDate(COLUMN_NAME_RECORD_LAST_UPDATED));
		return submitQuery;
	}

}
