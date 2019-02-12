package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.PackageAssignment;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class PackageAssignmentRowMapper implements RowMapper<PackageAssignment> {

	@Override
	public PackageAssignment mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final PackageAssignment packageAssignment = new PackageAssignment();
		packageAssignment.setPackageAssignmentId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("packageAssignmentId"), Long.class));
		packageAssignment.setSubscriptionPackageId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("subscriptionPackageId"), Long.class));
		packageAssignment.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerId"), Long.class));
		packageAssignment.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerName"), String.class));
		packageAssignment.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerEmail"), String.class));
		packageAssignment.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerContactNumber"), String.class));
		packageAssignment.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorId"), Long.class));
		packageAssignment.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorName"), String.class));
		packageAssignment.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorEmail"), String.class));
		packageAssignment.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		packageAssignment.setTotalHours(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("totalHours"), Integer.class));
		packageAssignment.setStartDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("startDateMillis"), Long.class));
		packageAssignment.setCompletedHours(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("completedHours"), Integer.class));
		packageAssignment.setCompletedMinutes(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("completedMinutes"), Integer.class));
		packageAssignment.setEndDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("endDateMillis"), Long.class));
		packageAssignment.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("whoActed"), String.class));
		packageAssignment.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("adminRemarks"), String.class));
		packageAssignment.setCustomerRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerRemarks"), String.class));
		packageAssignment.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorRemarks"), String.class));
		packageAssignment.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		packageAssignment.setIsCustomerGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("isCustomerGrieved"), String.class));
		packageAssignment.setIsTutorGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("isTutorGrieved"), String.class));
		packageAssignment.setCustomerHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerHappinessIndex"), String.class));
		packageAssignment.setTutorHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorHappinessIndex"), String.class));
		packageAssignment.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		packageAssignment.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("updatedBy"), String.class));
		packageAssignment.setTutorMapperId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorMapperId"), Long.class));
		packageAssignment.setTutorMapperQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorMapperQuotedTutorRate"), Integer.class));
		packageAssignment.setTutorMapperNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorMapperNegotiatedRateWithTutor"), Integer.class));
		packageAssignment.setTutorMapperTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorMapperTutorNegotiationRemarks"), String.class));
		packageAssignment.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryId"), Long.class));
		packageAssignment.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquirySubject"), String.class));
		packageAssignment.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryGrade"), String.class));
		packageAssignment.setEnquiryLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryLocation"), String.class));
		packageAssignment.setEnquiryAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryAddressDetails"), String.class));
		packageAssignment.setEnquiryAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryAdditionalDetails"), String.class));
		packageAssignment.setEnquiryPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryPreferredTeachingType"), String.class));
		packageAssignment.setEnquiryQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryQuotedClientRate"), Integer.class));
		packageAssignment.setEnquiryNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryNegotiatedRateWithClient"), Integer.class));
		packageAssignment.setEnquiryClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryClientNegotiationRemarks"), String.class));
		packageAssignment.setDemoId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoId"), Long.class));
		packageAssignment.setDemoClientRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoClientRemarks"), String.class));
		packageAssignment.setDemoTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoTutorRemarks"), String.class));
		packageAssignment.setDemoClientSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoClientSatisfiedFromTutor"), String.class));
		packageAssignment.setDemoTutorSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoTutorSatisfiedWithClient"), String.class));
		packageAssignment.setDemoAdminSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoAdminSatisfiedFromTutor"), String.class));
		packageAssignment.setDemoAdminSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoAdminSatisfiedWithClient"), String.class));
		packageAssignment.setDemoNeedPriceNegotiationWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoNeedPriceNegotiationWithClient"), String.class));
		packageAssignment.setDemoClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoClientNegotiationRemarks"), String.class));
		packageAssignment.setDemoNeedPriceNegotiationWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoNeedPriceNegotiationWithTutor"), String.class));
		packageAssignment.setDemoTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoTutorNegotiationRemarks"), String.class));
		packageAssignment.setDemoAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoAdminRemarks"), String.class));
		packageAssignment.setDemoNegotiatedOverrideRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoNegotiatedOverrideRateWithClient"), Integer.class));
		packageAssignment.setDemoNegotiatedOverrideRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoNegotiatedOverrideRateWithTutor"), Integer.class));
		packageAssignment.setDemoAdminFinalizingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("demoAdminFinalizingRemarks"), String.class));
		packageAssignment.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("whoActedName"), String.class));
		packageAssignment.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("updatedByName"), String.class));
		packageAssignment.setCreatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("createdMillis"), Long.class));
		packageAssignment.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryEmail"), String.class));
		packageAssignment.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(packageAssignment, row, rowNum);
		return packageAssignment;
	}
}
