var enquiryListMap = new Object();
enquiryListMap.selectedGrid = 'none';

function createEnquiryNavigatorObject(enquiryListResponse) {
	var obj =	{
		currentIndex 	: 0,
		enquiryList		: enquiryListResponse,
		
		getCurrentEnquiryRecord	: function() {
			if (null != this.enquiryList && this.enquiryList.length > 0)
				return this.enquiryList[this.currentIndex];
			else 
				return null;
		},
		
		previousEnquiryRecord		: function() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.enquiryList.length - 1;
			}
			return this.getCurrentEnquiryRecord();
		},
		
		nextEnquiryRecord			: function() {
			if (this.currentIndex < this.enquiryList.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0;
			}
			return this.getCurrentEnquiryRecord();
		},
		
		getParticularEnquiryRecord	: function(recordNumber) {
			if (recordNumber >= 0 && recordNumber < this.enquiryList.length) {
				this.currentIndex = recordNumber;
				return this.getCurrentEnquiryRecord();
			}
			return null;
		},
		
		getList : function() {
			return this.enquiryList;
		},
		
		getRecordCount : function() {
			if (null != this.enquiryList && this.enquiryList.length > 0)
				return this.enquiryList.length;
			else 
				return 0;
		},
		
		getListItem : function(index) {
			if (null != this.enquiryList && this.enquiryList.length > 0)
				return this.enquiryList[index];
			else 
				return null;
		}
	}
	return obj;
}

function showNonContactedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_contacted');
}

function showNonVerifiedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_verified');
}

function showVerifiedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verified');
}

function showVerificationFailedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verification_failed');
}

function showToBeRecontactedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'to_be_recontacted');
}

function showSelectedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'selected');
}

function showRejectedEnquiryRecords(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'rejected');
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, gridName) {
	enquiryListMap[gridName] = createEnquiryNavigatorObject(response);
	var enquiryNavigatorObject = enquiryListMap[gridName];
	var html ='';
	for (var i=0;i < enquiryNavigatorObject.getRecordCount(); i++) {
		var enquiryObj = enquiryNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularEnquiryRecord(\''+gridName+'\', '+i+')">'+showValue(enquiryObj.name)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(enquiryObj.emailId)+'">'+showValue(enquiryObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(enquiryObj.contactNumber)+'">'+showValue(enquiryObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-enquiry-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + enquiryNavigatorObject.getRecordCount());
}

function previousEnquiryRecord() {
	openTutorRecord(enquiryListMap[enquiryListMap.selectedGrid].previousEnquiryRecord());
}

function nextEnquiryRecord() {
	openTutorRecord(enquiryListMap[enquiryListMap.selectedGrid].nextEnquiryRecord());
}

function getParticularEnquiryRecord(gridName, recordNumber) {
	enquiryListMap.selectedGrid = gridName;
	openTutorRecord(enquiryListMap[enquiryListMap.selectedGrid].getParticularEnquiryRecord(recordNumber));
}

function hideEnquiryAllRecordsPage() {
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
	$('#'+enquiryListMap.selectedGrid+'-action-section').removeClass('noscreen');
	var obj = document.getElementById(enquiryListMap.selectedGrid+'-action-section');
	if (null != obj) {
		$('#remarks-div').removeClass('noscreen');
	} else {
		$('#remarks-div').addClass('noscreen');
	}
}

function showEnquiryAllRecordsPage() {
	$('#selected-record-div').addClass('noscreen');
	$('#download-report-div').removeClass('noscreen');
	$('#non_contacted-all-records-div').removeClass('noscreen');
	$('#non_verified-all-records-div').removeClass('noscreen');
	$('#verified-all-records-div').removeClass('noscreen');
	$('#verification_failed-all-records-div').removeClass('noscreen');
	$('#to_be_recontacted-all-records-div').removeClass('noscreen');
	$('#selected-all-records-div').removeClass('noscreen');
	$('#rejected-all-records-div').removeClass('noscreen');
}

function openTutorRecord(tutorObj) {
	hideEnquiryAllRecordsPage();
	if (null != tutorObj) {
		$('#ENQUIRY_DATE').html(showValue(tutorObj.enquiryDate));
		$('#ENQUIRY_STATUS').html(showValue(tutorObj.enquiryStatus));
		$('#NAME').html(showValue(tutorObj.name));
		$('#CONTACT_NUMBER').html('<a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a>');
		$('#EMAIL_ID').html('<a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a>');
		$('#STUDENT_GRADE').html(showValue(tutorObj.studentGrade));
		$('#SUBJECTS').html(showValue(tutorObj.subjects));
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
		$('#PREVIOUS_APPLICATION_DATE').html(showValue(tutorObj.previousApplicationDate));
		$('#RECORD_LAST_UPDATED').html(showValue(tutorObj.recordLastUpdated));
	} 
}

function takeAction(button) {
	var data = { 
			gridName: enquiryListMap.selectedGrid, 
			button : button, 
			enquiryId : enquiryListMap[enquiryListMap.selectedGrid].getCurrentEnquiryRecord().enquiryId,
			remarks : $('#remarks').val()
	}
	successMessage = 'Tutor Enquiry Admin action successfully taken.';
	callbackAfterCommonSuccess = takeActionAfterSuccessCallback;
	callWebservice('/rest/admin/takeActionOnEnquiredTutors', data, null, null, null, 'application/x-www-form-urlencoded');
}

var takeActionAfterSuccessCallback = function(response) {
	$('#remarks').val('');
	showEnquiryAllRecordsPage();
	loadGrids();
}

function loadGrids() {
	callWebservice('/rest/admin/displayNonContactedTutorEnquiries', null, showNonContactedEnquiryRecords);
	callWebservice('/rest/admin/displayNonVerifiedTutorEnquiries', null, showNonVerifiedEnquiryRecords);
	callWebservice('/rest/admin/displayVerifiedTutorEnquiries', null, showVerifiedEnquiryRecords);
	callWebservice('/rest/admin/displayVerificationFailedTutorEnquiries', null, showVerificationFailedEnquiryRecords);
	callWebservice('/rest/admin/displayToBeRecontactedTutorEnquiries', null, showToBeRecontactedEnquiryRecords);
	callWebservice('/rest/admin/displaySelectedTutorEnquiries', null, showSelectedEnquiryRecords);
	callWebservice('/rest/admin/displayRejectedTutorEnquiries', null, showRejectedEnquiryRecords);
}

function downloadReport() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminReportTutorEnquiries';
	form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminTutorEnquiryProfilePdf';
	$('#downloadForm-enquiryId').val(enquiryListMap[enquiryListMap.selectedGrid].getCurrentEnquiryRecord().enquiryId);
	$('#downloadForm-name').val(enquiryListMap[enquiryListMap.selectedGrid].getCurrentEnquiryRecord().name);
	form.submit();
}