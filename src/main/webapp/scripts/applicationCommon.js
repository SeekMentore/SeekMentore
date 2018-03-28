var ctxPath = '/SeekMentor';
var output;
commonErrorHandler = function(error) {
	output = error;
	$('#responseDiv').html(encodeObjectAsJSON(output));
	console.error('error', error);
}
commmonSuccessHandler = function(response) {
	if (null != response) {
		output = response;
	}
	$('#responseDiv').html(encodeObjectAsJSON(output));
	console.log('response', response);
}

function callWebservice(url, data, success, failure, method, contentType) {
	$.ajax({
        url			: ctxPath + url,
        type		: ((null != method) ? method : 'POST'),
        data		: data,
        contentType	: ((null != contentType) ? contentType : 'application/json'),
        cache		: false,
        dataType	: 'json',
        success		: ((null != success) ? success : commmonSuccessHandler),
        error		: ((null != failure) ? failure : commonErrorHandler)
    });
}

function encodeObjectAsJSON(object) {
	return null != object ? JSON.stringify(object) : null;
}

function decodeObjectFromJSON(json) {
	return null != json ? JSON.parse(json) : null;
}

function getFormF() {
	var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus							: 'SAVE_DRAFT',
			name 								: 'Demo Name',
			noticeDate 							:  new Date(),
			fullName 							: 'Demo Full Name',
			gender 								: 'M',
			doa 								:  new Date(),
			department 							: 'Demo Dept',
			employeeNumber 						: '11111',
			fathersName 						: 'Demo Father Name',
			husbandsName 						: 'NA',
			maritalStatus 						: 'S',
			dateOfBirth 						:  new Date(),
			permanentAddress 					: 'Demo Address Line 1, \nDemo Address Line 2, \nDemo Address Line 3',
			state 								: 'UP',
			pincode 							: '222222',
			appointmentDate						:  new Date(),
			designation							: 'Prog Analyst',
			familyFlagString					: 'Y',
			selfDependents						: 'Father',
			husbandDependents   				: 'Both',
			employeeGratuityNomineeDetailsList	: [
				{
					//id							:  1, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '1st Demo Name and Address',
					relationship				: 'S',
					age							: 26,
					percentage					: 30,
					deleteRecord				: false // Mark this true if you would like to delete this record
				},
				{
					//id							:  2, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '2nd Demo Name and Address',
					relationship				: 'W',
					age							: 28,
					percentage					: 70,
					deleteRecord				: false // Mark this true if you would like to delete this record
				},
				{
					//id							:  3, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 				: '3rd Demo Name and Address',
					relationship				: 'W',
					age							: 28,
					percentage					: 70,
					deleteRecord				: false // Mark this true if you would like to delete this record
				}
			]
		};
	return form;
}

function getForm11() {
	var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus						: 'SAVE_DRAFT',
			name 							: 'Demo Name',
			dateOfBirth 					:  new Date(),
			fatherOrHusbandsName 			: 'Demo Father Name',
			relationWithAbove 				: 'F',
			gender 							: 'M',
			mobileNumber 					: '9999999999',
			emailId 						: 'xxxxxxxxxxxxxxxxx@gmail.com',
			earlierMemberOfPF1952 			: 'N',
			earlierMemberOfPF1995 			: 'Y',
			uan 							: '123456789',
			pfMemberId 						: 'MMO12345',
			dateOfExit 						:  new Date(),
			schemeCertificateNumber 		: '987654321',
			ppoNumber 						: '999999999',
			internationalWorker 			: 'N',
			countryOfOrigin 				: 'IND',
			passportNumber 					: 'X123456',
			passportValidFrom 				:  new Date(),
			passportValidTo 				:  new Date(),
			educationalQualification 		: 'Bachelor of Technology',
			maritalStatus 					: 'S',
			speciallyAbled 					: 'N',
			category 						: 'P',
			nonContributoryDays				:  12,
			pfAccNumber						: '123546788',
			establishNameWithAddr			: 'Demo Establish Name With Addr',
			trustNameWithAddr				: 'Demo Trust Name With Addr',
			dateOfJoining					:  new Date(),
			kycDetailsList					: [
				{
					//id						:  4, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '1st Document Type',
					documentName			: '1st Demo Document Name',
					documentNumber			: '12345',
					remarks					: '1st Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					//id						:  6, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '2nd Demo Document Type',
					documentName			: '2nd Demo Document Name',
					documentNumber			: '67890',
					remarks					: '2nd Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					//id						:  2, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					documentType 			: '3nd Demo Document Type',
					documentName			: '3nd Demo Document Name',
					documentNumber			: '67890',
					remarks					: '3nd Demo Remarks',
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			]
	 	};
	return form;
}

function getForm2() {
	var form = {
			// empId is the Unique Id, which will be used to Search any particular form, but it is directly taken from SMHEADER/SMSESSION
			formStatus						: 'SAVE_DRAFT',
			name 							: 'Demo Name',
			fathersName 					: 'Demo Father Name',
			maritalStatus 					: 'S',
			accountNumber 					: '123456789',
			permanentAddress 				: 'Demo Address Line 1, \nDemo Address Line 2, \nDemo Address Line 3',
			temporaryAddress 				: 'Demo Temporary Address Line 1, \nDemo Temporary Address Line 2, \nDemo Temporary Address Line 3',
			gender 							: 'M',
			temporaryAddressSameAsPermament : 'N',
			dateOfBirth 					: new Date(),
			dateOfJoiningEPF 				: new Date(),
			dateOfJoiningEPS 				: new Date(),
			employeePFNominationDetailsList	: [
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '1st Demo Name and Address for relation',
					relationship			: '1st Demo Relation',
					dateOfBirth				: new Date(),
					percentage				: 89,
					guardian				: '1st Demo Guardian',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '2nd Demo Name and Address for relation',
					relationship			: '2nd Demo Relation',
					dateOfBirth				: new Date(),
					percentage				: 11,
					guardian				: '2nd Demo Guardian',
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			],
			widowChildrenPensionNomineeList	: [
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					name 					: '1st Demo Name',
					address					: '1st Demo Address',
					dateOfBirth				: new Date(),
					relationship			: '1st Demo Relation',
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					name 					: '2nd Demo Name',
					address					: '2nd Demo Address',
					dateOfBirth				: new Date(),
					relationship			: '2nd Demo Relation',
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			],
			onlyWidowPensionNomineeList	: [
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '1st Demo Name and Address for relation',
					relationship			: '1st Demo Relation',
					dateOfBirth				: new Date(),
					deleteRecord			: false // Mark this true if you would like to delete this record
				},
				{
					//id						:  27, // Do not send this attribute if this is a new record else send the existing value which was fetched while retrieving the form
					nameAndAddress 			: '2nd Demo Name and Address for relation',
					relationship			: '2nd Demo Relation',
					dateOfBirth				: new Date(),
					deleteRecord			: false // Mark this true if you would like to delete this record
				}
			]
		};
	return form;
}

$('#getForm11').on('click', function() {
	callWebservice('/rest/form-11/getForm', $('#empId11').val());
}); 

$('#saveForm11').on('click', function() {
	callWebservice('/rest/form-11/saveForm', encodeObjectAsJSON(getForm11()));
}); 

$('#getFormF').on('click', function() {
	callWebservice('/rest/form-F/getForm', $('#empIdF').val());
}); 

$('#saveFormF').on('click', function() {
	callWebservice('/rest/form-F/saveForm', encodeObjectAsJSON(getFormF()));
}); 

$('#getForm2').on('click', function() {
	callWebservice('/rest/form-2/getForm', $('#empId2').val());
}); 

$('#saveForm2').on('click', function() {
	callWebservice('/rest/form-2/saveForm', encodeObjectAsJSON(getForm2()));
}); 

$('#downloadForm11').on('click', function() {
	$('#downloadEmpId11').val($('#empId11').val());
	document.getElementById('downloadFileForm11').submit();
}); 

$('#downloadFormF').on('click', function() {
	$('#downloadEmpIdF').val($('#empIdF').val());
	document.getElementById('downloadFileFormF').submit();
}); 

$('#downloadForm2').on('click', function() {
	$('#downloadEmpId2').val($('#empId2').val());
	document.getElementById('downloadFileForm2').submit();
}); 

$('#downloadReport').on('click', function() {
	$('#downloadEmpIdAdmin').val($('#empIdAdmin').val());
	document.getElementById('downloadReportAdmin').submit();
}); 

$('#getCountryList').on('click', function() {
	callWebservice('/rest/commons/getCountryList', '');
}); 

$('#sendEmail').on('click', function() {
	callWebservice('/rest/commons/getStateList', '');
});
