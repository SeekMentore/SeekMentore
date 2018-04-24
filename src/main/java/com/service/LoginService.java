package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.model.User;

@Service(BeanConstants.BEAN_NAME_LOGIN_SERVICE)
public class LoginService implements LoginConstants {
	
	public User getUser(final String userId) {
		if (userId.equalsIgnoreCase("prm.seekmentore") || userId.equalsIgnoreCase("techlead.seekmentore") || userId.equalsIgnoreCase("partner.seekmentore") || userId.equalsIgnoreCase("founder.seekmentore")) {
			User user = new User();
			user.setUserId(userId);
			List<String> pageAccessTypes = new ArrayList<String>();
			pageAccessTypes.add("A");
			pageAccessTypes.add("G");
			user.setPageAccessTypes(pageAccessTypes);
			return user;
		}
		return null;
	}
}
