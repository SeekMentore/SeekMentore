package com.service.components;

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
import com.model.components.EnquiryObject;
import com.model.components.SubscribedCustomer;
import com.model.components.commons.SelectLookup;
import com.model.rowmappers.EnquiryObjectRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE)
	public class EnquiryService implements EnquiryConstants {

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void addEnquiry(final EnquiryObject enquiryObject) {
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
				paramsMap.put("matchStatus", "PENDING");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES : {
				paramsMap.put("matchStatus", "MAPPED");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES : {
				paramsMap.put("matchStatus", "ABANDONED");
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

	public List<EnquiryObject> displayAllEnquiriesForParticularCustomer(final Long customerId, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", customerId);
		final List<EnquiryObject> ernquiryObjectList =  applicationDao.findAll("SELECT * FROM ENQUIRIES E WHERE E.CUSTOMER_ID = :customerId", paramsMap, new EnquiryObjectRowMapper());
		for (final EnquiryObject ernquiryObject : ernquiryObjectList) {
			replacePlaceHolderAndIdsFromEnquiryObject(ernquiryObject, delimiter);
		}
		return ernquiryObjectList;
	}
	
	public void replacePlaceHolderAndIdsFromEnquiryObject(final EnquiryObject enquiryObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		enquiryObject.setSubject(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, enquiryObject.getSubject(), false, delimiter));
		enquiryObject.setGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, enquiryObject.getGrade(), false, delimiter));
		enquiryObject.setLocationDetails(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, enquiryObject.getLocationDetails(), true, delimiter));
		enquiryObject.setPreferredTeachingType(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, enquiryObject.getPreferredTeachingType(), true, delimiter));
		enquiryObject.setWhoActed(commonsService.getNameOfUserFromUserId(enquiryObject.getWhoActed()));
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
	public Map<String, Object> updateEnquiryDetails(final EnquiryObject enquiryObject, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, "Nothing to be updated.");
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
		if (ValidationUtils.validateNumber(enquiryObject.getQuotedClientRate(), false, 99, true, 0)) {
			if (!"UPDATE ENQUIRIES SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "QUOTED_CLIENT_RATE = :quotedClientRate";
			updatedPropertiesParams.put("quotedClientRate", enquiryObject.getQuotedClientRate());
		}
		if (ValidationUtils.validateNumber(enquiryObject.getNegotiatedRateWithClient(), false, 99, true, 0)) {
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
			updatedPropertiesParams.put("whoActed", user.getUserId());
			updateQuery += " ,LAST_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed WHERE ENQUIRY_ID = :enquiryId";
			updatedPropertiesParams.put("enquiryId", enquiryObject.getEnquiryId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}
}
