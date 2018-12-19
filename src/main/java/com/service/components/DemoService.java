package com.service.components;

import java.util.ArrayList;
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
import com.constants.components.DemoTrackerConstants;
import com.constants.components.SalesConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.DemoTracker;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.TutorMapper;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.DemoTrackerRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.QueryMapperService;
import com.utils.DateUtils;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_DEMO_SERVICE)
public class DemoService implements DemoTrackerConstants, SalesConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient TutorService tutorService;
	
	@Autowired
	private transient EnquiryService enquiryService;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
	private QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public List<DemoTracker> displayDemos(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM DEMO_TRACKER D WHERE D.DEMO_STATUS = :demoStatus");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SCHEDULED_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_RESCHEDULED_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_RESCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_FAILED_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CANCELED_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_CANCELED);
				break;
			}
		}
		final List<DemoTracker> demoTrackerList = applicationDao.findAll(query.toString(), paramsMap, new DemoTrackerRowMapper());
		for (final DemoTracker demoTrackerObject : demoTrackerList) {
			final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(demoTrackerObject.getTutorMapperId());
			final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
			final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
			final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
			demoTrackerObject.setCustomerName(subscribedCustomerObj.getName());
			demoTrackerObject.setTutorName(registeredTutorObj.getName());
		}
		return demoTrackerList;
	}
	
	public DemoTracker getDemoTracker(final Long demoTrackerId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("demoTrackerId", demoTrackerId);
		return applicationDao.find("SELECT * FROM DEMO_TRACKER WHERE DEMO_TRACKER_ID = :demoTrackerId", paramsMap, new DemoTrackerRowMapper());
	}
	
	public Map<String, Object> displayDemoDetails(final Long demoTrackerId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final DemoTracker demoTracker = getDemoTracker(demoTrackerId);
		final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(demoTracker.getTutorMapperId());
		final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
		enquiryService.replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		response.put("tutorMapperObject", tutorMapperObject);
		response.put("enquiryObject", enquiryObject);
		response.put("registeredTutorObj", registeredTutorObj);
		response.put("subscribedCustomerObj", subscribedCustomerObj);
		return response;
	}
	
	@Transactional
	public Map<String, Object> updateDemoTrackerDetails(final DemoTracker demoTrackerObject, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, true);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, "Nothing to be updated.");
		String updateQuery = "UPDATE DEMO_TRACKER SET ";
		final Map<String, Object> updatedPropertiesParams = new HashMap<String, Object>();
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getDemoOccurred())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "DEMO_OCCURRED = :demoOccurred";
			updatedPropertiesParams.put("demoOccurred", demoTrackerObject.getDemoOccurred());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getClientRemarks())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "CLIENT_REMARKS = :clientRemarks";
			updatedPropertiesParams.put("clientRemarks", demoTrackerObject.getClientRemarks());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getTutorRemarks())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TUTOR_REMARKS = :tutorRemarks";
			updatedPropertiesParams.put("tutorRemarks", demoTrackerObject.getTutorRemarks());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getClientSatisfiedFromTutor())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "CLIENT_SATISFIED_FROM_TUTOR = :clientSatisfiedFromTutor";
			updatedPropertiesParams.put("clientSatisfiedFromTutor", demoTrackerObject.getClientSatisfiedFromTutor());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getTutorSatisfiedWithClient())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TUTOR_SATISFIED_WITH_CLIENT = :tutorSatisfiedWithClient";
			updatedPropertiesParams.put("tutorSatisfiedWithClient", demoTrackerObject.getTutorSatisfiedWithClient());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getAdminSatisfiedFromTutor())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_SATISFIED_FROM_TUTOR = :adminSatisfiedFromTutor";
			updatedPropertiesParams.put("adminSatisfiedFromTutor", demoTrackerObject.getAdminSatisfiedFromTutor());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getAdminSatisfiedWithClient())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_SATISFIED_WITH_CLIENT = :adminSatisfiedWithClient";
			updatedPropertiesParams.put("adminSatisfiedWithClient", demoTrackerObject.getAdminSatisfiedWithClient());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getNeedPriceNegotiationWithClient())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEED_PRICE_NEGOTIATION_WITH_CLIENT = :needPriceNegotiationWithClient";
			updatedPropertiesParams.put("needPriceNegotiationWithClient", demoTrackerObject.getNeedPriceNegotiationWithClient());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getClientNegotiationRemarks())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "CLIENT_NEGOTIATION_REMARKS = :clientNegotiationRemarks";
			updatedPropertiesParams.put("clientNegotiationRemarks", demoTrackerObject.getClientNegotiationRemarks());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getNeedPriceNegotiationWithTutor())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEED_PRICE_NEGOTIATION_WITH_TUTOR = :needPriceNegotiationWithTutor";
			updatedPropertiesParams.put("needPriceNegotiationWithTutor", demoTrackerObject.getNeedPriceNegotiationWithTutor());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getTutorNegotiationRemarks())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "TUTOR_NEGOTIATION_REMARKS = :tutorNegotiationRemarks";
			updatedPropertiesParams.put("tutorNegotiationRemarks", demoTrackerObject.getTutorNegotiationRemarks());
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(demoTrackerObject.getAdminRemarks())) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "ADMIN_REMARKS = :adminRemarks";
			updatedPropertiesParams.put("adminRemarks", demoTrackerObject.getAdminRemarks());
		}
		if (ValidationUtils.validateNumber(demoTrackerObject.getNegotiatedOverrideRateWithClient(), false, 9999, true, 0)) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT = :negotiatedOverrideRateWithClient";
			updatedPropertiesParams.put("negotiatedOverrideRateWithClient", demoTrackerObject.getNegotiatedOverrideRateWithClient());
		}
		if (ValidationUtils.validateNumber(demoTrackerObject.getNegotiatedOverrideRateWithTutor(), false, 9999, true, 0)) {
			if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
				updateQuery += " ,";
			}
			updateQuery += "NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR = :negotiatedOverrideRateWithTutor";
			updatedPropertiesParams.put("negotiatedOverrideRateWithTutor", demoTrackerObject.getNegotiatedOverrideRateWithTutor());
		}
		if (!"UPDATE DEMO_TRACKER SET ".equals(updateQuery)) {
			updateQuery += " ,ADMIN_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed WHERE DEMO_TRACKER_ID = :demoTrackerId";
			updatedPropertiesParams.put("whoActed", user.getUserId());
			updatedPropertiesParams.put("demoTrackerId", demoTrackerObject.getDemoTrackerId());
			applicationDao.executeUpdate(updateQuery, updatedPropertiesParams);
			response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
			response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		}
		return response;
	}

	public Map<String, Object> takeActionOnDemo(final String actionName, final Long demoTrackerId, final String finalizingRemarks, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		String updateQuery = "UPDATE DEMO_TRACKER SET ";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(actionName) {
			case RestMethodConstants.REST_METHOD_NAME_DEMO_SUCCESS : {
				updateQuery += "IS_DEMO_SUCCESS = 'Y'";
				paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DEMO_FAILURE : {
				updateQuery += "IS_DEMO_SUCCESS = 'N'";
				paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CANCEL_DEMO : {
				updateQuery += "IS_DEMO_SUCCESS = NULL";
				paramsMap.put("demoStatus", DEMO_STATUS_CANCELED);
				break;
			}
		}
		updateQuery += " ,DEMO_STATUS = :demoStatus ,ADMIN_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed, ADMIN_FINALIZING_REMARKS = :adminFinalizingRemarks WHERE DEMO_TRACKER_ID = :demoTrackerId";
		paramsMap.put("whoActed", user.getUserId());
		paramsMap.put("adminFinalizingRemarks", finalizingRemarks);
		paramsMap.put("demoTrackerId", demoTrackerId);
		applicationDao.executeUpdate(updateQuery, paramsMap);
		switch(actionName) {
			case RestMethodConstants.REST_METHOD_NAME_DEMO_SUCCESS : {
				sendDemoSuccessNotificationEmails(demoTrackerId);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DEMO_FAILURE : {
				sendDemoFailedNotificationEmails(demoTrackerId);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CANCEL_DEMO : {
				sendDemoCanceledNotificationEmails(demoTrackerId);
				break;
			}
		}
		return response;
	}
	
	public void sendDemoSuccessNotificationEmails(final Long demoTrackerId) throws Exception {
		final DemoTracker demoTrackerObject = getDemoTracker(demoTrackerId);
		final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(demoTrackerObject.getTutorMapperId());
		final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
		enquiryService.replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		attributes.put("enquiryObject", enquiryObject);
		attributes.put("subscribedCustomerObj", subscribedCustomerObj);
		attributes.put("registeredTutorObj", registeredTutorObj);
		attributes.put("tutorMapperObject", tutorMapperObject);
		attributes.put("demoTrackerObject", demoTrackerObject);
		// Tutor Email
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Your demo was successful with Client - " + subscribedCustomerObj.getName(), 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_SUCCESS_TUTOR_EMAIL_PATH, attributes),
				null);
		// Client Email
		MailUtils.sendMimeMessageEmail( 
				subscribedCustomerObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Tutor demo was successful for your enquiry", 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_SUCCESS_CLIENT_EMAIL_PATH, attributes),
				null);
	}
	
	public void sendDemoFailedNotificationEmails(final Long demoTrackerId) throws Exception {
		final DemoTracker demoTrackerObject = getDemoTracker(demoTrackerId);
		final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(demoTrackerObject.getTutorMapperId());
		final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
		enquiryService.replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		attributes.put("enquiryObject", enquiryObject);
		attributes.put("subscribedCustomerObj", subscribedCustomerObj);
		attributes.put("registeredTutorObj", registeredTutorObj);
		attributes.put("tutorMapperObject", tutorMapperObject);
		attributes.put("demoTrackerObject", demoTrackerObject);
		// Sales Team Email
		MailUtils.sendMimeMessageEmail( 
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(), 
				null,
				null,
				"Demo faild with Client - " + subscribedCustomerObj.getName(), 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_FAILED_EMAIL_PATH, attributes),
				null);
	}
	
	public void sendDemoCanceledNotificationEmails(final Long demoTrackerId) throws Exception {
		final DemoTracker demoTrackerObject = getDemoTracker(demoTrackerId);
		final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(demoTrackerObject.getTutorMapperId());
		final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
		enquiryService.replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		attributes.put("enquiryObject", enquiryObject);
		attributes.put("subscribedCustomerObj", subscribedCustomerObj);
		attributes.put("registeredTutorObj", registeredTutorObj);
		attributes.put("tutorMapperObject", tutorMapperObject);
		attributes.put("demoTrackerObject", demoTrackerObject);
		// Tutor Email
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Your demo has been canceled with Client - " + subscribedCustomerObj.getName(), 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_CANCEL_TUTOR_EMAIL_PATH, attributes),
				null);
		// Client Email
		MailUtils.sendMimeMessageEmail( 
				subscribedCustomerObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Tutor demo has been cenceled for your enquiry", 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_CANCEL_CLIENT_EMAIL_PATH, attributes),
				null);
	}
	
	public Map<String, Object> rescheduleDemo(final Long demoTrackerId, final Date rescheduleDateAndTime, final String reschedulingRemarks, final User user) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final DemoTracker demoTrackerObject = getDemoTracker(demoTrackerId);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("demoTrackerId", demoTrackerObject.getDemoTrackerId());
		paramsMap.put("demoDateAndTime", rescheduleDateAndTime);
		paramsMap.put("reschedulingRemarks", reschedulingRemarks);
		paramsMap.put("tutorMapperId", demoTrackerObject.getTutorMapperId());
		paramsMap.put("demoOccurred", demoTrackerObject.getDemoOccurred());
		paramsMap.put("demoStatus", demoTrackerObject.getDemoStatus());
		paramsMap.put("clientRemarks", demoTrackerObject.getClientRemarks());
		paramsMap.put("tutorRemarks", demoTrackerObject.getTutorRemarks());
		paramsMap.put("clientSatisfiedFromTutor", demoTrackerObject.getClientSatisfiedFromTutor());
		paramsMap.put("tutorSatisfiedWithClient", demoTrackerObject.getTutorSatisfiedWithClient());
		paramsMap.put("adminSatisfiedFromTutor", demoTrackerObject.getAdminSatisfiedFromTutor());
		paramsMap.put("adminSatisfiedWithClient", demoTrackerObject.getAdminSatisfiedWithClient());
		paramsMap.put("whoActed", user.getUserId());
		paramsMap.put("isDemoSuccess", demoTrackerObject.getIsDemoSuccess());
		paramsMap.put("needPriceNegotiationWithClient", demoTrackerObject.getNeedPriceNegotiationWithClient());
		paramsMap.put("clientNegotiationRemarks", demoTrackerObject.getClientNegotiationRemarks());
		paramsMap.put("needPriceNegotiationWithTutor", demoTrackerObject.getNeedPriceNegotiationWithTutor());
		paramsMap.put("tutorNegotiationRemarks", demoTrackerObject.getTutorNegotiationRemarks());
		paramsMap.put("adminRemarks", demoTrackerObject.getAdminRemarks());
		paramsMap.put("negotiatedOverrideRateWithClient", demoTrackerObject.getNegotiatedOverrideRateWithClient());
		paramsMap.put("negotiatedOverrideRateWithTutor", demoTrackerObject.getNegotiatedOverrideRateWithTutor());
		applicationDao.executeUpdate("UPDATE DEMO_TRACKER SET DEMO_STATUS = 'RE-SCHEDULED', ADMIN_ACTION_DATE = SYSDATE(), WHO_ACTED = :whoActed, RESCHEDULING_REMARKS = :reschedulingRemarks WHERE DEMO_TRACKER_ID = :demoTrackerId", paramsMap);
		final Long newDemoTrackerId = applicationDao.insertAndReturnGeneratedKey("INSERT INTO DEMO_TRACKER(TUTOR_MAPPER_ID, DEMO_DATE_AND_TIME, DEMO_DATE_AND_TIME_MILLIS, DEMO_OCCURRED, DEMO_STATUS, CLIENT_REMARKS, TUTOR_REMARKS, CLIENT_SATISFIED_FROM_TUTOR, TUTOR_SATISFIED_WITH_CLIENT, ADMIN_SATISFIED_FROM_TUTOR, ADMIN_SATISFIED_WITH_CLIENT, WHO_ACTED, IS_DEMO_SUCCESS, NEED_PRICE_NEGOTIATION_WITH_CLIENT, CLIENT_NEGOTIATION_REMARKS, NEED_PRICE_NEGOTIATION_WITH_TUTOR, TUTOR_NEGOTIATION_REMARKS, ADMIN_REMARKS, NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT, NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR, ADMIN_ACTION_DATE) VALUES(:tutorMapperId, :demoDateAndTime, :demoDateAndTimeMillis, :demoOccurred, :demoStatus, :clientRemarks, :tutorRemarks, :clientSatisfiedFromTutor, :tutorSatisfiedWithClient, :adminSatisfiedFromTutor, :adminSatisfiedWithClient, :whoActed, :isDemoSuccess, :needPriceNegotiationWithClient, :clientNegotiationRemarks, :needPriceNegotiationWithTutor, :tutorNegotiationRemarks, :adminRemarks, :negotiatedOverrideRateWithClient, :negotiatedOverrideRateWithTutor, SYSDATE())", paramsMap);
		sendDemoScheduledNotificationEmails(newDemoTrackerId, demoTrackerObject);
		return response;
	}
	
	public void sendDemoScheduledNotificationEmails(final Long newDemoTrackerId, final DemoTracker oldDemoTrackerObject) throws Exception {
		final DemoTracker newDemoTrackerObject = getDemoTracker(newDemoTrackerId);
		final TutorMapper tutorMapperObject = enquiryService.getTutorMapperObject(newDemoTrackerObject.getTutorMapperId());
		final Enquiry enquiryObject =  enquiryService.getEnquiriesObject(tutorMapperObject.getEnquiryId());
		enquiryService.replacePlaceHolderAndIdsFromEnquiryObject(enquiryObject, LINE_BREAK);
		final RegisteredTutor registeredTutorObj = tutorService.getRegisteredTutorObject(tutorMapperObject.getTutorId());
		final SubscribedCustomer subscribedCustomerObj = customerService.getSubscribedCustomerObject(enquiryObject.getCustomerId());
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		attributes.put("enquiryObject", enquiryObject);
		attributes.put("subscribedCustomerObj", subscribedCustomerObj);
		attributes.put("registeredTutorObj", registeredTutorObj);
		attributes.put("tutorMapperObject", tutorMapperObject);
		attributes.put("oldDemoTrackerObject", oldDemoTrackerObject);
		attributes.put("newDemoTrackerObject", newDemoTrackerObject);
		attributes.put("demoDateAndTimeISTOld", DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(oldDemoTrackerObject.getDemoDateAndTimeMillis()));
		attributes.put("demoDateAndTimeISTNew", DateUtils.parseDateInIndianDTFormatAfterConvertingToIndianTimeZone(newDemoTrackerObject.getDemoDateAndTimeMillis()));
		// Tutor Email
		MailUtils.sendMimeMessageEmail( 
				registeredTutorObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Your demo has been re-scheduled with Client - " + subscribedCustomerObj.getName(), 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_RESCHEDULED_TUTOR_EMAIL_PATH, attributes),
				null);
		// Client Email
		MailUtils.sendMimeMessageEmail( 
				subscribedCustomerObj.getEmailId(), 
				null,
				jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList(),
				"Tutor demo has been re-scheduled for your enquiry", 
				VelocityUtils.parseTemplate(VELOCITY_TEMPLATES_DEMO_RESCHEDULED_CLIENT_EMAIL_PATH, attributes),
				null);
	}
	
	/**********************************************************************************************/
	public List<DemoTracker> getDemoList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-demo", "selectDemo");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoExistingFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoExistingSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-demo", "demoCurrentTutorAdditionalFilter");
				paramsMap.put("tutorId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-demo", "demoCurrentCustomerAdditionalFilter");
				paramsMap.put("customerId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_RESCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_RESCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_FAILED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CANCELED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_CANCELED);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new DemoTrackerRowMapper());
	}
	
	@Transactional
	public Long insertScheduledDemo (
			final Long tutorMapperId, 
			final Long demoDateAndTimeMillis, 
			final User activeUser, 
			final Boolean sendEmails,
			final Boolean isReScheduled,
			final String reschedulingRemarks,
			final Integer reScheduleCount
	) throws Exception {
		final Date currentTimestamp = new Date();
		final DemoTracker demoTracker = new DemoTracker();
		demoTracker.setTutorMapperId(tutorMapperId);
		demoTracker.setDemoDateAndTimeMillis(demoDateAndTimeMillis);
		demoTracker.setDemoStatus(DemoTrackerConstants.DEMO_STATUS_SCHEDULED);
		demoTracker.setWhoActed(activeUser.getUserId());
		demoTracker.setAdminActionDateMillis(currentTimestamp.getTime());
		demoTracker.setEntryDateMillis(currentTimestamp.getTime());
		String insertQueryId = "insertDemo";
		if (isReScheduled) {
			demoTracker.setReschedulingRemarks(reschedulingRemarks);
			demoTracker.setReScheduleCount(reScheduleCount + 1);
			insertQueryId = "insertReScheduledDemo";
		}
		final Long demoTrackerId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("sales-demo", insertQueryId, demoTracker);
		if (sendEmails) {
			sendDemoScheduledNotificationEmails(demoTrackerId);
		}
		return demoTrackerId;
	}
	
	public void sendDemoScheduledNotificationEmails(final Long demoTrackerId) throws Exception {
		/*final TutorMapper tutorMapperObject = getTutorMapperObject(tutorMapperId);
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
				null);*/
	}
	
	@Transactional
	public void reScheduleDemo(final DemoTracker demoTrackerObject, final User activeUser) throws Exception {
		final List<String> idList = new ArrayList<String>();
		idList.add(String.valueOf(demoTrackerObject.getDemoTrackerId()));
		takeActionOnDemo(BUTTON_ACTION_CANCEL, idList, "Re-scheduling demo", activeUser, false);
		final Long newDemoTrackerId = insertScheduledDemo(demoTrackerObject.getTutorMapperId(), demoTrackerObject.getDemoDateAndTimeMillis(), activeUser, false, true, demoTrackerObject.getReschedulingRemarks(), demoTrackerObject.getReScheduleCount());
		sendDemoReScheduledNotificationEmails(demoTrackerObject.getDemoTrackerId(), newDemoTrackerId);
	}
	
	public void sendDemoReScheduledNotificationEmails(final Long oldDemoTrackerId, final Long newDemoTrackerId) throws Exception {
		
	}

	@Transactional
	public void takeActionOnDemo(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("sales-demo", "updateDemo"));
		query.append(queryMapperService.getQuerySQL("sales-demo", "updateDemoStatus"));
		String demoStatus = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_CANCEL : {
				demoStatus = DEMO_STATUS_CANCELED;
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String demoTrackerId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("demoStatus", demoStatus);
			paramsMap.put("demoTrackerId", demoTrackerId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
		if (sendEmails) {
			switch(button) {
				case BUTTON_ACTION_CANCEL : {
					sendDemoCanceledNotificationEmails(idList);
					break;
				}
			}
		}
	}
	
	public void sendDemoCanceledNotificationEmails(final List<String> idList) throws Exception {
		
	}

	@Transactional
	public void updateDemoRecord(final DemoTracker demoTrackerObject, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE DEMO_TRACKER SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE DEMO_TRACKER_ID = :demoTrackerId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "demoOccurred" : {
						updateAttributesQuery.add("DEMO_OCCURRED = :demoOccurred");
						paramsMap.put("demoOccurred", demoTrackerObject.getDemoOccurred());
						break;
					}
					case "clientSatisfiedFromTutor" : {
						updateAttributesQuery.add("CLIENT_SATISFIED_FROM_TUTOR = :clientSatisfiedFromTutor");
						paramsMap.put("clientSatisfiedFromTutor", demoTrackerObject.getClientSatisfiedFromTutor());
						break;
					}
					case "clientRemarks" : {
						updateAttributesQuery.add("CLIENT_REMARKS = :clientRemarks");
						paramsMap.put("clientRemarks", demoTrackerObject.getClientRemarks());
						break;
					}
					case "tutorSatisfiedWithClient" : {
						updateAttributesQuery.add("TUTOR_SATISFIED_WITH_CLIENT = :tutorSatisfiedWithClient");
						paramsMap.put("tutorSatisfiedWithClient", demoTrackerObject.getTutorSatisfiedWithClient());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", demoTrackerObject.getTutorRemarks());
						break;
					}
					case "adminSatisfiedFromTutor" : {
						updateAttributesQuery.add("ADMIN_SATISFIED_FROM_TUTOR = :adminSatisfiedFromTutor");
						paramsMap.put("adminSatisfiedFromTutor", demoTrackerObject.getAdminSatisfiedFromTutor());
						break;
					}
					case "adminSatisfiedWithClient" : {
						updateAttributesQuery.add("ADMIN_SATISFIED_WITH_CLIENT = :adminSatisfiedWithClient");
						paramsMap.put("adminSatisfiedWithClient", demoTrackerObject.getAdminSatisfiedWithClient());
						break;
					}
					case "isDemoSuccess" : {
						updateAttributesQuery.add("IS_DEMO_SUCCESS = :isDemoSuccess");
						updateAttributesQuery.add("DEMO_STATUS = :demoStatus");
						paramsMap.put("isDemoSuccess", demoTrackerObject.getIsDemoSuccess());
						if (YES.equals(demoTrackerObject.getIsDemoSuccess())) {
							paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
						} else {
							paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
						}
						break;
					}
					case "needPriceNegotiationWithClient" : {
						updateAttributesQuery.add("NEED_PRICE_NEGOTIATION_WITH_CLIENT = :needPriceNegotiationWithClient");
						paramsMap.put("needPriceNegotiationWithClient", demoTrackerObject.getNeedPriceNegotiationWithClient());
						break;
					}
					case "negotiatedOverrideRateWithClient" : {
						updateAttributesQuery.add("NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT = :negotiatedOverrideRateWithClient");
						paramsMap.put("negotiatedOverrideRateWithClient", demoTrackerObject.getNegotiatedOverrideRateWithClient());
						break;
					}
					case "clientNegotiationRemarks" : {
						updateAttributesQuery.add("CLIENT_NEGOTIATION_REMARKS = :clientNegotiationRemarks");
						paramsMap.put("clientNegotiationRemarks", demoTrackerObject.getClientNegotiationRemarks());
						break;
					}
					case "needPriceNegotiationWithTutor" : {
						updateAttributesQuery.add("NEED_PRICE_NEGOTIATION_WITH_TUTOR = :needPriceNegotiationWithTutor");
						paramsMap.put("needPriceNegotiationWithTutor", demoTrackerObject.getNeedPriceNegotiationWithTutor());
						break;
					}
					case "negotiatedOverrideRateWithTutor" : {
						updateAttributesQuery.add("NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR = :negotiatedOverrideRateWithTutor");
						paramsMap.put("negotiatedOverrideRateWithTutor", demoTrackerObject.getNegotiatedOverrideRateWithTutor());
						break;
					}
					case "tutorNegotiationRemarks" : {
						updateAttributesQuery.add("TUTOR_NEGOTIATION_REMARKS = :tutorNegotiationRemarks");
						paramsMap.put("tutorNegotiationRemarks", demoTrackerObject.getTutorNegotiationRemarks());
						break;
					}
					case "adminRemarks" : {
						updateAttributesQuery.add("ADMIN_REMARKS = :adminRemarks");
						paramsMap.put("adminRemarks", demoTrackerObject.getAdminRemarks());
						break;
					}
					case "adminFinalizingRemarks" : {
						updateAttributesQuery.add("ADMIN_FINALIZING_REMARKS = :adminFinalizingRemarks");
						paramsMap.put("adminFinalizingRemarks", demoTrackerObject.getAdminFinalizingRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("demoTrackerId", demoTrackerObject.getDemoTrackerId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ADMIN_ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
}