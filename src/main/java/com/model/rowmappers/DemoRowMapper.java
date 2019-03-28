package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Demo;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class DemoRowMapper implements RowMapper<Demo> {

	@Override
	public Demo mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Demo demo = new Demo();
		demo.setDemoSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("demoSerialId"), String.class));
		demo.setTutorMapperSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorMapperSerialId"), String.class));
		demo.setDemoDateAndTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("demoDateAndTimeMillis"), Long.class));
		demo.setDemoOccurred(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("demoOccurred"), String.class));
		demo.setDemoStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("demoStatus"), String.class));
		demo.setClientRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("clientRemarks"), String.class));
		demo.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorRemarks"), String.class));
		demo.setClientSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("clientSatisfiedFromTutor"), String.class));
		demo.setTutorSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorSatisfiedWithClient"), String.class));
		demo.setAdminSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("adminSatisfiedFromTutor"), String.class));
		demo.setAdminSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("adminSatisfiedWithClient"), String.class));
		demo.setIsDemoSuccess(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("isDemoSuccess"), String.class));
		demo.setNeedPriceNegotiationWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("needPriceNegotiationWithClient"), String.class));
		demo.setClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("clientNegotiationRemarks"), String.class));
		demo.setNeedPriceNegotiationWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("needPriceNegotiationWithTutor"), String.class));
		demo.setTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorNegotiationRemarks"), String.class));
		demo.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("adminRemarks"), String.class));
		demo.setNegotiatedOverrideRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("negotiatedOverrideRateWithClient"), Integer.class));
		demo.setNegotiatedOverrideRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("negotiatedOverrideRateWithTutor"), Integer.class));
		demo.setAdminActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("adminActionDateMillis"), Long.class));
		demo.setAdminFinalizingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("adminFinalizingRemarks"), String.class));
		demo.setReschedulingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("reschedulingRemarks"), String.class));
		demo.setReScheduleCount(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("reScheduleCount"), Integer.class));
		demo.setRescheduledFromDemoSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("rescheduledFromDemoSerialId"), String.class));
		demo.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("whoActed"), String.class));
		demo.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("customerId"), Long.class));
		demo.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("customerName"), String.class));
		demo.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("customerEmail"), String.class));
		demo.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("customerContactNumber"), String.class));
		demo.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorId"), Long.class));
		demo.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorName"), String.class));
		demo.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorEmail"), String.class));
		demo.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		demo.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryId"), Long.class));
		demo.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquirySubject"), String.class));
		demo.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryGrade"), String.class));
		demo.setEnquiryLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryLocation"), String.class));
		demo.setEnquiryAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryAddressDetails"), String.class));
		demo.setEnquiryAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryAdditionalDetails"), String.class));
		demo.setEnquiryPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryPreferredTeachingType"), String.class));
		demo.setEnquiryQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryQuotedClientRate"), Integer.class));
		demo.setEnquiryNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryNegotiatedRateWithClient"), Integer.class));
		demo.setEnquiryClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryClientNegotiationRemarks"), String.class));
		demo.setTutorMapperQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorMapperQuotedTutorRate"), Integer.class));
		demo.setTutorMapperNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorMapperNegotiatedRateWithTutor"), Integer.class));
		demo.setTutorMapperTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("tutorMapperTutorNegotiationRemarks"), String.class));
		demo.setEntryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("entryDateMillis"), Long.class));
		demo.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("whoActedName"), String.class));
		demo.setIsSubscriptionCreated(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("isSubscriptionCreated"), String.class));
		demo.setSubscriptionCreatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("subscriptionCreatedMillis"), Long.class));
		demo.setIsEnquiryClosed(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("isEnquiryClosed"), String.class));
		demo.setEnquiryClosedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryClosedMillis"), Long.class));
		demo.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryEmail"), String.class));
		demo.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, demo.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(demo, row, rowNum);
		return demo;
	}

}
