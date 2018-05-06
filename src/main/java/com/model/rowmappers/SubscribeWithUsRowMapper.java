package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.components.publicaccess.SubscribeWithUs;

public class SubscribeWithUsRowMapper implements RowMapper<SubscribeWithUs>, SubscribeWithUsConstants {

	@Override
	public SubscribeWithUs mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribeWithUs subscribeWithUs = new SubscribeWithUs();
		subscribeWithUs.setTentativeSubscriptionId(row.getLong(COLUMN_NAME_TENTATIVE_SUBSCRIPTION_ID));
		subscribeWithUs.setApplicationDate(row.getDate(COLUMN_NAME_APPLICATION_DATE));
		subscribeWithUs.setApplicationStatus(row.getString(COLUMN_NAME_APPLICATION_STATUS));
		subscribeWithUs.setFirstName(row.getString(COLUMN_NAME_FIRST_NAME));
		subscribeWithUs.setLastName(row.getString(COLUMN_NAME_LAST_NAME));
		subscribeWithUs.setContactNumber(row.getString(COLUMN_NAME_CONTACT_NUMBER));
		subscribeWithUs.setEmailId(row.getString(COLUMN_NAME_EMAIL_ID));
		subscribeWithUs.setStudentGrade(row.getString(COLUMN_NAME_STUDENT_GRADE));
		subscribeWithUs.setSubjects(row.getString(COLUMN_NAME_SUBJECTS));
		subscribeWithUs.setPreferredTimeToCall(row.getString(COLUMN_NAME_PREFERRED_TIME_TO_CALL));
		subscribeWithUs.setAdditionalDetails(row.getString(COLUMN_NAME_ADDITIONAL_DETAILS));
		subscribeWithUs.setSubscribedCustomer(row.getString(COLUMN_NAME_SUBSCRIBED_CUSTOMER));
		subscribeWithUs.setIsContacted(row.getString(COLUMN_NAME_IS_CONTACTED));
		subscribeWithUs.setWhoContacted(row.getString(COLUMN_NAME_WHO_CONTACTED));
		subscribeWithUs.setContactedDate(row.getDate(COLUMN_NAME_CONTACTED_DATE));
		subscribeWithUs.setContactedRemarks(row.getString(COLUMN_NAME_CONTACTED_REMARKS));
		subscribeWithUs.setIsAuthenticationVerified(row.getString(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED));
		subscribeWithUs.setWhoVerified(row.getString(COLUMN_NAME_WHO_VERIFIED));
		subscribeWithUs.setVerificationDate(row.getDate(COLUMN_NAME_VERIFICATION_DATE));
		subscribeWithUs.setVerificationRemarks(row.getString(COLUMN_NAME_VERIFICATION_REMARKS));
		subscribeWithUs.setIsToBeRecontacted(row.getString(COLUMN_NAME_IS_TO_BE_RECONTACTED));
		subscribeWithUs.setWhoSuggestedForRecontact(row.getString(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT));
		subscribeWithUs.setSuggestionDate(row.getDate(COLUMN_NAME_SUGGESTION_DATE));
		subscribeWithUs.setSuggestionRemarks(row.getString(COLUMN_NAME_SUGGESTION_REMARKS));
		subscribeWithUs.setWhoRecontacted(row.getString(COLUMN_NAME_WHO_RECONTACTED));
		subscribeWithUs.setRecontactedDate(row.getDate(COLUMN_NAME_RECONTACTED_DATE));
		subscribeWithUs.setRecontactedRemarks(row.getString(COLUMN_NAME_RECONTACTED_REMARKS));
		subscribeWithUs.setIsSelected(row.getString(COLUMN_NAME_IS_SELECTED));
		subscribeWithUs.setWhoSelected(row.getString(COLUMN_NAME_WHO_SELECTED));
		subscribeWithUs.setSelectionDate(row.getDate(COLUMN_NAME_SELECTION_DATE));
		subscribeWithUs.setSelectionRemarks(row.getString(COLUMN_NAME_SELECTION_REMARKS));
		subscribeWithUs.setIsRejected(row.getString(COLUMN_NAME_IS_REJECTED));
		subscribeWithUs.setWhoRejected(row.getString(COLUMN_NAME_WHO_REJECTED));
		subscribeWithUs.setRejectionDate(row.getDate(COLUMN_NAME_REJECTION_DATE));
		subscribeWithUs.setRejectionRemarks(row.getString(COLUMN_NAME_REJECTION_REMARKS));
		subscribeWithUs.setRecordLastUpdated(row.getDate(COLUMN_NAME_RECORD_LAST_UPDATED));
		subscribeWithUs.setLocation(row.getString(COLUMN_NAME_LOCATION));
		subscribeWithUs.setReference(row.getString(COLUMN_NAME_REFERENCE));
		subscribeWithUs.setAddressDetails(row.getString(COLUMN_NAME_ADDRESS_DETAILS));
		return subscribeWithUs;
	}

}
