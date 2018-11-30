package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.dao.ApplicationDao;
import com.model.components.AlertReminder;
import com.model.components.Task;
import com.model.components.Workflow;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.AlertReminderRowMapper;
import com.model.rowmappers.TaskRowMapper;
import com.model.rowmappers.WorkflowRowMapper;
import com.utils.GridQueryUtils;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_NOTIFICATION_SERVICE)
public class NotificationService {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}

	public List<AlertReminder> getAlertAndReminderList(final String userId, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = "SELECT "
				+ "A.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = A.INITIATED_BY), A.INITIATED_BY) AS INITIATED_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = A.ACTION_BY), A.ACTION_BY) AS ACTION_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = A.RECIPIENT_USER_ID), A.RECIPIENT_USER_ID) AS RECIPIENT_USER_NAME "
				+ "FROM ALERT_REMINDER A";
		final String existingFilterQueryString = "WHERE RECIPIENT_USER_ID = :userId";
		final String existingSorterQueryString = "ORDER BY INITIATED_DATE_MILLIS ASC";
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new AlertReminderRowMapper());
	}
	
	public List<Task> getTaskList(final String userId, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = "SELECT "
				+ "T.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = T.INITIATED_BY), T.INITIATED_BY) AS INITIATED_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = T.ACTION_BY), T.ACTION_BY) AS ACTION_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = T.RECIPIENT_USER_ID), T.RECIPIENT_USER_ID) AS RECIPIENT_USER_NAME "
				+ "FROM TASK T";
		final String existingFilterQueryString = "WHERE RECIPIENT_USER_ID = :userId";
		final String existingSorterQueryString = "ORDER BY INITIATED_DATE_MILLIS ASC";
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new TaskRowMapper());
	}
	
	public List<Workflow> getWorkflowList(final String userId, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = "SELECT "
				+ "W.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = W.INITIATED_BY), W.INITIATED_BY) AS INITIATED_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = W.ACTION_BY), W.ACTION_BY) AS ACTION_BY_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = W.RECIPIENT_USER_ID), W.RECIPIENT_USER_ID) AS RECIPIENT_USER_NAME "
				+ "FROM WORKFLOW W";
		final String existingFilterQueryString = "WHERE RECIPIENT_USER_ID = :userId";
		final String existingSorterQueryString = "ORDER BY INITIATED_DATE_MILLIS ASC";
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new WorkflowRowMapper());
	}
}
