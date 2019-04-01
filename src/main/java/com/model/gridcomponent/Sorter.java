package com.model.gridcomponent;

import java.io.Serializable;
import java.util.List;

public class Sorter implements Serializable {

	private static final long serialVersionUID = 7647400278893275899L;
	
	private String id;
	private String type;
	private String mapping;
	private String columnId;
	private String columnName;
	private Integer order;
	private Boolean clubbedSorterMapping;
	private List<String> clubbedSorterProperties;
	
	public Sorter (
			final String id, 
			final String type, 
			final String mapping, 
			final String columnId, 
			final String columnName, 
			final Integer order,
			final Boolean clubbedSorterMapping, 
			final List<String> clubbedSorterProperties
	) {
		this.id = id;
		this.type = type;
		this.mapping = mapping;
		this.columnId = columnId;
		this.columnName = columnName;
		this.order = order;
		this.clubbedSorterMapping = clubbedSorterMapping;
		this.clubbedSorterProperties = clubbedSorterProperties;
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
	
	public Boolean getClubbedSorterMapping() {
		return clubbedSorterMapping;
	}

	public void setClubbedSorterMapping(Boolean clubbedSorterMapping) {
		this.clubbedSorterMapping = clubbedSorterMapping;
	}

	public List<String> getClubbedSorterProperties() {
		return clubbedSorterProperties;
	}

	public void setClubbedSorterProperties(List<String> clubbedSorterProperties) {
		this.clubbedSorterProperties = clubbedSorterProperties;
	}
}
