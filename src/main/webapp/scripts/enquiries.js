var customerListMap = new Object();
customerListMap.selectedGrid = 'none';
var particularCustomerEnquiryNavigatiorObject = new Object();

function showCustomerWithPendingEnquiries(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForCustomers(response, 'customers-with-pending-enquiries');
}

function showCustomerWithMappedEnquiries(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForCustomers(response, 'customers-with-mapped-enquiries');
}

function showCustomerWithAbandonedEnquiries(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForCustomers(response, 'customers-with-abandoned-enquiries');
}

function showAllEnquiriesForParticularCustomer(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectListForEnquiries(response);
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectListForCustomers(response, gridName) {
	customerListMap[gridName] = createNavigatorObject(response);
	var customerNavigatorObject = customerListMap[gridName];
	var html ='';
	for (var i=0;i < customerNavigatorObject.getRecordCount(); i++) {
		var customerObj = customerNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularCustomerRecord(\''+gridName+'\', '+i+')">'+showValue(customerObj.name)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(customerObj.emailId)+'">'+showValue(customerObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(customerObj.contactNumber)+'">'+showValue(customerObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + customerNavigatorObject.getRecordCount());
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectListForEnquiries(response) {
	particularCustomerEnquiryNavigatiorObject = createNavigatorObject(response);
	var html ='';
	for (var i=0;i < particularCustomerEnquiryNavigatiorObject.getRecordCount(); i++) {
		var enquiryObj = particularCustomerEnquiryNavigatiorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularEnquiryRecord('+i+')">'+showValue(enquiryObj.subject)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularEnquiryRecord('+i+')">'+showValue(enquiryObj.grade)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularEnquiryRecord('+i+')">'+showValue(enquiryObj.locationDetails)+ '</a></td>';
		html +='</tr>';
	}
	$('#all-enquiries-records').html(html);
	$('#all-enquiries-total-records').html('Total Records = ' + particularCustomerEnquiryNavigatiorObject.getRecordCount());
}

function hideAllCustomersEnquiriesPage() {
	$('#particular-customer-all-enquiries-div').removeClass('noscreen');
	$('#all-customers-with-enquiries-div').addClass('noscreen');
}

function showAllCustomersEnquiriesPage() {
	$('#all-customers-with-enquiries-div').removeClass('noscreen');
	$('#particular-customer-all-enquiries-div').addClass('noscreen');
}

function previousCustomerRecord() {
	openCustomerRecord(customerListMap[customerListMap.selectedGrid].previousRecord());
}

function nextCustomerRecord() {
	openCustomerRecord(customerListMap[customerListMap.selectedGrid].nextRecord());
}

function getParticularCustomerRecord(gridName, recordNumber) {
	customerListMap.selectedGrid = gridName;
	openCustomerRecord(customerListMap[customerListMap.selectedGrid].getParticularRecord(recordNumber));
}

function openCustomerRecord(customerObj) {
	hideAllCustomersEnquiriesPage();
	if (null != customerObj) {
		$('#NAME').html(showValue(customerObj.name));
		$('#CONTACT_NUMBER').html(showValue(customerObj.contactNumber));
		$('#EMAIL_ID').html(showValue(customerObj.emailId));
		$('#BASIC_INFO-NAME').html(showValue(customerObj.name));
		$('#BASIC_INFO-CONTACT_NUMBER').html(showValue(customerObj.contactNumber));
		$('#BASIC_INFO-EMAIL_ID').html(showValue(customerObj.emailId));
		$('#ADDRESS_DETAILS').html(showValue(customerObj.addressDetails));
		$('#ADDITIONAL_DETAILS').html(showValue(customerObj.additionalDetails));
		
		replacePlaceHoldersForEmailPanel(showValue(customerObj.emailId), showValue(customerObj.name));
		
		var data = { 
			customerId: customerObj.customerId
		}
		callWebservice('/rest/enquiry/displayAllEnquiriesForParticularCustomer', data, showAllEnquiriesForParticularCustomer, null, null, 'application/x-www-form-urlencoded');
	} 
}

function previousEnquiryRecord() {
	openEnquiryRecord(particularCustomerEnquiryNavigatiorObject.previousRecord());
}

function nextEnquiryRecord() {
	openEnquiryRecord(particularCustomerEnquiryNavigatiorObject.nextRecord());
}

function getParticularEnquiryRecord(recordNumber) {
	openEnquiryRecord(particularCustomerEnquiryNavigatiorObject.getParticularRecord(recordNumber));
}

function hideAllEnquiriesParticularCustomerPage() {
	$('#particular-customer-particular-enquiry-div').removeClass('noscreen');
	$('#particular-customer-all-enquiries-div').addClass('noscreen');
}

function showAllEnquiriesParticularCustomerPage() {
	$('#particular-customer-all-enquiries-div').removeClass('noscreen');
	$('#particular-customer-particular-enquiry-div').addClass('noscreen');
}

function openEnquiryRecord(enquiryObject) {
	hideAllEnquiriesParticularCustomerPage();
	if (null != enquiryObject) {
		$('#ENQUIRY_DETAILS_SUBJECT').html(showValue(enquiryObject.subject));
		$('#ENQUIRY_DETAILS_GRADE').html(showValue(enquiryObject.grade));
		$('#ENQUIRY_DETAILS_LOCATION_DETAILS').html(showValue(enquiryObject.locationDetails));
		$('#ENQUIRY_DETAILS_ADDRESS_DETAILS').html(showValue(enquiryObject.addressDetails));
		$('#ENQUIRY_DETAILS_PREFERRED_TEACHING_TYPE').html(showValue(enquiryObject.preferredTeachingType));
		$('#ENQUIRY_DETAILS_QUOTED_CLIENT_RATE').html(showValue(enquiryObject.quotedClientRate));
		$('#ENQUIRY_DETAILS_NEGOTIATED_CLIENT_RATE').html(showValue(enquiryObject.negotiatedRateWithClient));
		$('#ENQUIRY_DETAILS_CLEINT_NEGOTIATION_REMARKS').html(showValue(enquiryObject.clientNegotiationRemarks));
		$('#ENQUIRY_DETAILS_IS_MAPPED').html(showValue(enquiryObject.isMapped));
		$('#ENQUIRY_DETAILS_MATCH_STATUS').html(showValue(enquiryObject.matchStatus));
		$('#ENQUIRY_DETAILS_TUTOR_NAME').html(showValue(enquiryObject.tutorName));
		$('#ENQUIRY_DETAILS_TUTOR_EMAIL').html(showValue(enquiryObject.tutorEmail));
		$('#ENQUIRY_DETAILS_TUTOR_CONTACT_NUMBER').html(showValue(enquiryObject.tutorContactNumber));
		$('#ENQUIRY_DETAILS_ADDITIONAL_DETAILS').html(showValue(enquiryObject.additionalDetails));
		$('#ENQUIRY_DETAILS_WHO_ACTED').html(showValue(enquiryObject.whoActed));
		$('#ENQUIRY_DETAILS_LAST_ACTION_DATE').html(showValue(enquiryObject.lastActionDate));
		$('#ENQUIRY_DETAILS_ADMIN_REMARKS').html(showValue(enquiryObject.adminRemarks));
	} 
}

function loadEnquiryDetailsDropdowns(response) {
	var studentGradeSelectHTML = createSelectOptionOutOfSelectLookupArray(response.studentGradeLookUp);
	$('#form-grade').html($('#form-grade').html() + studentGradeSelectHTML);
	
	var subjectsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.subjectsLookUp);
	$('#form-subject').html($('#form-subject').html() + subjectsSelectHTML);
	
	var locationsSelectHTML = createSelectOptionOutOfSelectLookupArray(response.locationsLookUp);
	$('#form-locations').html($('#form-locations').html() + locationsSelectHTML);
	
	var preferredTeachingTypeHTML = createSelectOptionOutOfSelectLookupArray(response.preferredTeachingTypeLookUp);
	$('#form-preferred-teaching-type').html($('#form-preferred-teaching-type').html() + preferredTeachingTypeHTML);
}

function loadGrids() {
	callWebservice('/rest/enquiry/displayCustomerWithPendingEnquiries', null, showCustomerWithPendingEnquiries);
	callWebservice('/rest/enquiry/displayCustomerWithMappedEnquiries', null, showCustomerWithMappedEnquiries);
	callWebservice('/rest/enquiry/displayCustomerWithAbandonedEnquiries', null, showCustomerWithAbandonedEnquiries);
	
	callWebservice('/rest/enquiry/getDropdownListDataForEnquiryDetails', null, loadEnquiryDetailsDropdowns);
}

function prepareEnquiryUpdateParams() {
	var form = {
			enquiryId					: particularCustomerEnquiryNavigatiorObject.getCurrentRecord().enquiryId,
			subject 					: getAttributeValue('form-subject', false),
			grade 						: getAttributeValue('form-grade', false),
			quotedClientRate 			: getAttributeValue('form-quoted-client-rate', false),
			negotiatedRateWithClient 	: getAttributeValue('form-negotiated-client-rate', false),
			clientNegotiationRemarks 	: getAttributeValue('form-negotiation-details', false),
			adminRemarks				: getAttributeValue('form-admin-remarks', false),
			locationDetails 			: getAttributeValue('form-locations', true),
			addressDetails       		: getAttributeValue('form-address-details', false),
			additionalDetails 			: getAttributeValue('form-additional-details', false),
			preferredTeachingType 		: getAttributeValue('form-preferred-teaching-type', true)
		};
	return form;
}

function updateEnquiryDetails() {
	successMessage = 'Enquiry details updated successfully.';
	callbackAfterCommonSuccess = loadBackParticularCustomerAllEnquiries;
	callWebservice('/rest/enquiry/updateEnquiryDetails', encodeObjectAsJSON(prepareEnquiryUpdateParams()));
	resetEnquiryDetails();
}

function loadBackParticularCustomerAllEnquiries() {
	showAllEnquiriesParticularCustomerPage();
	openCustomerRecord(customerListMap[customerListMap.selectedGrid].getCurrentRecord());
}

function resetEnquiryDetails() {
	var form = document.getElementById('details-update-form');
	form.reset();
}

function downloadReport() {
	var form = document.getElementById('downloadForm');
	//form.action = ctxPath + '/rest/admin/downloadAdminReportTutorRegistrations';
	//form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	//form.action = ctxPath + '/rest/admin/downloadAdminTutorRegistrationProfilePdf';
	$('#downloadForm-tentativeTutorId').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().tentativeTutorId);
	$('#downloadForm-name').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().firstName + '_' + tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().lastName);
	//form.submit();
}