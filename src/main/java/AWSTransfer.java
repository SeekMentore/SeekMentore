import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.json.JsonArray;
import javax.json.JsonObject;

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
import com.utils.ExceptionUtils;
import com.utils.JSONUtils;

public class AWSTransfer {
	
	private String MIGRATE_FROM_BUCKET = "seekmentore-dev";
	private String MIGRATE_TO_BUCKET = "test-mashery";
	
	class ChangedFile {
		String action;
		String filePath;
		
		ChangedFile(String action, String filePath) {
			this.action = action;
			this.filePath = filePath;
		}
		
		public String toString() {
			return action + " " + filePath;
		}
	}
	
	private String CHANGES_JSON_FILE_PATH = "C:/Users/smukherjee/Documents/GitHub/SeekMentore/src/filesystem/changes/2019-04-11-16-46.json";
	
	private AmazonS3 s3client;
	
	public void connect() {
		final AWSCredentials newcredentials = new BasicAWSCredentials("", "");
		this.s3client = new AmazonS3Client(newcredentials);
	}
	
	AWSTransfer() throws IOException {
		//connect();
		//processChangedFiles(createChangedFilesList());
		//String bucketName = "seekmentore-dev";;
		//deleteBucket("test-mashery");
		//createNewBucketInS3Client("test-mashery");
		/*List<Bucket> bList = getAllBucketListForS3Client();
		for (Bucket bucket : bList) {
			System.out.println(bucket.getName());
		}*/
		//deleteAllContentFromBucket("seekmentore-dev2");
		//copyBucket("seekmentore-prod", "seekmentore-dev2");
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
	
	public String readChangedJSONStringFile(final String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    StringBuilder builder = new StringBuilder("");
	    String currentLine = reader.readLine();
	    while (currentLine != null) {
	        builder.append(currentLine);
	        builder.append("\n");
	        currentLine = reader.readLine();
	    }
	    reader.close();
	    return builder.toString();
	}
	
	public List<ChangedFile> createChangedFilesList() throws IOException {
		final String changesJSONString = readChangedJSONStringFile(this.CHANGES_JSON_FILE_PATH);
		final JsonArray changedFilesJsonArray = JSONUtils.getJSONArrayFromString(changesJSONString);
		final List<ChangedFile> changedFiles = new LinkedList<ChangedFile>();
		for (Object object : changedFilesJsonArray) {
			final JsonObject jsonObject = (JsonObject) object;
			final String action = JSONUtils.getValueFromJSONObject(jsonObject, "Action", String.class);
			final String filePath = JSONUtils.getValueFromJSONObject(jsonObject, "FilePath", String.class);
			changedFiles.add(new ChangedFile(action, filePath));
		}
		return changedFiles;
	}
	
	public void processChangedFiles(final List<ChangedFile> changedFiles) throws IOException {
		System.out.println("Reading Change List File >> " + CHANGES_JSON_FILE_PATH);
		System.out.println("Migrate From >> " + MIGRATE_FROM_BUCKET);
		System.out.println("Migrate To >> " + MIGRATE_TO_BUCKET);
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("Read the details above and then type 'CONFIRM' and press ENTER to move forward with Migration, type anything else to ABORT");
		String confirmText = sc.next();
		sc.close();
		if ("CONFIRM".equals(confirmText)) {
			System.out.println("Proceeding with Migration");
			for (final ChangedFile changedFile : changedFiles) {
				try {
					switch(changedFile.action) {
						case "InsertedNew" : {
							insertNewChangedFile(changedFile);
							break;
						}
					}
				} catch (Exception e) {
					System.out.println("Excetion occurred for Changed File >> " + changedFile);
					System.out.println("Exception Message Started");
					System.out.println(ExceptionUtils.generateErrorLog(e));
					System.out.println("Exception Message Ended");
				}
			}
			System.out.println("Migration Process Completed");
		} else {
			System.out.println("Migration Aborted");
		}
	}
	
	public void insertNewChangedFile(final ChangedFile changedFile) throws IOException {
		System.out.println("Placing New File >> " + changedFile.filePath);
		putObjectInS3Client(createPutObjectRequest(MIGRATE_TO_BUCKET, changedFile.filePath, readContentFromFileInS3Client(MIGRATE_FROM_BUCKET, changedFile.filePath), false));
		System.out.println("Completed");
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	public void copyBucket(String fromBucket, String toBucket) throws IOException {
		List<S3ObjectSummary> s3ObjectList = getFileListInBucket(fromBucket);
		for (S3ObjectSummary s3Object : s3ObjectList) {
			String key = s3Object.getKey();
			System.out.print(key);
			if (key.indexOf(ApplicationConstants.FORWARD_SLASH) != -1 && key.lastIndexOf(ApplicationConstants.FORWARD_SLASH) == key.length() - 1) {
				System.out.println("-------This is Folder");
				/*List<S3ObjectSummary> s3ObjectList1 = getS3ObjectListInFolder(originalBucket, key.substring(0 ,key.length() - 1));
				for (S3ObjectSummary s3Object1 : s3ObjectList1) {
					System.out.println("			"+s3Object1.getKey());
				}*/
				putObjectInS3Client(createPutObjectRequest(toBucket, key, new byte[0], false));
			} else {
				System.out.println("-------This is File");
				putObjectInS3Client(createPutObjectRequest(toBucket, key, readContentFromFileInS3Client(fromBucket, key), false));
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
