var user;
function showUserInfo(response) {
	user = response;
	$('#employee-name').html($('#employee-name').html() + user.name);
}

function showValue(val) {
	return (null != val) ? val : '';
}

callWebservice('/rest/commons/getUser', null, showUserInfo);