package com.model.control;

import java.io.Serializable;

public class S3ClientParams implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private String accessKeyEncypted;
	private String secretAccessEncypted;
	private String bucketNameEncypted;
	
	public String getAccessKeyEncypted() {
		return accessKeyEncypted;
	}

	public void setAccessKeyEncypted(String accessKeyEncypted) {
		this.accessKeyEncypted = accessKeyEncypted;
	}

	public String getBucketNameEncypted() {
		return bucketNameEncypted;
	}

	public void setBucketNameEncypted(String bucketNameEncypted) {
		this.bucketNameEncypted = bucketNameEncypted;
	}

	public String getSecretAccessEncypted() {
		return secretAccessEncypted;
	}

	public void setSecretAccessEncypted(String secretAccessEncypted) {
		this.secretAccessEncypted = secretAccessEncypted;
	}
}
