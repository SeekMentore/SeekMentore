package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.constants.RestMethodConstants;
import com.dao.ApplicationDao;
import com.model.Credential;
import com.model.ErrorPacket;
import com.model.User;
import com.service.components.CommonsService;
import com.utils.SecurityUtil;

@Service(BeanConstants.BEAN_NAME_LOGIN_SERVICE)
public class LoginService implements LoginConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	public User validateCredential(final Credential credential) throws Exception {
		User user = applicationDao.find("SELECT * FROM EMPLOYEE WHERE LOWER(USER_ID) = ?", new Object[] {credential.getUserId().toLowerCase()}, User.class);
		if (null != user) {
			final String decryptUserPasswordFromDB = SecurityUtil.decrypt(user.getEncyptedPassword());
			final String decryptUserPasswordFromUI = SecurityUtil.decryptClientSide(credential.getClientSideEncypytedPassword());
			if (decryptUserPasswordFromDB.equals(decryptUserPasswordFromUI)) {
				if (user.getUserType().equals(credential.getUserType())) {
					setAccessTypes(user);
				} else {
					final String message = "Hacking alert !!! <BR/> Received Invalid User Type for : <BR/>" + credential.toString();
					final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), RestMethodConstants.REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL, message);
					commonsService.feedErrorRecord(errorPacket);
					user = null;
				}
			} else {
				final String message = "Hacking alert !!! <BR/> Received Invalid Password for : <BR/>" + credential.toString();
				final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), RestMethodConstants.REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL, message);
				commonsService.feedErrorRecord(errorPacket);
				user = null;
			}
		}
		return user;
	}
	
	private void setAccessTypes(final User user) {
		final List<String> pageAccessTypes = new ArrayList<String>();
		if (user.getUserType().equals("Admin")) {
			pageAccessTypes.add("A");
			pageAccessTypes.add("G");
		}
		user.setPageAccessTypes(pageAccessTypes);
	}
}
