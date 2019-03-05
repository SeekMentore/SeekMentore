package com.model.workbook;

public class WorkbookCell {
	
	private Object value;
	
	public WorkbookCell(final Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
