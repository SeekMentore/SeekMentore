package com.webservices.rest.components;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
import com.service.components.CommonsService;
import com.service.components.TutorService;
import com.utils.ApplicationUtils;
import com.utils.FileSystemUtils;
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
	
	@Path(REST_METHOD_NAME_LOAD_TUTOR_RECORD)
	@Consumes({MediaType.APPLICATION_JSON})
	@POST
	public String loadTutorRecord (
			@Context final HttpServletRequest request
	) throws Exception {
		this.methodName = REST_METHOD_NAME_LOAD_TUTOR_RECORD;
		doSecurity(request);
		if (this.securityPassed) {
			final RegisteredTutor registeredTutorObj = getLoggedInUserTypeObject(request, RegisteredTutor.class).getACopy();
			getTutorService().loadTutorRecord(registeredTutorObj);
			return convertObjToJSONString(registeredTutorObj, REST_MESSAGE_JSON_RESPONSE_NAME);
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
			case REST_METHOD_NAME_GET_DROPDOWN_LIST_DATA_REGISTERED_TUTOR : {
				this.securityPassed = true;
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
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_JPG, this.photoFileName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_FILENAME_PHOTOGRAPH,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF, this.panCardFileName)) {
			ApplicationUtils.appendMessageInMapAttribute(
					this.securityFailureResponse, 
					VALIDATION_MESSAGE_INVALID_FILENAME_PAN,
					RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE);
			this.securityPassed = false;
		}
		if (!ValidationUtils.validateFileExtension(FileConstants.EXTENSION_PDF, this.aadhaarCardFileName)) {
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
					REST_METHOD_NAME_SEND_EMAIL, 
					this.securityFailureResponse.get(RESPONSE_MAP_ATTRIBUTE_FAILURE_MESSAGE) + LINE_BREAK + this.photoFileName + LINE_BREAK + this.panCardFileName + LINE_BREAK + this.aadhaarCardFileName + LINE_BREAK + this.tutorId);
			getCommonsService().feedErrorRecord(errorPacket);
		}
	}
}
