package com.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.constants.RestMethodConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
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
		User user = getUserFromDbUsingUserIdSwitchByUserType(credential.getUserId(), credential.getUserType());
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
	
	private User getUserFromDbUsingUserIdSwitchByUserType(final String userId, final String userType) throws DataAccessException, InstantiationException, IllegalAccessException {
		switch(userType) {
			case "Admin" : return commonsService.getUserFromEmployeeDbUsingUserId(userId);
			case "Tutor" : return commonsService.getUserFromTutorDbUsingUserId(userId);
			default	: return null;
		}
	}
	
	private void setAccessTypes(final User user) {
		final List<String> pageAccessTypes = new ArrayList<String>();
		switch(user.getUserType()) {
			case "Admin" : {
				pageAccessTypes.add("A");
				break;
			}
			case "Tutor" : {
				pageAccessTypes.add("M");
				break;
			}
			default	: {
			}
		}
		pageAccessTypes.add("G");
		user.setPageAccessTypes(pageAccessTypes);
	}

	public Map<String, Object> changePassword(final User user, final String loggedInUserId, final String loggedInUserType, final String newPassword) throws Exception {
		final Map<String, Object> response = new HashMap<String, Object>(); 
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, false);
		response.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		final String encryptedNewPassword = SecurityUtil.encrypt(SecurityUtil.decryptClientSide(newPassword));
		changePasswordAsPerUserType(loggedInUserType, loggedInUserId, encryptedNewPassword);
		user.setEncyptedPassword(encryptedNewPassword);
		return response;
	}
	
	private void changePasswordAsPerUserType(final String userType, final String loggedInUserId, final String encryptedNewPassword) throws IOException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", loggedInUserId);
		params.put("encryptedPassword", encryptedNewPassword);
		switch(userType) {
			case "Admin" : {
				applicationDao.executeUpdate("UPDATE EMPLOYEE SET ENCYPTED_PASSWORD = :encryptedPassword WHERE USER_ID = :userId", params);
				break;
			}
			case "Tutor" : {
				applicationDao.executeUpdate("UPDATE REGISTERED_TUTOR SET ENCYPTED_PASSWORD = :encryptedPassword WHERE USER_ID = :userId", params);
				break;
			}
			default	: {
				throw new ApplicationException("Invalid Usertype " + userType);
			}
		}
	}
}
