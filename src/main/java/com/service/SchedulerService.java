package com.service;

import java.io.IOException;
import java.sql.Timestamp;
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
import com.dao.ApplicationDao;
import com.model.ErrorPacket;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.mail.ApplicationMail;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.ExceptionUtils;
import com.utils.LoggerUtils;
import com.utils.MailUtils;
import com.utils.PasswordUtils;
import com.utils.SecurityUtil;

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
	
	public void executeEmailSenderJob(final JobExecutionContext context) throws IOException, MessagingException {
		final String key = lockService.lockObject("executeEmailSenderJob");
		if (null != key) {
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
					paramsMap.put("errorTrace", errorPacket.getErrorTrace());
					paramsMap.put("mailId", mailObj.getMailId());
					applicationDao.executeUpdate("UPDATE MAIL_QUEUE SET ERROR_OCCURED_WHILE_SENDING = :errorOccuredWhileSending, ERROR_DATE = :errorDate, ERROR_TRACE = :errorTrace WHERE MAIL_ID = :mailId", paramsMap);
					continue;
					// If exception occurred do not update the record as sent
				}
				final Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("mailSent", YES);
				paramsMap.put("sendDate", new Timestamp(new Date().getTime()));
				paramsMap.put("mailId", mailObj.getMailId());
				applicationDao.executeUpdate("UPDATE MAIL_QUEUE SET MAIL_SENT = :mailSent, SEND_DATE = :sendDate WHERE MAIL_ID = :mailId", paramsMap);
			}
			lockService.releaseLock("executeEmailSenderJob", key);
		}
	}
	
	public void executeTutorRegisterJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeTutorRegisterJob");
		if (null != key) {
			LoggerUtils.logOnConsole("executeTutorRegisterJob");
			final List<BecomeTutor> tutorObjList = tutorService.getSelectedTutorRegistrations(20);
			for (final BecomeTutor tutorObj : tutorObjList) {
				final String generateTemporaryPassword = ApplicationUtils.getStringFromCharacterArray(PasswordUtils.generateRandomPassword(new Character[] {'I','i','O','o','L','l'}, 4, 8, true, true, false, false, false, false, false, false, true));
				final String encryptedTemporaryPassword = SecurityUtil.encrypt(generateTemporaryPassword);
				final RegisteredTutor registeredTutorObj = new RegisteredTutor();
				registeredTutorObj.setName(tutorObj.getFirstName().toUpperCase()+WHITESPACE+tutorObj.getLastName().toUpperCase());
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
				registeredTutorObj.setEncryptedPassword(encryptedTemporaryPassword);
				registeredTutorObj.setUserId(tutorObj.getEmailId());
				tutorService.feedRegisteredTutorRecords(registeredTutorObj);
				tutorService.sendProfileGenerationEmailToTutor(registeredTutorObj, generateTemporaryPassword);
				tutorService.updateBecomeTutorForDataMigrated(tutorObj.getTentativeTutorId());
			}
			lockService.releaseLock("executeTutorRegisterJob", key);
		}
	}
	public void executeSubscribedCustomerJob(final JobExecutionContext context) throws Exception {
		final String key = lockService.lockObject("executeSubscribedCustomerJob");
		if (null != key) {
			LoggerUtils.logOnConsole("executeSubscribedCustomerJob");
			final List<FindTutor> customerObjList = customerService.getNonSubscribedCustomer(20);
			for (final FindTutor customerObj : customerObjList) {
				final String generateTemporaryPassword = ApplicationUtils.getStringFromCharacterArray(PasswordUtils.generateRandomPassword(new Character[] {'I','i','O','o','L','l'}, 4, 8, true, true, false, false, false, false, false, false, true));
				final String encryptedTemporaryPassword = SecurityUtil.encrypt(generateTemporaryPassword);
				final SubscribedCustomer subscribedCustomerObj = new SubscribedCustomer();
				subscribedCustomerObj.setName(customerObj.getName().toUpperCase());
				subscribedCustomerObj.setContactNumber(customerObj.getContactNumber());
				subscribedCustomerObj.setEmailId(customerObj.getEmailId());
				subscribedCustomerObj.setEnquiryID(customerObj.getEnquiryId());
				subscribedCustomerObj.setStudentGrades(customerObj.getStudentGrade());
				subscribedCustomerObj.setInterestedSubjects(customerObj.getSubjects());
				subscribedCustomerObj.setLocation(customerObj.getLocation());
				subscribedCustomerObj.setAdditionalDetails(customerObj.getAdditionalDetails());
				subscribedCustomerObj.setAddressDetails(customerObj.getAddressDetails());
				subscribedCustomerObj.setEncryptedPassword(encryptedTemporaryPassword);
				subscribedCustomerObj.setUserId(customerObj.getEmailId());
				customerService.feedSubscribedCustomerRecords(subscribedCustomerObj);
				customerService.sendProfileGenerationEmailToCustomer(subscribedCustomerObj, generateTemporaryPassword);
				customerService.updateFindTutorForDataMigrated(subscribedCustomerObj.getEnquiryID());
			}
			lockService.releaseLock("executeSubscribedCustomerJob", key);
		}
	}
	
}
