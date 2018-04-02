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

function getApplicationToBecomeTutor() {
	var form = {
			dateOfBirth 				: new Date(),
			contactNumber				: $('#contact').val(),
			emailId 					: $('#email').val(),
			firstName 					: 'Shantanu',
			lastName 					: 'Mukherjee',
			gender 						: 'M',
			qualification 				: 'B-Tech',
			primaryProfession 			: 'Software Expert',
			transportMode 				: '4-W',
			teachingExp 				: '3',
			locations 					: 'Z1-L2;Z1-L9;Z3-L34',
			preferredTimeToCall 		: 'T2;T4',
			additionalDetails 			: 'I am awesome!!!',
			isContacted 				: 'N',
			isAuthenticationVerified 	: null,
			isToBeRecontacted 			: null,
			isSelected 					: null
		};
	return form;
}

$('#testEmail').on('click', function() {
	callWebservice('/rest/publicaccess/testEmail');
}); 

$('#becomeTutor').on('click', function() {
	callWebservice('/rest/publicaccess/becomeTutor', encodeObjectAsJSON(getApplicationToBecomeTutor()));
}); 
