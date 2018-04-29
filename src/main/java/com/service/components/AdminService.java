package com.service.components;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.WorkbookReport;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;
import com.utils.ApplicationUtils;
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
	
	public byte[] downloadAdminReport() throws InstantiationException, IllegalAccessException, IOException {
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
		final BecomeTutor registeredTutorObject = applicationDao.find("SELECT * FROM BECOME_TUTOR WHERE TENTATIVE_TUTOR_ID = ?", new Object[] {tentativeTutorId}, BecomeTutor.class);
		if (null != registeredTutorObject) {
			replacePlaceHolderAndIdsFromTutorRegistrationObject(registeredTutorObject, WHITESPACE+SEMICOLON+WHITESPACE);
			replaceNullWithBlankRemarksInTutorRegistrationObject(registeredTutorObject);
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("registeredTutorObject", registeredTutorObject);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes));
		}
		return null;
	}
	
	public List<BecomeTutor> displayTutorRegistrations(final String grid, final String delimiter) {
		final StringBuilder query = new StringBuilder("SELECT * FROM BECOME_TUTOR WHERE ");
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_CONTACTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'N'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_NON_VERIFIED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED IS NULL AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFIED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'Y' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_VERIFICATION_FAILED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_AUTHENTICATION_VERIFIED = 'N' AND (IS_TO_BE_RECONTACTED IS NULL OR IS_TO_BE_RECONTACTED = 'N') AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_TO_BE_RECONTACTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_TO_BE_RECONTACTED = 'Y' AND IS_SELECTED IS NULL AND IS_REJECTED IS NULL");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SELECTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_SELECTED = 'Y'");
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_REJECTED_TUTOR_REGISTRATIONS : {
				query.append("IS_CONTACTED = 'Y' AND IS_REJECTED = 'Y'");
				break;
			}
		}
		final List<BecomeTutor> registeredTutorList = applicationDao.findAllWithoutParams(query.toString(), BecomeTutor.class);
		for (final BecomeTutor registeredTutorObject : registeredTutorList) {
			// Get all lookup data and user ids back to original label and values
			replacePlaceHolderAndIdsFromTutorRegistrationObject(registeredTutorObject, delimiter);
		}
		return registeredTutorList;
	}
	
	private void replacePlaceHolderAndIdsFromTutorRegistrationObject(final BecomeTutor registeredTutorObject, final String delimiter) {
		registeredTutorObject.setGender(preapreLookupLabelString("GENDER_LOOKUP",registeredTutorObject.getGender(), false, delimiter));
		registeredTutorObject.setQualification(preapreLookupLabelString("QUALIFICATION_LOOKUP",registeredTutorObject.getQualification(), false, delimiter));
		registeredTutorObject.setPrimaryProfession(preapreLookupLabelString("PROFESSION_LOOKUP",registeredTutorObject.getPrimaryProfession(), false, delimiter));
		registeredTutorObject.setTransportMode(preapreLookupLabelString("TRANSPORT_MODE_LOOKUP",registeredTutorObject.getTransportMode(), false, delimiter));
		registeredTutorObject.setStudentGrade(preapreLookupLabelString("STUDENT_GRADE_LOOKUP", registeredTutorObject.getStudentGrade(), true, delimiter));
		registeredTutorObject.setSubjects(preapreLookupLabelString("SUBJECTS_LOOKUP", registeredTutorObject.getSubjects(), true, delimiter));
		registeredTutorObject.setLocations(preapreLookupLabelString("LOCATIONS_LOOKUP", registeredTutorObject.getLocations(), true, delimiter));
		registeredTutorObject.setPreferredTimeToCall(preapreLookupLabelString("PREFERRED_TIME_LOOKUP", registeredTutorObject.getPreferredTimeToCall(), true, delimiter));
		registeredTutorObject.setWhoContacted(getNameOfUserFromUserId(registeredTutorObject.getWhoContacted()));
		registeredTutorObject.setWhoVerified(getNameOfUserFromUserId(registeredTutorObject.getWhoVerified()));
		registeredTutorObject.setWhoSuggestedForRecontact(getNameOfUserFromUserId(registeredTutorObject.getWhoSuggestedForRecontact()));
		registeredTutorObject.setWhoRecontacted(getNameOfUserFromUserId(registeredTutorObject.getWhoRecontacted()));
		registeredTutorObject.setWhoSelected(getNameOfUserFromUserId(registeredTutorObject.getWhoSelected()));
		registeredTutorObject.setWhoRejected(getNameOfUserFromUserId(registeredTutorObject.getWhoRejected()));
		registeredTutorObject.setIsContacted(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsContacted()));
		registeredTutorObject.setIsAuthenticationVerified(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsAuthenticationVerified()));
		registeredTutorObject.setIsToBeRecontacted(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsToBeRecontacted()));
		registeredTutorObject.setIsSelected(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsSelected()));
		registeredTutorObject.setIsRejected(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getIsRejected()));
		registeredTutorObject.setReApplied(ApplicationUtils.setYesOrNoFromYN(registeredTutorObject.getReApplied()));
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
		applicationDao.insertOrUpdateWithParams(query.toString(), paramsMap);
		return response;
	}
	
	private String getNameOfUserFromUserId(final String userId) {
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(userId)) {
			final User user = commonsService.getUserFromDbUsingUserId(userId);
			if (null != user) {
				return user.getName();
			}
		}
		return EMPTY_STRING;
	}
	
	private String preapreLookupLabelString(final String selectLookupTable, final String value, final boolean multiSelect, final String delimiter) {
		final StringBuilder multiLineString = new StringBuilder(EMPTY_STRING);
		if (multiSelect) {
			final List<SelectLookup> selectLookupList = commonsService.getSelectLookupEntryList(selectLookupTable, value.split(SEMICOLON));
			for(final SelectLookup selectLookup : selectLookupList) {
				multiLineString.append(selectLookup.getLabel());
				if (ValidationUtils.validatePlainNotNullAndEmptyTextString(selectLookup.getDescription())) {
					multiLineString.append(WHITESPACE).append(selectLookup.getDescription());
				}
				multiLineString.append(delimiter);
			}
		} else {
			multiLineString.append(commonsService.getSelectLookupEntry(selectLookupTable, value).getLabel());
		}
		return multiLineString.toString();
	}
}
