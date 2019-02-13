package com.service.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.SubscriptionPackageConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Demo;
import com.model.components.PackageAssignment;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.PackageAssignmentRowMapper;
import com.model.rowmappers.SubscriptionPackageRowMapper;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE)
public class SubscriptionPackageService implements SubscriptionPackageConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public List<SubscriptionPackage> getSubscriptionPackageList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageFilter");
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new SubscriptionPackageRowMapper());
	}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForTutor(final String grid, final Long tutorId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageTutorIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForCustomer(final String grid, final Long customerId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", customerId);
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCustomerIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public List<PackageAssignment> getSelectedSubscriptionPackageAssignmentList(final String grid, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectPackageAssignment");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentSubscriptionPackageIdFilter");
		paramsMap.put("subscriptionPackageId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "subscriptionPackageId", Long.class));
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_CURRENT_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentCurrentAssignmentAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_HISTORY_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentHistoryAssignmentAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new PackageAssignmentRowMapper());
	}
	
	@Transactional
	public void updateSubscriptionPackageRecord(final SubscriptionPackage subscriptionPackageObject, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIPTION_PACKAGE SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE SUBSCRIPTION_PACKAGE_ID = :subscriptionPackageId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "packageBillingType" : {
						updateAttributesQuery.add("PACKAGE_BILLING_TYPE = :packageBillingType");
						paramsMap.put("packageBillingType", subscriptionPackageObject.getPackageBillingType());
						break;
					}
					case "finalizedRate" : {
						updateAttributesQuery.add("FINALIZED_RATE = :finalizedRate");
						paramsMap.put("finalizedRate", subscriptionPackageObject.getFinalizedRate());
						break;
					}
					case "isCustomerGrieved" : {
						updateAttributesQuery.add("IS_CUSTOMER_GRIEVED = :isCustomerGrieved");
						paramsMap.put("isCustomerGrieved", subscriptionPackageObject.getIsCustomerGrieved());
						break;
					}
					case "customerHappinessIndex" : {
						updateAttributesQuery.add("CUSTOMER_HAPPINESS_INDEX = :customerHappinessIndex");
						paramsMap.put("customerHappinessIndex", subscriptionPackageObject.getCustomerHappinessIndex());
						break;
					}
					case "customerRemarks" : {
						updateAttributesQuery.add("CUSTOMER_REMARKS = :customerRemarks");
						paramsMap.put("customerRemarks", subscriptionPackageObject.getCustomerRemarks());
						break;
					}
					case "isTutorGrieved" : {
						updateAttributesQuery.add("IS_TUTOR_GRIEVED = :isTutorGrieved");
						paramsMap.put("isTutorGrieved", subscriptionPackageObject.getIsTutorGrieved());
						break;
					}
					case "tutorHappinessIndex" : {
						updateAttributesQuery.add("TUTOR_HAPPINESS_INDEX = :tutorHappinessIndex");
						paramsMap.put("tutorHappinessIndex", subscriptionPackageObject.getTutorHappinessIndex());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", subscriptionPackageObject.getTutorRemarks());
						break;
					}
					case "adminRemarks" : {
						updateAttributesQuery.add("ADMIN_REMARKS = :adminRemarks");
						paramsMap.put("adminRemarks", subscriptionPackageObject.getAdminRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("subscriptionPackageId", subscriptionPackageObject.getSubscriptionPackageId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	@Transactional
	public void takeActionOnSubscriptionPackage(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		final Date currentTimestamp = new Date();
		String demoStatus = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_CANCEL : {
				demoStatus = DEMO_STATUS_CANCELED;
				break;
			}
		}
		final List<Demo> paramObjectList = new LinkedList<Demo>();
		for (final String demoTrackerId : idList) {
			final Demo demo = new Demo();
			demo.setWhoActed(activeUser.getUserId());
			demo.setAdminFinalizingRemarks(comments);
			demo.setDemoStatus(demoStatus);
			demo.setAdminActionDateMillis(currentTimestamp.getTime());
			demo.setDemoTrackerId(Long.valueOf(demoTrackerId));
			paramObjectList.add(demo);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-demo", "updateDemoStatus", paramObjectList);
		if (sendEmails) {
			switch(button) {
				case BUTTON_ACTION_CANCEL : {
					sendDemoCanceledNotificationEmails(idList);
					break;
				}
			}
		}
	}
}
