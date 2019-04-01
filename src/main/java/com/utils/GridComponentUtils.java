package com.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		gridComponentObject.setGridRecordDataTotalRecords(ExceptionUtils.exceptionHandlerForRowMapper(row, gridComponentObject.resolveColumnNameForMapping(GRID_RECORD_DATA_TOTAL_RECORDS), Integer.class));
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
				Boolean clubbedFilterMapping = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_CLUBBED_FILTER_MAPPING, Boolean.class);
				if (!ValidationUtils.checkObjectAvailability(clubbedFilterMapping)) {
					clubbedFilterMapping = false;
				}
				final List<String> clubbedFilterProperties = new ArrayList<String>();
				if (clubbedFilterMapping) {
					final JsonArray listValueJSONArray = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_ATTRIBUTE_CLUBBED_FILTER_PROPERTIES, JsonArray.class);
					for (Object listValueObject : listValueJSONArray) {
						final String value = listValueObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
						clubbedFilterProperties.add(value);
					}
				}
				Filter filter = null;
				switch (type) {
					case COLUMN_FILTER_MAPPING_STRING : {
						filter = getFilter(
								id, 
								type,
								mapping, 
								columnId, 
								multiList, 
								clubbedFilterMapping, 
								clubbedFilterProperties,
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_STRING_VALUE, String.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_TEXT_CASE_SENSITIVE_SEARCH, Boolean.class),
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null);
						break;
					}
					case COLUMN_FILTER_MAPPING_DATE : {
						filter = getFilter(
								id, 
								type,
								mapping, 
								columnId, 
								multiList, 
								clubbedFilterMapping, 
								clubbedFilterProperties,
								null,
								null,
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_BEFORE_DATE_MILLIS, Long.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_ON_DATE_MILLIS, Long.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_AFTER_DATE_MILLIS, Long.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_LOCAL_TIMEZONE_OFFSET_IN_MILLISECONDS, Long.class),
								null,
								null,
								null,
								null);
						break;
					}
					case COLUMN_FILTER_MAPPING_NUMBER : {
						filter = getFilter(
								id, 
								type,
								mapping, 
								columnId, 
								multiList, 
								clubbedFilterMapping, 
								clubbedFilterProperties,
								null,
								null,
								null,
								null,
								null,
								null,
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_LESS_THAN, Integer.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_EQUAL_TO, Integer.class),
								JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_GREATER_THAN, Integer.class),
								null);
						break;
					}
					case COLUMN_FILTER_MAPPING_LIST : {
						final JsonArray listValueJSONArray = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_FILTER_VALUE_LIST_VALUE, JsonArray.class);
						final List<String> listValue = new LinkedList<String>();
						for (final Object listValueObject : listValueJSONArray) {
							final String value = listValueObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
							listValue.add(value);
						}
						filter = getFilter(
								id, 
								type,
								mapping, 
								columnId, 
								multiList, 
								clubbedFilterMapping, 
								clubbedFilterProperties,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								null,
								listValue);
						break;
					}
				}
				filterList.add(filter);
			}
		}
		return filterList;
	}
	
	public static Filter getFilter (
			final String id,
			final String type,
			final String mapping,
			final String columnId,
			final Boolean multiList,
			final Boolean clubbedFilterMapping,
			final List<String> clubbedFilterProperties,
			final String stringValue,
			final Boolean textCaseSensitiveSearch,
			final Long beforeDateMillis,
			final Long onDateMillis,
			final Long afterDateMillis,
			Long localTimezoneOffsetInMilliseconds,
			final Integer lessThan,
			final Integer equalTo,
			final Integer greaterThan,
			final List<String> listValue
	) {
		final Filter filter = new Filter(id, type, mapping, columnId, multiList, clubbedFilterMapping, clubbedFilterProperties);
		switch (type) {
			case COLUMN_FILTER_MAPPING_STRING : {
				filter.setStringValue(stringValue);
				filter.setTextCaseSensitiveSearch(textCaseSensitiveSearch);
				break;
			}
			case COLUMN_FILTER_MAPPING_DATE : {
				if (!ValidationUtils.checkObjectAvailability(localTimezoneOffsetInMilliseconds)) {
					localTimezoneOffsetInMilliseconds = 0L;
				}
				filter.setLocalTimezoneOffsetInMilliseconds(localTimezoneOffsetInMilliseconds);
				if (ValidationUtils.checkObjectAvailability(beforeDateMillis)) {
					filter.setBeforeDateMillis(beforeDateMillis + localTimezoneOffsetInMilliseconds);
					filter.setBeforeDate(new Date(filter.getBeforeDateMillis()));
				}
				if (ValidationUtils.checkObjectAvailability(onDateMillis)) {
					filter.setOnDateMillis(onDateMillis + localTimezoneOffsetInMilliseconds);
					filter.setOnDate(new Date(filter.getOnDateMillis()));
				}
				if (ValidationUtils.checkObjectAvailability(afterDateMillis)) {
					filter.setAfterDateMillis(afterDateMillis + localTimezoneOffsetInMilliseconds);
					filter.setAfterDate(new Date(filter.getAfterDateMillis()));
				}
				break;
			}
			case COLUMN_FILTER_MAPPING_NUMBER : {
				filter.setLessThan(lessThan);
				filter.setEqualTo(equalTo);
				filter.setGreaterThan(greaterThan);
				break;
			}
			case COLUMN_FILTER_MAPPING_LIST : {
				filter.setListValue(listValue);
				break;
			}
		}
		return filter;
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
				Boolean clubbedSorterMapping = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_CLUBBED_FILTER_MAPPING, Boolean.class);
				if (!ValidationUtils.checkObjectAvailability(clubbedSorterMapping)) {
					clubbedSorterMapping = false;
				}
				final List<String> clubbedSorterProperties = new ArrayList<String>();
				if (clubbedSorterMapping) {
					final JsonArray listValueJSONArray = JSONUtils.getValueFromJSONObject(jsonObject, COLUMN_SORTER_ATTRIBUTE_CLUBBED_FILTER_PROPERTIES, JsonArray.class);
					for (Object listValueObject : listValueJSONArray) {
						final String value = listValueObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
						clubbedSorterProperties.add(value);
					}
				}
				final Sorter sorter = new Sorter(id, type, mapping, columnId, columnName, order, clubbedSorterMapping, clubbedSorterProperties);
				sorterList.add(sorter);
			}
		}
		return sorterList;
	}
}
