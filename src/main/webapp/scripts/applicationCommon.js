var ctxPath = '/seekmentore';
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

function getApplicationToSubmitQuery() {
	var application = {
			queryRequestedDate 	: new Date(),
			queryStatus			: 'New',
			emailId 			: $('#submitQueryEmail').val(),
			queryDetails 		: 'This is just a simple query.',
			isContacted 		: 'N'
		};
	return application;
}

function getApplicationToFindTutor() {
	var application = {
			enquiryDate 		: new Date(),
			enquiryStatus		: 'New',
			name 				: 'Dummy Parent',
			contactNumber 		: $('#findTutorContact').val(),
			emailId 			: $('#findTutorEmail').val(),
			studentGrade 		: 'MS-7',
			subjects 			: '12-P;11-C;10-B',
			preferredTimeToCall : 'T2;T4',
			additionalDetails 	: 'Nothing much to provide.',
			subscribedWithUs	: 'N',
			isContacted 		: 'N'
		};
	return application;
}

function getApplicationToBecomeTutor() {
	var form = {
			applicationDate            	: new Date(),
			applicationStatus			: 'New',
			dateOfBirth 				: new Date(),
			contactNumber				: $('#becomeTutorContact').val(),
			emailId 					: $('#becomeTutorEmail').val(),
			firstName 					: 'Shantanu',
			lastName 					: 'Mukherjee',
			gender 						: 'M',
			qualification 				: 'B-Tech',
			primaryProfession 			: 'Software Expert',
			transportMode 				: '4-W',
			teachingExp 				: '3',
			subjects					: '12-P;11-C;10-B',
			locations 					: 'Z1-L2;Z1-L9;Z3-L34',
			preferredTimeToCall 		: 'T2;T4',
			additionalDetails 			: 'I am awesome!!!',
			isContacted 				: 'N'
		};
	return form;
}

$('#submitQuery').on('click', function() {
	callWebservice('/rest/publicaccess/submitQuery', encodeObjectAsJSON(getApplicationToSubmitQuery()));
}); 

$('#findTutor').on('click', function() {
	callWebservice('/rest/publicaccess/findTutor', encodeObjectAsJSON(getApplicationToFindTutor()));
}); 

$('#becomeTutor').on('click', function() {
	callWebservice('/rest/publicaccess/becomeTutor', encodeObjectAsJSON(getApplicationToBecomeTutor()));
}); 
