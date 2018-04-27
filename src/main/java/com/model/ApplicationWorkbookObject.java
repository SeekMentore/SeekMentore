package com.model;

public interface ApplicationWorkbookObject {
	
	Object[] getReportHeaders(final String reportSwitch);
	Object[] getReportRecords(final String reportSwitch);
}
