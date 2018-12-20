package com.model.workbook;

import java.io.Serializable;

public class WorkbookRecord implements Serializable {

	private static final long serialVersionUID = -8681265564543881119L;
	
	private Object[] record;
	private Boolean isMismatch = false;
	private Boolean isExtra = false;
	private Boolean isMissing = false;
	
	public enum TypeOfMismatchEnum {
		DATA_MISMATCH,
		EXTRA_RECORD,
		MISSING_RECORD;
	}
	
	public WorkbookRecord(final Object[] record, Boolean typeOfMismatch, TypeOfMismatchEnum typeOfMismatchEnum) {
		this.record = record;
		switch (typeOfMismatchEnum) {
			case DATA_MISMATCH : {
				this.isMismatch = typeOfMismatch;
				break;
			}
			case EXTRA_RECORD : {
				this.isExtra = typeOfMismatch;
				break;
			}
			case MISSING_RECORD : {
				this.isMissing = typeOfMismatch;
				break;
			}
		}
	}
	
	public WorkbookRecord(final Object[] record) {
		this.record = record;
		this.isMismatch = false;
	}

	public Object[] getRecord() {
		return record;
	}

	public Boolean getIsMismatch() {
		return isMismatch;
	}

	public Boolean getIsMissing() {
		return isMissing;
	}

	public Boolean getIsExtra() {
		return isExtra;
	}
}
