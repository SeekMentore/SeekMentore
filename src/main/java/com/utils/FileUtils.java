package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import com.constants.FileConstants;
import com.exception.ApplicationException;

public class FileUtils implements FileConstants {

	public static void writeFileToResponse (
			final HttpServletResponse response,
			final String fileName, 
			final String contentType, 
			final byte[] fileContent
	) throws IOException {
		response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + INVERTED_COMMA + fileName + INVERTED_COMMA);
		if (null != contentType) {
			response.setContentType(contentType);
		}
		response.setContentLength(fileContent.length);
		response.getOutputStream().write(fileContent);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	public static byte[] getPDFAsByteArray(final String sourcePath) throws IOException {
		final File file = new File(sourcePath);
		final InputStream inputStream = new FileInputStream(file);
        final byte[] bytes = new byte[(int)file.length()];
        int offset = 0;
        int numRead = 0;
        if (inputStream != null) {
        	while (offset < bytes.length && (numRead = inputStream.read(bytes, offset, bytes.length-offset)) >= 0) {
        		offset += numRead;
        	}
        }
        if (inputStream != null) {
        	inputStream.close();
        }
        if (offset < bytes.length) {
        	throw new ApplicationException(EXCEPTION_MESSAGE_COULD_NOT_COMPLETELY_READ_FILE + file.getName());
        }
        return bytes;
	}
	
	public static String deteteFile(final String url) {
		final File file = new File(url);
		if (file.delete()) {
			return EMPTY_STRING;
		}
		return EMPTY_STRING;
	}

	public static void generateFile(final byte[] content, final String url) throws IOException {
		final FileOutputStream fileOutputStream = new FileOutputStream(url);
		fileOutputStream.write(content);
		if (fileOutputStream != null) {
			fileOutputStream.close();
		}
	}

	public static String convertClobToString(Clob clob) throws SQLException, IOException {
		final StringBuilder stringBuilder = new StringBuilder();
		final Reader reader = clob.getCharacterStream();
		int character = -1;
		while((character = reader.read()) != -1) {
			stringBuilder.append(((char)character));
		}
		return stringBuilder.toString();
	}
	
	public static byte[] zipAttachment(final List<byte[]> fileBytesList, final List<String> fileNamesList) throws IOException {
		if (fileBytesList.size() != fileNamesList.size()) {
        	throw new ApplicationException(EXCEPTION_MESSAGE_MISMATCHED_NUMBER_OF_FILE_BYTES_AND_FILE_NAMES);
        }
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(fileBytesList.size());
		final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
		for(int i = 0; i < fileBytesList.size(); i++) {
			final byte[] bytes = fileBytesList.get(i);
			final ZipEntry entry = new ZipEntry(fileNamesList.get(i));
			entry.setSize(bytes.length);
			zipOutputStream.putNextEntry(entry);
			zipOutputStream.write(bytes);
			zipOutputStream.closeEntry();
		}
		zipOutputStream.flush(); 
		zipOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}
}
