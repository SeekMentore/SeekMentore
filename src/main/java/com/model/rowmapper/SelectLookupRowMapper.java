package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.SelectLookupConstants;
import com.model.components.commons.SelectLookup;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class SelectLookupRowMapper implements RowMapper<SelectLookup>, SelectLookupConstants {

	@Override
	public SelectLookup mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final SelectLookup selectLookup = new SelectLookup();
		selectLookup.setValue(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("value"), String.class));
		selectLookup.setLabel(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("label"), String.class));
		selectLookup.setCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("category"), String.class));
		selectLookup.setOrderOfCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("orderOfCategory"), Integer.class));
		selectLookup.setOrderInCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("orderInCategory"), Integer.class));
		selectLookup.setDescription(ExceptionUtils.exceptionHandlerForRowMapper(row, selectLookup.resolveColumnNameForMapping("description"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(selectLookup, row, rowNum);
		return selectLookup;
	}
}
