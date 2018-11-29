package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.constants.GridComponentConstants;
import com.model.GridComponentObject;
import com.model.gridcomponent.Filter;
import com.model.gridcomponent.GridComponent;
import com.model.gridcomponent.Sorter;

public class GridComponentUtils implements GridComponentConstants {
	
	public static void mapGridPseudoColumnsForRecords(final GridComponentObject gridComponentObject, final ResultSet row, final int rowNum) throws SQLException {
		gridComponentObject.setGridRecordDataTotalRecords(ExceptionUtils.exceptionHandlerForRowMapper(row, gridComponentObject.resolveColumnNameForMapping("gridRecordDataTotalRecords"), Integer.class));
	}
	
	public static Integer getTotalRecords(final List<? extends GridComponentObject> dataList, final GridComponent gridComponent) {
		if (!ValidationUtils.checkNonEmptyList(dataList)) return 0;
		if (gridComponent.getPagingAvailable()) {
			return dataList.get(0).getGridRecordDataTotalRecords();
		} else {
			return dataList.size();
		}
	}
	
	public static List<Filter> createFilterListFromFilterJSON(final String filters) {
		final List<Filter> filterList = new LinkedList<Filter>();
		if (ValidationUtils.checkStringAvailability(filters)) {
			final JsonArray jsonArray = JSONUtils.getJSONArrayFromString(filters);
			for (final Object object : jsonArray) {
				final JsonObject jsonObject = (JsonObject) object;
				final String id = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_ID, String.class);
				final String type = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_TYPE, String.class);
				final String mapping = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_MAPPING, String.class);
				final String columnId = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_COLUMN_ID, String.class);
				final Boolean multiList = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_MULTILIST, Boolean.class);
				final Filter filter = new Filter(id, type, mapping, columnId, multiList);
				switch (type) {
					case COLUMN_FILTER_MAPPING_STRING : {
						final String stringValue = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_STRING_VALUE, String.class);
						final Boolean textCaseSensitiveSearch = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_TEXT_CASE_SENSITIVE_SEARCH, Boolean.class);
						filter.setStringValue(stringValue);
						filter.setTextCaseSensitiveSearch(textCaseSensitiveSearch);
						break;
					}
					case COLUMN_FILTER_MAPPING_DATE : {
						final Long beforeDateMillis = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_BEFORE_DATE_MILLIS, Long.class);
						final Long onDateMillis = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_ON_DATE_MILLIS, Long.class);
						final Long afterDateMillis = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_AFTER_DATE_MILLIS, Long.class);
						filter.setBeforeDateMillis(beforeDateMillis);
						filter.setBeforeDate(new Date(beforeDateMillis));
						filter.setOnDateMillis(onDateMillis);
						filter.setOnDate(new Date(onDateMillis));
						filter.setAfterDateMillis(afterDateMillis);
						filter.setAfterDate(new Date(afterDateMillis));
						break;
					}
					case COLUMN_FILTER_MAPPING_NUMBER : {
						final Integer lessThan = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_LESS_THAN, Integer.class);
						final Integer equalTo = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_EQUAL_TO, Integer.class);
						final Integer greaterThan = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_GREATER_THAN, Integer.class);
						filter.setLessThan(lessThan);
						filter.setEqualTo(equalTo);
						filter.setGreaterThan(greaterThan);
						break;
					}
					case COLUMN_FILTER_MAPPING_LIST : {
						final JsonArray listValueJSONArray = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_LIST_VALUE, JsonArray.class);
						filter.setListValue(new LinkedList<String>());
						for (Object listValueObject : listValueJSONArray) {
							final String value = listValueObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
							filter.addListValue(value);
						}
						break;
					}
				}
				filterList.add(filter);
			}
		}
		return filterList;
	}
	
	public static List<Sorter> createSorterListFromSorterJSON(final String sorters) {
		final List<Sorter> sorterList = new LinkedList<Sorter>();
		if (ValidationUtils.checkStringAvailability(sorters)) {
			final JsonArray jsonArray = JSONUtils.getJSONArrayFromString(sorters);
			for (final Object object : jsonArray) {
				final JsonObject jsonObject = (JsonObject) object;
				final String id = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_ID, String.class);
				final String type = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_TYPE, String.class);
				final String mapping = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_MAPPING, String.class);
				final String columnId = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_COLUMN_ID, String.class);
				final String columnName = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_COLUMN_NAME, String.class);
				final Integer order = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_ORDER, Integer.class);
				final Sorter sorter = new Sorter(id, type, mapping, columnId, columnName, order);
				sorterList.add(sorter);
			}
		}
		return sorterList;
	}
}
