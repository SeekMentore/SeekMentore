package commons.model.Workbook;

public class WorkbookHeader {
	private Object[] header;
	private Integer howManyCellsPerColumn;
	
	public WorkbookHeader(final Object[] header, Integer howManyCellsPerColumn) {
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

	public void setHeader(Object[] header) {
		this.header = header;
	}

	public Integer getHowManyCellsPerColumn() {
		return howManyCellsPerColumn;
	}

	public void setHowManyCellsPerColumn(Integer howManyCellsPerColumn) {
		this.howManyCellsPerColumn = howManyCellsPerColumn;
	}
}
