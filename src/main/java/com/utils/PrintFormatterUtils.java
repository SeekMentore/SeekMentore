package com.utils;

import com.constants.PrintFormatterConstants;

public class PrintFormatterUtils implements PrintFormatterConstants {
	
	public static String startATable() {
		return TABLE_TAG_START;
	}
	
	public static String endATable() {
		return TABLE_TAG_END;
	}
	
	public static String startARow() {
		return TABLE_ROW_TAG_START;
	}
	
	public static String endARow() {
		return TABLE_ROW_TAG_END;
	}
	
	public static String startAColumn() {
		return TABLE_COLUMN_TAG_START;
	}
	
	public static String endAColumn() {
		return TABLE_COLUMN_TAG_END;
	}
	
	public static String startATableWithRow() {
		return startATable() + startARow();
	}
	
	public static String endATableWithRow() {
		return endARow() + endATable();
	}
	
	public static String startATableWithRowWithColumn() {
		return startATableWithRow() + startAColumn();
	}
	
	public static String endATableWithRowWithColumn() {
		return endAColumn() + endATableWithRow();
	}
	
	public static String printADataInColumn(Object data) {
		return startAColumn() + String.valueOf(data) + endAColumn();
	}
	
	public static String printALabelAndDataInRowWithTwoColumns(Object label, Object data) {
		return startARow() + startAColumn() + String.valueOf(label) + endAColumn() + startAColumn() + String.valueOf(data) + endAColumn() + endARow();
	}

}
