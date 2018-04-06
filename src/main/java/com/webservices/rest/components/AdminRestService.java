package com.webservices.rest.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.AdminConstants;
import com.exception.ApplicationException;
import com.service.components.AdminService;
import com.utils.FileUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ADMIN) 
public class AdminRestService extends AbstractRestWebservice implements RestMethodConstants, AdminConstants {
	
	@Path(REST_METHOD_NAME_DOWNLOAD_REPORT)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadReport (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		final String empId = getLoggedInEmpId(request);
		if (null != empId && !EMPTY_STRING.equals(empId.trim())) {
			FileUtils.writeFileToResponse(response, DOWNLOAD_EXCEL_REPORT_NAME + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getAdminService().downloadReport(empId));
		}
		else
			throw new ApplicationException("EXCEPTION_NO_EMP_ID_IN_REQUEST");
    }
	
	public AdminService getAdminService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ADMIN_SERVICE, AdminService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
}
