var form = document.getElementById('signin_form');
function authenticate() {
	form.action = ctxPath + '/rest/login/validateCredential';
	form.submit();
}