package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.SubscribedCustomerEmail;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class SubscribedCustomerEmailRowMapper implements RowMapper<SubscribedCustomerEmail> {

	@Override
	public SubscribedCustomerEmail mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SubscribedCustomerEmail subscribedCustomerEmail = new SubscribedCustomerEmail();
		subscribedCustomerEmail.setSubscribedCustomerEmailIdId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomerEmail.resolveColumnNameForMapping("subscribedCustomerEmailIdId"), Long.class));
		subscribedCustomerEmail.setCustomerId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomerEmail.resolveColumnNameForMapping("customerId"), Long.class));
		UserUtils.mapUserPseudoColumnsForOtherEmails(subscribedCustomerEmail, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscribedCustomerEmail, row, rowNum);
		return subscribedCustomerEmail;
	}
}
