package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.FindTutorConstants;
import com.model.components.publicaccess.FindTutor;
import com.utils.ExceptionUtils;

public class FindTutorRowMapper implements RowMapper<FindTutor>, FindTutorConstants {

	@Override
	public FindTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final FindTutor findTutor = new FindTutor();
		findTutor.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ENQUIRY_ID, Long.class));
		findTutor.setEnquiryDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ENQUIRY_DATE, Timestamp.class));
		findTutor.setEnquiryStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ENQUIRY_STATUS, String.class));
		findTutor.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_NAME, String.class));
		findTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACT_NUMBER, String.class));
		findTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_EMAIL_ID, String.class));
		findTutor.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_STUDENT_GRADE, String.class));
		findTutor.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUBJECTS, String.class));
		findTutor.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PREFERRED_TIME_TO_CALL, String.class));
		findTutor.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_LOCATION, String.class));
		findTutor.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REFERENCE, String.class));
		findTutor.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ADDRESS_DETAILS, String.class));
		findTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ADDITIONAL_DETAILS, String.class));
		findTutor.setSubscribedCustomer(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUBSCRIBED_CUSTOMER, String.class));
		findTutor.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_CONTACTED, String.class));
		findTutor.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_CONTACTED, String.class));
		findTutor.setContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_DATE, Timestamp.class));
		findTutor.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_REMARKS, String.class));
		findTutor.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, String.class));
		findTutor.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_VERIFIED, String.class));
		findTutor.setVerificationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_DATE, Timestamp.class));
		findTutor.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_REMARKS, String.class));
		findTutor.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_TO_BE_RECONTACTED, String.class));
		findTutor.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, String.class));
		findTutor.setSuggestionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_DATE, Timestamp.class));
		findTutor.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_REMARKS, String.class));
		findTutor.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_RECONTACTED, String.class));
		findTutor.setRecontactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_DATE, Timestamp.class));
		findTutor.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_REMARKS, String.class));
		findTutor.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_SELECTED, String.class));
		findTutor.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SELECTED, String.class));
		findTutor.setSelectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_DATE, Timestamp.class));
		findTutor.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_REMARKS, String.class));
		findTutor.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_REJECTED, String.class));
		findTutor.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_REJECTED, String.class));
		findTutor.setRejectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_DATE, Timestamp.class));
		findTutor.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_REMARKS, String.class));
		findTutor.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECORD_LAST_UPDATED, Timestamp.class));
		findTutor.setIsDataMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_DATA_MIGRATED", String.class));
		findTutor.setWhenMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHEN_MIGRATED", Timestamp.class));
		return findTutor;
	}

}
