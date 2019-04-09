package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribeWithUsConstants;
import com.model.components.publicaccess.SubscribeWithUs;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.PublicApplicationUtils;
import com.utils.RowMapperUtils;

public class SubscribeWithUsRowMapper implements RowMapper<SubscribeWithUs>, SubscribeWithUsConstants {

	@Override
	public SubscribeWithUs mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SubscribeWithUs subscribeWithUs = new SubscribeWithUs();
		subscribeWithUs.setSubscribeWithUsSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("subscribeWithUsSerialId"), String.class));
		subscribeWithUs.setApplicationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("applicationDateMillis"), Long.class));
		subscribeWithUs.setApplicationStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("applicationStatus"), String.class));
		subscribeWithUs.setFirstName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("firstName"), String.class));
		subscribeWithUs.setLastName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("lastName"), String.class));
		subscribeWithUs.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("contactNumber"), String.class));
		subscribeWithUs.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("emailId"), String.class));
		subscribeWithUs.setStudentGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("studentGrade"), String.class));
		subscribeWithUs.setSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("subjects"), String.class));
		subscribeWithUs.setPreferredTimeToCall(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("preferredTimeToCall"), String.class));
		subscribeWithUs.setLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("location"), String.class));
		subscribeWithUs.setReference(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("reference"), String.class));
		subscribeWithUs.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("addressDetails"), String.class));
		subscribeWithUs.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("additionalDetails"), String.class));
		subscribeWithUs.setSubscribedCustomer(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("subscribedCustomer"), String.class));
		subscribeWithUs.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isContacted"), String.class));
		subscribeWithUs.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoContacted"), String.class));
		subscribeWithUs.setContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("contactedDateMillis"), Long.class));
		subscribeWithUs.setContactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("contactedRemarks"), String.class));
		subscribeWithUs.setIsAuthenticationVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isAuthenticationVerified"), String.class));
		subscribeWithUs.setWhoVerified(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoVerified"), String.class));
		subscribeWithUs.setVerificationDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("verificationDateMillis"), Long.class));
		subscribeWithUs.setVerificationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("verificationRemarks"), String.class));
		subscribeWithUs.setIsToBeRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isToBeRecontacted"), String.class));
		subscribeWithUs.setWhoSuggestedForRecontact(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoSuggestedForRecontact"), String.class));
		subscribeWithUs.setSuggestionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("suggestionDateMillis"), Long.class));
		subscribeWithUs.setSuggestionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("suggestionRemarks"), String.class));
		subscribeWithUs.setWhoRecontacted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoRecontacted"), String.class));
		subscribeWithUs.setRecontactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("recontactedDateMillis"), Long.class));
		subscribeWithUs.setRecontactedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("recontactedRemarks"), String.class));
		subscribeWithUs.setIsSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isSelected"), String.class));
		subscribeWithUs.setWhoSelected(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoSelected"), String.class));
		subscribeWithUs.setSelectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("selectionDateMillis"), Long.class));
		subscribeWithUs.setSelectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("selectionRemarks"), String.class));
		subscribeWithUs.setIsRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isRejected"), String.class));
		subscribeWithUs.setWhoRejected(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoRejected"), String.class));
		subscribeWithUs.setRejectionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("rejectionDateMillis"), Long.class));
		subscribeWithUs.setRejectionRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("rejectionRemarks"), String.class));
		subscribeWithUs.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		subscribeWithUs.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("updatedBy"), String.class));
		subscribeWithUs.setWhoContactedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoContactedName"), String.class));
		subscribeWithUs.setWhoVerifiedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoVerifiedName"), String.class));
		subscribeWithUs.setWhoSuggestedForRecontactName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoSuggestedForRecontactName"), String.class));
		subscribeWithUs.setWhoRecontactedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoRecontactedName"), String.class));
		subscribeWithUs.setWhoSelectedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoSelectedName"), String.class));
		subscribeWithUs.setWhoRejectedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoRejectedName"), String.class));
		subscribeWithUs.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("updatedByName"), String.class));
		subscribeWithUs.setBlacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("blacklistedDateMillis"), Long.class));
		subscribeWithUs.setIsBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("isBlacklisted"), String.class));
		subscribeWithUs.setBlacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("blacklistedRemarks"), String.class));
		subscribeWithUs.setWhoBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoBlacklisted"), String.class));
		subscribeWithUs.setWhoBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoBlacklistedName"), String.class));
		subscribeWithUs.setUnblacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("unblacklistedDateMillis"), Long.class));
		subscribeWithUs.setUnblacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("unblacklistedRemarks"), String.class));
		subscribeWithUs.setWhoUnBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoUnBlacklisted"), String.class));
		subscribeWithUs.setWhoUnBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribeWithUs.resolveColumnNameForMapping("whoUnBlacklistedName"), String.class));
		PublicApplicationUtils.mapMigrationColumnsForRecords(subscribeWithUs, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscribeWithUs, row, rowNum);
		return subscribeWithUs;
	}

}
