package com.model.workbook;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.model.ApplicationWorkbookObject;
import com.utils.ValidationUtils;

public class WorkbookSheet implements Serializable {
	
	private static final long serialVersionUID = -4892708145390188343L;
	
	private String sheetName;
	private Class<?> recordObjectType;
	private List<?> objectTypeRecords;
	private String objectReportSwitch;
	private Integer rowPadding = 1;
	private Integer columnPadding = 1;
	private Integer columnWidth = 7000; // Default value
	private List<WorkbookHeader> headers;
	private List<WorkbookRecord> records;
	
	<T extends ApplicationWorkbookObject> WorkbookSheet (
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType, 
		final String objectReportSwitch, 
		final Integer rowPadding, 
		final Integer columnPadding
	) throws InstantiationException, IllegalAccessException {
		this.sheetName = sheetName;
		this.objectTypeRecords = objectTypeRecords;
		this.recordObjectType = recordObjectType;
		this.objectReportSwitch = objectReportSwitch;
		this.rowPadding = rowPadding;
		this.columnPadding = columnPadding;
		computeWorkbookHeaderFromRecordObjectType();
		computeWorkbookRecordsFromObjectTypeRecords();
		this.objectTypeRecords = null; // GC the object after Header & Record are computed
	}
	
	<T extends ApplicationWorkbookObject> WorkbookSheet (
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType, 
		final String objectReportSwitch, 
		final Integer rowPadding, 
		final Integer columnPadding,
		final Integer columnWidth
	) throws InstantiationException, IllegalAccessException {
		this.sheetName = sheetName;
		this.objectTypeRecords = objectTypeRecords;
		this.recordObjectType = recordObjectType;
		this.objectReportSwitch = objectReportSwitch;
		this.rowPadding = rowPadding;
		this.columnPadding = columnPadding;
		this.columnWidth = columnWidth;
		computeWorkbookHeaderFromRecordObjectType();
		computeWorkbookRecordsFromObjectTypeRecords();
		this.objectTypeRecords = null; // GC the object after Header & Record are computed
	}
	
	<T extends ApplicationWorkbookObject> WorkbookSheet (
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType,
		final String objectReportSwitch 
	) throws InstantiationException, IllegalAccessException {
		// No padding
		this.sheetName = sheetName;
		this.objectTypeRecords = objectTypeRecords;
		this.recordObjectType = recordObjectType;
		this.objectReportSwitch = objectReportSwitch;
		computeWorkbookHeaderFromRecordObjectType();
		computeWorkbookRecordsFromObjectTypeRecords();
		this.objectTypeRecords = null; // GC the object after Header & Record are computed
	}
	
	<T extends ApplicationWorkbookObject> WorkbookSheet (
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType,
		final String objectReportSwitch,
		final Integer columnWidth
	) throws InstantiationException, IllegalAccessException {
		// No padding
		this.sheetName = sheetName;
		this.objectTypeRecords = objectTypeRecords;
		this.recordObjectType = recordObjectType;
		this.objectReportSwitch = objectReportSwitch;
		this.columnWidth = columnWidth;
		computeWorkbookHeaderFromRecordObjectType();
		computeWorkbookRecordsFromObjectTypeRecords();
		this.objectTypeRecords = null; // GC the object after Header & Record are computed
	}
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records, 
		final Integer rowPadding, 
		final Integer columnPadding
	) {
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
		this.rowPadding = rowPadding;
		this.columnPadding = columnPadding;
	}
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records, 
		final Integer rowPadding, 
		final Integer columnPadding,
		final Integer columnWidth
	) {
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
		this.rowPadding = rowPadding;
		this.columnPadding = columnPadding;
		this.columnWidth = columnWidth;
	}
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records
	) {
		// No padding
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
	}
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records,
		final Integer columnWidth
	) {
		// No padding
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
		this.columnWidth = columnWidth;
	}

	@SuppressWarnings("unchecked")
	private <T extends ApplicationWorkbookObject> List<T> getObjectTypeRecords() {
		return (List<T>) objectTypeRecords;
	}

	@SuppressWarnings("unchecked")
	private <T extends ApplicationWorkbookObject> Class<T> getRecordObjectType() {
		return (Class<T>) recordObjectType;
	}
	
	public String getSheetName() {
		return sheetName;
	}

	public List<WorkbookRecord> getRecords() {
		return records;
	}

	public List<WorkbookHeader> getHeaders() {
		return headers;
	}

	public Integer getRowPadding() {
		return rowPadding;
	}

	public Integer getColumnPadding() {
		return columnPadding;
	}
	
	public String getObjectReportSwitch() {
		return objectReportSwitch;
	}
	
	public Integer getColumnWidth() {
		return columnWidth;
	}

	private void computeWorkbookHeaderFromRecordObjectType() throws InstantiationException, IllegalAccessException {
		if (!ValidationUtils.checkNonEmptyList(this.headers)) {
			this.headers = new LinkedList<WorkbookHeader>();
		}
		this.headers.add(new WorkbookHeader(this.getRecordObjectType().newInstance().getReportHeaders(this.getObjectReportSwitch())));
	}
	
	private void computeWorkbookRecordsFromObjectTypeRecords() {
		if (!ValidationUtils.checkNonEmptyList(this.records)) {
			this.records = new LinkedList<WorkbookRecord>();
		}
		for(final ApplicationWorkbookObject workbookObject : this.getObjectTypeRecords()) {
			final List<WorkbookCell> record = new LinkedList<WorkbookCell>();
			for (final Object value : workbookObject.getReportRecords(this.getObjectReportSwitch())) {
				record.add(new WorkbookCell(value));
			}
			this.records.add(new WorkbookRecord(record));
		}
	}
}
