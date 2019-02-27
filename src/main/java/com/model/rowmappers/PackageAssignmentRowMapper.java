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
		packageAssignment.setPackageAssignmentSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("packageAssignmentSerialId"), String.class));
		packageAssignment.setSubscriptionPackageSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("packageAssignmentSerialId"), String.class));
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
		packageAssignment.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("whoActedName"), String.class));
		packageAssignment.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("updatedByName"), String.class));
		packageAssignment.setCreatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("createdMillis"), Long.class));
		packageAssignment.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerId"), Long.class));
		packageAssignment.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerName"), String.class));
		packageAssignment.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerEmail"), String.class));
		packageAssignment.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("customerContactNumber"), String.class));
		packageAssignment.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorId"), Long.class));
		packageAssignment.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorName"), String.class));
		packageAssignment.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorEmail"), String.class));
		packageAssignment.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		packageAssignment.setEnquiryId(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryId"), Long.class));
		packageAssignment.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquirySubject"), String.class));
		packageAssignment.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryGrade"), String.class));
		packageAssignment.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryEmail"), String.class));
		packageAssignment.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, packageAssignment.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(packageAssignment, row, rowNum);
		return packageAssignment;
	}
}
