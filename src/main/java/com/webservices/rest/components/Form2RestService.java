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
import com.constants.components.Form2Constants;
import com.exception.ApplicationException;
import com.model.components.form2.Form2;
import com.service.components.Form2Service;
import com.utils.FileUtils;
import com.utils.LoggerUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_FORM_2) 
public class Form2RestService extends AbstractRestWebservice implements RestMethodConstants, Form2Constants {
	
	@Path(REST_METHOD_NAME_READ_FORM)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
	public Form2 getForm (
    		@Context final HttpServletRequest request
	) throws IOException, JSONException {
		return getForm2Service().getForm(getLoggedInEmpId(request));
    }
	
	/**
	 * JSON Object which this function accepts is as below
	 * 
	 * var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus						: 'SAVE_DRAFT',
			name 							: 'Demo Name',
			fathersName 					: 'Demo Father Name',
			maritalStatus 					: 'S',
			accountNumber 					: '123456789',
			permanentAddress 				: 'Demo Address Line 1, \nDemo Address Line 2, \nDemo Address Line 3',
			temporaryAddress 				: 'Demo Temporary Address Line 1, \nDemo Temporary Address Line 2, \nDemo Temporary Address Line 3',
			gender 							: 'M',
			temporaryAddressSameAsPermament : 'N',
			dateOfBirth 					: new Date(),
			dateOfJoiningEPF 				: new Date(),
			dateOfJoiningEPS 				: new Date(),
			employeePFNominationDetailsList	: [
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '1st Demo Name and Address for relation',
					relationship			: '1st Demo Relation',
					dateOfBirth				: new Date(),
					percentage				: 89,
					guardian				: '1st Demo Guardian',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '2nd Demo Name and Address for relation',
					relationship			: '2nd Demo Relation',
					dateOfBirth				: new Date(),
					percentage				: 11,
					guardian				: '2nd Demo Guardian',
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			],
			widowChildrenPensionNomineeList	: [
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					name 					: '1st Demo Name',
					address					: '1st Demo Address',
					dateOfBirth				: new Date(),
					relationship			: '1st Demo Relation',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					name 					: '2nd Demo Name',
					address					: '2nd Demo Address',
					dateOfBirth				: new Date(),
					relationship			: '2nd Demo Relation',
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			],
			onlyWidowPensionNomineeList	: [
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '1st Demo Name and Address for relation',
					relationship			: '1st Demo Relation',
					dateOfBirth				: new Date(),
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '2nd Demo Name and Address for relation',
					relationship			: '2nd Demo Relation',
					dateOfBirth				: new Date(),
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			]
		};
	 * 
	 * @param request
	 * @throws IOException
	 * @throws JSONException
	 */
	@Path(REST_METHOD_NAME_SAVE_OR_UPDATE_FORM)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public void saveOrUpdateForm (
			final Form2 form,
			@Context final HttpServletRequest request
	) throws IOException, JSONException {
		getForm2Service().saveOrUpdateForm(getLoggedInUser(request), form);
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
			FileUtils.writeFileToResponse(response, empId + DOWNLOADED_PDF_FORM_NAME + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getForm2Service().downloadForm(empId));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_EMP_ID_IN_REQUEST);
    }
	
	public Form2Service getForm2Service() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_FORM2_SERVICE, Form2Service.class);
	}
	
	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		LoggerUtils.logOnConsole("In Form2");
		// TODO Auto-generated method stub
		return true;
	}
}
