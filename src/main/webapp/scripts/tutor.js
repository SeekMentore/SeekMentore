var tutorObj;
var tutorDocuments;
function uploadDocuments() {
	showLoader();
	var form = document.getElementById('document-upload-form');
	form.action = ctxPath + '/rest/tutor/uploadDocuments';
	form.submit();
}

function resetDocuments() {
	var form = document.getElementById('document-upload-form');
	form.reset();
}

function updateDetails() {
	successMessage = 'Your details were updated successfully.';
	callbackAfterCommonSuccess = loadRecord;
	callWebservice('/rest/tutor/updateDetails', encodeObjectAsJSON(prepareUpdateParams()));
	resetDetails();
}

function resetDetails() {
	var form = document.getElementById('details-update-form');
	form.reset();
}

function showUploadSection() {
	$('#details-div').addClass('noscreen');
	$('#change-password-div').addClass('noscreen');
	$('#documents-div').removeClass('noscreen');
}

function showDetailsSection() {
	$('#change-password-div').addClass('noscreen');
	$('#details-div').removeClass('noscreen');
}

function showChangePassword() {
	$('#documents-div').addClass('noscreen');
	$('#details-div').addClass('noscreen');
	$('#change-password-div').removeClass('noscreen');
}

function showLoadedRecord(response) {
	tutorObj = response.tutorObj;
	if (null != tutorObj) {
		$('#NAME').html(showValue(tutorObj.name));
		$('#DATE_OF_BIRTH').html(showValue(tutorObj.dateOfBirth));
		$('#CONTACT_NUMBER').html(showValue(tutorObj.contactNumber));
		$('#EMAIL_ID').html(showValue(tutorObj.emailId));
		$('#GENDER').html(showValue(tutorObj.gender));
		$('#QUALIFICATION').html(showValue(tutorObj.qualification));
		$('#PRIMARY_PROFESSION').html(showValue(tutorObj.primaryProfession));
		$('#TRANSPORT_MODE').html(showValue(tutorObj.transportMode));
		$('#TEACHING_EXP').html(showValue(tutorObj.teachingExp));
		$('#INTERESTED_STUDENT_GRADES').html(showValue(tutorObj.interestedStudentGrades));
		$('#INTERESTED_SUBJECTS').html(showValue(tutorObj.interestedSubjects));
		$('#COMFORTABLE_LOCATIONS').html(showValue(tutorObj.comfortableLocations));
		$('#PREFERRED_TEACHING_TYPE').html(showValue(tutorObj.preferredTeachingType));
		$('#ADDITIONAL_DETAILS').html(showValue(tutorObj.additionalDetails));
	} 
	resetDocuments();
	tutorDocuments = response.tutorDocuments;
	if (null != tutorDocuments && tutorDocuments.length > 0) {
		for (var i = 0; i < tutorDocuments.length; i++) {
			var filename = tutorDocuments[i].filename;
			var isApproved = tutorDocuments[i].isApproved;
			var rejected = false;
			if (filename == 'PROFILE_PHOTO.jpg') {
				$('#inputFilePhoto').addClass('noscreen');
				$('#inputFilePhoto-link').removeClass('noscreen');
				$('#inputFilePhoto-status').removeClass('noscreen');
				if (null == isApproved) {
					$('#inputFilePhoto-status').html('Pending Approval');
				} else if ('Y' == isApproved) {
					$('#inputFilePhoto-status').html('Approved');
				} else if ('N' == isApproved) {
					$('#inputFilePhoto-status').html('<a href="javascript:void(0);" class="clickable-link-effect" onclick="showRejectedDetails('+i+', \'PROFILE_PHOTO\')">Asked for Re-Upload</a>');
					$('#inputFilePhoto').removeClass('noscreen');
					$('#inputFilePhoto-link').addClass('noscreen');
					rejected = true;
				}
			} else if (filename == 'PAN_CARD.pdf') {
				$('#inputFilePan').addClass('noscreen');
				$('#inputFilePan-link').removeClass('noscreen');
				$('#inputFilePan-status').removeClass('noscreen');
				if (null == isApproved) {
					$('#inputFilePan-status').html('Pending Approval');
				} else if ('Y' == isApproved) {
					$('#inputFilePan-status').html('Approved');
				} else if ('N' == isApproved) {
					$('#inputFilePan-status').html('<a href="javascript:void(0);" class="clickable-link-effect" onclick="showRejectedDetails('+i+', \'PAN_CARD\')">Asked for Re-Upload</a>');
					$('#inputFilePan').removeClass('noscreen');
					$('#inputFilePan-link').addClass('noscreen');
					rejected = true;
				}
			} else if (filename == 'AADHAAR_CARD.pdf') {
				$('#inputFileAadhaarCard').addClass('noscreen');
				$('#inputFileAadhaarCard-link').removeClass('noscreen');
				$('#inputFileAadhaarCard-status').removeClass('noscreen');
				if (null == isApproved) {
					$('#inputFileAadhaarCard-status').html('Pending Approval');
				} else if ('Y' == isApproved) {
					$('#inputFileAadhaarCard-status').html('Approved');
				} else if ('N' == isApproved) {
					$('#inputFileAadhaarCard-status').html('<a href="javascript:void(0);" class="clickable-link-effect" onclick="showRejectedDetails('+i+', \'AADHAAR_CARD\')">Asked for Re-Upload</a>');
					$('#inputFileAadhaarCard').removeClass('noscreen');
					$('#inputFileAadhaarCard-link').addClass('noscreen');
					rejected = true;
				}
			}
		}
		if (!rejected && tutorDocuments.length == 3) {
			$('#upload-action-buttons').addClass('noscreen');
			$('#upload-instructions').addClass('noscreen');
		} else {
			$('#upload-action-buttons').removeClass('noscreen');
			$('#upload-instructions').removeClass('noscreen');
		}
	}
}

function showRejectedDetails(index, documentType) {
	var tutorDocument = tutorDocuments[index];
	var html = tutorDocument.remarks + '<br/><br/>' + '<a href="javascript:void(0)" onclick="downloadRejectionDocument(\''+documentType+'\')" id="rejection-popuplink" class="clickable-link-effect">Download Document</a>';
	showNotificationModalWithModifiedHeader(html, 'Remarks for Re-Upload');
}

function prepareUpdateParams() {
	var form = {
			qualification 				: getAttributeValue('form-qualification', false),
			primaryProfession 			: getAttributeValue('form-primary-profession', false),
			transportMode 				: getAttributeValue('form-transport-mode', false),
			teachingExp 				: getAttributeValue('form-teaching-experience', false),
			interestedStudentGrades 	: getAttributeValue('form-student-grades', true),
			interestedSubjects			: getAttributeValue('form-subjects', true),
			comfortableLocations 		: getAttributeValue('form-comfortable-locations', true),
			preferredTeachingType       : getAttributeValue('form-preferred-teaching-type', true),
			additionalDetails 			: getAttributeValue('form-additional-details', false)
		};
	return form;
}

function loadBecomeTutorDropdowns(response) {
	var qualificationSelectHTML = createSelectOptionOutOfSelectLookupArray(response.qualificationLookUp);
	$('#form-qualification').html($('#form-qualification').html() + qualificationSelectHTML);
	
	var primaryProfessionSelectHTML = createSelectOptionOutOfSelectLookupArray(response.professionLookUp);
	$('#form-primary-profession').html($('#form-primary-profession').html() + primaryProfessionSelectHTML);
	
	var transportModeSelectHTML = createSelectOptionOutOfSelectLookupArray(response.transportModeLookUp);
	$('#form-transport-mode').html($('#form-transport-mode').html() + transportModeSelectHTML);
	
	var studentGradeSelectHTML = createSelectOptionOutOfSelectLookupArray(response.studentGradeLookUp);
	$('#form-student-grades').html($('#form-student-grades').html() + studentGradeSelectHTML);
	
	var subjectsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.subjectsLookUp);
	$('#form-subjects').html($('#form-subjects').html() + subjectsSelectHTML);
	
	var locationsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.locationsLookUp);
	$('#form-comfortable-locations').html($('#form-comfortable-locations').html() + locationsSelectHTML);
	
	var preferredTeachingTypeHTML = createSelectOptionOutOfSelectLookupArray(response.preferredTeachingTypeLookUp);
	$('#form-preferred-teaching-type').html($('#form-preferred-teaching-type').html() + preferredTeachingTypeHTML);
	
	loadRecord();
}

function loadRecord() {
	callWebservice('/rest/tutor/loadTutorRecord', null, showLoadedRecord);
}

function loadDropdowns() {
	callWebservice('/rest/tutor/getDropdownListDataRegisteredTutor', null, loadBecomeTutorDropdowns);
}

function downloadDocument(documentType) {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/tutor/downloadDocument';
	$('#downloadForm-documentType').val(documentType);
	form.submit();
	if (documentType == 'PROFILE_PHOTO') {
		$('#inputFilePhoto-link').addClass('noscreen');
		$('#inputFilePhoto-downloading').removeClass('noscreen');
	} else if (documentType == 'PAN_CARD') {
		$('#inputFilePan-link').addClass('noscreen');
		$('#inputFilePan-downloading').removeClass('noscreen');
	} else if (documentType == 'AADHAAR_CARD') {
		$('#inputFileAadhaarCard-link').addClass('noscreen');
		$('#inputFileAadhaarCard-downloading').removeClass('noscreen');
	}
	window.setTimeout(
			function disableDownloadButton() {
				if (documentType == 'PROFILE_PHOTO') {
					$('#inputFilePhoto-link').removeClass('noscreen');
					$('#inputFilePhoto-downloading').addClass('noscreen');
				} else if (documentType == 'PAN_CARD') {
					$('#inputFilePan-link').removeClass('noscreen');
					$('#inputFilePan-downloading').addClass('noscreen');
				} else if (documentType == 'AADHAAR_CARD') {
					$('#inputFileAadhaarCard-link').removeClass('noscreen');
					$('#inputFileAadhaarCard-downloading').addClass('noscreen');
				}
			}, 8000);
}

function downloadRejectionDocument(documentType) {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/tutor/downloadDocument';
	$('#downloadForm-documentType').val(documentType);
	form.submit();
	$('#rejection-popuplink').html('Downloading...');
	$('#rejection-popuplink').removeClass('clickable-link-effect');
	window.setTimeout(
			function disableDownloadButton() {
				$('#rejection-popuplink').html('Download Document');
				$('#rejection-popuplink').addClass('clickable-link-effect');
			}, 8000);
}

var success = queryParams['success'];
if (null != success) {
	if (success == 'false') {
		showNotificationModal(queryParams['message'], false);
	} else {
		showNotificationModal('Your documents are submitted and will be uploaded in background.<br/>Once our verification team verifies your documents you will receive an email on your registered email account<br/>and your profile will get activated.', true);
	}
}

