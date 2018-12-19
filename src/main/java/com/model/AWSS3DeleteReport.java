package com.model;

import java.io.Serializable;

import com.utils.ValidationUtils;

public class AWSS3DeleteReport extends GridComponentObject implements Serializable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -6349692224199736678L;
	
	private Long s3DeleteId;
	private Long occuredAtMillis;
	private String fsKeyToBeDeleted;
	private String fsKeyRecycled;
	private String isFolder;
	private String userId;
	private String userType;
	
	public AWSS3DeleteReport() {}
	
	public AWSS3DeleteReport (
			Long occuredAtMillis,
			String fsKeyToBeDeleted,
			String fsKeyRecycled,
			String isFolder,
			String userId,
			String userType
	) {
		this.occuredAtMillis = occuredAtMillis;
		this.fsKeyToBeDeleted = fsKeyToBeDeleted;
		this.fsKeyRecycled = fsKeyRecycled;
		this.isFolder = isFolder;
		this.userId = userId;
		this.userType = userType;
	}

	public Long getS3DeleteId() {
		return s3DeleteId;
	}

	public void setS3DeleteId(Long s3DeleteId) {
		this.s3DeleteId = s3DeleteId;
	}

	public Long getOccuredAtMillis() {
		return occuredAtMillis;
	}

	public void setOccuredAtMillis(Long occuredAtMillis) {
		this.occuredAtMillis = occuredAtMillis;
	}

	public String getFsKeyToBeDeleted() {
		return fsKeyToBeDeleted;
	}

	public void setFsKeyToBeDeleted(String fsKeyToBeDeleted) {
		this.fsKeyToBeDeleted = fsKeyToBeDeleted;
	}

	public String getFsKeyRecycled() {
		return fsKeyRecycled;
	}

	public void setFsKeyRecycled(String fsKeyRecycled) {
		this.fsKeyRecycled = fsKeyRecycled;
	}

	public String getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(String isFolder) {
		this.isFolder = isFolder;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public Object[] getReportHeaders(final String reportSwitch) {
		return null;
	}

	@Override
	public Object[] getReportRecords(String reportSwitch) {
		return null;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "s3DeleteId" : return "S3_DELETE_ID";
			case "occuredAtMillis" : return "OCCURED_AT_MILLIS";
			case "fsKeyToBeDeleted" : return "FS_KEY_TO_BE_DELETED";
			case "fsKeyRecycled" : return "FS_KEY_RECYCLED";
			case "isFolder" : return "IS_FOLDER";
			case "userId" : return "USER_ID";
			case "userType" : return "USER_TYPE";
		}
		return EMPTY_STRING;
	}
}
