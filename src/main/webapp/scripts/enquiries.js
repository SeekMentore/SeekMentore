var customerListMap = new Object();
customerListMap.selectedGrid = 'none';
var particularCustomerEnquiryNavigatiorObject = new Object();
var particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap = new Object();
particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid = 'none';
var selectedEligibleTutorId = '';
var selectedTutorMappedId = '';

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

function showAllEligibleTutors(response) {
	prepareHTMLToFillGridForEligibleTutors(response);
}

function showAllMappedDemoPendingTutors(response) {
	prepareHTMLToFillGridForMappedTutors(response, 'all-mapped-pending-tutors');
}

function showAllMappedDemoScheduledTutors(response) {
	prepareHTMLToFillGridForMappedTutors(response, 'all-mapped-scheduled-tutors');
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

function prepareHTMLToFillGridForEligibleTutors(response) {
	selectedEligibleTutorId = '';
	var eligibleTutorListWithSubjectGradeLocationTeachingType = response.eligibleTutorListWithSubjectGradeLocationTeachingType;
	var eligibleTutorListWithSubjectGradeLocation = response.eligibleTutorListWithSubjectGradeLocation;
	var eligibleTutorListWithSubjectGrade = response.eligibleTutorListWithSubjectGrade;
	var html ='';
	for (var i=0;i < eligibleTutorListWithSubjectGradeLocationTeachingType.length; i++) {
		var tutorObj = eligibleTutorListWithSubjectGradeLocationTeachingType[i];
		html +='<tr>';
		html += '<td>'+showValue(tutorObj.name)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedSubjects)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedStudentGrades)+ '</td>';
		html += '<td>'+showValue(tutorObj.teachingExp)+ '</td>';
		html += '<td>'+showValue(tutorObj.comfortableLocations)+ '</td>';
		html += '<td>'+showValue(tutorObj.preferredTeachingType)+ '</td>';
		html += '<td><input type="checkbox" onclick="toggleTutorIdForMapping(this,\''+tutorObj.tutorId+'\')" /></td>';
		html +='</tr>';
	}
	$('#all-eligible-tutors-with-subject-grade-location-and-teachingtype-records').html(html);
	$('#all-eligible-tutors-with-subject-grade-location-and-teachingtype-total-records').html('Total Records = ' + eligibleTutorListWithSubjectGradeLocationTeachingType.length);
	
	var html ='';
	for (var i=0;i < eligibleTutorListWithSubjectGradeLocation.length; i++) {
		var tutorObj = eligibleTutorListWithSubjectGradeLocation[i];
		html +='<tr>';
		html += '<td>'+showValue(tutorObj.name)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedSubjects)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedStudentGrades)+ '</td>';
		html += '<td>'+showValue(tutorObj.teachingExp)+ '</td>';
		html += '<td>'+showValue(tutorObj.comfortableLocations)+ '</td>';
		html += '<td>'+showValue(tutorObj.preferredTeachingType)+ '</td>';
		html += '<td><input type="checkbox" onclick="toggleTutorIdForMapping(this,\''+tutorObj.tutorId+'\')" /></td>';
		html +='</tr>';
	}
	$('#all-eligible-tutors-with-subject-grade-and-location-records').html(html);
	$('#all-eligible-tutors-with-subject-grade-and-location-total-records').html('Total Records = ' + eligibleTutorListWithSubjectGradeLocation.length);
	
	var html ='';
	for (var i=0;i < eligibleTutorListWithSubjectGrade.length; i++) {
		var tutorObj = eligibleTutorListWithSubjectGrade[i];
		html +='<tr>';
		html += '<td>'+showValue(tutorObj.name)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedSubjects)+ '</td>';
		html += '<td>'+showValue(tutorObj.interestedStudentGrades)+ '</td>';
		html += '<td>'+showValue(tutorObj.teachingExp)+ '</td>';
		html += '<td>'+showValue(tutorObj.comfortableLocations)+ '</td>';
		html += '<td>'+showValue(tutorObj.preferredTeachingType)+ '</td>';
		html += '<td><input type="checkbox" onclick="toggleTutorIdForMapping(this,\''+tutorObj.tutorId+'\')" /></td>';
		html +='</tr>';
	}
	$('#all-eligible-tutors-with-subject-and-grade-records').html(html);
	$('#all-eligible-tutors-with-subject-and-grade-total-records').html('Total Records = ' + eligibleTutorListWithSubjectGrade.length);
}

function prepareHTMLToFillGridForMappedTutors(response, gridName) {
	selectedTutorMappedId = '';
	particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[gridName] = createNavigatorObject(response);
	var particularCustomerParticularEnquiryTutorMapperNavigatiorObject = particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[gridName];
	var html ='';
	for (var i=0;i < particularCustomerParticularEnquiryTutorMapperNavigatiorObject.getRecordCount(); i++) {
		var tutorMapperObject = particularCustomerParticularEnquiryTutorMapperNavigatiorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorMapperRecord(\''+gridName+'\', '+i+')">'+showValue(tutorMapperObject.tutorName)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorMapperRecord(\''+gridName+'\', '+i+')">'+showValue(tutorMapperObject.tutorEmail)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorMapperRecord(\''+gridName+'\', '+i+')">'+showValue(tutorMapperObject.tutorContactNumber)+ '</a></td>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorMapperRecord(\''+gridName+'\', '+i+')">'+showValue(tutorMapperObject.mappingStatus)+ '</a></td>';
		if (gridName == 'all-mapped-pending-tutors') {
			html += '<td><input type="checkbox" onclick="toggleTutorMapperIdForUnMapping(this,\''+tutorMapperObject.tutorMapperId+'\')" /></td>';
		}
		html +='</tr>';
	}
	$('#'+gridName+'-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + particularCustomerParticularEnquiryTutorMapperNavigatiorObject.getRecordCount());
	if (particularCustomerParticularEnquiryTutorMapperNavigatiorObject.getRecordCount() > 0) {
		$('#form-subject').prop('disabled',true);
		$('#form-grade').prop('disabled',true);
		$('#form-locations').prop('disabled',true);
		$('#form-preferred-teaching-type').prop('disabled',true);
	}
}

function toggleTutorIdForMapping(obj, tutorId) {
	if (obj.checked) {
		selectedEligibleTutorId += tutorId + ';';
	} else {
		selectedEligibleTutorId = selectedEligibleTutorId.replace(tutorId + ';', '');
	}
}

function toggleTutorMapperIdForUnMapping(obj, tutorMapperId) {
	if (obj.checked) {
		selectedTutorMappedId += tutorMapperId + ';';
	} else {
		selectedTutorMappedId = selectedTutorMappedId.replace(tutorMapperId + ';', '');
	}
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
		$('#BASIC_INFO-2-NAME').html(showValue(customerObj.name));
		$('#BASIC_INFO-2-CONTACT_NUMBER').html(showValue(customerObj.contactNumber));
		$('#BASIC_INFO-2-EMAIL_ID').html(showValue(customerObj.emailId));
		$('#ADDRESS_DETAILS').html(showValue(customerObj.addressDetails));
		$('#ADDITIONAL_DETAILS').html(showValue(customerObj.additionalDetails));
		
		var data = { 
			customerId: customerObj.customerId,
			grid	: customerListMap.selectedGrid
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
		$('#BASIC_INFO-ENQUIRY_SUBJECT').html(showValue(enquiryObject.subject));
		$('#BASIC_INFO-ENQUIRY_GRADE').html(showValue(enquiryObject.grade));
		$('#BASIC_INFO-ENQUIRY_LOCATION').html(showValue(enquiryObject.locationDetails));
		$('#BASIC_INFO-ENQUIRY_TEACHING_TYPE').html(showValue(enquiryObject.preferredTeachingType));
		$('#BASIC_INFO-ENQUIRY_QUOTED_CLIENT_RATE').html(showValue(enquiryObject.quotedClientRate));
		$('#BASIC_INFO-ENQUIRY_NEGOTIATED_CLIENT_RATE').html(showValue(enquiryObject.negotiatedRateWithClient));
		
		loadMappedTutors();
		loadEligibleTutors();
	} 
}

function previousTutorMapperRecord() {
	openTutorMapperRecord(particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid].previousRecord());
}

function nextTutorMapperRecord() {
	openTutorMapperRecord(particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid].nextRecord());
}

function getParticularTutorMapperRecord(gridName, recordNumber) {
	particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid = gridName;
	openTutorMapperRecord(particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid].getParticularRecord(recordNumber));
}

function hideParticularEnquiryAllMappedAndEligibleTutorsPage() {
	$('#particular-customer-particular-enquiry-particular-mapped-tutor-div').removeClass('noscreen');
	$('#particular-customer-particular-enquiry-div').addClass('noscreen');
}

function showParticularEnquiryAllMappedAndEligibleTutorsPage() {
	$('#particular-customer-particular-enquiry-div').removeClass('noscreen');
	$('#particular-customer-particular-enquiry-particular-mapped-tutor-div').addClass('noscreen');
	$('.form-element').removeClass('noscreen');
}

function openTutorMapperRecord(tutorMapperObject) {
	hideParticularEnquiryAllMappedAndEligibleTutorsPage();
	if (null != tutorMapperObject) {
		$('#TUTOR_MAPPER_TUTOR_NAME').html(showValue(tutorMapperObject.tutorName));
		$('#TUTOR_MAPPER_TUTOR_EMAIL').html(showValue(tutorMapperObject.tutorEmail));
		$('#TUTOR_MAPPER_TUTOR_CONTACT_NUMBER').html(showValue(tutorMapperObject.tutorContactNumber));
		$('#MAPPING_STATUS').html(showValue(tutorMapperObject.mappingStatus));
		$('#QUOTED_TUTOR_RATE').html(showValue(tutorMapperObject.quotedTutorRate));
		$('#NEGOTIATED_RATE_WITH_TUTOR').html(showValue(tutorMapperObject.negotiatedRateWithTutor));
		$('#TUTOR_NEGOTIATION_REMARKS').html(showValue(tutorMapperObject.tutorNegotiationRemarks));
		$('#IS_TUTOR_CONTACTED').html(showValue(tutorMapperObject.isTutorContacted));
		$('#TUTOR_CONTACTED_DATE').html(showValue(tutorMapperObject.tutorContactedDate));
		$('#IS_TUTOR_AGREED').html(showValue(tutorMapperObject.isTutorAgreed));
		$('#IS_TUTOR_REJECTION_VALID').html(showValue(tutorMapperObject.isTutorRejectionValid));
		$('#ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE').html(showValue(tutorMapperObject.adminTutorRejectionValidityResponse));
		$('#TUTOR_RESPONSE').html(showValue(tutorMapperObject.tutorResponse));
		$('#ADMIN_REMARKS_FOR_TUTOR').html(showValue(tutorMapperObject.adminRemarksForTutor));
		$('#IS_CLIENT_CONTACTED').html(showValue(tutorMapperObject.isClientContacted));
		$('#CLIENT_CONTACTED_DATE').html(showValue(tutorMapperObject.clientContactedDate));
		$('#IS_CLIENT_AGREED').html(showValue(tutorMapperObject.isClientAgreed));
		$('#IS_CLIENT_REJECTION_VALID').html(showValue(tutorMapperObject.isClientRejectionValid));
		$('#ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE').html(showValue(tutorMapperObject.adminClientRejectionValidityResponse));
		$('#CLIENT_RESPONSE').html(showValue(tutorMapperObject.clientResponse));
		$('#ADMIN_REMARKS_FOR_CLIENT').html(showValue(tutorMapperObject.adminRemarksForClient));
		$('#ADMIN_ACTION_DATE').html(showValue(tutorMapperObject.adminActionDate));
		$('#ADMIN_ACTION_REMARKS').html(showValue(tutorMapperObject.adminActionRemarks));
		$('#WHO_ACTED').html(showValue(tutorMapperObject.whoActed));
		$('#IS_DEMO_SCHEDULED').html(showValue(tutorMapperObject.isDemoScheduled));
		$('#DEMO_TIME_AND_DATE').html(showValue(tutorMapperObject.demoDateAndTime));
		
		if ('all-mapped-scheduled-tutors' ==  particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid) {
			$('.form-element').addClass('noscreen');
		}
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

function prepareTutorMapperUpdateParams() {
	var form = {
			tutorMapperId						: particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid].getCurrentRecord().tutorMapperId,
			quotedTutorRate 					: getAttributeValue('form-quoted-tutor-rate', false),
			negotiatedRateWithTutor 			: getAttributeValue('form-negotiated-tutor-rate', false),
			tutorNegotiationRemarks 			: getAttributeValue('form-negotiated-tutor-remarks', false),
			isTutorContacted 					: getAttributeValue('form-is-tutor-contacted', false),
			isTutorAgreed 						: getAttributeValue('form-is-tutor-agreed', false),
			isTutorRejectionValid				: getAttributeValue('form-is-tutor-rejection-valid', false),
			adminTutorRejectionValidityResponse : getAttributeValue('form-admin-tutor-rejection-validity-remarks', false),
			tutorResponse       				: getAttributeValue('form-tutor-response', false),
			adminRemarksForTutor 				: getAttributeValue('form-admin-remarks-for-tutor', false),
			isClientContacted 					: getAttributeValue('form-is-client-contacted', false),
			isClientAgreed 						: getAttributeValue('form-is-client-agreed', false),
			clientResponse 						: getAttributeValue('form-client-response', false),
			isClientRejectionValid				: getAttributeValue('form-is-client-rejection-valid', false),
			adminClientRejectionValidityResponse: getAttributeValue('form-admin-client-rejection-validity-remarks', false),
			adminRemarksForClient       		: getAttributeValue('form-admin-remarks-for-client', false),
			adminActionRemarks 					: getAttributeValue('form-admin-action-remarks', false)
		};
	return form;
}

function updateEnquiryDetails() {
	successMessage = 'Enquiry details updated successfully.';
	callbackAfterCommonSuccess = loadBackParticularCustomerAllEnquiries;
	callWebservice('/rest/enquiry/updateEnquiryDetails', encodeObjectAsJSON(prepareEnquiryUpdateParams()));
	resetEnquiryDetails();
}

function updateTutorMapperDetails() {
	successMessage = 'Tutor Mapper details updated successfully.';
	callbackAfterCommonSuccess = loadBackParticularEnquiryAllMappedAndEligibleTutorsPage;
	callWebservice('/rest/enquiry/updateTutorMapperDetails', encodeObjectAsJSON(prepareTutorMapperUpdateParams()));
	resetTutorMappeDetails();
}

function loadBackParticularCustomerAllEnquiries() {
	showAllEnquiriesParticularCustomerPage();
	openCustomerRecord(customerListMap[customerListMap.selectedGrid].getCurrentRecord());
}

function loadBackParticularEnquiryAllMappedAndEligibleTutorsPage() {
	showParticularEnquiryAllMappedAndEligibleTutorsPage();
	openEnquiryRecord(particularCustomerEnquiryNavigatiorObject.getCurrentRecord());
}

function resetEnquiryDetails() {
	var form = document.getElementById('details-update-form');
	form.reset();
}

function resetTutorMappeDetails() {
	var form = document.getElementById('details-mapper-update-form');
	form.reset();
}

function loadEligibleTutors() {
	var data = { 
		enquiryId: particularCustomerEnquiryNavigatiorObject.getCurrentRecord().enquiryId
	}
	callWebservice('/rest/enquiry/displayAllEligibleTutors', data, showAllEligibleTutors, null, null, 'application/x-www-form-urlencoded');
}

function loadMappedTutors() {
	var data = { 
		enquiryId: particularCustomerEnquiryNavigatiorObject.getCurrentRecord().enquiryId
	}
	callWebservice('/rest/enquiry/displayAllMappedDemoPendingTutors', data, showAllMappedDemoPendingTutors, null, null, 'application/x-www-form-urlencoded');
	callWebservice('/rest/enquiry/displayAllMappedDemoScheduledTutors', data, showAllMappedDemoScheduledTutors, null, null, 'application/x-www-form-urlencoded');
}

function mapTutors() {
	var data = { 
		enquiryId: particularCustomerEnquiryNavigatiorObject.getCurrentRecord().enquiryId,
		selectedEligibleTutorIdSemicolonSeparatedList : selectedEligibleTutorId
	}
	successMessage = 'Tutors Mapped successfully.';
	callbackAfterCommonSuccess = refreshTutorLists;
	callWebservice('/rest/enquiry/mapTutors', data, null, null, null, 'application/x-www-form-urlencoded');
}

function unmapTutors() {
	var data = { 
			selectedTutorMappedIdSemicolonSeparatedList : selectedTutorMappedId
	}
	successMessage = 'Tutors unmapped successfully.';
	callbackAfterCommonSuccess = refreshTutorLists;
	callWebservice('/rest/enquiry/unmapTutors', data, null, null, null, 'application/x-www-form-urlencoded');
}

function scheduleDemo() {
	var data = { 
		tutorMapperId: particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap[particularCustomerParticularEnquiryTutorMapperNavigatiorObjectListMap.selectedGrid].getCurrentRecord().tutorMapperId,
		demoTimeInMillis : getDateValueInMillis(getAttributeValue('form-demo-date', false)+' '+getAttributeValue('form-demo-time', false))
	}
	successMessage = 'Demo Scheduled successfully.';
	callbackAfterCommonSuccess = loadBackParticularEnquiryAllMappedAndEligibleTutorsPage;
	callWebservice('/rest/enquiry/scheduleDemo', data, null, null, null, 'application/x-www-form-urlencoded');
}

function refreshTutorLists() {
	loadMappedTutors();
	loadEligibleTutors();
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