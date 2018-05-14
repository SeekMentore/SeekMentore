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
import com.dao.ApplicationDao;
import com.model.components.EnquiryObject;
import com.model.components.SubscribedCustomer;
import com.model.rowmappers.SubscribedCustomerRowMapper;

@Service(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE)
	public class EnquiryService implements EnquiryConstants {

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CustomerService customerService;
	
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
	public List<SubscribedCustomer> displayTutorRegistrations(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
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
			customerService.removeUltraSensitiveInformationFromSubscribedCustomerObject(customersEnquiryObject);
		}
		return customersEnquiryList;
	}
	
}
