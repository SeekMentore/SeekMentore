/**
 * 
 */
var subscriptionListMap = new Object();
subscriptionListMap.selectedGrid = 'none';
function loadGrids() {
	callWebservice('/rest/admin/displayNonContactedSubscriptions', null, showNonContactedSubscriptions);
	callWebservice('/rest/admin/displayNonVerifiedSubscriptions', null, showNonVerifiedSubscriptions);
	callWebservice('/rest/admin/displayVerifiedSubscriptions', null, showVerifiedSubscriptions);
	callWebservice('/rest/admin/displayVerificationFailedSubscriptions', null, showVerificationFailedSubscriptions);
	callWebservice('/rest/admin/displayToBeRecontactedSubscriptions', null, showToBeRecontactedSubscriptions);
	callWebservice('/rest/admin/displaySelectedSubscriptions', null, showSelectedSubscriptions);
	callWebservice('/rest/admin/displayRejectedSubscriptions', null, showRejectedSubscriptions); 
}

function showNonContactedSubscriptions(response){
    prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_contacted');
}

function showNonVerifiedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'non_verified');
}

function showVerifiedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verified');
}

function showVerificationFailedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'verification_failed');
}

function showToBeRecontactedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'to_be_recontacted');
}

function showSelectedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'selected');
}

function showRejectedSubscriptions(response){
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'rejected');
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, gridName) {
	subscriptionListMap[gridName] = createNavigatorObject(response);
	var subscriptionNavigatorObject = subscriptionListMap[gridName];
	var html ='';
	for (var i=0;i < subscriptionNavigatorObject.getRecordCount(); i++) {
		var subscriptionObj = subscriptionNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularSubscribedRecord(\''+gridName+'\', '+i+')">'+showValue(subscriptionObj.firstName)+' '+showValue(subscriptionObj.lastName)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(subscriptionObj.emailId)+'">'+showValue(subscriptionObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(subscriptionObj.contactNumber)+'">'+showValue(subscriptionObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-subscriptions-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + subscriptionNavigatorObject.getRecordCount());
}

function previousSubscribedRecord() {
	openSubscribedRecord(subscriptionListMap[subscriptionListMap.selectedGrid].previousRecord());
}

function nextSubscribedRecord() {
	openSubscribedRecord(subscriptionListMap[subscriptionListMap.selectedGrid].nextRecord());
}

function getParticularSubscribedRecord(gridName, recordNumber) {
	subscriptionListMap.selectedGrid = gridName;
	openSubscribedRecord(subscriptionListMap[subscriptionListMap.selectedGrid].getParticularRecord(recordNumber));
}

function hideSubscriptionAllRecordsPage() {
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
	$('#'+subscriptionListMap.selectedGrid+'-action-section').removeClass('noscreen');
	var obj = document.getElementById(subscriptionListMap.selectedGrid+'-action-section');
	if (null != obj) {
		$('#remarks-div').removeClass('noscreen');
	} else {
		$('#remarks-div').addClass('noscreen');
	}
}

function showSubscriptionAllRecordsPage() {
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

function openSubscribedRecord(subscriptionObj) {
	hideSubscribedAllRecordsPage();
	if (null != subscriptionObj) {
		$('#APPLICATION_DATE').html(showValue(subscriptionObj.applicationDate));
		$('#APPLICATION_STATUS').html(showValue(subscriptionObj.applicationStatus));
		$('#CONTACT_NUMBER').html('<a href="tel:'+showValue(subscriptionObj.contactNumber)+'">'+showValue(subscriptionObj.contactNumber)+'</a>');
		$('#EMAIL_ID').html('<a href="mailto:'+showValue(subscriptionObj.emailId)+'">'+showValue(subscriptionObj.emailId)+'</a>');
		$('#FIRST_NAME').html(showValue(subscriptionObj.firstName));
		$('#LAST_NAME').html(showValue(subscriptionObj.lastName));
		$('#STUDENT_GRADE').html(showValue(subscriptionObj.studentGrade));
		$('#SUBJECTS').html(showValue(subscriptionObj.subjects));
		$('#PREFERRED_TIME_TO_CALL').html(showValue(subscriptionObj.preferredTimeToCall));
		$('#LOCATION').html(showValue(subscriptionObj.location));
		$('#REFERENCE').html(showValue(subscriptionObj.reference));
		$('#ADDRESS_DETAILS').html(showValue(subscriptionObj.addressDetails));
		$('#ADDITIONAL_DETAILS').html(showValue(subscriptionObj.additionalDetails));
		$('#SUBSCRIBED_CUSTOMER').html(showValue(subscriptionObj.subscribedCustomer));
		$('#IS_CONTACTED').html(showValue(subscriptionObj.isContacted));
		$('#WHO_CONTACTED').html(showValue(subscriptionObj.whoContacted));
		$('#CONTACTED_DATE').html(showValue(subscriptionObj.contactedDate));
		$('#CONTACTED_REMARKS').html(showValue(subscriptionObj.contactedRemarks));
		$('#IS_AUTHENTICATION_VERIFIED').html(showValue(subscriptionObj.isAuthenticationVerified));
		$('#WHO_VERIFIED').html(showValue(subscriptionObj.whoVerified));
		$('#VERIFICATION_DATE').html(showValue(subscriptionObj.verificationDate));
		$('#VERIFICATION_REMARKS').html(showValue(subscriptionObj.verificationRemarks));
		$('#IS_TO_BE_RECONTACTED').html(showValue(subscriptionObj.isToBeRecontacted));
		$('#WHO_SUGGESTED_FOR_RECONTACT').html(showValue(subscriptionObj.whoSuggestedForRecontact));
		$('#SUGGESTION_DATE').html(showValue(subscriptionObj.suggestionDate));
		$('#SUGGESTION_REMARKS').html(showValue(subscriptionObj.suggestionRemarks));
		$('#WHO_RECONTACTED').html(showValue(subscriptionObj.whoRecontacted));
		$('#RECONTACTED_DATE').html(showValue(subscriptionObj.recontactedDate));
		$('#RECONTACTED_REMARKS').html(showValue(subscriptionObj.recontactedRemarks));
		$('#IS_SELECTED').html(showValue(subscriptionObj.isSelected));
		$('#WHO_SELECTED').html(showValue(subscriptionObj.whoSelected));
		$('#SELECTION_DATE').html(showValue(subscriptionObj.selectionDate));
		$('#SELECTION_REMARKS').html(showValue(subscriptionObj.selectionRemarks));
		$('#IS_REJECTED').html(showValue(subscriptionObj.isRejected));
		$('#WHO_REJECTED').html(showValue(subscriptionObj.whoRejected));
		$('#REJECTION_DATE').html(showValue(subscriptionObj.rejectionDate));
		$('#REJECTION_REMARKS').html(showValue(subscriptionObj.rejectionRemarks));
		$('#RECORD_LAST_UPDATED').html(showValue(subscriptionObj.recordLastUpdated));
		
		replacePlaceHoldersForEmailPanel(showValue(subscriptionObj.emailId), showValue(subscriptionObj.firstName)+' '+showValue(subscriptionObj.lastName));
	} 
}

function takeAction(button) {
	var data = { 
			gridName: subscriptionListMap.selectedGrid, 
			button : button, 
			tentativeSubscriptionId : subscriptionListMap[subscriptionListMap.selectedGrid].getCurrentSubscribedRecord().tentativeSubscriptionId,
			remarks : $('#remarks').val()
	}
	successMessage = 'Tutor Registration Admin action successfully taken.';
	callbackAfterCommonSuccess = takeActionAfterSuccessCallback;
	callWebservice('/rest/admin/takeActionOnSubscriptions', data, null, null, null, 'application/x-www-form-urlencoded');
}

var takeActionAfterSuccessCallback = function(response) {
	$('#remarks').val('');
	showSubscriptionAllRecordsPage();
	loadGrids();
}
function downloadReport() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminReportSubscriptions';
	form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/admin/downloadAdminIndividualSubscriptionProfilePdf';
	$('#downloadForm-tentativeSubscriptionId').val(subscriptionListMap[subscriptionListMap.selectedGrid].getCurrentSubscribedRecord().tentativeSubscriptionId);
	$('#downloadForm-name').val(subscriptionListMap[subscriptionListMap.selectedGrid].getCurrentSubscribedRecord().firstName + '_' + subscriptionListMap[subscriptionListMap.selectedGrid].getCurrentSubscribedRecord().lastName);
	form.submit();
}

function hideSubscribedAllRecordsPage() {
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
	$('#'+subscriptionListMap.selectedGrid+'-action-section').removeClass('noscreen');
	var obj = document.getElementById(subscriptionListMap.selectedGrid+'-action-section');
	if (null != obj) {
		$('#remarks-div').removeClass('noscreen');
	} else {
		$('#remarks-div').addClass('noscreen');
	}	
}