package com.utils;

import java.util.List;

import com.constants.GridComponentConstants;
import com.model.GridComponentObject;
import com.model.gridcomponent.Filter;
import com.model.gridcomponent.GridComponent;
import com.model.gridcomponent.Sorter;

public class GridQueryUtils implements GridComponentConstants {
	
	public static String getOrderDescription(final Integer order) {
		switch(order) {
			case 1 : return ASCENDING_ORDER;
			case 2 : return DESCENDING_ORDER;
		}
		return null;
	}
	
	public static String createSorterQuery(final List<Sorter> sorterList, final GridComponentObject gridComponentObject) {
		final StringBuilder sorterQuery = new StringBuilder(EMPTY_STRING);
		if (!sorterList.isEmpty()) {
			sorterQuery.append(ORDER_BY_APPENDER);
			for (final Sorter sorter : sorterList) {
				if (!ORDER_BY_APPENDER.equals(sorterQuery.toString())) {
					sorterQuery.append(COMMA_APPENDER);
				}
				sorterQuery.append(gridComponentObject.resolveColumnNameForMapping(sorter.getMapping())).append(WHITESPACE).append(getOrderDescription(sorter.getOrder()));
			}
		}
		LoggerUtils.logOnConsole("GRID SORTER QUERY >> " + sorterQuery.toString());
		return sorterQuery.toString();
	}
	
	public static String createFilterQuery(final List<Filter> filterList, final GridComponentObject gridComponentObject) {
		StringBuilder filterQuery = new StringBuilder(EMPTY_STRING);
		boolean filtersAttached = false;
		if (!filterList.isEmpty()) {
			filterQuery.append(WHERE_APPENDER);
			for (final Filter filter : filterList) {
				switch(filter.getType()) {
					case COLUMN_FILTER_MAPPING_STRING : {
						if (ValidationUtils.checkStringAvailability(filter.getStringValue())) {
							filtersAttached = true;
							if (!WHERE_APPENDER.equals(filterQuery.toString())) {
								filterQuery.append(AND_APPENDER);
							}
							if (filter.getTextCaseSensitiveSearch()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(LIKE_ALL_APPENDER).append(filter.getStringValue()).append(LIKE_ALL_APPENDER_CLOSURE);
							} else {
								filterQuery.append(UPPER_APPENDER).append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(") like '%").append(filter.getStringValue().toUpperCase()).append(LIKE_ALL_APPENDER_CLOSURE);
							}
						}
						break;
					}
					case COLUMN_FILTER_MAPPING_NUMBER : {
						if (ValidationUtils.checkObjectAvailability(filter.getLessThan()) || ValidationUtils.checkObjectAvailability(filter.getEqualTo()) || ValidationUtils.checkObjectAvailability(filter.getGreaterThan())) {
							filtersAttached = true;
							if (!WHERE_APPENDER.equals(filterQuery.toString())) {
								filterQuery.append(AND_APPENDER);
							}
							if (null != filter.getLessThan() || null != filter.getEqualTo() || null != filter.getGreaterThan()) {
								filterQuery.append(LEFT_BRACKET_APPENDER);
							}
							if (null != filter.getLessThan()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(LESS_THAN_APPENDER).append(filter.getLessThan());
								if (null != filter.getEqualTo() || null != filter.getGreaterThan()) {
									filterQuery.append(OR_APPENDER);
								}
							}
							if (null != filter.getEqualTo()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(EQUAL_TO_APPENDER).append(filter.getEqualTo());
								if (null != filter.getGreaterThan()) {
									filterQuery.append(OR_APPENDER);
								}
							}
							if (null != filter.getGreaterThan()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(GREATER_THAN_APPENDER).append(filter.getGreaterThan()).append(WHITESPACE);
							}
							if (null != filter.getLessThan() || null != filter.getEqualTo() || null != filter.getGreaterThan()) {
								filterQuery.append(RIGHT_BRACKET_APPENDER);
							}
						}
						break;
					}
					case COLUMN_FILTER_MAPPING_DATE : {
						if (ValidationUtils.checkObjectAvailability(filter.getBeforeDateMillis()) || ValidationUtils.checkObjectAvailability(filter.getOnDateMillis()) || ValidationUtils.checkObjectAvailability(filter.getAfterDateMillis())) {
							filtersAttached = true;
							if (!WHERE_APPENDER.equals(filterQuery.toString())) {
								filterQuery.append(AND_APPENDER);
							}
							if (null != filter.getBeforeDateMillis() || null != filter.getOnDateMillis() || null != filter.getAfterDateMillis()) {
								filterQuery.append(LEFT_BRACKET_APPENDER);
							}
							if (null != filter.getBeforeDateMillis()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(LESS_THAN_APPENDER).append(filter.getBeforeDateMillis());
								if (null != filter.getOnDateMillis() || null != filter.getAfterDateMillis()) {
									filterQuery.append(OR_APPENDER);
								}
							}
							if (null != filter.getOnDateMillis()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(EQUAL_TO_APPENDER).append(filter.getOnDateMillis());
								if (null != filter.getAfterDateMillis()) {
									filterQuery.append(OR_APPENDER);
								}
							}
							if (null != filter.getAfterDateMillis()) {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(GREATER_THAN_APPENDER).append(filter.getAfterDateMillis()).append(WHITESPACE);
							}
							if (null != filter.getBeforeDateMillis() || null != filter.getOnDateMillis() || null != filter.getAfterDateMillis()) {
								filterQuery.append(RIGHT_BRACKET_APPENDER);
							}
						}
						break;
					}
					case COLUMN_FILTER_MAPPING_LIST : {
						if (ValidationUtils.checkNonEmptyList(filter.getListValue())) {
							filtersAttached = true;
							if (!WHERE_APPENDER.equals(filterQuery.toString())) {
								filterQuery.append(AND_APPENDER);
							}
							if (filter.getMultiList()) {
								filterQuery.append(LEFT_BRACKET_APPENDER);
								for (int i = 0; i < filter.getListValue().size(); i++) {
									final String value = filter.getListValue().get(i);
									filterQuery.append(" upper(").append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(") like '%;").append(value.toUpperCase()).append(";%' ");
									filterQuery.append(" OR upper(").append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(") like '").append(value.toUpperCase()).append(";%' ");
									filterQuery.append(" OR upper(").append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(") like '%;").append(value.toUpperCase()).append("' ");
									if (i != filter.getListValue().size() - 1) {
										filterQuery.append(OR_APPENDER);
									}
								}
								filterQuery.append(RIGHT_BRACKET_APPENDER);
							} else {
								filterQuery.append(gridComponentObject.resolveColumnNameForMapping(filter.getMapping())).append(IN_APPENDER);
								for (int i = 0; i < filter.getListValue().size(); i++) {
									final String value = filter.getListValue().get(i);
									filterQuery.append(APOSTROPHE + value + APOSTROPHE);
									if (i != filter.getListValue().size() - 1) {
										filterQuery.append(COMMA_APPENDER);
									}
								}
								filterQuery.append(RIGHT_BRACKET_APPENDER);
							}
						}
						break;
					}
				}
			}
			if (!filtersAttached) {
				filterQuery = new StringBuilder(EMPTY_STRING);
			}
		}
		LoggerUtils.logOnConsole("GRID FILTER QUERY >> " + filterQuery.toString());
		return filterQuery.toString();
	}
	
	public static String createGridQuery (
			final String baseQuery,
			String existingFilterQueryString,
			String existingSorterQueryString,
			final GridComponent gridComponent
	) throws InstantiationException, IllegalAccessException {
		final Integer start = gridComponent.getStart(); 
		final Integer end = gridComponent.getEnd(); 
		final Boolean isPagingAvailable = gridComponent.getPagingAvailable();
		String additionalFilterQueryString = gridComponent.getAdditionalFilterQueryString();
		String additionalSorterQueryString = gridComponent.getAdditionalSorterQueryString();
		final GridComponentObject gridComponentObject = gridComponent.getGridComponentObjectClass().newInstance();
		String filterQueryString = createFilterQuery(gridComponent.getFilterList(), gridComponentObject);
		String sorterQueryString = createSorterQuery(gridComponent.getSorterList(), gridComponentObject);
		
		String completeQuery = EMPTY_STRING;
		
		String completeFilterQuery = EMPTY_STRING;
		if (ValidationUtils.checkStringAvailability(existingFilterQueryString)) {
			completeFilterQuery += WHITESPACE + existingFilterQueryString;
			if (ValidationUtils.checkStringAvailability(filterQueryString)) {
				filterQueryString = filterQueryString.replace(WHERE_CLAUSE, AND_CLAUSE);
			}
			if (ValidationUtils.checkStringAvailability(additionalFilterQueryString)) {
				additionalFilterQueryString = additionalFilterQueryString.replace(WHERE_CLAUSE, AND_CLAUSE);
			}
		}
		if (ValidationUtils.checkStringAvailability(filterQueryString)) {
			if (ValidationUtils.checkStringAvailability(additionalFilterQueryString)) {
				additionalFilterQueryString = additionalFilterQueryString.replace(WHERE_CLAUSE, AND_CLAUSE);
			}
			completeFilterQuery += WHITESPACE + filterQueryString;
		}
		if (ValidationUtils.checkStringAvailability(additionalFilterQueryString)) {
			completeFilterQuery += WHITESPACE + additionalFilterQueryString;
		}
		
		String completeSorterQuery = EMPTY_STRING;
		if (ValidationUtils.checkStringAvailability(existingSorterQueryString)) {
			completeSorterQuery += WHITESPACE + existingSorterQueryString;
			if (ValidationUtils.checkStringAvailability(sorterQueryString)) {
				sorterQueryString = sorterQueryString.replace(ORDER_BY_CLAUSE, COMMA);
			}
			if (ValidationUtils.checkStringAvailability(additionalSorterQueryString)) {
				additionalSorterQueryString = additionalSorterQueryString.replace(ORDER_BY_CLAUSE, COMMA);
			}
		}
		if (ValidationUtils.checkStringAvailability(sorterQueryString)) {
			if (ValidationUtils.checkStringAvailability(additionalSorterQueryString)) {
				additionalSorterQueryString = additionalSorterQueryString.replace(ORDER_BY_CLAUSE, COMMA);
			}
			completeSorterQuery += WHITESPACE + sorterQueryString;
		}
		if (ValidationUtils.checkStringAvailability(additionalSorterQueryString)) {
			completeSorterQuery += WHITESPACE + additionalSorterQueryString;
		}		
		
		if (isPagingAvailable) {
			final String coreQuery =  WHITESPACE +  baseQuery + WHITESPACE +  completeFilterQuery;
			final String mainQueryPseudoTable = " (" + coreQuery + WHITESPACE + completeSorterQuery + ") AS MAINQUERYPSEUDOTABLE";
			final String totalRecordsPseudoTable = " (SELECT COUNT(1) AS RECORD_COUNT FROM (" + coreQuery + ") AS COUNTPSEUDOTABLE) AS TOTALRECORDSPSEUDOTABLE";
			final String resultPseudoTable = " (SELECT TOTALRECORDSPSEUDOTABLE.RECORD_COUNT AS TOTAL_RECORDS, MAINQUERYPSEUDOTABLE.* FROM " + mainQueryPseudoTable + COMMA_APPENDER + totalRecordsPseudoTable + ") AS RESULTPSEUDOTABLE";
			final String rownumPseudoTable = " (SELECT @row_number:=0) AS ROWNUMPSEUDOTABLE";
			final String completeQueryPseudoTable = "(SELECT (@row_number:=@row_number + 1) AS RNUM, RESULTPSEUDOTABLE.* FROM " + resultPseudoTable + COMMA_APPENDER + rownumPseudoTable + ") AS COMPLETEQUERYPSEUDOTABLE";
			completeQuery += " SELECT COMPLETEQUERYPSEUDOTABLE.* FROM " + completeQueryPseudoTable + " WHERE RNUM BETWEEN " + start + " AND " + end + WHITESPACE;
		} else {
			completeQuery += WHITESPACE +  baseQuery + WHITESPACE +  completeFilterQuery + WHITESPACE + completeSorterQuery;
		}
		LoggerUtils.logOnConsole("GRID QUERY >> " + completeQuery);
		return completeQuery;
	}
	
}
