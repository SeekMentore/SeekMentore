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
import com.constants.components.Form11Constants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.User;
import com.model.components.form11.Form11;
import com.model.components.form11.KYCDetails;
import com.utils.PDFUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_FORM11_SERVICE)
public class Form11Service implements Form11Constants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}

	@Transactional
	public Form11 getForm(final String empId) {
		return applicationDao.find(empId, Form11.class);
	}
	
	private void deleteAllChildReccordsWhichWereRemoved(final Form11 form) {
		deleteListOfKYCDetails(form);
	}

	private void deleteListOfKYCDetails(final Form11 form) {
		if (null != form.getKycDetailsList() && !form.getKycDetailsList().isEmpty()) {
			final Set<KYCDetails> kycDetailsList = new HashSet<KYCDetails>();
			for (final KYCDetails kycDetails : form.getKycDetailsList()) {
				if (kycDetails.isDeleteRecord()) {
					applicationDao.delete(kycDetails);
				} else {
					kycDetailsList.add(kycDetails);
				}
			}
			form.setKycDetailsList(kycDetailsList);
		}
	}
	
	private void mergeUserDataInForm(final User user, final Form11 form) {
		form.setEmpId(user.getEmpId());
	}
	
	@Transactional
	public void saveOrUpdateForm(final User user, final Form11 form) {
		mergeUserDataInForm(user, form);
		deleteAllChildReccordsWhichWereRemoved(form);
		applicationDao.saveOrUpdate(form);
	}
	
	public byte[] downloadForm(final String empId) throws Exception {
		final Form11 form = getForm(empId);
		if (null != form) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put(FORM_OBJECT, form);
	        return PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parseTemplate(VELOCITY_TEMPLATE_PATH, attributes));
		}
		else
			throw new ApplicationException(EXCEPTION_NO_FORM_PRESENT_FOR_THE_EMP_ID_IN_REQUEST);
	}
}
