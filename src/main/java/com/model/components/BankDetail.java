package com.model.components;

import java.io.Serializable;

import com.model.ApplicationWorkbookObject;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class BankDetail extends GridComponentObject implements Serializable, Cloneable, ApplicationWorkbookObject {
	
	private static final long serialVersionUID = -7058576359707327688L;
	
	private String bankAccountSerialId;
	private String bankName;
	private String accountNumber;
	private String ifscCode;
	private String accountHolderName;
	private String isDefault;
	private String tutorSerialId;
	private String isApproved;
	private String whoActed;
	private String whoActedName;
	private String remarks;
	private Long actionDateMillis;
	
	public BankDetail() {}

	public String getBankAccountSerialId() {
		return bankAccountSerialId;
	}

	public void setBankAccountSerialId(String bankAccountSerialId) {
		this.bankAccountSerialId = bankAccountSerialId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public String getTutorSerialId() {
		return tutorSerialId;
	}

	public void setTutorSerialId(String tutorSerialId) {
		this.tutorSerialId = tutorSerialId;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getWhoActed() {
		return whoActed;
	}

	public void setWhoActed(String whoActed) {
		this.whoActed = whoActed;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getActionDateMillis() {
		return actionDateMillis;
	}

	public void setActionDateMillis(Long actionDateMillis) {
		this.actionDateMillis = actionDateMillis;
	}

	public String getWhoActedName() {
		return whoActedName;
	}

	public void setWhoActedName(String whoActedName) {
		this.whoActedName = whoActedName;
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
			case "bankAccountSerialId" : return "BANK_ACCOUNT_SERIAL_ID";
			case "tutorSerialId" : return "TUTOR_SERIAL_ID";
			case "bankName" : return "BANK_NAME";
			case "accountNumber" : return "ACCOUNT_NUMBER";
			case "ifscCode" : return "IFSC_CODE";
			case "accountHolderName" : return "ACCOUNT_HOLDER_NAME";
			case "isDefault" : return "IS_DEFAULT";
			case "isApproved" : return "IS_APPROVED";
			case "whoActed" : return "WHO_ACTED";
			case "whoActedName" : return "WHO_ACTED_NAME";
			case "actionDateMillis" : return "ACTION_DATE_MILLIS";
			case "remarks" : return "REMARKS";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public BankDetail clone() throws CloneNotSupportedException {  
		return (BankDetail)super.clone();
	}
}
