package com.model;

import com.constants.GridComponentConstants;

public abstract class GridComponentObject implements GridComponentConstants {
	
	private Integer gridRecordDataTotalRecords;

	public Integer getGridRecordDataTotalRecords() {
		return gridRecordDataTotalRecords;
	}

	public void setGridRecordDataTotalRecords(Integer gridRecordDataTotalRecords) {
		this.gridRecordDataTotalRecords = gridRecordDataTotalRecords;
	}
	
	public String resolveColumnNameForMapping(final String mappingProperty) {
		switch(mappingProperty) {
			case "gridRecordDataTotalRecords" : return "TOTAL_RECORDS";
		}
		return EMPTY_STRING;
	}
}
