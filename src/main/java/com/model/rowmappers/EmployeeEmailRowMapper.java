package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.EmployeeEmail;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class EmployeeEmailRowMapper implements RowMapper<EmployeeEmail> {

	@Override
	public EmployeeEmail mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final EmployeeEmail employeeEmail = new EmployeeEmail();
		employeeEmail.setEmployeeEmailIdId(ExceptionUtils.exceptionHandlerForRowMapper(row, employeeEmail.resolveColumnNameForMapping("employeeEmailIdId"), Long.class));
		employeeEmail.setEmployeeId(ExceptionUtils.exceptionHandlerForRowMapper(row, employeeEmail.resolveColumnNameForMapping("employeeId"), Long.class));
		UserUtils.mapUserPseudoColumnsForOtherEmails(employeeEmail, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(employeeEmail, row, rowNum);
		return employeeEmail;
	}
}
