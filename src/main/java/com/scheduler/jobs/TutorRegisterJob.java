package com.scheduler.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.constants.JobConstants;
import com.service.SchedulerService;
import com.utils.ExceptionUtils;

public class TutorRegisterJob implements Job, JobConstants {
	
	/**
	 * Static parameters for Job Configuration
	 */
	public static Long START_DELAY = 90*1000L;
	public static String CRON_EXPRESSION = "0 0/2 * * * ?";
	public static String DESCRIPTION = "Tutor Register Job";
	public static String KEY = "TutorRegisterJob";
	
	@Autowired
    private SchedulerService schedulerService;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
    	try {
			schedulerService.executeTutorRegisterJob(context);
		} catch (Exception e) {
			ExceptionUtils.rethrowCheckedExceptionAsUncheckedException(e);
		}
    }
}
