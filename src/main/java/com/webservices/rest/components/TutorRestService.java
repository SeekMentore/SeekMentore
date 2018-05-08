package com.webservices.rest.components;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.poi.util.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.constants.BeanConstants;
import com.constants.FileConstants;
import com.constants.RestMethodConstants;
import com.constants.RestPathConstants;
import com.constants.ScopeConstants;
import com.constants.components.TutorConstants;
import com.model.ErrorPacket;
import com.model.components.RegisteredTutor;
import com.model.components.TutorDocument;
import com.service.components.CommonsService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
import com.utils.FileUtils;
import com.utils.ValidationUtils;
import com.utils.WebServiceUtils;
import com.utils.context.AppContext;
import com.webservices.rest.AbstractRestWebservice;

@Component
@Scope(ScopeConstants.SCOPE_NAME_PROTOTYPE) 
@Path(RestPathConstants.REST_SERVICE_PATH_TUTOR) 
public class TutorRestService extends AbstractRestWebservice implements RestMethodConstants, TutorConstants {
	
	private Long tutorId;
	private String panCardFileName;
	private String photoFileName;
	private String aadhaarCardFileName;
	private String documentType;
	
	@Path(REST_METHOD_NAME_LOAD_TUTOR_RECORD)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String loadTutorRecord (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_LOAD_TUTOR_RECORD;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getTutorService().getTutorRecordWithDocuments(getLoggedInUserTypeObject(request, RegisteredTutor.class).getACopy()), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String getDropdownListDataRegisteredTutor (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getTutorService().getDropdownListData(), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
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
		doSecurity(request);
		if (this.securityPassed) {
			registeredTutorObj.setTutorId(getLoggedInUserTypeObject(request, RegisteredTutor.class).getTutorId());
			return convertObjToJSONString(getTutorService().updateDetails(registeredTutorObj), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes("application/x-www-form-urlencoded")
	@POST
    public void downloadDocument (
    		@FormParam("documentType") final String documentType,
    		@Context final HttpServletRequest request,
    		@Context final HttpServletResponse response
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT;
		this.tutorId = getLoggedInUserTypeObject(request, RegisteredTutor.class).getTutorId();
		this.documentType = documentType;
		doSecurity(request);
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
		this.tutorId = getLoggedInUserTypeObject(request, RegisteredTutor.class).getTutorId();
		this.photoFileName = (null != uploadedFileDetailFilePhoto) ? uploadedFileDetailFilePhoto.getFileName() : null; 
		this.panCardFileName = (null != uploadedFileDetailFilePan) ? uploadedFileDetailFilePan.getFileName() : null; 
		this.aadhaarCardFileName = (null != uploadedFileDetailFileAadhaarCard) ? uploadedFileDetailFileAadhaarCard.getFileName() : null; 
		doSecurity(request);
		if (this.securityPassed) {
			final String folderPathToUploadDocuments = getTutorService().getFolderPathToUploadTutorDocuments(String.valueOf(this.tutorId));
			final Map<String, String> uploadedFiles = new HashMap<String, String>();
			if (null != uploadedInputStreamFilePhoto) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePhoto);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "PROFILE_PHOTO.jpg");
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "PROFILE_PHOTO.jpg", fileBytes);
					uploadedFiles.put("PROFILE_PHOTO.jpg", key);
				}
			}
			if (null != uploadedInputStreamFilePan) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFilePan);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "PAN_CARD.pdf");
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "PAN_CARD.pdf", fileBytes);
					uploadedFiles.put("PAN_CARD.pdf", key);
				}
			}
			if (null != uploadedInputStreamFileAadhaarCard) {
				byte[] fileBytes = IOUtils.toByteArray(uploadedInputStreamFileAadhaarCard);
				if (fileBytes.length > 0) {
					FileSystemUtils.deleteFileInFolderOnApplicationFileSystem(folderPathToUploadDocuments, "AADHAAR_CARD.pdf");
					final String key = FileSystemUtils.createFileInsideFolderOnApplicationFileSystemAndReturnKey(folderPathToUploadDocuments, "AADHAAR_CARD.pdf", fileBytes);
					uploadedFiles.put("AADHAAR_CARD.pdf", key);
				}
			}
			getTutorService().feedDocumentsRecord(tutorId, uploadedFiles);
			WebServiceUtils.redirectToPage("/tutor.html?success=true", request, response);
		} else {
			WebServiceUtils.redirectToPage("/tutor.html?success=false&message="+this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE), request, response);
		}
	}
	
	/*
	 * Admin Functions
	 */
	@Path(REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String registeredTutorsList (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST;
		doSecurity(request);
		if (this.securityPassed) {
			return convertObjToJSONString(getTutorService().registeredTutorsList(LINE_BREAK), REST_MESSAGE_JSON_RESPONSE_NAME);
		} else {
			return convertObjToJSONString(securityFailureResponse, REST_MESSAGE_JSON_RESPONSE_NAME);
		}
	}
	
	@Path(REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT)
	@Produces({MediaType.APPLICATION_JSON})  
	@Consumes("application/x-www-form-urlencoded")
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
		doSecurity(request);
		if (this.securityPassed) {
			final String folderPathToUploadDocuments = getTutorService().getFolderPathToUploadTutorDocuments(String.valueOf(this.tutorId));
			final TutorDocument tutorDocument = getTutorService().downloadDocument(documentType, this.tutorId, folderPathToUploadDocuments);
			FileUtils.writeFileToResponse(response, tutorDocument.getFilename(), FileConstants.APPLICATION_TYPE_OCTET_STEAM, tutorDocument.getContent());
		}
    }
	
	public TutorService getTutorService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_TUTOR_SERVICE, TutorService.class);
	}
	
	public CommonsService getCommonsService() {
		return AppContext.getBean(BeanConstants.BEAN_NAME_COMMONS_SERVICE, CommonsService.class);
	}
	
	@Override
	public void doSecurity(final HttpServletRequest request) {
		this.securityFailureResponse = new HashMap<String, Object>();
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE, EMPTY_STRING);
		switch(this.methodName) {
			case REST_METHOD_NAME_LOAD_TUTOR_RECORD :
			case REST_METHOD_NAME_TO_UPDATE_DETAILS :
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR :
			case REST_METHOD_NAME_DISPLAY_REGISTERED_TUTORS_LIST :
			case REST_METHOD_NAME_DOWNLOAD_ADMIN_TUTOR_DOCUMENT : {
				this.securityPassed = true;
				break;
			}
			case REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT : {
				handleDownloadDocumentsSecurity();
				break;
			}
			case REST_METHOD_NAME_UPLOAD_DOCUMENTS : {
				handleUploadDocumentsSecurity();
				break;
			}
		}
		this.securityFailureResponse.put(RESPONSE_MAP_ATTRIBUTE_FAILURE, !this.securityPassed);
	}
	
	private void handleUploadDocumentsSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_ARRAY_JPG_JPEG.split(COMMA), this.photoFileName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_FILENAME_PHOTOGRAPH,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF.split(COMMA), this.panCardFileName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_FILENAME_PAN,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF.split(COMMA), this.aadhaarCardFileName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_FILENAME_AADHAAR_CARD,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		} 
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(String.valueOf(this.tutorId))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					REST_METHOD_NAME_UPLOAD_DOCUMENTS, 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.photoFileName + LINE_BREAK + this.panCardFileName + LINE_BREAK + this.aadhaarCardFileName + LINE_BREAK + this.tutorId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
	
	private void handleDownloadDocumentsSecurity() {
		this.securityPassed = true;
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(String.valueOf(this.tutorId))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_TUTOR_ID,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validatePlainNotNullAndEmptyTextString(String.valueOf(this.documentType))) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_DOCUMENT_TYPE,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
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
							RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
					this.securityPassed = false;
					break;
				}
			}
		}
		if (!this.securityPassed) {
			final ErrorPacket errorPacket = new ErrorPacket(new Timestamp(new Date().getTime()), 
					REST_METHOD_NAME_DOWNLOAD_TUTOR_DOCUMENT, 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.tutorId + LINE_BREAK + this.documentType);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
