package com.service.components;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.MailConstants;
import com.constants.MessageConstants;
import com.constants.RestMethodConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.SubscriptionPackageConstants;
import com.dao.ApplicationDao;
import com.model.ApplicationFile;
import com.model.User;
import com.model.components.AssignmentAttendance;
import com.model.components.AssignmentAttendanceDocument;
import com.model.components.Contract;
import com.model.components.PackageAssignment;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.model.mail.MailAttachment;
import com.model.rowmappers.AssignmentAttendanceDocumentRowMapper;
import com.model.rowmappers.AssignmentAttendanceRowMapper;
import com.model.rowmappers.MapRowMapper;
import com.model.rowmappers.PackageAssignmentRowMapper;
import com.model.rowmappers.SubscriptionPackageRowMapper;
import com.model.workbook.WorkbookCell;
import com.model.workbook.WorkbookCell.TypeOfStyleEnum;
import com.model.workbook.WorkbookRecord;
import com.model.workbook.WorkbookReport;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.QueryMapperService;
import com.utils.ApplicationUtils;
import com.utils.DateUtils;
import com.utils.FileSystemUtils;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;
import com.utils.localization.Message;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE)
public class SubscriptionPackageService implements SubscriptionPackageConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private QueryMapperService queryMapperService;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	public List<SubscriptionPackage> getSubscriptionPackageList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageFilter");
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new SubscriptionPackageRowMapper());
	}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForTutor(final String grid, final String tutorSerialId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageTutorSerialIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public List<SubscriptionPackage> getSubscriptionPackageListForCustomer(final String grid, final String customerSerialId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerSerialId", customerSerialId);
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCustomerIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CURRENT_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageCurrentPackageAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HISTORY_SUBSCRIPTION_PACKAGE_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageHistoryPackageAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public SubscriptionPackage getSubscriptionPackage(final String subscriptionPackageSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("subscriptionPackageSerialId", subscriptionPackageSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage")
												+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageSubscriptionPackageIdFilter"), paramsMap, new SubscriptionPackageRowMapper());
	}
	
	public Map<String, Boolean> getSubscriptionPackageFormUpdateAndActionStatus(final SubscriptionPackage subscriptionPackage) throws Exception {
		final Map<String, Boolean> securityAccess = new HashMap<String, Boolean>();
		securityAccess.put("subscriptionPackageFormEditMandatoryDisbaled", true);
		securityAccess.put("subscriptionPackageCanActivateSubscription", false);
		securityAccess.put("subscriptionPackageCanTerminateSubscription", false);
		securityAccess.put("subscriptionPackageCanCreateAssignment", false);
		if (!ValidationUtils.checkNonNegativeNonZeroNumberAvailability(subscriptionPackage.getEndDateMillis())) {
			securityAccess.put("subscriptionPackageFormEditMandatoryDisbaled", false);
			if (ValidationUtils.checkNonNegativeNonZeroNumberAvailability(subscriptionPackage.getStartDateMillis())) {
				final Boolean canCreateAssignment = checkIfPackageAssignmentCouldBeCreatedForSubscriptionPackage(subscriptionPackage.getSubscriptionPackageSerialId());
				securityAccess.put("subscriptionPackageCanCreateAssignment", canCreateAssignment);
				if (canCreateAssignment) {
					securityAccess.put("subscriptionPackageCanTerminateSubscription", true);
				}
			} else {
				securityAccess.put("subscriptionPackageCanActivateSubscription", true);
				securityAccess.put("subscriptionPackageCanTerminateSubscription", true);
			}
		}
		return securityAccess;
	}
	
	public List<PackageAssignment> getSelectedSubscriptionPackageAssignmentList(final String grid, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectPackageAssignment");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentSubscriptionPackageSerialIdFilter");
		paramsMap.put("subscriptionPackageSerialId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "subscriptionPackageSerialId", String.class));
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_CURRENT_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentCurrentAssignmentAdditionalFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTION_PACKAGE_HISTORY_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentHistoryAssignmentAdditionalFilter");
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new PackageAssignmentRowMapper());
	}
	
	@Transactional
	public void updateSubscriptionPackageRecord(final SubscriptionPackage subscriptionPackageObject, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE SUBSCRIPTION_PACKAGE SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE SUBSCRIPTION_PACKAGE_SERIAL_ID = :subscriptionPackageSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "packageBillingType" : {
						updateAttributesQuery.add("PACKAGE_BILLING_TYPE = :packageBillingType");
						paramsMap.put("packageBillingType", subscriptionPackageObject.getPackageBillingType());
						break;
					}
					case "finalizedRateForClient" : {
						updateAttributesQuery.add("FINALIZED_RATE_CLIENT = :finalizedRateForClient");
						paramsMap.put("finalizedRateForClient", subscriptionPackageObject.getFinalizedRateForClient());
						break;
					}
					case "finalizedRateForTutor" : {
						updateAttributesQuery.add("FINALIZED_RATE_TUTOR = :finalizedRateForTutor");
						paramsMap.put("finalizedRateForTutor", subscriptionPackageObject.getFinalizedRateForTutor());
						break;
					}
					case "isCustomerGrieved" : {
						updateAttributesQuery.add("IS_CUSTOMER_GRIEVED = :isCustomerGrieved");
						paramsMap.put("isCustomerGrieved", subscriptionPackageObject.getIsCustomerGrieved());
						break;
					}
					case "customerHappinessIndex" : {
						updateAttributesQuery.add("CUSTOMER_HAPPINESS_INDEX = :customerHappinessIndex");
						paramsMap.put("customerHappinessIndex", subscriptionPackageObject.getCustomerHappinessIndex());
						break;
					}
					case "customerRemarks" : {
						updateAttributesQuery.add("CUSTOMER_REMARKS = :customerRemarks");
						paramsMap.put("customerRemarks", subscriptionPackageObject.getCustomerRemarks());
						break;
					}
					case "isTutorGrieved" : {
						updateAttributesQuery.add("IS_TUTOR_GRIEVED = :isTutorGrieved");
						paramsMap.put("isTutorGrieved", subscriptionPackageObject.getIsTutorGrieved());
						break;
					}
					case "tutorHappinessIndex" : {
						updateAttributesQuery.add("TUTOR_HAPPINESS_INDEX = :tutorHappinessIndex");
						paramsMap.put("tutorHappinessIndex", subscriptionPackageObject.getTutorHappinessIndex());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", subscriptionPackageObject.getTutorRemarks());
						break;
					}
					case "adminRemarks" : {
						updateAttributesQuery.add("ADMIN_REMARKS = :adminRemarks");
						paramsMap.put("adminRemarks", subscriptionPackageObject.getAdminRemarks());
						break;
					}
					case "additionalDetailsClient" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS_CLIENT = :additionalDetailsClient");
						paramsMap.put("additionalDetailsClient", subscriptionPackageObject.getAdditionalDetailsClient());
						break;
					}
					case "additionalDetailsTutor" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS_TUTOR = :additionalDetailsTutor");
						paramsMap.put("additionalDetailsTutor", subscriptionPackageObject.getAdditionalDetailsTutor());
						break;
					}
					case "activatingRemarks" : {
						updateAttributesQuery.add("ACTIVATING_REMARKS = :activatingRemarks");
						paramsMap.put("activatingRemarks", subscriptionPackageObject.getActivatingRemarks());
						break;
					}
					case "terminatingRemarks" : {
						updateAttributesQuery.add("TERMINATING_REMARKS = :terminatingRemarks");
						paramsMap.put("terminatingRemarks", subscriptionPackageObject.getTerminatingRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("subscriptionPackageSerialId", subscriptionPackageObject.getSubscriptionPackageSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ACTION_DATE_MILLIS = :actionDateMillis");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("actionDateMillis", currenTimestamp.getTime());
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	@Transactional
	public String takeActionOnSubscriptionPackage(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		StringBuilder message = new StringBuilder(EMPTY_STRING);
		Integer successCount = 0;
		Integer failureCount = 0;
		final Date currentTimestamp = new Date();
		final Map<String, Object> paramsMap = new HashMap<String, Object>(); 
		paramsMap.put("subscriptionPackageSerialIdList", idList);
		final List<SubscriptionPackage> subscriptionPackageDbRecordList = applicationDao.findAll(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage")
																								+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageSubscriptionPackageIdListFilter"), paramsMap, new SubscriptionPackageRowMapper());
		final List<SubscriptionPackage> subscriptionPackageParamObjectList = new LinkedList<SubscriptionPackage>();
		final List<PackageAssignment> packageAssignmentParamObjectList = new LinkedList<PackageAssignment>();
		final List<Contract> contractParamObjectList = new LinkedList<Contract>();
		for (final SubscriptionPackage subscriptionPackage : subscriptionPackageDbRecordList) {
			subscriptionPackage.setActionDateMillis(currentTimestamp.getTime());
			subscriptionPackage.setWhoActed(activeUser.getUserId());
			subscriptionPackage.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			subscriptionPackage.setUpdatedBy(activeUser.getUserId());
			Boolean canTakeAction = true;
			final StringBuilder messageDesc = new StringBuilder(EMPTY_STRING);
			switch(button) {
				case BUTTON_ACTIVATE_SUBSCRIPTION : {
					if (ValidationUtils.checkObjectAvailability(subscriptionPackage.getStartDateMillis()) && subscriptionPackage.getStartDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_ALREADY_ACTIVE)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					} else if (ValidationUtils.checkObjectAvailability(subscriptionPackage.getEndDateMillis()) & subscriptionPackage.getEndDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_ALREADY_TERMINATED)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					}
					if (canTakeAction) {
						subscriptionPackage.setStartDateMillis(currentTimestamp.getTime());
						subscriptionPackage.setActivatingRemarks(ApplicationUtils.formatRemarksAndComments(comments));
						final Contract contract = new Contract();
						contract.setContractSerialId(UUIDGeneratorUtils.generateSerialGUID());
						contract.setInitiatedDateMillis(currentTimestamp.getTime());
						contract.setContractType(CUSTOMER_SUBSCRIPTION_PACKAGE_CONTRACT);
						contract.setIsActive(YES);
						contract.setFsKey(getFolderPathToUploadContracts(subscriptionPackage.getSubscriptionPackageSerialId())+"/Contract.pdf");
						contract.setRecordLastUpdatedMillis(currentTimestamp.getTime());
						contract.setUpdatedBy(activeUser.getUserId());
						subscriptionPackage.setContract(contract);
						subscriptionPackage.setContractSerialId(contract.getContractSerialId());
						contractParamObjectList.add(contract);
					}
					break;
				}
				case BUTTON_END_SUBSCRIPTION : {
					if (ValidationUtils.checkObjectAvailability(subscriptionPackage.getEndDateMillis()) && subscriptionPackage.getEndDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_ALREADY_TERMINATED)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					}
					if (canTakeAction) {
						subscriptionPackage.setEndDateMillis(currentTimestamp.getTime());
						subscriptionPackage.setTerminatingRemarks(ApplicationUtils.formatRemarksAndComments(comments));
					}
					break;
				}
				case BUTTON_CREATE_ASSIGNMENT_SUBSCRIPTION : {
					if (!ValidationUtils.checkObjectAvailability(subscriptionPackage.getStartDateMillis()) || subscriptionPackage.getStartDateMillis() == 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_NOT_ACTIVE)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					} else {
						canTakeAction = checkIfPackageAssignmentCouldBeCreatedForSubscriptionPackage(subscriptionPackage.getSubscriptionPackageSerialId());
						if (!canTakeAction) {
							messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_HAS_RUNNING_ASSIGNMENT)).append(NEW_LINE).append(LINE_BREAK);
						}
					}
					if (canTakeAction) {
						final PackageAssignment packageAssignment = new PackageAssignment();
						packageAssignment.setPackageAssignmentSerialId(UUIDGeneratorUtils.generateSerialGUID());
						packageAssignment.setSubscriptionPackageSerialId(subscriptionPackage.getSubscriptionPackageSerialId());
						packageAssignment.setCompletedHours(0);
						packageAssignment.setCompletedMinutes(0);
						packageAssignment.setCreatedMillis(currentTimestamp.getTime());
						packageAssignment.setRecordLastUpdatedMillis(currentTimestamp.getTime());
						packageAssignment.setUpdatedBy(activeUser.getUserId());
						packageAssignmentParamObjectList.add(packageAssignment);
					}
					break;
				}
			}
			if (canTakeAction) {
				switch(button) {
					case BUTTON_ACTIVATE_SUBSCRIPTION : 
					case BUTTON_END_SUBSCRIPTION : {
						subscriptionPackageParamObjectList.add(subscriptionPackage);
						break;
					}
				}
				successCount++;
			} else {
				message.append("SubscriptionPackageSerialId : '").append(subscriptionPackage.getSubscriptionPackageSerialId()).append("' Failed. Reason - ").append(messageDesc.toString()).append(NEW_LINE).append(LINE_BREAK);
				failureCount++;
			}
		}
		switch(button) {
			case BUTTON_ACTIVATE_SUBSCRIPTION : {
				if (ValidationUtils.checkNonEmptyList(subscriptionPackageParamObjectList)) {
					if (ValidationUtils.checkNonEmptyList(contractParamObjectList)) {
						applicationDao.executeBatchUpdateWithQueryMapper("contract", "insertContract", contractParamObjectList);
					}
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "activateSubscriptionPackage", subscriptionPackageParamObjectList);
					saveContractPDFInFileSystem(subscriptionPackageParamObjectList);
				}
				break;
			}
			case BUTTON_END_SUBSCRIPTION : {
				if (ValidationUtils.checkNonEmptyList(subscriptionPackageParamObjectList)) {
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "terminateSubscriptionPackage", subscriptionPackageParamObjectList);
				}
				break;
			}
			case BUTTON_CREATE_ASSIGNMENT_SUBSCRIPTION : {
				if (ValidationUtils.checkNonEmptyList(packageAssignmentParamObjectList)) {
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "insertPackageAssignment", packageAssignmentParamObjectList);
				}
				break;
			}
		}
		if (sendEmails) {
			switch(button) {
				case BUTTON_ACTIVATE_SUBSCRIPTION : {
					if (ValidationUtils.checkNonEmptyList(subscriptionPackageParamObjectList)) {
						sendNotificationEmailsForSubscriptionPackageActivation(subscriptionPackageParamObjectList);
					}
					break;
				}
				case BUTTON_END_SUBSCRIPTION : {
					if (ValidationUtils.checkNonEmptyList(subscriptionPackageParamObjectList)) {
						sendNotificationEmailsForSubscriptionPackageTermination(subscriptionPackageParamObjectList);
					}
					break;
				}
			}
		}
		if (!EMPTY_STRING.equals(message.toString())) {
			final String errorMessage = message.toString();
			message = new StringBuilder("'"+ failureCount +"' Subscription Packages failed").append(NEW_LINE).append(LINE_BREAK).append(errorMessage);
		}
		return message.toString();
	}
	
	private Boolean checkIfPackageAssignmentCouldBeCreatedForSubscriptionPackage(final String subscriptionPackageSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>(); 
		paramsMap.put("subscriptionPackageSerialId", subscriptionPackageSerialId);
		Integer activePackageAssignment = 0;
		final Map<String, Object> countActivePackageAssignment = applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "countActivePackageAssignmentForSubscriptionPackage"), paramsMap);
		if (ValidationUtils.checkObjectAvailability(countActivePackageAssignment) && ValidationUtils.checkObjectAvailability(countActivePackageAssignment.get("TOTAL_CURRENT_ASSIGNMENTS"))) {
			activePackageAssignment = Integer.valueOf(countActivePackageAssignment.get("TOTAL_CURRENT_ASSIGNMENTS").toString());
		}
		if (activePackageAssignment > 0) {
			return false;
		}
		return true;
	}
	
	private void saveContractPDFInFileSystem(final List<SubscriptionPackage> subscriptionPackageList) throws Exception {
		for (final SubscriptionPackage subscriptionPackage : subscriptionPackageList) {
			FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(
					getFolderPathToUploadContracts(subscriptionPackage.getSubscriptionPackageSerialId()), "Contract.pdf", createSubscriptionPackageContractPdf(subscriptionPackage));
		}
	}
	
	private String getFolderPathToUploadContracts(final String subscriptionPackageSerialId) {
		return "secured/contracts/customer/subscriptionpackage/" + subscriptionPackageSerialId;
	}
	
	private void sendNotificationEmailsForSubscriptionPackageActivation(final List<SubscriptionPackage> subscriptionPackageList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final SubscriptionPackage subscriptionPackage : subscriptionPackageList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("subscriptionPackage", subscriptionPackage);
			List<MailAttachment> attachments = new ArrayList<MailAttachment>();
			Map<String, Object> mailParams = new HashMap<String, Object>();
			// Client Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, subscriptionPackage.getEnquiryEmail());
			mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(subscriptionPackage.getCustomerEmail(), subscriptionPackage.getEnquiryEmail()) ? subscriptionPackage.getCustomerEmail() : null);
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your requested Subscription Package has been activated");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_SUBSCRIPTION_ACTIVATED_CLIENT_EMAIL_PATH, attributes));
			attachments.add(new MailAttachment("Contract.pdf", subscriptionPackage.getContract().getFsKey(), FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			attachments.add(new MailAttachment("Terms_and_Conditions.pdf", "public_access/termsandconditions/customer/v1/Terms_and_Conditions.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
			mailParamList.add(mailParams);
			mailParams = new HashMap<String, Object>();
			attachments = new ArrayList<MailAttachment>();
			// Tutor Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, subscriptionPackage.getTutorEmail());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "New Subscription Package has been activated for you");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_SUBSCRIPTION_ACTIVATED_TUTOR_EMAIL_PATH, attributes));
			attachments.add(new MailAttachment("Terms_and_Conditions.pdf", "public_access/termsandconditions/tutor/v1/Terms_and_Conditions.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
			mailParamList.add(mailParams);
			
			MailUtils.sendMultipleMimeMessageEmail(mailParamList);
		}
	}
	
	private void sendNotificationEmailsForSubscriptionPackageTermination(final List<SubscriptionPackage> subscriptionPackageParamObjectList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final SubscriptionPackage subscriptionPackage : subscriptionPackageParamObjectList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("subscriptionPackage", subscriptionPackage);
			List<MailAttachment> attachments = new ArrayList<MailAttachment>();
			Map<String, Object> mailParams = new HashMap<String, Object>();
			// Client Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, subscriptionPackage.getEnquiryEmail());
			mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(subscriptionPackage.getCustomerEmail(), subscriptionPackage.getEnquiryEmail()) ? subscriptionPackage.getCustomerEmail() : null);
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your Subscription Package has been terminated");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_CLIENT_EMAIL_PATH, attributes));
			attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
			mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
			mailParamList.add(mailParams);
			// Tutor Email
			mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, subscriptionPackage.getTutorEmail());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Subscription Package terminated");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_TUTOR_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			// Sales Team
			mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Subscription Package terminated");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_SUBSCRIPTION_TERMINATED_SALES_TEAM_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			MailUtils.sendMultipleMimeMessageEmail(mailParamList);
		}
	}
	
	public ApplicationFile downloadSubscriptionPackageContractPdf(final String subscriptionPackageSerialId) throws JAXBException, URISyntaxException, Exception {
		return new ApplicationFile("CONTRACT" + PERIOD + FileConstants.EXTENSION_PDF, FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(getFolderPathToUploadContracts(subscriptionPackageSerialId)+"/Contract.pdf"));
	}
	
	private byte[] createSubscriptionPackageContractPdf(final SubscriptionPackage subscriptionPackage) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("subscriptionPackage", subscriptionPackage);
		return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(SUBSCRIPTION_PACKAGE_CONTRACT_PDF_PATH, attributes));
	}
	
	@Transactional
	public void updateSubscriptionPackageAssignmentRecord(final PackageAssignment packageAssignmentObject, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE PACKAGE_ASSIGNMENT SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE PACKAGE_ASSIGNMENT_SERIAL_ID = :packageAssignmentSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "totalHours" : {
						updateAttributesQuery.add("TOTAL_HOURS = :totalHours");
						paramsMap.put("totalHours", packageAssignmentObject.getTotalHours());
						break;
					}
					case "isCustomerGrieved" : {
						updateAttributesQuery.add("IS_CUSTOMER_GRIEVED = :isCustomerGrieved");
						paramsMap.put("isCustomerGrieved", packageAssignmentObject.getIsCustomerGrieved());
						break;
					}
					case "customerHappinessIndex" : {
						updateAttributesQuery.add("CUSTOMER_HAPPINESS_INDEX = :customerHappinessIndex");
						paramsMap.put("customerHappinessIndex", packageAssignmentObject.getCustomerHappinessIndex());
						break;
					}
					case "customerRemarks" : {
						updateAttributesQuery.add("CUSTOMER_REMARKS = :customerRemarks");
						paramsMap.put("customerRemarks", packageAssignmentObject.getCustomerRemarks());
						break;
					}
					case "isTutorGrieved" : {
						updateAttributesQuery.add("IS_TUTOR_GRIEVED = :isTutorGrieved");
						paramsMap.put("isTutorGrieved", packageAssignmentObject.getIsTutorGrieved());
						break;
					}
					case "tutorHappinessIndex" : {
						updateAttributesQuery.add("TUTOR_HAPPINESS_INDEX = :tutorHappinessIndex");
						paramsMap.put("tutorHappinessIndex", packageAssignmentObject.getTutorHappinessIndex());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", packageAssignmentObject.getTutorRemarks());
						break;
					}
					case "adminRemarks" : {
						updateAttributesQuery.add("ADMIN_REMARKS = :adminRemarks");
						paramsMap.put("adminRemarks", packageAssignmentObject.getAdminRemarks());
						break;
					}
				}
			}
		}
		paramsMap.put("packageAssignmentSerialId", packageAssignmentObject.getPackageAssignmentSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("ACTION_DATE_MILLIS = :actionDateMillis");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("actionDateMillis", currenTimestamp.getTime());
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public PackageAssignment getPackageAssignment(final String packageAssignmentSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("packageAssignmentSerialId", packageAssignmentSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectPackageAssignment")
												+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentPackageAssignmentSerialIdFilter"), paramsMap, new PackageAssignmentRowMapper());
	}
	
	@Transactional
	public String takeActionOnSubscriptionPackageAssignment(final String button, final List<String> idList, final String comments, final User activeUser, final Boolean sendEmails) throws Exception {
		StringBuilder message = new StringBuilder(EMPTY_STRING);
		Integer successCount = 0;
		Integer failureCount = 0;
		final Date currentTimestamp = new Date();
		final Map<String, Object> paramsMap = new HashMap<String, Object>(); 
		paramsMap.put("packageAssignmentSerialIdList", idList);
		final List<PackageAssignment> packageAssignmentDbRecordList = applicationDao.findAll(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectPackageAssignment")
																								+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentPackageAssignmentSerialIdListFilter"), paramsMap, new PackageAssignmentRowMapper());
		final List<PackageAssignment> packageAssignmentParamObjectList = new LinkedList<PackageAssignment>();
		for (final PackageAssignment packageAssignment : packageAssignmentDbRecordList) {
			packageAssignment.setActionDateMillis(currentTimestamp.getTime());
			packageAssignment.setWhoActed(activeUser.getUserId());
			packageAssignment.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			packageAssignment.setUpdatedBy(activeUser.getUserId());
			Boolean canTakeAction = true;
			final StringBuilder messageDesc = new StringBuilder(EMPTY_STRING);
			switch(button) {
				case BUTTON_START_ASSIGNMENT : {
					if (ValidationUtils.checkObjectAvailability(packageAssignment.getStartDateMillis()) && packageAssignment.getStartDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PACKAGE_ASSIGNMENT_ALREADY_RUNNING)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					} else if (ValidationUtils.checkObjectAvailability(packageAssignment.getEndDateMillis()) & packageAssignment.getEndDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PACKAGE_ASSIGNMENT_ALREADY_COMPLETED)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					}
					if (canTakeAction) {
						packageAssignment.setStartDateMillis(currentTimestamp.getTime());
						packageAssignment.setAdminRemarks(ApplicationUtils.appendRemarksAndComments(packageAssignment.getAdminRemarks(), comments));
					}
					break;
				}
				case BUTTON_END_ASSIGNMENT : {
					if (ValidationUtils.checkObjectAvailability(packageAssignment.getEndDateMillis()) && packageAssignment.getEndDateMillis() > 0) {
						messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, PACKAGE_ASSIGNMENT_ALREADY_COMPLETED)).append(NEW_LINE).append(LINE_BREAK);
						canTakeAction = false;
					}
					if (canTakeAction) {
						packageAssignment.setEndDateMillis(currentTimestamp.getTime());
						packageAssignment.setAdminRemarks(ApplicationUtils.appendRemarksAndComments(packageAssignment.getAdminRemarks(), comments));
					}
					break;
				}
			}
			if (canTakeAction) {
				switch(button) {
					case BUTTON_START_ASSIGNMENT : 
					case BUTTON_END_ASSIGNMENT : {
						packageAssignmentParamObjectList.add(packageAssignment);
						break;
					}
				}
				successCount++;
			} else {
				message.append("PackageAssignmentSerialId : '").append(packageAssignment.getPackageAssignmentSerialId()).append("' Failed. Reason - ").append(messageDesc.toString()).append(NEW_LINE).append(LINE_BREAK);
				failureCount++;
			}
		}
		switch(button) {
			case BUTTON_START_ASSIGNMENT : {
				if (ValidationUtils.checkNonEmptyList(packageAssignmentParamObjectList)) {
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "startPackageAssignment", packageAssignmentParamObjectList);
				}
				break;
			}
			case BUTTON_END_ASSIGNMENT : {
				if (ValidationUtils.checkNonEmptyList(packageAssignmentParamObjectList)) {
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "endPackageAssignment", packageAssignmentParamObjectList);
				}
				break;
			}
		}
		if (sendEmails) {
			switch(button) {
				case BUTTON_START_ASSIGNMENT : {
					if (ValidationUtils.checkNonEmptyList(packageAssignmentParamObjectList)) {
						sendNotificationEmailsForStartOfPackageAssignment(packageAssignmentParamObjectList);
					}
					break;
				}
				case BUTTON_END_ASSIGNMENT : {
					if (ValidationUtils.checkNonEmptyList(packageAssignmentParamObjectList)) {
						sendNotificationEmailsForEndOfPackageAssignment(packageAssignmentParamObjectList);
					}
					break;
				}
			}
		}
		if (!EMPTY_STRING.equals(message.toString())) {
			final String errorMessage = message.toString();
			message = new StringBuilder("'"+ failureCount +"' Package Assignments failed").append(NEW_LINE).append(LINE_BREAK).append(errorMessage);
		}
		return message.toString();
	}
	
	private void sendNotificationEmailsForStartOfPackageAssignment(final List<PackageAssignment> packageAssignmentList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final PackageAssignment packageAssignment : packageAssignmentList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("packageAssignment", packageAssignment);
			Map<String, Object> mailParams = new HashMap<String, Object>();
			// Client Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, packageAssignment.getEnquiryEmail());
			mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(packageAssignment.getCustomerEmail(), packageAssignment.getEnquiryEmail()) ? packageAssignment.getCustomerEmail() : null);
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "New assignment has been started for your Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_STARTED_CLIENT_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			// Tutor Email
			mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, packageAssignment.getTutorEmail());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "New assignment has been started for your Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_STARTED_TUTOR_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			
			MailUtils.sendMultipleMimeMessageEmail(mailParamList);
		}
	}
	
	private void sendNotificationEmailsForEndOfPackageAssignment(final List<PackageAssignment> packageAssignmentList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final PackageAssignment packageAssignment : packageAssignmentList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("packageAssignment", packageAssignment);
			// Sales Email
			Map<String, Object> mailParams = new HashMap<String, Object>();
			mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Package Assignment for Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId() +" ended");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_END_SALES_TEAM_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			MailUtils.sendMultipleMimeMessageEmail(mailParamList);
		}
	}
	
	public List<PackageAssignment> getAssignmentList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectPackageAssignment");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentCreatedDateStartDateSorter");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NEW_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentNewAssignmentFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_STARTED_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentStartedAssignmentFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_HOURS_COMPLETED_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentHoursCompletedAssignmentFilter");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REVIEWED_ASSIGNMENT_LIST : {
				existingFilterQueryString += queryMapperService.getQuerySQL("sales-subscriptionpackage", "packageAssignmentReviewedAssignmentFilter");
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new PackageAssignmentRowMapper());
	}
	
	@Transactional
	public void insertAssignmentAttendance(final AssignmentAttendance assignmentAttendance, final PackageAssignment packageAssignment, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		Integer newCompletedHours = (ValidationUtils.checkNonNegativeNumberAvailability(packageAssignment.getCompletedHours()) ? packageAssignment.getCompletedHours() : 0)
									+ (ValidationUtils.checkNonNegativeNumberAvailability(assignmentAttendance.getDurationHours()) ? assignmentAttendance.getDurationHours() : 0);
		Integer newCompletedMinutes = (ValidationUtils.checkNonNegativeNumberAvailability(packageAssignment.getCompletedMinutes()) ? packageAssignment.getCompletedMinutes() : 0)
									+ (ValidationUtils.checkNonNegativeNumberAvailability(assignmentAttendance.getDurationMinutes()) ? assignmentAttendance.getDurationMinutes() : 0);
		if (newCompletedMinutes > 59) {
			newCompletedHours++;
			newCompletedMinutes -= 60;
		}
		packageAssignment.setCompletedHours(newCompletedHours);
		packageAssignment.setCompletedMinutes(newCompletedMinutes);
		packageAssignment.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		packageAssignment.setUpdatedBy("SYSTEM");
		assignmentAttendance.setAssignmentAttendanceSerialId(UUIDGeneratorUtils.generateSerialGUID());
		assignmentAttendance.setPackageAssignmentSerialId(packageAssignment.getPackageAssignmentSerialId());
		assignmentAttendance.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		assignmentAttendance.setUpdatedBy(activeUser.getUserId());
		assignmentAttendance.setUpdatedByUserType(activeUser.getUserType());
		applicationDao.executeUpdateWithQueryMapper("sales-subscriptionpackage", "insertAssignmentAttendance", assignmentAttendance);
		applicationDao.executeUpdateWithQueryMapper("sales-subscriptionpackage", "updateHoursTaughtInPackageAssignment", packageAssignment);
		final String fsKeyPrefix = packageAssignment.getSubscriptionPackageSerialId() + FORWARD_SLASH + packageAssignment.getPackageAssignmentSerialId() + FORWARD_SLASH + assignmentAttendance.getAssignmentAttendanceSerialId();
		insertAssignmentAttendanceDocuments(assignmentAttendance.getDocuments(), assignmentAttendance.getAssignmentAttendanceSerialId(), fsKeyPrefix, activeUser);
		if (newCompletedHours == packageAssignment.getTotalHours()) {
			sendNotificationEmailsForPackageAssignmentHoursCompletion(packageAssignment);
		} else {
			if (packageAssignment.getTotalHours() - newCompletedHours <= 3) {
				sendPackageAssignmentRenewReminderEmails(packageAssignment);
			}
		}
	}
	
	private String getFolderPathToUploadAttendanceDocuments (
			final String fsKeyPrefix, 
			final String documentTypeLabel
	) {
		return "secured/attendance/subscriptionpackage/" + fsKeyPrefix + DASH + "Documents/" + documentTypeLabel;
	}
	
	private String getUniqueFilenameForAttendanceDocument(final String documentTypeLabel, final String filename) {
		return UUIDGeneratorUtils.generateSerialGUID() + UNDERSCORE + documentTypeLabel + filename.substring(filename.lastIndexOf(PERIOD) + 1);
	}
	
	private void insertAssignmentAttendanceDocuments (
			final List<AssignmentAttendanceDocument> documents, 
			final String assignmentAttendanceSerialId, 
			final String fsKeyPrefix, 
			final User activeUser 
	) throws Exception {
		final Date currentTimestamp = new Date();
		if (ValidationUtils.checkNonEmptyList(documents)) {
			for (final AssignmentAttendanceDocument assignmentAttendanceDocument : documents) {
				final String documentTypeLabel = ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP, assignmentAttendanceDocument.getDocumentType());
				assignmentAttendanceDocument.setAssignmentAttendanceDocumentSerialId(UUIDGeneratorUtils.generateSerialGUID());
				assignmentAttendanceDocument.setAssignmentAttendanceSerialId(assignmentAttendanceSerialId);
				assignmentAttendanceDocument.setRecordLastUpdatedMillis(currentTimestamp.getTime());
				assignmentAttendanceDocument.setUpdatedBy(activeUser.getUserId());
				assignmentAttendanceDocument.setUpdatedByUserType(activeUser.getUserType());
				final String fsKey = getFolderPathToUploadAttendanceDocuments(fsKeyPrefix, documentTypeLabel) 
									+ FORWARD_SLASH + getUniqueFilenameForAttendanceDocument(documentTypeLabel, assignmentAttendanceDocument.getFilename());
				assignmentAttendanceDocument.setFsKey(fsKey);
			}
			applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "insertAssignmentAttendanceDocument", documents);
			for (final AssignmentAttendanceDocument assignmentAttendanceDocument : documents) {
				FileSystemUtils.createFileInsideFolderOnApplicationFileSystemUsingKey(assignmentAttendanceDocument.getFsKey(), assignmentAttendanceDocument.getContent());
			}
		}
	}
	
	@Transactional
	public void updateAssignmentAttendance (
			final AssignmentAttendance assignmentAttendance, 
			final PackageAssignment packageAssignmentObject, 
			final Integer additionalHoursTaught,
			final Integer additionalMinutesTaught,
			final List<String> changedAttributes, 
			final User activeUser
	) throws Exception {
		final Date currentTimestamp = new Date();
		final String baseQuery = "UPDATE ASSIGNMENT_ATTENDANCE SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE ASSIGNMENT_ATTENDANCE_SERIAL_ID = :assignmentAttendanceSerialId";
		Boolean timeNotUpdated =  true;
		Boolean isTimeModified = false;
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "entryDateMillis" :
					case "entryTimeMillis" :
					case "exitDateMillis" :
					case "exitTimeMillis" : {
						if (timeNotUpdated) {
							updateAttributesQuery.add("ENTRY_DATE_TIME_MILLIS = :entryDateTimeMillis");
							updateAttributesQuery.add("EXIT_DATE_TIME_MILLIS = :exitDateTimeMillis");
							updateAttributesQuery.add("DURATION_HOURS = :durationHours");
							updateAttributesQuery.add("DURATION_MINUTES = :durationMinutes");
							paramsMap.put("entryDateTimeMillis", assignmentAttendance.getEntryDateTimeMillis());
							paramsMap.put("exitDateTimeMillis", assignmentAttendance.getExitDateTimeMillis());
							paramsMap.put("durationHours", assignmentAttendance.getDurationHours());
							paramsMap.put("durationMinutes", assignmentAttendance.getDurationMinutes());
							isTimeModified = true;
							timeNotUpdated = false;
						}
						break;
					}
					case "topicsTaught" : {
						updateAttributesQuery.add("TOPICS_TAUGHT = :topicsTaught");
						paramsMap.put("topicsTaught", assignmentAttendance.getTopicsTaught());
						break;
					}
					case "isClassworkProvided" : {
						updateAttributesQuery.add("CLASSWORK_PROVIDED = :isClassworkProvided");
						paramsMap.put("isClassworkProvided", assignmentAttendance.getIsClassworkProvided());
						break;
					}
					case "isHomeworkProvided" : {
						updateAttributesQuery.add("HOMEWORK_PROVIDED = :isHomeworkProvided");
						paramsMap.put("isHomeworkProvided", assignmentAttendance.getIsHomeworkProvided());
						break;
					}
					case "isTestProvided" : {
						updateAttributesQuery.add("TEST_PROVIDED = :isTestProvided");
						paramsMap.put("isTestProvided", assignmentAttendance.getIsTestProvided());
						break;
					}
					case "tutorRemarks" : {
						updateAttributesQuery.add("TUTOR_REMARKS = :tutorRemarks");
						paramsMap.put("tutorRemarks", assignmentAttendance.getTutorRemarks());
						break;
					}
					case "tutorPunctualityIndex" : {
						updateAttributesQuery.add("TUTOR_PUNCTUALITY_INDEX = :tutorPunctualityIndex");
						paramsMap.put("tutorPunctualityIndex", assignmentAttendance.getTutorPunctualityIndex());
						break;
					}
					case "punctualityRemarks" : {
						updateAttributesQuery.add("PUNCTUALITY_REMARKS = :punctualityRemarks");
						paramsMap.put("punctualityRemarks", assignmentAttendance.getPunctualityRemarks());
						break;
					}
					case "tutorExpertiseIndex" : {
						updateAttributesQuery.add("TUTOR_EXPERTISE_INDEX = :tutorExpertiseIndex");
						paramsMap.put("tutorExpertiseIndex", assignmentAttendance.getTutorExpertiseIndex());
						break;
					}
					case "expertiseRemarks" : {
						updateAttributesQuery.add("EXPERTISE_REMARKS = :expertiseRemarks");
						paramsMap.put("expertiseRemarks", assignmentAttendance.getExpertiseRemarks());
						break;
					}
					case "tutorKnowledgeIndex" : {
						updateAttributesQuery.add("TUTOR_KNOWLEDGE_INDEX = :tutorKnowledgeIndex");
						paramsMap.put("tutorKnowledgeIndex", assignmentAttendance.getTutorKnowledgeIndex());
						break;
					}
					case "knowledgeRemarks" : {
						updateAttributesQuery.add("KNOWLEDGE_REMARKS = :knowledgeRemarks");
						paramsMap.put("knowledgeRemarks", assignmentAttendance.getKnowledgeRemarks());
						break;
					}
					case "studentRemarks" : {
						updateAttributesQuery.add("STUDENT_REMARKS = :studentRemarks");
						paramsMap.put("studentRemarks", assignmentAttendance.getStudentRemarks());
						break;
					}
					case "documents" : {
						final String fsKeyPrefix = packageAssignmentObject.getSubscriptionPackageSerialId() + FORWARD_SLASH + packageAssignmentObject.getPackageAssignmentSerialId() + FORWARD_SLASH + assignmentAttendance.getAssignmentAttendanceSerialId();
						insertAssignmentAttendanceDocuments(assignmentAttendance.getDocuments(), assignmentAttendance.getAssignmentAttendanceSerialId(), fsKeyPrefix, activeUser);
						break;
					}
				}
			}
		}
		paramsMap.put("assignmentAttendanceSerialId", assignmentAttendance.getAssignmentAttendanceSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			assignmentAttendance.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			assignmentAttendance.setUpdatedBy(activeUser.getUserId());
			assignmentAttendance.setUpdatedByUserType(activeUser.getUserType());
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :updatedBy");
			updateAttributesQuery.add("UPDATED_BY_USER_TYPE = :updatedByUserType");
			paramsMap.put("recordLastUpdatedMillis", assignmentAttendance.getRecordLastUpdatedMillis());
			paramsMap.put("updatedBy", assignmentAttendance.getUpdatedBy());
			paramsMap.put("updatedByUserType", assignmentAttendance.getUpdatedByUserType());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
			if (isTimeModified) {
				Integer newCompletedHours = (ValidationUtils.checkNonNegativeNumberAvailability(packageAssignmentObject.getCompletedHours()) ? packageAssignmentObject.getCompletedHours() : 0)
						+ (ValidationUtils.checkNonNegativeNumberAvailability(additionalHoursTaught) ? additionalHoursTaught : 0);
				Integer newCompletedMinutes = (ValidationUtils.checkNonNegativeNumberAvailability(packageAssignmentObject.getCompletedMinutes()) ? packageAssignmentObject.getCompletedMinutes() : 0)
						+ (ValidationUtils.checkNonNegativeNumberAvailability(additionalMinutesTaught) ? additionalMinutesTaught : 0);
				if (newCompletedMinutes > 59) {
					newCompletedHours++;
					newCompletedMinutes -= 60;
				}
				packageAssignmentObject.setCompletedHours(newCompletedHours);
				packageAssignmentObject.setCompletedMinutes(newCompletedMinutes);
				packageAssignmentObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
				packageAssignmentObject.setUpdatedBy("SYSTEM");
				applicationDao.executeUpdateWithQueryMapper("sales-subscriptionpackage", "updateHoursTaughtInPackageAssignment", packageAssignmentObject);
				if (newCompletedHours == packageAssignmentObject.getTotalHours()) {
					sendNotificationEmailsForPackageAssignmentHoursCompletion(packageAssignmentObject);
				} else {
					if (packageAssignmentObject.getTotalHours() - newCompletedHours <= 3) {
						sendPackageAssignmentRenewReminderEmails(packageAssignmentObject);
					}
				}
			}
		}
	}
	
	private void sendNotificationEmailsForPackageAssignmentHoursCompletion(final PackageAssignment packageAssignment) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("packageAssignment", packageAssignment);
		List<MailAttachment> attachments = new ArrayList<MailAttachment>();
		attachments.add(new MailAttachment("Attendance Tracker.xlsx", createWorkbookReportForAttendanceTracker(packageAssignment), FileConstants.APPLICATION_TYPE_OCTET_STEAM));
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Client Email
		mailParams.put(MailConstants.MAIL_PARAM_TO, packageAssignment.getEnquiryEmail());
		mailParams.put(MailConstants.MAIL_PARAM_CC, !ApplicationUtils.verifySameObjectWithNullCheck(packageAssignment.getCustomerEmail(), packageAssignment.getEnquiryEmail()) ? packageAssignment.getCustomerEmail() : null);
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Assignment hours have been completed for your Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_CLIENT_EMAIL_PATH, attributes));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		// Tutor Email
		mailParams = new HashMap<String, Object>();
		mailParams.put(MailConstants.MAIL_PARAM_TO, packageAssignment.getTutorEmail());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Assignment hours have been completed for your Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_TUTOR_EMAIL_PATH, attributes));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		// Sales Email
		mailParams = new HashMap<String, Object>();
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Assignment hours have been completed for Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_HOURS_COMPLETED_SALES_TEAM_EMAIL_PATH, attributes));
		mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	private void sendPackageAssignmentRenewReminderEmails(final PackageAssignment packageAssignment) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		final Map<String, Object> attributes = new HashMap<String, Object>();
		final String completedDuration = packageAssignment.getCompletedHours().toString() + WHITESPACE + "h" + WHITESPACE + WHITESPACE + packageAssignment.getCompletedMinutes().toString() + WHITESPACE + "m";
		final Map<String, Object> remainingTime = ApplicationUtils.calculateRemainingHoursMinutesSecondsFromTotalAndCompletedHoursMinutesSeconds(packageAssignment.getTotalHours(), 0, 0, packageAssignment.getCompletedHours(), packageAssignment.getCompletedMinutes(), 0);
		final String remainingDuration = remainingTime.get("remainingHours").toString() + WHITESPACE + "h" + WHITESPACE + WHITESPACE + remainingTime.get("remainingMinutes").toString() + WHITESPACE + "m";
		attributes.put("packageAssignment", packageAssignment);
		attributes.put("completedDuration", completedDuration);
		attributes.put("remainingDuration", remainingDuration);
		Map<String, Object> mailParams = new HashMap<String, Object>();
		// Sales Team Email 
		mailParams.put(MailConstants.MAIL_PARAM_TO, jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSalesDeptMailList());
		mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Assignment hours have been completed for Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
		mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_RENEWAL_NOTIFICATION_SALES_TEAM_EMAIL_PATH, attributes));
		mailParamList.add(mailParams);
		
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public List<AssignmentAttendance> getAssignmentAttendanceList(final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendance");
		final String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendancePackageAssignmentSerialIdFilter");
		final String existingSorterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendanceEntryDateSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("packageAssignmentSerialId", JSONUtils.getValueFromJSONObject(gridComponent.getOtherParamsAsJSONObject(), "packageAssignmentSerialId", String.class));
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new AssignmentAttendanceRowMapper());
	}
	
	public List<AssignmentAttendance> getAssignmentAttendanceList(final String packageAssignmentSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("packageAssignmentSerialId", packageAssignmentSerialId);
		return applicationDao.findAll(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendance")
												+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendancePackageAssignmentSerialIdFilter")
												+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendanceEntryDateSorter"), paramsMap, new AssignmentAttendanceRowMapper());
	}
	
	public ApplicationFile downloadAttendanceTrackerSheet(final String packageAssignmentSerialId) throws Exception {
		return new ApplicationFile("Attendance_Sheet_" + packageAssignmentSerialId + PERIOD + FileConstants.EXTENSION_XLSX, createWorkbookReportForAttendanceTracker(getPackageAssignment(packageAssignmentSerialId)));
	}
	
	private byte[] createWorkbookReportForAttendanceTracker(final PackageAssignment packageAssignment) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("Attendance", createAttendanceTracker(packageAssignment));
		workbookReport.setDefaultCellWidth(4500);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public List<WorkbookRecord> createAttendanceTracker(final PackageAssignment packageAssignment) throws Exception {
		final List<WorkbookRecord> workbookRecords = new LinkedList<WorkbookRecord>();
		if (ValidationUtils.checkObjectAvailability(packageAssignment)) {
			final String imageServerURL = Message.getMessage(MessageConstants.IMAGE_SERVER_URL);
			final String logoImageURL = imageServerURL + "/images/company-logo-complete.png";
			final String tagLine = "One Stop Solution to All Tutoring Problems";
			final String reportDesc = "Tutor Invoice and Attendance Tracker";
			final String customerSerialId = "Customer Serial Id - " + packageAssignment.getCustomerSerialId().toString();                                                                         
			final String customerName = packageAssignment.getCustomerName();
			final String customerContactNumber = packageAssignment.getCustomerContactNumber();
			final String customerEmail = packageAssignment.getCustomerEmail();
			final String tutorSerialId = "Tutor Serial Id - " + packageAssignment.getTutorSerialId().toString();                                                                         
			final String tutorName = packageAssignment.getTutorName();
			final String tutorContactNumber = packageAssignment.getTutorContactNumber();
			final String tutorEmail = packageAssignment.getTutorEmail();
			final String packageSerialId = "Package Serial Id - " + packageAssignment.getSubscriptionPackageSerialId();
			final String enquiryGrade = ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, packageAssignment.getEnquiryGrade());
			final String enquirySubject = ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, packageAssignment.getEnquirySubject());
			final String assignmentSerialId = "Assignment Serial Id - " + packageAssignment.getPackageAssignmentSerialId();
			final String startDate = DateUtils.parseDateInSpecifiedFormatAfterConvertingToIndianTimeZone(packageAssignment.getStartDateMillis(), "dd-MMM-yyyy");
			final String endDate = DateUtils.parseDateInSpecifiedFormatAfterConvertingToIndianTimeZone(packageAssignment.getEndDateMillis(), "dd-MMM-yyyy");
			final String totalDuration = packageAssignment.getTotalHours().toString() + WHITESPACE + "h";
			final String completedDuration = packageAssignment.getCompletedHours().toString() + WHITESPACE + "h" + WHITESPACE + WHITESPACE + packageAssignment.getCompletedMinutes().toString() + WHITESPACE + "m";
			final Map<String, Object> remainingTime = ApplicationUtils.calculateRemainingHoursMinutesSecondsFromTotalAndCompletedHoursMinutesSeconds(packageAssignment.getTotalHours(), 0, 0, packageAssignment.getCompletedHours(), packageAssignment.getCompletedMinutes(), 0);
			final String remainingDuration = remainingTime.get("remainingHours").toString() + WHITESPACE + "h" + WHITESPACE + WHITESPACE + remainingTime.get("remainingMinutes").toString() + WHITESPACE + "m";
			final String attendanceTrackerInformationLine1 = "1. Payout will be made on every Wednesday, Cut-off day for 'Wednesday-payouts' will be preceding Sunday";
			final String attendanceTrackerInformationLine2 = "2. Academic Quality, Punctuality and Overall rating columns are filled by parent";
			final String attendanceTrackerInformationLine3 = "3. Completion of package is mandatory for payout, SeekMentore will not be able to process any partial payments";
			final String attendanceTrackerInformationLine4 = "4. In order to have hassle free payout , kindly adhere to above instructions";
			final String attendanceTrackerInformationLine5 = "5. Kindly don’t extend number of sessions or hours, beyond the finalized terms. SeekMentore will not be liable to pay for any additional hour";
			
			List<WorkbookCell> workbookCells = null;
			WorkbookRecord workbookRecord = null;
			// 1st Row - Image Banner
			workbookCells = new LinkedList<WorkbookCell>();
			WorkbookCell workbookCell = new WorkbookCell(EMPTY_STRING, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_DARK_BLUE, TypeOfStyleEnum.BORDER_THIN_DARK_BLUE}, true, 6, 4);
			workbookCell.setImage(logoImageURL, null, null, null, 5, 4, null);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(tagLine, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_DARK_BLUE, TypeOfStyleEnum.FONT_COLOR_WHITE, TypeOfStyleEnum.BORDER_THIN_DARK_BLUE}, true, 6, 4);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 2nd Row - Sheet Description
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(reportDesc, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 3rd Row - Customer Id & Package Id
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(customerSerialId, true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(packageSerialId, true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 4th Row - Customer Name & Enquiry Grade
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(customerName, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Grade", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(enquiryGrade, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 5th Row - Customer Contact Number & Enquiry Subject
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(customerContactNumber, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Subject", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(enquirySubject, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 6th Row - Customer Email
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Email Id", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(customerEmail, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(EMPTY_STRING, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.DEFAULT_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 7th Row - Assignment Id
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(EMPTY_STRING, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.DEFAULT_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(assignmentSerialId, true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 8th Row - Tutor Id, Assignment Start Date & End Date
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(tutorSerialId, true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Start date", true, TypeOfStyleEnum.BOLD_HEADER_CELL);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(startDate, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("End date", true, TypeOfStyleEnum.BOLD_HEADER_CELL);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(endDate, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 9th Row - Tutor Name & Assignment Total Duration
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(tutorName, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Total Duration", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(totalDuration, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 10th Row - Tutor Contact Number & Assignment Completed Duration
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(tutorContactNumber, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Completed Duration", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_GREEN, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(completedDuration, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_GREEN, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 11th Row - Tutor Email & Assignment Remaining Duration
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell("Email id", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(tutorEmail, true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell("Remaining Duration", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_RED, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookCell = new WorkbookCell(remainingDuration, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_RED, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 12th Row - Separator
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(EMPTY_STRING, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 13th Row - All Attendance Record Headers + Values
			workbookRecords.addAll(WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getAssignmentAttendanceList(packageAssignment.getPackageAssignmentSerialId()), AssignmentAttendance.class, "ATTENDANCE_TRACKER_SHEET"));
			// 14th Row - Separator
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(EMPTY_STRING, true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// 15th Row - Gap
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(EMPTY_STRING, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// Attendance Tracker Information Lines - Line 1
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(attendanceTrackerInformationLine1, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// Attendance Tracker Information Lines - Line 2
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(attendanceTrackerInformationLine2, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// Attendance Tracker Information Lines - Line 3
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(attendanceTrackerInformationLine3, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// Attendance Tracker Information Lines - Line 4
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(attendanceTrackerInformationLine4, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
			// Attendance Tracker Information Lines - Line 5
			workbookCells = new LinkedList<WorkbookCell>();
			workbookCell = new WorkbookCell(attendanceTrackerInformationLine5, true, 12, 1);
			workbookCells.add(workbookCell);
			workbookRecord = new WorkbookRecord(workbookCells);
			workbookRecords.add(workbookRecord);
		}
		return workbookRecords;
	}

	public Map<String, Boolean> getPackageAssignmentFormUpdateAndActionStatusAssignmentMarkAndUpdateAttendanceFormDisabledStatus(final PackageAssignment packageAssignment) {
		final Map<String, Boolean> securityAccess = new HashMap<String, Boolean>();
		securityAccess.put("assignmentMarkAndUpdateAttendanceFormEditDisbaled", true);
		securityAccess.put("packageAssignmentFormEditMandatoryDisbaled", true);
		securityAccess.put("packageAssignmentCanStartAssignment", false);
		securityAccess.put("packageAssignmentCanReviewCompleteAssignment", false);
		if (!ValidationUtils.checkNonNegativeNonZeroNumberAvailability(packageAssignment.getEndDateMillis())) {
			securityAccess.put("packageAssignmentFormEditMandatoryDisbaled", false);
			securityAccess.put("packageAssignmentCanReviewCompleteAssignment", true);
			if (ValidationUtils.checkNonNegativeNonZeroNumberAvailability(packageAssignment.getStartDateMillis())) {
				if (packageAssignment.getCompletedHours() < packageAssignment.getTotalHours()) {
					securityAccess.put("assignmentMarkAndUpdateAttendanceFormEditDisbaled", false);
				}
			} else {
				securityAccess.put("packageAssignmentCanStartAssignment", true);
			}
		}
		return securityAccess;
	}
	
	public Map<String, Object> getAssignmentAttendanceUploadedDocumentCountAndExistence(final String assignmentAttendanceSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("assignmentAttendanceSerialId", assignmentAttendanceSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendanceUploadedDocumentCountAndExistence"), paramsMap, new MapRowMapper());
	}
	
	public ApplicationFile downloadAssignmentAttendanceDocumentFile(final String assignmentAttendanceSerialId, final String documentType) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("assignmentAttendanceSerialId", assignmentAttendanceSerialId);
		switch(documentType) {
			case "classwork" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_CLASSWORK);
				break;
			}
			case "homework" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_HOMEWORK);
				break;
			}
			case "test" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_TEST);
				break;
			}
			case "other" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_OTHER);
				break;
			}
		}
		final AssignmentAttendanceDocument assignmentAttendanceDocument = applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendanceDocument")
																							+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendanceDocumentAssignmentSerialIdAndDocumentTypeFilter"), paramsMap, new AssignmentAttendanceDocumentRowMapper());
		if (ValidationUtils.checkObjectAvailability(assignmentAttendanceDocument)) {
			return new ApplicationFile(assignmentAttendanceDocument.getFilename(), FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(assignmentAttendanceDocument.getFsKey()));
		}
		return null;
	}
	
	@Transactional
	public void removeAssignmentAttendanceDocumentFile(final String assignmentAttendanceSerialId, final String documentType, final User activeUser) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("assignmentAttendanceSerialId", assignmentAttendanceSerialId);
		switch(documentType) {
			case "classwork" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_CLASSWORK);
				break;
			}
			case "homework" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_HOMEWORK);
				break;
			}
			case "test" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_TEST);
				break;
			}
			case "other" : {
				paramsMap.put("documentType", ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_OTHER);
				break;
			}
		}
		final AssignmentAttendanceDocument assignmentAttendanceDocument = applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendanceDocument")
																							+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendanceDocumentAssignmentSerialIdAndDocumentTypeFilter"), paramsMap, new AssignmentAttendanceDocumentRowMapper());
		if (ValidationUtils.checkObjectAvailability(assignmentAttendanceDocument)) {
			paramsMap.put("assignmentAttendanceDocumentSerialId", assignmentAttendanceDocument.getAssignmentAttendanceDocumentSerialId());
			applicationDao.executeUpdate(queryMapperService.getQuerySQL("sales-subscriptionpackage", "deleteAssignmentAttendanceDocumentByDocumentSerialIdFilter"), paramsMap);
			FileSystemUtils.deleteFileInFolderOnApplicationFileSystemUsingKey(assignmentAttendanceDocument.getFsKey(), activeUser);
		}
	}

	public List<ApplicationFile> downloadAssignmentAttendanceAllDocuments(final String assignmentAttendanceSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("assignmentAttendanceSerialId", assignmentAttendanceSerialId);
		final List<AssignmentAttendanceDocument> assignmentAttendanceDocumentList = applicationDao.findAll(queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectAssignmentAttendanceDocument")
																											+ queryMapperService.getQuerySQL("sales-subscriptionpackage", "assignmentAttendanceDocumentAssignmentSerialIdFilter"), paramsMap, new AssignmentAttendanceDocumentRowMapper());
		if (ValidationUtils.checkNonEmptyList(assignmentAttendanceDocumentList)) {
			final List<ApplicationFile> applicationFiles = new LinkedList<ApplicationFile>();
			for (final AssignmentAttendanceDocument assignmentAttendanceDocument : assignmentAttendanceDocumentList) {
				applicationFiles.add(new ApplicationFile(getQualifiedFilenameForAssignmentAttendanceDocument(assignmentAttendanceDocument), 
															FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(assignmentAttendanceDocument.getFsKey())));
			}
			return applicationFiles;
		}
		return null;
	}
	
	private String getQualifiedFilenameForAssignmentAttendanceDocument(final AssignmentAttendanceDocument assignmentAttendanceDocument) {
		return ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP, assignmentAttendanceDocument.getDocumentType()) + BACKWARD_SLASH + assignmentAttendanceDocument.getFilename();
	}
}
