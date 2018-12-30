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
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.constants.RestMethodConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.Credential;
import com.model.ErrorPacket;
import com.model.LogonTracker;
import com.model.PasswordChangeTracker;
import com.model.User;
import com.model.UserAccessOptions;
import com.service.components.CommonsService;
import com.utils.MailUtils;
import com.utils.SecurityUtil;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_LOGIN_SERVICE)
public class LoginService implements LoginConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	public User validateCredential(final Credential credential) throws Exception {
		User user = getUserFromDbUsingUserIdSwitchByUserType(credential.getUserId(), credential.getUserType());
		if (null != user) {
			final String decryptUserPasswordFromDB = SecurityUtil.decrypt(user.getEncyptedPassword());
			final String decryptUserPasswordFromUI = SecurityUtil.decryptClientSide(credential.getClientSideEncypytedPassword());
			if (decryptUserPasswordFromDB.equals(decryptUserPasswordFromUI)) {
				if (user.getUserType().equals(credential.getUserType())) {
					setAccessTypes(user);
					setAccessOptions(user);
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
	
	@Transactional
	public void feedLogonTracker(final LogonTracker logonTracker) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", logonTracker.getUserId());
		paramsMap.put("userType", logonTracker.getUserType());
		paramsMap.put("loginTime", logonTracker.getLoginTime());
		paramsMap.put("loginTimeMillis", logonTracker.getLoginTimeMillis());
		paramsMap.put("loginFrom", logonTracker.getLoginFrom());
		paramsMap.put("machineIp", logonTracker.getMachineIp());
		applicationDao.executeUpdate("INSERT INTO LOGON_TRACKER(USER_ID, USER_TYPE, LOGIN_TIME, LOGIN_TIME_MILLIS, LOGIN_FROM, MACHINE_IP) VALUES(:userId, :userType, :loginTime, :loginTimeMillis, :loginFrom, :machineIp)", paramsMap);
	}
	
	@Transactional
	public void feedPasswordChangeTracker(final PasswordChangeTracker passwordChangeTracker) {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", passwordChangeTracker.getUserId());
		paramsMap.put("userType", passwordChangeTracker.getUserType());
		paramsMap.put("changeTime", passwordChangeTracker.getChangeTime());
		paramsMap.put("changeTimeMillis", passwordChangeTracker.getChangeTimeMillis());
		paramsMap.put("encryptedPasswordOld", passwordChangeTracker.getEncryptedPasswordOld());
		paramsMap.put("encryptedPasswordNew", passwordChangeTracker.getEncryptedPasswordNew());
		applicationDao.executeUpdate("INSERT INTO PASSWORD_CHANGE_TRACKER(USER_ID, USER_TYPE, CHANGE_TIME, CHANGE_TIME_MILLIS, ENCRYPTED_PASSWORD_OLD, ENCRYPTED_PASSWORD_NEW) VALUES(:userId, :userType, :changeTime, :changeTimeMillis, :encryptedPasswordOld, :encryptedPasswordNew)", paramsMap);
	}
	
	private User getUserFromDbUsingUserIdSwitchByUserType(final String userId, final String userType) throws DataAccessException, InstantiationException, IllegalAccessException {
		switch(userType) {
			case USER_TYPE_EMPLOYEE : return commonsService.getUserFromEmployeeDbUsingUserId(userId);
			case USER_TYPE_TUTOR    : return commonsService.getUserFromTutorDbUsingUserId(userId);
			case USER_TYPE_CUSTOMER : return commonsService.getUserFromSubscribedCustomerDbUsingUserId(userId);
			default	: return null;
		}
	}
	
	private void setAccessTypes(final User user) {
		final List<String> pageAccessTypes = new ArrayList<String>();
		pageAccessTypes.add("user");
		pageAccessTypes.add("common");
		pageAccessTypes.add("public");
		switch(user.getUserType()) {
			case USER_TYPE_EMPLOYEE : {
				// Compute from database
				pageAccessTypes.add("employee");
				pageAccessTypes.add("admin");
				pageAccessTypes.add("sales");
				pageAccessTypes.add("support");
				pageAccessTypes.add("superadmin");
				break;
			}
			case USER_TYPE_TUTOR : {
				pageAccessTypes.add("tutor");
				break;
			}
			case USER_TYPE_CUSTOMER : {
				pageAccessTypes.add("customer");
				break;
			}
		}
		user.setPageAccessTypes(pageAccessTypes);
	}
	
	private void setAccessOptions(final User user) {
		final UserAccessOptions accessOptions = new UserAccessOptions();
		switch(user.getUserType()) {
			case USER_TYPE_EMPLOYEE : {
				// Compute from database
				accessOptions.setImpersonationaccess(true);
				accessOptions.setEmailformaccess(true);
				break;
			}
			case USER_TYPE_TUTOR : {
				break;
			}
			case USER_TYPE_CUSTOMER : {
				break;
			}
		}
		user.setAccessOptions(accessOptions);
	}

	public void changePassword(final User user, final String newPassword, final String emailIdOfUserInSession) throws Exception {
		final String encryptedNewPassword = SecurityUtil.encrypt(SecurityUtil.decryptClientSide(newPassword));
		final String encryptedOldPassword = user.getEncyptedPassword();
		changePasswordAsPerUserType(user.getUserType(), user.getUserId(), encryptedNewPassword);
		user.setEncyptedPassword(encryptedNewPassword);
		final PasswordChangeTracker passwordChangeTracker = new PasswordChangeTracker();
		passwordChangeTracker.setUserId(user.getUserId());
		passwordChangeTracker.setUserType(user.getUserType());
		passwordChangeTracker.setChangeTime(new Timestamp(new Date().getTime()));
		passwordChangeTracker.setChangeTimeMillis(passwordChangeTracker.getChangeTime().getTime());
		passwordChangeTracker.setEncryptedPasswordOld(encryptedOldPassword);
		passwordChangeTracker.setEncryptedPasswordNew(encryptedNewPassword);
		feedPasswordChangeTracker(passwordChangeTracker);
		sendPasswordChangeEmailToUser(user, emailIdOfUserInSession);
	}
	
	public void sendPasswordChangeEmailToUser(final User user, final String emailIdOfUserInSession) throws Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", user.getName());
		attributes.put("supportMailListId", jndiAndControlConfigurationLoadService.getControlConfiguration().getMailConfiguration().getImportantCompanyMailIdsAndLists().getSystemSupportMailList());
		attributes.put("companyContactInfo", jndiAndControlConfigurationLoadService.getControlConfiguration().getCompanyContactDetails().getCompanyAdminContactDetails().getContactDetailsInEmbeddedFormat());
		MailUtils.sendMimeMessageEmail( 
				emailIdOfUserInSession, 
				null,
				null,
				"Alert - Your Seek Mentore password has been changed", 
				VelocityUtils.parseTemplate(PASSWORD_CHANGE_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void changePasswordAsPerUserType(final String userType, final String loggedInUserId, final String encryptedNewPassword) throws IOException {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", loggedInUserId);
		params.put("encryptedPassword", encryptedNewPassword);
		switch(userType) {
			case USER_TYPE_EMPLOYEE : {
				applicationDao.executeUpdate("UPDATE EMPLOYEE SET ENCRYPTED_PASSWORD = :encryptedPassword WHERE USER_ID = :userId", params);
				break;
			}
			case USER_TYPE_TUTOR : {
				applicationDao.executeUpdate("UPDATE REGISTERED_TUTOR SET ENCRYPTED_PASSWORD = :encryptedPassword WHERE USER_ID = :userId", params);
				break;
			}
			case USER_TYPE_CUSTOMER : {
				applicationDao.executeUpdate("UPDATE SUBSCRIBED_CUSTOMER SET ENCRYPTED_PASSWORD = :encryptedPassword WHERE USER_ID = :userId", params);
				break;
			}
			default	: {
				throw new ApplicationException("Invalid Usertype " + userType);
			}
		}
	}
}
