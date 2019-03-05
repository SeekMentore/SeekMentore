package com.model.workbook;

import java.io.Serializable;
import java.util.List;

public class WorkbookRecord implements Serializable {

	private static final long serialVersionUID = -8681265564543881119L;
	
	private List<WorkbookCell> record;
	private Boolean isRecordStyled = false;
	private TypeOfStyleEnum typeOfStyleEnum;
	
	public enum TypeOfStyleEnum {
		SOLID_FOREGROUND_YELLOW,
		SOLID_FOREGROUND_GOLD,
		SOLID_FOREGROUND_LAVENDER;
	}
	
	public WorkbookRecord(final List<WorkbookCell> record, final TypeOfStyleEnum typeOfStyleEnum) {
		this.record = record;
		this.setIsRecordStyled(true);
		this.setTypeOfStyleEnum(typeOfStyleEnum);
	}
	
	public WorkbookRecord(final List<WorkbookCell> record) {
		this.record = record;
	}

	public List<WorkbookCell> getRecord() {
		return record;
	}

	public Boolean getIsRecordStyled() {
		return isRecordStyled;
	}

	public void setIsRecordStyled(Boolean isRecordStyled) {
		this.isRecordStyled = isRecordStyled;
	}

	public TypeOfStyleEnum getTypeOfStyleEnum() {
		return typeOfStyleEnum;
	}

	public void setTypeOfStyleEnum(TypeOfStyleEnum typeOfStyleEnum) {
		this.typeOfStyleEnum = typeOfStyleEnum;
	}
}
