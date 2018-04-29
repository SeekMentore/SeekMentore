package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.User;

public class UserRowMapper implements RowMapper<User>, SubscribedCustomerConstants {

	@Override
	public User mapRow(ResultSet row, int rowNum) throws SQLException {
		final User user = new User();
		user.setEmployeeId(row.getString("EMPLOYEE_ID"));
		user.setName(row.getString("NAME"));
		user.setUserId(row.getString("USER_ID"));
		user.setEmailDomain(row.getString("EMAIL_DOMAIN"));
		user.setUserType(row.getString("USER_TYPE"));
		user.setEncyptedPassword(row.getString("ENCYPTED_PASSWORD"));
		return user;
	}

}
