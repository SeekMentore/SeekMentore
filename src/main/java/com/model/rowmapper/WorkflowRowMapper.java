package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Workflow;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.NotificationUtils;
import com.utils.RowMapperUtils;

public class WorkflowRowMapper implements RowMapper<Workflow> {

	@Override
	public Workflow mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Workflow workflow = new Workflow();
		workflow.setWorkflowSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, workflow.resolveColumnNameForMapping("workflowSerialId"), String.class));
		NotificationUtils.mapNotificationColumnsForRecords(workflow, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(workflow, row, rowNum);
		return workflow;
	}

}
