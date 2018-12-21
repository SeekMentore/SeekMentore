package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Complaint;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.gridcomponent.GridComponent;
import com.model.rowmappers.BecomeTutorRowMapper;
import com.model.rowmappers.ComplaintRowMapper;
import com.model.rowmappers.FindTutorRowMapper;
import com.model.rowmappers.SubmitQueryRowMapper;
import com.model.rowmappers.SubscribeWithUsRowMapper;
import com.model.workbook.WorkbookReport;
import com.utils.ApplicationUtils;
import com.utils.GridQueryUtils;
import com.utils.PDFUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_ADMIN_SERVICE)
public class AdminService implements AdminConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	/*
	 * Tutor Registration Admin
	 */
	public byte[] downloadAdminReportTutorRegistrations() throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
//		workbookReport.createSheet("NON_CONTACTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("NON_VERIFIED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("VERIFIED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("VERIFICATION_FAILED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("TO_BE_RECONTACTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("SELECTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("REJECTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
//		workbookReport.createSheet("REGISTERED_TUTORS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public byte[] downloadAdminTutorRegistrationProfilePdf(final String tentativeTutorId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tentativeTutorId", tentativeTutorId);
		final BecomeTutor tutorRegisterObject = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId", paramsMap, new BecomeTutorRowMapper());
		if (null != tutorRegisterObject) {
			replacePlaceHolderAndIdsFromTutorRegistrationObject(tutorRegisterObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInTutorRegistrationObject(tutorRegisterObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("tutorRegisterObject", tutorRegisterObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(TUTOR_REGISTER_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}	
	
	public List<BecomeTutor> displayTutorRegistrations(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM BECOME_TUTOR WHERE ");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'N' AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED IS NULL AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'Y' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'N' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_TO_BE_RECONTACTED = 'Y' AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_SELECTED = 'Y' AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_REJECTED = 'Y' AND IS_DATA_MIGRATED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS : {
				query.append("IS_DATA_MIGRATED = 'Y'");
				break;
			}
		}
		final List<BecomeTutor> tutorRegisterList = applicationDao.findAllWithoutParams(query.toString(), new BecomeTutorRowMapper());
		for (final BecomeTutor tutorRegisterObject : tutorRegisterList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromTutorRegistrationObject(tutorRegisterObject, delimiter);
		}
		return tutorRegisterList;
	}
	
	private void replacePlaceHolderAndIdsFromTutorRegistrationObject(final BecomeTutor tutorRegisterObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		tutorRegisterObject.setGender(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP,tutorRegisterObject.getGender(), false, delimiter));
		tutorRegisterObject.setQualification(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP,tutorRegisterObject.getQualification(), false, delimiter));
		tutorRegisterObject.setPrimaryProfession(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP,tutorRegisterObject.getPrimaryProfession(), false, delimiter));
		tutorRegisterObject.setTransportMode(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP,tutorRegisterObject.getTransportMode(), false, delimiter));
		tutorRegisterObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, tutorRegisterObject.getStudentGrade(), true, delimiter));
		tutorRegisterObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, tutorRegisterObject.getSubjects(), true, delimiter));
		tutorRegisterObject.setLocations(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, tutorRegisterObject.getLocations(), true, delimiter));
		tutorRegisterObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, tutorRegisterObject.getPreferredTimeToCall(), true, delimiter));
		tutorRegisterObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, tutorRegisterObject.getReference(), false, delimiter));
		tutorRegisterObject.setPreferredTeachingType(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, tutorRegisterObject.getPreferredTeachingType(), true, delimiter));
		tutorRegisterObject.setWhoContacted(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoContacted()));
		tutorRegisterObject.setWhoVerified(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoVerified()));
		tutorRegisterObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoSuggestedForRecontact()));
		tutorRegisterObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoRecontacted()));
		tutorRegisterObject.setWhoSelected(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoSelected()));
		tutorRegisterObject.setWhoRejected(commonsService.getNameOfUserFromUserId(tutorRegisterObject.getWhoRejected()));
		tutorRegisterObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsContacted()));
		tutorRegisterObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsAuthenticationVerified()));
		tutorRegisterObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsToBeRecontacted()));
		tutorRegisterObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsSelected()));
		tutorRegisterObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsRejected()));
		tutorRegisterObject.setReApplied(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getReApplied()));
		tutorRegisterObject.setIsDataMigrated(ApplicationUtils.setYesOrNoFromYN(tutorRegisterObject.getIsDataMigrated()));
	}
	
	private void replaceNullWithBlankRemarksInTutorRegistrationObject(final BecomeTutor tutorRegisterObject) {
		tutorRegisterObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getContactedRemarks()));
		tutorRegisterObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getVerificationRemarks()));
		tutorRegisterObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getSuggestionRemarks()));
		tutorRegisterObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getRecontactedRemarks()));
		tutorRegisterObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getSelectionRemarks()));
		tutorRegisterObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorRegisterObject.getRejectionRemarks()));
	}
	
	public Map<String, Object> takeActionOnTutorRegistration (
			final String gridName, 
			final String button, 
			final String tentativeTutorId,
			final String remarks,
			final User user
	) {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final StringBuilder query = new StringBuilder("UPDATE BECOME_TUTOR SET ");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", user.getUserId());
		paramsMap.put("remarks", remarks);
		paramsMap.put("tentativeTutorId", tentativeTutorId);
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE = SYSDATE(), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("APPLICATION_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE = SYSDATE(), REJECTION_REMARKS = :remarks, REJECTION_COUNT = (REJECTION_COUNT + 1), RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("APPLICATION_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE = SYSDATE(), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE = SYSDATE(), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
		}
		applicationDao.executeUpdate(query.toString(), paramsMap);
		return response;
	}
	/*
	 * Tutor Registration Admin
	 */
	/*
	 * Tutor Enquiry Admin
	 */
	public byte[] downloadAdminReportTutorEnquiry() throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
//		workbookReport.createSheet("NON_CONTACTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("NON_VERIFIED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("VERIFIED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("VERIFICATION_FAILED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("TO_BE_RECONTACTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("SELECTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
//		workbookReport.createSheet("REJECTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public byte[] downloadAdminTutorEnquiryProfilePdf(final String enquiryId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("enquiryId", enquiryId);
		final FindTutor tutorEnquiryObject = applicationDao.find("SELECT * FROM FIND_TUTOR WHERE ENQUIRY_ID = :enquiryId", paramsMap, new FindTutorRowMapper());
		if (null != tutorEnquiryObject) {
			replacePlaceHolderAndIdsFromTutorEnquiryObject(tutorEnquiryObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInTutorEnquiryObject(tutorEnquiryObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("tutorEnquiryObject", tutorEnquiryObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(TUTOR_ENQUIRY_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}
	
	public List<FindTutor> displayTutorEnquiries(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM FIND_TUTOR WHERE ");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'N'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED IS NULL AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'Y' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'N' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_TO_BE_RECONTACTED = 'Y' AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_SELECTED = 'Y'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES : {
				query.append("IS_CONTACTED = 'Y' AND IS_REJECTED = 'Y'");
				break;
			}
		}
		final List<FindTutor> tutorEnquiryList = applicationDao.findAllWithoutParams(query.toString(), new FindTutorRowMapper());
		for (final FindTutor tutorEnquiryObject : tutorEnquiryList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromTutorEnquiryObject(tutorEnquiryObject, delimiter);
		}
		return tutorEnquiryList;
	}
	
	private void replacePlaceHolderAndIdsFromTutorEnquiryObject(final FindTutor tutorEnquiryObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		tutorEnquiryObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, tutorEnquiryObject.getStudentGrade(), true, delimiter));
		tutorEnquiryObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, tutorEnquiryObject.getSubjects(), true, delimiter));
		tutorEnquiryObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, tutorEnquiryObject.getPreferredTimeToCall(), true, delimiter));
		tutorEnquiryObject.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, tutorEnquiryObject.getLocation(), true, delimiter));
		tutorEnquiryObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, tutorEnquiryObject.getReference(), false, delimiter));
		tutorEnquiryObject.setWhoContacted(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoContacted()));
		tutorEnquiryObject.setWhoVerified(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoVerified()));
		tutorEnquiryObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoSuggestedForRecontact()));
		tutorEnquiryObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoRecontacted()));
		tutorEnquiryObject.setWhoSelected(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoSelected()));
		tutorEnquiryObject.setWhoRejected(commonsService.getNameOfUserFromUserId(tutorEnquiryObject.getWhoRejected()));
		tutorEnquiryObject.setSubscribedCustomer(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getSubscribedCustomer()));
		tutorEnquiryObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getIsContacted()));
		tutorEnquiryObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getIsAuthenticationVerified()));
		tutorEnquiryObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getIsToBeRecontacted()));
		tutorEnquiryObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getIsSelected()));
		tutorEnquiryObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(tutorEnquiryObject.getIsRejected()));
	}
	
	private void replaceNullWithBlankRemarksInTutorEnquiryObject(final FindTutor tutorEnquiryObject) {
		tutorEnquiryObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getContactedRemarks()));
		tutorEnquiryObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getVerificationRemarks()));
		tutorEnquiryObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getSuggestionRemarks()));
		tutorEnquiryObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getRecontactedRemarks()));
		tutorEnquiryObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getSelectionRemarks()));
		tutorEnquiryObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(tutorEnquiryObject.getRejectionRemarks()));
	}
	
	public Map<String, Object> takeActionOnTutorEnquiry (
			final String gridName, 
			final String button, 
			final String enquiryId,
			final String remarks,
			final User user
	) {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final StringBuilder query = new StringBuilder("UPDATE FIND_TUTOR SET ");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", user.getUserId());
		paramsMap.put("remarks", remarks);
		paramsMap.put("enquiryId", enquiryId);
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("ENQUIRY_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("ENQUIRY_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE = SYSDATE(), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("ENQUIRY_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE = SYSDATE(), REJECTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("ENQUIRY_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("ENQUIRY_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("ENQUIRY_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE = SYSDATE(), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("ENQUIRY_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE = SYSDATE(), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
		}
		applicationDao.executeUpdate(query.toString(), paramsMap);
		return response;
	}
	/*
	 * Tutor Enquiry Admin
	 */
	
	/*
	 * Subscrition Admin
	 */
	public byte[] downloadAdminReportSubscriptions() throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
//		workbookReport.createSheet("NON_CONTACTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("NON_VERIFIED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("VERIFIED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("VERIFICATION_FAILED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("TO_BE_RECONTACTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("SELECTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
//		workbookReport.createSheet("REJECTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public byte[] downloadAdminIndividualSubscriptionProfilePdf(final String tentativeSubscriptionId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tentativeSubscriptionId", tentativeSubscriptionId);
		final SubscribeWithUs subscribeWithUsObject = applicationDao.find("SELECT * FROM SUBSCRIBE_WITH_US WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId", paramsMap, new SubscribeWithUsRowMapper());
		if (null != subscribeWithUsObject) {
			replacePlaceHolderAndIdsFromSubscribeWithUsObject(subscribeWithUsObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInSubscribeWithUsObject(subscribeWithUsObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("subscribeWithUsObject", subscribeWithUsObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(SUBSCRIBE_WITH_US_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}
	
	
	public List<SubscribeWithUs> displaySubscriptions(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM SUBSCRIBE_WITH_US WHERE ");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'N'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED IS NULL AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'Y' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'N' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_TO_BE_RECONTACTED = 'Y' AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_SELECTED = 'Y'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_REJECTED = 'Y'");
				break;
			}
		}
		final List<SubscribeWithUs> subscribeWithUsList = applicationDao.findAllWithoutParams(query.toString(), new SubscribeWithUsRowMapper());
		for (final SubscribeWithUs subscribeWithUsObject : subscribeWithUsList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromSubscribeWithUsObject(subscribeWithUsObject, delimiter);
		}
		return subscribeWithUsList;
	}
	
	private void replacePlaceHolderAndIdsFromSubscribeWithUsObject(final SubscribeWithUs subscribeWithUsObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		subscribeWithUsObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, subscribeWithUsObject.getStudentGrade(), true, delimiter));
		subscribeWithUsObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, subscribeWithUsObject.getSubjects(), true, delimiter));
		subscribeWithUsObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, subscribeWithUsObject.getPreferredTimeToCall(), true, delimiter));
		subscribeWithUsObject.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, subscribeWithUsObject.getLocation(), true, delimiter));
		subscribeWithUsObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, subscribeWithUsObject.getReference(), false, delimiter));
		subscribeWithUsObject.setWhoContacted(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoContacted()));
		subscribeWithUsObject.setWhoVerified(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoVerified()));
		subscribeWithUsObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoSuggestedForRecontact()));
		subscribeWithUsObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoRecontacted()));
		subscribeWithUsObject.setWhoSelected(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoSelected()));
		subscribeWithUsObject.setWhoRejected(commonsService.getNameOfUserFromUserId(subscribeWithUsObject.getWhoRejected()));
		subscribeWithUsObject.setSubscribedCustomer(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getSubscribedCustomer()));
		subscribeWithUsObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getIsContacted()));
		subscribeWithUsObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getIsAuthenticationVerified()));
		subscribeWithUsObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getIsToBeRecontacted()));
		subscribeWithUsObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getIsSelected()));
		subscribeWithUsObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(subscribeWithUsObject.getIsRejected()));
	}
	
	private void replaceNullWithBlankRemarksInSubscribeWithUsObject(final SubscribeWithUs subscribeWithUsObject) {
		subscribeWithUsObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getContactedRemarks()));
		subscribeWithUsObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getVerificationRemarks()));
		subscribeWithUsObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getSuggestionRemarks()));
		subscribeWithUsObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getRecontactedRemarks()));
		subscribeWithUsObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getSelectionRemarks()));
		subscribeWithUsObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribeWithUsObject.getRejectionRemarks()));
	}
	
	public Map<String, Object> takeActionOnSubscriptions (
			final String gridName, 
			final String button, 
			final String tentativeSubscriptionId,
			final String remarks,
			final User user
	) {
		final Map<String, Object> response = new HashMap<String, Object>();
		response.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		final StringBuilder query = new StringBuilder("UPDATE SUBSCRIBE_WITH_US SET ");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", user.getUserId());
		paramsMap.put("remarks", remarks);
		paramsMap.put("tentativeSubscriptionId", tentativeSubscriptionId);
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE = SYSDATE(), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("APPLICATION_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE = SYSDATE(), REJECTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("APPLICATION_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE = SYSDATE(), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE = SYSDATE(), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
		}
		applicationDao.executeUpdate(query.toString(), paramsMap);
		return response;
	}
	/*
	 * Subscrition Admin
	 * 
	 */
	
	/**************************************************************************************************/
	public List<BecomeTutor> getBecomeTutorList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "B.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_CONTACTED), B.WHO_CONTACTED) AS WHO_CONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_VERIFIED), B.WHO_VERIFIED) AS WHO_VERIFIED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_SUGGESTED_FOR_RECONTACT), B.WHO_SUGGESTED_FOR_RECONTACT) AS WHO_SUGGESTED_FOR_RECONTACT_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_RECONTACTED), B.WHO_RECONTACTED) AS WHO_RECONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_SELECTED), B.WHO_SELECTED) AS WHO_SELECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.WHO_REJECTED), B.WHO_REJECTED) AS WHO_REJECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = B.UPDATED_BY), B.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM BECOME_TUTOR B";
		String existingFilterQueryString = "";
		final String existingSorterQueryString = "ORDER BY APPLICATION_DATE_MILLIS DESC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'FRESH'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS IN ('CONTACTED_VERIFICATION_PENDING', 'RECONTACTED_VERIFICATION_PENDING')";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'VERIFICATION_FAILED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'SELECTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'REJECTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = "WHERE IS_DATA_MIGRATED = 'Y'";
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new BecomeTutorRowMapper());
	}
	
	public BecomeTutor getBecomeTutorApplicationInDatabaseWithEmailId(final String emailId) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE EMAIL_ID = :emailId", paramsMap, new BecomeTutorRowMapper());
	}
	
	public BecomeTutor getBecomeTutorApplicationInDatabaseWithContactNumber(final String contactNumber) throws DataAccessException, InstantiationException, IllegalAccessException {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE CONTACT_NUMBER = :contactNumber", paramsMap, new BecomeTutorRowMapper());
	}
	
	public byte[] downloadAdminReportBecomeTutorList(final String grid, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getBecomeTutorReportSheetName(grid), getBecomeTutorList(grid, gridComponent), BecomeTutor.class, SUPPORT_TEAM_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	private String getBecomeTutorReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST : {
				return "REJECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST : {
				return "REGISTERED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistBecomeTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE BECOME_TUTOR SET "
				+ "IS_BLACKLISTED = 'Y', "
				+ "BLACKLISTED_REMARKS = :comments, "
				+ "BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeTutorId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tentativeTutorId", tentativeTutorId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void unBlacklistBecomeTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE BECOME_TUTOR SET "
				+ "IS_BLACKLISTED = 'N', "
				+ "UN_BLACKLISTED_REMARKS = :comments, "
				+ "UN_BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_UN_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeTutorId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tentativeTutorId", tentativeTutorId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void takeActionOnBecomeTutor(final String button, final List<String> idList, final String comments, final User activeUser) {
		final StringBuilder query = new StringBuilder("UPDATE BECOME_TUTOR SET ");
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("APPLICATION_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), REJECTION_REMARKS = :remarks, REJECTION_COUNT = (REJECTION_COUNT + 1), RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("APPLICATION_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId");
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeTutorId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("tentativeTutorId", tentativeTutorId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void updateBecomeTutorRecord(final BecomeTutor becomeTutor, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE BECOME_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						updateAttributesQuery.add("FIRST_NAME = :firstName");
						paramsMap.put("firstName", becomeTutor.getFirstName());
						break;
					}
					case "lastName" : {
						updateAttributesQuery.add("LAST_NAME = :lastName");
						paramsMap.put("lastName", becomeTutor.getLastName());
						break;
					}
					case "dateOfBirth" : {
						updateAttributesQuery.add("DATE_OF_BIRTH = :dateOfBirth");
						paramsMap.put("dateOfBirth", becomeTutor.getDateOfBirth());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", becomeTutor.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", becomeTutor.getEmailId());
						break;
					}
					case "gender" : {
						updateAttributesQuery.add("GENDER = :gender");
						paramsMap.put("gender", becomeTutor.getGender());
						break;
					}
					case "qualification" : {
						updateAttributesQuery.add("QUALIFICATION = :qualification");
						paramsMap.put("qualification", becomeTutor.getQualification());
						break;
					}
					case "primaryProfession" : {
						updateAttributesQuery.add("PRIMARY_PROFESSION = :primaryProfession");
						paramsMap.put("primaryProfession", becomeTutor.getPrimaryProfession());
						break;
					}
					case "transportMode" : {
						updateAttributesQuery.add("TRANSPORT_MODE = :transportMode");
						paramsMap.put("transportMode", becomeTutor.getTransportMode());
						break;
					}
					case "teachingExp" : {
						updateAttributesQuery.add("TEACHING_EXP = :teachingExp");
						paramsMap.put("teachingExp", becomeTutor.getTeachingExp());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrades");
						paramsMap.put("studentGrades", becomeTutor.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", becomeTutor.getSubjects());
						break;
					}
					case "locations" : {
						updateAttributesQuery.add("LOCATIONS = :locations");
						paramsMap.put("locations", becomeTutor.getLocations());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", becomeTutor.getPreferredTimeToCall());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", becomeTutor.getAdditionalDetails());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", becomeTutor.getReference());
						break;
					}
					case "preferredTeachingType" : {
						updateAttributesQuery.add("PREFERRED_TEACHING_TYPE = :preferredTeachingType");
						paramsMap.put("preferredTeachingType", becomeTutor.getPreferredTeachingType());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", becomeTutor.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("tentativeTutorId", becomeTutor.getTentativeTutorId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<FindTutor> getFindTutorList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "F.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_CONTACTED), F.WHO_CONTACTED) AS WHO_CONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_VERIFIED), F.WHO_VERIFIED) AS WHO_VERIFIED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_SUGGESTED_FOR_RECONTACT), F.WHO_SUGGESTED_FOR_RECONTACT) AS WHO_SUGGESTED_FOR_RECONTACT_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_RECONTACTED), F.WHO_RECONTACTED) AS WHO_RECONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_SELECTED), F.WHO_SELECTED) AS WHO_SELECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.WHO_REJECTED), F.WHO_REJECTED) AS WHO_REJECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = F.UPDATED_BY), F.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM FIND_TUTOR F";
		String existingFilterQueryString = "";
		final String existingSorterQueryString = "ORDER BY ENQUIRY_DATE_MILLIS DESC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'FRESH'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS IN ('CONTACTED_VERIFICATION_PENDING', 'RECONTACTED_VERIFICATION_PENDING')";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'VERIFICATION_SUCCESSFUL'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'VERIFICATION_FAILED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'SUGGESTED_TO_BE_RECONTACTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'SELECTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST : {
				existingFilterQueryString = "WHERE ENQUIRY_STATUS = 'REJECTED'";
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new FindTutorRowMapper());
	}
	
	public byte[] downloadAdminReportFindTutorList(final String grid, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getFindTutorReportSheetName(grid), getFindTutorList(grid, gridComponent), FindTutor.class, SUPPORT_TEAM_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	private String getFindTutorReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_ENQUIRIES_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_ENQUIRIES_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_ENQUIRIES_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_ENQUIRIES_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_ENQUIRIES_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_ENQUIRIES_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_ENQUIRIES_LIST : {
				return "REJECTED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistFindTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE FIND_TUTOR SET "
				+ "IS_BLACKLISTED = 'Y', "
				+ "BLACKLISTED_REMARKS = :comments, "
				+ "BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE ENQUIRY_ID = :enquiryId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String enquiryId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("enquiryId", enquiryId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void unBlacklistFindTutorList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE FIND_TUTOR SET "
				+ "IS_BLACKLISTED = 'N', "
				+ "UN_BLACKLISTED_REMARKS = :comments, "
				+ "UN_BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_UN_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE ENQUIRY_ID = :enquiryId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String enquiryId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("enquiryId", enquiryId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void takeActionOnFindTutor(final String button, final List<String> idList, final String comments, final User activeUser) {
		final StringBuilder query = new StringBuilder("UPDATE FIND_TUTOR SET ");
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("ENQUIRY_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("ENQUIRY_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("ENQUIRY_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), REJECTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("ENQUIRY_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("ENQUIRY_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("ENQUIRY_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("ENQUIRY_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE ENQUIRY_ID = :enquiryId");
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String enquiryId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("enquiryId", enquiryId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void updateFindTutorRecord(final FindTutor findTutor, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE FIND_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE ENQUIRY_ID = :enquiryId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", findTutor.getName());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", findTutor.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", findTutor.getEmailId());
						break;
					}
					case "subscribedCustomer" : {
						updateAttributesQuery.add("SUBSCRIBED_CUSTOMER = :subscribedCustomer");
						paramsMap.put("subscribedCustomer", findTutor.getSubscribedCustomer());
						break;
					}
					case "studentGrade" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrade");
						paramsMap.put("studentGrade", findTutor.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", findTutor.getSubjects());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", findTutor.getPreferredTimeToCall());
						break;
					}
					case "location" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", findTutor.getLocation());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", findTutor.getReference());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", findTutor.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", findTutor.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("enquiryId", findTutor.getEnquiryId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<SubscribeWithUs> getSubscribeWithUsList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "S.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_CONTACTED), S.WHO_CONTACTED) AS WHO_CONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_VERIFIED), S.WHO_VERIFIED) AS WHO_VERIFIED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_SUGGESTED_FOR_RECONTACT), S.WHO_SUGGESTED_FOR_RECONTACT) AS WHO_SUGGESTED_FOR_RECONTACT_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_RECONTACTED), S.WHO_RECONTACTED) AS WHO_RECONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_SELECTED), S.WHO_SELECTED) AS WHO_SELECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.WHO_REJECTED), S.WHO_REJECTED) AS WHO_REJECTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = S.UPDATED_BY), S.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM SUBSCRIBE_WITH_US S";
		String existingFilterQueryString = "";
		final String existingSorterQueryString = "ORDER BY APPLICATION_DATE_MILLIS DESC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'FRESH'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS IN ('CONTACTED_VERIFICATION_PENDING', 'RECONTACTED_VERIFICATION_PENDING')";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'VERIFICATION_FAILED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'SELECTED'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = "WHERE APPLICATION_STATUS = 'REJECTED'";
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new SubscribeWithUsRowMapper());
	}
	
	public byte[] downloadAdminReportSubscribeWithUsList(final String grid, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getSubscribeWithUsReportSheetName(grid), getSubscribeWithUsList(grid, gridComponent), SubscribeWithUs.class, SUPPORT_TEAM_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	private String getSubscribeWithUsReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST : {
				return "REJECTED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistSubscriptionList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBE_WITH_US SET "
				+ "IS_BLACKLISTED = 'Y', "
				+ "BLACKLISTED_REMARKS = :comments, "
				+ "BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeSubscriptionId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tentativeSubscriptionId", tentativeSubscriptionId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void unBlacklistSubscriptionList(final List<String> idList, final String comments, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBE_WITH_US SET "
				+ "IS_BLACKLISTED = 'N', "
				+ "UN_BLACKLISTED_REMARKS = :comments, "
				+ "UN_BLACKLISTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "WHO_UN_BLACKLISTED = :userId, "
				+ "RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), "
				+ "UPDATED_BY = :userId "
				+ "WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId";
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeSubscriptionId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tentativeSubscriptionId", tentativeSubscriptionId);
			paramsMap.put("comments", comments);
			paramsMap.put("userId", activeUser.getUserId());
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void takeActionOnSubscription(final String button, final List<String> idList, final String comments, final User activeUser) {
		final StringBuilder query = new StringBuilder("UPDATE SUBSCRIBE_WITH_US SET ");
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = :userId, SUGGESTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SUGGESTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("APPLICATION_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), CONTACTED_REMARKS = :remarks, IS_REJECTED = 'Y', WHO_REJECTED = :userId, REJECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), REJECTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = :userId, VERIFICATION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), VERIFICATION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("APPLICATION_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = :userId, SELECTION_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), SELECTION_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = :userId, RECONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), RECONTACTED_REMARKS = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId");
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String tentativeSubscriptionId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("tentativeSubscriptionId", tentativeSubscriptionId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void updateSubscriptionRecord(final SubscribeWithUs subscription, final List<String> changedAttributes, final User activeUser) {
		final String baseQuery = "UPDATE SUBSCRIBE_WITH_US SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						updateAttributesQuery.add("FIRST_NAME = :firstName");
						paramsMap.put("firstName", subscription.getFirstName());
						break;
					}
					case "lastName" : {
						updateAttributesQuery.add("LAST_NAME = :lastName");
						paramsMap.put("lastName", subscription.getLastName());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", subscription.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", subscription.getEmailId());
						break;
					}
					case "subscribedCustomer" : {
						updateAttributesQuery.add("SUBSCRIBED_CUSTOMER = :subscribedCustomer");
						paramsMap.put("subscribedCustomer", subscription.getSubscribedCustomer());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrade");
						paramsMap.put("studentGrade", subscription.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", subscription.getSubjects());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", subscription.getPreferredTimeToCall());
						break;
					}
					case "locations" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", subscription.getLocation());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", subscription.getReference());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", subscription.getAddressDetails());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", subscription.getAdditionalDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("tentativeSubscriptionId", subscription.getTentativeSubscriptionId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000)");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<SubmitQuery> getSubmitQueryList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "Q.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = Q.WHO_CONTACTED), Q.WHO_CONTACTED) AS WHO_CONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = Q.WHO_NOT_ANSWERED), Q.WHO_NOT_ANSWERED) AS WHO_NOT_ANSWERED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = Q.UPDATED_BY), Q.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM SUBMIT_QUERY Q";
		String existingFilterQueryString = "";
		final String existingSorterQueryString = "ORDER BY QUERY_REQUESTED_DATE_MILLIS DESC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST : {
				existingFilterQueryString = "WHERE QUERY_STATUS = 'FRESH'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST : {
				existingFilterQueryString = "WHERE QUERY_STATUS = 'PUT_ON_HOLD'";
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ANSWERED_QUERY_LIST : {
				existingFilterQueryString = "WHERE QUERY_STATUS = 'RESPONDED'";
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new SubmitQueryRowMapper());
	}
	
	public byte[] downloadAdminReportSubmitQueryList(final String grid, final GridComponent gridComponent) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getSubmitQueryReportSheetName(grid), getSubmitQueryList(grid, gridComponent), SubmitQuery.class, SUPPORT_TEAM_REPORT);
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	private String getSubmitQueryReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST : {
				return "NOT_ANSWERED";
			}
			case RestMethodConstants.REST_METHOD_NAME_ANSWERED_QUERY_LIST : {
				return "ANSWERED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void takeActionOnSubmitQuery(final String button, final List<String> idList, final String comments, final User activeUser) {
		final StringBuilder query = new StringBuilder("UPDATE SUBMIT_QUERY SET ");
		switch(button) {
			case BUTTON_ACTION_RESPOND : {
				query.append("QUERY_STATUS = 'RESPONDED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), QUERY_RESPONSE = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE QUERY_ID = :queryId");
				break;
			}
			case BUTTON_ACTION_PUT_ON_HOLD : {
				query.append("QUERY_STATUS = 'PUT_ON_HOLD', NOT_ANSWERED = 'Y', WHO_NOT_ANSWERED = :userId, NOT_ANSWERED_REASON = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE QUERY_ID = :queryId");
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String queryId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("queryId", queryId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void updateSubmitQueryRecord(final SubmitQuery query, final List<String> changedAttributes, final User activeUser) {
		
	}
	
	public List<Complaint> getComplaintList(final String grid, final GridComponent gridComponent) throws DataAccessException, InstantiationException, IllegalAccessException {
		final String baseQuery = "SELECT "
				+ "C.*, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = C.WHO_CONTACTED), C.WHO_CONTACTED) AS WHO_CONTACTED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = C.WHO_NOT_RESOLVED), C.WHO_NOT_RESOLVED) AS WHO_NOT_RESOLVED_NAME, "
				+ "IFNULL((SELECT NAME FROM EMPLOYEE E WHERE E.USER_ID = C.UPDATED_BY), C.UPDATED_BY) AS UPDATED_BY_NAME "
				+ "FROM COMPLAINT C";
		String existingFilterQueryString = "";
		final String existingSorterQueryString = "ORDER BY COMPLAINT_FILED_DATE_MILLIS DESC";
		switch(grid) {
			case RestMethodConstants.REST_METHOD_CUSTOMER_COMPLAINT_LIST : {
				existingFilterQueryString = "WHERE COMPLAINT_STATUS = 'FRESH' AND COMPLAINT_USER = 'C'";
				break;
			}
			case RestMethodConstants.REST_METHOD_TUTOR_COMPLAINT_LIST : {
				existingFilterQueryString = "WHERE COMPLAINT_STATUS = 'FRESH' AND COMPLAINT_USER = 'T'";
				break;
			}
			case RestMethodConstants.REST_METHOD_EMPLOYEE_COMPLAINT_LIST : {
				existingFilterQueryString = "WHERE COMPLAINT_STATUS = 'FRESH' AND COMPLAINT_USER = 'E'";
				break;
			}
			case RestMethodConstants.REST_METHOD_RESOLVED_COMPLAINT_LIST : {
				existingFilterQueryString = "WHERE COMPLAINT_STATUS = 'RESOLVED'";
				break;
			}
		}
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new ComplaintRowMapper());
	}
	
	@Transactional
	public void takeActionOnComplaint(final String button, final List<String> idList, final String comments, final User activeUser) {
		final StringBuilder query = new StringBuilder("UPDATE COMPLAINT SET ");
		switch(button) {
			case BUTTON_ACTION_RESPOND : {
				query.append("COMPLAINT_STATUS = 'RESPONDED', IS_CONTACTED = 'Y', WHO_CONTACTED = :userId, CONTACTED_DATE_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), QUERY_RESPONSE = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE QUERY_ID = :queryId");
				break;
			}
			case BUTTON_ACTION_PUT_ON_HOLD : {
				query.append("COMPLAINT_STATUS = 'PUT_ON_HOLD', NOT_ANSWERED = 'Y', WHO_NOT_ANSWERED = :userId, NOT_ANSWERED_REASON = :remarks, RECORD_LAST_UPDATED_MILLIS = (UNIX_TIMESTAMP(SYSDATE()) * 1000), UPDATED_BY = :userId WHERE QUERY_ID = :queryId");
				break;
			}
		}
		final String baseQuery = query.toString();
		final List<Map<String, Object>> paramsList = new LinkedList<Map<String, Object>>();
		for (final String queryId : idList) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", activeUser.getUserId());
			paramsMap.put("remarks", comments);
			paramsMap.put("queryId", queryId);
			paramsList.add(paramsMap);
		}
		applicationDao.executeBatchUpdate(baseQuery, paramsList);
	}
	
	@Transactional
	public void updateComplaintRecord(final Complaint complaint, final List<String> changedAttributes, final User activeUser) {
		
	}
}
