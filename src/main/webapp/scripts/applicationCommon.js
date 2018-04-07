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
			emailId 			: $('#submitQueryEmail').val(),
			queryDetails 		: 'This is just a simple query.',
			captchaResponse				: 'Dummy Captcha'
		};
	return application;
}

function getApplicationToFindTutor() {
	var application = {
			name 				: 'Dummy Parent',
			contactNumber 		: $('#findTutorContact').val(),
			emailId 			: $('#findTutorEmail').val(),
			studentGrade 		: 'MS-7',
			subjects 			: '12-P;11-C;10-B',
			preferredTimeToCall : 'T2;T4',
			additionalDetails 	: 'Nothing much to provide.',
			captchaResponse				: 'Dummy Captcha'
		};
	return application;
}

function getApplicationToBecomeTutor() {
	var form = {
			firstName 					: 'Shantanu',
			lastName 					: 'Mukherjee',
			dateOfBirth 				: new Date(),
			contactNumber				: $('#becomeTutorContact').val(),
			emailId 					: $('#becomeTutorEmail').val(),
			gender 						: 'M',
			qualification 				: 'B-Tech',
			primaryProfession 			: 'Software Expert1',
			transportMode 				: '4-W',
			teachingExp 				: '3',
			subjects					: '12-P;11-C;10-B',
			locations 					: 'Z1-L2;Z1-L9;Z3-L34',
			preferredTimeToCall 		: 'T2;T4',
			additionalDetails 			: 'I am awesome!!!',
			captchaResponse				: '03AJIzXZ60W9IDEBdt3fzxhd9-L368LL6CzZxXiZOAvp8vPpyIqfjSgRCHGxiMrvpzHg18NLyr07jH7Yc5v138RZOyVt-GX02RwYpwByjg-IDaztPr5ePrnUBAxmgTn5zm3iVmrTNQb2uUyx6ad-trfTx1beXN-TsP6edqdRo6-OlnGbxj8lMRogHZwXY9Y7Mo-4In_B-q9cPOnPHzCvUVqY9xCsWATLCqgtngYuYn3bW6bd_HKnkcea6lLj9GVTp7_jW430gydB3UKomKDbxiX6JigEiQANdsIC1nCh5c0lv7yRfmpD-dEGkL40KshTTrDX_U4yyjYx-EbIynLIk4T3hFIuFbQsHaVTYlWj_TemeL48Y7S8sO5OPLBR1nkB3Euo4YB-BaZ-F8GShjaEw1te7VQDeU05OoBQ'
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
