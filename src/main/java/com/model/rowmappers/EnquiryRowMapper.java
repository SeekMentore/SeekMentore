package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Enquiry;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class EnquiryRowMapper implements RowMapper<Enquiry> {

	@Override
	public Enquiry mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Enquiry enquiryObject = new Enquiry();
		enquiryObject.setEnquirySerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("enquirySerialId"), String.class));
		enquiryObject.setCustomerSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("customerSerialId"), String.class));
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
		enquiryObject.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("tutorSerialId"), String.class));
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
		enquiryObject.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("enquiryEmail"), String.class));
		enquiryObject.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, enquiryObject.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(enquiryObject, row, rowNum);
		return enquiryObject;
	}

}
