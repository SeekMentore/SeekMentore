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
import com.constants.RestMethodConstants;
import com.constants.components.SubscriptionPackageConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Contract;
import com.model.components.PackageAssignment;
import com.model.components.SubscriptionPackage;
import com.model.gridcomponent.GridComponent;
import com.model.mail.MailAttachment;
import com.model.rowmappers.PackageAssignmentRowMapper;
import com.model.rowmappers.SubscriptionPackageRowMapper;
import com.service.QueryMapperService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.GridQueryUtils;
import com.utils.JSONUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.localization.Message;

@Service(BeanConstants.BEAN_NAME_SUBSCRIPTION_PACKAGE_SERVICE)
public class SubscriptionPackageService implements SubscriptionPackageConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private QueryMapperService queryMapperService;
	
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
	
	public List<SubscriptionPackage> getSubscriptionPackageListForTutor(final String grid, final Long tutorId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorId", tutorId);
		final String baseQuery = queryMapperService.getQuerySQL("sales-subscriptionpackage", "selectSubscriptionPackage");
		String existingFilterQueryString = queryMapperService.getQuerySQL("sales-subscriptionpackage", "subscriptionPackageTutorIdFilter");
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
	
	public List<SubscriptionPackage> getSubscriptionPackageListForCustomer(final String grid, final Long customerId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerId", customerId);
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
			updateAttributesQuery.add("ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
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
						paramsMap.put("subscriptionPackageSerialId", subscriptionPackage.getSubscriptionPackageSerialId());
						Integer activePackageAssignment = 0;
						final Map<String, Object> countActivePackageAssignment = applicationDao.find(queryMapperService.getQuerySQL("sales-subscriptionpackage", "countActivePackageAssignmentForSubscriptionPackage"), paramsMap);
						if (ValidationUtils.checkObjectAvailability(countActivePackageAssignment) && ValidationUtils.checkObjectAvailability(countActivePackageAssignment.get("TOTAL_CURRENT_ASSIGNMENTS"))) {
							activePackageAssignment = Integer.valueOf(countActivePackageAssignment.get("TOTAL_CURRENT_ASSIGNMENTS").toString());
						}
						if (activePackageAssignment > 0) {
							messageDesc.append(Message.getMessageFromFile(MESG_PROPERTY_FILE_NAME, SUBSCRIPTION_HAS_RUNNING_ASSIGNMENT)).append(NEW_LINE).append(LINE_BREAK);
							canTakeAction = false;
						}
					}
					if (canTakeAction) {
						final PackageAssignment packageAssignment = new PackageAssignment();
						packageAssignment.setPackageAssignmentSerialId(UUIDGeneratorUtils.generateSerialGUID());
						packageAssignment.setSubscriptionPackageSerialId(subscriptionPackage.getSubscriptionPackageSerialId());
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
	
	private void sendNotificationEmailsForSubscriptionPackageTermination(final List<SubscriptionPackage> subscriptionPackageParamObjectList) {
		// Customer Email
		// Tutor Email
	}
	
	public byte[] downloadSubscriptionPackageContractPdf(final String subscriptionPackageSerialId) throws JAXBException, URISyntaxException, Exception {
		return FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(getFolderPathToUploadContracts(subscriptionPackageSerialId)+"/Contract.pdf");
	}
	
	private byte[] createSubscriptionPackageContractPdf(final SubscriptionPackage subscriptionPackage) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("subscriptionPackage", subscriptionPackage);
		return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(SUBSCRIPTION_PACKAGE_CONTRACT_PDF_PATH, attributes));
	}
	
	@Transactional
	public void updateSubscriptionPackageAssignmentRecord(final PackageAssignment packageAssignmentObject, final List<String> changedAttributes, final User activeUser) {
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
			updateAttributesQuery.add("ACTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("WHO_ACTED = :userId");
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
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
			mailParams = new HashMap<String, Object>();
			// Tutor Email
			mailParams.put(MailConstants.MAIL_PARAM_TO, packageAssignment.getTutorEmail());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "New assignment has been started for your Subscription Package - " + packageAssignment.getSubscriptionPackageSerialId());
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(VELOCITY_TEMPLATES_ASSIGNMENT_STARTED_TUTOR_EMAIL_PATH, attributes));
			mailParamList.add(mailParams);
			
			MailUtils.sendMultipleMimeMessageEmail(mailParamList);
		}
	}
	
	private void sendNotificationEmailsForEndOfPackageAssignment(final List<PackageAssignment> packageAssignmentList) {
		// Customer Email
		// Tutor Email
	}
}
