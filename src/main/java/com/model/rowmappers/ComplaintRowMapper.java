package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Complaint;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class ComplaintRowMapper implements RowMapper<Complaint> {

	@Override
	public Complaint mapRow(ResultSet row, int rowNum) throws SQLException {
		final Complaint complaint = new Complaint();
		complaint.setComplaintId(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintId"), Long.class));
		complaint.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("name"), String.class));
		complaint.setComplaintFiledDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintFiledDateMillis"), Long.class));
		complaint.setComplaintStatus(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintStatus"), String.class));
		complaint.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("userId"), String.class));
		complaint.setComplaintDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintDetails"), String.class));
		complaint.setComplaintUser(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintUser"), String.class));
		complaint.setComplaintResponse(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("complaintResponse"), String.class));
		complaint.setIsContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("isContacted"), String.class));
		complaint.setWhoContacted(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("whoContacted"), String.class));
		complaint.setContactedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("contactedDateMillis"), Long.class));
		complaint.setResolved(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("resolved"), String.class));
		complaint.setNotResolved(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("notResolved"), String.class));
		complaint.setNotResolvedReason(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("notResolvedReason"), String.class));
		complaint.setWhoNotResolved(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("whoNotResolved"), String.class));
		complaint.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		complaint.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("updatedBy"), String.class));
		complaint.setWhoContactedName(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("whoContactedName"), String.class));
		complaint.setWhoNotResolvedName(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("whoNotResolvedName"), String.class));
		complaint.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, complaint.resolveColumnNameForMapping("updatedByName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(complaint, row, rowNum);
		return complaint;
	}

}
