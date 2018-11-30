package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Enquiry;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class EnquiryRowMapper implements RowMapper<Enquiry> {

	@Override
	public Enquiry mapRow(ResultSet row, int rowNum) throws SQLException {
		final Enquiry enquiryObject = new Enquiry();
		enquiryObject.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("enquiryId"), Long.class));
		enquiryObject.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("customerId"), Long.class));
		enquiryObject.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("customerName"), String.class));
		enquiryObject.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("customerEmail"), String.class));
		enquiryObject.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("customerContactNumber"), String.class));
		enquiryObject.setSubject(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("subject"), String.class));
		enquiryObject.setGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("grade"), String.class));
		enquiryObject.setQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("quotedClientRate"), Integer.class));
		enquiryObject.setNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("negotiatedRateWithClient"), Integer.class));
		enquiryObject.setClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("clientNegotiationRemarks"), String.class));
		enquiryObject.setIsMapped(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("isMapped"), String.class));
		enquiryObject.setLastActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("lastActionDateMillis"), Long.class));
		enquiryObject.setMatchStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("matchStatus"), String.class));
		enquiryObject.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("tutorId"), Long.class));
		enquiryObject.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("tutorName"), String.class));
		enquiryObject.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("tutorEmail"), String.class));
		enquiryObject.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		enquiryObject.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("adminRemarks"), String.class));
		enquiryObject.setLocationDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("locationDetails"), String.class));
		enquiryObject.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("addressDetails"), String.class));
		enquiryObject.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("additionalDetails"), String.class));
		enquiryObject.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("whoActed"), String.class));
		enquiryObject.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("whoActedName"), String.class));
		enquiryObject.setPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("preferredTeachingType"), String.class));
		enquiryObject.setEntryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("entryDateMillis"), Long.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(enquiryObject, row, rowNum);
		return enquiryObject;
	}

}
