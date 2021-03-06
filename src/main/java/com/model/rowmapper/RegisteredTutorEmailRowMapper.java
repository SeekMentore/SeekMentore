package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.RegisteredTutorEmail;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class RegisteredTutorEmailRowMapper implements RowMapper<RegisteredTutorEmail> {

	@Override
	public RegisteredTutorEmail mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final RegisteredTutorEmail registeredTutorEmail = new RegisteredTutorEmail();
		registeredTutorEmail.setRegisteredTutorEmailIdSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutorEmail.resolveColumnNameForMapping("registeredTutorEmailIdSerialId"), String.class));
		registeredTutorEmail.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutorEmail.resolveColumnNameForMapping("tutorSerialId"), String.class));
		UserUtils.mapUserPseudoColumnsForOtherEmails(registeredTutorEmail, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(registeredTutorEmail, row, rowNum);
		return registeredTutorEmail;
	}
}
