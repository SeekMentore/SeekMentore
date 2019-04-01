package com.model.gridcomponent;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonObject;

import com.constants.GridComponentConstants;
import com.model.GridComponentObject;
import com.utils.GridComponentUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;

public class GridComponent implements Serializable, GridComponentConstants {

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
	private StandardExtraParams standardExtraParams;
	private JsonObject otherParamsAsJSONObject;
	
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
		this.standardExtraParams = new StandardExtraParams(this.otherParamsAsJSONObject);
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
		this.standardExtraParams = new StandardExtraParams(this.otherParamsAsJSONObject);
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
		this.standardExtraParams = new StandardExtraParams(this.otherParamsAsJSONObject);
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
		this.standardExtraParams = new StandardExtraParams(this.otherParamsAsJSONObject);
	}
	
	public String getOtherParams() {
		return otherParams;
	}
	
	public List<Sorter> getSorterList() {
		return sorterList;
	}
	
	public List<Filter> getFilterList() {
		return filterList;
	}
	
	public String getFilters() {
		return filters;
	}

	public String getSorters() {
		return sorters;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getEnd() {
		return end;
	}

	public Boolean getPagingAvailable() {
		return pagingAvailable;
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

	public JsonObject getOtherParamsAsJSONObject() {
		return otherParamsAsJSONObject;
	}
	
	public StandardExtraParams getStandardExtraParams() {
		return standardExtraParams;
	}

	public void addStringFilterToFilterList (
			final String mapping,
			final String stringValue, 
			final Boolean textCaseSensitiveSearch, 
			final Boolean clubbedFilterMapping, 
			final List<String> clubbedFilterProperties) {
		if (!ValidationUtils.checkObjectAvailability(filterList)) {
			this.filterList = new LinkedList<Filter>();
		}
		this.filterList.add(GridComponentUtils.getFilter(
													DUMMY_COLUMN_FILTER, 
													COLUMN_FILTER_MAPPING_STRING,
													mapping, 
													DUMMY_COLUMN, 
													false, 
													clubbedFilterMapping, 
													clubbedFilterProperties,
													stringValue,
													textCaseSensitiveSearch,
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													null));
	}
	
	public void addDateFilterToFilterList (
			final String mapping,
			final Long beforeDateMillis,
			final Long onDateMillis,
			final Long afterDateMillis,
			final Long localTimezoneOffsetInMilliseconds,
			final Boolean clubbedFilterMapping, 
			final List<String> clubbedFilterProperties) {
		if (!ValidationUtils.checkObjectAvailability(filterList)) {
			this.filterList = new LinkedList<Filter>();
		}
		this.filterList.add(GridComponentUtils.getFilter(
													DUMMY_COLUMN_FILTER, 
													COLUMN_FILTER_MAPPING_DATE,
													mapping, 
													DUMMY_COLUMN, 
													false, 
													clubbedFilterMapping, 
													clubbedFilterProperties,
													null,
													null,
													beforeDateMillis,
													onDateMillis,
													afterDateMillis,
													localTimezoneOffsetInMilliseconds,
													null,
													null,
													null,
													null));
	}
	
	public void addNumberFilterToFilterList (
			final String mapping,
			final Integer lessThan,
			final Integer equalTo,
			final Integer greaterThan,
			final Boolean clubbedFilterMapping, 
			final List<String> clubbedFilterProperties) {
		if (!ValidationUtils.checkObjectAvailability(filterList)) {
			this.filterList = new LinkedList<Filter>();
		}
		this.filterList.add(GridComponentUtils.getFilter(
													DUMMY_COLUMN_FILTER, 
													COLUMN_FILTER_MAPPING_NUMBER,
													mapping, 
													DUMMY_COLUMN, 
													false, 
													clubbedFilterMapping, 
													clubbedFilterProperties,
													null,
													null,
													null,
													null,
													null,
													null,
													lessThan,
													equalTo,
													greaterThan,
													null));
	}
	
	public void addListFilterToFilterList (
			final String mapping,
			final List<String> listValue,
			final Boolean multiList,
			final Boolean clubbedFilterMapping, 
			final List<String> clubbedFilterProperties) {
		if (!ValidationUtils.checkObjectAvailability(filterList)) {
			this.filterList = new LinkedList<Filter>();
		}
		this.filterList.add(GridComponentUtils.getFilter(
													DUMMY_COLUMN_FILTER, 
													COLUMN_FILTER_MAPPING_LIST,
													mapping, 
													DUMMY_COLUMN, 
													multiList, 
													clubbedFilterMapping, 
													clubbedFilterProperties,
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													null,
													listValue));
	}
}
