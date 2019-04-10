package com.service.components;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SupportConstants;
import com.constants.components.publicaccess.PublicAccessConstants;
import com.dao.ApplicationDao;
import com.model.ApplicationFile;
import com.model.User;
import com.model.components.Complaint;
import com.model.components.publicaccess.BecomeTutor;
import com.model.components.publicaccess.FindTutor;
import com.model.components.publicaccess.SubmitQuery;
import com.model.components.publicaccess.SubscribeWithUs;
import com.model.gridcomponent.GridComponent;
import com.model.rowmapper.BecomeTutorRowMapper;
import com.model.rowmapper.ComplaintRowMapper;
import com.model.rowmapper.FindTutorRowMapper;
import com.model.rowmapper.SubmitQueryRowMapper;
import com.model.rowmapper.SubscribeWithUsRowMapper;
import com.model.workbook.WorkbookReport;
import com.service.QueryMapperService;
import com.utils.GridQueryUtils;
import com.utils.PDFUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_ADMIN_SERVICE)
public class AdminService implements AdminConstants, SupportConstants, PublicAccessConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public List<BecomeTutor> getBecomeTutorList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("public-application", "selectBecomeTutor");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorExistingSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		boolean withoutParams = false;
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_FRESH);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorMultiApplicationStatusFilter");
				paramsMap.put("applicationStatusList", Arrays.asList(new String[] {APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING, APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING}));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SELECTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_REJECTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "becomeTutorMigratedFilter");
				withoutParams = true;
				break;
			}
		}
		if (withoutParams) {
			return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), new BecomeTutorRowMapper());
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new BecomeTutorRowMapper());
	}
	
	public BecomeTutor getBecomeTutorApplicationInDatabaseWithEmailId(final String emailId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find(queryMapperService.getQuerySQL("public-application", "selectBecomeTutor") 
									+ queryMapperService.getQuerySQL("public-application", "becomeTutorEmailFilter"), paramsMap, new BecomeTutorRowMapper());
	}
	
	public BecomeTutor getBecomeTutorApplicationInDatabaseWithContactNumber(final String contactNumber) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find(queryMapperService.getQuerySQL("public-application", "selectBecomeTutor") 
								+ queryMapperService.getQuerySQL("public-application", "becomeTutorContactNumberFilter"), paramsMap, new BecomeTutorRowMapper());
	}
	
	public BecomeTutor getBecomeTutor(final String becomeTutorSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("becomeTutorSerialId", becomeTutorSerialId);
		return applicationDao.find(queryMapperService.getQuerySQL("public-application", "selectBecomeTutor")
									+ queryMapperService.getQuerySQL("public-application", "becomeTutorSerialIdFilter"), paramsMap, new BecomeTutorRowMapper());
	}
	
	public Map<String, Boolean> getBecomeTutorFormUpdateStatus(final BecomeTutor becomeTutor) throws Exception {
		final Map<String, Boolean> securityAccess = new HashMap<String, Boolean>();
		securityAccess.put("becomeTutorFormEditMandatoryDisbaled", true);
		securityAccess.put("becomeTutorCanMakeContacted", false);
		securityAccess.put("becomeTutorCanMakeToBeRecontact", false);
		securityAccess.put("becomeTutorCanMakeVerified", false);
		securityAccess.put("becomeTutorCanMakeReverified", false);
		securityAccess.put("becomeTutorCanMakeRecontacted", false);
		securityAccess.put("becomeTutorCanMakeSelected", false);
		securityAccess.put("becomeTutorCanMakeFailVerified", false);
		securityAccess.put("becomeTutorCanMakeRejected", false);
		securityAccess.put("becomeTutorCanBlacklist", false);
		if (!ValidationUtils.checkIfResponseIsStringYes(becomeTutor.getIsBlacklisted())) {
			securityAccess.put("becomeTutorFormEditMandatoryDisbaled", false);
			securityAccess.put("becomeTutorCanBlacklist", true);
			switch(becomeTutor.getApplicationStatus()) {
				case APPLICATION_STATUS_FRESH : {
					securityAccess.put("becomeTutorCanMakeContacted", true);
					securityAccess.put("becomeTutorCanMakeToBeRecontact", true);
					securityAccess.put("becomeTutorCanMakeRejected", true);
					break;
				}
				case APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING : 
				case APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING : {
					securityAccess.put("becomeTutorCanMakeVerified", true);
					securityAccess.put("becomeTutorCanMakeFailVerified", true);
					securityAccess.put("becomeTutorCanMakeRejected", true);
					break;
				}
				case APPLICATION_STATUS_VERIFICATION_SUCCESSFUL : {
					securityAccess.put("becomeTutorCanMakeSelected", true);
					securityAccess.put("becomeTutorCanMakeRejected", true);
					break;
				}
				case APPLICATION_STATUS_VERIFICATION_FAILED : {
					securityAccess.put("becomeTutorCanMakeReverified", true);
					securityAccess.put("becomeTutorCanMakeRejected", true);
					break;
				}
				case APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED : {
					securityAccess.put("becomeTutorCanMakeRecontacted", true);
					securityAccess.put("becomeTutorCanMakeRejected", true);
					break;
				}
				case APPLICATION_STATUS_SELECTED : {
					break;
				}
				case APPLICATION_STATUS_REJECTED : {
					securityAccess.put("becomeTutorCanMakeRecontacted", true);
					securityAccess.put("becomeTutorCanMakeSelected", true);
					break;
				}
			}
		}
		return securityAccess;
	}
	
	public ApplicationFile downloadReportBecomeTutorList(final String grid, final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getBecomeTutorReportSheetName(grid), WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getBecomeTutorList(grid, gridComponent), BecomeTutor.class, SUPPORT_TEAM_REPORT));
		return new ApplicationFile(getBecomeTutorReportSheetName(grid) + "_BT_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	public ApplicationFile downloadBecomeTutorProfilePdf(final String becomeTutorSerialId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("becomeTutorSerialId", becomeTutorSerialId);
		final BecomeTutor becomeTutor = applicationDao.find(queryMapperService.getQuerySQL("public-application", "selectBecomeTutor") 
										+ queryMapperService.getQuerySQL("public-application", "becomeTutorSerialIdFilter"), paramsMap, new BecomeTutorRowMapper());
		if (ValidationUtils.checkObjectAvailability(becomeTutor)) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("becomeTutor", becomeTutor);
	        return new ApplicationFile(becomeTutor.getFirstName() + "_" + becomeTutor.getLastName() + "_BT_PROFILE" + PERIOD + FileConstants.EXTENSION_PDF, 
	        							PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(BECOME_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes)));
		}
		return null;
	}	
	
	private String getBecomeTutorReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_BECOME_TUTORS_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_BECOME_TUTORS_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_BECOME_TUTORS_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_BECOME_TUTORS_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_BECOME_TUTORS_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_BECOME_TUTORS_LIST : {
				return "REJECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REGISTERED_BECOME_TUTORS_LIST : {
				return "REGISTERED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistBecomeTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BecomeTutor> paramObjectList = new LinkedList<BecomeTutor>();
		for (final String becomeTutorSerialId : idList) {
			final BecomeTutor becomeTutor = new BecomeTutor();
			becomeTutor.setIsBlacklisted(YES);
			becomeTutor.setBlacklistedRemarks(comments);
			becomeTutor.setBlacklistedDateMillis(currentTimestamp.getTime());
			becomeTutor.setWhoBlacklisted(activeUser.getUserId());
			becomeTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			becomeTutor.setUpdatedBy(activeUser.getUserId());
			becomeTutor.setBecomeTutorSerialId(becomeTutorSerialId);
			paramObjectList.add(becomeTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateBlacklistBecomeTutor", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistBecomeTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BecomeTutor> paramObjectList = new LinkedList<BecomeTutor>();
		for (final String becomeTutorSerialId : idList) {
			final BecomeTutor becomeTutor = new BecomeTutor();
			becomeTutor.setIsBlacklisted(NO);
			becomeTutor.setUnblacklistedRemarks(comments);
			becomeTutor.setUnblacklistedDateMillis(currentTimestamp.getTime());
			becomeTutor.setWhoUnBlacklisted(activeUser.getUserId());
			becomeTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			becomeTutor.setUpdatedBy(activeUser.getUserId());
			becomeTutor.setBecomeTutorSerialId(becomeTutorSerialId);
			paramObjectList.add(becomeTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateUnBlacklistBecomeTutor", paramObjectList);
	}
	
	@Transactional
	public void takeActionOnBecomeTutor(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String queryId = EMPTY_STRING;
		final BecomeTutor becomeTutorActionObject = new BecomeTutor();
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING);
				becomeTutorActionObject.setIsContacted(YES);
				becomeTutorActionObject.setWhoContacted(activeUser.getUserId());
				becomeTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setContactedRemarks(comments);
				queryId = "updateContactedBecomeTutor";
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				becomeTutorActionObject.setIsContacted(YES);
				becomeTutorActionObject.setWhoContacted(activeUser.getUserId());
				becomeTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setContactedRemarks(comments);
				becomeTutorActionObject.setIsToBeRecontacted(YES);
				becomeTutorActionObject.setWhoSuggestedForRecontact(activeUser.getUserId());
				becomeTutorActionObject.setSuggestionDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setSuggestionRemarks(comments);
				queryId = "updateRecontactBecomeTutor";
				break;
			}
			case BUTTON_ACTION_REJECT : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_REJECTED);
				becomeTutorActionObject.setIsContacted(YES);
				becomeTutorActionObject.setWhoContacted(activeUser.getUserId());
				becomeTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setContactedRemarks(comments);
				becomeTutorActionObject.setIsRejected(YES);
				becomeTutorActionObject.setWhoRejected(activeUser.getUserId());
				becomeTutorActionObject.setRejectionDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setRejectionRemarks(comments);
				queryId = "updateRejectBecomeTutor";
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				becomeTutorActionObject.setIsAuthenticationVerified(YES);
				becomeTutorActionObject.setWhoVerified(activeUser.getUserId());
				becomeTutorActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifyBecomeTutor";
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_FAILED);
				becomeTutorActionObject.setIsAuthenticationVerified(NO);
				becomeTutorActionObject.setWhoVerified(activeUser.getUserId());
				becomeTutorActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifyBecomeTutor";
				break;
			}
			case BUTTON_ACTION_SELECT : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_SELECTED);
				becomeTutorActionObject.setIsSelected(YES);
				becomeTutorActionObject.setWhoSelected(activeUser.getUserId());
				becomeTutorActionObject.setSelectionDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setSelectionRemarks(comments);
				queryId = "updateSelectedBecomeTutor";
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				becomeTutorActionObject.setApplicationStatus(APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING);
				becomeTutorActionObject.setIsToBeRecontacted(NO);
				becomeTutorActionObject.setWhoRecontacted(activeUser.getUserId());
				becomeTutorActionObject.setRecontactedDateMillis(currentTimestamp.getTime());
				becomeTutorActionObject.setRecontactedRemarks(comments);
				queryId = "updateRecontactedBecomeTutor";
				break;
			}
		}
		becomeTutorActionObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		becomeTutorActionObject.setUpdatedBy(activeUser.getUserId());
		final List<BecomeTutor> paramObjectList = new LinkedList<BecomeTutor>();
		for (final String becomeTutorSerialId : idList) {
			final BecomeTutor becomeTutor = becomeTutorActionObject.clone();
			becomeTutor.setBecomeTutorSerialId(becomeTutorSerialId);
			paramObjectList.add(becomeTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", queryId, paramObjectList);
	}
	
	@Transactional
	public void updateBecomeTutorRecord(final BecomeTutor becomeTutor, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE BECOME_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE BECOME_TUTOR_SERIAL_ID = :becomeTutorSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						updateAttributesQuery.add("FIRST_NAME = :firstName");
						paramsMap.put("firstName", becomeTutor.getFirstName());
						break;
					}
					case "lastName" : {
						updateAttributesQuery.add("LAST_NAME = :lastName");
						paramsMap.put("lastName", becomeTutor.getLastName());
						break;
					}
					case "dateOfBirth" : {
						updateAttributesQuery.add("DATE_OF_BIRTH = :dateOfBirth");
						paramsMap.put("dateOfBirth", becomeTutor.getDateOfBirth());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", becomeTutor.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", becomeTutor.getEmailId());
						break;
					}
					case "gender" : {
						updateAttributesQuery.add("GENDER = :gender");
						paramsMap.put("gender", becomeTutor.getGender());
						break;
					}
					case "qualification" : {
						updateAttributesQuery.add("QUALIFICATION = :qualification");
						paramsMap.put("qualification", becomeTutor.getQualification());
						break;
					}
					case "primaryProfession" : {
						updateAttributesQuery.add("PRIMARY_PROFESSION = :primaryProfession");
						paramsMap.put("primaryProfession", becomeTutor.getPrimaryProfession());
						break;
					}
					case "transportMode" : {
						updateAttributesQuery.add("TRANSPORT_MODE = :transportMode");
						paramsMap.put("transportMode", becomeTutor.getTransportMode());
						break;
					}
					case "teachingExp" : {
						updateAttributesQuery.add("TEACHING_EXP = :teachingExp");
						paramsMap.put("teachingExp", becomeTutor.getTeachingExp());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrades");
						paramsMap.put("studentGrades", becomeTutor.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", becomeTutor.getSubjects());
						break;
					}
					case "locations" : {
						updateAttributesQuery.add("LOCATIONS = :locations");
						paramsMap.put("locations", becomeTutor.getLocations());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", becomeTutor.getPreferredTimeToCall());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", becomeTutor.getAdditionalDetails());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", becomeTutor.getReference());
						break;
					}
					case "preferredTeachingType" : {
						updateAttributesQuery.add("PREFERRED_TEACHING_TYPE = :preferredTeachingType");
						paramsMap.put("preferredTeachingType", becomeTutor.getPreferredTeachingType());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", becomeTutor.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("becomeTutorSerialId", becomeTutor.getBecomeTutorSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<FindTutor> getFindTutorList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("public-application", "selectFindTutor");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorExistingSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_FRESH);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorMultiApplicationStatusFilter");
				paramsMap.put("applicationStatusList", Arrays.asList(new String[] {APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING, APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING}));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SELECTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_FIND_TUTOR_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "findTutorApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_REJECTED);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new FindTutorRowMapper());
	}
	
	public ApplicationFile downloadAdminReportFindTutorList(final String grid, final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getFindTutorReportSheetName(grid), WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getFindTutorList(grid, gridComponent), FindTutor.class, SUPPORT_TEAM_REPORT));
		return new ApplicationFile(getFindTutorReportSheetName(grid) + "_FT_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	public ApplicationFile downloadFindTutorProfilePdf(final String findTutorSerialId) throws JAXBException, URISyntaxException, Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("findTutorSerialId", findTutorSerialId);
		final FindTutor findTutor = applicationDao.find(queryMapperService.getQuerySQL("public-application", "selectFindTutor") 
										+ queryMapperService.getQuerySQL("public-application", "findTutorEnquiryIdFilter"), paramsMap, new FindTutorRowMapper());
		if (ValidationUtils.checkObjectAvailability(findTutor)) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("findTutor", findTutor);
	        return new ApplicationFile(findTutor.getName() + "_FT_PROFILE" + PERIOD + FileConstants.EXTENSION_PDF, 
	        							PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(FIND_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes)));
		}
		return null;
	}	
	
	private String getFindTutorReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_FIND_TUTOR_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_FIND_TUTOR_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_FIND_TUTOR_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_FIND_TUTOR_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_FIND_TUTOR_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_FIND_TUTOR_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_FIND_TUTOR_LIST : {
				return "REJECTED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistFindTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<FindTutor> paramObjectList = new LinkedList<FindTutor>();
		for (final String findTutorSerialId : idList) {
			final FindTutor findTutor = new FindTutor();
			findTutor.setIsBlacklisted(YES);
			findTutor.setBlacklistedRemarks(comments);
			findTutor.setBlacklistedDateMillis(currentTimestamp.getTime());
			findTutor.setWhoBlacklisted(activeUser.getUserId());
			findTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			findTutor.setUpdatedBy(activeUser.getUserId());
			findTutor.setFindTutorSerialId(findTutorSerialId);
			paramObjectList.add(findTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateBlacklistFindTutor", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistFindTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<FindTutor> paramObjectList = new LinkedList<FindTutor>();
		for (final String findTutorSerialId : idList) {
			final FindTutor findTutor = new FindTutor();
			findTutor.setIsBlacklisted(NO);
			findTutor.setUnblacklistedRemarks(comments);
			findTutor.setUnblacklistedDateMillis(currentTimestamp.getTime());
			findTutor.setWhoUnBlacklisted(activeUser.getUserId());
			findTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			findTutor.setUpdatedBy(activeUser.getUserId());
			findTutor.setFindTutorSerialId(findTutorSerialId);
			paramObjectList.add(findTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateUnBlacklistFindTutor", paramObjectList);
	}
	
	@Transactional
	public void takeActionOnFindTutor(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String queryId = EMPTY_STRING;
		final FindTutor findTutorActionObject = new FindTutor();
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING);
				findTutorActionObject.setIsContacted(YES);
				findTutorActionObject.setWhoContacted(activeUser.getUserId());
				findTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setContactedRemarks(comments);
				queryId = "updateContactedFindTutor";
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				findTutorActionObject.setIsContacted(YES);
				findTutorActionObject.setWhoContacted(activeUser.getUserId());
				findTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setContactedRemarks(comments);
				findTutorActionObject.setIsToBeRecontacted(YES);
				findTutorActionObject.setWhoSuggestedForRecontact(activeUser.getUserId());
				findTutorActionObject.setSuggestionDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setSuggestionRemarks(comments);
				queryId = "updateRecontactFindTutor";
				break;
			}
			case BUTTON_ACTION_REJECT : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_REJECTED);
				findTutorActionObject.setIsContacted(YES);
				findTutorActionObject.setWhoContacted(activeUser.getUserId());
				findTutorActionObject.setContactedDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setContactedRemarks(comments);
				findTutorActionObject.setIsRejected(YES);
				findTutorActionObject.setWhoRejected(activeUser.getUserId());
				findTutorActionObject.setRejectionDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setRejectionRemarks(comments);
				queryId = "updateRejectFindTutor";
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				findTutorActionObject.setIsAuthenticationVerified(YES);
				findTutorActionObject.setWhoVerified(activeUser.getUserId());
				findTutorActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifyFindTutor";
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_FAILED);
				findTutorActionObject.setIsAuthenticationVerified(NO);
				findTutorActionObject.setWhoVerified(activeUser.getUserId());
				findTutorActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifyFindTutor";
				break;
			}
			case BUTTON_ACTION_SELECT : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_SELECTED);
				findTutorActionObject.setIsSelected(YES);
				findTutorActionObject.setWhoSelected(activeUser.getUserId());
				findTutorActionObject.setSelectionDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setSelectionRemarks(comments);
				queryId = "updateSelectedFindTutor";
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				findTutorActionObject.setApplicationStatus(APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING);
				findTutorActionObject.setIsToBeRecontacted(NO);
				findTutorActionObject.setWhoRecontacted(activeUser.getUserId());
				findTutorActionObject.setRecontactedDateMillis(currentTimestamp.getTime());
				findTutorActionObject.setRecontactedRemarks(comments);
				queryId = "updateRecontactedFindTutor";
				break;
			}
		}
		findTutorActionObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		findTutorActionObject.setUpdatedBy(activeUser.getUserId());
		final List<FindTutor> paramObjectList = new LinkedList<FindTutor>();
		for (final String findTutorSerialId : idList) {
			final FindTutor findTutor = findTutorActionObject.clone();
			findTutor.setFindTutorSerialId(findTutorSerialId);
			paramObjectList.add(findTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", queryId, paramObjectList);
	}
	
	@Transactional
	public void updateFindTutorRecord(final FindTutor findTutor, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE FIND_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE FIND_TUTOR_SERIAL_ID = :findTutorSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", findTutor.getName());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", findTutor.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", findTutor.getEmailId());
						break;
					}
					case "subscribedCustomer" : {
						updateAttributesQuery.add("SUBSCRIBED_CUSTOMER = :subscribedCustomer");
						paramsMap.put("subscribedCustomer", findTutor.getSubscribedCustomer());
						break;
					}
					case "studentGrade" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrade");
						paramsMap.put("studentGrade", findTutor.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", findTutor.getSubjects());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", findTutor.getPreferredTimeToCall());
						break;
					}
					case "location" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", findTutor.getLocation());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", findTutor.getReference());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", findTutor.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", findTutor.getAddressDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("findTutorSerialId", findTutor.getFindTutorSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<SubscribeWithUs> getSubscribeWithUsList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("public-application", "selectSubscribeWithUs");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsExistingSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_FRESH);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsMultiApplicationStatusFilter");
				paramsMap.put("applicationStatusList", Arrays.asList(new String[] {APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING, APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING}));
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_VERIFICATION_FAILED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_SELECTED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "subscribeWithUsApplicationStatusFilter");
				paramsMap.put("applicationStatus", APPLICATION_STATUS_REJECTED);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubscribeWithUsRowMapper());
	}
	
	public ApplicationFile downloadAdminReportSubscribeWithUsList(final String grid, final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getSubscribeWithUsReportSheetName(grid), WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getSubscribeWithUsList(grid, gridComponent), SubscribeWithUs.class, SUPPORT_TEAM_REPORT));
		return new ApplicationFile(getSubscribeWithUsReportSheetName(grid) + "_SWS_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	private String getSubscribeWithUsReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_SUBSCRIPTIONS_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_VERIFIED_SUBSCRIPTIONS_LIST : {
				return "NON_VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFIED_SUBSCRIPTIONS_LIST : {
				return "VERIFIED";
			}
			case RestMethodConstants.REST_METHOD_NAME_VERIFICATION_FAILED_SUBSCRIPTIONS_LIST : {
				return "VERIFICATION_FAILED";
			}
			case RestMethodConstants.REST_METHOD_NAME_TO_BE_RECONTACTED_SUBSCRIPTIONS_LIST : {
				return "TO_BE_RECONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_SELECTED_SUBSCRIPTIONS_LIST : {
				return "SELECTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_REJECTED_SUBSCRIPTIONS_LIST : {
				return "REJECTED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void blacklistSubscriptionList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<SubscribeWithUs> paramObjectList = new LinkedList<SubscribeWithUs>();
		for (final String subscribeWithUsSerialId : idList) {
			final SubscribeWithUs subscribeWithUs = new SubscribeWithUs();
			subscribeWithUs.setIsBlacklisted(YES);
			subscribeWithUs.setBlacklistedRemarks(comments);
			subscribeWithUs.setBlacklistedDateMillis(currentTimestamp.getTime());
			subscribeWithUs.setWhoBlacklisted(activeUser.getUserId());
			subscribeWithUs.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			subscribeWithUs.setUpdatedBy(activeUser.getUserId());
			subscribeWithUs.setSubscribeWithUsSerialId(subscribeWithUsSerialId);
			paramObjectList.add(subscribeWithUs);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateBlacklistSubscribeWithUs", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistSubscriptionList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<SubscribeWithUs> paramObjectList = new LinkedList<SubscribeWithUs>();
		for (final String subscribeWithUsSerialId : idList) {
			final SubscribeWithUs subscribeWithUs = new SubscribeWithUs();
			subscribeWithUs.setIsBlacklisted(NO);
			subscribeWithUs.setUnblacklistedRemarks(comments);
			subscribeWithUs.setUnblacklistedDateMillis(currentTimestamp.getTime());
			subscribeWithUs.setWhoUnBlacklisted(activeUser.getUserId());
			subscribeWithUs.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			subscribeWithUs.setUpdatedBy(activeUser.getUserId());
			subscribeWithUs.setSubscribeWithUsSerialId(subscribeWithUsSerialId);
			paramObjectList.add(subscribeWithUs);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", "updateUnBlacklistSubscribeWithUs", paramObjectList);
	}
	
	@Transactional
	public void takeActionOnSubscription(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String queryId = EMPTY_STRING;
		final SubscribeWithUs subscribeWithUsActionObject = new SubscribeWithUs();
		switch(button) {
			case BUTTON_ACTION_CONTACTED : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_CONTACTED_VERIFICATION_PENDING);
				subscribeWithUsActionObject.setIsContacted(YES);
				subscribeWithUsActionObject.setWhoContacted(activeUser.getUserId());
				subscribeWithUsActionObject.setContactedDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setContactedRemarks(comments);
				queryId = "updateContactedSubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_RECONTACT : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_SUGGESTED_TO_BE_RECONTACTED);
				subscribeWithUsActionObject.setIsContacted(YES);
				subscribeWithUsActionObject.setWhoContacted(activeUser.getUserId());
				subscribeWithUsActionObject.setContactedDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setContactedRemarks(comments);
				subscribeWithUsActionObject.setIsToBeRecontacted(YES);
				subscribeWithUsActionObject.setWhoSuggestedForRecontact(activeUser.getUserId());
				subscribeWithUsActionObject.setSuggestionDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setSuggestionRemarks(comments);
				queryId = "updateRecontactSubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_REJECT : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_REJECTED);
				subscribeWithUsActionObject.setIsContacted(YES);
				subscribeWithUsActionObject.setWhoContacted(activeUser.getUserId());
				subscribeWithUsActionObject.setContactedDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setContactedRemarks(comments);
				subscribeWithUsActionObject.setIsRejected(YES);
				subscribeWithUsActionObject.setWhoRejected(activeUser.getUserId());
				subscribeWithUsActionObject.setRejectionDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setRejectionRemarks(comments);
				queryId = "updateRejectSubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_VERIFY:
			case BUTTON_ACTION_REVERIFY : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_SUCCESSFUL);
				subscribeWithUsActionObject.setIsAuthenticationVerified(YES);
				subscribeWithUsActionObject.setWhoVerified(activeUser.getUserId());
				subscribeWithUsActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifySubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_FAIL_VERIFY : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_VERIFICATION_FAILED);
				subscribeWithUsActionObject.setIsAuthenticationVerified(NO);
				subscribeWithUsActionObject.setWhoVerified(activeUser.getUserId());
				subscribeWithUsActionObject.setVerificationDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setVerificationRemarks(comments);
				queryId = "updateVerifyFailverifyReverifySubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_SELECT : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_SELECTED);
				subscribeWithUsActionObject.setIsSelected(YES);
				subscribeWithUsActionObject.setWhoSelected(activeUser.getUserId());
				subscribeWithUsActionObject.setSelectionDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setSelectionRemarks(comments);
				queryId = "updateSelectedSubscribeWithUs";
				break;
			}
			case BUTTON_ACTION_RECONTACTED : {
				subscribeWithUsActionObject.setApplicationStatus(APPLICATION_STATUS_RECONTACTED_VERIFICATION_PENDING);
				subscribeWithUsActionObject.setIsToBeRecontacted(NO);
				subscribeWithUsActionObject.setWhoRecontacted(activeUser.getUserId());
				subscribeWithUsActionObject.setRecontactedDateMillis(currentTimestamp.getTime());
				subscribeWithUsActionObject.setRecontactedRemarks(comments);
				queryId = "updateRecontactedSubscribeWithUs";
				break;
			}
		}
		subscribeWithUsActionObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		subscribeWithUsActionObject.setUpdatedBy(activeUser.getUserId());
		final List<SubscribeWithUs> paramObjectList = new LinkedList<SubscribeWithUs>();
		for (final String subscribeWithUsSerialId : idList) {
			final SubscribeWithUs subscribeWithUs = subscribeWithUsActionObject.clone();
			subscribeWithUs.setSubscribeWithUsSerialId(subscribeWithUsSerialId);
			paramObjectList.add(subscribeWithUs);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", queryId, paramObjectList);
	}
	
	@Transactional
	public void updateSubscriptionRecord(final SubscribeWithUs subscription, final List<String> changedAttributes, final User activeUser) {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE SUBSCRIBE_WITH_US SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE SUBSCRIBE_WITH_US_SERIAL_ID = :subscribeWithUsSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "firstName" : {
						updateAttributesQuery.add("FIRST_NAME = :firstName");
						paramsMap.put("firstName", subscription.getFirstName());
						break;
					}
					case "lastName" : {
						updateAttributesQuery.add("LAST_NAME = :lastName");
						paramsMap.put("lastName", subscription.getLastName());
						break;
					}
					case "contactNumber" : {
						updateAttributesQuery.add("CONTACT_NUMBER = :contactNumber");
						paramsMap.put("contactNumber", subscription.getContactNumber());
						break;
					}
					case "emailId" : {
						updateAttributesQuery.add("EMAIL_ID = :emailId");
						paramsMap.put("emailId", subscription.getEmailId());
						break;
					}
					case "subscribedCustomer" : {
						updateAttributesQuery.add("SUBSCRIBED_CUSTOMER = :subscribedCustomer");
						paramsMap.put("subscribedCustomer", subscription.getSubscribedCustomer());
						break;
					}
					case "studentGrades" : {
						updateAttributesQuery.add("STUDENT_GRADE = :studentGrade");
						paramsMap.put("studentGrade", subscription.getStudentGrade());
						break;
					}
					case "subjects" : {
						updateAttributesQuery.add("SUBJECTS = :subjects");
						paramsMap.put("subjects", subscription.getSubjects());
						break;
					}
					case "preferredTimeToCall" : {
						updateAttributesQuery.add("PREFERRED_TIME_TO_CALL = :preferredTimeToCall");
						paramsMap.put("preferredTimeToCall", subscription.getPreferredTimeToCall());
						break;
					}
					case "locations" : {
						updateAttributesQuery.add("LOCATION = :location");
						paramsMap.put("location", subscription.getLocation());
						break;
					}
					case "reference" : {
						updateAttributesQuery.add("REFERENCE = :reference");
						paramsMap.put("reference", subscription.getReference());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", subscription.getAddressDetails());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", subscription.getAdditionalDetails());
						break;
					}
				}
			}
		}
		paramsMap.put("subscribeWithUsSerialId", subscription.getSubscribeWithUsSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public List<SubmitQuery> getSubmitQueryList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("public-application", "selectSubmitQuery");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("public-application", "submitQueryExistingSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "submitQueryQueryStatusFilter");
				paramsMap.put("queryStatus", QUERY_STATUS_FRESH);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_ANSWERED_QUERY_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "submitQueryQueryStatusFilter");
				paramsMap.put("queryStatus", QUERY_STATUS_RESPONDED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("public-application", "submitQueryQueryStatusFilter");
				paramsMap.put("queryStatus", QUERY_STATUS_PUT_ON_HOLD);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new SubmitQueryRowMapper());
	}
	
	public ApplicationFile downloadAdminReportSubmitQueryList(final String grid, final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet(getSubmitQueryReportSheetName(grid), WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getSubmitQueryList(grid, gridComponent), SubmitQuery.class, SUPPORT_TEAM_REPORT));
		return new ApplicationFile(getSubmitQueryReportSheetName(grid) + "_QUERY_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	private String getSubmitQueryReportSheetName(final String grid) {
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_NON_CONTACTED_QUERY_LIST : {
				return "NON_CONTACTED";
			}
			case RestMethodConstants.REST_METHOD_NAME_NON_ANSWERED_QUERY_LIST : {
				return "NOT_ANSWERED";
			}
			case RestMethodConstants.REST_METHOD_NAME_ANSWERED_QUERY_LIST : {
				return "ANSWERED";
			}
		}
		return "COMPLETE";
	}
	
	@Transactional
	public void takeActionOnSubmitQuery(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String querySQLId = EMPTY_STRING;
		final SubmitQuery submitQueryActionObject = new SubmitQuery();
		switch(button) {
			case BUTTON_ACTION_RESPOND : {
				submitQueryActionObject.setQueryStatus(QUERY_STATUS_RESPONDED);
				submitQueryActionObject.setIsContacted(YES);
				submitQueryActionObject.setWhoContacted(activeUser.getUserId());
				submitQueryActionObject.setContactedDateMillis(currentTimestamp.getTime());
				submitQueryActionObject.setQueryResponse(comments);
				querySQLId = "updateRespondSubmitQuery";
				break;
			}
			case BUTTON_ACTION_PUT_ON_HOLD : {
				submitQueryActionObject.setQueryStatus(QUERY_STATUS_PUT_ON_HOLD);
				submitQueryActionObject.setNotAnswered(YES);
				submitQueryActionObject.setWhoNotAnswered(activeUser.getUserId());
				submitQueryActionObject.setNotAnsweredReason(comments);
				querySQLId = "updateHoldSubmitQuery";
				break;
			}
		}
		submitQueryActionObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		submitQueryActionObject.setUpdatedBy(activeUser.getUserId());
		final List<SubmitQuery> paramObjectList = new LinkedList<SubmitQuery>();
		for (final String querySerialId : idList) {
			final SubmitQuery submitQuery = submitQueryActionObject.clone();
			submitQuery.setQuerySerialId(querySerialId);
			paramObjectList.add(submitQuery);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("public-application", querySQLId, paramObjectList);
	}
	
	@Transactional
	public void updateSubmitQueryRecord(final SubmitQuery query, final List<String> changedAttributes, final User activeUser) {
		
	}
	
	public List<SubmitQuery> getSubmitQueryListForQueryResponded(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, SubmitQuery.class);
		} else {
			gridComponent = new GridComponent(SubmitQuery.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("public-application", "submitQueryRespondedNonEmailSentFilter"));
		return getSubmitQueryList(RestMethodConstants.REST_METHOD_NAME_ANSWERED_QUERY_LIST, gridComponent);
	}
	
	public List<Complaint> getComplaintList(final String grid, final GridComponent gridComponent) throws Exception {
		final String baseQuery = queryMapperService.getQuerySQL("complaint", "selectComplaint");
		String existingFilterQueryString = EMPTY_STRING;
		final String existingSorterQueryString = queryMapperService.getQuerySQL("complaint", "complaintExistingSorter");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_CUSTOMER_COMPLAINT_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("complaint", "complaintUserComplaintStatusFilter");
				paramsMap.put("complaintStatus", COMPLAINT_STATUS_FRESH);
				paramsMap.put("complaintUser", USER_CUSTOMER);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_TUTOR_COMPLAINT_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("complaint", "complaintUserComplaintStatusFilter");
				paramsMap.put("complaintStatus", COMPLAINT_STATUS_FRESH);
				paramsMap.put("complaintUser", USER_TUTOR);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_EMPLOYEE_COMPLAINT_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("complaint", "complaintUserComplaintStatusFilter");
				paramsMap.put("complaintStatus", COMPLAINT_STATUS_FRESH);
				paramsMap.put("complaintUser", USER_EMPLOYEE);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_RESOLVED_COMPLAINT_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("complaint", "complaintComplaintStatusFilter");
				paramsMap.put("complaintStatus", COMPLAINT_STATUS_RESOLVED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_NOT_RESOLVED_COMPLAINT_LIST : {
				existingFilterQueryString = queryMapperService.getQuerySQL("complaint", "complaintComplaintStatusFilter");
				paramsMap.put("complaintStatus", COMPLAINT_STATUS_PUT_ON_HOLD);
				break;
			}
		}
		return applicationDao.findAll(GridQueryUtils.createGridQuery(baseQuery, existingFilterQueryString, existingSorterQueryString, gridComponent), paramsMap, new ComplaintRowMapper());
	}
	
	@Transactional
	public void takeActionOnComplaint(final String button, final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		String queryId = EMPTY_STRING;
		final Complaint complaintActionObject = new Complaint();
		switch(button) {
			case BUTTON_ACTION_RESPOND : {
				complaintActionObject.setComplaintStatus(COMPLAINT_STATUS_RESOLVED);
				complaintActionObject.setIsContacted(YES);
				complaintActionObject.setWhoContacted(activeUser.getUserId());
				complaintActionObject.setContactedDateMillis(currentTimestamp.getTime());
				complaintActionObject.setComplaintResponse(comments);
				queryId = "updateRespondComplaint";
				break;
			}
			case BUTTON_ACTION_PUT_ON_HOLD : {
				complaintActionObject.setComplaintStatus(COMPLAINT_STATUS_PUT_ON_HOLD);
				complaintActionObject.setNotResolved(YES);
				complaintActionObject.setWhoNotResolved(activeUser.getUserId());
				complaintActionObject.setNotResolvedReason(comments);
				queryId = "updateHoldComplaint";
				break;
			}
		}
		complaintActionObject.setRecordLastUpdatedMillis(currentTimestamp.getTime());
		complaintActionObject.setUpdatedBy(activeUser.getUserId());
		final List<Complaint> paramObjectList = new LinkedList<Complaint>();
		for (final String complaintSerialId : idList) {
			final Complaint complaint = complaintActionObject.clone();
			complaint.setComplaintSerialId(complaintSerialId);
			paramObjectList.add(complaint);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("complaint", queryId, paramObjectList);
	}
	
	@Transactional
	public void updateComplaintRecord(final Complaint complaint, final List<String> changedAttributes, final User activeUser) {
		
	}
}
