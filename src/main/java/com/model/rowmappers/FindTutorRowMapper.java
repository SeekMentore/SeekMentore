package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.FindTutorConstants;
import com.model.components.publicaccess.FindTutor;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.PublicApplicationUtils;

public class FindTutorRowMapper implements RowMapper<FindTutor>, FindTutorConstants {

	@Override
	public FindTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final FindTutor findTutor = new FindTutor();
		findTutor.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("enquiryId"), Long.class));
		findTutor.setEnquiryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("enquiryDateMillis"), Long.class));
		findTutor.setEnquiryStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("enquiryStatus"), String.class));
		findTutor.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("name"), String.class));
		findTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("contactNumber"), String.class));
		findTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("emailId"), String.class));
		findTutor.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("studentGrade"), String.class));
		findTutor.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("subjects"), String.class));
		findTutor.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("preferredTimeToCall"), String.class));
		findTutor.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("location"), String.class));
		findTutor.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("reference"), String.class));
		findTutor.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("addressDetails"), String.class));
		findTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("additionalDetails"), String.class));
		findTutor.setSubscribedCustomer(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("subscribedCustomer"), String.class));
		findTutor.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("isContacted"), String.class));
		findTutor.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoContacted"), String.class));
		findTutor.setContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("contactedDateMillis"), Long.class));
		findTutor.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("contactedRemarks"), String.class));
		findTutor.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("isAuthenticationVerified"), String.class));
		findTutor.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoVerified"), String.class));
		findTutor.setVerificationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("verificationDateMillis"), Long.class));
		findTutor.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("verificationRemarks"), String.class));
		findTutor.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("isToBeRecontacted"), String.class));
		findTutor.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoSuggestedForRecontact"), String.class));
		findTutor.setSuggestionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("suggestionDateMillis"), Long.class));
		findTutor.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("suggestionRemarks"), String.class));
		findTutor.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoRecontacted"), String.class));
		findTutor.setRecontactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("recontactedDateMillis"), Long.class));
		findTutor.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("recontactedRemarks"), String.class));
		findTutor.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("isSelected"), String.class));
		findTutor.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoSelected"), String.class));
		findTutor.setSelectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("selectionDateMillis"), Long.class));
		findTutor.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("selectionRemarks"), String.class));
		findTutor.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("isRejected"), String.class));
		findTutor.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("whoRejected"), String.class));
		findTutor.setRejectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("rejectionDateMillis"), Long.class));
		findTutor.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("rejectionRemarks"), String.class));
		findTutor.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		findTutor.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, findTutor.resolveColumnNameForMapping("updatedBy"), String.class));
		PublicApplicationUtils.mapMigrationColumnsForRecords(findTutor, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(findTutor, row, rowNum);
		return findTutor;
	}

}
