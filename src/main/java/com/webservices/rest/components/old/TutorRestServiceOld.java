package com.webservices.rest.components.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.TutorConstants;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_TUTOR+"old") 
public class TutorRestServiceOld extends AbstractRestWebservice implements RestMethodConstants, TutorConstants {

	@Override
	protected void doSecurity(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}/*
	
	private Long tutorId;
	private String panCardFileName;
	private String photoFileName;
	private String aadhaarCardFileName;
	private String documentType;
	private String remarks;
	private String name;
	
	@Path(REST_METHOD_NAME_LOAD_TUTOR_RECORD)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String loadTutorRecord (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_LOAD_TUTOR_RECORD;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().getTutorRecordWithDocuments(((RegisteredTutor)getActiveUser(request)).clone()), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataRegisteredTutor (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().getDropdownListData(), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_TO_UPDATE_DETAILS)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String updateDetails (
			final RegisteredTutor registeredTutorObj,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_TO_UPDATE_DETAILS;
		doSecurity(request, response);
		if (this.securityPassed) {
			registeredTutorObj.setTutorId(((RegisteredTutor)getActiveUser(request)).getTutorId());
			return JSONUtils.convertObjToJSONString(getTutorService().updateDetails(registeredTutorObj), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadDocument (
    		@FormParam("documentType") final String documentType,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT;
		this.tutorId = ((RegisteredTutor)getActiveUser(request)).getTutorId();
		this.documentType = documentType;
		doSecurity(request, response);
		if (this.securityPassed) {
			final String folderPathToUploadDocuments = getTutorService().getFolderPathToUploadTutorDocuments(String.valueOf(this.tutorId));
			final TutorDocument tutorDocument = getTutorService().downloadDocument(documentType, this.tutorId, folderPathToUploadDocuments);
			FileUtils.writeFileToResponse(response, tutorDocument.getFilename(), FileConstants.APPLICATION_TYPE_OCTET_STEAM, tutorDocument.getContent());
		}
    }
	
	@Path(REST_METHOD_NAME_UPLOAD_DOCUMENTS)
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@POST
	public void uploadDocuments (
			@FormDataParam("inputFilePhoto") final InputStream uploadedInputStreamFilePhoto,
			@FormDataParam("inputFilePhoto") final FormDataContentDisposition uploadedFileDetailFilePhoto,
			@FormDataParam("inputFilePan") final InputStream uploadedInputStreamFilePan,
			@FormDataParam("inputFilePan") final FormDataContentDisposition uploadedFileDetailFilePan,
			@FormDataParam("inputFileAadhaarCard") final InputStream uploadedInputStreamFileAadhaarCard,
			@FormDataParam("inputFileAadhaarCard") final FormDataContentDisposition uploadedFileDetailFileAadhaarCard,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_UPLOAD_DOCUMENTS;
		this.tutorId = ((RegisteredTutor)getActiveUser(request)).getTutorId();
		this.photoFileName = (null != uploadedFileDetailFilePhoto) ? uploadedFileDetailFilePhoto.getFileName() : null; 
		this.panCardFileName = (null != uploadedFileDetailFilePan) ? uploadedFileDetailFilePan.getFileName() : null; 
		this.aadhaarCardFileName = (null != uploadedFileDetailFileAadhaarCard) ? uploadedFileDetailFileAadhaarCard.getFileName() : null; 
		doSecurity(request, response);
		if (this.securityPassed) {
			final String folderPathToUploadDocuments = getTutorService().getFolderPathToUploadTutorDocuments(String.valueOf(this.tutorId));
			final Map<String, String> uploadedFiles = new HashMap<String, String>();
			if (null != uploadedInputStreamFilePhoto) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePhoto);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "PROFILE_PHOTO.jpg", getActiveUser(request));
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "PROFILE_PHOTO.jpg", fileBytes);
					uploadedFiles.put("PROFILE_PHOTO.jpg", key);
				}
			}
			if (null != uploadedInputStreamFilePan) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePan);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "PAN_CARD.pdf", getActiveUser(request));
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "PAN_CARD.pdf", fileBytes);
					uploadedFiles.put("PAN_CARD.pdf", key);
				}
			}
			if (null != uploadedInputStreamFileAadhaarCard) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFileAadhaarCard);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "AADHAAR_CARD.pdf", getActiveUser(request));
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "AADHAAR_CARD.pdf", fileBytes);
					uploadedFiles.put("AADHAAR_CARD.pdf", key);
				}
			}
			getTutorService().feedDocumentsRecord(tutorId, uploadedFiles);
			WebServiceUtils.redirectToPage("/tutor.html?success=true", request, response);
		} else {
			WebServiceUtils.redirectToPage("/tutor.html?success=false&message="+this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE), request, response);
		}
	}
	
	
	 * Admin Functions
	 
	@Path(REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String registeredTutorsList (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().registeredTutorsList(LINE_BREAK), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} else {
			return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadDocumentFromAdmin (
    		@FormParam("tutorId") final Long tutorId,
    		@FormParam("documentType") final String documentType,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT;
		this.tutorId = tutorId;
		this.documentType = documentType;
		doSecurity(request, response);
		if (this.securityPassed) {
			final String folderPathToUploadDocuments = getTutorService().getFolderPathToUploadTutorDocuments(String.valueOf(this.tutorId));
			final TutorDocument tutorDocument = getTutorService().downloadDocument(documentType, this.tutorId, folderPathToUploadDocuments);
			FileUtils.writeFileToResponse(response, tutorDocument.getFilename(), FileConstants.APPLICATION_TYPE_OCTET_STEAM, tutorDocument.getContent());
		}
    }
	
	@Path(REST_METHOD_NAME_APRROVE_DOCUMENT_FROM_ADMIN)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String aprroveDocumentFromAdmin (
			@FormParam("tutorId") final Long tutorId,
			@FormParam("documentType") final String documentType,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_APRROVE_DOCUMENT_FROM_ADMIN;
		this.tutorId = tutorId;
		this.documentType = documentType;
		this.remarks = remarks;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().aprroveDocumentFromAdmin(tutorId, documentType, getActiveUserId(request), remarks), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_REJECT_DOCUMENT_FROM_ADMIN)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String rejectDocumentFromAdmin (
			@FormParam("tutorId") final Long tutorId,
			@FormParam("documentType") final String documentType,
			@FormParam("remarks") final String remarks,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_REJECT_DOCUMENT_FROM_ADMIN;
		this.tutorId = tutorId;
		this.documentType = documentType;
		this.remarks = remarks;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().rejectDocumentFromAdmin(tutorId, documentType, getActiveUserId(request), remarks), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_DOCUMENT_REMINDER_FROM_ADMIN)
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
	public String sendDocumentReminderEmail (
			@FormParam("tutorId") final Long tutorId,
			@FormParam("documentType") final String documentType,
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOCUMENT_REMINDER_FROM_ADMIN;
		this.tutorId = tutorId;
		this.documentType = documentType;
		doSecurity(request, response);
		if (this.securityPassed) {
			return JSONUtils.convertObjToJSONString(getTutorService().sendDocumentReminderEmailToTutor(tutorId, documentType), RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
		} 
		return JSONUtils.convertObjToJSONString(this.securityFailureResponse, RESPONSE_MAP_ATTRIBUTE_RESPONSE_NAME);
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_REGISTERED_TUTORS)
	@Produces({MediaType.APPLICATION_JSON})  
	@POST
    public void downloadAdminReportRegisteredTutors (
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_REGISTERED_TUTORS;
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Registered_Tutor_Report" + PERIOD + FileConstants.EXTENSION_XLSX, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getTutorService().downloadAdminReportRegisteredTutors());
		}
    }
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_REGISTERED_TUTOR_PROFILE_PDF)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes(APPLICATION_X_WWW_FORM_URLENCODED)
	@POST
    public void downloadAdminIndividualRegisteredTutorProfilePdf (
    		@FormParam("tutorId") final Long tutorId,
    		@FormParam("name") final String name,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_REGISTERED_TUTOR_PROFILE_PDF;
		this.tutorId = tutorId;
		this.name = name;
		doSecurity(request, response);
		if (this.securityPassed) {
			FileUtils.writeFileToResponse(response, "Admin_Registered_Tutor_PDF_For_" + name + PERIOD + FileConstants.EXTENSION_PDF, FileConstants.APPLICATION_TYPE_OCTET_STEAM, getTutorService().downloadAdminIndividualRegisteredTutorProfilePdf(tutorId));
		}
    }
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) throws Exception {
		this.request = request; this.response = response;
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_REPORT_REGISTERED_TUTORS :
			case REST_METHOD_NAME_LOAD_TUTOR_RECORD :
			case REST_METHOD_NAME_TO_UPDATE_DETAILS :
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR :
			case REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_INDIVIDUAL_REGISTERED_TUTOR_PROFILE_PDF : {
				handleAdminPDFDownloadSecurity();
				break;
			}
			case REST_METHOD_NAME_DOCUMENT_REMINDER_FROM_ADMIN :
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT :
			case REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT : {
				handleDownloadDocumentsSecurity();
				break;
			}
			case REST_METHOD_NAME_UPLOAD_DOCUMENTS : {
				handleUploadDocumentsSecurity();
				break;
			}
			case REST_METHOD_NAME_APRROVE_DOCUMENT_FROM_ADMIN : 
			case REST_METHOD_NAME_REJECT_DOCUMENT_FROM_ADMIN : {
				handleAdminIndividualTutorObjectAccessSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_SUCCESS, this.securityPassed);
	}
	
	private void handleUploadDocumentsSecurity() throws Exception {
		this.securityPassed = true;
		Integer counter = 0;
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(this.photoFileName)) {
			if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_JPEG.split(COMMA), this.photoFileName)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_INVALID_FILENAME_PHOTOGRAPH,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
			counter++;
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(this.panCardFileName)) {
			if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF.split(COMMA), this.panCardFileName)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_INVALID_FILENAME_PAN,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
			counter++;
		}
		if (ValidationUtils.validatePlainNotNullAndEmptyTextString(this.aadhaarCardFileName)) {
			if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF.split(COMMA), this.aadhaarCardFileName)) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_INVALID_FILENAME_AADHAAR_CARD,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
			counter++;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (this.securityPassed) {
			if (counter == 0) {
				ApplicationUtils.appendMessageInMapAttribute(
						this.securityFailureResponse, 
						VALIDATION_MESSAGE_NO_FILES_UPLOADED,
						RESPONSE_MAP_ATTRIBUTE_MESSAGE);
				this.securityPassed = false;
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.photoFileName + LINE_BREAK + this.panCardFileName + LINE_BREAK + this.aadhaarCardFileName + LINE_BREAK + this.tutorId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleAdminIndividualTutorObjectAccessSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.remarks)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_REMARKS,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.documentType)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(documentType) {
				case "PROFILE_PHOTO": break;
				case "PAN_CARD": break;
				case "AADHAAR_CARD": break;
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.tutorId + LINE_BREAK + this.remarks + LINE_BREAK + this.documentType);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleAdminPDFDownloadSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.name)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_NAME,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.tutorId + LINE_BREAK + this.name);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleDownloadDocumentsSecurity() throws Exception {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.tutorId)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(this.documentType)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE,
					RESPONSE_MAP_ATTRIBUTE_MESSAGE);
			this.securityPassed = false;
		} else {
			switch(documentType) {
				case "PROFILE_PHOTO": break;
				case "PAN_CARD": break;
				case "AADHAAR_CARD": break;
				default : {
					ApplicationUtils.appendMessageInMapAttribute(
							this.securityFailureResponse, 
							VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE,
							RESPONSE_MAP_ATTRIBUTE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(
					this.methodName + LINE_BREAK + getActiveUserIdAndTypeForPrinting(request), 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_MESSAGE) + LINE_BREAK + this.tutorId + LINE_BREAK + this.documentType);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
*/}
