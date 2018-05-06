package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.components.publicaccess.BecomeTutor;

public class BecomeTutorRowMapper implements RowMapper<BecomeTutor>, BecomeTutorConstants {

	@Override
	public BecomeTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final BecomeTutor becomeTutor = new BecomeTutor();
		becomeTutor.setTentativeTutorId(row.getLong(COLUMN_NAME_TENTATIVE_TUTOR_ID));
		becomeTutor.setApplicationDate(row.getDate(COLUMN_NAME_APPLICATION_DATE));
		becomeTutor.setApplicationStatus(row.getString(COLUMN_NAME_APPLICATION_STATUS));
		becomeTutor.setDateOfBirth(row.getDate(COLUMN_NAME_DATE_OF_BIRTH));
		becomeTutor.setContactNumber(row.getString(COLUMN_NAME_CONTACT_NUMBER));
		becomeTutor.setEmailId(row.getString(COLUMN_NAME_EMAIL_ID));
		becomeTutor.setFirstName(row.getString(COLUMN_NAME_FIRST_NAME));
		becomeTutor.setLastName(row.getString(COLUMN_NAME_LAST_NAME));
		becomeTutor.setGender(row.getString(COLUMN_NAME_GENDER));
		becomeTutor.setQualification(row.getString(COLUMN_NAME_QUALIFICATION));
		becomeTutor.setPrimaryProfession(row.getString(COLUMN_NAME_PRIMARY_PROFESSION));
		becomeTutor.setTransportMode(row.getString(COLUMN_NAME_TRANSPORT_MODE));
		becomeTutor.setTeachingExp(row.getInt(COLUMN_NAME_TEACHING_EXPERIENCE));
		becomeTutor.setStudentGrade(row.getString(COLUMN_NAME_STUDENT_GRADE));
		becomeTutor.setSubjects(row.getString(COLUMN_NAME_SUBJECTS));
		becomeTutor.setLocations(row.getString(COLUMN_NAME_LOCATIONS));
		becomeTutor.setPreferredTimeToCall(row.getString(COLUMN_NAME_PREFERRED_TIME_TO_CALL));
		becomeTutor.setAdditionalDetails(row.getString(COLUMN_NAME_ADDITIONAL_DETAILS));
		becomeTutor.setIsContacted(row.getString(COLUMN_NAME_IS_CONTACTED));
		becomeTutor.setWhoContacted(row.getString(COLUMN_NAME_WHO_CONTACTED));
		becomeTutor.setContactedDate(row.getDate(COLUMN_NAME_CONTACTED_DATE));
		becomeTutor.setContactedRemarks(row.getString(COLUMN_NAME_CONTACTED_REMARKS));
		becomeTutor.setIsAuthenticationVerified(row.getString(COLUMN_NAME_IS_AUTHENTICATION_VERIFIED));
		becomeTutor.setWhoVerified(row.getString(COLUMN_NAME_WHO_VERIFIED));
		becomeTutor.setVerificationDate(row.getDate(COLUMN_NAME_VERIFICATION_DATE));
		becomeTutor.setVerificationRemarks(row.getString(COLUMN_NAME_VERIFICATION_REMARKS));
		becomeTutor.setIsToBeRecontacted(row.getString(COLUMN_NAME_IS_TO_BE_RECONTACTED));
		becomeTutor.setWhoSuggestedForRecontact(row.getString(COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT));
		becomeTutor.setSuggestionDate(row.getDate(COLUMN_NAME_SUGGESTION_DATE));
		becomeTutor.setSuggestionRemarks(row.getString(COLUMN_NAME_SUGGESTION_REMARKS));
		becomeTutor.setWhoRecontacted(row.getString(COLUMN_NAME_WHO_RECONTACTED));
		becomeTutor.setRecontactedDate(row.getDate(COLUMN_NAME_RECONTACTED_DATE));
		becomeTutor.setRecontactedRemarks(row.getString(COLUMN_NAME_RECONTACTED_REMARKS));
		becomeTutor.setIsSelected(row.getString(COLUMN_NAME_IS_SELECTED));
		becomeTutor.setWhoSelected(row.getString(COLUMN_NAME_WHO_SELECTED));
		becomeTutor.setSelectionDate(row.getDate(COLUMN_NAME_SELECTION_DATE));
		becomeTutor.setSelectionRemarks(row.getString(COLUMN_NAME_SELECTION_REMARKS));
		becomeTutor.setIsRejected(row.getString(COLUMN_NAME_IS_REJECTED));
		becomeTutor.setWhoRejected(row.getString(COLUMN_NAME_WHO_REJECTED));
		becomeTutor.setRejectionDate(row.getDate(COLUMN_NAME_REJECTION_DATE));
		becomeTutor.setRejectionRemarks(row.getString(COLUMN_NAME_REJECTION_REMARKS));
		becomeTutor.setRejectionCount(row.getInt(COLUMN_NAME_REJECTION_COUNT));
		becomeTutor.setReApplied(row.getString(COLUMN_NAME_RE_APPLIED));
		becomeTutor.setReference(row.getString(COLUMN_NAME_REFERENCE));
		becomeTutor.setPreferredTeachingType(row.getString(COLUMN_NAME_PREFERRED_TEACHING_TYPE));
		becomeTutor.setPreviousApplicationDate(row.getDate(COLUMN_NAME_PREVIOUS_APPLICATION_DATE));
		becomeTutor.setRecordLastUpdated(row.getDate(COLUMN_NAME_RECORD_LAST_UPDATED));
		return becomeTutor;
	}

}
