package com.service;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.SchedulerConstants;
import com.dao.ApplicationDao;
import com.utils.LoggerUtils;

@Service(BeanConstants.BEAN_NAME_SCHEDULER_SERVICE)
public class SchedulerService implements SchedulerConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	public void executeReminderEmailJob(final JobExecutionContext context) {
		LoggerUtils.logOnConsole("Fired " + new Date() + " " + applicationDao);
	}
}
