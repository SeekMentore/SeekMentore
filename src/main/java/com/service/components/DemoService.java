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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.MailConstants;
import com.constants.RestMethodConstants;
import com.constants.components.DemoConstants;
import com.constants.components.SalesConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Demo;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.DemoRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.QueryMapperService;
import com.utils.ApplicationUtils;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.localization.Message;

@Service(BeanConstants.BEAN_NAME_DEMO_SERVICE)
public class DemoService implements DemoConstants, SalesConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@Autowired
	private transient JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}

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
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("tutorSerialId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "tutorSerialId", String.class));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_CUSTOMER_SCHEDULED_DEMO_LIST : {
				paramsMap.put("demoStatus", DEMO_STATUS_SCHEDULED);
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-demo", "demoDemoStatusFilter") 
											+ queryMapperService.getQuerySQL("sales-demo", "demoCurrentCustomerAdditionalFilter")
											+ queryMapperService.getQuerySQL("sales-demo", "demoEnquiryOpenAdditionalFilter");
				paramsMap.put("customerSerialId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "customerSerialId", String.class));
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
	public String insertScheduledDemo (
			final String tutorMapperSerialId, 
			final Long demoDateAndTimeMillis, 
			final User activeUser, 
			final Boolean sendEmails,
			final Boolean isReScheduled,
			final String reschedulingRemarks,
			final Integer reScheduleCount,
			final String rescheduledFromDemoSerialId
	) throws Exception {
		final Date currentTimestamp = new Date();
		final Demo demo = new Demo();
		demo.setDemoSerialId(UUIDGeneratorUtils.generateSerialGUID());
		demo.setTutorMapperSerialId(tutorMapperSerialId);
		demo.setDemoDateAndTimeMillis(demoDateAndTimeMillis);
		demo.setDemoStatus(DEMO_STATUS_SCHEDULED);
		demo.setWhoActed(activeUser.getUserId());
		demo.setAdminActionDateMillis(currentTimestamp.getTime());
		demo.setEntryDateMillis(currentTimestamp.getTime());
		String insertQueryId = "insertDemo";
		if (isReScheduled) {
			demo.setReschedulingRemarks(reschedulingRemarks);
			demo.setReScheduleCount(reScheduleCount + 1);
			demo.setRescheduledFromDemoSerialId(rescheduledFromDemoSerialId);
			insertQueryId = "insertReScheduledDemo";
		}
		applicationDao.executeUpdateWithQueryMapper("sales-demo", insertQueryId, demo);
		if (sendEmails) {
			sendDemoScheduledNotificationEmails(demo.getDemoSerialId());
		}
		return demo.getDemoSerialId();
	}
	
	public void sendDemoScheduledNotificationEmails(final String demoSerialId) throws Exception {
		final Demo demo = getDemo(demoSerialId);
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("demo", demo);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Client Email
		mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getEnquiryEmail());
		mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(demo.getCustomerEmail(), demo.getEnquiryEmail()) ? demo.getCustomerEmail() : null);
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Tutor demo has been scheduled for your enquiry");
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_SCHEDULED_CLIENT_EMAIL_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		// Tutor Email
		mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getTutorEmail());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your demo has been scheduled with Client - " + demo.getCustomerName());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_SCHEDULED_TUTOR_EMAIL_PATH, attributes));
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public Demo getDemo(final String demoSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("demoSerialId", demoSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("sales-demo", "selectDemo")
									+ queryMapperService.getQuerySQL("sales-demo", "demoDemoSerialIdFilter"), paramsMap, new DemoRowMapper());
	}
	
	public Map<String, Boolean> getDemoFormUpdateAndRescheduleStatus(final Demo demo) throws Exception {
		final Map<String, Boolean> securityAccess = new HashMap<String, Boolean>();
		securityAccess.put("demoFormEditMandatoryDisbaled", true);
		securityAccess.put("demoRescheduleMandatoryDisbaled", true);
		securityAccess.put("demoCanSuccessFailDemo", false);
		securityAccess.put("demoCanCancelDemo", false);
		if (!ValidationUtils.checkStringAvailability(demo.getIsEnquiryClosed()) || NO.equals(demo.getIsEnquiryClosed())) {
			if (DEMO_STATUS_SCHEDULED.equals(demo.getDemoStatus())) {
				securityAccess.put("demoFormEditMandatoryDisbaled", false);
				if (ValidationUtils.checkStringAvailability(demo.getDemoOccurred()) && YES.equals(demo.getDemoOccurred())) {
					securityAccess.put("demoCanSuccessFailDemo", true);
				} else {
					securityAccess.put("demoCanCancelDemo", true);
					securityAccess.put("demoRescheduleMandatoryDisbaled", false);
				}
			}
		}
		return securityAccess;
	}
	
	public List<Demo> getDemoList(final List<String> demoSerialIdList) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("demoSerialIdList", demoSerialIdList);
		return applicationDao.findAll(queryMapperService.getQuerySQL("sales-demo", "selectDemo")
										+ queryMapperService.getQuerySQL("sales-demo", "demoDemoSerialIdListFilter"), paramsMap, new DemoRowMapper());
	}
	
	@Transactional
	public String reScheduleDemo(final Demo demoObject, final User activeUser) throws Exception {
		final Demo demo = getDemo(demoObject.getDemoSerialId());
		if (ValidationUtils.checkObjectAvailability(demo)) {
			takeActionOnDemo(BUTTON_ACTION_CANCEL, Arrays.asList(new String[] {demoObject.getDemoSerialId()}), "Re-scheduling demo", activeUser, false);
			final String newDemoSerialId = insertScheduledDemo(demo.getTutorMapperSerialId(), demoObject.getDemoDateAndTimeMillis(), activeUser, false, true, demoObject.getReschedulingRemarks(), demo.getReScheduleCount(), demo.getDemoSerialId());
			sendDemoReScheduledNotificationEmails(demoObject.getDemoSerialId(), newDemoSerialId);
			return newDemoSerialId;
		}
		return Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, INVALID_RESCHEDULE_NEW_DATE_AND_TIME);
	}
	
	public void sendDemoReScheduledNotificationEmails(final String oldDemoSerialId, final String newDemoSerialId) throws Exception {
		final Demo oldDemo = getDemo(oldDemoSerialId);
		final Demo newDemo = getDemo(newDemoSerialId);
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("oldDemo", oldDemo);
		attributes.put("newDemo", newDemo);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Client Email
		mailParams.put(MailConstants.MAIL_PARAM_TO, newDemo.getEnquiryEmail());
		mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(newDemo.getCustomerEmail(), newDemo.getEnquiryEmail()) ? newDemo.getCustomerEmail() : null);
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Tutor demo has been re-scheduled for your enquiry");
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_RESCHEDULED_CLIENT_EMAIL_PATH, attributes));
		mailParamList.add(mailParams);
		mailParams = new HashMap<String, Object>();
		// Tutor Email
		mailParams.put(MailConstants.MAIL_PARAM_TO, newDemo.getTutorEmail());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your demo has been re-scheduled with Client - " + newDemo.getCustomerName());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_RESCHEDULED_TUTOR_EMAIL_PATH, attributes));
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}

	@Transactional
	public void takeActionOnDemo(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		final Date currentTimestamp = new Date();
		Boolean isSuccessOrFailure = false;
		String demoStatus = EMPTY_STRING;
		String isDemoSuccess = EMPTY_STRING;
		switch(button) {
			case BUTTON_ACTION_DEMO_SUCCESS : {
				demoStatus = DEMO_STATUS_SUCCESS;
				isDemoSuccess = YES;
				isSuccessOrFailure = true;
				break;
			}
			case BUTTON_ACTION_DEMO_FAILED : {
				demoStatus = DEMO_STATUS_FAILED;
				isDemoSuccess = NO;
				isSuccessOrFailure = true;
				break;
			}
			case BUTTON_ACTION_CANCEL : {
				demoStatus = DEMO_STATUS_CANCELED;
				break;
			}
		}
		final List<Demo> paramObjectList = new LinkedList<Demo>();
		for (final String demoSerialId : idList) {
			final Demo demo = new Demo();
			demo.setWhoActed(activeUser.getUserId());
			demo.setAdminFinalizingRemarks(comments);
			demo.setDemoStatus(demoStatus);
			if (isSuccessOrFailure) {
				demo.setIsDemoSuccess(isDemoSuccess);
			}
			demo.setAdminActionDateMillis(currentTimestamp.getTime());
			demo.setDemoSerialId(demoSerialId);
			paramObjectList.add(demo);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("sales-demo", "updateDemoStatus", paramObjectList);
		if (isSuccessOrFailure) {
			applicationDao.executeBatchUpdateWithQueryMapper("sales-demo", "updateDemoSuccessFailure", paramObjectList);
		}
		if (sendEmails) {
			switch(button) {
				case BUTTON_ACTION_CANCEL : {
					sendDemoCanceledNotificationEmails(idList);
					break;
				}
				case BUTTON_ACTION_DEMO_SUCCESS : {
					sendDemoSuccessNotificationEmails(idList);
					break;
				}
				case BUTTON_ACTION_DEMO_FAILED : {
					sendDemoFailedNotificationEmails(idList);
					break;
				}
			}
		}
	}
	
	public void sendDemoCanceledNotificationEmails(final List<String> idList) throws Exception {
		if (ValidationUtils.checkNonEmptyList(idList)) {
			final List<Demo> demoList = getDemoList(idList);
			if (ValidationUtils.checkNonEmptyList(demoList)) {
				final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
				for (final Demo demo : demoList) {
					final Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("demo", demo);
					Map<String, Object> mailParams = new HashMap<String, Object>();
					// Client Email
					mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getEnquiryEmail());
					mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(demo.getCustomerEmail(), demo.getEnquiryEmail()) ? demo.getCustomerEmail() : null);
					mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Tutor demo has been cenceled for your enquiry");
					mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_CANCEL_CLIENT_EMAIL_PATH, attributes));
					mailParamList.add(mailParams);
					mailParams = new HashMap<String, Object>();
					// Tutor Email
					mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getTutorEmail());
					mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your demo has been canceled with Client - " + demo.getCustomerName());
					mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_CANCEL_TUTOR_EMAIL_PATH, attributes));
					mailParamList.add(mailParams);
				}
				MailUtils.sendMultipleMimeMessageEmail(mailParamList);
			}
		}
	}
	
	public void sendDemoSuccessNotificationEmails(final List<String> idList) throws Exception {
		final List<Demo> demoList = getDemoList(idList);
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final Demo demo : demoList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("demo", demo);
			Map<String, Object> mailParams = new HashMap<String, Object>();
			// Client Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getEnquiryEmail());
			mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(demo.getCustomerEmail(), demo.getEnquiryEmail()) ? demo.getCustomerEmail() : null);
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Tutor demo was successful for your enquiry");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_SUCCESS_CLIENT_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			mailParams = new HashMap<String, Object>();
			// Tutor Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, demo.getTutorEmail());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your demo was successful with Client - " + demo.getCustomerName());
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_SUCCESS_TUTOR_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
		}
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public void sendDemoFailedNotificationEmails(final List<String> idList) throws Exception {
		final List<Demo> demoList = getDemoList(idList);
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final Demo demo : demoList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("demo", demo);
			Map<String, Object> mailParams = new HashMap<String, Object>();
			// Sales Team Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Demo faild with Client - " + demo.getCustomerName());
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_DEMO_FAILED_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
		}
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}

	@Transactional
	public void updateDemoRecord(final Demo demoObject, final List<String> changedAttributes, final User activeUser) throws Exception {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE DEMO SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE DEMO_SERIAL_ID = :demoSerialId";
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
				}
			}
		}
		paramsMap.put("demoSerialId", demoObject.getDemoSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ADMIN_ACTION_DATE_MILLIS = :adminActionDateMillis");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			paramsMap.put("adminActionDateMillis", currenTimestamp.getTime());
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
	
	public List<Demo> getDemoListForTutorMapperSerialId(final String tutorMapperSerialId, final String demoStatus) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorMapperSerialId", tutorMapperSerialId);
		paramsMap.put("demoStatus", demoStatus);
		return applicationDao.findAll(queryMapperService.getQuerySQL("sales-demo", "selectDemo")
										+ queryMapperService.getQuerySQL("sales-demo", "demoTutorMapperSerialIdAndDemoStatusFilter"), paramsMap, new DemoRowMapper());
	}
}