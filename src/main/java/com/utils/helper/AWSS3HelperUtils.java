package com.utils.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.IOUtils;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.constants.AWSConstants;
import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.AWSS3DeleteReport;
import com.model.ErrorPacket;
import com.model.User;
import com.service.AWSHelperService;
import com.service.QueryMapperService;
import com.service.components.CommonsService;
import com.utils.ExceptionUtils;
import com.utils.LoggerUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;

public class AWSS3HelperUtils implements AWSConstants {
	
	/**
	 * All 'folderName' are just the path till the folder minus the last \ sign
	 */
	
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
	
	public static String createFileUsingKey(final String key, final byte[] bytes, final boolean enablePublicAccess) {
		final PutObjectRequest putObjectRequest = createPutObjectRequestUsingKey(key, bytes, enablePublicAccess);
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
	
	public static byte[] readContentFromFileInS3Client(final String folderName, final String filename) throws IOException {
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + filename;
		final S3Object s3object = getAWSHelperService().getS3client().getObject(new GetObjectRequest(getAWSHelperService().getBucketName(), key));
	    final InputStream stream = s3object.getObjectContent();
	    return IOUtils.toByteArray(stream);
	}
	
	public static byte[] readContentFromFileUsingKeyInS3Client(final String key) throws IOException {
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
	
	private static PutObjectRequest createPutObjectRequestUsingKey(final String key, final byte[] bytes, final boolean enablePublicAccess) {
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);
		final InputStream inputStream = new ByteArrayInputStream(bytes);
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
	
	public static void deleteFolder(final String folderName, final User activeUser) {
		for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder(folderName)) {
			deleteObjectFromS3Client(objectSummary.getKey(), activeUser);
		}
		deleteObjectFromS3Client(folderName, activeUser);
	}
	
	public static void deleteFileInFolder(final String folderName, final String filename, final User activeUser) {
		final String key = folderName + ApplicationConstants.FORWARD_SLASH + filename;
		deleteObjectFromS3Client(key, activeUser);
	}
	
	public static void deleteFileInFolderUsingKeyInS3Client(final String key, final User activeUser) {
		deleteObjectFromS3Client(key, activeUser);
	}
	
	@Transactional
	private static void deleteObjectFromS3Client(final String key, final User activeUser) {
		if (ValidationUtils.checkObjectAvailability(activeUser)) {
			if (callRecycleBin(key, activeUser)) {
				getAWSHelperService().getS3client().deleteObject(getAWSHelperService().getBucketName(), key);
			} else {
				throw new ApplicationException("Cannot recycle key = " + key);
			}
		} else {
			throw new ApplicationException("Deletion in File System could only be performed if you are a valid and authorized user");
		}
	}
	
	private static Boolean callRecycleBin(final String key, final User activeUser) {
		LoggerUtils.logOnConsole("Recycling User = " + activeUser.getName() + " " + activeUser.getUserType() + " " + activeUser.getUserId());
		LoggerUtils.logOnConsole("Recycling key = " + key);
		final Date currentTimestamp = new Date();
		Boolean recycleSuccessfull = false;
		try {
			if (!(key.indexOf(ApplicationConstants.FORWARD_SLASH) != -1 && key.lastIndexOf(ApplicationConstants.FORWARD_SLASH) == key.length() - 1)) {
				LoggerUtils.logOnConsole("This is a file");
				String fileKeyThatDoesNotExists = RECYCLE_BIN_FOLDER_PATH + key;
				Boolean objectExists = false;
				int counter = 0;
				do {
					try {
						final ObjectMetadata objMeta = getObjectMetadata(fileKeyThatDoesNotExists);
						if (null != objMeta) {
							objectExists =  true;
						} else {
							objectExists = false;
						}
					} catch (AmazonS3Exception e) {
						objectExists = false;
					}
					if (objectExists) {
						counter++;
						fileKeyThatDoesNotExists = RECYCLE_BIN_FOLDER_PATH + key.substring(0, key.lastIndexOf(PERIOD)) + LEFT_ROUND_BRACKET + counter + RIGHT_ROUND_BRACKET + key.substring(key.lastIndexOf(PERIOD));
					}
				} while(objectExists);
				LoggerUtils.logOnConsole("fileKeyThatDoesNotExists in recycle bin = " + fileKeyThatDoesNotExists);
				getApplicationDao().executeUpdateWithQueryMapper("aws", "insertAWSS3DeleteReport", new AWSS3DeleteReport(currentTimestamp.getTime(), key, fileKeyThatDoesNotExists, NO, activeUser.getUserId(), activeUser.getUserType()));
				LoggerUtils.logOnConsole("Copying contents from <" + key + "> to <" + fileKeyThatDoesNotExists + ">");
				createFileUsingKey(fileKeyThatDoesNotExists, readContentFromFileUsingKeyInS3Client(key), false);
				recycleSuccessfull = true;
			} else {
				LoggerUtils.logOnConsole("This is a folder");
				getApplicationDao().executeUpdateWithQueryMapper("aws", "insertAWSS3DeleteReport", new AWSS3DeleteReport(currentTimestamp.getTime(), key, FOLDER_NOT_RECYCLED, NO, activeUser.getUserId(), activeUser.getUserType()));
				recycleSuccessfull = true;
			}
		} catch (Exception e) {
			final ErrorPacket errorPacket = new ErrorPacket(currentTimestamp.getTime(), "AWS_S3_DELETE_ERROR_REPORT", ExceptionUtils.generateErrorLog(e), true, activeUser);
			getCommonsService().feedErrorRecord(errorPacket);
			recycleSuccessfull = false;
		}
		return recycleSuccessfull;
	}
	
	private static ObjectMetadata getObjectMetadata(final String key) {
		return getAWSHelperService().getS3client().getObjectMetadata(getAWSHelperService().getBucketName(), key);
	}
	
	public static AWSHelperService getAWSHelperService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_AWS_HELPER_SERVICE, AWSHelperService.class);
	}
	
	public static ApplicationDao getApplicationDao() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_APPLICATION_DAO, ApplicationDao.class);
	}
	
	public static QueryMapperService getQueryMapperService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_QUERY_MAPPER_SERVICE, QueryMapperService.class);
	}
	
	public static CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
}