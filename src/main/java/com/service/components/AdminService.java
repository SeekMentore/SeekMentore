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
		return WorkbookUtils.createWorkbook(workbookReport);
	}
	
	public byte[] downloadAdminTutorRegistrationProfilePdf(final String tentativeTutorId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tentativeTutorId", tentativeTutorId);
		final BecomeTutor registeredTutorObject = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE TENTATIVE_TUTOR_ID = :tentativeTutorId", paramsMap, new BecomeTutorRowMapper());
		if (null != registeredTutorObject) {
			replacePlaceHolderAndIdsFromTutorRegistrationObject(registeredTutorObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInTutorRegistrationObject(registeredTutorObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("registeredTutorObject", registeredTutorObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
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
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS : {
				query.append("IS_DATA_MIGRATED = 'Y'");
				break;
			}
		}
		final List<BecomeTutor> registeredTutorList = applicationDao.findAllWithoutParams(query.toString(), new BecomeTutorRowMapper());
		for (final BecomeTutor registeredTutorObject : registeredTutorList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromTutorRegistrationObject(registeredTutorObject, delimiter);
		}
		return registeredTutorList;
	}
	
	private void replacePlaceHolderAndIdsFromTutorRegistrationObject(final BecomeTutor registeredTutorObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		registeredTutorObject.setGender(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_GENDER_LOOKUP,registeredTutorObject.getGender(), false, delimiter));
		registeredTutorObject.setQualification(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_QUALIFICATION_LOOKUP,registeredTutorObject.getQualification(), false, delimiter));
		registeredTutorObject.setPrimaryProfession(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PROFESSION_LOOKUP,registeredTutorObject.getPrimaryProfession(), false, delimiter));
		registeredTutorObject.setTransportMode(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_TRANSPORT_MODE_LOOKUP,registeredTutorObject.getTransportMode(), false, delimiter));
		registeredTutorObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, registeredTutorObject.getStudentGrade(), true, delimiter));
		registeredTutorObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, registeredTutorObject.getSubjects(), true, delimiter));
		registeredTutorObject.setLocations(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, registeredTutorObject.getLocations(), true, delimiter));
		registeredTutorObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, registeredTutorObject.getPreferredTimeToCall(), true, delimiter));
		registeredTutorObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, registeredTutorObject.getReference(), false, delimiter));
		registeredTutorObject.setPreferredTeachingType(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TEACHING_TYPE_LOOKUP, registeredTutorObject.getPreferredTeachingType(), true, delimiter));
		registeredTutorObject.setWhoContacted(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoContacted()));
		registeredTutorObject.setWhoVerified(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoVerified()));
		registeredTutorObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoSuggestedForRecontact()));
		registeredTutorObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoRecontacted()));
		registeredTutorObject.setWhoSelected(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoSelected()));
		registeredTutorObject.setWhoRejected(commonsService.getNameOfUserFromUserId(registeredTutorObject.getWhoRejected()));
		registeredTutorObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsContacted()));
		registeredTutorObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsAuthenticationVerified()));
		registeredTutorObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsToBeRecontacted()));
		registeredTutorObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsSelected()));
		registeredTutorObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsRejected()));
		registeredTutorObject.setReApplied(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getReApplied()));
		registeredTutorObject.setIsDataMigrated(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsDataMigrated()));
	}
	
	private void replaceNullWithBlankRemarksInTutorRegistrationObject(final BecomeTutor registeredTutorObject) {
		registeredTutorObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getContactedRemarks()));
		registeredTutorObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getVerificationRemarks()));
		registeredTutorObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getSuggestionRemarks()));
		registeredTutorObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getRecontactedRemarks()));
		registeredTutorObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getSelectionRemarks()));
		registeredTutorObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getRejectionRemarks()));
	}
	
	public Map<String, Object> takeActionOnRegisteredTutors (
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
		final FindTutor enquiredTutorObject = applicationDao.find("SELECT * FROM FIND_TUTOR WHERE ENQUIRY_ID = :enquiryId", paramsMap, new FindTutorRowMapper());
		if (null != enquiredTutorObject) {
			replacePlaceHolderAndIdsFromTutorEnquiryObject(enquiredTutorObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInTutorEnquiryObject(enquiredTutorObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("enquiredTutorObject", enquiredTutorObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(ENQUIRED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
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
		final List<FindTutor> enquiredTutorList = applicationDao.findAllWithoutParams(query.toString(), new FindTutorRowMapper());
		for (final FindTutor enquiredTutorObject : enquiredTutorList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromTutorEnquiryObject(enquiredTutorObject, delimiter);
		}
		return enquiredTutorList;
	}
	
	private void replacePlaceHolderAndIdsFromTutorEnquiryObject(final FindTutor enquiredTutorObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		enquiredTutorObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, enquiredTutorObject.getStudentGrade(), true, delimiter));
		enquiredTutorObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, enquiredTutorObject.getSubjects(), true, delimiter));
		enquiredTutorObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, enquiredTutorObject.getPreferredTimeToCall(), true, delimiter));
		enquiredTutorObject.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, enquiredTutorObject.getLocation(), true, delimiter));
		enquiredTutorObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, enquiredTutorObject.getReference(), false, delimiter));
		enquiredTutorObject.setWhoContacted(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoContacted()));
		enquiredTutorObject.setWhoVerified(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoVerified()));
		enquiredTutorObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoSuggestedForRecontact()));
		enquiredTutorObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoRecontacted()));
		enquiredTutorObject.setWhoSelected(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoSelected()));
		enquiredTutorObject.setWhoRejected(commonsService.getNameOfUserFromUserId(enquiredTutorObject.getWhoRejected()));
		enquiredTutorObject.setSubscribedCustomer(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getSubscribedCustomer()));
		enquiredTutorObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getIsContacted()));
		enquiredTutorObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getIsAuthenticationVerified()));
		enquiredTutorObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getIsToBeRecontacted()));
		enquiredTutorObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getIsSelected()));
		enquiredTutorObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(enquiredTutorObject.getIsRejected()));
	}
	
	private void replaceNullWithBlankRemarksInTutorEnquiryObject(final FindTutor registeredTutorObject) {
		registeredTutorObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getContactedRemarks()));
		registeredTutorObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getVerificationRemarks()));
		registeredTutorObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getSuggestionRemarks()));
		registeredTutorObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getRecontactedRemarks()));
		registeredTutorObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getSelectionRemarks()));
		registeredTutorObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(registeredTutorObject.getRejectionRemarks()));
	}
	
	public Map<String, Object> takeActionOnEnquiredTutors (
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
		final SubscribeWithUs enquiredSubscriptionObject = applicationDao.find("SELECT * FROM SUBSCRIBE_WITH_US WHERE TENTATIVE_SUBSCRIPTION_ID = :tentativeSubscriptionId", paramsMap, new SubscribeWithUsRowMapper());
		if (null != enquiredSubscriptionObject) {
			replacePlaceHolderAndIdsFromSubscribeWithUsObject(enquiredSubscriptionObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInSubscribeWithUsObject(enquiredSubscriptionObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("enquiredSubscriptionObject", enquiredSubscriptionObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(SUBSCRIPTION_INDIVIDUAL_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
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
	
	private void replacePlaceHolderAndIdsFromSubscribeWithUsObject(final SubscribeWithUs enquiredSubscriptionObject, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		enquiredSubscriptionObject.setStudentGrade(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_STUDENT_GRADE_LOOKUP, enquiredSubscriptionObject.getStudentGrade(), true, delimiter));
		enquiredSubscriptionObject.setSubjects(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_SUBJECTS_LOOKUP, enquiredSubscriptionObject.getSubjects(), true, delimiter));
		enquiredSubscriptionObject.setPreferredTimeToCall(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_PREFERRED_TIME_LOOKUP, enquiredSubscriptionObject.getPreferredTimeToCall(), true, delimiter));
		enquiredSubscriptionObject.setLocation(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_LOCATIONS_LOOKUP, enquiredSubscriptionObject.getLocation(), true, delimiter));
		enquiredSubscriptionObject.setReference(commonsService.preapreLookupLabelString(SelectLookupConstants.SELECT_LOOKUP_TABLE_REFERENCE_LOOKUP, enquiredSubscriptionObject.getReference(), false, delimiter));
		enquiredSubscriptionObject.setWhoContacted(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoContacted()));
		enquiredSubscriptionObject.setWhoVerified(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoVerified()));
		enquiredSubscriptionObject.setWhoSuggestedForRecontact(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoSuggestedForRecontact()));
		enquiredSubscriptionObject.setWhoRecontacted(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoRecontacted()));
		enquiredSubscriptionObject.setWhoSelected(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoSelected()));
		enquiredSubscriptionObject.setWhoRejected(commonsService.getNameOfUserFromUserId(enquiredSubscriptionObject.getWhoRejected()));
		enquiredSubscriptionObject.setSubscribedCustomer(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getSubscribedCustomer()));
		enquiredSubscriptionObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getIsContacted()));
		enquiredSubscriptionObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getIsAuthenticationVerified()));
		enquiredSubscriptionObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getIsToBeRecontacted()));
		enquiredSubscriptionObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getIsSelected()));
		enquiredSubscriptionObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(enquiredSubscriptionObject.getIsRejected()));
	}
	
	private void replaceNullWithBlankRemarksInSubscribeWithUsObject(final SubscribeWithUs subscribedIndividualObject) {
		subscribedIndividualObject.setContactedRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getContactedRemarks()));
		subscribedIndividualObject.setVerificationRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getVerificationRemarks()));
		subscribedIndividualObject.setSuggestionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getSuggestionRemarks()));
		subscribedIndividualObject.setRecontactedRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getRecontactedRemarks()));
		subscribedIndividualObject.setSelectionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getSelectionRemarks()));
		subscribedIndividualObject.setRejectionRemarks(ApplicationUtils.returnBlankIfStringNull(subscribedIndividualObject.getRejectionRemarks()));
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
