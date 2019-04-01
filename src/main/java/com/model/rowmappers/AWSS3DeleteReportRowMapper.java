package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.AWSS3DeleteReport;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class AWSS3DeleteReportRowMapper implements RowMapper<AWSS3DeleteReport> {

	@Override
	public AWSS3DeleteReport mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final AWSS3DeleteReport awsS3DeleteReport = new AWSS3DeleteReport();
		awsS3DeleteReport.setS3DeleteSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("s3DeleteSerialId"), String.class));
		awsS3DeleteReport.setOccuredAtMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("occuredAtMillis"), Long.class));
		awsS3DeleteReport.setFsKeyToBeDeleted(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("fsKeyToBeDeleted"), String.class));
		awsS3DeleteReport.setFsKeyRecycled(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("fsKeyRecycled"), String.class));
		awsS3DeleteReport.setIsFolder(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("isFolder"), String.class));
		awsS3DeleteReport.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("userId"), String.class));
		awsS3DeleteReport.setUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, awsS3DeleteReport.resolveColumnNameForMapping("userType"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(awsS3DeleteReport, row, rowNum);
		return awsS3DeleteReport;
	}

}
