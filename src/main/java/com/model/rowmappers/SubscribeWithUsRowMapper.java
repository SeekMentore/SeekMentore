package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.components.publicaccess.SubscribeWithUs;
import com.utils.ExceptionUtils;

public class SubscribeWithUsRowMapper implements RowMapper<SubscribeWithUs>, SubscribeWithUsConstants {

	@Override
	public SubscribeWithUs mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribeWithUs subscribeWithUs = new SubscribeWithUs();
		subscribeWithUs.setTentativeSubscriptionId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_TENTATIVE_SUBSCRIPTION_ID, Long.class));
		subscribeWithUs.setApplicationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_APPLICATION_DATE, Timestamp.class));
		subscribeWithUs.setApplicationStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_APPLICATION_STATUS, String.class));
		subscribeWithUs.setFirstName(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_FIRST_NAME, String.class));
		subscribeWithUs.setLastName(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_LAST_NAME, String.class));
		subscribeWithUs.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACT_NUMBER, String.class));
		subscribeWithUs.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_EMAIL_ID, String.class));
		subscribeWithUs.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_STUDENT_GRADE, String.class));
		subscribeWithUs.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUBJECTS, String.class));
		subscribeWithUs.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PREFERRED_TIME_TO_CALL, String.class));
		subscribeWithUs.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_LOCATION, String.class));
		subscribeWithUs.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REFERENCE, String.class));
		subscribeWithUs.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ADDRESS_DETAILS, String.class));
		subscribeWithUs.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ADDITIONAL_DETAILS, String.class));
		subscribeWithUs.setSubscribedCustomer(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUBSCRIBED_CUSTOMER, String.class));
		subscribeWithUs.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_CONTACTED, String.class));
		subscribeWithUs.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_CONTACTED, String.class));
		subscribeWithUs.setContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_DATE, Timestamp.class));
		subscribeWithUs.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_REMARKS, String.class));
		subscribeWithUs.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, String.class));
		subscribeWithUs.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_VERIFIED, String.class));
		subscribeWithUs.setVerificationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_DATE, Timestamp.class));
		subscribeWithUs.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_REMARKS, String.class));
		subscribeWithUs.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_TO_BE_RECONTACTED, String.class));
		subscribeWithUs.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, String.class));
		subscribeWithUs.setSuggestionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_DATE, Timestamp.class));
		subscribeWithUs.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_REMARKS, String.class));
		subscribeWithUs.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_RECONTACTED, String.class));
		subscribeWithUs.setRecontactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_DATE, Timestamp.class));
		subscribeWithUs.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_REMARKS, String.class));
		subscribeWithUs.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_SELECTED, String.class));
		subscribeWithUs.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SELECTED, String.class));
		subscribeWithUs.setSelectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_DATE, Timestamp.class));
		subscribeWithUs.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_REMARKS, String.class));
		subscribeWithUs.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_REJECTED, String.class));
		subscribeWithUs.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_REJECTED, String.class));
		subscribeWithUs.setRejectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_DATE, Timestamp.class));
		subscribeWithUs.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_REMARKS, String.class));
		subscribeWithUs.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECORD_LAST_UPDATED, Timestamp.class));
		return subscribeWithUs;
	}

}
