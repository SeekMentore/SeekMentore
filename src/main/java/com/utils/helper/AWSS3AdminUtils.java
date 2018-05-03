package com.utils.helper;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.constants.BeanConstants;
import com.service.AWSHelperService;
import com.utils.context.AppContext;

public class AWSS3AdminUtils {
	
	public static List<Bucket> getAllBucketListForS3Client() {
		return getAWSHelperService().getS3client().listBuckets();
	}
	public static Bucket createNewBucketInS3Client(final String bucketName) {
		return getAWSHelperService().getS3client().createBucket(bucketName);
	}
	public static List<S3ObjectSummary> getFileListInBucket(final String bucketName) {
		return getAWSHelperService().getS3client().listObjects(bucketName).getObjectSummaries();
	}
	public static void deleteBucket(final String bucketName) {
		getAWSHelperService().getS3client().deleteBucket(bucketName);
	}
	
	public static AWSHelperService getAWSHelperService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_AWS_HELPER_SERVICE, AWSHelperService.class);
	}
}