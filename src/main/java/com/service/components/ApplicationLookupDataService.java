package com.service.components;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.components.commons.SelectLookup;
import com.model.rowmappers.SelectLookupRowMapper;
import com.utils.QueryUtils;

@Service(BeanConstants.BEAN_NAME_APPLICATION_LOOKUP_DATA_SERVICE)
public class ApplicationLookupDataService implements SelectLookupConstants {
	
	private List<SelectLookup> yesNoLookupData;
	private List<SelectLookup> genderLookupData;
	private List<SelectLookup> qualificationLookupData;
	private List<SelectLookup> professionLookupData;
	private List<SelectLookup> transportModeLookupData;
	private List<SelectLookup> locationsLookupData;
	private List<SelectLookup> preferredTimeLookupData;
	private List<SelectLookup> studentGradeLookupData;
	private List<SelectLookup> subjectsLookupData;
	private List<SelectLookup> referenceLookupData;
	private List<SelectLookup> preferredTeachingTypeLookupData;
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {
		final String baseQuery = "SELECT LT.* FROM SELECT_LOOKUP_TABLE_NAME LT";
		final String existingFilterQueryString = "WHERE LT.HIDDEN IS NULL";
		final String existingSorterQueryString = "ORDER BY LT.ORDER_OF_CATEGORY, LT.ORDER_IN_CATEGORY";
		this.yesNoLookupData = new LinkedList<SelectLookup>();
		yesNoLookupData.add(new SelectLookup(YES, YES_TEXT));
		yesNoLookupData.add(new SelectLookup(NO, NO_TEXT));
		this.genderLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_GENDER_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.qualificationLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.professionLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.transportModeLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.locationsLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.preferredTimeLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.studentGradeLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.subjectsLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.referenceLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.preferredTeachingTypeLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
	}
	
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		switch(selectLookUpTable) {
			case SELECT_LOOKUP_TABLE_YES_NO_LOOKUP : return this.yesNoLookupData;
			case SELECT_LOOKUP_TABLE_GENDER_LOOKUP : return this.genderLookupData;
			case SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP : return this.qualificationLookupData;
			case SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP : return this.professionLookupData;
			case SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP : return this.transportModeLookupData;
			case SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP : return this.locationsLookupData;
			case SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP : return this.preferredTimeLookupData;
			case SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP : return this.studentGradeLookupData;
			case SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP : return this.subjectsLookupData;
			case SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP : return this.referenceLookupData;
			case SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP : return this.preferredTeachingTypeLookupData;
		}
		return null;
	}
}
