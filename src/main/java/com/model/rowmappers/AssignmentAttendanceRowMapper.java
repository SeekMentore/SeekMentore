package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.AssignmentAttendance;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class AssignmentAttendanceRowMapper implements RowMapper<AssignmentAttendance> {

	@Override
	public AssignmentAttendance mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final AssignmentAttendance assignmentAttendance = new AssignmentAttendance();
		assignmentAttendance.setAssignmentAttendanceSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("assignmentAttendanceSerialId"), String.class));
		assignmentAttendance.setPackageAssignmentSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("packageAssignmentSerialId"), String.class));
		assignmentAttendance.setEntryDateTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("entryDateTimeMillis"), Long.class));
		assignmentAttendance.setExitDateTimeMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("exitDateTimeMillis"), Long.class));
		assignmentAttendance.setDurationHours(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("durationHours"), Integer.class));
		assignmentAttendance.setDurationMinutes(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("durationMinutes"), Integer.class));
		assignmentAttendance.setTopicsTaught(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("topicsTaught"), String.class));
		assignmentAttendance.setIsClassworkProvided(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("isClassworkProvided"), String.class));
		assignmentAttendance.setIsHomeworkProvided(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("isHomeworkProvided"), String.class));
		assignmentAttendance.setIsTestProvided(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("isTestProvided"), String.class));
		assignmentAttendance.setTutorRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("tutorRemarks"), String.class));
		assignmentAttendance.setTutorPunctualityIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("tutorPunctualityIndex"), String.class));
		assignmentAttendance.setPunctualityRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("punctualityRemarks"), String.class));
		assignmentAttendance.setTutorExpertiseIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("tutorExpertiseIndex"), String.class));
		assignmentAttendance.setExpertiseRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("expertiseRemarks"), String.class));
		assignmentAttendance.setTutorKnowledgeIndex(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("tutorKnowledgeIndex"), String.class));
		assignmentAttendance.setKnowledgeRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("knowledgeRemarks"), String.class));
		assignmentAttendance.setStudentRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("studentRemarks"), String.class));
		assignmentAttendance.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		assignmentAttendance.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("updatedBy"), String.class));
		assignmentAttendance.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("updatedByName"), String.class));
		assignmentAttendance.setUpdatedByUserType(ExceptionUtils.exceptionHandlerForRowMapper(row, assignmentAttendance.resolveColumnNameForMapping("updatedByUserType"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(assignmentAttendance, row, rowNum);
		return assignmentAttendance;
	}
}
