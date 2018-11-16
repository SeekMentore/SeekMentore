package com.model.gridcomponent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Filter implements Serializable {

	private static final long serialVersionUID = 4340143574772981352L;
	
	private String id;
	private String type;
	private String mapping;
	private String columnId;
	private Integer lessThan;
	private Integer equalTo;
	private Integer greaterThan;
	private String stringValue;
	private Boolean textCaseSensitiveSearch;
	private Date beforeDate;
	private Date onDate;
	private Date afterDate;
	private Long beforeDateMillis;
	private Long onDateMillis;
	private Long afterDateMillis;
	private List<String> listValue;

	public Filter() {}
	
	public Filter(String id, String type, String mapping, String columnId) {
		this.id = id;
		this.type = type;
		this.mapping = mapping;
		this.columnId = columnId;
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

	public Integer getLessThan() {
		return lessThan;
	}

	public void setLessThan(Integer lessThan) {
		this.lessThan = lessThan;
	}

	public Integer getEqualTo() {
		return equalTo;
	}

	public void setEqualTo(Integer equalTo) {
		this.equalTo = equalTo;
	}

	public Integer getGreaterThan() {
		return greaterThan;
	}

	public void setGreaterThan(Integer greaterThan) {
		this.greaterThan = greaterThan;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Date getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(Date beforeDate) {
		this.beforeDate = beforeDate;
	}

	public Date getOnDate() {
		return onDate;
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}

	public Date getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(Date afterDate) {
		this.afterDate = afterDate;
	}

	public Long getBeforeDateMillis() {
		return beforeDateMillis;
	}

	public void setBeforeDateMillis(Long beforeDateMillis) {
		this.beforeDateMillis = beforeDateMillis;
	}

	public Long getOnDateMillis() {
		return onDateMillis;
	}

	public void setOnDateMillis(Long onDateMillis) {
		this.onDateMillis = onDateMillis;
	}

	public Long getAfterDateMillis() {
		return afterDateMillis;
	}

	public void setAfterDateMillis(Long afterDateMillis) {
		this.afterDateMillis = afterDateMillis;
	}

	public List<String> getListValue() {
		return listValue;
	}

	public void setListValue(List<String> listValue) {
		this.listValue = listValue;
	}
	
	public void addListValue(String value) {
		this.listValue.add(value);
	}

	public Boolean getTextCaseSensitiveSearch() {
		return textCaseSensitiveSearch;
	}

	public void setTextCaseSensitiveSearch(Boolean textCaseSensitiveSearch) {
		this.textCaseSensitiveSearch = textCaseSensitiveSearch;
	}
	
	@Override
	public String toString() {
		String output = this.id + " " + this.type + " " + this.mapping + " " + this.columnId;
		switch (type) {
			case "string" : {
				output += " " + this.stringValue + " " + this.textCaseSensitiveSearch;
				break;
			}
			case "date" : {
				output += " " + this.beforeDateMillis + " " + this.beforeDate + " " + this.onDateMillis + " " + this.onDate + " " + this.afterDateMillis + " " + this.afterDate;
				break;
			}
			case "number" : {
				output += " " + this.lessThan + " " + this.equalTo + " " + this.greaterThan;
				break;
			}
			case "list" : {
				for (String value : this.listValue) {
					output += " " + value;
				}
				break;
			}
		}
		return  output;
	}
}
