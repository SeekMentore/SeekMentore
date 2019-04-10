package com.constants;

import org.joda.time.DateTimeZone;

public interface DateConstants extends ApplicationConstants {
	
	String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	String DATE_FORMAT_INDIA = "dd-M-yyyy hh:mm:ss a";
	String DATE_FORMAT_INDIA_WT = "dd-M-yyyy";
	String DATE_FORMAT_INDIA_WITH_COMPLETE_MONTH_AND_NO_SECONDS = "dd-MMM-yyyy hh:mm a";
	String DATE_FORMAT_INDIA_WITH_COMPLETE_MONTH_WT = "dd-MMM-yyyy";
	String DATE_FORMAT_ONLY_HOUR_AND_MINUTE_12H = "hh:mm a";
	DateTimeZone DT_ZONE_INDIA = DateTimeZone.forID("Asia/Kolkata");
	DateTimeZone DT_ZONE_NEW_YORK = DateTimeZone.forID("America/New_York");
	
}
