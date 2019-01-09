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
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_NOTIFICATION_SERVICE)
public class NotificationService {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}

	public List<AlertReminder> getAlertAndReminderList(final String userId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = queryMapperService.getQuerySQL("notification", "selectAlertReminder");
		final String existingFilterQueryString = queryMapperService.getQuerySQL("notification", "alertReminderRecipientUserIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("notification", "alertReminderExistingSorter");
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new AlertReminderRowMapper());
	}
	
	public List<Task> getTaskList(final String userId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = queryMapperService.getQuerySQL("notification", "selectTask");
		final String existingFilterQueryString = queryMapperService.getQuerySQL("notification", "taskRecipientUserIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("notification", "taskExistingSorter");
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new TaskRowMapper());
	}
	
	public List<Workflow> getWorkflowList(final String userId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", userId);
		final String baseQuery = queryMapperService.getQuerySQL("notification", "selectWorkflow");
		final String existingFilterQueryString = queryMapperService.getQuerySQL("notification", "workflowRecipientUserIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("notification", "workflowExistingSorter");
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new WorkflowRowMapper());
	}
}
