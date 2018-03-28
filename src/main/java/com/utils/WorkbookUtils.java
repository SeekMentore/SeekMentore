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

public class WorkbookUtils implements WorkbookConstants {
	
	public static byte[] createWorkbook (
			final String sheetName,
			final Map <String, Object[]> workbookData
	) throws IOException {
		final XSSFWorkbook workbook = new XSSFWorkbook(); 
		final XSSFSheet spreadsheet = workbook.createSheet(sheetName);
		final Set <String> keyId = workbookData.keySet();
		int rowid = 0;
		for (final String key : keyId) {
			final XSSFRow row = spreadsheet.createRow(rowid++);
			final Object[] objectArr = workbookData.get(key);
			int cellid = 0;
			for (final Object obj : objectArr) {
				final Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
			}
		}
		final ByteArrayOutputStream workbookOutputStream = new ByteArrayOutputStream();
		workbook.write(workbookOutputStream);
		workbookOutputStream.close();
		return workbookOutputStream.toByteArray();
	}
}
