package com.service.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.DemoConstants;
import com.constants.components.SalesConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Demo;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.DemoRowMapper;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_DEMO_SERVICE)
public class DemoService implements DemoConstants, SalesConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}

	/*public void sendDemoSuccessNotificationEmails(final Long demoTrackerId) throws Exception {
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
	}*/
	
	/**
	 * @throws Exception ********************************************************************************************/
	public List<Demo> getDemoList(final String grid, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-demo", "selectDemo");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoEntryDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_TUTOR_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter") 
											+ queryMapperService.getQuerySQL("sales-demo", "demoCurrentTutorAdditionalFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoCurrentCustomerAdditionalFilter");
				paramsMap.put("tutorId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter") 
											+ queryMapperService.getQuerySQL("sales-demo", "demoCurrentCustomerAdditionalFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoCurrentCustomerAdditionalFilter");
				paramsMap.put("customerId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerId", Long.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SCHEDULED_DEMO_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_FAILED_DEMO_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CANCELED_DEMO_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("demoStatus", DEMO_STATUS_CANCELED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ENQUIRY_CLOSED_DEMO_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoEnquiryClosedFilter");
				paramsMap.put("demoStatus", DEMO_STATUS_CANCELED);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new DemoRowMapper());
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
		final Demo demo = new Demo();
		demo.setTutorMapperId(tutorMapperId);
		demo.setDemoDateAndTimeMillis(demoDateAndTimeMillis);
		demo.setDemoStatus(DEMO_STATUS_SCHEDULED);
		demo.setWhoActed(activeUser.getUserId());
		demo.setAdminActionDateMillis(currentTimestamp.getTime());
		demo.setEntryDateMillis(currentTimestamp.getTime());
		String insertQueryId = "insertDemo";
		if (isReScheduled) {
			demo.setReschedulingRemarks(reschedulingRemarks);
			demo.setReScheduleCount(reScheduleCount + 1);
			insertQueryId = "insertReScheduledDemo";
		}
		final Long demoTrackerId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("sales-demo", insertQueryId, demo);
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
	public void reScheduleDemo(final Demo demoObject, final User activeUser) throws Exception {
		final List<String> idList = new ArrayList<String>();
		idList.add(String.valueOf(demoObject.getDemoTrackerId()));
		takeActionOnDemo(BUTTON_ACTION_CANCEL, idList, "Re-scheduling demo", activeUser, false);
		final Long newDemoTrackerId = insertScheduledDemo(demoObject.getTutorMapperId(), demoObject.getDemoDateAndTimeMillis(), activeUser, false, true, demoObject.getReschedulingRemarks(), demoObject.getReScheduleCount());
		sendDemoReScheduledNotificationEmails(demoObject.getDemoTrackerId(), newDemoTrackerId);
	}
	
	public void sendDemoReScheduledNotificationEmails(final Long oldDemoTrackerId, final Long newDemoTrackerId) throws Exception {
		
	}

	@Transactional
	public void takeActionOnDemo(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		final Date currentTimestamp = new Date();
		String demoStatus = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_CANCEL : {
				demoStatus = DEMO_STATUS_CANCELED;
				break;
			}
		}
		final List<Demo> paramObjectList = new LinkedList<Demo>();
		for (final String demoTrackerId : idList) {
			final Demo demo = new Demo();
			demo.setWhoActed(activeUser.getUserId());
			demo.setAdminFinalizingRemarks(comments);
			demo.setDemoStatus(demoStatus);
			demo.setAdminActionDateMillis(currentTimestamp.getTime());
			demo.setDemoTrackerId(Long.valueOf(demoTrackerId));
			paramObjectList.add(demo);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-demo", "updateDemoStatus", paramObjectList);
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
	public void updateDemoRecord(final Demo demoObject, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE DEMO SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE DEMO_TRACKER_ID = :demoTrackerId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "demoOccurred" : {
						updateAttributesQuery.add("DEMO_OCCURRED = :demoOccurred");
						paramsMap.put("demoOccurred", demoObject.getDemoOccurred());
						break;
					}
					case "clientSatisfiedFromTutor" : {
						updateAttributesQuery.add("CLIENT_SATISFIED_FROM_TUTOR = :clientSatisfiedFromTutor");
						paramsMap.put("clientSatisfiedFromTutor", demoObject.getClientSatisfiedFromTutor());
						break;
					}
					case "clientRemarks" : {
						updateAttributesQuery.add("CLIENT_REMARKS = :clientRemarks");
						paramsMap.put("clientRemarks", demoObject.getClientRemarks());
						break;
					}
					case "tutorSatisfiedWithClient" : {
						updateAttributesQuery.add("TUTOR_SATISFIED_WITH_CLIENT = :tutorSatisfiedWithClient");
						paramsMap.put("tutorSatisfiedWithClient", demoObject.getTutorSatisfiedWithClient());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", demoObject.getTutorRemarks());
						break;
					}
					case "adminSatisfiedFromTutor" : {
						updateAttributesQuery.add("ADMIN_SATISFIED_FROM_TUTOR = :adminSatisfiedFromTutor");
						paramsMap.put("adminSatisfiedFromTutor", demoObject.getAdminSatisfiedFromTutor());
						break;
					}
					case "adminSatisfiedWithClient" : {
						updateAttributesQuery.add("ADMIN_SATISFIED_WITH_CLIENT = :adminSatisfiedWithClient");
						paramsMap.put("adminSatisfiedWithClient", demoObject.getAdminSatisfiedWithClient());
						break;
					}
					case "isDemoSuccess" : {
						updateAttributesQuery.add("IS_DEMO_SUCCESS = :isDemoSuccess");
						updateAttributesQuery.add("DEMO_STATUS = :demoStatus");
						paramsMap.put("isDemoSuccess", demoObject.getIsDemoSuccess());
						if (YES.equals(demoObject.getIsDemoSuccess())) {
							paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
						} else {
							paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
						}
						break;
					}
					case "needPriceNegotiationWithClient" : {
						updateAttributesQuery.add("NEED_PRICE_NEGOTIATION_WITH_CLIENT = :needPriceNegotiationWithClient");
						paramsMap.put("needPriceNegotiationWithClient", demoObject.getNeedPriceNegotiationWithClient());
						break;
					}
					case "negotiatedOverrideRateWithClient" : {
						updateAttributesQuery.add("NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT = :negotiatedOverrideRateWithClient");
						paramsMap.put("negotiatedOverrideRateWithClient", demoObject.getNegotiatedOverrideRateWithClient());
						break;
					}
					case "clientNegotiationRemarks" : {
						updateAttributesQuery.add("CLIENT_NEGOTIATION_REMARKS = :clientNegotiationRemarks");
						paramsMap.put("clientNegotiationRemarks", demoObject.getClientNegotiationRemarks());
						break;
					}
					case "needPriceNegotiationWithTutor" : {
						updateAttributesQuery.add("NEED_PRICE_NEGOTIATION_WITH_TUTOR = :needPriceNegotiationWithTutor");
						paramsMap.put("needPriceNegotiationWithTutor", demoObject.getNeedPriceNegotiationWithTutor());
						break;
					}
					case "negotiatedOverrideRateWithTutor" : {
						updateAttributesQuery.add("NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR = :negotiatedOverrideRateWithTutor");
						paramsMap.put("negotiatedOverrideRateWithTutor", demoObject.getNegotiatedOverrideRateWithTutor());
						break;
					}
					case "tutorNegotiationRemarks" : {
						updateAttributesQuery.add("TUTOR_NEGOTIATION_REMARKS = :tutorNegotiationRemarks");
						paramsMap.put("tutorNegotiationRemarks", demoObject.getTutorNegotiationRemarks());
						break;
					}
					case "adminRemarks" : {
						updateAttributesQuery.add("ADMIN_REMARKS = :adminRemarks");
						paramsMap.put("adminRemarks", demoObject.getAdminRemarks());
						break;
					}
					case "adminFinalizingRemarks" : {
						updateAttributesQuery.add("ADMIN_FINALIZING_REMARKS = :adminFinalizingRemarks");
						paramsMap.put("adminFinalizingRemarks", demoObject.getAdminFinalizingRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("demoTrackerId", demoObject.getDemoTrackerId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ADMIN_ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<Demo> getSuccessfullDemoList(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, Demo.class);
		} else {
			gridComponent = new GridComponent(Demo.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("sales-demo", "successfullDemoSubscriptionPendingFilter"));
		return getDemoList(RestMethodConstants.REST_METHOD_NAME_SUCCESSFUL_DEMO_LIST, gridComponent);
	}
}