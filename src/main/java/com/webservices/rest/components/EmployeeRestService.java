package com.webservices.rest.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.ScopeConstants;
import com.model.components.AlertReminder;
import com.model.components.Task;
import com.model.components.Workflow;
import com.utils.JSONUtils;
import com.utils.LoggerUtils;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path("/employee") 
public class EmployeeRestService extends AbstractRestWebservice implements RestMethodConstants {
	
	@Path("/alertsRemindersGrid")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String alertsRemindersGrid (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		List<AlertReminder> data = new LinkedList<AlertReminder>();
		data.add(new AlertReminder(1L));
		data.add(new AlertReminder(2L));
		data.add(new AlertReminder(3L));
		data.add(new AlertReminder(4L));
		data.add(new AlertReminder(5L));
		data.add(new AlertReminder(6L));
		data.add(new AlertReminder(7L));
		data.add(new AlertReminder(8L));
		data.add(new AlertReminder(9L));
		data.add(new AlertReminder(10L));		
		restResponse.put("data", data);
		restResponse.put("totalRecords", data.size());
		restResponse.put("success", true);
		restResponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/tasksGrid")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String tasksGrid (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		List<Task> data = new LinkedList<Task>();
		data.add(new Task(1L));
		data.add(new Task(2L));
		data.add(new Task(3L));
		data.add(new Task(4L));
		data.add(new Task(5L));
		data.add(new Task(6L));
		data.add(new Task(7L));
		data.add(new Task(8L));
		data.add(new Task(9L));
		data.add(new Task(10L));		
		restResponse.put("data", data);
		restResponse.put("totalRecords", data.size());
		restResponse.put("success", true);
		restResponse.put("message", "");
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path("/workflowsGrid")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String workflowsGrid (
			@FormParam(GRID_COMPONENT_START) final String start,
			@FormParam(GRID_COMPONENT_LIMIT) final String limit,
			@FormParam(GRID_COMPONENT_OTHER_PARAMS) final String otherParams,
			@FormParam(GRID_COMPONENT_FILTERS) final String filters,
			@FormParam(GRID_COMPONENT_SORTERS) final String sorters,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		final Map<String, Object> restResponse = new HashMap<String, Object>();
		List<Workflow> data = new LinkedList<Workflow>();
		data.add(new Workflow(1L));
		data.add(new Workflow(2L));
		data.add(new Workflow(3L));
		data.add(new Workflow(4L));
		data.add(new Workflow(5L));
		data.add(new Workflow(6L));
		data.add(new Workflow(7L));
		data.add(new Workflow(8L));
		data.add(new Workflow(9L));
		data.add(new Workflow(10L));		
		restResponse.put("data", data);
		restResponse.put("totalRecords", data.size());
		restResponse.put("success", true);
		restResponse.put("message", "");
		LoggerUtils.logOnConsole("user " + JSONUtils.convertObjToJSONString(getActiveUser(request), "USER_OBJ"));
		return JSONUtils.convertObjToJSONString(restResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
	}
}
