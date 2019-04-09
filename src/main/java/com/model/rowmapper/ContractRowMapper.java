package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Contract;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class ContractRowMapper implements RowMapper<Contract> {

	@Override
	public Contract mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Contract contract = new Contract();
		contract.setContractSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("contractSerialId"), String.class));
		contract.setContractType(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("contractType"), String.class));
		contract.setInitiatedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("initiatedDateMillis"), Long.class));
		contract.setTerminatedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("terminatedDateMillis"), Long.class));
		contract.setIsActive(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("isActive"), String.class));
		contract.setFsKey(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("fsKey"), String.class));
		contract.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		contract.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("updatedBy"), String.class));
		contract.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, contract.resolveColumnNameForMapping("updatedByName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(contract, row, rowNum);
		return contract;
	}

}
