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
		LoggerUtils.logOnConsoleForcefully("baseQuery >> " + baseQuery);
		LoggerUtils.logOnConsoleForcefully("baseQuery >> " + existingFilterQueryString);
		LoggerUtils.logOnConsoleForcefully("baseQuery >> " + existingSorterQueryString);
		LoggerUtils.logOnConsoleForcefully("baseQuery >> " + additionalFilterQueryString);
		LoggerUtils.logOnConsoleForcefully("baseQuery >> " + additionalSorterQueryString);
		final String completeQuery = WHITESPACE +  baseQuery + WHITESPACE +  existingFilterQueryString + WHITESPACE + existingSorterQueryString;
		LoggerUtils.logOnConsoleForcefully("completeQuery >> " + completeQuery);
		return completeQuery;
	}	
}
