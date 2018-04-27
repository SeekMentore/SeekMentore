var user;
function showUserInfo(response) {
	user = response;
	$('#employee-name').html($('#employee-name').html() + user.name);
}

function showValue(val) {
	return (null != val) ? (('N' != val) ? (('Y' != val) ? val : 'Yes') : 'No') : '';
}

callWebservice('/rest/commons/getUser', null, showUserInfo);