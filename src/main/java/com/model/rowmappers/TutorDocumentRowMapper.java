package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.TutorDocument;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;

public class TutorDocumentRowMapper implements RowMapper<TutorDocument> {

	@Override
	public TutorDocument mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final TutorDocument tutorDocument = new TutorDocument();
		tutorDocument.setDocumentId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("documentId"), Long.class));
		tutorDocument.setDocumentType(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("documentType"), String.class));
		tutorDocument.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("tutorId"), Long.class));
		tutorDocument.setFsKey(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("fsKey"), String.class));
		tutorDocument.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("filename"), String.class));
		tutorDocument.setIsApproved(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("isApproved"), String.class));
		tutorDocument.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("whoActed"), String.class));
		tutorDocument.setWhoActedName(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("whoActedName"), String.class));
		tutorDocument.setActionDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("actionDateMillis"), Long.class));
		tutorDocument.setRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, tutorDocument.resolveColumnNameForMapping("remarks"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(tutorDocument, row, rowNum);
		return tutorDocument;
	}

}
