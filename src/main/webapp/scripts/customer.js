/**
 * 
 */
var subscribedCustomerObj;
function updateDetails() {
	successMessage = 'Your details were updated successfully.';
	callbackAfterCommonSuccess = loadRecord;
	callWebservice('/rest/customer/updateSubscribedCustomerDetails', encodeObjectAsJSON(prepareUpdateParams()));
	resetDetails();
}

function resetDetails() {
	var form = document.getElementById('details-update-form');
	form.reset();
}

function showChangePassword() {
	$('#documents-div').addClass('noscreen');
	$('#details-div').addClass('noscreen');
	$('#change-password-div').removeClass('noscreen');
}

function showLoadedRecord(response) {
	subscribedCustomerObj = response.subscribedCustomerObj;
	if (null != subscribedCustomerObj) {
		$('#NAME').html(showValue(subscribedCustomerObj.name));
		$('#CONTACT_NUMBER').html(showValue(subscribedCustomerObj.contactNumber));
		$('#EMAIL_ID').html(showValue(subscribedCustomerObj.emailId));
		$('#INTERESTED_STUDENT_GRADES').html(showValue(subscribedCustomerObj.studentGrades));
		$('#INTERESTED_SUBJECTS').html(showValue(subscribedCustomerObj.interestedSubjects));
		$('#LOCATIONS').html(showValue(subscribedCustomerObj.location));
		$('#ADDRESS_DETAILS').html(showValue(subscribedCustomerObj.addressDetails));
		$('#ADDITIONAL_DETAILS').html(showValue(subscribedCustomerObj.additionalDetails));
	} 
  }

function prepareUpdateParams() {
	var form = {
			studentGrades           	: getAttributeValue('form-student-grades', true),
			interestedSubjects			: getAttributeValue('form-subjects', true),
			location 	            	: getAttributeValue('form-comfortable-locations', true)
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

function loadSubscribedDropdowns(response) {
	var studentGradeSelectHTML = createSelectOptionOutOfSelectLookupArray(response.studentGradeLookUp);
	$('#form-student-grades').html($('#form-student-grades').html() + studentGradeSelectHTML);
	
	var subjectsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.subjectsLookUp);
	$('#form-subjects').html($('#form-subjects').html() + subjectsSelectHTML);
	
	var locationsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.locationsLookUp);
	$('#form-comfortable-locations').html($('#form-comfortable-locations').html() + locationsSelectHTML);	
	
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
	callWebservice('/rest/customer/loadSubscribedCustomerRecord', null, showLoadedRecord);
}

function loadDropdowns() {
	callWebservice('/rest/customer/getDropdownListDataSubscribedCustomer', null, loadSubscribedDropdowns);
}



var success = queryParams['success'];
if (null != success) {
	if (success == 'false') {
		showNotificationModal(queryParams['message'], false);
	} else {
		showNotificationModal('Your documents are submitted and will be uploaded in background.<br/>Once our verification team verifies your documents you will receive an email on your registered email account<br/>and your profile will get activated.', true);
	}
}

