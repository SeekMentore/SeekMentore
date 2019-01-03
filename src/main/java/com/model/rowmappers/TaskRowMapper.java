package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.Task;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.NotificationUtils;
import com.utils.RowMapperUtils;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final Task task = new Task();
		task.setTaskId(ExceptionUtils.exceptionHandlerForRowMapper(row, task.resolveColumnNameForMapping("taskId"), Long.class));
		NotificationUtils.mapNotificationColumnsForRecords(task, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(task, row, rowNum);
		return task;
	}

}
