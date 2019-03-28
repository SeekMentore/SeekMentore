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
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.MailConstants;
import com.constants.SchedulerConstants;
import com.constants.components.EnquiryConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.ErrorPacket;
import com.model.components.Demo;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.RegisteredTutorContactNumber;
import com.model.components.RegisteredTutorEmail;
import com.model.components.SubscribedCustomer;
import com.model.components.SubscribedCustomerContactNumber;
import com.model.components.SubscribedCustomerEmail;
import com.model.components.SubscriptionPackage;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.mail.ApplicationMail;
import com.model.mail.MailAttachment;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.components.AdminService;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.DemoService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.ExceptionUtils;
import com.utils.LoggerUtils;
import com.utils.MailUtils;
import com.utils.PasswordUtils;
import com.utils.SecurityUtil;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

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
	private transient DemoService demoService;
	
	@Autowired
	private transient MailService mailService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@Transactional
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
							final ErrorPacket errorPacket = new ErrorPacket("executeEmailSenderJob", ExceptionUtils.generateErrorLog(e), false, null);
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
	
	@Transactional
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
						final Long tutorId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("admin-registeredtutor", "insertRegisteredTutor", registeredTutorObj);
						final RegisteredTutorEmail registeredTutorEmail = new RegisteredTutorEmail();
						registeredTutorEmail.setTutorId(tutorId);
						registeredTutorEmail.setEmailId(registeredTutorObj.getEmailId());
						registeredTutorEmail.setIsPrimary(YES);
						applicationDao.executeUpdateWithQueryMapper("admin-registeredtutor", "insertRegisteredTutorEmail", registeredTutorEmail);
						final RegisteredTutorContactNumber registeredTutorContactNumber = new RegisteredTutorContactNumber();
						registeredTutorContactNumber.setTutorId(tutorId);
						registeredTutorContactNumber.setContactNumber(registeredTutorObj.getContactNumber());
						registeredTutorContactNumber.setIsPrimary(YES);
						applicationDao.executeUpdateWithQueryMapper("admin-registeredtutor", "insertRegisteredTutorContactNumber", registeredTutorContactNumber);
						registeredTutorList.add(registeredTutorObj);
						tutorObj.setIsDataMigrated(YES);
						tutorObj.setWhenMigratedMillis(currentTimestamp.getTime());
					}
					applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateMigratedBecomeTutor", tutorObjList);
					tutorService.sendProfileGenerationEmailToRegisteredTutorList(registeredTutorList);
				}
			} catch(Exception e) {
				lockService.releaseLock("executeTutorRegisterJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeTutorRegisterJob", key);
		}
	}
	
	@Transactional
	public void executeSubscribedCustomerJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeSubscribedCustomerJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeSubscribedCustomerJob");
				final Date currentTimestamp = new Date();
				final List<FindTutor> findTutorObjList = customerService.getFindTutorListForEnquiryStatusSelected(true, 20);
				final List<FindTutor> findTutorObjListToBeMigrated = new ArrayList<FindTutor>();
				if (ValidationUtils.checkNonEmptyList(findTutorObjList)) {
					final List<SubscribedCustomer> subscribedCustomerList = new ArrayList<SubscribedCustomer>();
					final List<Enquiry> enquiryObjectList = new ArrayList<Enquiry>();
					for (final FindTutor findTutorObj : findTutorObjList) {
						Boolean proceedForEnquiryCreation = false;
						Long customerId = null;
						Boolean emailPresentInSystem = false;
						Boolean contactNumberPresentInSystem = false;
						Map<String, Object> paramsMap = new HashMap<String, Object>();
						paramsMap.put("emailId", findTutorObj.getEmailId());
						StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
						query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerEmailFilter"));
						final SubscribedCustomer subscribedCustomerInDatabaseWithEmailId = applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
						if (ValidationUtils.checkObjectAvailability(subscribedCustomerInDatabaseWithEmailId) && ValidationUtils.checkObjectAvailability(subscribedCustomerInDatabaseWithEmailId.getCustomerId())) {
							emailPresentInSystem = true;
						} 
						paramsMap = new HashMap<String, Object>();
						paramsMap.put("contactNumber", findTutorObj.getContactNumber());
						query = new StringBuilder(queryMapperService.getQuerySQL("admin-subscribedcustomer", "selectSubscribedCustomer"));
						query.append(queryMapperService.getQuerySQL("admin-subscribedcustomer", "subscribedCustomerContactNumberFilter"));
						final SubscribedCustomer subscribedCustomerInDatabaseWithContactNumber = applicationDao.find(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
						if (ValidationUtils.checkObjectAvailability(subscribedCustomerInDatabaseWithContactNumber) && ValidationUtils.checkObjectAvailability(subscribedCustomerInDatabaseWithContactNumber.getCustomerId())) {
							contactNumberPresentInSystem = true;
						}
						if (emailPresentInSystem || contactNumberPresentInSystem) {
							if (emailPresentInSystem && contactNumberPresentInSystem) {
								if (subscribedCustomerInDatabaseWithEmailId.getCustomerId().equals(subscribedCustomerInDatabaseWithContactNumber.getCustomerId())) {
									proceedForEnquiryCreation = true;
									customerId = subscribedCustomerInDatabaseWithContactNumber.getCustomerId();
								} else {
									final ErrorPacket errorPacket = new ErrorPacket("executeSubscribedCustomerJob", "Find Tutor Id = " + findTutorObj.getEnquiryId() +" ; Set to Subscribed Customer = Y have different Customer Records for EmailId & Contact Number", false, null);
									commonsService.feedErrorRecord(errorPacket);
								}
							} else {
								if (emailPresentInSystem) {
									proceedForEnquiryCreation = true;
									customerId = subscribedCustomerInDatabaseWithEmailId.getCustomerId();
									final SubscribedCustomerContactNumber subscribedCustomerContactNumber = new SubscribedCustomerContactNumber();
									subscribedCustomerContactNumber.setCustomerId(subscribedCustomerInDatabaseWithEmailId.getCustomerId());
									subscribedCustomerContactNumber.setContactNumber(findTutorObj.getContactNumber());
									subscribedCustomerContactNumber.setIsPrimary(NO);
									applicationDao.executeUpdateWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomerContactNumber", subscribedCustomerContactNumber);
								}
								if (contactNumberPresentInSystem) {
									proceedForEnquiryCreation = true;
									customerId = subscribedCustomerInDatabaseWithContactNumber.getCustomerId();
									final SubscribedCustomerEmail subscribedCustomerEmail = new SubscribedCustomerEmail();
									subscribedCustomerEmail.setCustomerId(subscribedCustomerInDatabaseWithContactNumber.getCustomerId());
									subscribedCustomerEmail.setEmailId(findTutorObj.getEmailId());
									subscribedCustomerEmail.setIsPrimary(NO);
									applicationDao.executeUpdateWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomerEmail", subscribedCustomerEmail);
								}
							}
						} else {
							proceedForEnquiryCreation = true;
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
							subscribedCustomerObj.setRecordLastUpdatedMillis(currentTimestamp.getTime());
							customerId = applicationDao.insertAndReturnGeneratedKeyWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomer", subscribedCustomerObj);
							subscribedCustomerList.add(subscribedCustomerObj);
							final SubscribedCustomerEmail subscribedCustomerEmail = new SubscribedCustomerEmail();
							subscribedCustomerEmail.setCustomerId(customerId);
							subscribedCustomerEmail.setEmailId(subscribedCustomerObj.getEmailId());
							subscribedCustomerEmail.setIsPrimary(YES);
							applicationDao.executeUpdateWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomerEmail", subscribedCustomerEmail);
							final SubscribedCustomerContactNumber subscribedCustomerContactNumber = new SubscribedCustomerContactNumber();
							subscribedCustomerContactNumber.setCustomerId(customerId);
							subscribedCustomerContactNumber.setContactNumber(subscribedCustomerObj.getContactNumber());
							subscribedCustomerContactNumber.setIsPrimary(YES);
							applicationDao.executeUpdateWithQueryMapper("admin-subscribedcustomer", "insertSubscribedCustomerContactNumber", subscribedCustomerContactNumber);
						}
						if (proceedForEnquiryCreation) {
							final String[] splitSubjects = findTutorObj.getSubjects().split(SEMICOLON);
							for (final String subject : splitSubjects) {
								final Enquiry enquiryObject = new Enquiry();
								enquiryObject.setCustomerId(customerId);
								enquiryObject.setEnquiryContactNumber(findTutorObj.getContactNumber());
								enquiryObject.setEnquiryEmail(findTutorObj.getEmailId());
								enquiryObject.setSubject(subject);
								enquiryObject.setGrade(findTutorObj.getStudentGrade());
								enquiryObject.setMatchStatus(EnquiryConstants.MATCH_STATUS_PENDING);
								enquiryObject.setLocationDetails(findTutorObj.getLocation());
								enquiryObject.setAddressDetails(findTutorObj.getAddressDetails());
								enquiryObject.setAdditionalDetails(findTutorObj.getAdditionalDetails());
								enquiryObjectList.add(enquiryObject);
							}
							findTutorObj.setIsDataMigrated(YES);
							findTutorObj.setWhenMigratedMillis(currentTimestamp.getTime());
							findTutorObjListToBeMigrated.add(findTutorObj);
						}
					}
					if (ValidationUtils.checkNonEmptyList(subscribedCustomerList)) {
						customerService.sendProfileGenerationEmailToSubscribedCustomerList(subscribedCustomerList);
					}
					if (ValidationUtils.checkNonEmptyList(enquiryObjectList)) {
						applicationDao.executeBatchUpdateWithQueryMapper("sales-enquiry", "insertEnquiry", enquiryObjectList);
					}
					if (ValidationUtils.checkNonEmptyList(findTutorObjListToBeMigrated)) {
						applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateFindTutorDataMigrated", findTutorObjListToBeMigrated);
					}
				}
			} catch (Exception e) {
				lockService.releaseLock("executeSubscribedCustomerJob", key);
				throw new ApplicationException(e);
			}
			lockService.releaseLock("executeSubscribedCustomerJob", key);
		}
	}
	
	@Transactional
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
						final Map<String, Object> attributes = new HashMap<String, Object>();
						attributes.put("submitQueryObj", submitQueryObj);
						final Map<String, Object> mailParams = new HashMap<String, Object>();
						final List<MailAttachment> attachments = new ArrayList<MailAttachment>();
						mailParams.put(MailConstants.MAIL_PARAM_TO, submitQueryObj.getEmailId());
						mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Response from \"Seek Mentore\" for your query");
						mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(PublicAccessConstants.SUBMIT_QUERY_RESPONSE_NOTIFICATION_VELOCITY_TEMPLATE_PATH, attributes));
						attachments.add(new MailAttachment("Brochure.pdf", "public_access/media/brochures/v1/Brochure.pdf", FileConstants.APPLICATION_TYPE_OCTET_STEAM));
						mailParams.put(MailConstants.MAIL_PARAM_ATTACHMENTS, attachments);
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
	
	@Transactional
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
						subscriptionPackage.setSubscriptionPackageSerialId(UUIDGeneratorUtils.generateSerialGUID());
						subscriptionPackage.setDemoSerialId(demo.getDemoSerialId());
						subscriptionPackage.setTutorMapperSerialId(demo.getTutorMapperSerialId());
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
