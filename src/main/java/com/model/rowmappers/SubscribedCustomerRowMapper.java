package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.CustomerConstants;
import com.model.components.SubscribedCustomer;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class SubscribedCustomerRowMapper implements RowMapper<SubscribedCustomer>, CustomerConstants {

	@Override 
	public SubscribedCustomer mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
		subscribedCustomer.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("customerId"), Long.class));
		subscribedCustomer.setFindTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("findTutorId"), Long.class));
		subscribedCustomer.setStudentGrades(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("studentGrades"), String.class));
		subscribedCustomer.setInterestedSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("interestedSubjects"), String.class));
		subscribedCustomer.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("location"), String.class));
		subscribedCustomer.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("additionalDetails"), String.class));
		subscribedCustomer.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("addressDetails"), String.class));
		subscribedCustomer.setBlacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("blacklistedDateMillis"), Long.class));
		subscribedCustomer.setIsBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("isBlacklisted"), String.class));
		subscribedCustomer.setBlacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("blacklistedRemarks"), String.class));
		subscribedCustomer.setWhoBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("whoBlacklisted"), String.class));
		subscribedCustomer.setWhoBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("whoBlacklistedName"), String.class));
		subscribedCustomer.setUnblacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("unblacklistedDateMillis"), Long.class));
		subscribedCustomer.setUnblacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("unblacklistedRemarks"), String.class));
		subscribedCustomer.setWhoUnBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("whoUnBlacklisted"), String.class));
		subscribedCustomer.setWhoUnBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("whoUnBlacklistedName"), String.class));
		UserUtils.mapUserPseudoColumnsForRecords(subscribedCustomer, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscribedCustomer, row, rowNum);
		return subscribedCustomer;
	}
}
