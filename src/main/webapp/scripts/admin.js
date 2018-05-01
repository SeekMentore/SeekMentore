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
	$('#salutationText').html('Hello ' + recepientName + ',');
}

function sendEmail() {
	var form = document.getElementById('email-form');
	form.action = ctxPath + '/rest/admin/sendEmail';
	$('#recepientEmailId').val($('#recepientEmailId-text').html());
	$('#emailBody').val($('#salutationText').html()+'<br/><br/><p>'+$('#emailText').val().replace(/\n/g, '\n<br/>')+'</p><br/><br/>Thanks & Regards,<br/>'+$('#email-regards-username').html()+'<br/><Company Contact Information>');
	form.submit();
	showNotificationModal('Your mail would be sent in background.<br/>Please continue with your work.', true);
	resetEmail();
}

function resetEmail() {
	var form = document.getElementById('email-form');
	form.reset();
}

callWebservice('/rest/commons/getUser', null, showUserInfo);