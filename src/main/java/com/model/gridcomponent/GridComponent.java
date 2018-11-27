package com.model.gridcomponent;

import java.io.Serializable;
import java.util.List;

import com.utils.GridComponentUtils;

public class GridComponent implements Serializable {

	private static final long serialVersionUID = -7525245577014493220L;
	
	private Integer start;
	private Integer limit;
	private Integer end;
	private Boolean pagingAvailable;
	private String otherParams;	
	private String filters;	
	private String sorters;	
	private List<Sorter> sorterList;
	private List<Filter> filterList;
	
	public GridComponent() {}
	
	public GridComponent(
		final Integer start,
		final Integer limit,
		final String otherParams,
		final String filters,
		final String sorters
	) {
		if (null != start && start > 0 && null != limit && limit > 0) {
			this.start = start;
			this.limit = limit;
			this.end = this.start + this.limit - 1;
			this.pagingAvailable = true;
		} else {
			this.start = -1;
			this.limit = -1;
			this.end = -1;
			this.pagingAvailable = false;
		}
		this.otherParams = otherParams;
		this.filters = filters;
		this.sorters = sorters;
		this.filterList = GridComponentUtils.createFilterListFromFilterJSON(this.filters);
		this.sorterList = GridComponentUtils.createSorterListFromSorterJSON(this.sorters);
	}
	
	public GridComponent(
		final String start,
		final String limit,
		final String otherParams,
		final String filters,
		final String sorters
	) {
		try {
			this.start = Integer.parseInt(start);
			this.limit = Integer.parseInt(limit);
			if (null != this.start && this.start > 0 && null != this.limit && this.limit > 0) {
				this.end = this.start + this.limit - 1;
				this.pagingAvailable = true;
			} else {
				this.start = -1;
				this.limit = -1;
				this.end = -1;
				this.pagingAvailable = false;
			}
		} catch (NumberFormatException e) {
			this.start = -1;
			this.limit = -1;
			this.end = -1;
			this.pagingAvailable = false;
		}
		this.otherParams = otherParams;
		this.filters = filters;
		this.sorters = sorters;
		this.filterList = GridComponentUtils.createFilterListFromFilterJSON(this.filters);
		this.sorterList = GridComponentUtils.createSorterListFromSorterJSON(this.sorters);
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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Boolean getPagingAvailable() {
		return pagingAvailable;
	}

	public void setPagingAvailable(Boolean pagingAvailable) {
		this.pagingAvailable = pagingAvailable;
	}
}
