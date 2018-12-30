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
		employee.setEmployeeId(ExceptionUtils.exceptionHandlerForRowMapper(row, employee.resolveColumnNameForMapping("employeeId"), Long.class));
		employee.setEmailDomain(ExceptionUtils.exceptionHandlerForRowMapper(row, employee.resolveColumnNameForMapping("emailDomain"), String.class));
		UserUtils.mapUserPseudoColumnsForRecords(employee, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(employee, row, rowNum);
		return employee;
	}

}
