package com.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.constants.FileConstants;
import com.utils.helper.AWSS3HelperUtils;

public class FileSystemUtils implements FileConstants {
	
	public static void createFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) {
		AWSS3HelperUtils.createFolderInBucket(folderNameWithPathFromRootFolder, false);
	}
	
	public static void createFileInsideFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final File file) {
		AWSS3HelperUtils.createFileInsideFolder(folderNameWithPathFromRootFolder, file, false);
	}
	
	public static void createFileInsideFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String customFilename, final File file) {
		AWSS3HelperUtils.createFileInsideFolder(folderNameWithPathFromRootFolder, customFilename, file, false);
	}
	
	public static void createFileInsideFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String filename, final byte[] bytes) {
		AWSS3HelperUtils.createFileInsideFolder(folderNameWithPathFromRootFolder, filename, bytes, false);
	}
	
	public static String createFolderOnApplicationFileSystemAndReturnKey(final String folderNameWithPathFromRootFolder) {
		return AWSS3HelperUtils.createFolderInBucketAndReturnKey(folderNameWithPathFromRootFolder, false);
	}
	
	public static String createFileInsideFolderOnApplicationFileSystemAndReturnKey(final String folderNameWithPathFromRootFolder, final File file) {
		return AWSS3HelperUtils.createFileInsideFolderAndReturnKey(folderNameWithPathFromRootFolder, file, false);
	}
	
	public static String createFileInsideFolderOnApplicationFileSystemAndReturnKey(final String folderNameWithPathFromRootFolder, final String customFilename, final File file) {
		return AWSS3HelperUtils.createFileInsideFolderAndReturnKey(folderNameWithPathFromRootFolder, customFilename, file, false);
	}
	
	public static String createFileInsideFolderOnApplicationFileSystemAndReturnKey(final String folderNameWithPathFromRootFolder, final String filename, final byte[] bytes) {
		return AWSS3HelperUtils.createFileInsideFolderAndReturnKey(folderNameWithPathFromRootFolder, filename, bytes, false);
	}
	
	public static List<String> getAllFileNamesFromFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) {
		return AWSS3HelperUtils.getAllFileNamesFromFolder(folderNameWithPathFromRootFolder);
	}
	
	public static Map<String, byte[]> getAllFileNamesAndContentFromFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) throws IOException {
		return AWSS3HelperUtils.getAllFileNamesAndContentFromFolder(folderNameWithPathFromRootFolder);
	}
	
	public static void deleteFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) {
		AWSS3HelperUtils.deleteFolder(folderNameWithPathFromRootFolder);
	}
	
	public static void deleteFileInFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String filename) {
		AWSS3HelperUtils.deleteFileInFolder(folderNameWithPathFromRootFolder, filename);
	}
	
	public static byte[] readContentFromFileOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String filename) throws IOException {
		return AWSS3HelperUtils.readContentFromFileInS3Client(folderNameWithPathFromRootFolder, filename);
	}

}
