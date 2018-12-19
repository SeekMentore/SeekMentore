package com.model.gridcomponent;

import java.io.Serializable;
import java.util.List;

import javax.json.JsonObject;

import com.model.GridComponentObject;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;

public class GridComponent implements Serializable {

	private static final long serialVersionUID = -7525245577014493220L;
	
	private Integer start;
	private Integer limit;
	private Integer end;
	private Boolean pagingAvailable;
	private String otherParams;	
	private String filters;	
	private String sorters;	
	private Class<? extends GridComponentObject> gridComponentObjectClass;
	private List<Sorter> sorterList;
	private List<Filter> filterList;
	private String additionalFilterQueryString;
	private String additionalSorterQueryString;
	private JsonObject otherParamsAsJSONObject;
	
	public GridComponent() {}
	
	public GridComponent(
		final Integer start,
		final Integer limit,
		final String otherParams,
		final String filters,
		final String sorters,
		final Class<? extends GridComponentObject> gridComponentObjectClass
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
		this.gridComponentObjectClass = gridComponentObjectClass;
		this.otherParamsAsJSONObject = JSONUtils.getJSONObjectFromString(otherParams);
	}
	
	public GridComponent(
		final String start,
		final String limit,
		final String otherParams,
		final String filters,
		final String sorters,
		final Class<? extends GridComponentObject> gridComponentObjectClass
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
		this.gridComponentObjectClass = gridComponentObjectClass;
		this.otherParamsAsJSONObject = JSONUtils.getJSONObjectFromString(otherParams);
	}
	
	public GridComponent(
			final Integer start,
			final Integer limit,
			final Class<? extends GridComponentObject> gridComponentObjectClass
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
		this.otherParams = null;
		this.filters = null;
		this.sorters = null;
		this.filterList = GridComponentUtils.createFilterListFromFilterJSON(this.filters);
		this.sorterList = GridComponentUtils.createSorterListFromSorterJSON(this.sorters);
		this.gridComponentObjectClass = gridComponentObjectClass;
		this.otherParamsAsJSONObject = JSONUtils.getJSONObjectFromString(this.otherParams);
	}
	
	public GridComponent(final Class<? extends GridComponentObject> gridComponentObjectClass) {
		this.start = -1;
		this.limit = -1;
		this.end = -1;
		this.pagingAvailable = false;
		this.otherParams = null;
		this.filters = null;
		this.sorters = null;
		this.filterList = GridComponentUtils.createFilterListFromFilterJSON(this.filters);
		this.sorterList = GridComponentUtils.createSorterListFromSorterJSON(this.sorters);
		this.gridComponentObjectClass = gridComponentObjectClass;
		this.otherParamsAsJSONObject = JSONUtils.getJSONObjectFromString(this.otherParams);
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

	public String getAdditionalFilterQueryString() {
		return additionalFilterQueryString;
	}

	public void setAdditionalFilterQueryString(String additionalFilterQueryString) {
		this.additionalFilterQueryString = additionalFilterQueryString;
	}

	public String getAdditionalSorterQueryString() {
		return additionalSorterQueryString;
	}

	public void setAdditionalSorterQueryString(String additionalSorterQueryString) {
		this.additionalSorterQueryString = additionalSorterQueryString;
	}

	public Class<? extends GridComponentObject> getGridComponentObjectClass() {
		return gridComponentObjectClass;
	}

	public void setGridComponentObjectClass(Class<? extends GridComponentObject> gridComponentObjectClass) {
		this.gridComponentObjectClass = gridComponentObjectClass;
	}

	public JsonObject getOtherParamsAsJSONObject() {
		return otherParamsAsJSONObject;
	}

	public void setOtherParamsAsJSONObject(JsonObject otherParamsAsJSONObject) {
		this.otherParamsAsJSONObject = otherParamsAsJSONObject;
	}
}
