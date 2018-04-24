package com.service.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.components.AdminConstants;
import com.dao.ApplicationDao;
import com.model.components.commons.SelectLookup;
import com.model.components.publicaccess.BecomeTutor;

@Service(BeanConstants.BEAN_NAME_ADMIN_SERVICE)
public class AdminService implements AdminConstants{
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@PostConstruct
	public void init() {}
	
	public List<BecomeTutor> displayTutorRegistrations() {
		final List<BecomeTutor> registeredTutorList = applicationDao.findAllWithoutParams("SELECT * FROM BECOME_TUTOR WHERE IS_SELECTED IS NULL AND IS_REJECTED IS NULL", BecomeTutor.class);
		for (final BecomeTutor registeredTutorObject : registeredTutorList) {
			registeredTutorObject.setGender(preapreLookupLabelString("GENDER_LOOKUP",registeredTutorObject.getGender(), false));
			registeredTutorObject.setQualification(preapreLookupLabelString("QUALIFICATION_LOOKUP",registeredTutorObject.getQualification(), false));
			registeredTutorObject.setPrimaryProfession(preapreLookupLabelString("PROFESSION_LOOKUP",registeredTutorObject.getPrimaryProfession(), false));
			registeredTutorObject.setTransportMode(preapreLookupLabelString("TRANSPORT_MODE_LOOKUP",registeredTutorObject.getTransportMode(), false));
			registeredTutorObject.setStudentGrade(preapreLookupLabelString("STUDENT_GRADE_LOOKUP", registeredTutorObject.getStudentGrade(), true));
			registeredTutorObject.setSubjects(preapreLookupLabelString("SUBJECTS_LOOKUP", registeredTutorObject.getSubjects(), true));
			registeredTutorObject.setLocations(preapreLookupLabelString("LOCATIONS_LOOKUP", registeredTutorObject.getLocations(), true));
			registeredTutorObject.setPreferredTimeToCall(preapreLookupLabelString("PREFERRED_TIME_LOOKUP", registeredTutorObject.getPreferredTimeToCall(), true));
		}
		return registeredTutorList;
	}
	
	private String preapreLookupLabelString(final String selectLookupTable, final String value, final boolean multiSelect) {
		final StringBuilder multiLineString = new StringBuilder(EMPTY_STRING);
		if (multiSelect) {
			final List<SelectLookup> selectLookupList = commonsService.getSelectLookupEntryList(selectLookupTable, value.split(SEMICOLON));
			for(final SelectLookup selectLookup : selectLookupList) {
				multiLineString.append(selectLookup.getLabel()).append(LINE_BREAK);
			}
		} else {
			multiLineString.append(commonsService.getSelectLookupEntry(selectLookupTable, value).getLabel());
		}
		return multiLineString.toString();
	}
}
