package com.model.workbook;

import java.io.Serializable;
import java.util.List;

public class WorkbookRecord implements Serializable {

	private static final long serialVersionUID = -8681265564543881119L;
	
	private List<WorkbookCell> recordCells;
	private Boolean isContinuedRecordInMergedRow = false;
	
	public WorkbookRecord(final List<WorkbookCell> recordCells) {
		this.recordCells = recordCells;
	}
	
	public List<WorkbookCell> getRecordCells() {
		return recordCells;
	}

	public Boolean getIsContinuedRecordInMergedRow() {
		return isContinuedRecordInMergedRow;
	}

	public void setIsContinuedRecordInMergedRow(Boolean isContinuedRecordInMergedRow) {
		this.isContinuedRecordInMergedRow = isContinuedRecordInMergedRow;
	}
}
