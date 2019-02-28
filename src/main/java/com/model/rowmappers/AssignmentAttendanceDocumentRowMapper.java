package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.AssignmentAttendanceDocument;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class AssignmentAttendanceDocumentRowMapper implements RowMapper<AssignmentAttendanceDocument> {

	@Override
	public AssignmentAttendanceDocument mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final AssignmentAttendanceDocument assignmentAttendanceDocument = new AssignmentAttendanceDocument();
		assignmentAttendanceDocument.setAssignmentAttendanceDocumentSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("assignmentAttendanceDocumentSerialId"), String.class));
		assignmentAttendanceDocument.setAssignmentAttendanceSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("assignmentAttendanceSerialId"), String.class));
		assignmentAttendanceDocument.setDocumentType(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("documentType"), String.class));
		assignmentAttendanceDocument.setFsKey(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("fsKey"), String.class));
		assignmentAttendanceDocument.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("filename"), String.class));
		assignmentAttendanceDocument.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		assignmentAttendanceDocument.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("whoActed"), String.class));
		assignmentAttendanceDocument.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("whoActedName"), String.class));
		assignmentAttendanceDocument.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		assignmentAttendanceDocument.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("updatedBy"), String.class));
		assignmentAttendanceDocument.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendanceDocument.resolveColumnNameForMapping("updatedByName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(assignmentAttendanceDocument, row, rowNum);
		return assignmentAttendanceDocument;
	}
}
