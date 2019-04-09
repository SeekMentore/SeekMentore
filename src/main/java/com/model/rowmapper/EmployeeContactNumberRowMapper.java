package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.EmployeeContactNumber;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class EmployeeContactNumberRowMapper implements RowMapper<EmployeeContactNumber> {

	@Override
	public EmployeeContactNumber mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final EmployeeContactNumber employeeContactNumber = new EmployeeContactNumber();
		employeeContactNumber.setEmployeeContactNumberSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, employeeContactNumber.resolveColumnNameForMapping("employeeContactNumberSerialId"), String.class));
		employeeContactNumber.setEmployeeSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, employeeContactNumber.resolveColumnNameForMapping("employeeSerialId"), String.class));
		UserUtils.mapUserPseudoColumnsForOtherContactNumbers(employeeContactNumber, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(employeeContactNumber, row, rowNum);
		return employeeContactNumber;
	}
}
