package com.view.http.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter("/CORSFilter")
public class CORSFilter extends OncePerRequestFilter {	

	@Override
	protected void doFilterInternal (
			final HttpServletRequest request, 
			final HttpServletResponse response, 
			final FilterChain filterChain
	) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		filterChain.doFilter(request, response);
	}
}
