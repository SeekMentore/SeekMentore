package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Employee;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Employee employee = new Employee();
		employee.setEmployeeSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, employee.resolveColumnNameForMapping("employeeSerialId"), String.class));
		employee.setEmailDomain(ExceptionUtils.exceptionHandlerForRowMapper(row, employee.resolveColumnNameForMapping("emailDomain"), String.class));
		UserUtils.mapUserPseudoColumnsForRecords(employee, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(employee, row, rowNum);
		return employee;
	}

}
