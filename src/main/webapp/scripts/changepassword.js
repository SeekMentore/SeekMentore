function changePassword() {
	var data = { 
			oldPassword			: $('#old-password').val(), 
			newPassword			: $('#new-password').val(), 
			retypenewPassword	: $('#retypenew-password').val()
	}
	successMessage = 'Successfully changed password.';
	callbackAfterCommonSuccess = clearChangePasswordForm;
	callWebservice('/rest/login/changePassword', data, null, null, null, 'application/x-www-form-urlencoded');
}

function clearChangePasswordForm() {
	var form = document.getElementById('changepassword_form');
	form.reset();
}