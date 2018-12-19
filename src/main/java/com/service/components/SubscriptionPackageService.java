package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.dao.ApplicationDao;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.SubscriptionPackageRowMapper;
import com.utils.GridQueryUtils;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE)
public class SubscriptionPackageService {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForTutor(final String grid, final Long tutorId, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		final String baseQuery = "SELECT "
				+ "S.*, "
				+ "(SELECT NAME FROM SUBSCRIBED_CUSTOMER C WHERE C.CUSTOMER_ID = S.CUSTOMER_ID) AS CUSTOMER_NAME, "
				+ "(SELECT NAME FROM REGISTERED_TUTOR C WHERE C.TUTOR_ID = S.TUTOR_ID) AS TUTOR_NAME "
				+ "FROM SUBSCRIPTION_PACKAGE S";
		String existingFilterQueryString = "WHERE TUTOR_ID = :tutorId";
		final String existingSorterQueryString = "ORDER BY START_DATE_MILLIS ASC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_PACKAGE_LIST : {
				existingFilterQueryString += " AND END_DATE_MILLIS IS NULL";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_PACKAGE_LIST : {
				existingFilterQueryString += " AND END_DATE_MILLIS IS NOT NULL";
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForCustomer(final String grid, final Long customerId, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", customerId);
		final String baseQuery = "SELECT "
				+ "S.*, "
				+ "(SELECT NAME FROM SUBSCRIBED_CUSTOMER C WHERE C.CUSTOMER_ID = S.CUSTOMER_ID) AS CUSTOMER_NAME, "
				+ "(SELECT NAME FROM REGISTERED_TUTOR C WHERE C.TUTOR_ID = S.TUTOR_ID) AS TUTOR_NAME "
				+ "FROM SUBSCRIPTION_PACKAGE S";
		String existingFilterQueryString = "WHERE CUSTOMER_ID = :customerId";
		final String existingSorterQueryString = "ORDER BY START_DATE_MILLIS ASC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_PACKAGE_LIST : {
				existingFilterQueryString += " AND END_DATE_MILLIS IS NULL";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_PACKAGE_LIST : {
				existingFilterQueryString += " AND END_DATE_MILLIS IS NOT NULL";
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
}
