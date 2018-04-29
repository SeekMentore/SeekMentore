package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.SelectLookupConstants;
import com.model.components.commons.SelectLookup;
import com.utils.ExceptionUtils;

public class SelectLookupRowMapper implements RowMapper<SelectLookup>, SelectLookupConstants {

	@Override
	public SelectLookup mapRow(ResultSet row, int rowNum) throws SQLException {
		final SelectLookup selectLookup = new SelectLookup();
		selectLookup.setValue(ExceptionUtils.exceptionHandlerForRowMapper(row, "VALUE", String.class));
		selectLookup.setLabel(ExceptionUtils.exceptionHandlerForRowMapper(row, "LABEL", String.class));
		selectLookup.setCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, "CATEGORY", String.class));
		selectLookup.setOrderOfCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, "ORDER_OF_CATEGORY", String.class));
		selectLookup.setOrderInCategory(ExceptionUtils.exceptionHandlerForRowMapper(row, "ORDER_IN_CATEGORY", String.class));
		selectLookup.setDescription(ExceptionUtils.exceptionHandlerForRowMapper(row, "DESCRIPTION", String.class));
		return selectLookup;
	}

}
