package com.model.components;

import java.io.Serializable;

public class BankAccount implements Serializable {
	
	private static final long serialVersionUID = -7058576359707327688L;
	
	private Long bankAccountId;
	private String bankName;
	private String accountNumber;
	private String ifscCode;
	private String accountHolderName;
	private String isDefault;		 
	
	public BankAccount(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
		bankName = "KOTAK MAHINDRA";
		accountNumber = "1712179763";
		ifscCode = "Fake file";
		isDefault = bankAccountId%2 == 0 ? "Y" : "N";
		accountHolderName = "AMIT MISHRA";
	}

	public Long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
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
}
