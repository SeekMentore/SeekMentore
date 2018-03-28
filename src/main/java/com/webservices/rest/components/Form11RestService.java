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
import com.constants.components.Form11Constants;
import com.exception.ApplicationException;
import com.model.components.form11.Form11;
import com.service.components.Form11Service;
import com.utils.FileUtils;
import com.utils.LoggerUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_FORM_11) 
public class Form11RestService extends AbstractRestWebservice implements RestMethodConstants, Form11Constants {
	
	@Path(REST_METHOD_NAME_READ_FORM)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public Form11 getForm (
    		@Context final HttpServletRequest request
	) throws IOException, JSONException {
		return getForm11Service().getForm(getLoggedInEmpId(request));
    }
	
	/**
	 * JSON Object which this function accepts is as below
	 * 
	 * var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus						: 'SAVE_DRAFT',
			name 							: 'Demo Name',
			dateOfBirth 					:  new Date(),
			fatherOrHusbandsName 			: 'Demo Father Name',
			relationWithAbove 				: 'F',
			gender 							: 'M',
			mobileNumber 					: '9999999999',
			emailId 						: 'xxxxxxxxxxxxxxxxx@gmail.com',
			earlierMemberOfPF1952 			: 'N',
			earlierMemberOfPF1995 			: 'Y',
			uan 							: '123456789',
			pfMemberId 						: 'MMO12345',
			dateOfExit 						:  new Date(),
			schemeCertificateNumber 		: '987654321',
			ppoNumber 						: '999999999',
			internationalWorker 			: 'N',
			countryOfOrigin 				: 'IND',
			passportNumber 					: 'X123456',
			passportValidFrom 				:  new Date(),
			passportValidTo 				:  new Date(),
			educationalQualification 		: 'Bachelor of Technology',
			maritalStatus 					: 'S',
			speciallyAbled 					: 'N',
			category 						: 'P',
			nonContributoryDays				:  12,
			pfAccNumber						: '123546788',
			establishNameWithAddr			: 'Demo Establish Name With Addr',
			trustNameWithAddr				: 'Demo Trust Name With Addr',
			dateOfJoining					:  new Date(),
			kycDetailsList					: [
				{
					id						:  4, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '1st Document Type',
					documentName			: '1st Demo Document Name',
					documentNumber			: '12345',
					remarks					: '1st Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					id						:  6, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '2nd Demo Document Type',
					documentName			: '2nd Demo Document Name',
					documentNumber			: '67890',
					remarks					: '2nd Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					id						:  2, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '3nd Demo Document Type',
					documentName			: '3nd Demo Document Name',
					documentNumber			: '67890',
					remarks					: '3nd Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
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
			final Form11 form,
			@Context final HttpServletRequest request
	) throws IOException, JSONException {
		getForm11Service().saveOrUpdateForm(getLoggedInUser(request), form);
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
			FileUtils.writeFileToResponse(response, empId + DOWNLOADED_PDF_FORM_NAME  + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getForm11Service().downloadForm(empId));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_EMP_ID_IN_REQUEST);
    }
	
	public Form11Service getForm11Service() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_FORM11_SERVICE, Form11Service.class);
	}

	@Override
	public boolean doSecurity(final HttpServletRequest request) {
		LoggerUtils.logOnConsole("In Form11");
		// TODO Auto-generated method stub
		return true;
	}
}
