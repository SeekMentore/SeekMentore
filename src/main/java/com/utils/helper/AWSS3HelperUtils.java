package com.utils.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.IOUtils;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.service.AWSHelperService;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;

public class AWSS3HelperUtils {
	
	public static PutObjectResult createFolderInBucket(final String folderName, final boolean enablePublicAccess) {
		return putObjectInS3Client(createPutObjectRequest(folderName, null, new byte[0], false));
	}
	
	public static PutObjectResult createFileInsideFolder(final String folderName, final File file, final boolean enablePublicAccess) {
		return putObjectInS3Client(createPutObjectRequest(folderName, null, file, enablePublicAccess));
	}
	
	public static PutObjectResult createFileInsideFolder(final String folderName, final String customFilename, final File file, final boolean enablePublicAccess) {
		return putObjectInS3Client(createPutObjectRequest(folderName, customFilename, file, enablePublicAccess));
	}
	
	public static PutObjectResult createFileInsideFolder(final String folderName, final String filename, final byte[] bytes, final boolean enablePublicAccess) {
		return putObjectInS3Client(createPutObjectRequest(folderName, filename, bytes, enablePublicAccess));
	}
	
	public static String createFolderInBucketAndReturnKey(final String folderName, final boolean enablePublicAccess) {
		final PutObjectRequest putObjectRequest = createPutObjectRequest(folderName, null, new byte[0], false);
		putObjectInS3Client(putObjectRequest);
		return putObjectRequest.getKey();
	}
	
	public static String createFileInsideFolderAndReturnKey(final String folderName, final File file, final boolean enablePublicAccess) {
		final PutObjectRequest putObjectRequest = createPutObjectRequest(folderName, null, file, enablePublicAccess);
		putObjectInS3Client(putObjectRequest);
		return putObjectRequest.getKey();
	}
	
	public static String createFileInsideFolderAndReturnKey(final String folderName, final String customFilename, final File file, final boolean enablePublicAccess) {
		final PutObjectRequest putObjectRequest = createPutObjectRequest(folderName, customFilename, file, enablePublicAccess);
		putObjectInS3Client(putObjectRequest);
		return putObjectRequest.getKey();
	}
	
	public static String createFileInsideFolderAndReturnKey(final String folderName, final String filename, final byte[] bytes, final boolean enablePublicAccess) {
		final PutObjectRequest putObjectRequest = createPutObjectRequest(folderName, filename, bytes, enablePublicAccess);
		putObjectInS3Client(putObjectRequest);
		return putObjectRequest.getKey();
	}
	
	public static List<String> getAllFileNamesFromFolder(final String folderName) {
		final List<String> fileNames = new ArrayList<String>();
		for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder(folderName)) {
			fileNames.add(getFileNameFromKey(objectSummary.getKey()));
		}
		return fileNames;
	}
	
	public static Map<String, byte[]> getAllFileNamesAndContentFromFolder(final String folderName) throws IOException {
		final Map<String, byte[]> fileNamesAndContent = new HashMap<String, byte[]>();
		for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder(folderName)) {
			fileNamesAndContent.put(getFileNameFromKey(objectSummary.getKey()), readContentFromFileInS3Client(folderName, getFileNameFromKey(objectSummary.getKey())));
		}
		return fileNamesAndContent;
	}
	
	public static void deleteFolder(final String folderName) {
		for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder(folderName)) {
			deleteObjectFromS3Client(objectSummary.getKey());
		}
		deleteObjectFromS3Client(folderName);
	}
	
	public static void deleteFileInFolder(final String folderName, final String filename) {
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + filename;
		deleteObjectFromS3Client(key);
	}
	
	public static byte[] readContentFromFileInS3Client(final String folderName, final String filename) throws IOException {
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + filename;
		final S3Object s3object = getAWSHelperService().getS3client().getObject(new GetObjectRequest(getAWSHelperService().getBucketName(), key));
	    final InputStream stream = s3object.getObjectContent();
	    return IOUtils.toByteArray(stream);
	}
	
	public static String getS3ClientKey(final String folderName, final String filename) {
		return folderName + ApplicationConstants.FORWARD_SLASH + filename;
	}
	
	private static String getFileNameFromKey(final String key) {
		return key.substring(key.lastIndexOf(ApplicationConstants.FORWARD_SLASH));
	}
	
	private static List<S3ObjectSummary> getS3ObjectListInFolder(final String folderName) {
		return getAWSHelperService().getS3client().listObjects(getAWSHelperService().getBucketName(), folderName).getObjectSummaries();
	}
	
	private static PutObjectRequest createPutObjectRequest(final String folderName, final String filename, final byte[] bytes, final boolean enablePublicAccess) {
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);
		final InputStream inputStream = new ByteArrayInputStream(bytes);
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + (null != filename ? filename : ApplicationConstants.EMPTY_STRING);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(getAWSHelperService().getBucketName(), key, inputStream, metadata);
		if (enablePublicAccess) {
			putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		}
		return putObjectRequest;
	}
	
	private static PutObjectRequest createPutObjectRequest(final String folderName, final String customFilename, final File file, final boolean enablePublicAccess) {
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + (ValidationUtils.validatePlainNotNullAndEmptyTextString(customFilename) ? customFilename : file.getName());
		final PutObjectRequest putObjectRequest = new PutObjectRequest(getAWSHelperService().getBucketName(), key, file);
		if (enablePublicAccess) {
			putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		}
		return putObjectRequest;
	}
	
	private static PutObjectResult putObjectInS3Client(final PutObjectRequest putObjectRequest) {
		return getAWSHelperService().getS3client().putObject(putObjectRequest);
	}
	
	private static void deleteObjectFromS3Client(final String key) {
		getAWSHelperService().getS3client().deleteObject(getAWSHelperService().getBucketName(), key);
	}
	
	public static AWSHelperService getAWSHelperService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_AWS_HELPER_SERVICE, AWSHelperService.class);
	}
}