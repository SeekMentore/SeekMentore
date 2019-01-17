package com.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.LoginConstants;
import com.constants.RestMethodConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.Credential;
import com.model.Employee;
import com.model.ErrorPacket;
import com.model.ForgotPasswordToken;
import com.model.LogonTracker;
import com.model.PasswordChangeTracker;
import com.model.User;
import com.model.UserAccessOptions;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.service.components.CommonsService;
import com.service.components.CustomerService;
import com.service.components.EmployeeService;
import com.service.components.TutorService;
import com.utils.MailUtils;
import com.utils.SecurityUtil;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_LOGIN_SERVICE)
public class LoginService implements LoginConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private transient EmployeeService employeeService;
	
	@Autowired
	private transient TutorService tutorService;
	
	@Autowired
	private transient CustomerService customerService;
	
	public User validateCredential(final Credential credential) throws Exception {
		User user = getUserFromDbUsingUserIdSwitchByUserType(credential.getUserId(), credential.getUserType());
		if (null != user) {
			final String decryptUserPasswordFromDB = SecurityUtil.decrypt(user.getEncryptedPassword());
			final String decryptUserPasswordFromUI = SecurityUtil.decryptClientSide(credential.getClientSideEncypytedPassword());
			if (decryptUserPasswordFromDB.equals(decryptUserPasswordFromUI)) {
				if (user.getUserType().equals(credential.getUserType())) {
					setAccessTypes(user);
					setAccessOptions(user);
				} else {
					final String message = "Hacking alert !!! <BR/> Received Invalid User Type for : <BR/>" + credential.toString();
					final ErrorPacket errorPacket = new ErrorPacket(RestMethodConstants.REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL, message);
					commonsService.feedErrorRecord(errorPacket);
					user = null;
				}
			} else {
				final String message = "Hacking alert !!! <BR/> Received Invalid Password for : <BR/>" + credential.toString();
				final ErrorPacket errorPacket = new ErrorPacket(RestMethodConstants.REST_METHOD_NAME_TO_VALIDATE_CREDENTIAL, message);
				commonsService.feedErrorRecord(errorPacket);
				user = null;
			}
		}
		return user;
	}
	
	@Transactional
	public void feedLogonTracker(final LogonTracker logonTracker) throws Exception {
		applicationDao.executeUpdateWithQueryMapper("login", "insertLogonTracker", logonTracker);
	}
	
	@Transactional
	public void feedPasswordChangeTracker(final PasswordChangeTracker passwordChangeTracker) throws Exception {
		applicationDao.executeUpdateWithQueryMapper("login", "insertPasswordChangeTracker", passwordChangeTracker);
	}
	
	public User getUserFromDbUsingUserIdSwitchByUserType(final String userId, final String userType) throws Exception {
		switch(userType) {
			case USER_TYPE_EMPLOYEE : return employeeService.getEmployeeFromDbUsingUserId(userId);
			case USER_TYPE_TUTOR    : return tutorService.getRegisteredTutorFromDbUsingUserId(userId);
			case USER_TYPE_CUSTOMER : return customerService.getSubscribedCustomerFromDbUsingUserId(userId);
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

	public void changePassword(final User user, final String encryptedOldPassword, final String newPassword, final String emailIdOfUserInSession) throws Exception {
		final Date currentTimestamp = new Date();
		final String encryptedNewPassword = SecurityUtil.encrypt(SecurityUtil.decryptClientSide(newPassword));
		changePasswordAsPerUserType(user.getUserType(), user.getUserId(), encryptedNewPassword);
		user.setEncryptedPassword(encryptedNewPassword);
		final PasswordChangeTracker passwordChangeTracker = new PasswordChangeTracker();
		passwordChangeTracker.setUserId(user.getUserId());
		passwordChangeTracker.setUserType(user.getUserType());
		passwordChangeTracker.setChangeTimeMillis(currentTimestamp.getTime());
		passwordChangeTracker.setEncryptedPasswordOld(encryptedOldPassword);
		passwordChangeTracker.setEncryptedPasswordNew(encryptedNewPassword);
		feedPasswordChangeTracker(passwordChangeTracker);
		sendPasswordChangeEmailToUser(user, emailIdOfUserInSession);
	}
	
	public void sendPasswordChangeEmailToUser(final User user, final String emailIdOfUserInSession) throws Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", user.getName());
		MailUtils.sendMimeMessageEmail( 
				emailIdOfUserInSession, 
				null,
				null,
				"Alert - Your \"Seek Mentore\" password has been changed", 
				VelocityUtils.parseEmailTemplate(PASSWORD_CHANGE_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
	
	private void changePasswordAsPerUserType(final String userType, final String loggedInUserId, final String encryptedNewPassword) throws Exception {
		switch(userType) {
			case USER_TYPE_EMPLOYEE : {
				final Employee employee = new Employee();
				employee.setUserId(loggedInUserId);
				employee.setEncryptedPassword(encryptedNewPassword);
				applicationDao.executeUpdateWithQueryMapper("employee", "updateEmployeePassword", employee);
				break;
			}
			case USER_TYPE_TUTOR : {
				final RegisteredTutor registeredtutor = new RegisteredTutor();
				registeredtutor.setUserId(loggedInUserId);
				registeredtutor.setEncryptedPassword(encryptedNewPassword);
				applicationDao.executeUpdateWithQueryMapper("admin-registeredtutor", "updateRegisteredtutorPassword", registeredtutor);
				break;
			}
			case USER_TYPE_CUSTOMER : {
				final SubscribedCustomer subscribedcustomer = new SubscribedCustomer();
				subscribedcustomer.setUserId(loggedInUserId);
				subscribedcustomer.setEncryptedPassword(encryptedNewPassword);
				applicationDao.executeUpdateWithQueryMapper("admin-subscribedcustomer", "updateSubscribedCustomerPassword", subscribedcustomer);
				break;
			}
			default	: {
				throw new ApplicationException("Invalid Usertype " + userType);
			}
		}
	}
	
	/* Returns error message if any else returns EMPTY_STRING*/
	public String resetPassword(final Credential credential) throws Exception {
		String errorMessage = EMPTY_STRING;
		final User user = getUserFromDbUsingUserIdSwitchByUserType(credential.getUserId(), credential.getUserType());
		if (ValidationUtils.checkObjectAvailability(user)) {
			final Date currentTimestamp = new Date();
			final ForgotPasswordToken forgotPasswordToken = new ForgotPasswordToken();
			forgotPasswordToken.setUserId(user.getUserId());
			forgotPasswordToken.setUserType(user.getUserType());
			forgotPasswordToken.setToken(SecurityUtil.encrypt(UUIDGeneratorUtils.generateRandomGUID()));
			forgotPasswordToken.setIssueDateMillis(currentTimestamp.getTime());
			forgotPasswordToken.setExpiryDateMillis(currentTimestamp.getTime() + (12 * 60 * 60 * 1000)); // 12 hours
			forgotPasswordToken.setIsValid(YES);
			applicationDao.executeUpdateWithQueryMapper("login", "insertForgotPasswordToken", forgotPasswordToken);
			sendResetPasswordEmailToUser(user, forgotPasswordToken.getToken());
		} else {
			errorMessage = "No user found in system for the credentials provided.";
		}
		return errorMessage;
	}
	
	private void sendResetPasswordEmailToUser(final User user, final String token) throws Exception {
		final Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("addressName", user.getName());
		attributes.put("urlEncodedToken", URLEncoder.encode(token, "UTF-8"));
		MailUtils.sendMimeMessageEmail( 
				user.getEmailId(), 
				null,
				null,
				"Alert - Your \"Seek Mentore\" password has been reset", 
				VelocityUtils.parseEmailTemplate(PASSWORD_RESET_VELOCITY_TEMPLATE_PATH, attributes),
				null);
	}
}
