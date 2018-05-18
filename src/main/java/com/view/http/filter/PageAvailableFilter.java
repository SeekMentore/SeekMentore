package com.view.http.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
import com.model.ErrorPacket;
import com.service.JNDIandControlConfigurationLoadService;
import com.service.MenuService;
import com.service.components.CommonsService;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractWebservice;

public class PageAvailableFilter extends AbstractWebservice implements Filter, FilterConstants, PageConstants {
	
	protected transient ServletContext servletContext;
	private transient MenuService menuService;
	private transient JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	private transient CommonsService commonsService;
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
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			if (!(response instanceof HttpServletResponse)) {  
				return;
			}
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			final String pageURL = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
			if (menuService.pageExists(pageURL)) {
				chain.doFilter(request, response);
			} else {
				final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), pageURL + LINE_BREAK + getLoggedInUserIdAndTypeForPrinting(httpRequest), "Page not found on Server.");
				commonsService.feedErrorRecord(errorPacket);
				WebServiceUtils.redirectToPage("/error.html", httpRequest, httpResponse);
			}
			
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(final FilterConfig config) throws ServletException {
		servletContext = config.getServletContext();
		menuService = AppContext.getBean(BeanConstants.BEAN_NAME_MENU_SERVICE, MenuService.class);
		jndiAndControlConfigurationLoadService = AppContext.getBean(BeanConstants.BEAN_NAME_JNDI_AND_CONTROL_CONFIGURATION_LOAD_SERVICE, JNDIandControlConfigurationLoadService.class);
		commonsService = AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
		applyFilterToApplication = jndiAndControlConfigurationLoadService.getControlConfiguration().getApplyFilterToApplication();
	}
}
