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
import com.constants.FileConstants;
import com.constants.MailConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.CustomerConstants;
import com.dao.ApplicationDao;
import com.model.ApplicationFile;
import com.model.User;
import com.model.components.SubscribedCustomer;
import com.model.components.publicaccess.FindTutor;
import com.model.gridcomponent.GridComponent;
import com.model.rowmapper.SubscribedCustomerContactNumberRowMapper;
import com.model.rowmapper.SubscribedCustomerEmailRowMapper;
import com.model.rowmapper.SubscribedCustomerRowMapper;
import com.model.workbook.WorkbookReport;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.SecurityUtil;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_CUSTOMER_SERVICE)
	public class CustomerService implements CustomerConstants {

	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient AdminService adminService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public List<SubscribedCustomer> getSubscribedCustomersList(final GridComponent gridComponent) throws Exception {
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"), null, null, gridComponent), new SubscribedCustomerRowMapper());
	}
	
	public ApplicationFile downloadAdminReportSubscribedCustomerList(final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("SUBSCRIBED_CUSTOMERS", WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getSubscribedCustomersList(gridComponent), SubscribedCustomer.class, AdminConstants.ADMIN_REPORT));
		return new ApplicationFile("SUBSCRIBED_CUSTOMERS_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	public ApplicationFile downloadSubscribedCustomerProfilePdf(final String customerSerialId, final Boolean isAdminProfile) throws Exception {
		final SubscribedCustomer subscribedCustomer = getSubscribedCustomer(customerSerialId);
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerSerialId", customerSerialId);
		if (ValidationUtils.checkObjectAvailability(subscribedCustomer)) {
			subscribedCustomer.setSubscribedCustomerEmails(applicationDao.findAll(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomerEmail") 
														+ queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerEmailCustomerSerialIdFilter"), paramsMap, new SubscribedCustomerEmailRowMapper()));
			subscribedCustomer.setSubscribedCustomerContactNumbers(applicationDao.findAll(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomerContactNumber") 
																+ queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerContactNumberCustomerSerialIdFilter"), paramsMap, new SubscribedCustomerContactNumberRowMapper()));
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("subscribedCustomer", subscribedCustomer);
	        attributes.put("fullAdminProfile", isAdminProfile);
	        return new ApplicationFile(subscribedCustomer.getName() + "_PROFILE" + PERIOD + FileConstants.EXTENSION_PDF, PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(SUBSCRIBED_CUSTOMER_PROFILE_VELOCITY_TEMPLATE_PATH, attributes)));
		}
		return null;
	}
	
	public SubscribedCustomer getSubscribedCustomer(final String customerSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("customerSerialId", customerSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer") 
														+ queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerCustomerSerialIdFilter"), paramsMap, new SubscribedCustomerRowMapper());
	}
	
	public Map<String, Boolean> getRecordFormUpdateStatus(final SubscribedCustomer subscribedCustomer) {
		final Map<String, Boolean> securityAccess = new HashMap<String, Boolean>();
		securityAccess.put("subscribedCustomerFormEditMandatoryDisbaled", false);
		return securityAccess;
	}
	
	public SubscribedCustomer getSubscribedCustomerInDatabaseWithEmailId(final String emailId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
		query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerEmailFilter"));
		return applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
	}
	
	public SubscribedCustomer getSubscribedCustomerInDatabaseWithContactNumber(final String contactNumber) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
		query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerContactNumberFilter"));
		return applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
	}
	
	@Transactional
	public void blacklistSubscribedCustomerList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<SubscribedCustomer> paramObjectList = new LinkedList<SubscribedCustomer>();
		for (final String customerSerialId : idList) {
			final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
			subscribedCustomer.setIsBlacklisted(YES);
			subscribedCustomer.setBlacklistedRemarks(comments);
			subscribedCustomer.setBlacklistedDateMillis(currentTimestamp.getTime());
			subscribedCustomer.setWhoBlacklisted(activeUser.getUserId());
			subscribedCustomer.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			subscribedCustomer.setUpdatedBy(activeUser.getUserId());
			subscribedCustomer.setCustomerSerialId(customerSerialId);
			paramObjectList.add(subscribedCustomer);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-subscribedcustomer", "updateBlacklistSubscribedCustomer", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistSubscribedCustomerList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<SubscribedCustomer> paramObjectList = new LinkedList<SubscribedCustomer>();
		for (final String customerSerialId : idList) {
			final SubscribedCustomer subscribedCustomer = new SubscribedCustomer();
			subscribedCustomer.setIsBlacklisted(NO);
			subscribedCustomer.setUnblacklistedRemarks(comments);
			subscribedCustomer.setUnblacklistedDateMillis(currentTimestamp.getTime());
			subscribedCustomer.setWhoUnBlacklisted(activeUser.getUserId());
			subscribedCustomer.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			subscribedCustomer.setUpdatedBy(activeUser.getUserId());
			subscribedCustomer.setCustomerSerialId(customerSerialId);
			paramObjectList.add(subscribedCustomer);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-subscribedcustomer", "updateUnBlacklistSubscribedCustomer", paramObjectList);
	}
	
	@Transactional
	public void updateCustomerRecord(final SubscribedCustomer customer, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE SUBSCRIBED_CUSTOMER SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE CUSTOMER_SERIAL_ID = :customerSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", customer.getName());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrades");
						paramsMap.put("studentGrades", customer.getStudentGrades());
						break;
					}
					case "interestedSubjects" : {
						updateAttributesQuery.add("SUBJECTS = :interestedSubjects");
						paramsMap.put("interestedSubjects", customer.getInterestedSubjects());
						break;
					}
					case "location" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", customer.getLocation());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", customer.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", customer.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("customerSerialId", customer.getCustomerSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<FindTutor> getFindTutorListForApplicationStatusSelected(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, FindTutor.class);
		} else {
			gridComponent = new GridComponent(FindTutor.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("public-application", "findTutorNonMigratedFilter"));
		return adminService.getFindTutorList(RestMethodConstants.REST_METHOD_NAME_SELECTED_FIND_TUTOR_LIST, gridComponent);
	}
	
	public void sendProfileGenerationEmailToSubscribedCustomerList(final List<SubscribedCustomer> subscribedCustomerList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final SubscribedCustomer subscribedCustomerObj : subscribedCustomerList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("addressName", subscribedCustomerObj.getName());
			attributes.put("userId", subscribedCustomerObj.getUserId());
			attributes.put("temporaryPassword", SecurityUtil.decrypt(subscribedCustomerObj.getEncryptedPassword()));
			final Map<String, Object> mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, subscribedCustomerObj.getEmailId());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your Seek Mentore \"Parent & Student\" profile is created");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(PROFILE_CREATION_VELOCITY_TEMPLATE_PATH_SUBSCRIBED_CUSTOMER, attributes));
			mailParamList.add(mailParams);
		}
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public SubscribedCustomer getSubscribedCustomerFromDbUsingUserId(final String userId) throws Exception {
		if (ValidationUtils.checkStringAvailability(userId)) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer")
										+ queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerUserIdFilter"), paramsMap, new SubscribedCustomerRowMapper());
		}
		return null;
	}
}
