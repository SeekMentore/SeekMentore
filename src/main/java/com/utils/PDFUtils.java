package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.constants.PDFConstants;
import com.exception.ApplicationException;

public class PDFUtils implements PDFConstants {
	
	public static byte[] getPDFByteArrayFromHTMLString(final String htmlString) throws Exception {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(htmlString.getBytes())), null);
		renderer.layout();
		renderer.createPDF(outputStream);
		return outputStream.toByteArray();
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

}
