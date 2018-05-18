package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.components.publicaccess.BecomeTutor;
import com.utils.ExceptionUtils;

public class BecomeTutorRowMapper implements RowMapper<BecomeTutor>, BecomeTutorConstants {

	@Override
	public BecomeTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final BecomeTutor becomeTutor = new BecomeTutor();
		becomeTutor.setTentativeTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_TENTATIVE_TUTOR_ID, Long.class));
		becomeTutor.setApplicationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_APPLICATION_DATE, Timestamp.class));
		becomeTutor.setApplicationStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_APPLICATION_STATUS, String.class));
		becomeTutor.setDateOfBirth(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_DATE_OF_BIRTH, Timestamp.class));
		becomeTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACT_NUMBER, String.class));
		becomeTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_EMAIL_ID, String.class));
		becomeTutor.setFirstName(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_FIRST_NAME, String.class));
		becomeTutor.setLastName(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_LAST_NAME, String.class));
		becomeTutor.setGender(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_GENDER, String.class));
		becomeTutor.setQualification(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_QUALIFICATION, String.class));
		becomeTutor.setPrimaryProfession(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PRIMARY_PROFESSION, String.class));
		becomeTutor.setTransportMode(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_TRANSPORT_MODE, String.class));
		becomeTutor.setTeachingExp(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_TEACHING_EXPERIENCE, Integer.class));
		becomeTutor.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_STUDENT_GRADE, String.class));
		becomeTutor.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUBJECTS, String.class));
		becomeTutor.setLocations(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_LOCATIONS, String.class));
		becomeTutor.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PREFERRED_TIME_TO_CALL, String.class));
		becomeTutor.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REFERENCE, String.class));
		becomeTutor.setPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PREFERRED_TEACHING_TYPE, String.class));
		becomeTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_ADDITIONAL_DETAILS, String.class));
		becomeTutor.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_CONTACTED, String.class));
		becomeTutor.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_CONTACTED, String.class));
		becomeTutor.setContactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_DATE, Timestamp.class));
		becomeTutor.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_CONTACTED_REMARKS, String.class));
		becomeTutor.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_AUTHENTICATION_VERIFIED, String.class));
		becomeTutor.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_VERIFIED, String.class));
		becomeTutor.setVerificationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_DATE, Timestamp.class));
		becomeTutor.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_VERIFICATION_REMARKS, String.class));
		becomeTutor.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_TO_BE_RECONTACTED, String.class));
		becomeTutor.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SUGGESTED_FOR_RECONTACT, String.class));
		becomeTutor.setSuggestionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_DATE, Timestamp.class));
		becomeTutor.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SUGGESTION_REMARKS, String.class));
		becomeTutor.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_RECONTACTED, String.class));
		becomeTutor.setRecontactedDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_DATE, Timestamp.class));
		becomeTutor.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECONTACTED_REMARKS, String.class));
		becomeTutor.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_SELECTED, String.class));
		becomeTutor.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_SELECTED, String.class));
		becomeTutor.setSelectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_DATE, Timestamp.class));
		becomeTutor.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_SELECTION_REMARKS, String.class));
		becomeTutor.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_IS_REJECTED, String.class));
		becomeTutor.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_WHO_REJECTED, String.class));
		becomeTutor.setRejectionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_DATE, Timestamp.class));
		becomeTutor.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_REMARKS, String.class));
		becomeTutor.setRejectionCount(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_REJECTION_COUNT, Integer.class));
		becomeTutor.setReApplied(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RE_APPLIED, String.class));
		becomeTutor.setPreviousApplicationDate(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_PREVIOUS_APPLICATION_DATE, Timestamp.class));
		becomeTutor.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, COLUMN_NAME_RECORD_LAST_UPDATED, Timestamp.class));
		becomeTutor.setIsDataMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_DATA_MIGRATED", String.class));
		becomeTutor.setWhenMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHEN_MIGRATED", Timestamp.class));
		return becomeTutor;
	}

}
