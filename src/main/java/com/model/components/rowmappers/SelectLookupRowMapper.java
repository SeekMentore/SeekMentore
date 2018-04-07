package com.model.components.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.SelectLookupConstants;
import com.model.components.commons.SelectLookup;

public class SelectLookupRowMapper implements RowMapper<SelectLookup>, SelectLookupConstants {

	@Override
	public SelectLookup mapRow(ResultSet row, int rowNum) throws SQLException {
		final SelectLookup selectLookup = new SelectLookup();
		selectLookup.setValue(row.getString("VALUE"));
		selectLookup.setLabel(row.getString("LABEL"));
		selectLookup.setCategory(row.getString("CATEGORY"));
		selectLookup.setOrderOfCategory(row.getString("ORDER_OF_CATEGORY"));
		selectLookup.setOrderInCategory(row.getString("ORDER_IN_CATEGORY"));
		selectLookup.setDescription(row.getString("DESCRIPTION"));
		return selectLookup;
	}

}
