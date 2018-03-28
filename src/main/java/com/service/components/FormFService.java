package com.service.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.FormFConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.User;
import com.model.components.formF.EmployeeGratuityNomineeDetails;
import com.model.components.formF.FormF;
import com.utils.PDFUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_FORMF_SERVICE)
public class FormFService implements FormFConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}

	@Transactional
	public FormF getForm(final String empId) {
		return applicationDao.find(empId, FormF.class);
	}
	
	private void deleteAllChildReccordsWhichWereRemoved(final FormF form) {
		deleteListOfEmployeeGratuityNomineeDetails(form);
	}

	private void deleteListOfEmployeeGratuityNomineeDetails(final FormF form) {
		if (null != form.getEmployeeGratuityNomineeDetailsList() && !form.getEmployeeGratuityNomineeDetailsList().isEmpty()) {
			final Set<EmployeeGratuityNomineeDetails> employeeGratuityNomineeDetailsList = new HashSet<EmployeeGratuityNomineeDetails>();
			for (final EmployeeGratuityNomineeDetails employeeGratuityNomineeDetails : form.getEmployeeGratuityNomineeDetailsList()) {
				if (employeeGratuityNomineeDetails.isDeleteRecord()) {
					applicationDao.delete(employeeGratuityNomineeDetails);
				} else {
					employeeGratuityNomineeDetailsList.add(employeeGratuityNomineeDetails);
				}
			}
			form.setEmployeeGratuityNomineeDetailsList(employeeGratuityNomineeDetailsList);
		}
	}
	
	private void mergeUserDataInForm(final User user, final FormF form) {
		form.setEmpId(user.getEmpId());
	}
	
	@Transactional
	public void saveOrUpdateForm(final User user, final FormF form) {
		mergeUserDataInForm(user, form);
		deleteAllChildReccordsWhichWereRemoved(form);
		applicationDao.saveOrUpdate(form);
	}
	
	public byte[] downloadForm(final String empId) throws Exception {
		final FormF form = getForm(empId);
		if (null != form) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put(FORM_OBJECT, form);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(VELOCITY_TEMPLATE_PATH, attributes));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_FORM_PRESENT_FOR_THE_EMP_ID_IN_REQUEST);
	}

}
