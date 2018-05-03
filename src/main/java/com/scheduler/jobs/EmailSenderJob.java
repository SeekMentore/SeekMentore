package com.scheduler.jobs;

import java.io.IOException;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.constants.JobConstants;
import com.service.SchedulerService;
import com.utils.ExceptionUtils;

public class EmailSenderJob implements Job, JobConstants {
	
	/**
	 * Static parameters for Job Configuration
	 */
	public static Long START_DELAY = 90*1000L;
	public static String CRON_EXPRESSION = "0 0/2 * * * ?";
	public static String DESCRIPTION = "Email Sender Job";
	public static String KEY = "EmailSenderJob";
	
	@Autowired
    private SchedulerService schedulerService;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
    	try {
			schedulerService.executeEmailSenderJob(context);
		} catch (IOException | MessagingException e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
    }
}
