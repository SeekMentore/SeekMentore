package com.model.workbook;

import java.io.Serializable;
import java.util.List;

public class WorkbookSheet implements Serializable {
	
	private static final long serialVersionUID = -4892708145390188343L;
	
	private String sheetName;
	private Integer rowPadding = 1;
	private Integer columnPadding = 1;
	private List<WorkbookRecord> workbookRecords;
	
	public WorkbookSheet (
		final String sheetName, 
		final List<WorkbookRecord> workbookRecords
	) {
		this.sheetName = sheetName;
		this.workbookRecords = workbookRecords;
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
}
