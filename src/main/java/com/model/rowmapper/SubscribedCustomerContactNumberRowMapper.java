package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.SubscribedCustomerContactNumber;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class SubscribedCustomerContactNumberRowMapper implements RowMapper<SubscribedCustomerContactNumber> {

	@Override
	public SubscribedCustomerContactNumber mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SubscribedCustomerContactNumber subscribedCustomerContactNumber = new SubscribedCustomerContactNumber();
		subscribedCustomerContactNumber.setSubscribedCustomerContactNumberSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomerContactNumber.resolveColumnNameForMapping("subscribedCustomerContactNumberSerialId"), String.class));
		subscribedCustomerContactNumber.setCustomerSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, subscribedCustomerContactNumber.resolveColumnNameForMapping("customerSerialId"), String.class));
		UserUtils.mapUserPseudoColumnsForOtherContactNumbers(subscribedCustomerContactNumber, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(subscribedCustomerContactNumber, row, rowNum);
		return subscribedCustomerContactNumber;
	}
}
