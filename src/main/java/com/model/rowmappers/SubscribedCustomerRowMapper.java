package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.components.SubscribedCustomer;

public class SubscribedCustomerRowMapper implements RowMapper<SubscribedCustomer>, SubscribedCustomerConstants {

	@Override
	public SubscribedCustomer mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
		subscribedCustomer.setCustomerId(row.getString("CUSTOMER_ID"));
		subscribedCustomer.setName(row.getString("NAME"));
		subscribedCustomer.setContactNumber(row.getString("CONTACT_NUMBER"));
		subscribedCustomer.setEmailId(row.getString("EMAIL_ID"));
		return subscribedCustomer;
	}

}
