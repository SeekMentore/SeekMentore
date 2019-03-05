package com.model.workbook;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.model.ApplicationWorkbookObject;

public class WorkbookReport implements Serializable {
	
	private static final long serialVersionUID = -9215359627401277574L;
	
	private List<WorkbookSheet> sheets;
	private Integer defaultCellWidth = 7000; // Default value
	
	public WorkbookReport() {
		this.sheets = new LinkedList<WorkbookSheet>();
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
	
	public void createSheet(
		final String sheetName, 
		final List<WorkbookRecord> workbookRecords
	) {
		sheets.add(new WorkbookSheet(sheetName, workbookRecords));
	}
	
	public List<WorkbookSheet> getSheets() {
		return sheets;
	}

	public Integer getDefaultCellWidth() {
		return defaultCellWidth;
	}
}
