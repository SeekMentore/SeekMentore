package com.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.constants.JobConstants;
import com.service.SchedulerService;

public class ReminderEmailJob implements Job, JobConstants {
	
	/**
	 * Static parameters for Job Configuration
	 */
	public static Long START_DELAY = 0L;
	public static String CRON_EXPRESSION = "0/10 * * * * ?";
	public static String DESCRIPTION = "Reminder Email Job for HR Admin";
	public static String KEY = "ReminderEmailJob";
	
	@Autowired
    private SchedulerService schedulerService;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
    	schedulerService.executeReminderEmailJob(context);
    }
}
