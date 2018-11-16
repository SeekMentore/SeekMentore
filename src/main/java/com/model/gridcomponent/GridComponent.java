package com.model.gridcomponent;

import java.io.Serializable;
import java.util.List;

public class GridComponent implements Serializable {

	private static final long serialVersionUID = -7525245577014493220L;
	
	private int start;
	private int limit;
	private String otherParams;	
	private String filters;	
	private String sorters;	
	private List<Sorter> sorterList;
	private List<Filter> filterList;
	
	public GridComponent() {}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getOtherParams() {
		return otherParams;
	}
	public void setOtherParams(String otherParams) {
		this.otherParams = otherParams;
	}
	public List<Sorter> getSorterList() {
		return sorterList;
	}
	public void setSorterList(List<Sorter> sorterList) {
		this.sorterList = sorterList;
	}
	public List<Filter> getFilterList() {
		return filterList;
	}
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getSorters() {
		return sorters;
	}

	public void setSorters(String sorters) {
		this.sorters = sorters;
	}
}
