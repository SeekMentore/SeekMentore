import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.util.IOUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
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
	
	AWSTransfer() throws IOException {
		//String bucketName = "seekmentore-dev";;
		final AWSCredentials credentials = new BasicAWSCredentials("AKIAITIIPMBFXRB2QRDA", "tr0Dh4dLH3gQ7CIxOLzuM/SLA0/gaAXcPhgJrAMM");
		this.s3client = new AmazonS3Client(credentials);
		//deleteBucket("seekmentore-dev2");
		//createNewBucketInS3Client("seekmentore-dev2");
		/*List<Bucket> bList = getAllBucketListForS3Client();
		for (Bucket bucket : bList) {
			if (!bucket.getName().equals("seekmentore-prod")) {
				System.out.println(bucket.getName());
			}
		}*/
		//deleteAllContentFromBucket( "seekmentore-dev");
		//copyBucket("", "seekmentore-dev");
		//deleteBucket("seekmentore-dev2");
	}
	
	public static void main(String args[]) throws IOException {
		new AWSTransfer();
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
