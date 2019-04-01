package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.BankDetail;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class BankDetailRowMapper implements RowMapper<BankDetail> {

	@Override
	public BankDetail mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final BankDetail bankDetail = new BankDetail();
		bankDetail.setBankAccountSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("bankAccountSerialId"), String.class));
		bankDetail.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("tutorSerialId"), String.class));
		bankDetail.setBankName(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("bankName"), String.class));
		bankDetail.setAccountNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("accountNumber"), String.class));
		bankDetail.setIfscCode(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("ifscCode"), String.class));
		bankDetail.setAccountHolderName(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("accountHolderName"), String.class));
		bankDetail.setIsDefault(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("isDefault"), String.class));
		bankDetail.setIsApproved(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("isApproved"), String.class));
		bankDetail.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("whoActed"), String.class));
		bankDetail.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("whoActedName"), String.class));
		bankDetail.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		bankDetail.setRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, bankDetail.resolveColumnNameForMapping("remarks"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(bankDetail, row, rowNum);
		return bankDetail;
	}

}
