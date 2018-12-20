package commons.model.Workbook;

import java.util.List;

public class WorkbookSheet {
	private String sheetName;
	private List<WorkbookRecord> records;
	private List<WorkbookHeader> headers;
	private Integer rowPadding;
	private Integer columnPadding;
	
	public WorkbookSheet(final String sheetName, List<WorkbookHeader> headers, List<WorkbookRecord> records, Integer rowPadding, Integer columnPadding) {
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
		this.rowPadding = rowPadding;
		this.columnPadding = columnPadding;
	}
	
	public WorkbookSheet(final String sheetName, List<WorkbookHeader> headers, List<WorkbookRecord> records) {
		// No padding
		this.sheetName = sheetName;
		this.headers = headers;
		this.records = records;
		this.rowPadding = 0;
		this.columnPadding = 0;
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
}
