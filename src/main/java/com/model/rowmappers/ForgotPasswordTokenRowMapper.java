package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.ForgotPasswordToken;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class ForgotPasswordTokenRowMapper implements RowMapper<ForgotPasswordToken> {

	@Override
	public ForgotPasswordToken mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
		forgotPasswordToken.setForgotPasswordTokenSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("forgotPasswordTokenSerialId"), String.class));
		forgotPasswordToken.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("userId"), String.class));
		forgotPasswordToken.setUserName(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("userName"), String.class));
		forgotPasswordToken.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("userType"), String.class));
		forgotPasswordToken.setToken(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("token"), String.class));
		forgotPasswordToken.setIssueDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("issueDateMillis"), Long.class));
		forgotPasswordToken.setExpiryDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("expiryDateMillis"), Long.class));
		forgotPasswordToken.setIsValid(ExceptionUtils.exceptionHandlerForRowMapper(row, forgotPasswordToken.resolveColumnNameForMapping("isValid"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(forgotPasswordToken, row, rowNum);
		return forgotPasswordToken;
	}

}
