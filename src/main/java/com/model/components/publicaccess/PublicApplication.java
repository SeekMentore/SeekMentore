package com.model.components.publicaccess;

import java.io.Serializable;

import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public abstract class PublicApplication extends GridComponentObject implements Serializable {

	private static final long serialVersionUID = -2232244327975645054L;
	
	private Boolean flag;
	private String isDataMigrated;
	private Long whenMigratedMillis;
	private String captchaResponse;

	public Boolean isFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	public String getIsDataMigrated() {
		return isDataMigrated;
	}

	public void setIsDataMigrated(String isDataMigrated) {
		this.isDataMigrated = isDataMigrated;
	}

	public Boolean getFlag() {
		return flag;
	}

	public Long getWhenMigratedMillis() {
		return whenMigratedMillis;
	}

	public void setWhenMigratedMillis(Long whenMigratedMillis) {
		this.whenMigratedMillis = whenMigratedMillis;
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "isDataMigrated" : return "IS_DATA_MIGRATED";
			case "whenMigratedMillis" : return "WHEN_MIGRATED_MILLIS";
		}
		return EMPTY_STRING;
	}
}
