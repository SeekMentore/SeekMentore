package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.SubscribedCustomer;
import com.utils.ExceptionUtils;

public class SubscribedCustomerRowMapper implements RowMapper<SubscribedCustomer> {

	@Override 
	public SubscribedCustomer mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
		subscribedCustomer.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, "CUSTOMER_ID", Long.class));
		subscribedCustomer.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, "NAME", String.class));
		subscribedCustomer.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, "CONTACT_NUMBER", String.class));
		subscribedCustomer.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMAIL_ID", String.class));
		subscribedCustomer.setEnquiryID(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENQUIRY_ID", Long.class));
		subscribedCustomer.setStudentGrades(ExceptionUtils.exceptionHandlerForRowMapper(row, "STUDENT_GRADE", String.class));
		subscribedCustomer.setInterestedSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, "SUBJECTS", String.class));
		subscribedCustomer.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, "LOCATION", String.class));
		subscribedCustomer.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADDITIONAL_DETAILS", String.class));
		subscribedCustomer.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADDRESS_DETAILS", String.class));
		subscribedCustomer.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, "RECORD_LAST_UPDATED", Timestamp.class));
		subscribedCustomer.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		subscribedCustomer.setEncryptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD", String.class));
		subscribedCustomer.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, "UPDATED_BY", String.class));
		return subscribedCustomer;
	}
}
