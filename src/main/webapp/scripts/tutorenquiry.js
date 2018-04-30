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
	openEnquiryRecord(enquiryListMap[enquiryListMap.selectedGrid].previousEnquiryRecord());
}

function nextEnquiryRecord() {
	openEnquiryRecord(enquiryListMap[enquiryListMap.selectedGrid].nextEnquiryRecord());
}

function getParticularEnquiryRecord(gridName, recordNumber) {
	enquiryListMap.selectedGrid = gridName;
	openEnquiryRecord(enquiryListMap[enquiryListMap.selectedGrid].getParticularEnquiryRecord(recordNumber));
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

function openEnquiryRecord(enquiryObj) {
	hideEnquiryAllRecordsPage();
	if (null != enquiryObj) {
		$('#ENQUIRY_DATE').html(showValue(enquiryObj.enquiryDate));
		$('#ENQUIRY_STATUS').html(showValue(enquiryObj.enquiryStatus));
		$('#NAME').html(showValue(enquiryObj.name));
		$('#CONTACT_NUMBER').html('<a href="tel:'+showValue(enquiryObj.contactNumber)+'">'+showValue(enquiryObj.contactNumber)+'</a>');
		$('#EMAIL_ID').html('<a href="mailto:'+showValue(enquiryObj.emailId)+'">'+showValue(enquiryObj.emailId)+'</a>');
		$('#STUDENT_GRADE').html(showValue(enquiryObj.studentGrade));
		$('#SUBJECTS').html(showValue(enquiryObj.subjects));
		$('#PREFERRED_TIME_TO_CALL').html(showValue(enquiryObj.preferredTimeToCall));
		$('#ADDITIONAL_DETAILS').html(showValue(enquiryObj.additionalDetails));
		$('#IS_CONTACTED').html(showValue(enquiryObj.isContacted));
		$('#WHO_CONTACTED').html(showValue(enquiryObj.whoContacted));
		$('#CONTACTED_DATE').html(showValue(enquiryObj.contactedDate));
		$('#CONTACTED_REMARKS').html(showValue(enquiryObj.contactedRemarks));
		$('#IS_AUTHENTICATION_VERIFIED').html(showValue(enquiryObj.isAuthenticationVerified));
		$('#WHO_VERIFIED').html(showValue(enquiryObj.whoVerified));
		$('#VERIFICATION_DATE').html(showValue(enquiryObj.verificationDate));
		$('#VERIFICATION_REMARKS').html(showValue(enquiryObj.verificationRemarks));
		$('#IS_TO_BE_RECONTACTED').html(showValue(enquiryObj.isToBeRecontacted));
		$('#WHO_SUGGESTED_FOR_RECONTACT').html(showValue(enquiryObj.whoSuggestedForRecontact));
		$('#SUGGESTION_DATE').html(showValue(enquiryObj.suggestionDate));
		$('#SUGGESTION_REMARKS').html(showValue(enquiryObj.suggestionRemarks));
		$('#WHO_RECONTACTED').html(showValue(enquiryObj.whoRecontacted));
		$('#RECONTACTED_DATE').html(showValue(enquiryObj.recontactedDate));
		$('#RECONTACTED_REMARKS').html(showValue(enquiryObj.recontactedRemarks));
		$('#IS_SELECTED').html(showValue(enquiryObj.isSelected));
		$('#WHO_SELECTED').html(showValue(enquiryObj.whoSelected));
		$('#SELECTION_DATE').html(showValue(enquiryObj.selectionDate));
		$('#SELECTION_REMARKS').html(showValue(enquiryObj.selectionRemarks));
		$('#IS_REJECTED').html(showValue(enquiryObj.isRejected));
		$('#WHO_REJECTED').html(showValue(enquiryObj.whoRejected));
		$('#REJECTION_DATE').html(showValue(enquiryObj.rejectionDate));
		$('#REJECTION_REMARKS').html(showValue(enquiryObj.rejectionRemarks));
		$('#PREVIOUS_APPLICATION_DATE').html(showValue(enquiryObj.previousApplicationDate));
		$('#RECORD_LAST_UPDATED').html(showValue(enquiryObj.recordLastUpdated));
		
		replacePlaceHoldersForEmailPanel(showValue(enquiryObj.emailId), showValue(enquiryObj.name));
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