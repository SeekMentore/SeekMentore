package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.constants.components.publicaccess.SubscribedCustomerConstants;
import com.dao.ApplicationDao;
import com.model.WorkbookReport;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.SubscribedCustomer;
import com.model.components.TutorDocument;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.FindTutor;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE)
	public class CustomerService implements SubscribedCustomerConstants{

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	public Map<String, List<SelectLookup>> getDropdownListData() {
		final Map<String, List<SelectLookup>> mapListSelectLookup = new HashMap<String, List<SelectLookup>>();
		mapListSelectLookup.put("studentGradeLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP));
		mapListSelectLookup.put("subjectsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP));
		mapListSelectLookup.put("locationsLookUp", commonsService.getSelectLookupList(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP));
		return mapListSelectLookup;
	}
	
	@Transactional
	public Map<String, Object> updateDetails(final SubscribedCustomer subscriberCustomerObj) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE SUBSCRIBED_CUSTOMER SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();

		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getStudentGrades(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "STUDENT_GRADE = :studentGrades";
			updatedPropertiesParams.put("studentGrades", subscriberCustomerObj.getStudentGrades());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getInterestedSubjects(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "SUBJECTS = :interestedSubjects";
			updatedPropertiesParams.put("interestedSubjects", subscriberCustomerObj.getInterestedSubjects());
		}
		if (ValidationUtils.validateAgainstSelectLookupValues(subscriberCustomerObj.getLocation(), SEMI_COLON, SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP)) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "LOCATION = :Location";
			updatedPropertiesParams.put("Location", subscriberCustomerObj.getLocation());
		}
		
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(subscriberCustomerObj.getAddressDetails())) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDRESS_DETAILS = :addressDetails";
			updatedPropertiesParams.put("addressDetails", subscriberCustomerObj.getAddressDetails());
		}
		
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(subscriberCustomerObj.getAdditionalDetails())) {
			if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADDITIONAL_DETAILS = :additionalDetails";
			updatedPropertiesParams.put("additionalDetails", subscriberCustomerObj.getAdditionalDetails());
		}
		if (!"UPDATE SUBSCRIBED_CUSTOMER SET ".equals(updateQuery)) {
			updatedPropertiesParams.put("updatedBy", "SELF");
			updateQuery += " ,RECORD_LAST_UPDATED = SYSDATE(), UPDATED_BY = :updatedBy WHERE CUSTOMER_ID = :customerId";
			updatedPropertiesParams.put("customerId", subscriberCustomerObj.getCustomerId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}
	
	public void replacePlaceHolderAndIdsFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		subscriberCustomerObj.setStudentGrades(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, subscriberCustomerObj.getStudentGrades(), true, delimiter));
		subscriberCustomerObj.setInterestedSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, subscriberCustomerObj.getInterestedSubjects(), true, delimiter));
		subscriberCustomerObj.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, subscriberCustomerObj.getLocation(), true, delimiter));
	}
	
	private void replaceNullWithBlankRemarksInSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setAdditionalDetails(ApplicationUtils.returnBlankIfStringNull(subscriberCustomerObj.getAdditionalDetails()));
		subscriberCustomerObj.setAddressDetails(ApplicationUtils.returnBlankIfStringNull(subscriberCustomerObj.getAddressDetails()));
	}
	
	public void removeAllSensitiveInformationFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setCustomerId(null);
		subscriberCustomerObj.setEnquiryID(null);
		subscriberCustomerObj.setEncyptedPassword(null);
		subscriberCustomerObj.setUserId(null);
		subscriberCustomerObj.setRecordLastUpdated(null);
		subscriberCustomerObj.setUpdatedBy(null);
	}
	
	public void removeUltraSensitiveInformationFromSubscribedCustomerObject(final SubscribedCustomer subscriberCustomerObj) {
		subscriberCustomerObj.setCustomerId(null);
		subscriberCustomerObj.setEncyptedPassword(null);
		subscriberCustomerObj.setEnquiryID(null);
	}

	public void removeSensitiveInformationFromTutorDocumentObject(final TutorDocument tutorDocumentObj) {
		tutorDocumentObj.setWhoActed(null);
		tutorDocumentObj.setActionDate(null);
	}
	
	/*
	 * Admin Functions
	 */
	public List<SubscribedCustomer> subscribedCustomersList(final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final List<SubscribedCustomer> subscribedCustomerList = applicationDao.findAllWithoutParams("SELECT * FROM SUBSCRIBED_CUSTOMER", new SubscribedCustomerRowMapper());
		for (final SubscribedCustomer subscribedCustomerObject : subscribedCustomerList) {
			// Get all lookup data and user ids back to original label and values
			removeUltraSensitiveInformationFromSubscribedCustomerObject(subscribedCustomerObject);
			replacePlaceHolderAndIdsFromSubscribedCustomerObject(subscribedCustomerObject, delimiter);
		}
		return subscribedCustomerList;
	}
	
	public Map<String, Object> getSubscribedCustomer(final SubscribedCustomer subscribedCustomerObj) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		replacePlaceHolderAndIdsFromSubscribedCustomerObject(subscribedCustomerObj, LINE_BREAK);
		removeAllSensitiveInformationFromSubscribedCustomerObject(subscribedCustomerObj);
		response.put("subscribedCustomerObj", subscribedCustomerObj);
		return response;
	}
	
}
