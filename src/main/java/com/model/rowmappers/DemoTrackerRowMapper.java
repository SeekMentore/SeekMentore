package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.DemoTracker;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class DemoTrackerRowMapper implements RowMapper<DemoTracker> {

	@Override
	public DemoTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final DemoTracker demoTracker = new DemoTracker();
		demoTracker.setDemoTrackerId(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("demoTrackerId"), Long.class));
		demoTracker.setTutorMapperId(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorMapperId"), Long.class));
		demoTracker.setDemoDateAndTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("demoDateAndTimeMillis"), Long.class));
		demoTracker.setDemoOccurred(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("demoOccurred"), String.class));
		demoTracker.setDemoStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("demoStatus"), String.class));
		demoTracker.setClientRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("clientRemarks"), String.class));
		demoTracker.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorRemarks"), String.class));
		demoTracker.setClientSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("clientSatisfiedFromTutor"), String.class));
		demoTracker.setTutorSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorSatisfiedWithClient"), String.class));
		demoTracker.setAdminSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("adminSatisfiedFromTutor"), String.class));
		demoTracker.setAdminSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("adminSatisfiedWithClient"), String.class));
		demoTracker.setIsDemoSuccess(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("isDemoSuccess"), String.class));
		demoTracker.setNeedPriceNegotiationWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("needPriceNegotiationWithClient"), String.class));
		demoTracker.setClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("clientNegotiationRemarks"), String.class));
		demoTracker.setNeedPriceNegotiationWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("needPriceNegotiationWithTutor"), String.class));
		demoTracker.setTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorNegotiationRemarks"), String.class));
		demoTracker.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("adminRemarks"), String.class));
		demoTracker.setNegotiatedOverrideRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("negotiatedOverrideRateWithClient"), Integer.class));
		demoTracker.setNegotiatedOverrideRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("negotiatedOverrideRateWithTutor"), Integer.class));
		demoTracker.setAdminActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("adminActionDateMillis"), Long.class));
		demoTracker.setAdminFinalizingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("adminFinalizingRemarks"), String.class));
		demoTracker.setReschedulingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("reschedulingRemarks"), String.class));
		demoTracker.setReScheduleCount(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("reScheduleCount"), Integer.class));
		demoTracker.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("whoActed"), String.class));
		demoTracker.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("customerId"), Long.class));
		demoTracker.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("customerName"), String.class));
		demoTracker.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("customerEmail"), String.class));
		demoTracker.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("customerContactNumber"), String.class));
		demoTracker.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorId"), Long.class));
		demoTracker.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorName"), String.class));
		demoTracker.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorEmail"), String.class));
		demoTracker.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		demoTracker.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryId"), Long.class));
		demoTracker.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquirySubject"), String.class));
		demoTracker.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryGrade"), String.class));
		demoTracker.setEnquiryLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryLocation"), String.class));
		demoTracker.setEnquiryPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryPreferredTeachingType"), String.class));
		demoTracker.setEnquiryQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryQuotedClientRate"), Integer.class));
		demoTracker.setEnquiryNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryNegotiatedRateWithClient"), Integer.class));
		demoTracker.setEnquiryClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("enquiryClientNegotiationRemarks"), String.class));
		demoTracker.setTutorMapperQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorMapperQuotedTutorRate"), Integer.class));
		demoTracker.setTutorMapperNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorMapperNegotiatedRateWithTutor"), Integer.class));
		demoTracker.setTutorMapperTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("tutorMapperTutorNegotiationRemarks"), String.class));
		demoTracker.setEntryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("entryDateMillis"), Long.class));
		demoTracker.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, demoTracker.resolveColumnNameForMapping("whoActedName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(demoTracker, row, rowNum);
		return demoTracker;
	}

}
