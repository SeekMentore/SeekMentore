package com.model.workbook;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class WorkbookReport implements Serializable {
	
	private static final long serialVersionUID = -9215359627401277574L;
	
	private List<WorkbookSheet> sheets;
	private Integer defaultCellWidth = 7000; // Default value
	
	public WorkbookReport() {
		this.sheets = new LinkedList<WorkbookSheet>();
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
	
	public void setDefaultCellWidth(Integer defaultCellWidth) {
		this.defaultCellWidth = defaultCellWidth;
	}
}
