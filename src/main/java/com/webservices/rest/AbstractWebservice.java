package com.webservices.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.ApplicationConstants;
import com.constants.FileConstants;
import com.model.ApplicationFile;
import com.model.User;
import com.utils.FileUtils;
import com.utils.LoginUtils;
import com.utils.ValidationUtils;
import com.utils.WebServiceUtils;

public abstract class AbstractWebservice implements ApplicationConstants {
	
	public String getLoggedInUserId(final HttpServletRequest request) throws Exception {
		return LoginUtils.getUserFromSession(request).getUserId();
	}
	
	public String getLoggedInUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		return LoginUtils.getLoggedInUserIdAndTypeForPrinting(request);
	}
	
	public User getLoggedInUser(final HttpServletRequest request) throws Exception {
		return LoginUtils.getUserFromSession(request);
	}
	
	public String getLoggedInUserType(final HttpServletRequest request) {
		return LoginUtils.getUserTypeFromSession(request);
	}
	
	public String getActiveUserId(final HttpServletRequest request) throws Exception {
		return getLoggedInUserId(request);
	}
	
	public String getActiveUserIdAndTypeForPrinting(final HttpServletRequest request) throws Exception {
		return LoginUtils.getActiveUserIdAndTypeForPrintingWithExceptionHandled(request);
	}
	
	public User getActiveUser(final HttpServletRequest request) throws Exception {
		return getLoggedInUser(request);
	}
	
	public String getActiveUserType(final HttpServletRequest request) {
		return getLoggedInUserType(request);
	}
	
	public String getServerURL(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI()));
	}
	
	public String getServerURLWithContextPath(final HttpServletRequest request) {
		return String.valueOf(request.getRequestURL()).substring(0, String.valueOf(request.getRequestURL()).indexOf(request.getRequestURI())) + request.getContextPath();
	}
	
	public void downloadFile(final ApplicationFile applicationFile, final HttpServletResponse response) throws IOException {
		WebServiceUtils.writeFileToResponse(response, applicationFile.getFilename(), FileConstants.APPLICATION_TYPE_OCTET_STEAM, applicationFile.getContent());
	}
	
	public void downloadFile(final List<ApplicationFile> applicationFiles, final HttpServletResponse response) throws IOException {
		if (ValidationUtils.checkNonEmptyList(applicationFiles)) {
			if (applicationFiles.size() == 1) {
				downloadFile(applicationFiles.get(0), response);
			} else {
				downloadZipFile(applicationFiles, "BUNDLED_FILES" + PERIOD + FileConstants.EXTENSION_ZIP, response);
			}
		}
	}
	
	public void downloadZipFile(final List<ApplicationFile> applicationFiles, final String zipFilename, final HttpServletResponse response) throws IOException {
		if (ValidationUtils.checkNonEmptyList(applicationFiles)) {
			if (applicationFiles.size() == 1) {
				downloadFile(applicationFiles.get(0), response);
			} else {
				WebServiceUtils.writeFileToResponse(response, zipFilename, FileConstants.APPLICATION_TYPE_OCTET_STEAM, FileUtils.getZippedBytes(applicationFiles));
			}
		}
	}
}
