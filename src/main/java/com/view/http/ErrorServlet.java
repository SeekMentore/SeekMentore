package com.view.http;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.ApplicationConstants;
import com.constants.BeanConstants;
import com.constants.MessageConstants;
import com.model.ErrorPacket;
import com.service.components.CommonsService;
import com.utils.ExceptionUtils;
import com.utils.LoginUtils;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.utils.localization.Message;

public class ErrorServlet extends HttpServlet implements MessageConstants {

	private static final long serialVersionUID = 1819743056284692668L;

	@Override
	public void service (
		final HttpServletRequest request,
		final HttpServletResponse response
	) throws ServletException, IOException {
	     // Analyze the servlet exception       
	     Throwable throwable = (Throwable)
	     request.getAttribute("javax.servlet.error.exception");
	     Integer statusCode = (Integer)
	     request.getAttribute("javax.servlet.error.status_code");
	     String servletName = (String)
	     request.getAttribute("javax.servlet.error.servlet_name");
	        
	     if (servletName == null) {
	        servletName = "Unknown";
	     }
	     String requestUri = (String)
	     request.getAttribute("javax.servlet.error.request_uri");
	     
	     if (requestUri == null) {
	        requestUri = "Unknown";
	     }
	
	     String errorText = "";
	     if (throwable == null && statusCode == null) {
	    	 errorText = "Error information is missing";
	     } else {
	    	 errorText = ExceptionUtils.generateErrorLog(throwable);
	     }
	     final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), requestUri + LINE_BREAK + LoginUtils.getLoggedInUserIdAndTypeForPrinting(request), errorText);
	     getCommonsService().feedErrorRecord(errorPacket);
	     WebServiceUtils.redirectToPage("/error.html", request, response);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}

	@Override
	public String getServletInfo() {
		return Message.getMessage(MessageConstants.APPLICATION_NAME) + ApplicationConstants.WHITESPACE + "ErrorServlet";
	}
}