var form = document.getElementById('signin_form');
function authenticate() {
	form.action = ctxPath + '/rest/login/validateCredential';
	form.submit();
}

if (queryParams['message'] != null) {
	showNotificationModal('Login failed.<br/>Please try again.', false);
}