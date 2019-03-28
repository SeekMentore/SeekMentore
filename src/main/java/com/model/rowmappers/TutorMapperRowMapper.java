package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.TutorMapper;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class TutorMapperRowMapper implements RowMapper<TutorMapper> {

	@Override
	public TutorMapper mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final TutorMapper tutorMapperObject = new TutorMapper();
		tutorMapperObject.setTutorMapperSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorMapperSerialId"), String.class));
		tutorMapperObject.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryId"), Long.class));
		tutorMapperObject.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquirySubject"), String.class));
		tutorMapperObject.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryGrade"), String.class));
		tutorMapperObject.setEnquiryLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryLocation"), String.class));
		tutorMapperObject.setEnquiryAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryAddressDetails"), String.class));
		tutorMapperObject.setEnquiryAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryAdditionalDetails"), String.class));
		tutorMapperObject.setEnquiryPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryPreferredTeachingType"), String.class));
		tutorMapperObject.setEnquiryQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryQuotedClientRate"), Integer.class));
		tutorMapperObject.setEnquiryNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryNegotiatedRateWithClient"), Integer.class));
		tutorMapperObject.setEnquiryClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryClientNegotiationRemarks"), String.class));
		tutorMapperObject.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("customerId"), Long.class));
		tutorMapperObject.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("customerName"), String.class));
		tutorMapperObject.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("customerEmail"), String.class));
		tutorMapperObject.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("customerContactNumber"), String.class));
		tutorMapperObject.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorId"), Long.class));
		tutorMapperObject.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorName"), String.class));
		tutorMapperObject.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorEmail"), String.class));
		tutorMapperObject.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		tutorMapperObject.setQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("quotedTutorRate"), Integer.class));
		tutorMapperObject.setNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("negotiatedRateWithTutor"), Integer.class));
		tutorMapperObject.setTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorNegotiationRemarks"), String.class));
		tutorMapperObject.setIsTutorContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isTutorContacted"), String.class));
		tutorMapperObject.setTutorContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorContactedDateMillis"), Long.class));
		tutorMapperObject.setIsTutorAgreed(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isTutorAgreed"), String.class));
		tutorMapperObject.setIsTutorRejectionValid(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isTutorRejectionValid"), String.class));
		tutorMapperObject.setAdminTutorRejectionValidityResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminTutorRejectionValidityResponse"), String.class));
		tutorMapperObject.setTutorResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("tutorResponse"), String.class));
		tutorMapperObject.setAdminRemarksForTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminRemarksForTutor"), String.class));
		tutorMapperObject.setIsClientContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isClientContacted"), String.class));
		tutorMapperObject.setClientContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("clientContactedDateMillis"), Long.class));
		tutorMapperObject.setIsClientAgreed(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isClientAgreed"), String.class));
		tutorMapperObject.setClientResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("clientResponse"), String.class));
		tutorMapperObject.setIsClientRejectionValid(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isClientRejectionValid"), String.class));
		tutorMapperObject.setAdminClientRejectionValidityResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminClientRejectionValidityResponse"), String.class));
		tutorMapperObject.setAdminRemarksForClient(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminRemarksForClient"), String.class));
		tutorMapperObject.setAdminActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminActionDateMillis"), Long.class));
		tutorMapperObject.setAdminActionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("adminActionRemarks"), String.class));
		tutorMapperObject.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("whoActed"), String.class));
		tutorMapperObject.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("whoActedName"), String.class));
		tutorMapperObject.setIsDemoScheduled(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isDemoScheduled"), String.class));
		tutorMapperObject.setMappingStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("mappingStatus"), String.class));
		tutorMapperObject.setEntryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("entryDateMillis"), Long.class));
		tutorMapperObject.setIsEnquiryClosed(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("isEnquiryClosed"), String.class));
		tutorMapperObject.setEnquiryClosedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryClosedMillis"), Long.class));
		tutorMapperObject.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryEmail"), String.class));
		tutorMapperObject.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorMapperObject.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(tutorMapperObject, row, rowNum);
		return tutorMapperObject;
	}

}
