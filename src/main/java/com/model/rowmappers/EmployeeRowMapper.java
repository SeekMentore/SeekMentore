package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Employee;
import com.utils.ExceptionUtils;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet row, int rowNum) throws SQLException {
		final Employee employee = new Employee();
		employee.setEmployeeId(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMPLOYEE_ID", String.class));
		employee.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, "NAME", String.class));
		employee.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		employee.setEmailDomain(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMAIL_DOMAIN", String.class));
		employee.setEncyptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD", String.class));
		employee.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_TYPE", String.class));
		return employee;
	}

}
