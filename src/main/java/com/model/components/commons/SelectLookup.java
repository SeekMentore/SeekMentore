package com.model.components.commons;

import java.io.Serializable;

import com.constants.components.SelectLookupConstants;

public class SelectLookup implements Serializable, SelectLookupConstants {
	
	private static final long serialVersionUID = -1763649873039566289L;
	private String value;
	private String label;
	private String category;
	private String orderOfCategory;
	private String orderInCategory;
	private String description;
	
	public SelectLookup() {}
	
	public SelectLookup (String value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SelectLookup && obj != null && this.value.equals(((SelectLookup)obj).getValue());
	}
	
	@Override
	public int hashCode() {
		return this.value.hashCode();
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
	
	public String getOrderOfCategory() {
		return orderOfCategory;
	}

	public void setOrderOfCategory(String orderOfCategory) {
		this.orderOfCategory = orderOfCategory;
	}

	public String getOrderInCategory() {
		return orderInCategory;
	}

	public void setOrderInCategory(String orderInCategory) {
		this.orderInCategory = orderInCategory;
	}
}
