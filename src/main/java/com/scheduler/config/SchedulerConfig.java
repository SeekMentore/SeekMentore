package com.scheduler.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.CronTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.constants.SchedulerConstants;
import com.scheduler.jobs.EmailSenderJob;
import com.scheduler.jobs.SubscribedCustomerJob;
import com.scheduler.jobs.TutorRegisterJob;
import com.service.LockService;
import com.utils.context.AppContext;

@Configuration
public class SchedulerConfig implements SchedulerConstants {
	
	private Properties schedulerProperties = new Properties();

    @Autowired
    private DataSource dataSource;
    
    @Autowired
	private transient LockService lockService;
    
    @Bean
    public JobFactory jobFactory() {
    	class SchedulerJobFactory extends SpringBeanJobFactory {
            @Override
            protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
                final Object job = super.createJobInstance(bundle);
                AppContext.getBeanFactory().autowireBean(job);
                return job;
            }
        }
        return new SchedulerJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        final SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory());
        schedulerProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(SCHEDULER_PROPERTIES_FILE));
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(schedulerProperties);
        factory.setTriggers(getTriggerArray());
        return factory;
    }
    
    private Trigger[] getTriggerArray() {
    	final List<Trigger> triggerList = new ArrayList<Trigger>();
    	triggerList.add(emailSenderJobTrigger().getObject());
    	triggerList.add(tutorRegisterJobTrigger().getObject());
    	triggerList.add(subscribedCustomerJobTrigger().getObject());
        return triggerList.toArray(new Trigger[0]);
    }
    
    /**
     * Configuring Trigger & JobDetails for "EmailSenderJob"
     */
    @Bean(name = "emailSenderJobTrigger")
    public CronTriggerFactoryBean emailSenderJobTrigger() {
        final CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(emailSenderJobDetails().getObject());
        factoryBean.setStartDelay(EmailSenderJob.START_DELAY);
        factoryBean.setCronExpression(EmailSenderJob.CRON_EXPRESSION);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        return factoryBean;
    }

    @Bean(name = "emailSenderJobDetails")
    public JobDetailFactoryBean emailSenderJobDetails() {
        final JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(EmailSenderJob.class);
        jobDetailFactoryBean.setDescription(EmailSenderJob.DESCRIPTION);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(EmailSenderJob.KEY);
        return jobDetailFactoryBean;
    }
    /**
     * Configuring Trigger & JobDetails for "EmailSenderJob"
     */
    /**
     * Configuring Trigger & JobDetails for "TutorRegisterJob"
     */
    @Bean(name = "tutorRegisterJobTrigger")
    public CronTriggerFactoryBean tutorRegisterJobTrigger() {
        final CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(tutorRegisterJobDetails().getObject());
        factoryBean.setStartDelay(TutorRegisterJob.START_DELAY);
        factoryBean.setCronExpression(TutorRegisterJob.CRON_EXPRESSION);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        return factoryBean;
    }

    @Bean(name = "tutorRegisterJobDetails")
    public JobDetailFactoryBean tutorRegisterJobDetails() {
        final JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(TutorRegisterJob.class);
        jobDetailFactoryBean.setDescription(TutorRegisterJob.DESCRIPTION);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(TutorRegisterJob.KEY);
        return jobDetailFactoryBean;
    }
    /**
     * Configuring Trigger & JobDetails for "TutorRegisterJob"
     */
    
    /**
     * Configuring Trigger & JobDetails for SubscribedCustomer Job
     **/
    
    @Bean(name = "subscribedCustomerJobTrigger")
    public CronTriggerFactoryBean subscribedCustomerJobTrigger() {
    	final String key = lockService.lockObject("subscribedCustomerJobTrigger");
    	final CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		if (null != key) {        
        factoryBean.setJobDetail(subscribedCustomerJobDetails().getObject());
        factoryBean.setStartDelay(SubscribedCustomerJob.START_DELAY);
        factoryBean.setCronExpression(SubscribedCustomerJob.CRON_EXPRESSION);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY);
        lockService.releaseLock("subscribedCustomerJobTrigger", key);
        }
		return factoryBean;
    }

    @Bean(name = "subscribedCustomerJobDetails")
    public JobDetailFactoryBean subscribedCustomerJobDetails() {
        final JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(SubscribedCustomerJob.class);
        jobDetailFactoryBean.setDescription(SubscribedCustomerJob.DESCRIPTION);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(SubscribedCustomerJob.KEY);
        return jobDetailFactoryBean;
    }
    
    
    /**
     * Configuring Trigger & JobDetails for SubscribedCustomer Job
     **/
     
}
