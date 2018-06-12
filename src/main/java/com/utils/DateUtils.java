package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import com.constants.DateConstants;

public class DateUtils implements DateConstants {
	
	public static String parseDateInIndianDTFormat(final Date date) {
		if (!isValid(date))
			return null;
		final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_INDIA);
		return formatter.format(date);
	}
	
	public static Date convertToIndianTimeZone(final Date localDate) {
		if (!isValid(localDate))
			return null;
		final DateTime dateTime = new DateTime(localDate);
		final DateTime dateTimeIndia = dateTime.withZone(DT_ZONE_INDIA);
		final Date dateInIndia = dateTimeIndia.toLocalDateTime().toDate(); 
		return dateInIndia;
	}
	
	public static Date convertToIndianTimeZone(final Long localDateTimeMillis) {
		if (!isValid(localDateTimeMillis))
			return null;
		return convertToIndianTimeZone(new Date(localDateTimeMillis));
	}
	
	public static String parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(final Date localDate) {
		if (!isValid(localDate))
			return null;
		return parseDateInIndianDTFormat(convertToIndianTimeZone(localDate));
	}
	
	public static String parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(final Long localDateTimeMillis) {
		if (!isValid(localDateTimeMillis))
			return null;
		return parseDateInIndianDTFormat(convertToIndianTimeZone(localDateTimeMillis));
	}
	
	private static boolean isValid(final Date date) {
		return (null != date);
	}
	
	private static boolean isValid(final Long dateTimeMillis) {
		return (null != dateTimeMillis && dateTimeMillis > 0);
	}
}
