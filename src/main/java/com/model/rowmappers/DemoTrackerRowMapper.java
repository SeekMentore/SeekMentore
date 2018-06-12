package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.DemoTracker;
import com.utils.ExceptionUtils;

public class DemoTrackerRowMapper implements RowMapper<DemoTracker> {

	@Override
	public DemoTracker mapRow(ResultSet row, int rowNum) throws SQLException {
		final DemoTracker demoTracker = new DemoTracker();
		demoTracker.setDemoTrackerId(ExceptionUtils.exceptionHandlerForRowMapper(row, "DEMO_TRACKER_ID", Long.class));
		demoTracker.setTutorMapperId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_MAPPER_ID", Long.class));
		demoTracker.setDemoDateAndTime(ExceptionUtils.exceptionHandlerForRowMapper(row, "DEMO_DATE_AND_TIME", Timestamp.class));
		demoTracker.setDemoDateAndTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, "DEMO_DATE_AND_TIME_MILLIS", Long.class));
		demoTracker.setDemoOccurred(ExceptionUtils.exceptionHandlerForRowMapper(row, "DEMO_OCCURRED", String.class));
		demoTracker.setDemoStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, "DEMO_STATUS", String.class));
		demoTracker.setClientRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_REMARKS", String.class));
		demoTracker.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_REMARKS", String.class));
		demoTracker.setClientSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_SATISFIED_FROM_TUTOR", String.class));
		demoTracker.setTutorSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_SATISFIED_WITH_CLIENT", String.class));
		demoTracker.setAdminSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_SATISFIED_FROM_TUTOR", String.class));
		demoTracker.setAdminSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_SATISFIED_WITH_CLIENT", String.class));
		demoTracker.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHO_ACTED", String.class));
		demoTracker.setIsDemoSuccess(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_DEMO_SUCCESS", String.class));
		demoTracker.setNeedPriceNegotiationWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEED_PRICE_NEGOTIATION_WITH_CLIENT", String.class));
		demoTracker.setClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "CLIENT_NEGOTIATION_REMARKS", String.class));
		demoTracker.setNeedPriceNegotiationWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEED_PRICE_NEGOTIATION_WITH_TUTOR", String.class));
		demoTracker.setTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_NEGOTIATION_REMARKS", String.class));
		demoTracker.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_REMARKS", String.class));
		demoTracker.setNegotiatedOverrideRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT", Integer.class));
		demoTracker.setNegotiatedOverrideRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, "NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR", Integer.class));
		demoTracker.setAdminActionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_ACTION_DATE", Timestamp.class));
		demoTracker.setAdminFinalizingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADMIN_FINALIZING_REMARKS", String.class));
		demoTracker.setReschedulingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "RESCHEDULING_REMARKS", String.class));
		return demoTracker;
	}

}
