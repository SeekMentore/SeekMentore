package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class MapRowMapper implements RowMapper<Map<String, Object>> {

	@Override
	public Map<String, Object> mapRow(ResultSet row, int rowNum) throws SQLException {
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		final ResultSetMetaData resultSetMetaData = row.getMetaData();
		// The column count starts from 1
		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
		  resultMap.put(resultSetMetaData.getColumnName(i), row.getObject(resultSetMetaData.getColumnName(i)));
		}
		return resultMap;
	}

}
