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
import com.constants.components.Form2Constants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.User;
import com.model.components.form2.EmployeePFNominationDetails;
import com.model.components.form2.Form2;
import com.model.components.form2.OnlyWidowPensionNominee;
import com.model.components.form2.WidowChildrenPensionNominee;
import com.utils.PDFUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_FORM2_SERVICE)
public class Form2Service implements Form2Constants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}

	@Transactional
	public Form2 getForm(final String empId) {
		return applicationDao.find(empId, Form2.class);
	}
	
	private void deleteAllChildReccordsWhichWereRemoved(final Form2 form) {
		deleteListOfEmployeePFNominationDetails(form);
		deleteListOfWidowChildrenPensionNominee(form);
		deleteListOfOnlyWidowPensionNominee(form);
	}
	
	private void deleteListOfEmployeePFNominationDetails(final Form2 form) {
		if (null != form.getEmployeePFNominationDetailsList() && !form.getEmployeePFNominationDetailsList().isEmpty()) {
			final Set<EmployeePFNominationDetails> employeePFNominationDetailsList = new HashSet<EmployeePFNominationDetails>();
			for (final EmployeePFNominationDetails employeePFNominationDetails : form.getEmployeePFNominationDetailsList()) {
				if (employeePFNominationDetails.isDeleteRecord()) {
					applicationDao.delete(employeePFNominationDetails);
				} else {
					employeePFNominationDetailsList.add(employeePFNominationDetails);
				}
			}
			form.setEmployeePFNominationDetailsList(employeePFNominationDetailsList);
		}
	}
	
	private void deleteListOfWidowChildrenPensionNominee(final Form2 form) {
		if (null != form.getWidowChildrenPensionNomineeList() && !form.getWidowChildrenPensionNomineeList().isEmpty()) {
			final Set<WidowChildrenPensionNominee> widowChildrenPensionNomineeList = new HashSet<WidowChildrenPensionNominee>();
			for (final WidowChildrenPensionNominee widowChildrenPensionNominee : form.getWidowChildrenPensionNomineeList()) {
				if (widowChildrenPensionNominee.isDeleteRecord()) {
					applicationDao.delete(widowChildrenPensionNominee);
				} else {
					widowChildrenPensionNomineeList.add(widowChildrenPensionNominee);
				}
			}
			form.setWidowChildrenPensionNomineeList(widowChildrenPensionNomineeList);
		}
	}

	private void deleteListOfOnlyWidowPensionNominee(final Form2 form) {
		if (null != form.getOnlyWidowPensionNomineeList() && !form.getOnlyWidowPensionNomineeList().isEmpty()) {
			final Set<OnlyWidowPensionNominee> onlyWidowPensionNomineeList = new HashSet<OnlyWidowPensionNominee>();
			for (final OnlyWidowPensionNominee onlyWidowPensionNominee : form.getOnlyWidowPensionNomineeList()) {
				if (onlyWidowPensionNominee.isDeleteRecord()) {
					applicationDao.delete(onlyWidowPensionNominee);
				} else {
					onlyWidowPensionNomineeList.add(onlyWidowPensionNominee);
				}
			}
			form.setOnlyWidowPensionNomineeList(onlyWidowPensionNomineeList);
		}
	}
	
	private void mergeUserDataInForm(final User user, final Form2 form) {
		form.setEmpId(user.getEmpId());
	}
	
	@Transactional
	public void saveOrUpdateForm(final User user, final Form2 form) {
		mergeUserDataInForm(user, form);
		deleteAllChildReccordsWhichWereRemoved(form);
		applicationDao.saveOrUpdate(form);
	}
	
	public byte[] downloadForm(final String empId) throws Exception {
        final Form2 form = getForm(empId);
		if (null != form) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put(FORM_OBJECT, form);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(VELOCITY_TEMPLATE_PATH, attributes));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_FORM_PRESENT_FOR_THE_EMP_ID_IN_REQUEST);
	}

}
