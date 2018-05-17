package com.model.rowmappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.FindTutorConstants;
import com.model.components.publicaccess.FindTutor;
import com.utils.ExceptionUtils;

public class FindTutorRowMapper implements RowMapper<FindTutor>, FindTutorConstants {

	@Override
	public FindTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final FindTutor findTutor = new FindTutor();
		findTutor.setEnquiryId(row.getLong(COLUMN_NAME_ENQUIRY_ID));
		findTutor.setEnquiryDate(row.getDate(COLUMN_NAME_ENQUIRY_DATE));
		findTutor.setEnquiryStatus(row.getString(COLUMN_NAME_ENQUIRY_STATUS));
		findTutor.setName(row.getString(COLUMN_NAME_NAME));
		findTutor.setContactNumber(row.getString(COLUMN_NAME_CONTACT_NUMBER));
		findTutor.setEmailId(row.getString(COLUMN_NAME_EMAIL_ID));
		findTutor.setStudentGrade(row.getString(COLUMN_NAME_STUDENT_GRADE));
		findTutor.setSubjects(row.getString(COLUMN_NAME_SUBJECTS));
		findTutor.setPreferredTimeToCall(row.getString(COLUMN_NAME_PREFERRED_TIME_TO_CALL));
		findTutor.setLocation(row.getString(COLUMN_NAME_LOCATION));
		findTutor.setReference(row.getString(COLUMN_NAME_REFERENCE));
		findTutor.setAddressDetails(row.getString(COLUMN_NAME_ADDRESS_DETAILS));
		findTutor.setAdditionalDetails(row.getString(COLUMN_NAME_ADDITIONAL_DETAILS));
		findTutor.setSubscribedCustomer(row.getString(COLUMN_NAME_SUBSCRIBED_CUSTOMER));
		findTutor.setIsContacted(row.getString(COLUMN_NAME_IS_CONTACTED));
		findTutor.setWhoContacted(row.getString(COLUMN_NAME_WHO_CONTACTED));
		findTutor.setContactedDate(row.getDate(COLUMN_NAME_CONTACTED_DATE));
		findTutor.setContactedRemarks(row.getString(COLUMN_NAME_CONTACTED_REMARKS));
		findTutor.setIsAuthenticationVerified(row.getString(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED));
		findTutor.setWhoVerified(row.getString(COLUMN_NAME_WHO_VERIFIED));
		findTutor.setVerificationDate(row.getDate(COLUMN_NAME_VERIFICATION_DATE));
		findTutor.setVerificationRemarks(row.getString(COLUMN_NAME_VERIFICATION_REMARKS));
		findTutor.setIsToBeRecontacted(row.getString(COLUMN_NAME_IS_TO_BE_RECONTACTED));
		findTutor.setWhoSuggestedForRecontact(row.getString(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT));
		findTutor.setSuggestionDate(row.getDate(COLUMN_NAME_SUGGESTION_DATE));
		findTutor.setSuggestionRemarks(row.getString(COLUMN_NAME_SUGGESTION_REMARKS));
		findTutor.setWhoRecontacted(row.getString(COLUMN_NAME_WHO_RECONTACTED));
		findTutor.setRecontactedDate(row.getDate(COLUMN_NAME_RECONTACTED_DATE));
		findTutor.setRecontactedRemarks(row.getString(COLUMN_NAME_RECONTACTED_REMARKS));
		findTutor.setIsSelected(row.getString(COLUMN_NAME_IS_SELECTED));
		findTutor.setWhoSelected(row.getString(COLUMN_NAME_WHO_SELECTED));
		findTutor.setSelectionDate(row.getDate(COLUMN_NAME_SELECTION_DATE));
		findTutor.setSelectionRemarks(row.getString(COLUMN_NAME_SELECTION_REMARKS));
		findTutor.setIsRejected(row.getString(COLUMN_NAME_IS_REJECTED));
		findTutor.setWhoRejected(row.getString(COLUMN_NAME_WHO_REJECTED));
		findTutor.setRejectionDate(row.getDate(COLUMN_NAME_REJECTION_DATE));
		findTutor.setRejectionRemarks(row.getString(COLUMN_NAME_REJECTION_REMARKS));
		findTutor.setRecordLastUpdated(row.getDate(COLUMN_NAME_RECORD_LAST_UPDATED));
		findTutor.setIsDataMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_DATA_MIGRATED", String.class));
		findTutor.setWhenMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHEN_MIGRATED", Date.class));
		return findTutor;
	}

}
