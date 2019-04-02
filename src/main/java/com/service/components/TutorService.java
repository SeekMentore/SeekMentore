package com.service.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.MailConstants;
import com.constants.RestMethodConstants;
import com.constants.components.AdminConstants;
import com.constants.components.SelectLookupConstants;
import com.constants.components.TutorConstants;
import com.dao.ApplicationDao;
import com.exception.ApplicationException;
import com.model.ApplicationFile;
import com.model.User;
import com.model.components.BankDetail;
import com.model.components.RegisteredTutor;
import com.model.components.TutorDocument;
import com.model.components.publicaccess.BecomeTutor;
import com.model.gridcomponent.GridComponent;
import com.model.mail.MailAttachment;
import com.model.rowmappers.BankDetailRowMapper;
import com.model.rowmappers.RegisteredTutorContactNumberRowMapper;
import com.model.rowmappers.RegisteredTutorEmailRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.TutorDocumentRowMapper;
import com.model.workbook.WorkbookReport;
import com.service.QueryMapperService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.GridQueryUtils;
import com.utils.MailUtils;
import com.utils.PDFUtils;
import com.utils.SecurityUtil;
import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_TUTOR_SERVICE)
public class TutorService implements TutorConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient AdminService adminService;
	
	@Autowired
	private transient QueryMapperService queryMapperService;
	
	@PostConstruct
	public void init() {}
	
	public List<RegisteredTutor> getRegisteredTutorList(final GridComponent gridComponent) throws Exception {
		return applicationDao.findAllWithoutParams(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor"), null, null, gridComponent), new RegisteredTutorRowMapper());
	}
	
	public List<RegisteredTutor> getRegisteredTutorListWithParams(final GridComponent gridComponent, final Map<String, Object> paramsMap) throws Exception {
		return applicationDao.findAll(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor"), null, null, gridComponent), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public ApplicationFile downloadAdminReportRegisteredTutorList(final GridComponent gridComponent) throws Exception {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("REGISTERED_TUTORS", WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(getRegisteredTutorList(gridComponent), RegisteredTutor.class, AdminConstants.ADMIN_REPORT));
		return new ApplicationFile("REGISTERED_TUTORS_REPORT" + PERIOD + FileConstants.EXTENSION_XLSX, WorkbookUtils.createWorkbook(workbookReport));
	}
	
	public ApplicationFile downloadRegisteredTutorProfilePdf(final String tutorSerialId, final Boolean isAdminProfile) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		final RegisteredTutor registeredTutor = applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
												+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorTutorIdFilter"), paramsMap, new RegisteredTutorRowMapper());
		if (ValidationUtils.checkObjectAvailability(registeredTutor)) {
			registeredTutor.setRegisteredTutorEmails(applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutorEmail") 
														+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorEmailTutorIdFilter"), paramsMap, new RegisteredTutorEmailRowMapper()));
			registeredTutor.setRegisteredTutorContactNumbers(applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutorContactNumber") 
																+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorContactNumberTutorIdFilter"), paramsMap, new RegisteredTutorContactNumberRowMapper()));
			registeredTutor.setDocuments(getTutorDocumentList(registeredTutor.getTutorSerialId()));
			registeredTutor.setHasProfilePicture(false);
			if (ValidationUtils.checkNonEmptyList(registeredTutor.getDocuments())) {
				for (final TutorDocument tutorDocument : registeredTutor.getDocuments()) {
					if ("Profile Photo".equals(ApplicationUtils.getSelectLookupItemLabel(SelectLookupConstants.SELECT_LOOKUP_TABLE_TUTOR_DOCUMENT_TYPE_LOOKUP, tutorDocument.getDocumentType()))) {
						registeredTutor.setHasProfilePicture(true);
						registeredTutor.setProfilePicturePath("/images/profile/registeredtutor/"+tutorSerialId+"/"+tutorDocument.getFilename());
					}
				}
			}
			if (isAdminProfile) {
				registeredTutor.setBankDetails(getBankDetailList(registeredTutor.getTutorSerialId()));
			}
			final Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("registeredTutor", registeredTutor);
	        attributes.put("fullAdminProfile", isAdminProfile);
	        return new ApplicationFile(registeredTutor.getName() + "_PROFILE" + PERIOD + FileConstants.EXTENSION_PDF, PDFUtils.getPDFByteArrayFromHTMLString(VelocityUtils.parsePDFTemplate(REGISTERED_TUTOR_PROFILE_VELOCITY_TEMPLATE_PATH, attributes)));
		}
		return null;
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithEmailId(final String emailId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("emailId", emailId);
		return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
								+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorEmailFilter"), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public RegisteredTutor getRegisteredTutorInDatabaseWithContactNumber(final String contactNumber) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("contactNumber", contactNumber);
		return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
								+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorContactNumberFilter"), paramsMap, new RegisteredTutorRowMapper());
	}
	
	public List<TutorDocument> getTutorDocumentList(final String tutorSerialId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		return applicationDao.findAll(
				GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument"), 
											queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentTutorIdFilter"), 
											queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentFilenameSorter"), 
											gridComponent), paramsMap, new TutorDocumentRowMapper());
	}
	
	public List<TutorDocument> getTutorDocumentList(final String tutorSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		return applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument") 
										+ queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentTutorIdFilter"), paramsMap, new TutorDocumentRowMapper());
	}
	
	public List<TutorDocument> getTutorDocumentList(final List<String> documentSerialIdList) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("documentSerialIdList", documentSerialIdList);
		return applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument") 
										+ queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentMultiDocumentIdFilter"), paramsMap, new TutorDocumentRowMapper());
	}
	
	public TutorDocument getTutorDocument(final String documentSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("documentSerialId", documentSerialId);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocument"));
		query.append(queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentDocumentIdFilter"));
		final TutorDocument tutorDocument = applicationDao.find(query.toString(), paramsMap, new TutorDocumentRowMapper());
		tutorDocument.setContent(FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(tutorDocument.getFsKey()));
		return tutorDocument;
	}
	
	public ApplicationFile downloadTutorDocument(final String documentSerialId) throws Exception {
		final TutorDocument tutorDocument = getTutorDocument(documentSerialId);
		return new ApplicationFile(tutorDocument.getFilename(), tutorDocument.getContent());
	}
	
	public List<BankDetail> getBankDetailList(final String tutorSerialId, final GridComponent gridComponent) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		return applicationDao.findAll(GridQueryUtils.createGridQuery(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "selectBankDetail"), 
																	queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankdetailTutorIdFilter"), 
																	queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankdetailExistingSorter"), gridComponent), paramsMap, new BankDetailRowMapper());
	}
	
	public List<BankDetail> getBankDetailList(final String tutorSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		return applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "selectBankDetail") 
										+ queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankdetailTutorIdFilter"), paramsMap, new BankDetailRowMapper());
	}
	
	public List<BankDetail> getBankDetailList(final List<String> bankAccountSerialIdList) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("bankAccountSerialIdList", bankAccountSerialIdList);
		return applicationDao.findAll(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "selectBankDetail") 
										+ queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankDetailMultiBankAccountIdFilter"), paramsMap, new BankDetailRowMapper());
	}
	
	public BankDetail getBankDetail(final String bankAccountSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("bankAccountSerialId", bankAccountSerialId);
		final StringBuilder query = new StringBuilder(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "selectBankDetail"));
		query.append(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "bankDetailBankAccountIdilter"));
		return applicationDao.find(query.toString(), paramsMap, new BankDetailRowMapper());
	}
	
	@Transactional
	public void blacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<RegisteredTutor> paramObjectList = new LinkedList<RegisteredTutor>();
		for (final String tutorSerialId : idList) {
			final RegisteredTutor registeredTutor = new RegisteredTutor();
			registeredTutor.setIsBlacklisted(YES);
			registeredTutor.setBlacklistedRemarks(comments);
			registeredTutor.setBlacklistedDateMillis(currentTimestamp.getTime());
			registeredTutor.setWhoBlacklisted(activeUser.getUserId());
			registeredTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			registeredTutor.setUpdatedBy(activeUser.getUserId());
			registeredTutor.setTutorSerialId(tutorSerialId);
			paramObjectList.add(registeredTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor", "updateRegisteredTutorBlacklist", paramObjectList);
	}
	
	@Transactional
	public void unBlacklistRegisteredTutorList(final List<String> idList, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<RegisteredTutor> paramObjectList = new LinkedList<RegisteredTutor>();
		for (final String tutorSerialId : idList) {
			final RegisteredTutor registeredTutor = new RegisteredTutor();
			registeredTutor.setIsBlacklisted(NO);
			registeredTutor.setUnblacklistedRemarks(comments);
			registeredTutor.setUnblacklistedDateMillis(currentTimestamp.getTime());
			registeredTutor.setWhoUnBlacklisted(activeUser.getUserId());
			registeredTutor.setRecordLastUpdatedMillis(currentTimestamp.getTime());
			registeredTutor.setUpdatedBy(activeUser.getUserId());
			registeredTutor.setTutorSerialId(tutorSerialId);
			paramObjectList.add(registeredTutor);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor", "updateRegisteredTutorUnBlacklist", paramObjectList);
	}
	
	@Transactional
	public void aprroveTutorDocumentList(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<TutorDocument> paramObjectList = new LinkedList<TutorDocument>();
		for (final String documentSerialId : idList) {
			final TutorDocument tutorDocument = new TutorDocument();
			tutorDocument.setIsApproved(YES);
			tutorDocument.setWhoActed(activeUser.getUserId());
			tutorDocument.setRemarks(comments);
			tutorDocument.setActionDateMillis(currentTimestamp.getTime());
			tutorDocument.setDocumentSerialId(documentSerialId);
			paramObjectList.add(tutorDocument);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "updateTakeActionTutorDocument", paramObjectList);
	}
	
	@Transactional
	public void rejectTutorDocumentList(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<TutorDocument> paramObjectList = new LinkedList<TutorDocument>();
		for (final String documentSerialId : idList) {
			final TutorDocument tutorDocument = new TutorDocument();
			tutorDocument.setIsApproved(NO);
			tutorDocument.setWhoActed(activeUser.getUserId());
			tutorDocument.setRemarks(comments);
			tutorDocument.setActionDateMillis(currentTimestamp.getTime());
			tutorDocument.setDocumentSerialId(documentSerialId);
			paramObjectList.add(tutorDocument);
		}
		if (ValidationUtils.checkNonEmptyList(paramObjectList)) {
			applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "updateTakeActionTutorDocument", paramObjectList);
			sendTutorDocumentListRejectionEmails(idList, tutorSerialId, comments, activeUser);
		}
	}
	
	public void sendTutorDocumentListRejectionEmails(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		final RegisteredTutor registeredTutor = getRegisteredTutor(tutorSerialId);
		if (ValidationUtils.checkObjectAvailability(registeredTutor)) {
			final List<TutorDocument> tutorDocuments = getTutorDocumentList(idList);
			if (ValidationUtils.checkNonEmptyList(tutorDocuments)) {
				final Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("addressName", registeredTutor.getName());
				attributes.put("tutorDocuments", tutorDocuments);
				final List<MailAttachment> attachments = new ArrayList<MailAttachment>();
				for (final TutorDocument tutorDocument : tutorDocuments) {
					attachments.add(new MailAttachment(tutorDocument.getFilename(), FileSystemUtils.readContentFromFileOnApplicationFileSystemUsingKey(tutorDocument.getFsKey()), FileConstants.APPLICATION_TYPE_OCTET_STEAM));
				}
				MailUtils.sendMimeMessageEmail( 
						registeredTutor.getEmailId(), 
						null,
						null,
						"Your document/s has been asked for Re-upload", 
						VelocityUtils.parseEmailTemplate(REGISTERED_TUTOR_DOCUMENT_REJECTED_VELOCITY_TEMPLATE_PATH, attributes),
						attachments);
			}
		}
	}
	
	public void sendTutorDocumentListReminderEmails(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		if (ValidationUtils.checkNonEmptyList(idList)) {
			final RegisteredTutor registeredTutor = getRegisteredTutor(tutorSerialId);
			if (ValidationUtils.checkObjectAvailability(registeredTutor)) {
				final List<TutorDocument> tutorDocuments = getTutorDocumentList(idList);
				if (ValidationUtils.checkNonEmptyList(tutorDocuments)) {
					final Map<String, Object> attributes = new HashMap<String, Object>();
					attributes.put("addressName", registeredTutor.getName());
					attributes.put("tutorDocuments", tutorDocuments);
					MailUtils.sendMimeMessageEmail( 
							registeredTutor.getEmailId(), 
							null,
							null,
							"Your document/s has been asked for upload", 
							VelocityUtils.parseEmailTemplate(REGISTERED_TUTOR_DOCUMENT_REMINDER_VELOCITY_TEMPLATE_PATH, attributes),
							null);
				}
			}
		}
	}
	
	@Transactional
	public void aprroveBankAccountList(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BankDetail> paramObjectList = new LinkedList<BankDetail>();
		for (final String bankAccountSerialId : idList) {
			final BankDetail bankDetail = new BankDetail();
			bankDetail.setIsApproved(YES);
			bankDetail.setWhoActed(activeUser.getUserId());
			bankDetail.setRemarks(comments);
			bankDetail.setActionDateMillis(currentTimestamp.getTime());
			bankDetail.setBankAccountSerialId(bankAccountSerialId);
			paramObjectList.add(bankDetail);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateTakeActionBankDetail", paramObjectList);
	}
	
	@Transactional
	public void makeDefaultBankAccount(final String bankAccountSerialId, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		resetPreviousDefaultBankAccount(tutorSerialId);
		final Date currentTimestamp = new Date();
		final BankDetail bankDetail = new BankDetail();
		bankDetail.setIsDefault(YES);;
		bankDetail.setWhoActed(activeUser.getUserId());
		bankDetail.setRemarks(comments);
		bankDetail.setActionDateMillis(currentTimestamp.getTime());
		bankDetail.setBankAccountSerialId(bankAccountSerialId);
		applicationDao.executeUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateMakeDefaultBankDetail", bankDetail);
	}
	
	private void resetPreviousDefaultBankAccount(final String tutorSerialId) throws Exception {
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("tutorSerialId", tutorSerialId);
		applicationDao.executeUpdate(queryMapperService.getQuerySQL("admin-registeredtutor-bankdetail", "resetPreviousDefaultBankAccount"), paramsMap);
	}
	
	@Transactional
	public void rejectBankAccountList(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		final Date currentTimestamp = new Date();
		final List<BankDetail> paramObjectList = new LinkedList<BankDetail>();
		for (final String bankAccountSerialId : idList) {
			final BankDetail bankDetail = new BankDetail();
			bankDetail.setIsApproved(NO);
			bankDetail.setWhoActed(activeUser.getUserId());
			bankDetail.setRemarks(comments);
			bankDetail.setActionDateMillis(currentTimestamp.getTime());
			bankDetail.setBankAccountSerialId(bankAccountSerialId);
			paramObjectList.add(bankDetail);
		}
		applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-bankdetail", "updateTakeActionBankDetail", paramObjectList);
		sendBankAccountListRejectionEmails(idList, tutorSerialId, comments, activeUser);
	}
	
	public void sendBankAccountListRejectionEmails(final List<String> idList, final String tutorSerialId, final String comments, final User activeUser) throws Exception {
		// @ TODO - Email functionality 
	}
	
	public void sendProfileGenerationEmailToRegisteredTutorList(final List<RegisteredTutor> registeredTutorList) throws Exception {
		final List<Map<String, Object>> mailParamList = new ArrayList<Map<String, Object>>();
		for (final RegisteredTutor registeredTutorObj : registeredTutorList) {
			final Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("addressName", registeredTutorObj.getName());
			attributes.put("userId", registeredTutorObj.getUserId());
			attributes.put("temporaryPassword", SecurityUtil.decrypt(registeredTutorObj.getEncryptedPassword()));
			final Map<String, Object> mailParams = new HashMap<String, Object>();
			mailParams.put(MailConstants.MAIL_PARAM_TO, registeredTutorObj.getEmailId());
			mailParams.put(MailConstants.MAIL_PARAM_SUBJECT, "Your Seek Mentore \"Tutor\" profile is created");
			mailParams.put(MailConstants.MAIL_PARAM_MESSAGE, VelocityUtils.parseEmailTemplate(PROFILE_CREATION_VELOCITY_TEMPLATE_PATH, attributes));
			mailParamList.add(mailParams);
		}
		MailUtils.sendMultipleMimeMessageEmail(mailParamList);
	}
	
	public List<BecomeTutor> getBecomeTutorListForApplicationStatusSelected(final Boolean limitRecords, final Integer limit) throws Exception {
		GridComponent gridComponent = null;
		if (limitRecords) {
			gridComponent = new GridComponent(1, limit, BecomeTutor.class);
		} else {
			gridComponent = new GridComponent(BecomeTutor.class);
		}
		gridComponent.setAdditionalFilterQueryString(queryMapperService.getQuerySQL("public-application", "becomeTutorNonMigratedFilter"));
		return adminService.getBecomeTutorList(RestMethodConstants.REST_METHOD_NAME_SELECTED_BECOME_TUTORS_LIST, gridComponent);
	}
	
	public String getFolderPathToUploadTutorDocuments(final String tutorSerialId) {
		return "secured/tutor/documents/" + tutorSerialId;
	}
	
	@Transactional
	public void uploadTutorDocuments(final List<TutorDocument> documents, final String tutorSerialId, final Boolean isAdminOverride, final User activeUser) throws Exception {
		if (ValidationUtils.checkNonEmptyList(documents)) {
			final Date currentTimeStamp = new Date();
			String queryIdInsert = EMPTY_STRING;
			if (isAdminOverride) {
				queryIdInsert = "insertTutorDocumentFromAdmin";
			} else {
				queryIdInsert = "insertTutorDocumentFromTutor";
			}
			final String folderPathToUploadDocuments = getFolderPathToUploadTutorDocuments(tutorSerialId);
			final List<String> documentTypeList = new ArrayList<String>();
			for (final TutorDocument document : documents) {
				documentTypeList.add(document.getDocumentType());
			}
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("tutorSerialId", tutorSerialId);
			params.put("documentTypeList", documentTypeList);
			final String queryToSelectOlderFileName = queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "selectTutorDocumentFileNameAndFSKey") + 
														queryMapperService.getQuerySQL("admin-registeredtutor-tutordocument", "tutorDocumentMultiDocumentTypeFilter");
			final List<Map<String, Object>> documentsToBeDeletedFromFileSystem = applicationDao.findAll(queryToSelectOlderFileName, params);
			for (final Map<String, Object> document : documentsToBeDeletedFromFileSystem) {
				FileSystemUtils.deleteFileInFolderOnApplicationFileSystemUsingKey(String.valueOf(document.get("FS_KEY")), activeUser);
			}
			for (final TutorDocument document : documents) {
				document.setDocumentSerialId(UUIDGeneratorUtils.generateSerialGUID());
				document.setTutorSerialId(tutorSerialId);
				document.setFilename(generateFileNameForDocumentTypeAndIncomingFile(document));
				document.setFsKey(FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, document.getFilename(), document.getContent()));
				if (isAdminOverride) {
					document.setIsApproved(YES);
					document.setWhoActed(activeUser.getUserId());
					document.setActionDateMillis(currentTimeStamp.getTime());
					document.setRemarks("Admin override");
				}
			}
			applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", "deleteTutorDocumentForTutorIdDocumentType", documents);
			applicationDao.executeBatchUpdateWithQueryMapper("admin-registeredtutor-tutordocument", queryIdInsert, documents);
		}
	}
	
	private String generateFileNameForDocumentTypeAndIncomingFile(final TutorDocument tutorDocument) {
		final String fileExtension = tutorDocument.getFilename().substring(tutorDocument.getFilename().lastIndexOf(PERIOD) + 1);
		switch(tutorDocument.getDocumentType()) {
			case DOCUMENT_TYPE_PROFILE_PHOTO : return "Profile_Photo." + fileExtension;
			case DOCUMENT_TYPE_PAN_CARD : return "PAN_Card." + fileExtension;
			case DOCUMENT_TYPE_AADHAAR_CARD : return "Aadhaar_Card." + fileExtension;
		}
		throw new ApplicationException("Invalid Document Type");
	}
	
	@Transactional
	public void updateTutorRecord(final RegisteredTutor tutor, final List<String> changedAttributes, final User activeUser) throws Exception {
		final Date currenTimestamp = new Date();
		final String baseQuery = "UPDATE REGISTERED_TUTOR SET";
		final List<String> updateAttributesQuery = new ArrayList<String>();
		final String existingFilterQueryString = "WHERE TUTOR_SERIAL_ID = :tutorSerialId";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		if (ValidationUtils.checkNonEmptyList(changedAttributes)) {
			for (final String attributeName : changedAttributes) {
				switch(attributeName) {
					case "name" : {
						updateAttributesQuery.add("NAME = :name");
						paramsMap.put("name", tutor.getName());
						break;
					}
					case "dateOfBirth" : {
						updateAttributesQuery.add("DATE_OF_BIRTH = :dateOfBirth");
						paramsMap.put("dateOfBirth", tutor.getDateOfBirth());
						break;
					}
					case "gender" : {
						updateAttributesQuery.add("GENDER = :gender");
						paramsMap.put("gender", tutor.getGender());
						break;
					}
					case "qualification" : {
						updateAttributesQuery.add("QUALIFICATION = :qualification");
						paramsMap.put("qualification", tutor.getQualification());
						break;
					}
					case "primaryProfession" : {
						updateAttributesQuery.add("PRIMARY_PROFESSION = :primaryProfession");
						paramsMap.put("primaryProfession", tutor.getPrimaryProfession());
						break;
					}
					case "transportMode" : {
						updateAttributesQuery.add("TRANSPORT_MODE = :transportMode");
						paramsMap.put("transportMode", tutor.getTransportMode());
						break;
					}
					case "interestedStudentGrades" : {
						updateAttributesQuery.add("INTERESTED_STUDENT_GRADES = :interestedStudentGrades");
						paramsMap.put("interestedStudentGrades", tutor.getInterestedStudentGrades());
						break;
					}
					case "interestedSubjects" : {
						updateAttributesQuery.add("INTERESTED_SUBJECTS = :interestedSubjects");
						paramsMap.put("interestedSubjects", tutor.getInterestedSubjects());
						break;
					}
					case "comfortableLocations" : {
						updateAttributesQuery.add("COMFORTABLE_LOCATIONS = :comfortableLocations");
						paramsMap.put("comfortableLocations", tutor.getComfortableLocations());
						break;
					}
					case "preferredTeachingType" : {
						updateAttributesQuery.add("PREFERRED_TEACHING_TYPE = :preferredTeachingType");
						paramsMap.put("preferredTeachingType", tutor.getPreferredTeachingType());
						break;
					}
					case "teachingExp" : {
						updateAttributesQuery.add("TEACHING_EXP = :teachingExp");
						paramsMap.put("teachingExp", tutor.getTeachingExp());
						break;
					}
					case "additionalDetails" : {
						updateAttributesQuery.add("ADDITIONAL_DETAILS = :additionalDetails");
						paramsMap.put("additionalDetails", tutor.getAdditionalDetails());
						break;
					}
					case "addressDetails" : {
						updateAttributesQuery.add("ADDRESS_DETAILS = :addressDetails");
						paramsMap.put("addressDetails", tutor.getAddressDetails());
						break;
					}
					case "documents" : {
						if (ValidationUtils.checkNonEmptyList(tutor.getDocuments())) {
							uploadTutorDocuments(tutor.getDocuments(), tutor.getTutorSerialId(), true, activeUser);
						}
						break;
					}
				}
			}
		}
		paramsMap.put("tutorSerialId", tutor.getTutorSerialId());
		if (ValidationUtils.checkNonEmptyList(updateAttributesQuery)) {
			updateAttributesQuery.add("RECORD_LAST_UPDATED_MILLIS = :recordLastUpdatedMillis");
			updateAttributesQuery.add("UPDATED_BY = :userId");
			paramsMap.put("recordLastUpdatedMillis", currenTimestamp.getTime());
			paramsMap.put("userId", activeUser.getUserId());
			final String completeQuery = WHITESPACE + baseQuery + WHITESPACE + String.join(COMMA, updateAttributesQuery) + WHITESPACE + existingFilterQueryString;
			applicationDao.executeUpdate(completeQuery, paramsMap);
		}
	}
	
	public RegisteredTutor getRegisteredTutorFromDbUsingUserId(final String userId) throws Exception {
		if (null != userId) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", userId.toLowerCase());
			return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
									+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorUserIdFilter"), paramsMap, new RegisteredTutorRowMapper());
		}
		return null;
	}
	
	public RegisteredTutor getRegisteredTutor(final String tutorSerialId) throws Exception {
		if (ValidationUtils.checkStringAvailability(tutorSerialId)) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorSerialId", tutorSerialId);
			return applicationDao.find(queryMapperService.getQuerySQL("admin-registeredtutor", "selectRegisteredTutor") 
									+ queryMapperService.getQuerySQL("admin-registeredtutor", "registeredTutorTutorIdFilter"), paramsMap, new RegisteredTutorRowMapper());
		}
		return null;
	}
}
