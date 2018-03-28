package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.model.User;

@Service(BeanConstants.BEAN_NAME_LOGIN_SERVICE)
public class LoginService implements LoginConstants {
	
	public User getUser(final String empId) {
		User user = new User();
		user.setEmpId(empId);
		List<String> pageAccessTypes = new ArrayList<String>();
		pageAccessTypes.add("U");
		user.setPageAccessTypes(pageAccessTypes);
		return user;
	}
}
