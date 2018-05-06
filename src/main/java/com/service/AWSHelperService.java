package com.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.constants.BeanConstants;
import com.constants.VelocityConstants;
import com.utils.SecurityUtil;

@Service(BeanConstants.BEAN_NAME_AWS_HELPER_SERVICE)
public class AWSHelperService implements VelocityConstants {
	
	private AmazonS3 s3client;
	private String bucketName;
	
	@Autowired
    private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
    private CipherService cipherService;
	
	@PostConstruct
	public void init() throws Exception {
		if (null == s3client) {
			final AWSCredentials credentials = 
					new BasicAWSCredentials(
						SecurityUtil.decrypt(jndiAndControlConfigurationLoadService.getControlConfiguration().getAwsParams().getS3ClientParams().getAccessKeyEncypted(), cipherService, jndiAndControlConfigurationLoadService), 
						SecurityUtil.decrypt(jndiAndControlConfigurationLoadService.getControlConfiguration().getAwsParams().getS3ClientParams().getSecretAccessEncypted(), cipherService, jndiAndControlConfigurationLoadService));
			this.s3client = new AmazonS3Client(credentials);
			this.bucketName = SecurityUtil.decrypt(jndiAndControlConfigurationLoadService.getControlConfiguration().getAwsParams().getS3ClientParams().getBucketNameEncypted(), cipherService, jndiAndControlConfigurationLoadService);
		}
	}

	public AmazonS3 getS3client() {
		return s3client;
	}

	public String getBucketName() {
		return bucketName;
	}
}
