var user;
function showUserInfo(response) {
	user = response;
	$('#employee-name').html($('#employee-name').html() + user.name);
}

function showValue(val) {
	return (null != val) ? val : '';
}

function replaceEmailPanelStaticHolders() {
	$('#email-regards-username').html(user.name);
}

function replacePlaceHoldersForEmailPanel(recepientMailId, recepientName) {
	replaceEmailPanelStaticHolders();
	$('#recepientEmailId-text').html(recepientMailId);
	$('#salutationText').html(recepientName);
}

function sendEmail() {
	var form = document.getElementById('email-form');
	form.action = ctxPath + '/rest/admin/sendEmail';
	$('#recepientEmailId').val($('#recepientEmailId-text').html());
	$('#email-salutation-name').val($('#salutationText').html());
	form.submit();
	showNotificationModal('Your mail would be sent in background.<br/>Please continue with your work.', true);
	resetEmail();
}

function resetEmail() {
	var form = document.getElementById('email-form');
	form.reset();
}

callWebservice('/rest/commons/getUser', null, showUserInfo);