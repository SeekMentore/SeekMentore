package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
import com.model.workbook.WorkbookCell;
import com.model.workbook.WorkbookCell.TypeOfStyleEnum;
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
			final Boolean applyAnyCellStyle,
			final List<Integer> alreadyCreatedRowIds,
			final List<Integer> alreadyCreatedCellIds
	) {
	    final CellRangeAddress cellMergeRegion = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
	    cleanBeforeMergeOnValidCells(sheet, cellMergeRegion, cellStyle, applyAnyCellStyle, alreadyCreatedRowIds, alreadyCreatedCellIds);
	    sheet.addMergedRegion(cellMergeRegion);
	}
	
	private static void cleanBeforeMergeOnValidCells (
			final XSSFSheet sheet, 
			final CellRangeAddress region, 
			final XSSFCellStyle cellStyle, 
			final Boolean applyAnyCellStyle, 
			final List<Integer> alreadyCreatedRowIds,
			final List<Integer> alreadyCreatedCellIds
	) {
	    for(Integer rowNum = region.getFirstRow(); rowNum <= region.getLastRow(); rowNum++) {
	        final XSSFRow row = getXSSFRowWithRowId(sheet, alreadyCreatedRowIds, rowNum);
	        for(Integer colNum = region.getFirstColumn(); colNum <= region.getLastColumn(); colNum++) {
	           final XSSFCell currentCell = getXSSFCellWithCellId(row, alreadyCreatedCellIds, colNum); 
	           if (applyAnyCellStyle) {
	        	   currentCell.setCellStyle(cellStyle);
	           }
	        }
	    }
	}
	
	private static XSSFCellStyle getTypeStyleCellStyle(final XSSFWorkbook workbook, final TypeOfStyleEnum typeOfStyleEnum) {
		final XSSFCellStyle cellStyle = workbook.createCellStyle();
		switch(typeOfStyleEnum) {
			case DEFAULT_HEADER_CELL: {
				// Set Color Black
				final XSSFColor headerCellBorderColor = new XSSFColor(new java.awt.Color(0, 0, 0));
				// Set Border Color
				cellStyle.setBottomBorderColor(headerCellBorderColor);
				cellStyle.setTopBorderColor(headerCellBorderColor);
				cellStyle.setLeftBorderColor(headerCellBorderColor);
				cellStyle.setRightBorderColor(headerCellBorderColor);
				// Set Border Style
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				// Set Data Alignment
				cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				break;
			}
			case SOLID_FOREGROUND_YELLOW : {
				cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			}
			case SOLID_FOREGROUND_GOLD : {
				cellStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			}
			case SOLID_FOREGROUND_LAVENDER : {
				cellStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				break;
			}
		}
		return cellStyle;
	}
	
	private static XSSFRow getXSSFRowWithRowId(final XSSFSheet spreadsheet, final List<Integer> alreadyCreatedRowIds, final Integer rowid) {
		if (ValidationUtils.checkNonEmptyList(alreadyCreatedRowIds)) {
			if (alreadyCreatedRowIds.contains(rowid)) {
				return spreadsheet.getRow(rowid);
			}
		}
		alreadyCreatedRowIds.add(rowid);
		return spreadsheet.createRow(rowid);
	}
	
	private static XSSFCell getXSSFCellWithCellId(final XSSFRow row, final List<Integer> alreadyCreatedCellIds, final Integer cellid) {
		if (ValidationUtils.checkNonEmptyList(alreadyCreatedCellIds)) {
			if (alreadyCreatedCellIds.contains(cellid)) {
				return row.getCell(cellid);
			}
		}
		alreadyCreatedCellIds.add(cellid);
		return row.createCell(cellid);
	}
	
	private static Integer returnIncrementedRowId(final WorkbookRecord record, final List<Integer> alreadyCreatedRowIds, final Integer rowid) {
		if (record.getIsContinuedRecordInMergedRow()) {
			// TODO code
		}
		Integer tempId = rowid + 1;
		while (alreadyCreatedRowIds.contains(tempId)) {
			tempId += 1;
		}
		return tempId;
	}
	
	public static byte[] createWorkbook(WorkbookReport workbookReport) throws IOException {
		final List<Integer> alreadyCreatedRowIds = new LinkedList<Integer>();
		final XSSFWorkbook workbook = new XSSFWorkbook();
		LoggerUtils.logOnConsole("Total Sheets = "+workbookReport.getSheets().size());
		for (WorkbookSheet sheet : workbookReport.getSheets()) {
			final XSSFSheet spreadsheet = workbook.createSheet(sheet.getSheetName());
			LoggerUtils.logOnConsole("Sheet - "+sheet.getSheetName());
			Integer maxColumnsEncountered = 0;
			Integer rowid = 0;
			// Row Padding With One Blank Cell
			for (int i = 0; i < sheet.getRowPadding(); i++) {
				final List<Integer> alreadyCreatedCellIds = new LinkedList<Integer>();
				final XSSFRow row = getXSSFRowWithRowId(spreadsheet, alreadyCreatedRowIds, rowid);
				final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedCellIds, 0);
				cell.setCellValue(EMPTY_STRING);
				rowid += 1;
			}
			LoggerUtils.logOnConsole("Total Workbook Records = "+sheet.getWorkbookRecords().size());
			Integer countWorkbookRecords = 0;
			for (WorkbookRecord record : sheet.getWorkbookRecords()) {
				final List<Integer> alreadyCreatedCellIds = new LinkedList<Integer>();
				final XSSFRow row = getXSSFRowWithRowId(spreadsheet, alreadyCreatedRowIds, rowid);
				Integer cellid = 0;
				// Column Padding With One Blank Cell
				for (int i = 0; i < sheet.getColumnPadding(); i++) {
					final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedCellIds, cellid);
					cell.setCellValue(EMPTY_STRING);
					cellid += 1;
				}
				for (WorkbookCell workbookCell : record.getRecordCells()) {
					Object value = workbookCell.getValue();
					final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedCellIds, cellid);
					cell.setCellValue(null != value ? String.valueOf(value) : EMPTY_STRING);
					XSSFCellStyle cellStyle = null;
					Boolean applyAnyCellStyle = false;
					if (workbookCell.getIsCellStyled()) {
						cellStyle = getTypeStyleCellStyle(workbook, workbookCell.getTypeOfStyleEnum());
						applyAnyCellStyle = true;
						cell.setCellStyle(cellStyle);
					}
					if (workbookCell.getIsCellMerged()) {
						setCellMerge(
									spreadsheet, 
									rowid, 
									rowid + (workbookCell.getNumberOfMergedRowsForThisCell() - 1), 
									cellid, 
									cellid + (workbookCell.getNumberOfMergedColumnsForThisCell() - 1), 
									cellStyle, 
									applyAnyCellStyle, 
									alreadyCreatedRowIds,
									alreadyCreatedCellIds
								);
						cellid += (workbookCell.getNumberOfMergedColumnsForThisCell() - 1);
					}
					cellid += 1;
					// GC value after it is done
					value = null;
				}
				if (maxColumnsEncountered < cellid) {
					maxColumnsEncountered = cellid;
				}
				//rowid = returnIncrementedRowId(record, alreadyCreatedRowIds, rowid);
				rowid += 1;
				// GC header after it is done
				record = null;
				countWorkbookRecords++;
			}
			for (int i = 1; i < maxColumnsEncountered; i++) {
				spreadsheet.setColumnWidth(i, workbookReport.getDefaultCellWidth());
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
