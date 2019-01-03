package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.MailConstants;
import com.constants.SchedulerConstants;
import com.constants.components.EnquiryConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.ErrorPacket;
import com.model.components.Demo;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.SubscriptionPackage;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.mail.ApplicationMail;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.DemoService;
import com.service.components.EnquiryService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.ExceptionUtils;
import com.utils.LoggerUtils;
import com.utils.MailUtils;
import com.utils.PasswordUtils;
import com.utils.SecurityUtil;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_SCHEDULER_SERVICE)
public class SchedulerService implements SchedulerConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private transient LockService lockService;
	
	@Autowired
	private transient AdminService adminService;
	
	@Autowired
	private transient TutorService tutorService;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient EnquiryService enquiryService;
	
	@Autowired
	private transient DemoService demoService;
	
	@Autowired
	private transient MailService mailService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	public void executeEmailSenderJob(final JobExecutionContext context) throws IOException, MessagingException {
		final String key = lockService.lockObject("executeEmailSenderJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeEmailSenderJob");
				final List<ApplicationMail> applicationMailList = mailService.getPedingApplicationMailList(true, 20);
				if (ValidationUtils.checkNonEmptyList(applicationMailList)) {
					for (final ApplicationMail applicationMail : applicationMailList) {
						applicationMail.setAttachments(mailService.getMailAttachmentList(applicationMail.getMailId()));
						try {
							int retriedCounter = 0;
							do {
								try {
									MailUtils.sendingCustomisedFromAddressMimeMessageEmail(
											applicationMail.getFromAddress(), 
											applicationMail.getToAddress(), 
											applicationMail.getCcAddress(), 
											applicationMail.getBccAddress(), 
											applicationMail.getSubjectContent(), 
											applicationMail.getMessageContent(), 
											applicationMail.getAttachments());
									// break the loop if mail sending for successful
									break;
								} catch (Exception e) {
									if (retriedCounter == 1) 
										// throw exception to bigger catch block if the retry counter is hit twice
										throw new Exception(e);
								}
								retriedCounter++;
								// This condition is just a safety check
							} while(retriedCounter < 2);
						} catch (Exception e) {
							final ErrorPacket errorPacket = new ErrorPacket("executeEmailSenderJob", ExceptionUtils.generateErrorLog(e));
							commonsService.feedErrorRecord(errorPacket);
							applicationMail.setErrorOccuredWhileSending(YES);
							applicationMail.setErrorDateMillis(errorPacket.getOccuredAtMillis());
							applicationMail.setErrorTrace(errorPacket.getErrorTrace());
							applicationDao.executeUpdateWithQueryMapper("mail", "updateApplicationMailSendingError", applicationMail);
							continue;
							// If exception occurred do not update the record as sent
						}
						final Date currentTimestamp = new Date();
						applicationMail.setMailSent(YES);
						applicationMail.setSendDateMillis(currentTimestamp.getTime());
						applicationDao.executeUpdateWithQueryMapper("mail", "updateApplicationMailSent", applicationMail);
					}
				}
			} catch(Exception e) {
				lockService.releaseLock("executeEmailSenderJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeEmailSenderJob", key);
		}
	}
	
	public void executeTutorRegisterJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeTutorRegisterJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeTutorRegisterJob");
				final Date currentTimestamp = new Date();
				final List<BecomeTutor> tutorObjList = tutorService.getBecomeTutorListForApplicationStatusSelected(true, 20);
				if (ValidationUtils.checkNonEmptyList(tutorObjList)) {
					final List<RegisteredTutor> registeredTutorList = new ArrayList<RegisteredTutor>();
					for (final BecomeTutor tutorObj : tutorObjList) {
						final String generateTemporaryPassword = ApplicationUtils.getStringFromCharacterArray(PasswordUtils.generateRandomPassword(new Character[] {'I','i','O','o','L','l'}, 4, 8, true, true, false, false, false, false, false, false, true));
						final String encryptedTemporaryPassword = SecurityUtil.encrypt(generateTemporaryPassword);
						final RegisteredTutor registeredTutorObj = new RegisteredTutor();
						registeredTutorObj.setName(tutorObj.getFirstName().toUpperCase() + WHITESPACE + tutorObj.getLastName().toUpperCase());
						registeredTutorObj.setContactNumber(tutorObj.getContactNumber());
						registeredTutorObj.setEmailId(tutorObj.getEmailId());
						registeredTutorObj.setTentativeTutorId(tutorObj.getTentativeTutorId());
						registeredTutorObj.setDateOfBirth(tutorObj.getDateOfBirth());
						registeredTutorObj.setGender(tutorObj.getGender());
						registeredTutorObj.setQualification(tutorObj.getQualification());
						registeredTutorObj.setPrimaryProfession(tutorObj.getPrimaryProfession());
						registeredTutorObj.setTransportMode(tutorObj.getTransportMode());
						registeredTutorObj.setTeachingExp(tutorObj.getTeachingExp());
						registeredTutorObj.setInterestedStudentGrades(tutorObj.getStudentGrade());
						registeredTutorObj.setInterestedSubjects(tutorObj.getSubjects());
						registeredTutorObj.setComfortableLocations(tutorObj.getLocations());
						registeredTutorObj.setPreferredTeachingType(tutorObj.getPreferredTeachingType());
						registeredTutorObj.setAdditionalDetails(tutorObj.getAdditionalDetails());
						registeredTutorObj.setAddressDetails(tutorObj.getAddressDetails());
						registeredTutorObj.setEncryptedPassword(encryptedTemporaryPassword);
						registeredTutorObj.setUserId(tutorObj.getEmailId());
						registeredTutorObj.setRecordLastUpdatedMillis(currentTimestamp.getTime());
						registeredTutorObj.setUpdatedBy("SYSTEM_SCHEDULER");
						registeredTutorList.add(registeredTutorObj);
						tutorObj.setIsDataMigrated(YES);
						tutorObj.setWhenMigratedMillis(currentTimestamp.getTime());
					}
					tutorService.feedRegisteredTutorList(registeredTutorList, tutorObjList);
				}
			} catch(Exception e) {
				lockService.releaseLock("executeTutorRegisterJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeTutorRegisterJob", key);
		}
	}
	
	public void executeSubscribedCustomerJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeSubscribedCustomerJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeSubscribedCustomerJob");
				final Date currenTimestamp = new Date();
				final List<FindTutor> findTutorObjList = customerService.getFindTutorListForEnquiryStatusSelected(true, 20);
				if (ValidationUtils.checkNonEmptyList(findTutorObjList)) {
					for (final FindTutor findTutorObj : findTutorObjList) {
						final List<Enquiry> enquiryObjectList = new ArrayList<Enquiry>();
						if (NO.equalsIgnoreCase(findTutorObj.getSubscribedCustomer())) {
							final String generateTemporaryPassword = ApplicationUtils.getStringFromCharacterArray(PasswordUtils.generateRandomPassword(new Character[] {'I','i','O','o','L','l'}, 4, 8, true, true, false, false, false, false, false, false, true));
							final String encryptedTemporaryPassword = SecurityUtil.encrypt(generateTemporaryPassword);
							final SubscribedCustomer subscribedCustomerObj = new SubscribedCustomer();
							subscribedCustomerObj.setName(findTutorObj.getName().toUpperCase());
							subscribedCustomerObj.setContactNumber(findTutorObj.getContactNumber());
							subscribedCustomerObj.setEmailId(findTutorObj.getEmailId());
							subscribedCustomerObj.setFindTutorId(findTutorObj.getEnquiryId());
							subscribedCustomerObj.setStudentGrades(findTutorObj.getStudentGrade());
							subscribedCustomerObj.setInterestedSubjects(findTutorObj.getSubjects());
							subscribedCustomerObj.setLocation(findTutorObj.getLocation());
							subscribedCustomerObj.setAdditionalDetails(findTutorObj.getAdditionalDetails());
							subscribedCustomerObj.setAddressDetails(findTutorObj.getAddressDetails());
							subscribedCustomerObj.setEncryptedPassword(encryptedTemporaryPassword);
							subscribedCustomerObj.setUserId(findTutorObj.getEmailId());
							subscribedCustomerObj.setUpdatedBy("SYSTEM_SCHEDULER");
							subscribedCustomerObj.setRecordLastUpdatedMillis(currenTimestamp.getTime());
							final Long customerId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomer", subscribedCustomerObj);
							final Enquiry enquiryObject = new Enquiry();
							enquiryObject.setCustomerId(customerId);
							enquiryObject.setSubject(findTutorObj.getSubjects());
							enquiryObject.setGrade(findTutorObj.getStudentGrade());
							enquiryObject.setMatchStatus(EnquiryConstants.MATCH_STATUS_PENDING);
							enquiryObject.setLocationDetails(findTutorObj.getLocation());
							enquiryObject.setAddressDetails(findTutorObj.getAddressDetails());
							enquiryObject.setAdditionalDetails(findTutorObj.getAdditionalDetails());
							enquiryObjectList.add(enquiryObject);
							customerService.sendProfileGenerationEmailToCustomer(subscribedCustomerObj, generateTemporaryPassword);
						} else {
							Map<String, Object> paramsMap = new HashMap<String, Object>();
							paramsMap.put("emailId", findTutorObj.getEmailId());
							StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
							query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerEmailFilter"));
							final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
							if (null != subscribedCustomerInDatabaseWithEmailId) {
								if (null != subscribedCustomerInDatabaseWithEmailId.getCustomerId()) {
									final Enquiry enquiryObject = new Enquiry();
									enquiryObject.setCustomerId(subscribedCustomerInDatabaseWithEmailId.getCustomerId());
									enquiryObject.setSubject(findTutorObj.getSubjects());
									enquiryObject.setGrade(findTutorObj.getStudentGrade());
									enquiryObject.setMatchStatus(EnquiryConstants.MATCH_STATUS_PENDING);
									enquiryObject.setLocationDetails(findTutorObj.getLocation());
									enquiryObject.setAddressDetails(findTutorObj.getAddressDetails());
									enquiryObject.setAdditionalDetails(findTutorObj.getAdditionalDetails());
									enquiryObjectList.add(enquiryObject);
								}
							} 
							paramsMap = new HashMap<String, Object>();
							paramsMap.put("contactNumber", findTutorObj.getContactNumber());
							query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
							query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerContactNumberFilter"));
							final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
							if (null != subscribedCustomerInDatabaseWithContactNumber) {
								if (null != subscribedCustomerInDatabaseWithContactNumber.getCustomerId()) {
									final Enquiry enquiryObject = new Enquiry();
									enquiryObject.setCustomerId(subscribedCustomerInDatabaseWithContactNumber.getCustomerId());
									enquiryObject.setSubject(findTutorObj.getSubjects());
									enquiryObject.setGrade(findTutorObj.getStudentGrade());
									enquiryObject.setMatchStatus(EnquiryConstants.MATCH_STATUS_PENDING);
									enquiryObject.setLocationDetails(findTutorObj.getLocation());
									enquiryObject.setAddressDetails(findTutorObj.getAddressDetails());
									enquiryObject.setAdditionalDetails(findTutorObj.getAdditionalDetails());
									enquiryObjectList.add(enquiryObject);
								}
							} 
							
						}
						if (!enquiryObjectList.isEmpty()) {
							enquiryService.feedEnquiryList(enquiryObjectList);
						}
					}
					applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateFindTutorDataMigrated", findTutorObjList);
				}
			} catch (Exception e) {
				lockService.releaseLock("executeSubscribedCustomerJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeSubscribedCustomerJob", key);
		}
	}
	
	public void executeSubmitQueryResponderJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeSubmitQueryResponderJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeSubmitQueryResponderJob");
				final Date currentTimestamp = new Date();
				final List<SubmitQuery> submitQueryList = adminService.getSubmitQueryListForQueryResponded(true, 20);
				if (ValidationUtils.checkNonEmptyList(submitQueryList)) {
					final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
					for (final SubmitQuery submitQueryObj : submitQueryList) {
						// TODO - Proper mail template
						final Map<String, Object> mailParams = new HashMap<String, Object>();
						mailParams.put(MailConstants.MAIL_PARAM_TO, submitQueryObj.getEmailId());
						mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Response from Seek Mentore for your query");
						mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, submitQueryObj.getQueryResponse());
						mailParamList.add(mailParams);
						submitQueryObj.setIsMailSent(YES);
						submitQueryObj.setMailSentMillis(currentTimestamp.getTime());
					}
					MailUtils.sendMultipleMimeMessageEmail(mailParamList);
					applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateRespondSubmitQueryMailSent", submitQueryList);
				}
			} catch(Exception e) {
				lockService.releaseLock("executeSubmitQueryResponderJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeSubmitQueryResponderJob", key);
		}
	}
	
	public void executeSubscriptionCreationJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeSubscriptionCreationJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeSubscriptionCreationJob");
				final Date currentTimestamp = new Date();
				final List<Demo> demoList = demoService.getSuccessfullDemoList(true, 20);
				if (ValidationUtils.checkNonEmptyList(demoList)) {
					final List<SubscriptionPackage> subscriptionPackageList = new ArrayList<SubscriptionPackage>();
					final List<Enquiry> enquiryList = new ArrayList<Enquiry>();
					for (final Demo demo : demoList) {
						final SubscriptionPackage subscriptionPackage = new SubscriptionPackage();
						subscriptionPackage.setDemoId(demo.getDemoTrackerId());
						subscriptionPackage.setTutorMapperId(demo.getTutorMapperId());
						subscriptionPackage.setEnquiryId(demo.getEnquiryId());
						subscriptionPackage.setCustomerId(demo.getCustomerId());
						subscriptionPackage.setTutorId(demo.getTutorId());
						subscriptionPackage.setRecordLastUpdatedMillis(currentTimestamp.getTime());
						subscriptionPackage.setCreatedMillis(currentTimestamp.getTime());
						subscriptionPackage.setUpdatedBy("SYSTEM_SCHEDULER");
						subscriptionPackageList.add(subscriptionPackage);
						final Enquiry enquiry = new Enquiry();
						enquiry.setEnquiryId(demo.getEnquiryId());
						enquiry.setMatchStatus(EnquiryConstants.MATCH_STATUS_COMPLETED);
						enquiry.setTutorId(demo.getTutorId());
						enquiry.setIsMapped(YES);
						enquiry.setWhoActed("SYSTEM_SCHEDULER");
						enquiry.setLastActionDateMillis(currentTimestamp.getTime());
						enquiry.setAdminRemarks("Completed enquiry by Scheduler");
						enquiryList.add(enquiry);
						demo.setIsSubscriptionCreated(YES);
						demo.setSubscriptionCreatedMillis(currentTimestamp.getTime());
						demo.setIsEnquiryClosed(YES);
						demo.setEnquiryClosedMillis(currentTimestamp.getTime());
					}
					applicationDao.executeBatchUpdateWithQueryMapper("sales-subscriptionpackage", "insertSubscriptionPackage", subscriptionPackageList);
					applicationDao.executeBatchUpdateWithQueryMapper("sales-demo", "updateDemoSubscriptionCreated", demoList);
					applicationDao.executeBatchUpdateWithQueryMapper("sales-enquiry", "updateEnquiryCompleted", enquiryList);
				}
				final Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("isEnquiryClosed", YES);
				paramsMap.put("enquiryClosedMillis", currentTimestamp.getTime());
				paramsMap.put("matchStatusList", Arrays.asList(new String[] {EnquiryConstants.MATCH_STATUS_COMPLETED, EnquiryConstants.MATCH_STATUS_ABORTED}));
				applicationDao.executeUpdate(queryMapperService.getQuerySQL("sales-tutormapper", "updateTutorMapperEnquiryClosedForEnquiryMatchStatusList"), paramsMap);
				applicationDao.executeUpdate(queryMapperService.getQuerySQL("sales-demo", "updateDemoEnquiryClosedForEnquiryMatchStatusList"), paramsMap);
			} catch(Exception e) {
				lockService.releaseLock("executeSubscriptionCreationJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeSubscriptionCreationJob", key);
		}
	}
}
