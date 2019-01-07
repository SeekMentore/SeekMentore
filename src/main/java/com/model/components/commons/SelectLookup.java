package com.model.components.commons;

import java.io.Serializable;

import com.constants.components.SelectLookupConstants;
import com.model.GridComponentObject;
import com.utils.ValidationUtils;

public class SelectLookup extends GridComponentObject implements Serializable, SelectLookupConstants, Cloneable {
	
	private static final long serialVersionUID = -1763649873039566289L;
	
	private String value;
	private String label;
	private String category;
	private Integer orderOfCategory;
	private Integer orderInCategory;
	private String description;
	private String hidden;
	
	public SelectLookup() {}
	
	public SelectLookup(String value) {
		this.value = value;
	}
	
	public SelectLookup(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getOrderOfCategory() {
		return orderOfCategory;
	}

	public void setOrderOfCategory(Integer orderOfCategory) {
		this.orderOfCategory = orderOfCategory;
	}

	public Integer getOrderInCategory() {
		return orderInCategory;
	}

	public void setOrderInCategory(Integer orderInCategory) {
		this.orderInCategory = orderInCategory;
	}
	
	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public boolean equals(Object object) {
		return ValidationUtils.checkObjectAvailability(object) 
				&& object instanceof SelectLookup 
				&& ValidationUtils.checkStringAvailability(this.value) 
				&& ValidationUtils.checkStringAvailability(((SelectLookup)object).getValue()) 
				&& this.value.equals(((SelectLookup)object).getValue());
	}
	
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
	
	@Override
	public String resolveColumnNameForMapping(final String mappingProperty) {
		final String columnName = super.resolveColumnNameForMapping(mappingProperty);
		if (ValidationUtils.checkStringAvailability(columnName)) return columnName;
		switch(mappingProperty) {
			case "value" : return "VALUE";
			case "label" : return "LABEL";
			case "category" : return "CATEGORY";
			case "orderOfCategory" : return "ORDER_OF_CATEGORY";
			case "orderInCategory" : return "ORDER_IN_CATEGORY";
			case "description" : return "DESCRIPTION";
			case "hidden" : return "HIDDEN";
		}
		return EMPTY_STRING;
	}
	
	@Override
	public SelectLookup clone() throws CloneNotSupportedException {  
		return (SelectLookup)super.clone();
	}
}
