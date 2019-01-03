package com.service.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.EnquiryConstants;
import com.constants.components.SalesConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Enquiry;
import com.model.components.TutorMapper;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.EnquiryRowMapper;
import com.model.rowmappers.TutorMapperRowMapper;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE)
public class EnquiryService implements EnquiryConstants, SalesConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@Autowired
	private transient DemoService demoService;
	
	@PostConstruct
	public void init() {}
	
	/*public Map<String, Object> displayAllEligibleTutors(final Long enquiryId, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		final List<RegisteredTutor> eligibleTutorList =  applicationDao.findAll("SELECT * FROM REGISTERED_TUTOR R WHERE R.INTERESTED_SUBJECTS LIKE CONCAT('%', (SELECT SUBJECT FROM ENQUIRIES E WHERE E.ENQUIRY_ID = :enquiryId) ,'%') AND R.INTERESTED_STUDENT_GRADES LIKE CONCAT('%', (SELECT GRADE FROM ENQUIRIES E WHERE E.ENQUIRY_ID = :enquiryId) ,'%') AND R.TUTOR_ID NOT IN (SELECT DISTINCT TUTOR_ID FROM TUTOR_MAPPER WHERE ENQUIRY_ID = :enquiryId)", paramsMap, new RegisteredTutorRowMapper());
		final Enquiry enquiryObject = applicationDao.find("SELECT * FROM ENQUIRIES E WHERE E.ENQUIRY_ID = :enquiryId", paramsMap, new EnquiryRowMapper());
		final List<RegisteredTutor> eligibleTutorListWithSubjectGrade = new ArrayList<RegisteredTutor>();
		final List<RegisteredTutor> eligibleTutorListWithSubjectGradeLocation = new ArrayList<RegisteredTutor>();
		final List<RegisteredTutor> eligibleTutorListWithSubjectGradeLocationTeachingType = new ArrayList<RegisteredTutor>();
		for (final RegisteredTutor eligibleTutorObject : eligibleTutorList) {
			Boolean locationMatch = false;
			Boolean teachingTypeMatch = false;
			if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getLocationDetails())) {
				final String[] locations = enquiryObject.getLocationDetails().split(SEMICOLON);
				for (final String location : locations) {
					if (eligibleTutorObject.getComfortableLocations().indexOf(location) != -1) {
						locationMatch = true;
						break;
					}
				}
			}
			if (locationMatch) {
				if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getPreferredTeachingType())) {
					final String[] teachingTypes = enquiryObject.getPreferredTeachingType().split(SEMICOLON);
					for (final String teachingType : teachingTypes) {
						if (eligibleTutorObject.getPreferredTeachingType().indexOf(teachingType) != -1) {
							teachingTypeMatch = true;
							break;
						}
					}
				}
			}
			tutorService.replacePlaceHolderAndIdsFromRegisteredTutorObject(eligibleTutorObject, delimiter);
			tutorService.removeUltraSensitiveInformationFromRegisteredTutorObject(eligibleTutorObject);
			if (teachingTypeMatch) {
				eligibleTutorListWithSubjectGradeLocationTeachingType.add(eligibleTutorObject);
			} else if (locationMatch) {
				eligibleTutorListWithSubjectGradeLocation.add(eligibleTutorObject);
			} else {
				eligibleTutorListWithSubjectGrade.add(eligibleTutorObject);
			}
		}
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put("eligibleTutorListWithSubjectGrade", eligibleTutorListWithSubjectGrade);
		response.put("eligibleTutorListWithSubjectGradeLocation", eligibleTutorListWithSubjectGradeLocation);
		response.put("eligibleTutorListWithSubjectGradeLocationTeachingType", eligibleTutorListWithSubjectGradeLocationTeachingType);
		return response;
	}*/
	
	/**************************************************************************************************************/
	public List<Enquiry> getEnquiryList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-enquiry", "selectEnquiry");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-enquiry", "enquiryMatchStatusFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-enquiry", "enquiryEntryDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_PENDING_ENQUIRIES_LIST : {
				paramsMap.put("matchStatus", MATCH_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_MAPPED_ENQUIRIES_GRID_LIST : {
				paramsMap.put("matchStatus", MATCH_STATUS_TO_BE_MAPPED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_COMPLETED_ENQUIRIES_LIST : {
				paramsMap.put("matchStatus", MATCH_STATUS_COMPLETED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ABORTED_ENQUIRIES_LIST : {
				paramsMap.put("matchStatus", MATCH_STATUS_ABORTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_CUSTOMER_ALL_PENDING_ENQUIRIES_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-enquiry", "enquiryCurrentCustomerAdditionalFilter");
				paramsMap.put("matchStatus", MATCH_STATUS_PENDING);
				paramsMap.put("customerId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class));
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new EnquiryRowMapper());
	}
	
	@Transactional
	public void takeActionOnEnquiry(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String matchStatus = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_TO_BE_MAPPED : {
				matchStatus = MATCH_STATUS_TO_BE_MAPPED;
				break;
			}
			case BUTTON_ACTION_ABORTED : {
				matchStatus = MATCH_STATUS_ABORTED;
				break;
			}
			case BUTTON_ACTION_PENDING : {
				matchStatus = MATCH_STATUS_PENDING;
				break;
			}
		}
		final List<Enquiry> paramObjectList = new LinkedList<Enquiry>();
		for (final String enquiryId : idList) {
			final Enquiry enquiry = new Enquiry();
			enquiry.setWhoActed(activeUser.getUserId());
			enquiry.setAdminRemarks(comments);
			enquiry.setMatchStatus(matchStatus);
			enquiry.setLastActionDateMillis(currentTimestamp.getTime());
			enquiry.setEnquiryId(Long.valueOf(enquiryId));
			paramObjectList.add(enquiry);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-enquiry", "updateEnquiryMatchStatus", paramObjectList);
	}
	
	@Transactional
	public void feedEnquiryList(final List<Enquiry> enquiryList) throws Exception {
		applicationDao.executeBatchUpdateWithQueryMapper("sales-enquiry", "insertEnquiry", enquiryList);
	}
	
	@Transactional
	public void updateEnquiryRecord(final Enquiry enquiryObject, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE ENQUIRY SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE ENQUIRY_ID = :enquiryId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "subject" : {
						updateAttributesQuery.add("SUBJECT = :subject");
						paramsMap.put("subject", enquiryObject.getSubject());
						break;
					}
					case "grade" : {
						updateAttributesQuery.add("GRADE = :grade");
						paramsMap.put("grade", enquiryObject.getGrade());
						break;
					}
					case "quotedClientRate" : {
						updateAttributesQuery.add("QUOTED_CLIENT_RATE = :quotedClientRate");
						paramsMap.put("quotedClientRate", enquiryObject.getQuotedClientRate());
						break;
					}
					case "negotiatedRateWithClient" : {
						updateAttributesQuery.add("NEGOTIATED_RATE_WITH_CLIENT = :negotiatedRateWithClient");
						paramsMap.put("negotiatedRateWithClient", enquiryObject.getNegotiatedRateWithClient());
						break;
					}
					case "clientNegotiationRemarks" : {
						updateAttributesQuery.add("CLIENT_NEGOTIATION_REMARKS = :clientNegotiationRemarks");
						paramsMap.put("clientNegotiationRemarks", enquiryObject.getClientNegotiationRemarks());
						break;
					}
					case "locationDetails" : {
						updateAttributesQuery.add("LOCATION_DETAILS = :locationDetails");
						paramsMap.put("locationDetails", enquiryObject.getLocationDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", enquiryObject.getAddressDetails());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", enquiryObject.getAdditionalDetails());
						break;
					}
					case "preferredTeachingType" : {
						updateAttributesQuery.add("PREFERRED_TEACHING_TYPE = :preferredTeachingType");
						paramsMap.put("preferredTeachingType", enquiryObject.getPreferredTeachingType());
						break;
					}
				}
			}
		}
		paramsMap.put("enquiryId", enquiryObject.getEnquiryId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("LAST_ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<Enquiry> getCompletedAndAbortedEnquiryList(final Boolean limitRecords, final Integer limit) throws DataAccessException, InstantiationException, IllegalAccessException {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, Enquiry.class);
		} else {
			gridComponent = new GridComponent(Enquiry.class);
		}
		final String baseQuery = queryMapperService.getQuerySQL("sales-enquiry", "selectEnquiry");
		final String existingFilterQueryString = queryMapperService.getQuerySQL("sales-enquiry", "enquiryMultipleMatchStatusFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-enquiry", "enquiryEntryDateSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("matchStatusList", Arrays.asList(new String[] {EnquiryConstants.MATCH_STATUS_COMPLETED, EnquiryConstants.MATCH_STATUS_ABORTED}));
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new EnquiryRowMapper());
	}
	
	public List<TutorMapper> getAllMappedTutorsList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-tutor-mapper", "selectTutorMapper");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-tutor-mapper", "tutorMapperMappingStatusFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-tutor-mapper", "tutorMapperEntryDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_PENDING_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", MAPPING_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DEMO_READY_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", MAPPING_STATUS_DEMO_READY);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DEMO_SCHEDULED_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", MAPPING_STATUS_DEMO_SCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-tutor-mapper", "tutorMapperCurrentEnquiryAllMappedTutors");
				paramsMap.put("enquiryId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "enquiryId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_TUTOR_MAPPING_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-tutor-mapper", "tutorMapperCurrentTutorMappingFilter");
				paramsMap.put("tutorId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_CUSTOMER_MAPPING_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-tutor-mapper", "tutorMapperCurrentCustomerMappingFilter");
				paramsMap.put("customerId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class));
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new TutorMapperRowMapper());
	}
	
	@Transactional
	public void mapRegisteredTutors(final Long enquiryId, final List<String> idList, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<TutorMapper> tutorMapperList = new LinkedList<TutorMapper>();
		for (final String tutorId : idList) {
			final TutorMapper tutorMapperObject = new TutorMapper();
			tutorMapperObject.setEnquiryId(enquiryId);
			tutorMapperObject.setTutorId(Long.valueOf(tutorId));
			tutorMapperObject.setMappingStatus(MAPPING_STATUS_PENDING);
			tutorMapperObject.setWhoActed(activeUser.getUserId());
			tutorMapperObject.setIsDemoScheduled(NO);
			tutorMapperObject.setAdminActionDateMillis(currentTimestamp.getTime());
			tutorMapperObject.setEntryDateMillis(currentTimestamp.getTime());
			tutorMapperList.add(tutorMapperObject);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-tutor-mapper", "insertTutorMapper", tutorMapperList);
	}
	
	@Transactional
	public void unmapRegisteredTutors(final List<String> idList, final User activeUser) throws Exception {
		final List<TutorMapper> tutorMapperList = new LinkedList<TutorMapper>();
		for (final String tutorMapperId : idList) {
			final TutorMapper tutorMapperObject = new TutorMapper();
			tutorMapperObject.setTutorMapperId(Long.valueOf(tutorMapperId));
			tutorMapperList.add(tutorMapperObject);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-tutor-mapper", "deleteTutorMapper", tutorMapperList);
	}
	
	@Transactional
	public void takeActionOnTutorMapper(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String mappingStatus = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_DEMO_READY : {
				mappingStatus = MAPPING_STATUS_DEMO_READY;
				break;
			}
			case BUTTON_ACTION_PENDING : {
				mappingStatus = MAPPING_STATUS_PENDING;
				break;
			}
		}
		final List<TutorMapper> paramObjectList = new LinkedList<TutorMapper>();
		for (final String tutorMapperId : idList) {
			final TutorMapper tutorMapper = new TutorMapper();
			tutorMapper.setWhoActed(activeUser.getUserId());
			tutorMapper.setAdminActionRemarks(comments);
			tutorMapper.setMappingStatus(mappingStatus);
			tutorMapper.setAdminActionDateMillis(currentTimestamp.getTime());
			tutorMapper.setTutorMapperId(Long.valueOf(tutorMapperId));
			paramObjectList.add(tutorMapper);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-tutor-mapper", "updateTutorMapperMappingStatus", paramObjectList);
	}
	
	@Transactional
	public void updateTutorMapperRecord(final TutorMapper tutorMapperObject, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE TUTOR_MAPPER SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE TUTOR_MAPPER_ID = :tutorMapperId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		Boolean isTutorContacted = false;
		Boolean isClientContacted = false;
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "quotedTutorRate" : {
						updateAttributesQuery.add("QUOTED_TUTOR_RATE = :quotedTutorRate");
						paramsMap.put("quotedTutorRate", tutorMapperObject.getQuotedTutorRate());
						break;
					}
					case "negotiatedRateWithTutor" : {
						updateAttributesQuery.add("NEGOTIATED_RATE_WITH_TUTOR = :negotiatedRateWithTutor");
						paramsMap.put("negotiatedRateWithTutor", tutorMapperObject.getNegotiatedRateWithTutor());
						break;
					}
					case "tutorNegotiationRemarks" : {
						updateAttributesQuery.add("TUTOR_NEGOTIATION_REMARKS = :tutorNegotiationRemarks");
						paramsMap.put("tutorNegotiationRemarks", tutorMapperObject.getTutorNegotiationRemarks());
						break;
					}
					case "isTutorAgreed" : {
						updateAttributesQuery.add("IS_TUTOR_AGREED = :isTutorAgreed");
						paramsMap.put("isTutorAgreed", tutorMapperObject.getIsTutorAgreed());
						isTutorContacted = true;
						break;
					}
					case "tutorResponse" : {
						updateAttributesQuery.add("TUTOR_RESPONSE = :tutorResponse");
						paramsMap.put("tutorResponse", tutorMapperObject.getTutorResponse());
						break;
					}
					case "adminRemarksForTutor" : {
						updateAttributesQuery.add("ADMIN_REMARKS_FOR_TUTOR = :adminRemarksForTutor");
						paramsMap.put("adminRemarksForTutor", tutorMapperObject.getAdminRemarksForTutor());
						break;
					}
					case "isTutorRejectionValid" : {
						updateAttributesQuery.add("IS_TUTOR_REJECTION_VALID = :isTutorRejectionValid");
						paramsMap.put("isTutorRejectionValid", tutorMapperObject.getIsTutorRejectionValid());
						break;
					}
					case "adminTutorRejectionValidityResponse" : {
						updateAttributesQuery.add("ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE = :adminTutorRejectionValidityResponse");
						paramsMap.put("adminTutorRejectionValidityResponse", tutorMapperObject.getAdminTutorRejectionValidityResponse());
						break;
					}
					case "isClientAgreed" : {
						updateAttributesQuery.add("IS_CLIENT_AGREED = :isClientAgreed");
						paramsMap.put("isClientAgreed", tutorMapperObject.getIsClientAgreed());
						isClientContacted = true;
						break;
					}
					case "clientResponse" : {
						updateAttributesQuery.add("CLIENT_RESPONSE = :clientResponse");
						paramsMap.put("clientResponse", tutorMapperObject.getClientResponse());
						break;
					}
					case "adminRemarksForClient" : {
						updateAttributesQuery.add("ADMIN_REMARKS_FOR_CLIENT = :adminRemarksForClient");
						paramsMap.put("adminRemarksForClient", tutorMapperObject.getAdminRemarksForClient());
						break;
					}
					case "isClientRejectionValid" : {
						updateAttributesQuery.add("IS_CLIENT_REJECTION_VALID = :isClientRejectionValid");
						paramsMap.put("isClientRejectionValid", tutorMapperObject.getIsClientRejectionValid());
						break;
					}
					case "adminClientRejectionValidityResponse" : {
						updateAttributesQuery.add("ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE = :adminClientRejectionValidityResponse");
						paramsMap.put("adminClientRejectionValidityResponse", tutorMapperObject.getAdminClientRejectionValidityResponse());
						break;
					}
					case "adminActionRemarks" : {
						updateAttributesQuery.add("ADMIN_ACTION_REMARKS = :adminActionRemarks");
						paramsMap.put("adminActionRemarks", tutorMapperObject.getAdminActionRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("tutorMapperId", tutorMapperObject.getTutorMapperId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			if (isTutorContacted) {
				updateAttributesQuery.add("IS_TUTOR_CONTACTED = 'Y'");
				updateAttributesQuery.add("TUTOR_CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			}
			if (isClientContacted) {
				updateAttributesQuery.add("IS_CLIENT_CONTACTED = 'Y'");
				updateAttributesQuery.add("CLIENT_CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			}
			updateAttributesQuery.add("ADMIN_ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	@Transactional
	public void scheduleDemo(final TutorMapper tutorMapperObject, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		tutorMapperObject.setIsDemoScheduled(YES);
		tutorMapperObject.setMappingStatus(EnquiryConstants.MAPPING_STATUS_DEMO_SCHEDULED);
		tutorMapperObject.setWhoActed(activeUser.getUserId());
		tutorMapperObject.setAdminActionDateMillis(currentTimestamp.getTime());
		applicationDao.executeUpdateWithQueryMapper("sales-tutor-mapper", "updateTutorMapperForDemoScheduled", tutorMapperObject);
		demoService.insertScheduledDemo(tutorMapperObject.getTutorMapperId(), tutorMapperObject.getDemoDateAndTimeMillis(), activeUser, true, false, null, null);
	}
}
