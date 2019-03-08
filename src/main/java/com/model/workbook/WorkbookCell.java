package com.model.workbook;

import com.exception.ApplicationException;
import com.utils.ValidationUtils;

public class WorkbookCell {
	
	private Object value;
	private Boolean isCellStyled = false;
	private TypeOfStyleEnum[] typeOfStyleEnums;
	private Boolean isCellMerged = false;
	private Integer numberOfMergedColumnsForThisCell;
	private Integer numberOfMergedRowsForThisCell;
	private Boolean hasImage = false;
	private String imageURL;
	private Integer columnsToLeaveFromCellColumnStart;
	private Integer rowsToLeaveFromCellRowStart;
	private Integer totalColumnsToCaptureInCell;
	private Integer totalRowsToCaptureInCell;
	private Boolean isImageLocatedInSecuredServer = false;
	
	public enum TypeOfStyleEnum {
		DEFAULT_HEADER_CELL,
		BOLD_HEADER_CELL,
		BORDER_THIN_BLACK,
		BORDER_THIN_DARK_BLUE,
		CONTENT_VERTICAL_CENTER,
		CONTENT_HORIZONTAL_CENTER,
		SOLID_FOREGROUND_YELLOW,
		SOLID_FOREGROUND_GOLD,
		SOLID_FOREGROUND_LAVENDER,
		SOLID_FOREGROUND_DARK_BLUE,
		SOLID_FOREGROUND_LIGHT_GREY,
		FONT_COLOR_RED,
		FONT_COLOR_GREEN,
		FONT_COLOR_WHITE;
	}
	
	public WorkbookCell(final Object value) {
		this.value = value;
		this.isCellStyled = false;
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum typeOfStyleEnum) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		if (ValidationUtils.checkObjectAvailability(typeOfStyleEnum)) {
			this.typeOfStyleEnums = new TypeOfStyleEnum[] {typeOfStyleEnum};
		}
		if (this.isCellStyled && !ValidationUtils.checkNonEmptyArray(this.typeOfStyleEnums)) {
			this.isCellStyled = false;
		}
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum[] typeOfStyleEnums) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		this.typeOfStyleEnums = typeOfStyleEnums;
		if (this.isCellStyled && !ValidationUtils.checkNonEmptyArray(this.typeOfStyleEnums)) {
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
		if (ValidationUtils.checkObjectAvailability(typeOfStyleEnum)) {
			this.typeOfStyleEnums = new TypeOfStyleEnum[] {typeOfStyleEnum};
		}
		if (this.isCellStyled && !ValidationUtils.checkNonEmptyArray(this.typeOfStyleEnums)) {
			this.isCellStyled = false;
		}
		this.isCellMerged = isCellMerged;
		this.numberOfMergedColumnsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedColumnsForThisCell) ? numberOfMergedColumnsForThisCell : 1;
		this.numberOfMergedRowsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedRowsForThisCell) ? numberOfMergedRowsForThisCell : 1;
		if (this.isCellMerged && (this.numberOfMergedColumnsForThisCell <= 1 && this.numberOfMergedRowsForThisCell <= 1)) {
			this.isCellMerged = false;
		}
	}
	
	public WorkbookCell(final Object value, final Boolean isCellStyled, final TypeOfStyleEnum[] typeOfStyleEnums, final Boolean isCellMerged, final Integer numberOfMergedColumnsForThisCell, final Integer numberOfMergedRowsForThisCell) {
		this.value = value;
		this.isCellStyled = isCellStyled;
		this.typeOfStyleEnums = typeOfStyleEnums;
		if (this.isCellStyled && !ValidationUtils.checkNonEmptyArray(this.typeOfStyleEnums)) {
			this.isCellStyled = false;
		}
		this.isCellMerged = isCellMerged;
		this.numberOfMergedColumnsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedColumnsForThisCell) ? numberOfMergedColumnsForThisCell : 1;
		this.numberOfMergedRowsForThisCell = ValidationUtils.checkNonNegativeNumberAvailability(numberOfMergedRowsForThisCell) ? numberOfMergedRowsForThisCell : 1;
		if (this.isCellMerged && (this.numberOfMergedColumnsForThisCell <= 1 && this.numberOfMergedRowsForThisCell <= 1)) {
			this.isCellMerged = false;
		}
	}
	
	public void setImage (
			final String imageURL, 
			final Boolean fullCellCapture,
			final Integer columnsToLeaveFromCellColumnStart,
			final Integer rowsToLeaveFromCellRowStart,
			final Integer totalColumnsToCaptureInCell,
			final Integer totalRowsToCaptureInCell,
			final Boolean isImageLocatedInSecuredServer
	) {
		if (ValidationUtils.checkStringAvailability(imageURL)) {
			this.hasImage = true;
			this.imageURL = imageURL;
			this.columnsToLeaveFromCellColumnStart = 0;
			this.rowsToLeaveFromCellRowStart = 0;
			this.totalColumnsToCaptureInCell = 1;
			this.totalRowsToCaptureInCell = 1;
			this.isImageLocatedInSecuredServer = false;
			if (ValidationUtils.checkObjectAvailability(fullCellCapture) && fullCellCapture) {
				if (this.isCellMerged) {
					this.totalColumnsToCaptureInCell = this.numberOfMergedColumnsForThisCell;
					this.totalRowsToCaptureInCell = this.numberOfMergedRowsForThisCell;
				}
			}  else {
				if (ValidationUtils.checkObjectAvailability(columnsToLeaveFromCellColumnStart)) {
					this.columnsToLeaveFromCellColumnStart = columnsToLeaveFromCellColumnStart;
				}
				if (ValidationUtils.checkObjectAvailability(rowsToLeaveFromCellRowStart)) {
					this.rowsToLeaveFromCellRowStart = rowsToLeaveFromCellRowStart;
				}
				if (ValidationUtils.checkObjectAvailability(totalColumnsToCaptureInCell)) {
					this.totalColumnsToCaptureInCell = totalColumnsToCaptureInCell;
				}
				if (ValidationUtils.checkObjectAvailability(totalRowsToCaptureInCell)) {
					this.totalRowsToCaptureInCell = totalRowsToCaptureInCell;
				}
				if (this.columnsToLeaveFromCellColumnStart > 0 || this.rowsToLeaveFromCellRowStart > 0 || this.totalColumnsToCaptureInCell > 1 || this.totalRowsToCaptureInCell > 1) {
					if (!this.isCellMerged) {
						throw new ApplicationException("Cannot leave padding columns for Image in a WokbookCell which does not have Merged XSSFCells");
					} else {
						Boolean isImageColumnCaptureRangeValid = true;
						if (this.columnsToLeaveFromCellColumnStart >= this.numberOfMergedColumnsForThisCell) {
							isImageColumnCaptureRangeValid = false;
						}
						if (this.columnsToLeaveFromCellColumnStart + this.totalColumnsToCaptureInCell > this.numberOfMergedColumnsForThisCell) {
							isImageColumnCaptureRangeValid = false;
						}
						Boolean isImageRowCaptureRangeValid = true;
						if (this.rowsToLeaveFromCellRowStart >= this.numberOfMergedRowsForThisCell) {
							isImageRowCaptureRangeValid = false;
						}
						if (this.rowsToLeaveFromCellRowStart + this.totalRowsToCaptureInCell > this.numberOfMergedRowsForThisCell) {
							isImageRowCaptureRangeValid = false;
						}
						if (!isImageColumnCaptureRangeValid || !isImageRowCaptureRangeValid) {
							throw new ApplicationException("Cannot place Image in WokbookCell, the Cell range is smaller than Image capture range");
						}
					}
				}
			}
			if (ValidationUtils.checkObjectAvailability(isImageLocatedInSecuredServer)) {
				this.isImageLocatedInSecuredServer = isImageLocatedInSecuredServer;
			}
		}
	}

	public Object getValue() {
		return value;
	}

	public TypeOfStyleEnum[] getTypeOfStyleEnums() {
		return typeOfStyleEnums;
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
	
	public String getImageURL() {
		return imageURL;
	}

	public Boolean getHasImage() {
		return hasImage;
	}
	
	public Boolean getIsImageLocatedInSecuredServer() {
		return isImageLocatedInSecuredServer;
	}
	
	public Integer getColumnsToLeaveFromCellColumnStart() {
		return columnsToLeaveFromCellColumnStart;
	}

	public Integer getRowsToLeaveFromCellRowStart() {
		return rowsToLeaveFromCellRowStart;
	}

	public Integer getTotalColumnsToCaptureInCell() {
		return totalColumnsToCaptureInCell;
	}

	public Integer getTotalRowsToCaptureInCell() {
		return totalRowsToCaptureInCell;
	}

}
