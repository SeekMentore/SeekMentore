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
import com.model.mail.ApplicationMail;
import com.service.components.CommonsService;
import com.utils.ExceptionUtils;
import com.utils.LoggerUtils;
import com.utils.MailUtils;

@Service(BeanConstants.BEAN_NAME_SCHEDULER_SERVICE)
public class SchedulerService implements SchedulerConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private CommonsService commonsService;
	
	@Autowired
	private LockService lockService;
	
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
					applicationDao.insertOrUpdateWithParams("UPDATE MAIL_QUEUE SET ERROR_OCCURED_WHILE_SENDING = :errorOccuredWhileSending, ERROR_DATE = :errorDate, ERROR_TRACE = :errorTrace WHERE MAIL_ID = :mailId", paramsMap);
					continue;
					// If exception occurred do not update the record as sent
				}
				final Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("mailSent", YES);
				paramsMap.put("sendDate", new Timestamp(new Date().getTime()));
				paramsMap.put("mailId", mailObj.getMailId());
				applicationDao.insertOrUpdateWithParams("UPDATE MAIL_QUEUE SET MAIL_SENT = :mailSent, SEND_DATE = :sendDate WHERE MAIL_ID = :mailId", paramsMap);
			}
			lockService.releaseLock("executeEmailSenderJob", key);
		}
	}
}
