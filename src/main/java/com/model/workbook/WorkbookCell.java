package com.model.workbook;

import com.utils.ValidationUtils;

public class WorkbookCell {
	
	private Object value;
	private Boolean isCellStyled = false;
	private TypeOfStyleEnum typeOfStyleEnum;
	private Boolean isCellMerged = false;
	private Integer numberOfMergedColumnsForThisCell;
	private Integer numberOfMergedRowsForThisCell;
	
	public enum TypeOfStyleEnum {
		DEFAULT_HEADER_CELL,
		SOLID_FOREGROUND_YELLOW,
		SOLID_FOREGROUND_GOLD,
		SOLID_FOREGROUND_LAVENDER,
		SOLID_FOREGROUND_GREY;
	}
	
	public WorkbookCell(final Object value) {
		this.value = value;
		this.isCellStyled = false;
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum typeOfStyleEnum) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		this.typeOfStyleEnum = typeOfStyleEnum;
		if (this.isCellStyled && !ValidationUtils.checkObjectAvailability(this.typeOfStyleEnum)) {
			this.isCellStyled = false;
		}
	}
	
	public WorkbookCell(final Object value, final Boolean isCellMerged, final Integer numberOfMergedColumnsForThisCell, final Integer numberOfMergedRowsForThisCell) {
		this.value = value;
		this.isCellMerged = isCellMerged;
		this.numberOfMergedColumnsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedColumnsForThisCell) ? numberOfMergedColumnsForThisCell : 1;
		this.numberOfMergedRowsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedRowsForThisCell) ? numberOfMergedRowsForThisCell : 1;
		if (this.isCellMerged && (this.numberOfMergedColumnsForThisCell <= 1 && this.numberOfMergedRowsForThisCell <= 1)) {
			this.isCellMerged = false;
		}
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum typeOfStyleEnum, final Boolean isCellMerged, final Integer numberOfMergedColumnsForThisCell, final Integer numberOfMergedRowsForThisCell) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		this.typeOfStyleEnum = typeOfStyleEnum;
		if (this.isCellStyled && !ValidationUtils.checkObjectAvailability(this.typeOfStyleEnum)) {
			this.isCellStyled = false;
		}
		this.isCellMerged = isCellMerged;
		this.numberOfMergedColumnsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedColumnsForThisCell) ? numberOfMergedColumnsForThisCell : 1;
		this.numberOfMergedRowsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedRowsForThisCell) ? numberOfMergedRowsForThisCell : 1;
		if (this.isCellMerged && (this.numberOfMergedColumnsForThisCell <= 1 && this.numberOfMergedRowsForThisCell <= 1)) {
			this.isCellMerged = false;
		}
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

	public Boolean getIsCellMerged() {
		return isCellMerged;
	}

	public Integer getNumberOfMergedColumnsForThisCell() {
		return numberOfMergedColumnsForThisCell;
	}

	public Integer getNumberOfMergedRowsForThisCell() {
		return numberOfMergedRowsForThisCell;
	}
}
