package com.utils;

import com.constants.GridComponentConstants;

public class QueryUtils implements GridComponentConstants {
	
	public static String createQueryToPickupLimitedResult (
			final String baseQuery,
			String existingFilterQueryString,
			String existingSorterQueryString,
			final Integer resultLimit
	) {
		// @ TODO - Write logic to do this 
		return baseQuery;
	}	
	
	public static String createQueryWithFilterAndSorter (
			final String baseQuery,
			String existingFilterQueryString,
			String existingSorterQueryString,
			String additionalFilterQueryString,
			String additionalSorterQueryString
	) {
		LoggerUtils.logOnConsole("baseQuery >> " + baseQuery);
		LoggerUtils.logOnConsole("baseQuery >> " + existingFilterQueryString);
		LoggerUtils.logOnConsole("baseQuery >> " + existingSorterQueryString);
		LoggerUtils.logOnConsole("baseQuery >> " + additionalFilterQueryString);
		LoggerUtils.logOnConsole("baseQuery >> " + additionalSorterQueryString);
		final String completeQuery = WHITESPACE +  baseQuery + WHITESPACE +  existingFilterQueryString + WHITESPACE + existingSorterQueryString;
		LoggerUtils.logOnConsole("completeQuery >> " + completeQuery);
		return completeQuery;
	}	
}
