package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.SubscriptionPackage;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class SubscriptionPackageRowMapper implements RowMapper<SubscriptionPackage> {

	@Override
	public SubscriptionPackage mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscriptionPackage subscriptionPackage = new SubscriptionPackage();
		subscriptionPackage.setSubscriptionPackageId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("subscriptionPackageId"), Long.class));
		subscriptionPackage.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerId"), Long.class));
		subscriptionPackage.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerName"), String.class));
		subscriptionPackage.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorId"), Long.class));
		subscriptionPackage.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorName"), String.class));
		subscriptionPackage.setTotalHours(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("totalHours"), Integer.class));
		subscriptionPackage.setStartDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("startDateMillis"), Long.class));
		subscriptionPackage.setCompletedHours(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("completedHours"), Integer.class));
		subscriptionPackage.setCompletedMinutes(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("completedMinutes"), Integer.class));
		subscriptionPackage.setEndDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("endDateMillis"), Long.class));
		subscriptionPackage.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("whoActed"), String.class));
		subscriptionPackage.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("adminRemarks"), String.class));
		subscriptionPackage.setCustomerRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerRemarks"), String.class));
		subscriptionPackage.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorRemarks"), String.class));
		subscriptionPackage.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		subscriptionPackage.setIsCustomerGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("isCustomerGrieved"), String.class));
		subscriptionPackage.setIsTutorGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("isTutorGrieved"), String.class));
		subscriptionPackage.setCustomerHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerHappinessIndex"), String.class));
		subscriptionPackage.setTutorHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorHappinessIndex"), String.class));
		subscriptionPackage.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		subscriptionPackage.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("updatedBy"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscriptionPackage, row, rowNum);
		return subscriptionPackage;
	}

}
