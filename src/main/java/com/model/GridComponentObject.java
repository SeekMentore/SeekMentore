package com.model;

public abstract class GridComponentObject {
	
	private Integer gridRecordDataTotalRecords;

	public Integer getGridRecordDataTotalRecords() {
		return gridRecordDataTotalRecords;
	}

	public void setGridRecordDataTotalRecords(Integer gridRecordDataTotalRecords) {
		this.gridRecordDataTotalRecords = gridRecordDataTotalRecords;
	}
	
	public abstract String resolveColumnNameForMapping(final String mappingProperty);
}
