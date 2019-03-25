package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.constants.FileConstants;
import com.model.ApplicationFile;

public class FileUtils implements FileConstants {

	public static String deteteArbitraryFile(final String url) {
		final File file = new File(url);
		if (file.delete()) {
			return EMPTY_STRING;
		}
		return EMPTY_STRING;
	}

	public static void generateArbitraryFile(final byte[] content, final String url) throws IOException {
		try (final FileOutputStream fileOutputStream = new FileOutputStream(url)) {
			fileOutputStream.write(content);
		}
	}

	public static byte[] getZippedBytes(final List<ApplicationFile> applicationFiles) throws IOException {
		if (ValidationUtils.checkNonEmptyList(applicationFiles)) {
        	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(applicationFiles.size());
        	final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        	for(ApplicationFile applicationFile : applicationFiles) {
        		final byte[] bytes = applicationFile.getContent();
        		final ZipEntry entry = new ZipEntry(applicationFile.getFilename());
        		entry.setSize(bytes.length);
        		zipOutputStream.putNextEntry(entry);
        		zipOutputStream.write(bytes);
        		zipOutputStream.closeEntry();
        	}
        	zipOutputStream.flush(); 
        	zipOutputStream.close();
        	return byteArrayOutputStream.toByteArray();
        }
		return null;
	}
}
