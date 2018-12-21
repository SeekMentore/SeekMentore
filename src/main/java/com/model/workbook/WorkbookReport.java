package com.model.workbook;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.model.ApplicationWorkbookObject;

public class WorkbookReport implements Serializable {
	
	private static final long serialVersionUID = -9215359627401277574L;
	
	private List<WorkbookSheet> sheets;
	
	public WorkbookReport() {
		this.sheets = new LinkedList<WorkbookSheet>();
	}
	
	@SuppressWarnings("hiding")
	public <T extends ApplicationWorkbookObject> void createSheet(
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType, 
		final String objectReportSwitch, 
		final Integer rowPadding, 
		final Integer columnPadding
	) throws InstantiationException, IllegalAccessException {
		sheets.add(new WorkbookSheet(sheetName, objectTypeRecords, recordObjectType, objectReportSwitch, rowPadding, columnPadding));
	}
	
	@SuppressWarnings("hiding")
	public <T extends ApplicationWorkbookObject> void createSheet(
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType, 
		final String objectReportSwitch, 
		final Integer rowPadding, 
		final Integer columnPadding,
		final Integer columnWidth
	) throws InstantiationException, IllegalAccessException {
		sheets.add(new WorkbookSheet(sheetName, objectTypeRecords, recordObjectType, objectReportSwitch, rowPadding, columnPadding, columnWidth));
	}
	
	@SuppressWarnings("hiding")
	public <T extends ApplicationWorkbookObject> void createSheet(
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType,
		final String objectReportSwitch 
	) throws InstantiationException, IllegalAccessException {
		sheets.add(new WorkbookSheet(sheetName, objectTypeRecords, recordObjectType, objectReportSwitch));
	}
	
	@SuppressWarnings("hiding")
	public <T extends ApplicationWorkbookObject> void createSheet(
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType,
		final String objectReportSwitch,
		final Integer columnWidth
	) throws InstantiationException, IllegalAccessException {
		sheets.add(new WorkbookSheet(sheetName, objectTypeRecords, recordObjectType, objectReportSwitch, columnWidth));
	}
	
	public void createSheet(
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records, 
		final Integer rowPadding, 
		final Integer columnPadding
	) {
		sheets.add(new WorkbookSheet(sheetName, headers, records, rowPadding, columnPadding));
	}
	
	public void createSheet(
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records, 
		final Integer rowPadding, 
		final Integer columnPadding,
		final Integer columnWidth
	) {
		sheets.add(new WorkbookSheet(sheetName, headers, records, rowPadding, columnPadding, columnWidth));
	}
	
	public void createSheet(
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records
	) {
		sheets.add(new WorkbookSheet(sheetName, headers, records));
	}
	
	public void createSheet(
		final String sheetName, 
		final List<WorkbookHeader> headers, 
		final List<WorkbookRecord> records,
		final Integer columnWidth
	) {
		sheets.add(new WorkbookSheet(sheetName, headers, records, columnWidth));
	}
	
	public List<WorkbookSheet> getSheets() {
		return sheets;
	}
}
