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
	$('#documents-div').addClass('noscreen');
	$('#change-password-div').addClass('noscreen');
	$('#details-div').removeClass('noscreen');
}

function showChangePassword() {
	$('#documents-div').addClass('noscreen');
	$('#details-div').addClass('noscreen');
	$('#change-password-div').removeClass('noscreen');
}

function showLoadedRecord(response) {
	var tutorObj = response;
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

function getAttributeValue(id, isArray) {
	var element = $('#'+id);
	var value;
	if (null != element) {
		if (isArray) {
			if (element.val().length > 0) {
				value = '';
			}
			for (var i = 0; i < element.val().length; i++) {
				value += element.val()[i];
				if (i != element.val().length - 1) {
					value += ';';
				}
			}
		} else {
			value = element.val().trim();
		}
		if (null != value && value.trim() != '') {
			return value;
		}
		return null;
	} 
	return null;
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

function createSelectOptionOutOfSelectLookupArray(lookupArray) {
	var html = '';
	var previousCategory = null;
	var categoryOpen = false;
	for(var i = 0; i < lookupArray.length; i++) {		
		var selectLookupItem = lookupArray[i];
		var currentCategory = selectLookupItem.category;
		if (previousCategory != currentCategory) {
			if (null != previousCategory) {
				categoryOpen = false;
				html += '</optgroup>';
			}
			if (null != currentCategory) {
				categoryOpen = true;
				html += '<optgroup label="' + currentCategory + '">';
			}
		}
		html += '<option value="' + selectLookupItem.value + '">' + selectLookupItem.label + '</option>';
		previousCategory = currentCategory;
	}
	if (categoryOpen) {
		html += '</optgroup>';
	}
	return html;
}

function loadRecord() {
	callWebservice('/rest/tutor/loadTutorRecord', null, showLoadedRecord);
}

function loadDropdowns() {
	callWebservice('/rest/tutor/getDropdownListDataRegisteredTutor', null, loadBecomeTutorDropdowns);
}

var success = queryParams['success'];
if (null != success) {
	if (success == 'false') {
		showNotificationModal(queryParams['message'], false);
	} else {
		showNotificationModal('Your documents are submitted and will be uploaded in background.<br/>Once our verification team verifies your documents you will receive an email on your registered email account<br/>and your profile will get activated.', true);
	}
}

