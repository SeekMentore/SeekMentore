package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.RegisteredTutorContactNumber;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class RegisteredTutorContactNumberRowMapper implements RowMapper<RegisteredTutorContactNumber> {

	@Override
	public RegisteredTutorContactNumber mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final RegisteredTutorContactNumber registeredTutorContactNumber = new RegisteredTutorContactNumber();
		registeredTutorContactNumber.setRegisteredTutorContactNumberSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutorContactNumber.resolveColumnNameForMapping("registeredTutorContactNumberSerialId"), String.class));
		registeredTutorContactNumber.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutorContactNumber.resolveColumnNameForMapping("tutorSerialId"), String.class));
		UserUtils.mapUserPseudoColumnsForOtherContactNumbers(registeredTutorContactNumber, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(registeredTutorContactNumber, row, rowNum);
		return registeredTutorContactNumber;
	}
}
