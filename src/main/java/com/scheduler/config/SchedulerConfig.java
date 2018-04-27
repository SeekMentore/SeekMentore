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
import com.utils.context.AppContext;

@Configuration
public class SchedulerConfig implements SchedulerConstants {
	
	private Properties schedulerProperties = new Properties();

    @Autowired
    private DataSource dataSource;
    
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
}
