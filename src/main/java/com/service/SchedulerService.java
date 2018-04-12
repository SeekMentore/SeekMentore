package com.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
	
	public void executeEmailSenderJob(final JobExecutionContext context) {
		final String key = lockService.lockObject("executeEmailSenderJob");
		if (null != key) {
			LoggerUtils.logOnConsole("executeEmailSenderJob");
			final List<ApplicationMail> mailObjList = commonsService.getPedingEmailList(20);
			for (final ApplicationMail mailObj : mailObjList) {
				try {
					MailUtils.sendingCustomisedFromAddressMimeMessageEmail(
							mailObj.getFromAddress(), 
							mailObj.getToAddress(), 
							mailObj.getCcAddress(), 
							mailObj.getBccAddress(), 
							mailObj.getSubjectContent(), 
							mailObj.getMessageContent(), 
							mailObj.getAttachments());
					// Make the system sleep for 10 seconds after
					// successfully sending out an email
					Thread.sleep(20*1000);
				} catch (Exception e) {
					final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), "executeEmailSenderJob", ExceptionUtils.generateErrorLog(e));
					commonsService.feedErrorRecord(errorPacket);
					applicationDao.updateWithPreparedQueryAndIndividualOrderedParams("UPDATE MAIL_QUEUE SET ERROR_OCCURED_WHILE_SENDING = ?, ERROR_DATE = ?, ERROR_TRACE = ? WHERE MAIL_ID = ?", new Object[] {YES, errorPacket.getOccuredAt(), errorPacket.getErrorTrace(), mailObj.getMailId()});
					continue;
					// If exception occurred do not update the record as sent
				}
				applicationDao.updateWithPreparedQueryAndIndividualOrderedParams("UPDATE MAIL_QUEUE SET MAIL_SENT = ?, SEND_DATE = ? WHERE MAIL_ID = ?", new Object[] {YES, new Timestamp(new Date().getTime()), mailObj.getMailId()});
			}
			lockService.releaseLock("executeEmailSenderJob", key);
		}
	}
}
