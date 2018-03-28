package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.constants.PDFConstants;

public class PDFUtils implements PDFConstants {
	
	public static byte[] getPDFByteArrayFromHTMLString(final String htmlString) throws Exception {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		final ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(htmlString.getBytes())), null);
		renderer.layout();
		renderer.createPDF(outputStream);
		return outputStream.toByteArray();
	}

}
