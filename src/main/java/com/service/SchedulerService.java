package com.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.SchedulerConstants;
import com.constants.components.EnquiryConstants;
import com.dao.ApplicationDao;
import com.model.ErrorPacket;
import com.model.components.Enquiry;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.mail.ApplicationMail;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
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
	private transient TutorService tutorService;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient EnquiryService enquiryService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	public void executeEmailSenderJob(final JobExecutionContext context) throws IOException, MessagingException {
		final String key = lockService.lockObject("executeEmailSenderJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeEmailSenderJob");
				final List<ApplicationMail> mailObjList = commonsService.getPedingEmailList(20);
				for (final ApplicationMail mailObj : mailObjList) {
					mailObj.setAttachments(commonsService.getAttachments(mailObj.getMailId()));
					try {
						int retriedCounter = 0;
						do {
							try {
								MailUtils.sendingCustomisedFromAddressMimeMessageEmail(
										mailObj.getFromAddress(), 
										mailObj.getToAddress(), 
										mailObj.getCcAddress(), 
										mailObj.getBccAddress(), 
										mailObj.getSubjectContent(), 
										mailObj.getMessageContent(), 
										mailObj.getAttachments());
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
						final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), "executeEmailSenderJob", ExceptionUtils.generateErrorLog(e));
						commonsService.feedErrorRecord(errorPacket);
						final Map<String, Object> paramsMap = new HashMap<String, Object>();
						paramsMap.put("errorOccuredWhileSending", YES);
						paramsMap.put("errorDate", errorPacket.getOccuredAt());
						paramsMap.put("errorDateMillis", errorPacket.getOccuredAt().getTime());
						paramsMap.put("errorTrace", errorPacket.getErrorTrace());
						paramsMap.put("mailId", mailObj.getMailId());
						applicationDao.executeUpdate("UPDATE MAIL_QUEUE SET ERROR_OCCURED_WHILE_SENDING = :errorOccuredWhileSending, ERROR_DATE = :errorDate, ERROR_DATE_MILLIS = :errorDateMillis, ERROR_TRACE = :errorTrace WHERE MAIL_ID = :mailId", paramsMap);
						continue;
						// If exception occurred do not update the record as sent
					}
					final Map<String, Object> paramsMap = new HashMap<String, Object>();
					final Long currentMillis = new Date().getTime();
					final Timestamp sendTimestamp = new Timestamp(currentMillis); 
					paramsMap.put("mailSent", YES);
					paramsMap.put("sendDate", sendTimestamp);
					paramsMap.put("sendDateMillis", currentMillis);
					paramsMap.put("mailId", mailObj.getMailId());
					applicationDao.executeUpdate("UPDATE MAIL_QUEUE SET MAIL_SENT = :mailSent, SEND_DATE = :sendDate, SEND_DATE_MILLIS = :sendDateMillis WHERE MAIL_ID = :mailId", paramsMap);
				}
			} catch(Exception e) {
				lockService.releaseLock("executeEmailSenderJob", key);
				throw e;
			}
			lockService.releaseLock("executeEmailSenderJob", key);
		}
	}
	
	public void executeTutorRegisterJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeTutorRegisterJob");
		if (null != key) {
			try {
				LoggerUtils.logOnConsole("executeTutorRegisterJob");
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
						registeredTutorList.add(registeredTutorObj);
					}
					tutorService.feedRegisteredTutorList(registeredTutorList);
				}
			} catch(Exception e) {
				lockService.releaseLock("executeTutorRegisterJob", key);
				throw e;
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
				throw e;
			}
			lockService.releaseLock("executeSubscribedCustomerJob", key);
		}
	}
	
}
