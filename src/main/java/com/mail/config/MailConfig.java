package com.mail.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.constants.MailConstants;
import com.model.control.MailConfiguration;
import com.service.CipherService;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.SecurityUtil;
 
@Configuration
public class MailConfig implements MailConstants {
	
	private Properties mailProperties = new Properties();
	
	@Autowired
    private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@Autowired
    private CipherService cipherService;
   
	@Bean
    public JavaMailSender getMailSender() throws Exception {
        final MailConfiguration mailConfiguration = jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration();
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfiguration.getHost());
        mailSender.setPort(mailConfiguration.getPort());
        mailSender.setUsername(SecurityUtil.decrypt(mailConfiguration.getEncryptedUsername(), cipherService, jndiAndControlConfigurationLoadService));
        mailSender.setPassword(SecurityUtil.decrypt(mailConfiguration.getEncryptedPassword(), cipherService, jndiAndControlConfigurationLoadService));
        mailProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(MAIL_PROPERTIES_FILE));
        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }
}