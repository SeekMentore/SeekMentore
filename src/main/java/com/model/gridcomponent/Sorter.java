package com.model.gridcomponent;

import java.io.Serializable;

public class Sorter implements Serializable {

	private static final long serialVersionUID = 7647400278893275899L;
	
	private String id;
	private String type;
	private String mapping;
	private String columnId;
	private String columnName;
	private Integer order;
	
	public Sorter() {}
	
	public Sorter(String id, String type, String mapping, String columnId, String columnName, Integer order) {
		this.id = id;
		this.type = type;
		this.mapping = mapping;
		this.columnId = columnId;
		this.columnName = columnName;
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	
	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return this.id + " " + this.type + " " + this.mapping + " " + this.columnId + " " + this.columnName + " " + this.order;
	}
}
