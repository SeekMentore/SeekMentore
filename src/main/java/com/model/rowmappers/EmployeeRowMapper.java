package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Employee;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.UserUtils;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet row, int rowNum) throws SQLException {
		final Employee employee = new Employee();
		employee.setEmployeeId(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMPLOYEE_ID", Long.class));
		employee.setEmailDomain(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMAIL_DOMAIN", String.class));
		UserUtils.mapUserPseudoColumnsForRecords(employee, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(employee, row, rowNum);
		return employee;
	}

}
