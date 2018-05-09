package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.TutorDocument;
import com.utils.ExceptionUtils;

public class TutorDocumentRowMapper implements RowMapper<TutorDocument> {

	@Override
	public TutorDocument mapRow(ResultSet row, int rowNum) throws SQLException {
		final TutorDocument tutorDocument = new TutorDocument();
		tutorDocument.setDocumentId(ExceptionUtils.exceptionHandlerForRowMapper(row, "DOCUMENT_ID", Long.class));
		tutorDocument.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_ID", Long.class));
		tutorDocument.setFsKey(ExceptionUtils.exceptionHandlerForRowMapper(row, "FS_KEY", String.class));
		tutorDocument.setFilename(ExceptionUtils.exceptionHandlerForRowMapper(row, "FILENAME", String.class));
		tutorDocument.setIsApproved(ExceptionUtils.exceptionHandlerForRowMapper(row, "IS_APPROVED", String.class));
		tutorDocument.setWhoActed(ExceptionUtils.exceptionHandlerForRowMapper(row, "WHO_ACTED", String.class));
		tutorDocument.setActionDate(ExceptionUtils.exceptionHandlerForRowMapper(row, "ACTION_DATE", Timestamp.class));
		tutorDocument.setRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, "REMARKS", String.class));
		return tutorDocument;
	}

}
