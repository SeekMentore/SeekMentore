package com.model.control;

import java.io.Serializable;

public class AWSParams implements Serializable {
	
	private static final long serialVersionUID = 6693618464803123134L;
	
	private S3ClientParams s3ClientParams;

	public S3ClientParams getS3ClientParams() {
		return s3ClientParams;
	}

	public void setS3ClientParams(S3ClientParams s3ClientParams) {
		this.s3ClientParams = s3ClientParams;
	}
}
