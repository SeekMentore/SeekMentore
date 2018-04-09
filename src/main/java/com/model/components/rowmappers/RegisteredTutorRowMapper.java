package com.model.components.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.components.publicaccess.RegisteredTutor;

public class RegisteredTutorRowMapper implements RowMapper<RegisteredTutor>, RegisteredTutorConstants {

	@Override
	public RegisteredTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final RegisteredTutor registeredTutor = new RegisteredTutor();
		registeredTutor.setTutorId(row.getString("TUTOR_ID"));
		registeredTutor.setName(row.getString("NAME"));
		registeredTutor.setContactNumber(row.getString("CONTACT_NUMBER"));
		registeredTutor.setEmailId(row.getString("EMAIL_ID"));
		return registeredTutor;
	}

}
