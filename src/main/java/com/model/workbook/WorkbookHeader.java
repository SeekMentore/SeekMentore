package com.model.workbook;

import java.io.Serializable;

public class WorkbookHeader implements Serializable {

	private static final long serialVersionUID = 7382214405588651014L;
	
	private Object[] header;
	private Integer howManyCellsPerColumn;
	
	public WorkbookHeader(final Object[] header, final Integer howManyCellsPerColumn) {
		this.header = header;
		this.howManyCellsPerColumn = howManyCellsPerColumn;
	}
	
	public WorkbookHeader(final Object[] header) {
		this.header = header;
		this.howManyCellsPerColumn = 1;
	}

	public Object[] getHeader() {
		return header;
	}

	public Integer getHowManyCellsPerColumn() {
		return howManyCellsPerColumn;
	}
}
