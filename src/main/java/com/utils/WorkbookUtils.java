package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.constants.WorkbookConstants;
import com.model.workbook.WorkbookHeader;
import com.model.workbook.WorkbookRecord;
import com.model.workbook.WorkbookReport;
import com.model.workbook.WorkbookSheet;

public class WorkbookUtils implements WorkbookConstants {
	
	private static void setCellMerge (
			final XSSFSheet sheet, 
			final Integer numRow, 
			final Integer untilRow, 
			final Integer numCol, 
			final Integer untilCol, 
			final XSSFCellStyle cellStyle, 
			final Boolean applyAnyCellStyle
	) {
	    final CellRangeAddress cellMergeRegion = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
	    cleanBeforeMergeOnValidCells(sheet, cellMergeRegion, cellStyle, applyAnyCellStyle);
	    sheet.addMergedRegion(cellMergeRegion);
	}
	
	private static void cleanBeforeMergeOnValidCells (
			final XSSFSheet sheet, 
			final CellRangeAddress region, 
			final XSSFCellStyle cellStyle, 
			final Boolean applyAnyCellStyle
	) {
	    for(int rowNum = region.getFirstRow(); rowNum<=region.getLastRow(); rowNum++){
	        final XSSFRow row= sheet.getRow(rowNum);
	        if(null == row){
	            sheet.createRow(rowNum);
	        }
	        for(int colNum = region.getFirstColumn(); colNum<=region.getLastColumn(); colNum++) {
	           XSSFCell currentCell = row.getCell(colNum); 
	           if(null == currentCell){
	               currentCell = row.createCell(colNum);
	           }    
	           if (applyAnyCellStyle) {
	        	   currentCell.setCellStyle(cellStyle);
	           }
	        }
	    }
	}
	
	private static XSSFCellStyle getHeaderCellStyle(final XSSFWorkbook workbook) {
		final XSSFCellStyle headerCellStyle = workbook.createCellStyle();
		// Set Color Black
		final XSSFColor headerCellBorderColor = new XSSFColor(new java.awt.Color(0, 0, 0));
		// Set Border Color
		headerCellStyle.setBottomBorderColor(headerCellBorderColor);
		headerCellStyle.setTopBorderColor(headerCellBorderColor);
		headerCellStyle.setLeftBorderColor(headerCellBorderColor);
		headerCellStyle.setRightBorderColor(headerCellBorderColor);
		// Set Border Style
		headerCellStyle.setBorderTop(BorderStyle.THIN);
		headerCellStyle.setBorderBottom(BorderStyle.THIN);
		headerCellStyle.setBorderLeft(BorderStyle.THIN);
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		// Set Data Alignment
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		return headerCellStyle;
	}
	
	private static XSSFCellStyle getMismatchCellStyle(final XSSFWorkbook workbook) {
		final XSSFCellStyle mismatchCellStyle = workbook.createCellStyle();
		// Set Cell as YELLOW
		mismatchCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		mismatchCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return mismatchCellStyle;
	}
	
	private static XSSFCellStyle getExtraCellStyle(final XSSFWorkbook workbook) {
		final XSSFCellStyle mismatchCellStyle = workbook.createCellStyle();
		// Set Cell as YELLOW
		mismatchCellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
		mismatchCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return mismatchCellStyle;
	}
	
	private static XSSFCellStyle getMissingCellStyle(final XSSFWorkbook workbook) {
		final XSSFCellStyle mismatchCellStyle = workbook.createCellStyle();
		// Set Cell as YELLOW
		mismatchCellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		mismatchCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return mismatchCellStyle;
	}
	
	public static byte[] createWorkbook(WorkbookReport workbookReport) throws IOException {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		final XSSFCellStyle headerCellStyle = getHeaderCellStyle(workbook);
		final XSSFCellStyle mismatchCellStyle = getMismatchCellStyle(workbook);
		final XSSFCellStyle extraCellStyle = getExtraCellStyle(workbook);
		final XSSFCellStyle missingCellStyle = getMissingCellStyle(workbook);
		LoggerUtils.logOnConsole("Total Sheets = "+workbookReport.getSheets().size());
		for (WorkbookSheet sheet : workbookReport.getSheets()) {
			final XSSFSheet spreadsheet = workbook.createSheet(sheet.getSheetName());
			LoggerUtils.logOnConsole("Sheet - "+sheet.getSheetName());
			Integer maxColumnsEncountered = 0;
			Integer rowid = 0;
			// Row Padding With One Blank Cell
			for (int i = 0; i < sheet.getRowPadding(); i++) {
				final XSSFRow row = spreadsheet.createRow(rowid);
				final XSSFCell cell = row.createCell(0);
				cell.setCellValue(EMPTY_STRING);
				rowid += 1;
			}
			// Generate Headers
			LoggerUtils.logOnConsole("Total Headers = "+sheet.getHeaders().size());
			Integer countHeaders = 0;
			for (WorkbookHeader header : sheet.getHeaders()) {
				final XSSFRow row = spreadsheet.createRow(rowid);
				Integer cellid = 0;
				// Column Padding With One Blank Cell
				for (int i = 0; i < sheet.getColumnPadding(); i++) {
					final XSSFCell cell = row.createCell(cellid);
					cell.setCellValue(EMPTY_STRING);
					cellid += 1;
				}
				for (Object value : header.getHeader()) {
					final XSSFCell cell = row.createCell(cellid);
					cell.setCellValue(null != value ? String.valueOf(value) : EMPTY_STRING);
					cell.setCellStyle(headerCellStyle);
					if (header.getHowManyCellsPerColumn() > 1) {
						setCellMerge(spreadsheet, rowid, rowid, cellid, cellid + (header.getHowManyCellsPerColumn() - 1), headerCellStyle, true);
						cellid += (header.getHowManyCellsPerColumn() - 1);
					}
					cellid += 1;
					// GC value after it is done
					value = null;
				}
				if (maxColumnsEncountered < cellid) {
					maxColumnsEncountered = cellid;
				}
				rowid += 1;
				// GC header after it is done
				header = null;
				countHeaders++;
			}
			// Generate Records
			LoggerUtils.logOnConsole("Total Records = "+sheet.getRecords().size());
			Integer countRecords = 0;
			for(WorkbookRecord record : sheet.getRecords()) {
				final XSSFRow row = spreadsheet.createRow(rowid);
				Integer cellid = 0;
				// Column Padding With One Blank Cell
				for (int i = 0; i < sheet.getColumnPadding(); i++) {
					final XSSFCell cell = row.createCell(cellid);
					cell.setCellValue(EMPTY_STRING);
					cellid += 1;
				}
				for (Object value : record.getRecord()) {
					final XSSFCell cell = row.createCell(cellid);
					cell.setCellValue(null != value ? String.valueOf(value) : EMPTY_STRING);
					if (record.getIsMismatch()) {
						cell.setCellStyle(mismatchCellStyle);
					} else if (record.getIsExtra()) {
						cell.setCellStyle(extraCellStyle);
					} else if (record.getIsMissing()) {
						cell.setCellStyle(missingCellStyle);
					}
					cellid += 1;
					// GC value after it is done
					value = null;
				}
				if (maxColumnsEncountered < cellid) {
					maxColumnsEncountered = cellid;
				}
				rowid += 1;
				// GC header after it is done
				record = null;
				countRecords++;
			}
			for (int i = 1; i < maxColumnsEncountered; i++) {
				spreadsheet.setColumnWidth(i, sheet.getColumnWidth());
			}
			// GC sheet after it is done
			sheet = null;
		}
		// GC workbookReport after it is done
		workbookReport = null;
		final ByteArrayOutputStream workbookOutputStream = new ByteArrayOutputStream();
		workbook.write(workbookOutputStream);
		workbookOutputStream.close();
		return workbookOutputStream.toByteArray();
	}
	
	public static byte[] createWorkbook (
			final Map<String, Map <String, Object[]>> reportData
	) throws IOException {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		for (Map.Entry<String, Map <String, Object[]>> entry : reportData.entrySet()) {
			final String sheetName = entry.getKey();
			final Map <String, Object[]> workbookData = entry.getValue();
			final XSSFSheet spreadsheet = workbook.createSheet(sheetName);
			final Set <String> keyId = workbookData.keySet();
			int rowid = 0;
			for (final String key : keyId) {
				final XSSFRow row = spreadsheet.createRow(rowid++);
				final Object[] objectArr = workbookData.get(key);
				int cellid = 0;
				for (final Object obj : objectArr) {
					final Cell cell = row.createCell(cellid++);
					cell.setCellValue(null != obj ? String.valueOf(obj) : EMPTY_STRING);
				}
			}
		}
		final ByteArrayOutputStream workbookOutputStream = new ByteArrayOutputStream();
		workbook.write(workbookOutputStream);
		workbookOutputStream.close();
		return workbookOutputStream.toByteArray();
	}
}
