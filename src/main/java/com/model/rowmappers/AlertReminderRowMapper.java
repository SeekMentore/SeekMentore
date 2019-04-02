package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.components.AlertReminder;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.NotificationUtils;
import com.utils.RowMapperUtils;

public class AlertReminderRowMapper implements RowMapper<AlertReminder> {

	@Override
	public AlertReminder mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final AlertReminder alertReminder = new AlertReminder();
		alertReminder.setAlertReminderSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, alertReminder.resolveColumnNameForMapping("alertReminderSerialId"), String.class));
		NotificationUtils.mapNotificationColumnsForRecords(alertReminder, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(alertReminder, row, rowNum);
		return alertReminder;
	}

}
