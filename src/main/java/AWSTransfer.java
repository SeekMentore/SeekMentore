import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.util.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.constants.ApplicationConstants;

public class AWSTransfer {
	private AmazonS3 s3client;
	
	public void connect() {
		final AWSCredentials newcredentials = new BasicAWSCredentials("", "");
		this.s3client = new AmazonS3Client(newcredentials);
	}
	
	AWSTransfer() throws IOException {
		connect();
		//String bucketName = "seekmentore-dev";;
		//deleteBucket("test-mashery");
		//createNewBucketInS3Client("test-mashery");
		/*List<Bucket> bList = getAllBucketListForS3Client();
		for (Bucket bucket : bList) {
			System.out.println(bucket.getName());
		}*/
		//deleteAllContentFromBucket("seekmentore-dev2");
		//copyBucket("seekmentore-dev", "seekmentore-dev2");
		//copyBucket("seekmentore-dev2", "seekmentore-dev");
		//deleteBucket("seekmentore-dev2");
		//deleteFolder("seekmentore-dev2", "recycle_bin");
		//putObjectInS3Client(createPutObjectRequest("seekmentore-dev2", "recycle_bin/1(1).txt", "Hello New 1".getBytes(), false));
		//readAllContentFromBucket("seekmentore-dev2");
		//recycleBinComputation("seekmentore-dev2", "recycle_bin/");
		/*for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder("seekmentore-dev2", "recycle_bin")) {
			deleteObjectFromS3Client("seekmentore-dev2", objectSummary.getKey());
			System.out.println(objectSummary.getKey());
			//break;
		}*/
		//deleteObjectFromS3Client("seekmentore-dev2", "secured/tutor/documents/21/PROFILE_PHOTO.jpg");
	}
	
	public void moveFileToDifferentLocationInSameBucket(final String bucket, final String fromFsKey, final String toFsKey) throws IOException {
		System.out.println("FROM >> [" + fromFsKey + "] : TO >> [" + toFsKey + "]");
		putObjectInS3Client(createPutObjectRequest(bucket, toFsKey, readContentFromFileInS3Client(bucket, fromFsKey), false));
	}
	
	public Boolean recycleBinComputation(final String bucket, final String key) {
		Boolean recycleSuccessfull = false;
		try {
			if (!(key.indexOf(ApplicationConstants.FORWARD_SLASH) != -1 && key.lastIndexOf(ApplicationConstants.FORWARD_SLASH) == key.length() - 1)) {
				// This is File
				String fileKeyThatDoesNotExists = "recycle_bin/" + key;
				Boolean objectExists = false;
				int counter = 0;
				do {
					try {
						ObjectMetadata objMeta = s3client.getObjectMetadata(bucket, fileKeyThatDoesNotExists);
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
						fileKeyThatDoesNotExists = "recycle_bin/" + key.substring(0, key.lastIndexOf(".")) + "(" + counter + ")" + key.substring(key.lastIndexOf("."));
					}
				} while(objectExists);
				System.out.println(fileKeyThatDoesNotExists);
				System.out.println("Copying contents from <" + key + "> to <" + fileKeyThatDoesNotExists + ">");
				putObjectInS3Client(createPutObjectRequest(bucket, fileKeyThatDoesNotExists, readContentFromFileInS3Client(bucket, key), false));
				recycleSuccessfull = true;
			}
		} catch (Exception e) {
			
		}
		return recycleSuccessfull;
	}
	
	public static void main(String args[]) throws IOException {
		new AWSTransfer();
	}
	
	public void readAllContentFromBucket(String copyBucket) {
		List<S3ObjectSummary> s3ObjectList = getFileListInBucket(copyBucket);
		for (S3ObjectSummary s3Object : s3ObjectList) {
			String key = s3Object.getKey();
			System.out.println(key);
		}
	}
	
	public void deleteAllContentFromBucket(String copyBucket) {
		List<S3ObjectSummary> s3ObjectList = getFileListInBucket(copyBucket);
		for (S3ObjectSummary s3Object : s3ObjectList) {
			String key = s3Object.getKey();
			deleteObjectFromS3Client(copyBucket, key);
		}
	}
	
	public void copyBucket(String originalBucket, String copyBucket) throws IOException {
		List<S3ObjectSummary> s3ObjectList = getFileListInBucket(originalBucket);
		for (S3ObjectSummary s3Object : s3ObjectList) {
			String key = s3Object.getKey();
			System.out.print(key);
			if (key.indexOf(ApplicationConstants.FORWARD_SLASH) != -1 && key.lastIndexOf(ApplicationConstants.FORWARD_SLASH) == key.length() - 1) {
				System.out.println("-------This is Folder");
				/*List<S3ObjectSummary> s3ObjectList1 = getS3ObjectListInFolder(originalBucket, key.substring(0 ,key.length() - 1));
				for (S3ObjectSummary s3Object1 : s3ObjectList1) {
					System.out.println("			"+s3Object1.getKey());
				}*/
				putObjectInS3Client(createPutObjectRequest(copyBucket, key, new byte[0], false));
			} else {
				System.out.println("-------This is File");
				putObjectInS3Client(createPutObjectRequest(copyBucket, key, readContentFromFileInS3Client(originalBucket, key), false));
			}
		}
	}
	
	public List<Bucket> getAllBucketListForS3Client() {
		return s3client.listBuckets();
	}
	
	public Bucket createNewBucketInS3Client(final String bucketName) {
		return s3client.createBucket(bucketName);
	}
	
	public List<S3ObjectSummary> getFileListInBucket(final String bucketName) {
		return s3client.listObjects(bucketName).getObjectSummaries();
	}
	
	public void deleteBucket(final String bucketName) {
		s3client.deleteBucket(bucketName);
	}
	
	public void deleteFolder(String bucketName, final String folderName) {
		for (final S3ObjectSummary objectSummary : getS3ObjectListInFolder(bucketName, folderName)) {
			deleteObjectFromS3Client(bucketName, objectSummary.getKey());
		}
		deleteObjectFromS3Client(bucketName, folderName);
	}
	
	public void deleteFileInFolder(String bucketName, final String key) {
		deleteObjectFromS3Client(bucketName, key);
	}
	
	private void deleteObjectFromS3Client(String bucketName, final String key) {
		//recycleBinComputation(bucketName, key);
		System.out.println("DELETING >> [" + key + "]");
		s3client.deleteObject(bucketName, key);
	}
	
	private List<S3ObjectSummary> getS3ObjectListInFolder(String bucketName, final String folderName) {
		return s3client.listObjects(bucketName, folderName).getObjectSummaries();
	}
	
	private PutObjectRequest createPutObjectRequest(String bucketName, final String key, final byte[] bytes, final boolean enablePublicAccess) {
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);
		final InputStream inputStream = new ByteArrayInputStream(bytes);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
		if (enablePublicAccess) {
			putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
		}
		return putObjectRequest;
	}
	
	private PutObjectResult putObjectInS3Client(final PutObjectRequest putObjectRequest) {
		return s3client.putObject(putObjectRequest);
	}
	
	public byte[] readContentFromFileInS3Client(String bucketName, final String key) throws IOException {
		final S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, key));
	    final InputStream stream = s3object.getObjectContent();
	    return IOUtils.toByteArray(stream);
	}
}
