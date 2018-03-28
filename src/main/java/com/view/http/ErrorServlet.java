package com.view.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.ApplicationConstants;
import com.constants.MessageConstants;
import com.utils.localization.Message;

public class ErrorServlet extends HttpServlet implements MessageConstants {

	private static final long serialVersionUID = 1819743056284692668L;

	@Override
	public void service (
		final HttpServletRequest request,
		final HttpServletResponse response
	) throws ServletException, IOException {
		System.out.println("Inside here");
	}

	@Override
	public String getServletInfo() {
		return Message.getMessage(MessageConstants.APPLICATION_NAME) + ApplicationConstants.WHITESPACE + "ErrorServlet";
	}
}