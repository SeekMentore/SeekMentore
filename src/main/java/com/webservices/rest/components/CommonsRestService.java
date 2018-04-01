package com.webservices.rest.components;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.CommonsConstants;
import com.service.components.CommonsService;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_COMMONS) 
public class CommonsRestService extends AbstractRestWebservice implements RestMethodConstants, CommonsConstants {
	
	@Path(REST_METHOD_NAME_GET_COUNTRY_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public void getCountryList (
			@Context final HttpServletRequest request
	) throws IOException, JSONException {
		getCommonsService().testJDBCConnection();
	}
	
	@Path(REST_METHOD_NAME_GET_STATE_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
    public void getStateList (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		
    }
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}
}
