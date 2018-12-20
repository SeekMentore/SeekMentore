package commons.model.Workbook;

import java.util.LinkedList;
import java.util.List;

public class WorkbookReport {
	
	private List<WorkbookSheet> sheets;
	private Integer columnWidth;
	private Integer recordThreshold = 16000;
	private boolean checkSheetThreshold = false;
	
	public WorkbookReport(Integer columnWidth) {
		this.sheets = new LinkedList<WorkbookSheet>();
		this.columnWidth = columnWidth;
	}
	
	public void createSheet(final String sheetName, final List<WorkbookHeader> headers, final List<WorkbookRecord> records, final Integer rowPadding, final Integer columnPadding) {
		sheets.add(new WorkbookSheet(sheetName, headers, records, rowPadding, columnPadding));
	}
	
	public List<WorkbookSheet> getSheets() {
		return sheets;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public Integer getRecordThreshold() {
		return recordThreshold;
	}

	public void setRecordThreshold(Integer recordThreshold) {
		this.recordThreshold = recordThreshold;
	}

	public boolean isCheckSheetThreshold() {
		return checkSheetThreshold;
	}
}
