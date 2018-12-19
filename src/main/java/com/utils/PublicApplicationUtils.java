package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.constants.GridComponentConstants;
import com.model.components.publicaccess.PublicApplication;

public class PublicApplicationUtils implements GridComponentConstants {
	
	public static void mapMigrationColumnsForRecords(final PublicApplication publicApplication, final ResultSet row, final int rowNum) throws SQLException {
		publicApplication.setIsDataMigrated(ExceptionUtils.exceptionHandlerForRowMapper(row, publicApplication.resolveColumnNameForMapping("isDataMigrated"), String.class));
		publicApplication.setWhenMigratedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, publicApplication.resolveColumnNameForMapping("whenMigratedMillis"), Long.class));
	}
}
