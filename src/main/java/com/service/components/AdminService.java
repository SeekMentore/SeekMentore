package com.service.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;
import com.utils.ValidationUtils;

@Service(BeanConstants.BEAN_NAME_ADMIN_SERVICE)
public class AdminService implements AdminConstants{
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	public List<BecomeTutor> displayTutorRegistrations(final String grid) {
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
			registeredTutorObject.setGender(preapreLookupLabelString("GENDER_LOOKUP",registeredTutorObject.getGender(), false));
			registeredTutorObject.setQualification(preapreLookupLabelString("QUALIFICATION_LOOKUP",registeredTutorObject.getQualification(), false));
			registeredTutorObject.setPrimaryProfession(preapreLookupLabelString("PROFESSION_LOOKUP",registeredTutorObject.getPrimaryProfession(), false));
			registeredTutorObject.setTransportMode(preapreLookupLabelString("TRANSPORT_MODE_LOOKUP",registeredTutorObject.getTransportMode(), false));
			registeredTutorObject.setStudentGrade(preapreLookupLabelString("STUDENT_GRADE_LOOKUP", registeredTutorObject.getStudentGrade(), true));
			registeredTutorObject.setSubjects(preapreLookupLabelString("SUBJECTS_LOOKUP", registeredTutorObject.getSubjects(), true));
			registeredTutorObject.setLocations(preapreLookupLabelString("LOCATIONS_LOOKUP", registeredTutorObject.getLocations(), true));
			registeredTutorObject.setPreferredTimeToCall(preapreLookupLabelString("PREFERRED_TIME_LOOKUP", registeredTutorObject.getPreferredTimeToCall(), true));
			registeredTutorObject.setWhoContacted(getNameOfUserFromUserId(registeredTutorObject.getWhoContacted()));
			registeredTutorObject.setWhoVerified(getNameOfUserFromUserId(registeredTutorObject.getWhoVerified()));
			registeredTutorObject.setWhoSuggestedForRecontact(getNameOfUserFromUserId(registeredTutorObject.getWhoSuggestedForRecontact()));
			registeredTutorObject.setWhoRecontacted(getNameOfUserFromUserId(registeredTutorObject.getWhoRecontacted()));
			registeredTutorObject.setWhoSelected(getNameOfUserFromUserId(registeredTutorObject.getWhoSelected()));
			registeredTutorObject.setWhoRejected(getNameOfUserFromUserId(registeredTutorObject.getWhoRejected()));
		}
		return registeredTutorList;
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
		Object[] params = null;
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				query.append("APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING', IS_CONTACTED = 'Y', WHO_CONTACTED = ?, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[3];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				query.append("APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED', IS_CONTACTED = 'Y', WHO_CONTACTED = ?, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = ?, IS_TO_BE_RECONTACTED = 'Y', WHO_SUGGESTED_FOR_RECONTACT = ?, SUGGESTION_DATE = SYSDATE(), SUGGESTION_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[5];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = user.getUserId();
				params[3] = remarks;
				params[4] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_REJECT : {
				query.append("APPLICATION_STATUS = 'REJECTED', IS_CONTACTED = 'Y', WHO_CONTACTED = ?, CONTACTED_DATE = SYSDATE(), CONTACTED_REMARKS = ?, IS_REJECTED = 'Y', WHO_REJECTED = ?, REJECTION_DATE = SYSDATE(), REJECTION_REMARKS = ?, REJECTION_COUNT = (REJECTION_COUNT + 1), RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[5];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = user.getUserId();
				params[3] = remarks;
				params[4] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL', IS_AUTHENTICATION_VERIFIED = 'Y', WHO_VERIFIED = ?, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[3];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_FAILVERIFY : {
				query.append("APPLICATION_STATUS = 'VERIFICATION_FAILED', IS_AUTHENTICATION_VERIFIED = 'N', WHO_VERIFIED = ?, VERIFICATION_DATE = SYSDATE(), VERIFICATION_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[3];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_SELECT : {
				query.append("APPLICATION_STATUS = 'SELECTED', IS_SELECTED = 'Y', WHO_SELECTED = ?, SELECTION_DATE = SYSDATE(), SELECTION_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[3];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = tentativeTutorId;
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				query.append("APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING', IS_TO_BE_RECONTACTED = 'N', WHO_RECONTACTED = ?, RECONTACTED_DATE = SYSDATE(), RECONTACTED_REMARKS = ?, RECORD_LAST_UPDATED = SYSDATE() WHERE TENTATIVE_TUTOR_ID = ?");
				params = new Object[3];
				params[0] = user.getUserId();
				params[1] = remarks;
				params[2] = tentativeTutorId;
				break;
			}
		}
		applicationDao.updateWithPreparedQueryAndIndividualOrderedParams(query.toString(), params);
		return response;
	}
	
	private String getNameOfUserFromUserId(final String userId) {
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(userId)) {
			final User user = commonsService.getUserFromDbUsingUserId(userId);
			if (null != user) {
				return user.getName();
			}
		}
		return null;
	}
	
	private String preapreLookupLabelString(final String selectLookupTable, final String value, final boolean multiSelect) {
		final StringBuilder multiLineString = new StringBuilder(EMPTY_STRING);
		if (multiSelect) {
			final List<SelectLookup> selectLookupList = commonsService.getSelectLookupEntryList(selectLookupTable, value.split(SEMICOLON));
			for(final SelectLookup selectLookup : selectLookupList) {
				multiLineString.append(selectLookup.getLabel());
				if (ValidationUtils.validatePlainNotNullAndEmptyTextString(selectLookup.getDescription())) {
					multiLineString.append(WHITESPACE).append(selectLookup.getDescription());
				}
				multiLineString.append(LINE_BREAK);
			}
		} else {
			multiLineString.append(commonsService.getSelectLookupEntry(selectLookupTable, value).getLabel());
		}
		return multiLineString.toString();
	}
}
