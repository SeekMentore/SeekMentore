package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.WorkbookReport;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.rowmappers.BecomeTutorRowMapper;
import com.model.rowmappers.FindTutorRowMapper;
import com.model.rowmappers.SubscribeWithUsRowMapper;
import com.utils.ApplicationUtils;
import com.utils.PDFUtils;
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
		final WorkbookReport workbookReport = new WorkbookReport("Admin_Report");
		workbookReport.createSheet("NON_CONTACTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("NON_VERIFIED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("VERIFIED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("VERIFICATION_FAILED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("TO_BE_RECONTACTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("SELECTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("REJECTED_TUTOR_REGISTRATIONS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
		workbookReport.createSheet("REGISTERED_TUTORS", displayTutorRegistrations(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_FROM_TUTOR_REGISTRATIONS, WHITESPACE+SEMICOLON+WHITESPACE), BecomeTutor.class);
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
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
			case BUTTON_ACTION_FAILVERIFY : {
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
		final WorkbookReport workbookReport = new WorkbookReport("Admin_Report");
		workbookReport.createSheet("NON_CONTACTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("NON_VERIFIED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("VERIFIED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("VERIFICATION_FAILED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("TO_BE_RECONTACTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("SELECTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
		workbookReport.createSheet("REJECTED_TUTOR_REGISTRATIONS", displayTutorEnquiries(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_ENQUIRIES, WHITESPACE+SEMICOLON+WHITESPACE), FindTutor.class);
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
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
			case BUTTON_ACTION_FAILVERIFY : {
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
		final WorkbookReport workbookReport = new WorkbookReport("Admin_Report");
		workbookReport.createSheet("NON_CONTACTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("NON_VERIFIED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("VERIFIED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("VERIFICATION_FAILED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("TO_BE_RECONTACTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("SELECTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
		workbookReport.createSheet("REJECTED_SUBSCRIPTIONS", displaySubscriptions(RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_SUBSCRIPTIONS, WHITESPACE+SEMICOLON+WHITESPACE), SubscribeWithUs.class);
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
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
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
			case BUTTON_ACTION_FAILVERIFY : {
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
}
