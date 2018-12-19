var ctxPath = '/seekmentore';
var successMessage = '';
var callbackAfterCommonSuccess = null;

commonErrorHandler = function(error) {
	showNotificationModal('Connection lost.<br/>Please check your network connection and refresh the page.', false);
}

commmonSuccessHandler = function(response) {
	var failure = response.FAILURE;
	if (failure) {
		showNotificationModal(response.FAILURE_MESSAGE, false);
		return;
	}
	showNotificationModal(successMessage, true);
	if (null != callbackAfterCommonSuccess) {
		callbackAfterCommonSuccess(response);
		callbackAfterCommonSuccess = null;
	}
}

function encodeObjectAsJSON(object) {
	return null != object ? JSON.stringify(object) : null;
}

function decodeObjectFromJSON(json) {
	return null != json ? JSON.parse(json) : null;
}

function callWebservice(url, data, success, failure, method, contentType) {
	// Show Pop up loader 
	showLoader();
	$.ajax({
        url			: ctxPath + url,
        type		: ((null != method) ? method : 'POST'),
        data		: data,
        contentType	: ((null != contentType) ? contentType : 'application/json'),
        cache		: false,
        dataType	: 'json',
        success		: function(data) {
        				removeLoader();
        				var response = decodeObjectFromJSON(data.response)
			        	if (null != success) {
			        		success(response);
			        	} else {
			        		commmonSuccessHandler(response);
			        	}
		},
		error		: function(error) {
						removeLoader();
			        	if (null != failure) {
			        		failure(error);
			        	} else {
			        		commonErrorHandler(error);
			        	}
		}
    });
}

function showLoader() {
	if (null != $('#loader-popup-modal')) {
		$('#loader-popup-modal').removeClass('noscreen');
	}
}

function removeLoader() {
	if (null != $('#loader-popup-modal')) {
		$('#loader-popup-modal').addClass('noscreen');
	}
}

function showNotificationModal(message, isSuccess) {
	$('#notification-popup-modal').removeClass('noscreen');
	$('#notification-popup-model-content-section').html(message);
	if (isSuccess) {
		$('#alert-title').html('Success');
		
		$('#alert-title').addClass('successMessage');
		$('#alert-title').removeClass('failureMessage');
		
		$('#notification-popup-model-content-section').addClass('successMessage');
		$('#notification-popup-model-content-section').removeClass('failureMessage');
	} else {
		$('#alert-title').html('Error');
		
		$('#alert-title').addClass('failureMessage');
		$('#alert-title').removeClass('successMessage');
		
		$('#notification-popup-model-content-section').addClass('failureMessage');
		$('#notification-popup-model-content-section').removeClass('successMessage');
	}
}

function showNotificationModalWithModifiedHeader(message, header) {
	$('#notification-popup-modal').removeClass('noscreen');
	$('#notification-popup-model-content-section').html(message);
	$('#alert-title').html(header);
	
	$('#alert-title').addClass('successMessage');
	$('#alert-title').removeClass('failureMessage');
	
	$('#notification-popup-model-content-section').addClass('successMessage');
	$('#notification-popup-model-content-section').removeClass('failureMessage');
}

//Configure notification popup modal and register events
function closeNotificationPopUpModal() {
	$('#notification-popup-modal').addClass('noscreen');
}

window.onclick = function(event) {
	var modal = document.getElementById('notification-popup-modal');
	if (event.target == modal) {
		$('#notification-popup-modal').addClass('noscreen');
	}
}