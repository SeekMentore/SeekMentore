package com.service.components;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.dao.ApplicationDao;
import com.model.Employee;
import com.model.rowmapper.EmployeeRowMapper;
import com.service.QueryMapperService;

@Service(BeanConstants.BEAN_NAME_EMPLOYEE_SERVICE)
public class EmployeeService {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public Employee getEmployeeFromDbUsingUserId(final String userId) throws Exception {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find(queryMapperService.getQuerySQL("employee", "selectEmployee") 
										+ queryMapperService.getQuerySQL("employee", "employeeUserIdFilter"), paramsMap, new EmployeeRowMapper());
		}
		return null;
	}
}
