package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.constants.WorkbookConstants;
import com.model.ApplicationWorkbookObject;
import com.model.WorkbookReport;

public class WorkbookUtils implements WorkbookConstants {
	
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
	
	public static byte[] createWorkbook (
			final WorkbookReport workbookReport
	) throws IOException, InstantiationException, IllegalAccessException {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		for (WorkbookReport.WorkbookSheet sheet : workbookReport.getSheets()) {
			final XSSFSheet spreadsheet = workbook.createSheet(sheet.getSheetName());
			int rowid = 0;
			{
				// Genarate Headers
				final XSSFRow row = spreadsheet.createRow(rowid++);
				final Object[] headers = sheet.getRecordObjectType().newInstance().getReportHeaders(workbookReport.getObjectReportSwitch());
				int cellid = 0;
				for (final Object header : headers) {
					final Cell cell = row.createCell(cellid++);
					cell.setCellValue(null != header ? String.valueOf(header) : EMPTY_STRING);
				}
			}
			for(final ApplicationWorkbookObject workbookObject : sheet.getRecords()) {
				// Generate Records
				final XSSFRow row = spreadsheet.createRow(rowid++);
				final Object[] record = workbookObject.getReportRecords(workbookReport.getObjectReportSwitch());
				int cellid = 0;
				for (final Object value : record) {
					final Cell cell = row.createCell(cellid++);
					cell.setCellValue(null != value ? String.valueOf(value) : EMPTY_STRING);
				}
			}
		}
		final ByteArrayOutputStream workbookOutputStream = new ByteArrayOutputStream();
		workbook.write(workbookOutputStream);
		workbookOutputStream.close();
		return workbookOutputStream.toByteArray();
	}
}
