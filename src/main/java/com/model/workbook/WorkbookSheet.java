package com.model.workbook;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.model.ApplicationWorkbookObject;
import com.model.workbook.WorkbookCell.TypeOfStyleEnum;
import com.utils.ValidationUtils;

public class WorkbookSheet implements Serializable {
	
	private static final long serialVersionUID = -4892708145390188343L;
	
	private String sheetName;
	private Class<?> recordObjectType;
	private List<?> objectTypeRecords;
	private String objectReportSwitch;
	private Integer rowPadding = 1;
	private Integer columnPadding = 1;
	private List<WorkbookRecord> workbookRecords;
	
	<T extends ApplicationWorkbookObject> WorkbookSheet (
		final String sheetName, 
		final List<T> objectTypeRecords, 
		final Class<T> recordObjectType,
		final String objectReportSwitch 
	) throws InstantiationException, IllegalAccessException {
		this.sheetName = sheetName;
		this.objectTypeRecords = objectTypeRecords;
		this.recordObjectType = recordObjectType;
		this.objectReportSwitch = objectReportSwitch;
		computeWorkbookRowsFromRecordObjectTypeRecords();
		this.objectTypeRecords = null; // GC the object after Header & Record are computed
	}
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookRecord> workbookRecords
	) {
		this.sheetName = sheetName;
		this.workbookRecords = workbookRecords;
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

	public Integer getRowPadding() {
		return rowPadding;
	}

	public Integer getColumnPadding() {
		return columnPadding;
	}
	
	public void setRowPadding(Integer rowPadding) {
		this.rowPadding = rowPadding;
	}

	public void setColumnPadding(Integer columnPadding) {
		this.columnPadding = columnPadding;
	}
	
	public List<WorkbookRecord> getWorkbookRecords() {
		return workbookRecords;
	}

	private void computeWorkbookRowsFromRecordObjectTypeRecords() throws InstantiationException, IllegalAccessException {
		if (!ValidationUtils.checkNonEmptyList(this.workbookRecords)) {
			this.workbookRecords = new LinkedList<WorkbookRecord>();
		}
		final List<WorkbookCell> headerCells1 = new LinkedList<WorkbookCell>();
		for (final Object value : this.getRecordObjectType().newInstance().getReportHeaders(this.objectReportSwitch)) {
			headerCells1.add(new WorkbookCell(value, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 2));
		}
		this.workbookRecords.add(new WorkbookRecord(headerCells1));
		final List<WorkbookCell> headerCells = new LinkedList<WorkbookCell>();
		for (final Object value : this.getRecordObjectType().newInstance().getReportHeaders(this.objectReportSwitch)) {
			headerCells.add(new WorkbookCell(value, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL));
		}
		this.workbookRecords.add(new WorkbookRecord(headerCells));
		for(final ApplicationWorkbookObject workbookObject : this.getObjectTypeRecords()) {
			final List<WorkbookCell> recordCells = new LinkedList<WorkbookCell>();
			for (final Object value : workbookObject.getReportRecords(this.objectReportSwitch)) {
				recordCells.add(new WorkbookCell(value, true, TypeOfStyleEnum.SOLID_FOREGROUND_GOLD));
			}
			this.workbookRecords.add(new WorkbookRecord(recordCells));
		}
		for(final ApplicationWorkbookObject workbookObject : this.getObjectTypeRecords()) {
			final List<WorkbookCell> recordCells = new LinkedList<WorkbookCell>();
			for (final Object value : workbookObject.getReportRecords(this.objectReportSwitch)) {
				recordCells.add(new WorkbookCell(value, true, 2, 3));
			}
			this.workbookRecords.add(new WorkbookRecord(recordCells));
		}
	}
}
