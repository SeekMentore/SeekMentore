package com.model.workbook;

import java.io.Serializable;
import java.util.List;

public class WorkbookHeader implements Serializable {

	private static final long serialVersionUID = 7382214405588651014L;
	
	private List<WorkbookCell> headerCells;
	private Integer howManyCellsPerColumn;
	
	public WorkbookHeader(final List<WorkbookCell> headerCells, final Integer howManyCellsPerColumn) {
		this.headerCells = headerCells;
		this.howManyCellsPerColumn = howManyCellsPerColumn;
	}
	
	public WorkbookHeader(final List<WorkbookCell> headerCells) {
		this.headerCells = headerCells;
		this.howManyCellsPerColumn = 1;
	}

	public List<WorkbookCell> getHeaderCells() {
		return headerCells;
	}

	public Integer getHowManyCellsPerColumn() {
		return howManyCellsPerColumn;
	}
}
