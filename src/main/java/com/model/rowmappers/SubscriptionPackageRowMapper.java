package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.SubscriptionPackage;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class SubscriptionPackageRowMapper implements RowMapper<SubscriptionPackage> {

	@Override
	public SubscriptionPackage mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SubscriptionPackage subscriptionPackage = new SubscriptionPackage();
		subscriptionPackage.setSubscriptionPackageSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("subscriptionPackageSerialId"), String.class));
		subscriptionPackage.setCustomerSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerSerialId"), String.class));
		subscriptionPackage.setCustomerName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerName"), String.class));
		subscriptionPackage.setCustomerEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerEmail"), String.class));
		subscriptionPackage.setCustomerContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerContactNumber"), String.class));
		subscriptionPackage.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorSerialId"), String.class));
		subscriptionPackage.setTutorName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorName"), String.class));
		subscriptionPackage.setTutorEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorEmail"), String.class));
		subscriptionPackage.setTutorContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorContactNumber"), String.class));
		subscriptionPackage.setTutorMapperSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorMapperSerialId"), String.class));
		subscriptionPackage.setTutorMapperQuotedTutorRate(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorMapperQuotedTutorRate"), Integer.class));
		subscriptionPackage.setTutorMapperNegotiatedRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorMapperNegotiatedRateWithTutor"), Integer.class));
		subscriptionPackage.setTutorMapperTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorMapperTutorNegotiationRemarks"), String.class));
		subscriptionPackage.setEnquirySerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquirySerialId"), String.class));
		subscriptionPackage.setEnquirySubject(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquirySubject"), String.class));
		subscriptionPackage.setEnquiryGrade(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryGrade"), String.class));
		subscriptionPackage.setEnquiryLocation(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryLocation"), String.class));
		subscriptionPackage.setEnquiryEmail(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryEmail"), String.class));
		subscriptionPackage.setEnquiryContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryContactNumber"), String.class));
		subscriptionPackage.setEnquiryAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryAddressDetails"), String.class));
		subscriptionPackage.setEnquiryAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryAdditionalDetails"), String.class));
		subscriptionPackage.setEnquiryPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryPreferredTeachingType"), String.class));
		subscriptionPackage.setEnquiryQuotedClientRate(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryQuotedClientRate"), Integer.class));
		subscriptionPackage.setEnquiryNegotiatedRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryNegotiatedRateWithClient"), Integer.class));
		subscriptionPackage.setEnquiryClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("enquiryClientNegotiationRemarks"), String.class));
		subscriptionPackage.setDemoSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoSerialId"), String.class));
		subscriptionPackage.setDemoClientRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoClientRemarks"), String.class));
		subscriptionPackage.setDemoTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoTutorRemarks"), String.class));
		subscriptionPackage.setDemoClientSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoClientSatisfiedFromTutor"), String.class));
		subscriptionPackage.setDemoTutorSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoTutorSatisfiedWithClient"), String.class));
		subscriptionPackage.setDemoAdminSatisfiedFromTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoAdminSatisfiedFromTutor"), String.class));
		subscriptionPackage.setDemoAdminSatisfiedWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoAdminSatisfiedWithClient"), String.class));
		subscriptionPackage.setDemoNeedPriceNegotiationWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoNeedPriceNegotiationWithClient"), String.class));
		subscriptionPackage.setDemoClientNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoClientNegotiationRemarks"), String.class));
		subscriptionPackage.setDemoNeedPriceNegotiationWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoNeedPriceNegotiationWithTutor"), String.class));
		subscriptionPackage.setDemoTutorNegotiationRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoTutorNegotiationRemarks"), String.class));
		subscriptionPackage.setDemoAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoAdminRemarks"), String.class));
		subscriptionPackage.setDemoNegotiatedOverrideRateWithClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoNegotiatedOverrideRateWithClient"), Integer.class));
		subscriptionPackage.setDemoNegotiatedOverrideRateWithTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoNegotiatedOverrideRateWithTutor"), Integer.class));
		subscriptionPackage.setDemoAdminFinalizingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("demoAdminFinalizingRemarks"), String.class));
		subscriptionPackage.setCreatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("createdMillis"), Long.class));
		subscriptionPackage.setStartDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("startDateMillis"), Long.class));
		subscriptionPackage.setPackageBillingType(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("packageBillingType"), String.class));
		subscriptionPackage.setFinalizedRateForClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("finalizedRateForClient"), Integer.class));
		subscriptionPackage.setFinalizedRateForTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("finalizedRateForTutor"), Integer.class));
		subscriptionPackage.setEndDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("endDateMillis"), Long.class));
		subscriptionPackage.setIsCustomerGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("isCustomerGrieved"), String.class));
		subscriptionPackage.setCustomerHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerHappinessIndex"), String.class));
		subscriptionPackage.setCustomerRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("customerRemarks"), String.class));
		subscriptionPackage.setIsTutorGrieved(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("isTutorGrieved"), String.class));
		subscriptionPackage.setTutorHappinessIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorHappinessIndex"), String.class));
		subscriptionPackage.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("tutorRemarks"), String.class));
		subscriptionPackage.setAdminRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("adminRemarks"), String.class));
		subscriptionPackage.setAdditionalDetailsClient(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("additionalDetailsClient"), String.class));
		subscriptionPackage.setAdditionalDetailsTutor(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("additionalDetailsTutor"), String.class));
		subscriptionPackage.setActivatingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("activatingRemarks"), String.class));
		subscriptionPackage.setTerminatingRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("terminatingRemarks"), String.class));
		subscriptionPackage.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		subscriptionPackage.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("whoActed"), String.class));
		subscriptionPackage.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("whoActedName"), String.class));
		subscriptionPackage.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		subscriptionPackage.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("updatedBy"), String.class));
		subscriptionPackage.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("updatedByName"), String.class));
		subscriptionPackage.setContractSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscriptionPackage.resolveColumnNameForMapping("contractSerialId"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscriptionPackage, row, rowNum);
		return subscriptionPackage;
	}

}
