function uploadDocuments() {
	var form = document.getElementById('document-upload-form');
	form.action = ctxPath + '/rest/tutor/uploadDocuments';
	form.submit();
	showNotificationModal('Your documents will be uploaded in background', true);
	resetDocuments();
}

function resetDocuments() {
	var form = document.getElementById('document-upload-form');
	form.reset();
}

