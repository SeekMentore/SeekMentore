package com.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.constants.FileConstants;
import com.model.User;
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
	
	public static void createFileInsideFolderOnApplicationFileSystemUsingKey(final String fsKey, final byte[] bytes) {
		final String folderNameWithPathFromRootFolder = fsKey.substring(0, fsKey.lastIndexOf(FORWARD_SLASH));
		final String filename = fsKey.substring(fsKey.lastIndexOf(FORWARD_SLASH) + 1);
		AWSS3HelperUtils.createFileInsideFolderAndReturnKey(folderNameWithPathFromRootFolder, filename, bytes, false);
	}
	
	public static List<String> getAllFileNamesFromFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) {
		return AWSS3HelperUtils.getAllFileNamesFromFolder(folderNameWithPathFromRootFolder);
	}
	
	public static Map<String, byte[]> getAllFileNamesAndContentFromFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder) throws IOException {
		return AWSS3HelperUtils.getAllFileNamesAndContentFromFolder(folderNameWithPathFromRootFolder);
	}
	
	public static void deleteFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final User activeUser) {
		AWSS3HelperUtils.deleteFolder(folderNameWithPathFromRootFolder, activeUser);
	}
	
	public static void deleteFileInFolderOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String filename, final User activeUser) {
		AWSS3HelperUtils.deleteFileInFolder(folderNameWithPathFromRootFolder, filename, activeUser);
	}
	
	public static void deleteFileInFolderOnApplicationFileSystemUsingKey(final String fsKey, final User activeUser) {
		AWSS3HelperUtils.deleteFileInFolderUsingKeyInS3Client(fsKey, activeUser);
	}
	
	public static byte[] readContentFromFileOnApplicationFileSystem(final String folderNameWithPathFromRootFolder, final String filename) throws IOException {
		return AWSS3HelperUtils.readContentFromFileInS3Client(folderNameWithPathFromRootFolder, filename);
	}
	
	public static byte[] readContentFromFileOnApplicationFileSystemUsingKey(final String fsKey) throws IOException {
		return AWSS3HelperUtils.readContentFromFileUsingKeyInS3Client(fsKey);
	}
}
