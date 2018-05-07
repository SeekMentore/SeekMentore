package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.model.components.SubscribedCustomer;
import com.utils.ExceptionUtils;

public class SubscribedCustomerRowMapper implements RowMapper<SubscribedCustomer>, SubscribedCustomerConstants {

	@Override
	public SubscribedCustomer mapRow(ResultSet row, int rowNum) throws SQLException {
		final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
		subscribedCustomer.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, "CUSTOMER_ID", Long.class));
		subscribedCustomer.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, "NAME", String.class));
		subscribedCustomer.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, "CONTACT_NUMBER", String.class));
		subscribedCustomer.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMAIL_ID", String.class));
		return subscribedCustomer;
	}

}
