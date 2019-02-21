package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class Contract extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {

	private static final long serialVersionUID = -6980182144471502902L;
	
	private String contractSerialId; 
	private String contractType;
	private Long initiatedDateMillis;
	private Long terminatedDateMillis;
	private String isActive;
	private String fsKey;
	private Long recordLastUpdatedMillis;
	private String updatedBy;
	private String updatedByName;
	
	public Contract() {}
	
	public String getContractSerialId() {
		return contractSerialId;
	}

	public void setContractSerialId(String contractSerialId) {
		this.contractSerialId = contractSerialId;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Long getInitiatedDateMillis() {
		return initiatedDateMillis;
	}

	public void setInitiatedDateMillis(Long initiatedDateMillis) {
		this.initiatedDateMillis = initiatedDateMillis;
	}

	public Long getTerminatedDateMillis() {
		return terminatedDateMillis;
	}

	public void setTerminatedDateMillis(Long terminatedDateMillis) {
		this.terminatedDateMillis = terminatedDateMillis;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getFsKey() {
		return fsKey;
	}

	public void setFsKey(String fsKey) {
		this.fsKey = fsKey;
	}

	public Long getRecordLastUpdatedMillis() {
		return recordLastUpdatedMillis;
	}

	public void setRecordLastUpdatedMillis(Long recordLastUpdatedMillis) {
		this.recordLastUpdatedMillis = recordLastUpdatedMillis;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	@Override
	public Object[] getReportHeaders(String reportSwitch) {
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
			case "contractSerialId" : return "CONTRACT_SERIAL_ID";
			case "contractType" : return "CONTRACT_TYPE";
			case "initiatedDateMillis" : return "INITIATED_DATE_MILLIS";
			case "terminatedDateMillis" : return "TERMINATED_DATE_MILLIS";
			case "isActive" : return "IS_ACTIVE";
			case "fsKey" : return "FS_KEY";
			case "recordLastUpdatedMillis" : return "RECORD_LAST_UPDATED_MILLIS";
			case "updatedBy" : return "UPDATED_BY";
			case "updatedByName" : return "UPDATED_BY_NAME";
		}
		return EMPTY_STRING;
	}

	@Override
	public Contract clone() throws CloneNotSupportedException {  
		return (Contract)super.clone();
	}
}
