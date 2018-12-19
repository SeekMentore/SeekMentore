package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubmitQueryConstants;
import com.model.components.publicaccess.SubmitQuery;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.PublicApplicationUtils;

public class SubmitQueryRowMapper implements RowMapper<SubmitQuery>, SubmitQueryConstants {

	@Override
	public SubmitQuery mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubmitQuery submitQuery = new SubmitQuery();
		submitQuery.setQueryId(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("queryId"), Long.class));
		submitQuery.setQueryRequestedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("queryRequestedDateMillis"), Long.class));
		submitQuery.setQueryStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("queryStatus"), String.class));
		submitQuery.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("emailId"), String.class));
		submitQuery.setRegisteredTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("registeredTutor"), String.class));
		submitQuery.setSubscribedCustomer(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("subscribedCustomer"), String.class));
		submitQuery.setQueryDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("queryDetails"), String.class));
		submitQuery.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("isContacted"), String.class));
		submitQuery.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("whoContacted"), String.class));
		submitQuery.setContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("contactedDateMillis"), Long.class));
		submitQuery.setQueryResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("queryResponse"), String.class));
		submitQuery.setNotAnswered(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("notAnswered"), String.class));
		submitQuery.setNotAnsweredReason(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("notAnsweredReason"), String.class));
		submitQuery.setWhoNotAnswered(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("whoNotAnswered"), String.class));
		submitQuery.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		submitQuery.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("updatedBy"), String.class));
		submitQuery.setWhoContactedName(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("whoContactedName"), String.class));
		submitQuery.setWhoNotAnsweredName(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("whoNotAnsweredName"), String.class));
		submitQuery.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, submitQuery.resolveColumnNameForMapping("updatedByName"), String.class));
		PublicApplicationUtils.mapMigrationColumnsForRecords(submitQuery, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(submitQuery, row, rowNum);
		return submitQuery;
	}

}
