package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.CustomerConstants;
import com.model.components.SubscribedCustomer;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class SubscribedCustomerRowMapper implements RowMapper<SubscribedCustomer>, CustomerConstants {

	@Override 
	public SubscribedCustomer mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
		subscribedCustomer.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("customerId"), Long.class));
		subscribedCustomer.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("name"), String.class));
		subscribedCustomer.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("contactNumber"), String.class));
		subscribedCustomer.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("emailId"), String.class));
		subscribedCustomer.setEnquiryID(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("enquiryID"), Long.class));
		subscribedCustomer.setStudentGrades(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("studentGrades"), String.class));
		subscribedCustomer.setInterestedSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("interestedSubjects"), String.class));
		subscribedCustomer.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("location"), String.class));
		subscribedCustomer.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("additionalDetails"), String.class));
		subscribedCustomer.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("addressDetails"), String.class));
		subscribedCustomer.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		subscribedCustomer.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("userId"), String.class));
		subscribedCustomer.setEncryptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("encryptedPassword"), String.class));
		subscribedCustomer.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("updatedBy"), String.class));
		subscribedCustomer.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomer.resolveColumnNameForMapping("updatedByName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscribedCustomer, row, rowNum);
		return subscribedCustomer;
	}
}
