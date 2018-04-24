package com.view.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.BeanConstants;
import com.constants.FilterConstants;
import com.constants.PageConstants;
import com.exception.ApplicationException;
import com.model.User;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MenuService;
import com.utils.LoginUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractWebservice;

public class PostAuthenticationFilter extends AbstractWebservice implements Filter, FilterConstants, PageConstants {
	
	protected transient ServletContext servletContext;
	private transient MenuService menuService;
	private transient JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	private transient Boolean applyFilterToApplication;

	@Override
	public void destroy() {}

	@Override
	public void doFilter (
		final ServletRequest request, 
		final ServletResponse response, 
		final FilterChain chain
	) throws IOException, ServletException {
		if (applyFilterToApplication) {
			if (!(request instanceof HttpServletRequest)) {  
				return;
			}
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			if (!(response instanceof HttpServletResponse)) {  
				return;
			}
			final String pageURL = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
			if (menuService.isPageSecured(pageURL)) {
				final User user = LoginUtils.getUserFromSession(httpRequest);
				if (menuService.hasAccessToURL(user.getUserId(), pageURL, user.getPageAccessTypes(), null)) {
					chain.doFilter(request, response);
				} else {
					throw new ApplicationException("No access to this page.");
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		servletContext = config.getServletContext();
		menuService = AppContext.getBean(BeanConstants.BEAN_NAME_MENU_SERVICE, MenuService.class);
		jndiAndControlConfigurationLoadService = AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
		applyFilterToApplication = jndiAndControlConfigurationLoadService.getControlConfiguration().getApplyFilterToApplication();
	}
}
