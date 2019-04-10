package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

import com.constants.DateConstants;

public class DateUtils implements DateConstants {
	
	public static Date parseYYYYMMDD(final String dateString) {
		if (ValidationUtils.checkStringAvailability(dateString)) {
			try {
				final SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
				return simpleDateFormatter.parse(dateString);
			} catch(ParseException e) {
				return null;
			}
		}
		return null;
	}
	
	public static String parseDateInFormat(final Date date, final String format) {
		if (!isValid(date))
			return null;
		final SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	public static String parseDateInIndianDTFormat(final Date date) {
		if (!isValid(date))
			return null;
		final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_INDIA_WITH_COMPLETE_MONTH_AND_NO_SECONDS);
		return formatter.format(date);
	}
	
	public static String parseDateInIndianDTFormatWithoutTime(final Date date) {
		if (!isValid(date))
			return null;
		final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_INDIA_WITH_COMPLETE_MONTH_WT);
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
	
	public static String parseDateInIndianDTFormatAfterConvertingToIndianTimeZoneWithoutTime(final Long localDateTimeMillis) {
		if (!isValid(localDateTimeMillis))
			return null;
		return parseDateInIndianDTFormatWithoutTime(convertToIndianTimeZone(localDateTimeMillis));
	}
	
	public static String parseDateInSpecifiedFormatAfterConvertingToIndianTimeZone(final Long localDateTimeMillis, final String format) {
		if (!isValid(localDateTimeMillis))
			return null;
		return parseDateInFormat(convertToIndianTimeZone(localDateTimeMillis), format);
	}
	
	public static String parseSystemDateInSpecifiedFormatAfterConvertingToIndianTimeZone(final String format) {
		final Long localDateTimeMillis = new Date().getTime();
		if (!isValid(localDateTimeMillis))
			return null;
		return parseDateInFormat(convertToIndianTimeZone(localDateTimeMillis), format);
	}
	
	private static boolean isValid(final Date date) {
		return (null != date);
	}
	
	private static boolean isValid(final Long dateTimeMillis) {
		return (null != dateTimeMillis && dateTimeMillis > 0);
	}
}
