package com.model;

import java.util.LinkedList;
import java.util.List;

public class WorkbookReport {
	
	private List<WorkbookSheet> sheets;
	private String objectReportSwitch;
	
	public class WorkbookSheet {
		private String sheetName;
		private List<?> records;
		private Class<?> recordObjectType;
		
		<T extends ApplicationWorkbookObject> WorkbookSheet(final String sheetName, List<T> records, Class<T> recordObjectType) {
			this.sheetName = sheetName;
			this.records = records;
			this.recordObjectType = recordObjectType;
		}

		public String getSheetName() {
			return sheetName;
		}

		@SuppressWarnings("unchecked")
		public <T extends ApplicationWorkbookObject> List<T> getRecords() {
			return (List<T>) records;
		}

		@SuppressWarnings("unchecked")
		public <T extends ApplicationWorkbookObject> Class<T> getRecordObjectType() {
			return (Class<T>) recordObjectType;
		}
	}
	
	public WorkbookReport(final String objectReportSwitch) {
		this.sheets = new LinkedList<WorkbookSheet>();
		this.objectReportSwitch = objectReportSwitch;
	}
	
	public <T extends ApplicationWorkbookObject> void createSheet(final String sheetName, final List<T> records, final Class<T> type) {
		sheets.add(new WorkbookSheet(sheetName, records, type));
	}
	
	public List<WorkbookSheet> getSheets() {
		return sheets;
	}

	public String getObjectReportSwitch() {
		return objectReportSwitch;
	}
}
