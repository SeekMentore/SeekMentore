package com.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.constants.JNDIandControlConfigurationConstants;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.context.AppContext;

public class RowMapperUtils implements ApplicationConstants {
	
	public static void showQueryFetch(final ResultSet row, final Integer rowNum, final RowMapper<?> rowMapperClass) throws SQLException {
		Boolean isServerLocal = false;
		try {
			isServerLocal = getJNDIandControlConfigurationLoadService().getServerName().equals(JNDIandControlConfigurationConstants.SERVER_NAME_LOCAL);
		} catch(Exception e) {}
		if (isServerLocal) {
			final ResultSetMetaData resultSetMetaData = row.getMetaData();
			if (rowNum == 0) {
				LoggerUtils.logOnConsole("All Columns fetched from the Query");
				final StringBuilder columnNames = new StringBuilder(EMPTY_STRING);
				columnNames.append(LEFT_SQUARE_BRACKET);
				for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
					columnNames.append(resultSetMetaData.getColumnName(i));
					if (i < resultSetMetaData.getColumnCount()) {
						columnNames.append(WHITESPACE).append(WHITESPACE);
					}
				}
				columnNames.append(RIGHT_SQUARE_BRACKET);
				LoggerUtils.logOnConsole(columnNames.toString().trim());
				LoggerUtils.logOnConsole("All Column Data fetched from the Query");
			}
			final StringBuilder columnValues = new StringBuilder(EMPTY_STRING);
			columnValues.append(LEFT_SQUARE_BRACKET);
			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				//columnValues.append(ExceptionUtils.exceptionHandlerForRowMapper(row, resultSetMetaData.getColumnName(i), Object.class));
				try {
					columnValues.append(row.getObject(resultSetMetaData.getColumnName(i)));
				} catch(Exception e) {
					columnValues.append("CANNOT_FETCH_FOR_COLUMN##["+resultSetMetaData.getColumnName(i)+"]");
				}
				if (i < resultSetMetaData.getColumnCount()) {
					columnValues.append(WHITESPACE).append(WHITESPACE);
				}
			}
			columnValues.append(RIGHT_SQUARE_BRACKET);
			LoggerUtils.logOnConsole(columnValues.toString().trim());
		}
	}
	
	public static JNDIandControlConfigurationLoadService getJNDIandControlConfigurationLoadService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
	}
}
