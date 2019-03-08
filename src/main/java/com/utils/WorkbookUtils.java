package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.constants.WorkbookConstants;
import com.model.ApplicationWorkbookObject;
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
			final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds
	) {
	    final CellRangeAddress cellMergeRegion = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
	    cleanBeforeMergeOnValidCells(sheet, cellMergeRegion, cellStyle, applyAnyCellStyle, alreadyCreatedRowAndCellIds);
	    sheet.addMergedRegion(cellMergeRegion);
	}
	
	private static void cleanBeforeMergeOnValidCells (
			final XSSFSheet sheet, 
			final CellRangeAddress region, 
			final XSSFCellStyle cellStyle, 
			final Boolean applyAnyCellStyle, 
			final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds
	) {
	    for(Integer rowNum = region.getFirstRow(); rowNum <= region.getLastRow(); rowNum++) {
	        final XSSFRow row = getXSSFRowWithRowId(sheet, alreadyCreatedRowAndCellIds, rowNum);
	        for(Integer colNum = region.getFirstColumn(); colNum <= region.getLastColumn(); colNum++) {
	           final XSSFCell currentCell = getXSSFCellWithCellId(row, alreadyCreatedRowAndCellIds, colNum); 
	           if (applyAnyCellStyle) {
	        	   currentCell.setCellStyle(cellStyle);
	           }
	        }
	    }
	}
	
	private static XSSFCellStyle getTypeStyleCellStyle(final XSSFWorkbook workbook, final TypeOfStyleEnum[] typeOfStyleEnums) {
		final XSSFCellStyle cellStyle = workbook.createCellStyle();
		final XSSFFont cellFont = workbook.createFont();
		for (final TypeOfStyleEnum typeOfStyleEnum : typeOfStyleEnums) {
			switch(typeOfStyleEnum) {
				case DEFAULT_HEADER_CELL: {
					final XSSFColor headerCellBorderColor = new XSSFColor(new java.awt.Color(0, 0, 0));
					cellStyle.setBottomBorderColor(headerCellBorderColor);
					cellStyle.setTopBorderColor(headerCellBorderColor);
					cellStyle.setLeftBorderColor(headerCellBorderColor);
					cellStyle.setRightBorderColor(headerCellBorderColor);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);
					cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					break;
				}
				case BOLD_HEADER_CELL: {
					final XSSFColor headerCellBorderColor = new XSSFColor(new java.awt.Color(0, 0, 0));
					cellStyle.setBottomBorderColor(headerCellBorderColor);
					cellStyle.setTopBorderColor(headerCellBorderColor);
					cellStyle.setLeftBorderColor(headerCellBorderColor);
					cellStyle.setRightBorderColor(headerCellBorderColor);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);
					cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					cellFont.setBold(true);
					cellStyle.setFont(cellFont);
					break;
				}
				case BORDER_THIN_BLACK : {
					final XSSFColor headerCellBorderColor = new XSSFColor(new java.awt.Color(0, 0, 0));
					cellStyle.setBottomBorderColor(headerCellBorderColor);
					cellStyle.setTopBorderColor(headerCellBorderColor);
					cellStyle.setLeftBorderColor(headerCellBorderColor);
					cellStyle.setRightBorderColor(headerCellBorderColor);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);
					break;
				}
				case BORDER_THIN_DARK_BLUE : {
					final XSSFFont tempcellFont = workbook.createFont();
					tempcellFont.setColor(IndexedColors.DARK_BLUE.getIndex());
					final XSSFColor headerCellBorderColor = tempcellFont.getXSSFColor();
					cellStyle.setBottomBorderColor(headerCellBorderColor);
					cellStyle.setTopBorderColor(headerCellBorderColor);
					cellStyle.setLeftBorderColor(headerCellBorderColor);
					cellStyle.setRightBorderColor(headerCellBorderColor);
					cellStyle.setBorderTop(BorderStyle.THIN);
					cellStyle.setBorderBottom(BorderStyle.THIN);
					cellStyle.setBorderLeft(BorderStyle.THIN);
					cellStyle.setBorderRight(BorderStyle.THIN);
					break;
				}
				case CONTENT_VERTICAL_CENTER : {
					cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					break;
				}
				case CONTENT_HORIZONTAL_CENTER : {
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
				case SOLID_FOREGROUND_LIGHT_GREY : {
					cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					break;
				}
				case SOLID_FOREGROUND_DARK_BLUE : {
					cellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
					cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					break;
				}
				case FONT_COLOR_RED : {
					cellFont.setColor(IndexedColors.RED.getIndex());
					cellStyle.setFont(cellFont);
					break;
				}
				case FONT_COLOR_GREEN : {
					cellFont.setColor(IndexedColors.GREEN.getIndex());
					cellStyle.setFont(cellFont);
					break;
				}
				case FONT_COLOR_WHITE : {
					cellFont.setColor(IndexedColors.WHITE.getIndex());
					cellStyle.setFont(cellFont);
					break;
				}
			}
		}
		return cellStyle;
	}
	
	private static XSSFRow getXSSFRowWithRowId(final XSSFSheet spreadsheet, final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds, final Integer rowid) {
		if (ValidationUtils.checkObjectAvailability(alreadyCreatedRowAndCellIds) 
				&& ValidationUtils.checkObjectAvailability(alreadyCreatedRowAndCellIds.get(rowid))) {
			return spreadsheet.getRow(rowid);
		}
		alreadyCreatedRowAndCellIds.put(rowid, new LinkedList<Integer>());
		return spreadsheet.createRow(rowid);
	}
	
	private static XSSFCell getXSSFCellWithCellId(final XSSFRow row, final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds, final Integer cellid) {
		if (ValidationUtils.checkObjectAvailability(alreadyCreatedRowAndCellIds) 
				&& ValidationUtils.checkNonEmptyList(alreadyCreatedRowAndCellIds.get(row.getRowNum())) 
				&& alreadyCreatedRowAndCellIds.get(row.getRowNum()).contains(cellid)) {
			return row.getCell(cellid);
		}
		alreadyCreatedRowAndCellIds.get(row.getRowNum()).add(cellid);
		return row.createCell(cellid);
	}
	
	private static Integer returnIncrementedRowId(final WorkbookRecord record, final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds, final Integer rowid) {
		Integer tempId = rowid + 1;
		if (record.getIsContinuedRecordInMergedRow()) {
			// TODO code
		} else {
			while (ValidationUtils.checkObjectAvailability(alreadyCreatedRowAndCellIds) 
					&& ValidationUtils.checkObjectAvailability(alreadyCreatedRowAndCellIds.get(tempId))) {
				tempId += 1;
			}
		}
		return tempId;
	}
	
	public static byte[] createWorkbook(WorkbookReport workbookReport) throws IOException {
		final Map<Integer, List<Integer>> alreadyCreatedRowAndCellIds = new HashMap<Integer, List<Integer>>();
		final XSSFWorkbook workbook = new XSSFWorkbook();
		LoggerUtils.logOnConsole("Total Sheets = "+workbookReport.getSheets().size());
		for (WorkbookSheet sheet : workbookReport.getSheets()) {
			final XSSFSheet spreadsheet = workbook.createSheet(sheet.getSheetName());
			LoggerUtils.logOnConsole("Sheet - "+sheet.getSheetName());
			Integer maxColumnsEncountered = 0;
			Integer rowid = 0;
			// Row Padding With One Blank Cell
			for (int i = 0; i < sheet.getRowPadding(); i++) {
				final XSSFRow row = getXSSFRowWithRowId(spreadsheet, alreadyCreatedRowAndCellIds, rowid);
				final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedRowAndCellIds, 0);
				cell.setCellValue(EMPTY_STRING);
				rowid += 1;
			}
			LoggerUtils.logOnConsole("Total Workbook Records = "+sheet.getWorkbookRecords().size());
			Integer countWorkbookRecords = 0;
			for (WorkbookRecord record : sheet.getWorkbookRecords()) {
				final XSSFRow row = getXSSFRowWithRowId(spreadsheet, alreadyCreatedRowAndCellIds, rowid);
				Integer cellid = 0;
				// Column Padding With One Blank Cell
				for (int i = 0; i < sheet.getColumnPadding(); i++) {
					final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedRowAndCellIds, cellid);
					cell.setCellValue(EMPTY_STRING);
					cellid += 1;
				}
				for (WorkbookCell workbookCell : record.getRecordCells()) {
					Object value = workbookCell.getValue();
					final XSSFCell cell = getXSSFCellWithCellId(row, alreadyCreatedRowAndCellIds, cellid);
					cell.setCellValue(null != value ? String.valueOf(value) : EMPTY_STRING);
					XSSFCellStyle cellStyle = null;
					Boolean applyAnyCellStyle = false;
					if (workbookCell.getIsCellStyled()) {
						cellStyle = getTypeStyleCellStyle(workbook, workbookCell.getTypeOfStyleEnums());
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
									alreadyCreatedRowAndCellIds
								);
					}
					if (workbookCell.getHasImage()) {
						setImageInXSSFCell(workbookCell, workbook, spreadsheet, rowid, cellid);
					}
					if (workbookCell.getIsCellMerged()) {
						cellid += (workbookCell.getNumberOfMergedColumnsForThisCell() - 1);
					}
					cellid += 1;
					// GC value after it is done
					value = null;
				}
				if (maxColumnsEncountered < cellid) {
					maxColumnsEncountered = cellid;
				}
				rowid = returnIncrementedRowId(record, alreadyCreatedRowAndCellIds, rowid);
				//rowid += 1;
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
	
	private static void setImageInXSSFCell (
			final WorkbookCell workbookCell, 
			final XSSFWorkbook workbook, 
			final XSSFSheet spreadsheet,
			final Integer rowid,
			final Integer cellid
			
	) throws IOException {
		byte[] imageBytes = null;
		if (!workbookCell.getIsImageLocatedInSecuredServer()) {
			imageBytes = captureImageContentFromOpenServer(workbookCell.getImageURL());
		}
		if (ValidationUtils.checkObjectAvailability(imageBytes)) {
			final Integer pictureIdx = workbook.addPicture(imageBytes, XSSFWorkbook.PICTURE_TYPE_PNG);
			final CreationHelper helper = workbook.getCreationHelper();
			final Drawing drawing = spreadsheet.createDrawingPatriarch();
			final ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(cellid + workbookCell.getColumnsToLeaveFromCellColumnStart());
			anchor.setRow1(rowid + workbookCell.getRowsToLeaveFromCellRowStart());
			anchor.setCol2(cellid + workbookCell.getColumnsToLeaveFromCellColumnStart() + workbookCell.getTotalColumnsToCaptureInCell());
			anchor.setRow2(rowid + workbookCell.getRowsToLeaveFromCellRowStart() + workbookCell.getTotalRowsToCaptureInCell());
			@SuppressWarnings("unused")
			final Picture pict = drawing.createPicture(anchor, pictureIdx);
		}
	}
	
	private static byte[] captureImageContentFromOpenServer(final String imageServerPath) throws IOException {
		final InputStream inputStream = new URL(imageServerPath).openConnection().getInputStream();
		final byte[] bytes = IOUtils.toByteArray(inputStream);
		inputStream.close();
		return bytes;
	}
	
	public static List<WorkbookRecord> computeHeaderAndRecordsForApplicationWorkbookObjectList(
			final List<? extends ApplicationWorkbookObject> objectTypeRecords, 
			final Class<? extends ApplicationWorkbookObject> recordObjectType, 
			final String reportType
	) throws InstantiationException, IllegalAccessException {
		final List<WorkbookRecord> workbookRecords = new LinkedList<WorkbookRecord>();
		{
			final List<WorkbookCell> headerCells = new LinkedList<WorkbookCell>();
			for (final Object value : recordObjectType.newInstance().getReportHeaders(reportType)) {
				if (value instanceof WorkbookCell)
					headerCells.add((WorkbookCell)value);
				else
					headerCells.add(new WorkbookCell(value, true, TypeOfStyleEnum.BOLD_HEADER_CELL));
			}
			workbookRecords.add(new WorkbookRecord(headerCells));
		}
		{
			for(final ApplicationWorkbookObject workbookObject : objectTypeRecords) {
				final List<WorkbookCell> recordCells = new LinkedList<WorkbookCell>();
				for (final Object value : workbookObject.getReportRecords(reportType)) {
					if (value instanceof WorkbookCell)
						recordCells.add((WorkbookCell)value);
					else
						recordCells.add(new WorkbookCell(value));
				}
				workbookRecords.add(new WorkbookRecord(recordCells));
			}
		}
		return workbookRecords;
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
