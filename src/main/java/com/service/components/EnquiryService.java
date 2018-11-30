package com.service.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.TutorMapper;
import com.model.components.commons.SelectLookup;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.EnquiryRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.model.rowmappers.TutorMapperRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.DateUtils;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE)
public class EnquiryService implements EnquiryConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private transient TutorService tutorService;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void addEnquiry(final Enquiry enquiryObject) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", enquiryObject.getCustomerId());
		paramsMap.put("subject", enquiryObject.getSubject());
		paramsMap.put("grade", enquiryObject.getGrade());
		paramsMap.put("matchStatus", enquiryObject.getMatchStatus());
		paramsMap.put("locationDetails", enquiryObject.getLocationDetails());
		paramsMap.put("addressDetails", enquiryObject.getAddressDetails());
		paramsMap.put("additionalDetails", enquiryObject.getAdditionalDetails());
		applicationDao.executeUpdate("INSERT INTO ENQUIRIES(CUSTOMER_ID, SUBJECT, GRADE, MATCH_STATUS, LOCATION_DETAILS, ADDRESS_DETAILS, ADDITIONAL_DETAILS) VALUES(:customerId, :subject, :grade, :matchStatus, :locationDetails, :addressDetails, :additionalDetails)", paramsMap);
	}
	
	@Transactional
	public List<SubscribedCustomer> displayCustomerWithEnquiries(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM SUBSCRIBED_CUSTOMER S WHERE S.CUSTOMER_ID IN (SELECT DISTINCT CUSTOMER_ID FROM ENQUIRIES E WHERE E.MATCH_STATUS = :matchStatus)");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_COMPLETED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_ABORTED);
				break;
			}
		}
		final List<SubscribedCustomer> customersEnquiryList = applicationDao.findAll(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
		for (final SubscribedCustomer customersEnquiryObject : customersEnquiryList) {
			// Get all lookup data and user ids back to original label and values
			customerService.removePasswordFromSubscribedCustomerObject(customersEnquiryObject);
		}
		return customersEnquiryList;
	}
	
	public List<Enquiry> displayAllEnquiriesForParticularCustomer(final Long customerId, final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("matchStatus", getMatchStatusFromGridName(grid));
		paramsMap.put("customerId", customerId);
		final List<Enquiry> enquiryObjectList =  applicationDao.findAll("SELECT * FROM ENQUIRIES E WHERE E.CUSTOMER_ID = :customerId AND E.MATCH_STATUS = :matchStatus", paramsMap, new EnquiryRowMapper());
		for (final Enquiry enquiryObject : enquiryObjectList) {
			replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, delimiter);
		}
		return enquiryObjectList;
	}
	
	private String getMatchStatusFromGridName(final String grid) {
		switch(grid) {
			case "customers-with-pending-enquiries" : return MATCH_STATUS_PENDING;
			case "customers-with-mapped-enquiries" : return MATCH_STATUS_PENDING;
			case "customers-with-abandoned-enquiries" : return MATCH_STATUS_PENDING;
			default : return null;
		}
	}
	
	public void replacePlaceHolderAndIdsFromEnquiryObject(final Enquiry enquiryObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		enquiryObject.setSubject(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, enquiryObject.getSubject(), false, delimiter));
		enquiryObject.setGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, enquiryObject.getGrade(), false, delimiter));
		enquiryObject.setLocationDetails(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, enquiryObject.getLocationDetails(), true, delimiter));
		enquiryObject.setPreferredTeachingType(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, enquiryObject.getPreferredTeachingType(), true, delimiter));
		enquiryObject.setWhoActed(commonsService.getNameOfUserFromUserId(enquiryObject.getWhoActed()));
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getTutorId())) {
			final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(enquiryObject.getTutorId());
			if (null != registeredTutorObj) {
				enquiryObject.setTutorName(registeredTutorObj.getName());
				enquiryObject.setTutorContactNumber(registeredTutorObj.getContactNumber());
				enquiryObject.setTutorEmail(registeredTutorObj.getEmailId());
			}
		}
	}
	
	public Map<String, List<SelectLookup>> getDropdownListData() {
		final Map<String, List<SelectLookup>> mapListSelectLookup = new HashMap<String, List<SelectLookup>>();
		mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
		mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
		mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		mapListSelectLookup.put("preferredTeachingTypeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP));
		return mapListSelectLookup;
	}
	
	@Transactional
	public Map<String, Object> updateEnquiryDetails(final Enquiry enquiryObject, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE ENQUIRIES SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();
		if (ValidationUtils.validateAgainstSelectLookupValues(enquiryObject.getSubject(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "SUBJECT = :subject";
			updatedPropertiesParams.put("subject", enquiryObject.getSubject());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(enquiryObject.getGrade(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "GRADE = :grade";
			updatedPropertiesParams.put("grade", enquiryObject.getGrade());
		}
		if (ValidationUtils.validateNumber(enquiryObject.getQuotedClientRate(), false, 9999, true, 0)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "QUOTED_CLIENT_RATE = :quotedClientRate";
			updatedPropertiesParams.put("quotedClientRate", enquiryObject.getQuotedClientRate());
		}
		if (ValidationUtils.validateNumber(enquiryObject.getNegotiatedRateWithClient(), false, 9999, true, 0)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEGOTIATED_RATE_WITH_CLIENT = :negotiatedRateWithClient";
			updatedPropertiesParams.put("negotiatedRateWithClient", enquiryObject.getNegotiatedRateWithClient());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getClientNegotiationRemarks())) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "CLIENT_NEGOTIATION_REMARKS = :clientNegotiationRemarks";
			updatedPropertiesParams.put("clientNegotiationRemarks", enquiryObject.getClientNegotiationRemarks());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(enquiryObject.getLocationDetails(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "LOCATION_DETAILS = :locationDetails";
			updatedPropertiesParams.put("locationDetails", enquiryObject.getLocationDetails());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(enquiryObject.getPreferredTeachingType(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "PREFERRED_TEACHING_TYPE = :preferredTeachingType";
			updatedPropertiesParams.put("preferredTeachingType", enquiryObject.getPreferredTeachingType());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getAddressDetails())) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDRESS_DETAILS = :addressDetails";
			updatedPropertiesParams.put("addressDetails", enquiryObject.getAddressDetails());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getAdditionalDetails())) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDITIONAL_DETAILS = :additionalDetails";
			updatedPropertiesParams.put("additionalDetails", enquiryObject.getAdditionalDetails());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(enquiryObject.getAdminRemarks())) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_REMARKS = :adminRemarks";
			updatedPropertiesParams.put("adminRemarks", enquiryObject.getAdminRemarks());
		}
		if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
			updateQuery += " ,LAST_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed WHERE ENQUIRY_ID = :enquiryId";
			updatedPropertiesParams.put("whoActed", user.getUserId());
			updatedPropertiesParams.put("enquiryId", enquiryObject.getEnquiryId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}
	
	public Map<String, Object> displayAllEligibleTutors(final Long enquiryId, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
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
	}
	
	public List<TutorMapper> displayAllMappedTutors(final Long enquiryId, final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS : {
				paramsMap.put("mappingStatus", MATCH_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS : {
				paramsMap.put("mappingStatus", "SCHEDULED");
				break;
			}
		}
		final List<TutorMapper> tutorMapperObjectList =  applicationDao.findAll("SELECT * FROM TUTOR_MAPPER E WHERE E.ENQUIRY_ID = :enquiryId AND E.MAPPING_STATUS = :mappingStatus", paramsMap, new TutorMapperRowMapper());
		for (final TutorMapper tutorMapperObject : tutorMapperObjectList) {
			final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
			tutorMapperObject.setTutorName(registeredTutorObj.getName());
			tutorMapperObject.setTutorContactNumber(registeredTutorObj.getContactNumber());
			tutorMapperObject.setTutorEmail(registeredTutorObj.getEmailId());
		}
		return tutorMapperObjectList;
	}
	
	public Map<String, Object> mapTutors(final Long enquiryId, final List<Long> tutorIdList) {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		for (final Long tutorId : tutorIdList) {
			paramsMap.put("tutorId", tutorId);
			applicationDao.executeUpdate("INSERT INTO TUTOR_MAPPER(ENQUIRY_ID, TUTOR_ID, MAPPING_STATUS) VALUES(:enquiryId, :tutorId, 'PENDING')", paramsMap);
		}
		return response;
	}
	
	public Map<String, Object> unmapTutors(final List<Long> tutorMapperIdList) {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		for (final Long tutorMapperId : tutorMapperIdList) {
			paramsMap.put("tutorMapperId", tutorMapperId);
			applicationDao.executeUpdate("DELETE FROM TUTOR_MAPPER WHERE TUTOR_MAPPER_ID = :tutorMapperId", paramsMap);
		}
		return response;
	}
	
	@Transactional
	public Map<String, Object> updateTutorMapperDetails(final TutorMapper tutorMapperObject, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE TUTOR_MAPPER SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();
		if (ValidationUtils.validateNumber(tutorMapperObject.getQuotedTutorRate(), false, 9999, true, 0)) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "QUOTED_TUTOR_RATE = :quotedTutorRate";
			updatedPropertiesParams.put("quotedTutorRate", tutorMapperObject.getQuotedTutorRate());
		}
		if (ValidationUtils.validateNumber(tutorMapperObject.getNegotiatedRateWithTutor(), false, 9999, true, 0)) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEGOTIATED_RATE_WITH_TUTOR = :negotiatedRateWithTutor";
			updatedPropertiesParams.put("negotiatedRateWithTutor", tutorMapperObject.getNegotiatedRateWithTutor());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getTutorNegotiationRemarks())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TUTOR_NEGOTIATION_REMARKS = :tutorNegotiationRemarks";
			updatedPropertiesParams.put("tutorNegotiationRemarks", tutorMapperObject.getTutorNegotiationRemarks());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsTutorContacted())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			if (YES.equalsIgnoreCase(tutorMapperObject.getIsTutorContacted())) {
				updateQuery += "IS_TUTOR_CONTACTED = :isTutorContacted, TUTOR_CONTACTED_DATE = SYSDATE()";
			} else {
				updateQuery += "IS_TUTOR_CONTACTED = :isTutorContacted, TUTOR_CONTACTED_DATE = SYSDATE()";
			}
			updatedPropertiesParams.put("isTutorContacted", tutorMapperObject.getIsTutorContacted());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsTutorAgreed())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "IS_TUTOR_AGREED = :isTutorAgreed";
			updatedPropertiesParams.put("isTutorAgreed", tutorMapperObject.getIsTutorAgreed());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsTutorRejectionValid())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "IS_TUTOR_REJECTION_VALID = :isTutorRejectionValid";
			updatedPropertiesParams.put("isTutorRejectionValid", tutorMapperObject.getIsTutorRejectionValid());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getAdminTutorRejectionValidityResponse())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE = :adminTutorRejectionValidityResponse";
			updatedPropertiesParams.put("adminTutorRejectionValidityResponse", tutorMapperObject.getAdminTutorRejectionValidityResponse());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getTutorResponse())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TUTOR_RESPONSE = :tutorResponse";
			updatedPropertiesParams.put("tutorResponse", tutorMapperObject.getTutorResponse());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getAdminRemarksForTutor())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_REMARKS_FOR_TUTOR = :adminRemarksForTutor";
			updatedPropertiesParams.put("adminRemarksForTutor", tutorMapperObject.getAdminRemarksForTutor());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsClientContacted())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			if (YES.equalsIgnoreCase(tutorMapperObject.getIsClientContacted())) {
				updateQuery += "IS_CLIENT_CONTACTED = :isClientContacted, CLIENT_CONTACTED_DATE = SYSDATE()";
			} else {
				updateQuery += "IS_CLIENT_CONTACTED = :isClientContacted, CLIENT_CONTACTED_DATE = SYSDATE()";
			}
			updatedPropertiesParams.put("isClientContacted", tutorMapperObject.getIsClientContacted());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsClientAgreed())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "IS_CLIENT_AGREED = :isClientAgreed";
			updatedPropertiesParams.put("isClientAgreed", tutorMapperObject.getIsClientAgreed());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getClientResponse())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "CLIENT_RESPONSE = :clientResponse";
			updatedPropertiesParams.put("clientResponse", tutorMapperObject.getClientResponse());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getIsClientRejectionValid())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "IS_CLIENT_REJECTION_VALID = :isClientRejectionValid";
			updatedPropertiesParams.put("isClientRejectionValid", tutorMapperObject.getIsClientRejectionValid());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getAdminClientRejectionValidityResponse())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE = :adminClientRejectionValidityResponse";
			updatedPropertiesParams.put("adminClientRejectionValidityResponse", tutorMapperObject.getAdminClientRejectionValidityResponse());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getAdminRemarksForClient())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_REMARKS_FOR_CLIENT = :adminRemarksForClient";
			updatedPropertiesParams.put("adminRemarksForClient", tutorMapperObject.getAdminRemarksForClient());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperObject.getAdminActionRemarks())) {
			if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_ACTION_REMARKS = :adminActionRemarks";
			updatedPropertiesParams.put("adminActionRemarks", tutorMapperObject.getAdminActionRemarks());
		}
		if (!"UPDATE TUTOR_MAPPER SET ".equals(updateQuery)) {
			updateQuery += " ,ADMIN_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed WHERE TUTOR_MAPPER_ID = :tutorMapperId";
			updatedPropertiesParams.put("whoActed", user.getUserId());
			updatedPropertiesParams.put("tutorMapperId", tutorMapperObject.getTutorMapperId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}

	public Map<String, Object> scheduleDemo(final Long tutorMapperId, final Date demoDateAndTime, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("whoActed", user.getUserId());
		paramsMap.put("demoDateAndTime", demoDateAndTime);
		paramsMap.put("tutorMapperId", tutorMapperId);
		paramsMap.put("demoDateAndTimeMillis", demoDateAndTime.getTime());
		applicationDao.executeUpdate("UPDATE TUTOR_MAPPER SET IS_DEMO_SCHEDULED = 'Y', MAPPING_STATUS = 'SCHEDULED', ADMIN_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed WHERE TUTOR_MAPPER_ID = :tutorMapperId", paramsMap);
		applicationDao.executeUpdate("INSERT INTO DEMO_TRACKER(TUTOR_MAPPER_ID, DEMO_DATE_AND_TIME, DEMO_DATE_AND_TIME_MILLIS, DEMO_STATUS) VALUES(:tutorMapperId, :demoDateAndTime, :demoDateAndTimeMillis, 'PENDING')", paramsMap);
		sendDemoScheduledNotificationEmails(tutorMapperId, demoDateAndTime.getTime());
		return response;
	}
	
	public TutorMapper getTutorMapperObject(final Long tutorMapperId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorMapperId", tutorMapperId);
		return applicationDao.find("SELECT * FROM TUTOR_MAPPER E WHERE E.TUTOR_MAPPER_ID = :tutorMapperId", paramsMap, new TutorMapperRowMapper());
	}
	
	public Enquiry getEnquiriesObject(final Long enquiryId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		return applicationDao.find("SELECT * FROM ENQUIRIES E WHERE E.ENQUIRY_ID = :enquiryId", paramsMap, new EnquiryRowMapper());
	}
	
	public void sendDemoScheduledNotificationEmails(final Long tutorMapperId, final Long demoTimeInMillis) throws Exception {
		final TutorMapper tutorMapperObject = getTutorMapperObject(tutorMapperId);
		final Enquiry enquiryObject =  getEnquiriesObject(tutorMapperObject.getEnquiryId());
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		attributes.put("enquiryObject", enquiryObject);
		attributes.put("subscribedCustomerObj", subscribedCustomerObj);
		attributes.put("registeredTutorObj", registeredTutorObj);
		attributes.put("tutorMapperObject", tutorMapperObject);
		attributes.put("demoDateAndTimeIST", DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(demoTimeInMillis));
		// Tutor Email
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Your demo has been scheduled with Client - " + subscribedCustomerObj.getName(), 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_SCHEDULED_TUTOR_EMAIL_PATH, attributes),
				null);
		// Client Email
		MailUtils.sendMimeMessageEmail( 
				subscribedCustomerObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Tutor demo has been scheduled for your enquiry", 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_SCHEDULED_CLIENT_EMAIL_PATH, attributes),
				null);
	}
	
	/**************************************************************************************************************/
	public List<Enquiry> getEnquiryList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = "SELECT "
				+ "EN.*, "
				+ "C.NAME AS CUSTOMER_NAME, "
				+ "C.EMAIL_ID AS CUSTOMER_EMAIL, "
				+ "C.CONTACT_NUMBER AS CUSTOMER_CONTACT_NUMBER, "
				+ "T.NAME AS TUTOR_NAME, "
				+ "T.EMAIL_ID AS TUTOR_EMAIL, "
				+ "T.CONTACT_NUMBER AS TUTOR_CONTACT_NUMBER, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = EN.WHO_ACTED), EN.WHO_ACTED) AS WHO_ACTED_NAME "
				+ "FROM ENQUIRIES EN "
				+ "LEFT OUTER JOIN SUBSCRIBED_CUSTOMER C ON EN.CUSTOMER_ID = C.CUSTOMER_ID "
				+ "LEFT OUTER JOIN REGISTERED_TUTOR T ON EN.TUTOR_ID = T.TUTOR_ID";
		String existingFilterQueryString = "WHERE EN.MATCH_STATUS = :matchStatus";
		final String existingSorterQueryString = "ORDER BY ENTRY_DATE_MILLIS";		
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
				existingFilterQueryString += " AND EN.CUSTOMER_ID = :customerId";
				paramsMap.put("matchStatus", MATCH_STATUS_PENDING);
				paramsMap.put("customerId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class));
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new EnquiryRowMapper());
	}
	
	public List<TutorMapper> getAllMappedTutorsList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = "SELECT "
				+ "TM.*, "
				+ "EN.SUBJECT AS ENQUIRY_SUBJECT, "
				+ "EN.GRADE AS ENQUIRY_GRADE, "
				+ "EN.LOCATION_DETAILS AS ENQUIRY_LOCATION, "
				+ "EN.PREFERRED_TEACHING_TYPE AS ENQUIRY_PREFERRED_TEACHING_TYPE, "
				+ "T.NAME AS TUTOR_NAME, "
				+ "T.EMAIL_ID AS TUTOR_EMAIL, "
				+ "T.CONTACT_NUMBER AS TUTOR_CONTACT_NUMBER, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = TM.WHO_ACTED), TM.WHO_ACTED) AS WHO_ACTED_NAME "
				+ "FROM TUTOR_MAPPER TM "
				+ "INNER JOIN REGISTERED_TUTOR T ON TM.TUTOR_ID = T.TUTOR_ID "
				+ "INNER JOIN ENQUIRIES EN ON TM.ENQUIRY_ID = EN.ENQUIRY_ID";
		String existingFilterQueryString = "WHERE TM.MAPPING_STATUS = :mappingStatus";
		final String existingSorterQueryString = "ORDER BY ENTRY_DATE_MILLIS";	
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_ALL_PENDING_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", "PENDING");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ALL_DEMO_READY_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", "DEMO_READY");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ALL_DEMO_SCHEDULED_MAPPED_TUTORS_LIST : {
				paramsMap.put("mappingStatus", "DEMO_SCHEDULED");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_ENQUIRY_ALL_MAPPED_TUTORS_LIST : {
				existingFilterQueryString = "WHERE TM.ENQUIRY_ID = :enquiryId";
				paramsMap.put("enquiryId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "enquiryId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_TUTOR_ALL_MAPPING_LIST : {
				existingFilterQueryString = "WHERE TM.TUTOR_ID = :tutorId";
				paramsMap.put("tutorId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class));
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new TutorMapperRowMapper());
	}
}
