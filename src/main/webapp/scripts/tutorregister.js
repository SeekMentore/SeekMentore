var tutorListMap = new Object();
tutorListMap.selectedGrid = 'none';

function createTutorNavigatorObject(tutorListResponse) {
	var obj =	{
		currentIndex 	: 0,
		tutorList		: tutorListResponse,
		
		getCurrentTutorRecord	: function() {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList[this.currentIndex];
			else 
				return null;
		},
		
		previousTutorRecord		: function() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.tutorList.length - 1;
			}
			return this.getCurrentTutorRecord();
		},
		
		nextTutorRecord			: function() {
			if (this.currentIndex < this.tutorList.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0;
			}
			return this.getCurrentTutorRecord();
		},
		
		getParticularTutorRecord	: function(recordNumber) {
			if (recordNumber >= 0 && recordNumber < this.tutorList.length) {
				this.currentIndex = recordNumber;
				return this.getCurrentTutorRecord();
			}
			return null;
		},
		
		getList : function() {
			return this.tutorList;
		},
		
		getRecordCount : function() {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList.length;
			else 
				return 0;
		},
		
		getListItem : function(index) {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList[index];
			else 
				return null;
		}
	}
	return obj;
}

function showNonContactedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_contacted');
}

function showNonVerifiedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_verified');
}

function showVerifiedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verified');
}

function showVerificationFailedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verification_failed');
}

function showToBeRecontactedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'to_be_recontacted');
}

function showSelectedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'selected');
}

function showRejectedTutorRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'rejected');
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, gridName) {
	tutorListMap[gridName] = createTutorNavigatorObject(response);
	var tutorNavigatorObject = tutorListMap[gridName];
	var html ='';
	for (var i=0;i < tutorNavigatorObject.getRecordCount(); i++) {
		var tutorObj = tutorNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorRecord(\''+gridName+'\', '+i+')">'+showValue(tutorObj.firstName)+' '+showValue(tutorObj.lastName)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-tutor-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + tutorNavigatorObject.getRecordCount());
}

function previousTutorRecord() {
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].previousTutorRecord());
}

function nextTutorRecord() {
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].nextTutorRecord());
}

function getParticularTutorRecord(gridName, recordNumber) {
	tutorListMap.selectedGrid = gridName;
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].getParticularTutorRecord(recordNumber));
}

function hideTutorAllRecordsPage() {
	$('#selected-record-div').removeClass('noscreen');
	$('.action-section').addClass('noscreen');
	$('#download-report-div').addClass('noscreen');
	$('#non_contacted-all-records-div').addClass('noscreen');
	$('#non_verified-all-records-div').addClass('noscreen');
	$('#verified-all-records-div').addClass('noscreen');
	$('#verification_failed-all-records-div').addClass('noscreen');
	$('#to_be_recontacted-all-records-div').addClass('noscreen');
	$('#selected-all-records-div').addClass('noscreen');
	$('#rejected-all-records-div').addClass('noscreen');
	$('#header-div').addClass('noscreen');
	$('#'+tutorListMap.selectedGrid+'-action-section').removeClass('noscreen');
	var obj = document.getElementById(tutorListMap.selectedGrid+'-action-section');
	if (null != obj) {
		$('#remarks-div').removeClass('noscreen');
	} else {
		$('#remarks-div').addClass('noscreen');
	}
}

function showTutorAllRecordsPage() {
	$('#selected-record-div').addClass('noscreen');
	$('#download-report-div').removeClass('noscreen');
	$('#non_contacted-all-records-div').removeClass('noscreen');
	$('#non_verified-all-records-div').removeClass('noscreen');
	$('#verified-all-records-div').removeClass('noscreen');
	$('#verification_failed-all-records-div').removeClass('noscreen');
	$('#to_be_recontacted-all-records-div').removeClass('noscreen');
	$('#selected-all-records-div').removeClass('noscreen');
	$('#rejected-all-records-div').removeClass('noscreen');
	$('#header-div').removeClass('noscreen');
}

function openTutorRecord(tutorObj) {
	hideTutorAllRecordsPage();
	if (null != tutorObj) {
		$('#APPLICATION_DATE').html(showValue(tutorObj.applicationDate));
		$('#APPLICATION_STATUS').html(showValue(tutorObj.applicationStatus));
		$('#DATE_OF_BIRTH').html(showValue(tutorObj.dateOfBirth));
		$('#CONTACT_NUMBER').html('<a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a>');
		$('#EMAIL_ID').html('<a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a>');
		$('#FIRST_NAME').html(showValue(tutorObj.firstName));
		$('#LAST_NAME').html(showValue(tutorObj.lastName));
		$('#GENDER').html(showValue(tutorObj.gender));
		$('#QUALIFICATION').html(showValue(tutorObj.qualification));
		$('#PRIMARY_PROFESSION').html(showValue(tutorObj.primaryProfession));
		$('#TRANSPORT_MODE').html(showValue(tutorObj.transportMode));
		$('#TEACHING_EXP').html(showValue(tutorObj.teachingExp));
		$('#STUDENT_GRADE').html(showValue(tutorObj.studentGrade));
		$('#SUBJECTS').html(showValue(tutorObj.subjects));
		$('#LOCATIONS').html(showValue(tutorObj.locations));
		$('#PREFERRED_TIME_TO_CALL').html(showValue(tutorObj.preferredTimeToCall));
		$('#ADDITIONAL_DETAILS').html(showValue(tutorObj.additionalDetails));
		$('#IS_CONTACTED').html(showValue(tutorObj.isContacted));
		$('#WHO_CONTACTED').html(showValue(tutorObj.whoContacted));
		$('#CONTACTED_DATE').html(showValue(tutorObj.contactedDate));
		$('#CONTACTED_REMARKS').html(showValue(tutorObj.contactedRemarks));
		$('#IS_AUTHENTICATION_VERIFIED').html(showValue(tutorObj.isAuthenticationVerified));
		$('#WHO_VERIFIED').html(showValue(tutorObj.whoVerified));
		$('#VERIFICATION_DATE').html(showValue(tutorObj.verificationDate));
		$('#VERIFICATION_REMARKS').html(showValue(tutorObj.verificationRemarks));
		$('#IS_TO_BE_RECONTACTED').html(showValue(tutorObj.isToBeRecontacted));
		$('#WHO_SUGGESTED_FOR_RECONTACT').html(showValue(tutorObj.whoSuggestedForRecontact));
		$('#SUGGESTION_DATE').html(showValue(tutorObj.suggestionDate));
		$('#SUGGESTION_REMARKS').html(showValue(tutorObj.suggestionRemarks));
		$('#WHO_RECONTACTED').html(showValue(tutorObj.whoRecontacted));
		$('#RECONTACTED_DATE').html(showValue(tutorObj.recontactedDate));
		$('#RECONTACTED_REMARKS').html(showValue(tutorObj.recontactedRemarks));
		$('#IS_SELECTED').html(showValue(tutorObj.isSelected));
		$('#WHO_SELECTED').html(showValue(tutorObj.whoSelected));
		$('#SELECTION_DATE').html(showValue(tutorObj.selectionDate));
		$('#SELECTION_REMARKS').html(showValue(tutorObj.selectionRemarks));
		$('#IS_REJECTED').html(showValue(tutorObj.isRejected));
		$('#WHO_REJECTED').html(showValue(tutorObj.whoRejected));
		$('#REJECTION_DATE').html(showValue(tutorObj.rejectionDate));
		$('#REJECTION_REMARKS').html(showValue(tutorObj.rejectionRemarks));
		$('#REJECTION_COUNT').html(showValue(tutorObj.rejectionCount));
		$('#RE_APPLIED').html(showValue(tutorObj.reApplied));
		$('#PREVIOUS_APPLICATION_DATE').html(showValue(tutorObj.previousApplicationDate));
		$('#RECORD_LAST_UPDATED').html(showValue(tutorObj.recordLastUpdated));
		
		replacePlaceHoldersForEmailPanel(showValue(tutorObj.emailId), showValue(tutorObj.firstName)+' '+showValue(tutorObj.lastName));
	} 
}

function takeAction(button) {
	var data = { 
			gridName: tutorListMap.selectedGrid, 
			button : button, 
			tentativeTutorId : tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().tentativeTutorId,
			remarks : $('#remarks').val()
	}
	successMessage = 'Tutor Registration Admin action successfully taken.';
	callbackAfterCommonSuccess = takeActionAfterSuccessCallback;
	callWebservice('/rest/admin/takeActionOnRegisteredTutors', data, null, null, null, 'application/x-www-form-urlencoded');
}

var takeActionAfterSuccessCallback = function(response) {
	$('#remarks').val('');
	showTutorAllRecordsPage();
	loadGrids();
}

function loadGrids() {
	callWebservice('/rest/admin/displayNonContactedTutorRegistrations', null, showNonContactedTutorRecords);
	callWebservice('/rest/admin/displayNonVerifiedTutorRegistrations', null, showNonVerifiedTutorRecords);
	callWebservice('/rest/admin/displayVerifiedTutorRegistrations', null, showVerifiedTutorRecords);
	callWebservice('/rest/admin/displayVerificationFailedTutorRegistrations', null, showVerificationFailedTutorRecords);
	callWebservice('/rest/admin/displayToBeRecontactedTutorRegistrations', null, showToBeRecontactedTutorRecords);
	callWebservice('/rest/admin/displaySelectedTutorRegistrations', null, showSelectedTutorRecords);
	callWebservice('/rest/admin/displayRejectedTutorRegistrations', null, showRejectedTutorRecords);
}

function downloadReport() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminReportTutorRegistrations';
	form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminTutorRegistrationProfilePdf';
	$('#downloadForm-tentativeTutorId').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().tentativeTutorId);
	$('#downloadForm-name').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().firstName + '_' + tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().lastName);
	form.submit();
}