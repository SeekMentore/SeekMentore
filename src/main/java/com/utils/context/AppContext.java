package com.utils.context;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

public class AppContext {

    private static ApplicationContext ctx;
    private static AutowireCapableBeanFactory beanFactory;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
    
    public static <T extends Object> T getBean(final String name, final Class<T> type) {
		return type.cast(ctx.getBean(name));
	}

	public static AutowireCapableBeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
		AppContext.beanFactory = beanFactory;
	}
}
