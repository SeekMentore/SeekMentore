package com.webservices.rest.components.old;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.EnquiryConstants;
import com.model.ErrorPacket;
import com.model.components.Enquiry;
import com.model.components.TutorMapper;
import com.service.components.CommonsService;
import com.service.components.EnquiryService;
import com.utils.ApplicationUtils;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_ENQUIRY+"old") 
public class EnquiryRestServiceOld extends AbstractRestWebservice implements RestMethodConstants, EnquiryConstants {
	
	private Long customerId;
	private Long enquiryId;
	private Date scheduledDemoDateAndTime;
	private Long tutorMapperId;
	private String selectedEligibleTutorIdSemicolonSeparatedList;
	private String selectedTutorMappedIdSemicolonSeparatedList;
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithPendingEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayCustomerWithEnquiries(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithMappedEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayCustomerWithEnquiries(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String displayCustomerWithAbandonedEnquiries (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayCustomerWithEnquiries(REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_ALL_ENQUIRIES_FOR_PARTICULAR_CUSTOMER)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String displayAllEnquiriesForParticularCustomer (
			@FormParam("customerId") final Long customerId,
			@FormParam("grid") final String grid,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_ALL_ENQUIRIES_FOR_PARTICULAR_CUSTOMER;
		this.customerId = customerId;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayAllEnquiriesForParticularCustomer(customerId, grid, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FOR_ENQUIRY_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataForEnquiryDetails (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FOR_ENQUIRY_DETAILS;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().getDropdownListData(), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_UPDATE_ENQUIRY_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String updateEnquiryDetails (
			final Enquiry enquiryObject,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_UPDATE_ENQUIRY_DETAILS;
		this.enquiryId = enquiryObject.getEnquiryId();
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().updateEnquiryDetails(enquiryObject, getActiveUser(request)), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_ALL_ELIGIBLE_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String displayAllEligibleTutors (
			@FormParam("enquiryId") final Long enquiryId,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_ALL_ELIGIBLE_TUTORS;
		this.enquiryId = enquiryId;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayAllEligibleTutors(enquiryId, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_MAP_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String mapTutors (
			@FormParam("enquiryId") final Long enquiryId,
			@FormParam("selectedEligibleTutorIdSemicolonSeparatedList") final String selectedEligibleTutorIdSemicolonSeparatedList,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_MAP_TUTORS;
		this.enquiryId = enquiryId;
		this.selectedEligibleTutorIdSemicolonSeparatedList = selectedEligibleTutorIdSemicolonSeparatedList;
		doSecurity(request);
		if (this.securityPassed) {
			final List<Long> tutorIdList = new ArrayList<Long>();
			for (final String tutorId: selectedEligibleTutorIdSemicolonSeparatedList.split(SEMICOLON)) {
				if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorId)) {
					tutorIdList.add(Long.parseLong(tutorId));
				}
			}
			return JSONUtils.convertObjToJSONString(getEnquiryService().mapTutors(enquiryId, tutorIdList), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String displayAllMappedDemoPendingTutors (
			@FormParam("enquiryId") final Long enquiryId,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS;
		this.enquiryId = enquiryId;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayAllMappedTutors(enquiryId, REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_UNMAP_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String unmapTutors (
			@FormParam("selectedTutorMappedIdSemicolonSeparatedList") final String selectedTutorMappedIdSemicolonSeparatedList,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UNMAP_TUTORS;
		this.selectedTutorMappedIdSemicolonSeparatedList = selectedTutorMappedIdSemicolonSeparatedList;
		doSecurity(request);
		if (this.securityPassed) {
			final List<Long> tutorMapperIdList = new ArrayList<Long>();
			for (final String tutorMapperId: selectedTutorMappedIdSemicolonSeparatedList.split(SEMICOLON)) {
				if (ValidationUtils.validatePlainNotNullAndEmptyTextString(tutorMapperId)) {
					tutorMapperIdList.add(Long.parseLong(tutorMapperId));
				}
			}
			return JSONUtils.convertObjToJSONString(getEnquiryService().unmapTutors(tutorMapperIdList), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String displayAllMappedDemoScheduledTutors (
			@FormParam("enquiryId") final Long enquiryId,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS;
		this.enquiryId = enquiryId;
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().displayAllMappedTutors(enquiryId, REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS, LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_TO_UPDATE_TUTOR_MAPPER_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String updateTutorMapperDetails (
			final TutorMapper tutorMapperObject,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_UPDATE_TUTOR_MAPPER_DETAILS;
		this.tutorMapperId = tutorMapperObject.getTutorMapperId();
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().updateTutorMapperDetails(tutorMapperObject, getActiveUser(request)), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_SCHEDULE_DEMO)
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public String scheduleDemo (
			@FormParam("tutorMapperId") final Long tutorMapperId,
			@FormParam("demoTimeInMillis") final Long demoTimeInMillis,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_SCHEDULE_DEMO;
		this.tutorMapperId = tutorMapperId;
		this.scheduledDemoDateAndTime = new Date(demoTimeInMillis);
		doSecurity(request);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getEnquiryService().scheduleDemo(tutorMapperId, scheduledDemoDateAndTime, getActiveUser(request)), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	public EnquiryService getEnquiryService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_ENQUIRY_SERVICE, EnquiryService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) throws Exception {
		this.request = request;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES : 
			case REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES :
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_FOR_ENQUIRY_DETAILS :{
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DISPLAY_ALL_ENQUIRIES_FOR_PARTICULAR_CUSTOMER : {
				handleParticularCustomerActionSecurity();
				break;
			}
			case REST_METHOD_NAME_TO_UPDATE_ENQUIRY_DETAILS :
			case REST_METHOD_NAME_DISPLAY_ALL_ELIGIBLE_TUTORS :
			case REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_PENDING_TUTORS : 
			case REST_METHOD_NAME_DISPLAY_ALL_MAPPED_DEMO_SCHEDULED_TUTORS : {
				handleParticularEnquirySecurity();
				break;
			}
			case REST_METHOD_NAME_MAP_TUTORS : {
				handleParticularEnquiryTutorMappingSecurity();
				break;
			}
			case REST_METHOD_NAME_UNMAP_TUTORS : {
				handleParticularEnquiryTutorUnMappingSecurity();
				break;
			}
			case REST_METHOD_NAME_TO_UPDATE_TUTOR_MAPPER_DETAILS : {
				handleParticularTutorMapperSecurity();
				break;
			}
			case REST_METHOD_NAME_SCHEDULE_DEMO : {
				handleScheduledDemoSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleParticularCustomerActionSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.customerId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_CUSTOMER_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.customerId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleParticularEnquirySecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.enquiryId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_ENQUIRY_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.enquiryId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleParticularEnquiryTutorMappingSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.enquiryId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_ENQUIRY_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.selectedEligibleTutorIdSemicolonSeparatedList)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID_LIST,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.selectedEligibleTutorIdSemicolonSeparatedList);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleParticularEnquiryTutorUnMappingSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.selectedTutorMappedIdSemicolonSeparatedList)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID_LIST,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.selectedTutorMappedIdSemicolonSeparatedList);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleParticularTutorMapperSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.tutorMapperId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleScheduledDemoSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorMapperId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_MAPPER_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateDate(this.scheduledDemoDateAndTime)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_SCHEDULE_TIME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.tutorMapperId + LINE_BREAK + this.scheduledDemoDateAndTime);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
