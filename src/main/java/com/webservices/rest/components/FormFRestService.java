package com.webservices.rest.components;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.FormFConstants;
import com.exception.ApplicationException;
import com.model.components.formF.FormF;
import com.service.components.FormFService;
import com.utils.FileUtils;
import com.utils.LoggerUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_FORM_F) 
public class FormFRestService extends AbstractRestWebservice implements RestMethodConstants, FormFConstants {
	
	@Path(REST_METHOD_NAME_READ_FORM)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
	public FormF getForm (
    		@Context final HttpServletRequest request
	) throws IOException, JSONException {
		return getFormFService().getForm(getLoggedInEmpId(request));
    }
	
	/**
	 * JSON Object which this function accepts is as below
	 * 
	 * var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus							: 'SAVE_DRAFT',
			name 								: 'Demo Name',
			noticeDate 							:  new Date(),
			fullName 							: 'Demo Full Name',
			gender 								: 'M',
			doa 								:  new Date(),
			department 							: 'Demo Dept',
			employeeNumber 						: '11111',
			fathersName 						: 'Demo Father Name',
			husbandsName 						: 'NA',
			maritalStatus 						: 'S',
			dateOfBirth 						:  new Date(),
			permanentAddress 					: 'Demo Address Line 1, \nDemo Address Line 2, \nDemo Address Line 3',
			state 								: 'UP',
			pincode 							: '222222',
			appointmentDate						:  new Date(),
			designation							: 'Prog Analyst',
			familyFlagString					: 'Y',
			selfDependents						: 'Father',
			husbandDependents   				: 'Both',
			employeeGratuityNomineeDetailsList	: [
				{
					id							:  1, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '1st Modified Demo Name and Address',
					relationship				: 'S',
					age							: 26,
					percentage					: 30,
					deleteRecord				: false // Mark this true if you would like to delete this record
				},
				{
					id							:  2, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '2nd Demo Name and Address',
					relationship				: 'W',
					age							: 28,
					percentage					: 70,
					deleteRecord				: false // Mark this true if you would like to delete this record
				},
				{
					id							:  3, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '3rd Demo Name and Address',
					relationship				: 'W',
					age							: 28,
					percentage					: 70,
					deleteRecord				: false // Mark this true if you would like to delete this record
				}
			]
		};
	 * 
	 * @param form
	 * @param request
	 * @throws IOException
	 * @throws JSONException
	 */
	@Path(REST_METHOD_NAME_SAVE_OR_UPDATE_FORM)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public void saveOrUpdateForm (
			final FormF form,
			@Context final HttpServletRequest request
	) throws IOException, JSONException {
		getFormFService().saveOrUpdateForm(getLoggedInUser(request), form);
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_FORM_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadForm (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		final String empId = getLoggedInEmpId(request);
		if (null != empId && !EMPTY_STRING.equals(empId.trim())) {
			FileUtils.writeFileToResponse(response, empId + DOWNLOADED_PDF_FORM_NAME + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getFormFService().downloadForm(empId));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_EMP_ID_IN_REQUEST);
    }
	
	public FormFService getFormFService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_FORMF_SERVICE, FormFService.class);
	}
	
	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		LoggerUtils.logOnConsole("In FormF");
		// TODO Auto-generated method stub
		return true;
	}
}
