package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.SubscriptionPackageConstants;
import com.dao.ApplicationDao;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.SubscriptionPackageRowMapper;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;

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
}
