package com.model.rowmappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.EnquiryObject;
import com.utils.ExceptionUtils;

public class EnquiryObjectRowMapper implements RowMapper<EnquiryObject> {

	@Override
	public EnquiryObject mapRow(ResultSet row, int rowNum) throws SQLException {
		final EnquiryObject enquiryObject = new EnquiryObject();
		enquiryObject.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENQUIRY_ID", Long.class));
		enquiryObject.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, "CUSTOMER_ID", Long.class));
		enquiryObject.setSubject(ExceptionUtils.exceptionHandlerForRowMapper(row, "SUBJECT", String.class));
		enquiryObject.setGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, "GRADE", String.class));
		enquiryObject.setQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, "QUOTED_CLIENT_RATE", Integer.class));
		enquiryObject.setNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEGOTIATED_RATE_WITH_CLIENT", Integer.class));
		enquiryObject.setClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_NEGOTIATION_REMARKS", String.class));
		enquiryObject.setIsMapped(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_MAPPED", String.class));
		enquiryObject.setLastActionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "LAST_ACTION_DATE", Date.class));
		enquiryObject.setMatchStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, "MATCH_STATUS", String.class));
		enquiryObject.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_ID", Long.class));
		enquiryObject.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_REMARKS", String.class));
		enquiryObject.setLocationDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "LOCATION_DETAILS", String.class));
		enquiryObject.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADDRESS_DETAILS", String.class));
		enquiryObject.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADDITIONAL_DETAILS", String.class));
		enquiryObject.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHO_ACTED", String.class));
		return enquiryObject;
	}

}
