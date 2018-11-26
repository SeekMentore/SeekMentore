package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for extracting data from HttpServletRequest objects.
 * 
 */
public class HttpRequestUtil {
	
	/**
	 * Searches for a cookie with the specified name.
	 * 
	 * @param request
	 * @param cookieName
	 * @return the cookie matching the given name.
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int curCookie = 0;
			while (curCookie < cookies.length && !cookies[curCookie].getName().equals(cookieName)) curCookie ++;
			
			if (curCookie < cookies.length) return cookies[curCookie];
		}
		
		return null;
	}
	
	/**
	 * Gets the domain of the incoming request (and essentially where the servlet is currently located). This
	 * ignores subdomains and only returns the main domain of the request. For example, customer.dev.omnitracs.com
	 * would return a domain of omnitracs.com.
	 * 
	 * @param request
	 * @return domain of the incomming request
	 */
	public static String getDomain(HttpServletRequest request) {
		return getDomain(request.getRequestURL().toString());
	}
	
	/**
	 * Gets the domain of the incoming request (and essentially where the servlet is currently located). This
	 * ignores subdomains and only returns the main domain of the request. For example, customer.dev.omnitracs.com
	 * would return a domain of omnitracs.com.
	 * 
	 * @param url
	 * @return domain of the incomming request
	 */
	public static String getDomain(String url) {
		StringBuffer urlBuffer = new StringBuffer(url);
		// https://customer.dev.omnitracs.com:8080/blah
		if (urlBuffer.indexOf("https://") > -1) {
			urlBuffer.delete(0, 8);
		} else {
			urlBuffer.delete(0, 7);
		}
		
		// customer.dev.omnitracs.com:8080/blah
		if (urlBuffer.indexOf("/") > -1) {
			urlBuffer.delete(urlBuffer.indexOf("/"), urlBuffer.length());
		}
		
		// customer.dev.omnitracs.com:8080
		int mainDomainIndex = urlBuffer.toString().lastIndexOf(".", urlBuffer.toString().lastIndexOf(".") - 1);
		//if (mainDomainIndex == -1) mainDomainIndex = 0;
		urlBuffer.delete(0, mainDomainIndex + 1);
		
		// omnitracs.com:8080
		int portIndex = urlBuffer.indexOf(":");
		if (portIndex > -1) {
			urlBuffer.delete(portIndex, urlBuffer.length());
		}
		
		// omnitracs.com
		return urlBuffer.toString();
	}
	
	/**
	 * Gets the environment of the request by search for key terms.
	 * 
	 * @param request
	 * @return environment of the request
	 */
	public static String getEnvironment(HttpServletRequest request) {
		return getEnvironment(request.getRequestURL().toString());
	}
	
	/**
	 * Gets the environment of the request by searching for key terms.
	 * 
	 * @param url
	 * @return environment of the request
	 */
	public static String getEnvironment(String url) {
		if (url.contains(".dev.")) return ".dev";
		else if (url.contains(".int.")) return ".int";
		else if (url.contains(".uat.")) return ".uat";
		else if (url.contains(".stage.")) return ".stage";
		else if (url.contains(".cert.")) return ".cert";
		else if (url.contains(".perf.")) return ".perf";
		else if (url.contains(".sbx.")) return ".sbx";
		else if (url.contains(".cusp.")) return ".cusp";
		else if (url.contains(".stg.")) return ".stg";
		else if (url.contains(".omg.")) return ".omg";
		else return "";
	}
	
	/**
	 * Gets the environment and domain of the incoming request (and essentially where the servlet is currently located). 
	 * For example, customer.dev.omnitracs.com would return a domain of dev.omnitracs.com.
	 * 
	 * @param request
	 * @return domain of the incomming request
	 */
	public static String getFullDomain(HttpServletRequest request) {
		return getFullDomain(request.getRequestURL().toString());
	}
	
	/**
	 * Gets the environment and domain of the incoming request (and essentially where the servlet is currently located). 
	 * For example, customer.dev.omnitracs.com would return a domain of dev.omnitracs.com.
	 * 
	 * @param request
	 * @return domain of the incomming request
	 */
	public static String getFullDomain(String  url) {
		return getEnvironment(url) + "." + getDomain(url);
	}
	
	public static String getPrefix(HttpServletRequest request) {
		return getPrefix(request.getRequestURL().toString());
	}
	
	public static String getPrefix(String url) {
		StringBuffer urlBuffer = new StringBuffer(url);
		// https://customer.dev.omnitracs.com:8080/blah
		if (urlBuffer.indexOf("https://") > -1) {
			urlBuffer.delete(0, 8);
		} else {
			urlBuffer.delete(0, 7);
		}
		
		// customer.dev.omnitracs.com:8080/blah
		if (urlBuffer.indexOf("/") > -1) {
			urlBuffer.delete(urlBuffer.indexOf("/"), urlBuffer.length());
		}
		
		// customer.dev.omnitracs.com:8080
		int mainDomainIndex = urlBuffer.toString().lastIndexOf(".", urlBuffer.toString().lastIndexOf(".") - 1);
		//if (mainDomainIndex == -1) mainDomainIndex = 0;
		urlBuffer.delete(mainDomainIndex, urlBuffer.length());
		
		// customer
		return urlBuffer.toString();
	}
	
	/**
	 * Gets the Services Portal instance of the incoming request. 
	 * For example, customer.dev.omnitracs.com would return an instance of 1.
	 * 
	 * @param request
	 * @return SP instance of the incomming request
	 */
	public static String getSpInstance(HttpServletRequest request) {
		return getSpInstance(request.getRequestURL().toString());
	}
	
	/**
	 * Gets the Services Portal instance of the incoming request. 
	 * For example, customer.dev.omnitracs.com would return an instance of 1.
	 * 
	 * @param request
	 * @return SP instance of the incomming request
	 */
	public static String getSpInstance(String url) {
		String domain = getDomain(url);
		String spInstance = "1";
		if (domain != null) {
			if (domain.equalsIgnoreCase("omnitracs-mhub.com")) {
				spInstance = "2";
			} else if (domain.equalsIgnoreCase("myshawtracking.ca")) {
				spInstance = "2";
			} else if (domain.equalsIgnoreCase("cnrportal.com")) {
				spInstance = "3";
			}
		}
		
		return spInstance;
	}
	
	
	
	/**
	 * Gets the server number the request is being served by.
	 * 
	 * @param request
	 * @return number of the request
	 */
	public static String getServerNumber(HttpServletRequest request) {
		String serverName = request.getLocalName() == null ? "" : request.getLocalName();
		
		String serverNumber = serverName.replaceAll("[^0-9]", "");
		
		return serverNumber;
	}
	
	/**
	 * Searches for a version 1 cookie with the specified name.
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookieV1(HttpServletRequest request, String cookieName) {
		Cookie cookie = null;
		
		String cookieHeader = request.getHeader("Cookie");
		if (cookieHeader != null) {
			String[] cookiesRaw = cookieHeader.split("; ");
	        for (int i = 0; i < cookiesRaw.length; i++)
	        {
	            String[] parts = cookiesRaw[i].split("=", 2);
	            String value = parts.length > 1 ? parts[1] : "";
	            if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\""))
	            {
	                value = value.substring(1, value.length() - 1);
	            }
	            if (parts[0].equals(cookieName)) {
		            cookie = new Cookie(parts[0], value);
		            cookie.setVersion(1);
	            }
	        }
		}
		
		return cookie;
	}
}
