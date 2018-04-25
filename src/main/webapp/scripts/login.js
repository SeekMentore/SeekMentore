var form = document.getElementById('signin_form');
function authenticate() {
	form.action = ctxPath + '/rest/login/validateCredential';
	form.submit();
}

if (queryParams['message'] != null) {
	alert ('Login failed. Please try again.');
}