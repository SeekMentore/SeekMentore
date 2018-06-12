var demoListMap = new Object();
demoListMap.selectedGrid = 'none';

function showScheduledDemos(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, 'all-scheduled-demo');
}

function showRescheduledDemos(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, 'all-rescheduled-demo');
}

function showSuccessDemos(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, 'all-successfull-demo');
}

function showFailedDemos(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, 'all-failed-demo');
}

function showCanceledDemos(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, 'all-canceled-demo');
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectListForDemos(response, gridName) {
	demoListMap[gridName] = createNavigatorObject(response);
	var demoNavigatorObject = demoListMap[gridName];
	var html ='';
	for (var i=0;i < demoNavigatorObject.getRecordCount(); i++) {
		var demoObj = demoNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularDemoRecord(\''+gridName+'\', '+i+')">'+showValue(demoObj.demoDateAndTime)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularDemoRecord(\''+gridName+'\', '+i+')">'+showValue(demoObj.customerName)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularDemoRecord(\''+gridName+'\', '+i+')">'+showValue(demoObj.tutorName)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularDemoRecord(\''+gridName+'\', '+i+')">'+showValue(demoObj.demoOccurred)+ '</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + demoNavigatorObject.getRecordCount());
}

function hideAllDemosPage() {
	$('#particular-demo-div').removeClass('noscreen');
	$('#all-demos-div').addClass('noscreen');
}

function showAllDemosPage() {
	$('#all-demos-div').removeClass('noscreen');
	$('#particular-demo-div').addClass('noscreen');
}

function previousDemoRecord() {
	openDemoRecord(demoListMap[demoListMap.selectedGrid].previousRecord());
}

function nextDemoRecord() {
	openDemoRecord(demoListMap[demoListMap.selectedGrid].nextRecord());
}

function getParticularDemoRecord(gridName, recordNumber) {
	demoListMap.selectedGrid = gridName;
	openDemoRecord(demoListMap[demoListMap.selectedGrid].getParticularRecord(recordNumber));
}

function openDemoRecord(demoObj) {
	hideAllDemosPage();
	if (null != demoObj) {
		$('#DEMO_DATE_AND_TIME').html(showValue(demoObj.demoDateAndTime));
		$('#DEMO_STATUS').html(showValue(demoObj.demoStatus));
		$('#DEMO_OCCURRED').html(showValue(demoObj.demoOccurred));
		$('#CLIENT_SATISFIED_FROM_TUTOR').html(showValue(demoObj.clientSatisfiedFromTutor));
		$('#CLIENT_REMARKS').html(showValue(demoObj.clientRemarks));
		$('#TUTOR_SATISFIED_WITH_CLIENT').html(showValue(demoObj.tutorSatisfiedWithClient));
		$('#TUTOR_REMARKS').html(showValue(demoObj.tutorRemarks));
		$('#ADMIN_SATISFIED_WITH_CLIENT').html(showValue(demoObj.adminSatisfiedWithClient));
		$('#ADMIN_SATISFIED_FROM_TUTOR').html(showValue(demoObj.adminSatisfiedFromTutor));
		$('#NEED_PRICE_NEGOTIATION_WITH_CLIENT').html(showValue(demoObj.needPriceNegotiationWithClient));
		$('#NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT').html(showValue(demoObj.negotiatedOverrideRateWithClient));
		$('#CLIENT_NEGOTIATION_REMARKS').html(showValue(demoObj.clientNegotiationRemarks));
		$('#NEED_PRICE_NEGOTIATION_WITH_TUTOR').html(showValue(demoObj.needPriceNegotiationWithTutor));
		$('#NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR').html(showValue(demoObj.negotiatedOverrideRateWithTutor));
		$('#TUTOR_NEGOTIATION_REMARKS').html(showValue(demoObj.tutorNegotiationRemarks));
		$('#IS_DEMO_SUCCESS').html(showValue(demoObj.isDemoSuccess));
		$('#ADMIN_ACTION_DATE').html(showValue(demoObj.adminActionDate));
		$('#ADMIN_REMARKS').html(showValue(demoObj.adminRemarks));
		$('#WHO_ACTED').html(showValue(demoObj.whoActed));
		$('#ADMIN_FINALIZING_REMARKS').html(showValue(demoObj.adminFinalizingRemarks));
		$('#RESCHEDULING_REMARKS').html(showValue(demoObj.reschedulingRemarks));
		
		if ('all-scheduled-demo' ==  demoListMap.selectedGrid) {
			$('.form-element').removeClass('noscreen');
		} else {
			$('.form-element').addClass('noscreen');
		}
		
		var data = { 
			demoTrackerId: demoObj.demoTrackerId
		}
		callWebservice('/rest/demo/displayDemoDetails', data, showParticularDemo, null, null, 'application/x-www-form-urlencoded');
	} 
}

function prepareDemoTrackerUpdateParams() {
	var form = {
			demoTrackerId						: demoListMap[demoListMap.selectedGrid].getCurrentRecord().demoTrackerId,
			demoOccurred 						: getAttributeValue('form-demo-occurred', false),
			clientSatisfiedFromTutor 			: getAttributeValue('form-client-satisfied-from-tutor', false),
			clientRemarks 						: getAttributeValue('form-client-remarks', false),
			tutorSatisfiedWithClient 			: getAttributeValue('form-tutor-satisfied-with-client', false),
			tutorRemarks 						: getAttributeValue('form-tutor-remarks', false),
			adminSatisfiedWithClient			: getAttributeValue('form-admin-satisfied-with-client', false),
			adminSatisfiedFromTutor 			: getAttributeValue('form-admin-satisfied-with-tutor', false),
			needPriceNegotiationWithClient      : getAttributeValue('form-need-price-negotiation-with-client', false),
			negotiatedOverrideRateWithClient 	: getAttributeValue('form-negotiated-override-rate-with-client', false),
			clientNegotiationRemarks 			: getAttributeValue('form-client-negotiation-remarks', false),
			needPriceNegotiationWithTutor 		: getAttributeValue('form-need-price-negotiation-with-tutor', false),
			negotiatedOverrideRateWithTutor 	: getAttributeValue('form-negotiated-override-rate-with-tutor', false),
			tutorNegotiationRemarks				: getAttributeValue('form-tutor-negotiation-remarks', false),
			adminRemarks						: getAttributeValue('form-admin-remarks', false)
		};
	return form;
}

function updateDemoTrackerDetails() {
	successMessage = 'Demo Tracker details updated successfully.';
	callbackAfterCommonSuccess = updateDemoTrackerDetailsSuccess;
	callWebservice('/rest/demo/updateDemoTrackerDetails', encodeObjectAsJSON(prepareDemoTrackerUpdateParams()));
	resetDemoTrackerDetails();
}

function demoSuccess() {
	successMessage = 'Demo Successfull.';
	callbackAfterCommonSuccess = updateDemoTrackerDetailsSuccess;
	var data = { 
		demoTrackerId: demoListMap[demoListMap.selectedGrid].getCurrentRecord().demoTrackerId,
		finalizingRemarks : getAttributeValue('form-failure-cancel-remarks', false)
	}
	callWebservice('/rest/demo/demoSuccess', data, null, null, null, 'application/x-www-form-urlencoded');
	resetDemoTrackerDetails();
}

function demoFailure() {
	successMessage = 'Demo Failed.';
	callbackAfterCommonSuccess = updateDemoTrackerDetailsSuccess;
	var data = { 
		demoTrackerId: demoListMap[demoListMap.selectedGrid].getCurrentRecord().demoTrackerId,
		finalizingRemarks : getAttributeValue('form-failure-cancel-remarks', false)
	}
	callWebservice('/rest/demo/demoFailure', data, null, null, null, 'application/x-www-form-urlencoded');
	resetDemoTrackerDetails();
}

function cancelDemo() {
	successMessage = 'Demo Canceled';
	callbackAfterCommonSuccess = updateDemoTrackerDetailsSuccess;
	var data = { 
		demoTrackerId: demoListMap[demoListMap.selectedGrid].getCurrentRecord().demoTrackerId,
		finalizingRemarks : getAttributeValue('form-failure-cancel-remarks', false)
	}
	callWebservice('/rest/demo/cancelDemo', data, null, null, null, 'application/x-www-form-urlencoded');
	resetDemoTrackerDetails();
}

function rescheduleDemo() {
	successMessage = 'Demo Successfull.';
	callbackAfterCommonSuccess = updateDemoTrackerDetailsSuccess;
	var demoTimeInMillis = getDateValueInMillis(getAttributeValue('form-demo-date', false)+' '+getAttributeValue('form-demo-time', false));
	var data = { 
		demoTrackerId: demoListMap[demoListMap.selectedGrid].getCurrentRecord().demoTrackerId,
		demoTimeInMillis : isNaN(demoTimeInMillis) ? null : demoTimeInMillis,
		finalizingRemarks : getAttributeValue('form-reschedule-remarks', false)
	}
	callWebservice('/rest/demo/rescheduleDemo', data, null, null, null, 'application/x-www-form-urlencoded');
	resetDemoTrackerDetails();
}

function updateDemoTrackerDetailsSuccess() {
	loadGrids();
	showAllDemosPage();
}

function resetDemoTrackerDetails() {
	var form = document.getElementById('demo-tracker-details-update-form');
	form.reset();
}

function showParticularDemo(response) {
	var tutorMapperObject = response.tutorMapperObject;
	var enquiryObject = response.enquiryObject;
	var registeredTutorObj = response.registeredTutorObj;
	var subscribedCustomerObj = response.subscribedCustomerObj;
	
	$('#CLIENT_NAME').html(showValue(subscribedCustomerObj.name));
	$('#CLIENT_EMAIl').html(showValue(subscribedCustomerObj.emailId));
	$('#CLIENT_CONTACT_NUMBER').html(showValue(subscribedCustomerObj.contactNumber));
	
	$('#SUBJECT').html(showValue(enquiryObject.subject));
	$('#GRADE').html(showValue(enquiryObject.grade));
	$('#ADDRESS').html(showValue(enquiryObject.addressDetails));
	$('#QUOTED_CLIENT_RATE').html(showValue(enquiryObject.quotedClientRate));
	$('#NEGOTIATED_CLIENT_RATE').html(showValue(enquiryObject.negotiatedRateWithClient));
	
	$('#TUTOR_NAME').html(showValue(registeredTutorObj.name));
	$('#TUTOR_EMAIl').html(showValue(registeredTutorObj.emailId));
	$('#TUTOR_CONTACT_NUMBER').html(showValue(registeredTutorObj.contactNumber));
	
	$('#QUOTED_TUTOR_RATE').html(showValue(tutorMapperObject.quotedTutorRate));
	$('#NEGOTIATED_TUTOR_RATE').html(showValue(tutorMapperObject.negotiatedRateWithTutor));
}

function loadGrids() {
	callWebservice('/rest/demo/displayScheduledDemos', null, showScheduledDemos);
	callWebservice('/rest/demo/displayRescheduledDemos', null, showRescheduledDemos);
	callWebservice('/rest/demo/displaySuccessfullDemos', null, showSuccessDemos);
	callWebservice('/rest/demo/displayFailedDemos', null, showFailedDemos);
	callWebservice('/rest/demo/displayCanceledDemos', null, showCanceledDemos);
}