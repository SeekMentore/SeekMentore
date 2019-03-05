package com.model.workbook;

public class WorkbookCell {
	
	private Object value;
	private Boolean isCellStyled = false;
	private TypeOfStyleEnum typeOfStyleEnum;
	
	public enum TypeOfStyleEnum {
		DEFAULT_HEADER_CELL,
		SOLID_FOREGROUND_YELLOW,
		SOLID_FOREGROUND_GOLD,
		SOLID_FOREGROUND_LAVENDER;
	}
	
	public WorkbookCell(final Object value) {
		this.value = value;
		this.isCellStyled = false;
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum typeOfStyleEnum) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		this.typeOfStyleEnum = typeOfStyleEnum;
	}

	public Object getValue() {
		return value;
	}

	public TypeOfStyleEnum getTypeOfStyleEnum() {
		return typeOfStyleEnum;
	}

	public Boolean getIsCellStyled() {
		return isCellStyled;
	}
}
