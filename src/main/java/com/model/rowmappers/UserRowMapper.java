package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.User;
import com.utils.ExceptionUtils;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet row, int rowNum) throws SQLException {
		final User user = new User();
		user.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, "NAME", String.class));
		user.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		user.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_TYPE", String.class));
		user.setEncyptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD", String.class));
		return user;
	}

}
