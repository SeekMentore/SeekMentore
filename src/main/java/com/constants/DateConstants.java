package com.constants;

import org.joda.time.DateTimeZone;

public interface DateConstants extends ApplicationConstants {
	
	String DATE_FORMAT_INDIA = "dd-M-yyyy hh:mm:ss a";
	DateTimeZone DT_ZONE_INDIA = DateTimeZone.forID("Asia/Kolkata");
	DateTimeZone DT_ZONE_NEW_YORK = DateTimeZone.forID("America/New_York");
	
}
