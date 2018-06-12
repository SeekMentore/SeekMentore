package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.TutorMapper;
import com.utils.ExceptionUtils;

public class TutorMapperRowMapper implements RowMapper<TutorMapper> {

	@Override
	public TutorMapper mapRow(ResultSet row, int rowNum) throws SQLException {
		final TutorMapper tutorMapperObject = new TutorMapper();
		tutorMapperObject.setTutorMapperId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_MAPPER_ID", Long.class));
		tutorMapperObject.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENQUIRY_ID", Long.class));
		tutorMapperObject.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_ID", Long.class));
		tutorMapperObject.setQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, "QUOTED_TUTOR_RATE", Integer.class));
		tutorMapperObject.setNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEGOTIATED_RATE_WITH_TUTOR", Integer.class));
		tutorMapperObject.setTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_NEGOTIATION_REMARKS", String.class));
		tutorMapperObject.setIsTutorContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_TUTOR_CONTACTED", String.class));
		tutorMapperObject.setTutorContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_CONTACTED_DATE", Timestamp.class));
		tutorMapperObject.setIsTutorAgreed(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_TUTOR_AGREED", String.class));
		tutorMapperObject.setIsTutorRejectionValid(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_TUTOR_REJECTION_VALID", String.class));
		tutorMapperObject.setAdminTutorRejectionValidityResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE", String.class));
		tutorMapperObject.setTutorResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_RESPONSE", String.class));
		tutorMapperObject.setAdminRemarksForTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_REMARKS_FOR_TUTOR", String.class));
		tutorMapperObject.setIsClientContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_CLIENT_CONTACTED", String.class));
		tutorMapperObject.setClientContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_CONTACTED_DATE", Timestamp.class));
		tutorMapperObject.setIsClientAgreed(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_CLIENT_AGREED", String.class));
		tutorMapperObject.setClientResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_RESPONSE", String.class));
		tutorMapperObject.setIsClientRejectionValid(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_CLIENT_REJECTION_VALID", String.class));
		tutorMapperObject.setAdminClientRejectionValidityResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE", String.class));
		tutorMapperObject.setAdminRemarksForClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_REMARKS_FOR_CLIENT", String.class));
		tutorMapperObject.setAdminActionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_ACTION_DATE", Timestamp.class));
		tutorMapperObject.setAdminActionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_ACTION_REMARKS", String.class));
		tutorMapperObject.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHO_ACTED", String.class));
		tutorMapperObject.setIsDemoScheduled(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_DEMO_SCHEDULED", String.class));
		tutorMapperObject.setMappingStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, "MAPPING_STATUS", String.class));
		return tutorMapperObject;
	}

}
