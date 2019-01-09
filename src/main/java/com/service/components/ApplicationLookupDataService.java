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
import com.utils.ValidationUtils;

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
	private List<SelectLookup> docuemtTypeLookupData;
	private List<SelectLookup> emailTemplateLookupData;
	private List<SelectLookup> publicApplicationStatusLookupData;
	private List<SelectLookup> queryStatusLookupData;
	private List<SelectLookup> complaintStatusLookupData;
	private List<SelectLookup> userLookupData;
	private List<SelectLookup> enquiryMatchStatusLookupData;
	private List<SelectLookup> tutorMappingStatusLookupData;
	private List<SelectLookup> demoStatusLookupData;
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() throws Exception {
		refreshAllSelectLookups();
	}
	
	public void refreshAllSelectLookups() throws Exception {
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
		this.docuemtTypeLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_DOCUMENT_TYPE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.emailTemplateLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.publicApplicationStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_PUBLIC_APPLICATION_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.queryStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_QUERY_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.complaintStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_COMPLAINT_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.userLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_USER_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.enquiryMatchStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_ENQUIRY_MATCH_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.tutorMappingStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_TUTOR_MAPPING_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
		this.demoStatusLookupData = applicationDao.findAllWithoutParams(QueryUtils.createQueryWithFilterAndSorter(baseQuery.replaceAll(SELECT_LOOKUP_TABLE_NAME, SELECT_LOOKUP_TABLE_DEMO_STATUS_LOOKUP), existingFilterQueryString, existingSorterQueryString, null, null), new SelectLookupRowMapper());
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
			case SELECT_LOOKUP_TABLE_DOCUMENT_TYPE_LOOKUP : return this.docuemtTypeLookupData;
			case SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP : return this.emailTemplateLookupData;
			case SELECT_LOOKUP_TABLE_PUBLIC_APPLICATION_STATUS_LOOKUP : return this.publicApplicationStatusLookupData;
			case SELECT_LOOKUP_TABLE_QUERY_STATUS_LOOKUP : return this.queryStatusLookupData;
			case SELECT_LOOKUP_TABLE_COMPLAINT_STATUS_LOOKUP : return this.complaintStatusLookupData;
			case SELECT_LOOKUP_TABLE_USER_LOOKUP : return this.userLookupData;
			case SELECT_LOOKUP_TABLE_ENQUIRY_MATCH_STATUS_LOOKUP : return this.enquiryMatchStatusLookupData;
			case SELECT_LOOKUP_TABLE_TUTOR_MAPPING_STATUS_LOOKUP : return this.tutorMappingStatusLookupData;
			case SELECT_LOOKUP_TABLE_DEMO_STATUS_LOOKUP : return this.demoStatusLookupData;
		}
		return null;
	}
	
	public SelectLookup getSelectLookupItem(final String selectLookUpTable, final String value) {
		switch(selectLookUpTable) {
			case SELECT_LOOKUP_TABLE_YES_NO_LOOKUP : return getSelectLookupItem(this.yesNoLookupData, value);
			case SELECT_LOOKUP_TABLE_GENDER_LOOKUP : return getSelectLookupItem(this.genderLookupData, value);
			case SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP : return getSelectLookupItem(this.qualificationLookupData, value);
			case SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP : return getSelectLookupItem(this.professionLookupData, value);
			case SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP : return getSelectLookupItem(this.transportModeLookupData, value);
			case SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP : return getSelectLookupItem(this.locationsLookupData, value);
			case SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP : return getSelectLookupItem(this.preferredTimeLookupData, value);
			case SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP : return getSelectLookupItem(this.studentGradeLookupData, value);
			case SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP : return getSelectLookupItem(this.subjectsLookupData, value);
			case SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP : return getSelectLookupItem(this.referenceLookupData, value);
			case SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP : return getSelectLookupItem(this.preferredTeachingTypeLookupData, value);
			case SELECT_LOOKUP_TABLE_DOCUMENT_TYPE_LOOKUP : return getSelectLookupItem(this.docuemtTypeLookupData, value);
			case SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP : return getSelectLookupItem(this.emailTemplateLookupData, value);
			case SELECT_LOOKUP_TABLE_PUBLIC_APPLICATION_STATUS_LOOKUP : return getSelectLookupItem(this.publicApplicationStatusLookupData, value);
			case SELECT_LOOKUP_TABLE_QUERY_STATUS_LOOKUP : return getSelectLookupItem(this.queryStatusLookupData, value);
			case SELECT_LOOKUP_TABLE_COMPLAINT_STATUS_LOOKUP : return getSelectLookupItem(this.complaintStatusLookupData, value);
			case SELECT_LOOKUP_TABLE_USER_LOOKUP : return getSelectLookupItem(this.userLookupData, value);
			case SELECT_LOOKUP_TABLE_ENQUIRY_MATCH_STATUS_LOOKUP : return getSelectLookupItem(this.enquiryMatchStatusLookupData, value);
			case SELECT_LOOKUP_TABLE_TUTOR_MAPPING_STATUS_LOOKUP : return getSelectLookupItem(this.tutorMappingStatusLookupData, value);
			case SELECT_LOOKUP_TABLE_DEMO_STATUS_LOOKUP : return getSelectLookupItem(this.demoStatusLookupData, value);
		}
		return null;
	}
	
	private SelectLookup getSelectLookupItem(final List<SelectLookup> selectLookupList, final String value) {
		if (ValidationUtils.checkStringAvailability(value)) {
			if (selectLookupList.contains(new SelectLookup(value))) {
				return selectLookupList.get(selectLookupList.indexOf(new SelectLookup(value)));
			}
		}
		return null;
	}
	
	public List<SelectLookup> getSelectLookupItemList(final String selectLookUpTable, final String multivalue, final String delimiter) {
		switch(selectLookUpTable) {
			case SELECT_LOOKUP_TABLE_YES_NO_LOOKUP : return getSelectLookupItemList(this.yesNoLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_GENDER_LOOKUP : return getSelectLookupItemList(this.genderLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP : return getSelectLookupItemList(this.qualificationLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP : return getSelectLookupItemList(this.professionLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP : return getSelectLookupItemList(this.transportModeLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP : return getSelectLookupItemList(this.locationsLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP : return getSelectLookupItemList(this.preferredTimeLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP : return getSelectLookupItemList(this.studentGradeLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP : return getSelectLookupItemList(this.subjectsLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP : return getSelectLookupItemList(this.referenceLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP : return getSelectLookupItemList(this.preferredTeachingTypeLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_DOCUMENT_TYPE_LOOKUP : return getSelectLookupItemList(this.docuemtTypeLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_EMAIL_TEMPLATE_LOOKUP : return getSelectLookupItemList(this.emailTemplateLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_PUBLIC_APPLICATION_STATUS_LOOKUP : return getSelectLookupItemList(this.publicApplicationStatusLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_QUERY_STATUS_LOOKUP : return getSelectLookupItemList(this.queryStatusLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_COMPLAINT_STATUS_LOOKUP : return getSelectLookupItemList(this.complaintStatusLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_USER_LOOKUP : return getSelectLookupItemList(this.userLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_ENQUIRY_MATCH_STATUS_LOOKUP : return getSelectLookupItemList(this.enquiryMatchStatusLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_TUTOR_MAPPING_STATUS_LOOKUP : return getSelectLookupItemList(this.tutorMappingStatusLookupData, multivalue, delimiter);
			case SELECT_LOOKUP_TABLE_DEMO_STATUS_LOOKUP : return getSelectLookupItemList(this.demoStatusLookupData, multivalue, delimiter);
		}
		return null;
	}
	
	private List<SelectLookup> getSelectLookupItemList(final List<SelectLookup> selectLookupList, final String multivalue, final String delimiter) {
		final List<SelectLookup> selectLookupItemList = new LinkedList<SelectLookup>();
		if (ValidationUtils.checkStringAvailability(multivalue) && ValidationUtils.checkObjectAvailability(delimiter)) {
			for (final String value : multivalue.split(delimiter)) {
				if (selectLookupList.contains(new SelectLookup(value.trim()))) {
					selectLookupItemList.add(selectLookupList.get(selectLookupList.indexOf(new SelectLookup(value.trim()))));
				}
			}
		}
		if (ValidationUtils.checkNonEmptyList(selectLookupItemList)) {
			return selectLookupItemList;
		}
		return null;
	}
}
