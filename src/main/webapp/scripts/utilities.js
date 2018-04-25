var ctxPath = '/seekmentore';

commonErrorHandler = function(error) {
	$('#responseDiv').html(encodeObjectAsJSON(error));
}

commmonSuccessHandler = function(response) {
	$('#responseDiv').html(encodeObjectAsJSON(response));
}

function encodeObjectAsJSON(object) {
	return null != object ? JSON.stringify(object) : null;
}

function decodeObjectFromJSON(json) {
	return null != json ? JSON.parse(json) : null;
}

function callWebservice(url, data, success, failure, method, contentType) {
	// Show Pop up loader 
	if (null != $('#loader-popup-modal')) {
		$('#loader-popup-modal').removeClass('noscreen');
	}
	$.ajax({
        url			: ctxPath + url,
        type		: ((null != method) ? method : 'POST'),
        data		: data,
        contentType	: ((null != contentType) ? contentType : 'application/json'),
        cache		: false,
        dataType	: 'json',
        success		: function(data) {
        				if (null != $('#loader-popup-modal')) {
        					$('#loader-popup-modal').addClass('noscreen');
        				}
        				var response = decodeObjectFromJSON(data.response)
			        	if (null != success) {
			        		success(response);
			        	} else {
			        		commmonSuccessHandler(response);
			        	}
		},
		error		: function(error) {
						if (null != $('#loader-popup-modal')) {
							$('#loader-popup-modal').addClass('noscreen');
						}
			        	if (null != failure) {
			        		failure(error);
			        	} else {
			        		commonErrorHandler(error);
			        	}
		}
    });
}

var queryParams = new Object();
function readGetParameters() {
	var url = location.href;
	var queryParamStart = url.indexOf('?');
	if (queryParamStart != -1) {
		var paramsString = url.substring(queryParamStart + 1);
		var paramArray = paramsString.split('&');
		for (var i = 0; i < paramArray.length; i++) {
			var paramString = paramArray[i];
			var paramValueArray = paramString.split('=');
			queryParams[paramValueArray[0]] = paramValueArray[1];
		}
	}
}

var form = document.getElementById('signout_form');
function logout() {
	form.action = ctxPath + '/rest/login/logout';
	form.submit();
}

readGetParameters();