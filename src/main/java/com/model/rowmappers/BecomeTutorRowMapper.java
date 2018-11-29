package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.BecomeTutorConstants;
import com.model.components.publicaccess.BecomeTutor;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.PublicApplicationUtils;

public class BecomeTutorRowMapper implements RowMapper<BecomeTutor>, BecomeTutorConstants {

	@Override
	public BecomeTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final BecomeTutor becomeTutor = new BecomeTutor();
		becomeTutor.setTentativeTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("tentativeTutorId"), Long.class));
		becomeTutor.setApplicationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("applicationDateMillis"), Long.class));
		becomeTutor.setApplicationStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("applicationStatus"), String.class));
		becomeTutor.setDateOfBirth(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("dateOfBirth"), Timestamp.class));
		becomeTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("contactNumber"), String.class));
		becomeTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("emailId"), String.class));
		becomeTutor.setFirstName(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("firstName"), String.class));
		becomeTutor.setLastName(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("lastName"), String.class));
		becomeTutor.setGender(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("gender"), String.class));
		becomeTutor.setQualification(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("qualification"), String.class));
		becomeTutor.setPrimaryProfession(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("primaryProfession"), String.class));
		becomeTutor.setTransportMode(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("transportMode"), String.class));
		becomeTutor.setTeachingExp(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("teachingExp"), Integer.class));
		becomeTutor.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("studentGrade"), String.class));
		becomeTutor.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("subjects"), String.class));
		becomeTutor.setLocations(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("locations"), String.class));
		becomeTutor.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("preferredTimeToCall"), String.class));
		becomeTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("additionalDetails"), String.class));
		becomeTutor.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("isContacted"), String.class));
		becomeTutor.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoContacted"), String.class));
		becomeTutor.setContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("contactedDateMillis"), Long.class));
		becomeTutor.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("contactedRemarks"), String.class));
		becomeTutor.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("isAuthenticationVerified"), String.class));
		becomeTutor.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoVerified"), String.class));
		becomeTutor.setVerificationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("verificationDateMillis"), Long.class));
		becomeTutor.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("verificationRemarks"), String.class));
		becomeTutor.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("isToBeRecontacted"), String.class));
		becomeTutor.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoSuggestedForRecontact"), String.class));
		becomeTutor.setSuggestionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("suggestionDateMillis"), Long.class));
		becomeTutor.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("suggestionRemarks"), String.class));
		becomeTutor.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoRecontacted"), String.class));
		becomeTutor.setRecontactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("recontactedDateMillis"), Long.class));
		becomeTutor.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("recontactedRemarks"), String.class));
		becomeTutor.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("isSelected"), String.class));
		becomeTutor.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoSelected"), String.class));
		becomeTutor.setSelectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("selectionDateMillis"), Long.class));
		becomeTutor.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("selectionRemarks"), String.class));
		becomeTutor.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("isRejected"), String.class));
		becomeTutor.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("whoRejected"), String.class));
		becomeTutor.setRejectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("rejectionDateMillis"), Long.class));
		becomeTutor.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("rejectionRemarks"), String.class));
		becomeTutor.setRejectionCount(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("rejectionCount"), Integer.class));
		becomeTutor.setReApplied(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("reApplied"), String.class));
		becomeTutor.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("reference"), String.class));
		becomeTutor.setPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("preferredTeachingType"), String.class));
		becomeTutor.setPreviousApplicationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("previousApplicationDateMillis"), Long.class));
		becomeTutor.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		becomeTutor.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, becomeTutor.resolveColumnNameForMapping("updatedBy"), String.class));
		PublicApplicationUtils.mapMigrationColumnsForRecords(becomeTutor, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(becomeTutor, row, rowNum);
		return becomeTutor;
	}

}
