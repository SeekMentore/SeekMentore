var tutorNavigatorObject;

function createTutorNavigatorObject(tutorListResponse) {
	var obj =	{
		currentIndex 	: 0,
		tutorList		: tutorListResponse,
		
		getCurrentTutorRecord	: function() {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList[this.currentIndex];
			else 
				return null;
		},
		
		previousTutorRecord		: function() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.tutorList.length - 1;
			}
			return this.getCurrentTutorRecord();
		},
		
		nextTutorRecord			: function() {
			if (this.currentIndex < this.tutorList.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0;
			}
			return this.getCurrentTutorRecord();
		},
		
		getParticularTutorRecord	: function(recordNumber) {
			if (recordNumber >= 0 && recordNumber < this.tutorList.length) {
				this.currentIndex = recordNumber;
				return this.getCurrentTutorRecord();
			}
			return null;
		},
		
		getList : function() {
			return this.tutorList;
		},
		
		getRecordCount : function() {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList.length;
			else 
				return 0;
		},
		
		getListItem : function(index) {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList[index];
			else 
				return null;
		}
	}
	return obj;
}

function showRegisteredTutors(response) {
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response);
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectList(response) {
	tutorNavigatorObject = createTutorNavigatorObject(response);
	var html ='';
	for (var i=0;i < tutorNavigatorObject.getRecordCount(); i++) {
		var tutorObj = tutorNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorRecord('+i+')">'+showValue(tutorObj.name)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#tutor-records').html(html);
	$('#total-records').html('Total Records = ' + tutorNavigatorObject.getRecordCount());
}

function previousTutorRecord() {
	openTutorRecord(tutorNavigatorObject.previousTutorRecord());
}

function nextTutorRecord() {
	openTutorRecord(tutorNavigatorObject.nextTutorRecord());
}

function getParticularTutorRecord(recordNumber) {
	openTutorRecord(tutorNavigatorObject.getParticularTutorRecord(recordNumber));
}

function hideTutorAllRecordsPage() {
	$('#selected-record-div').removeClass('noscreen');
	$('#download-report-div').addClass('noscreen');
	$('#header-div').addClass('noscreen');
	$('#all-records-div').addClass('noscreen');
}

function showTutorAllRecordsPage() {
	$('#download-report-div').removeClass('noscreen');
	$('#header-div').removeClass('noscreen');
	$('#all-records-div').removeClass('noscreen');
	$('#selected-record-div').addClass('noscreen');
}

function openTutorRecord(tutorObj) {
	hideTutorAllRecordsPage();
	if (null != tutorObj) {
		$('#NAME').html(showValue(tutorObj.name));
		$('#DATE_OF_BIRTH').html(showValue(tutorObj.dateOfBirth));
		$('#CONTACT_NUMBER').html(showValue(tutorObj.contactNumber));
		$('#EMAIL_ID').html(showValue(tutorObj.emailId));
		$('#GENDER').html(showValue(tutorObj.gender));
		$('#QUALIFICATION').html(showValue(tutorObj.qualification));
		$('#PRIMARY_PROFESSION').html(showValue(tutorObj.primaryProfession));
		$('#TRANSPORT_MODE').html(showValue(tutorObj.transportMode));
		$('#TEACHING_EXP').html(showValue(tutorObj.teachingExp));
		$('#INTERESTED_STUDENT_GRADES').html(showValue(tutorObj.interestedStudentGrades));
		$('#INTERESTED_SUBJECTS').html(showValue(tutorObj.interestedSubjects));
		$('#COMFORTABLE_LOCATIONS').html(showValue(tutorObj.comfortableLocations));
		$('#PREFERRED_TEACHING_TYPE').html(showValue(tutorObj.preferredTeachingType));
		$('#ADDITIONAL_DETAILS').html(showValue(tutorObj.additionalDetails));
		
		replacePlaceHoldersForEmailPanel(showValue(tutorObj.emailId), showValue(tutorObj.name));
		
		var tutorDocuments = tutorObj.documents;
		$('#inputFilePhoto-absent').removeClass('noscreen');
		$('.inputFilePhoto-reminder').removeClass('noscreen');
		$('#inputFilePhoto-link').addClass('noscreen');
		$('.inputFilePhoto-action').addClass('noscreen');
		
		$('#inputFilePan-absent').removeClass('noscreen');
		$('.inputFilePan-reminder').removeClass('noscreen');
		$('#inputFilePan-link').addClass('noscreen');
		$('.inputFilePan-action').addClass('noscreen');
		
		$('#inputFileAadhaarCard-absent').removeClass('noscreen');
		$('.inputFileAadhaarCard-reminder').removeClass('noscreen');
		$('#inputFileAadhaarCard-link').addClass('noscreen');
		$('.inputFileAadhaarCard-action').addClass('noscreen');
		
		if (null != tutorDocuments && tutorDocuments.length > 0) {
			for (var i = 0; i < tutorDocuments.length; i++) {
				var filename = tutorDocuments[i].filename;
				if (filename == 'PROFILE_P1HOTO.jpg') {
					$('#inputFilePhoto-absent').addClass('noscreen');
					$('.inputFilePhoto-reminder').addClass('noscreen');
					$('#inputFilePhoto-link').removeClass('noscreen');
					$('.inputFilePhoto-action').removeClass('noscreen');
				} else if (filename == 'PAN_CARD.pdf') {
					$('#inputFilePan-absent').addClass('noscreen');
					$('.inputFilePan-reminder').addClass('noscreen');
					$('#inputFilePan-link').removeClass('noscreen');
					$('.inputFilePan-action').removeClass('noscreen');
				} else if (filename == 'AADHAAR_CARD.pdf') {
					$('#inputFileAadhaarCard-absent').addClass('noscreen');
					$('.inputFileAadhaarCard-reminder').addClass('noscreen');
					$('#inputFileAadhaarCard-link').removeClass('noscreen');
					$('.inputFileAadhaarCard-action').removeClass('noscreen');
				}
			}
		}
	} 
}

function downloadDocument(documentType) {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/tutor/downloadDocumentFromAdmin';
	$('#downloadForm-documentType').val(documentType);
	$('#downloadForm-tutorId').val(tutorNavigatorObject.getCurrentTutorRecord().tutorId);
	form.submit();
	if (documentType == 'PROFILE_PHOTO') {
		$('#inputFilePhoto-link').addClass('noscreen');
		$('#inputFilePhoto-downloading').removeClass('noscreen');
	} else if (documentType == 'PAN_CARD') {
		$('#inputFilePan-link').addClass('noscreen');
		$('#inputFilePan-downloading').removeClass('noscreen');
	} else if (documentType == 'AADHAAR_CARD') {
		$('#inputFileAadhaarCard-link').addClass('noscreen');
		$('#inputFileAadhaarCard-downloading').removeClass('noscreen');
	}
	window.setTimeout(
			function disableDownloadButton() {
				if (documentType == 'PROFILE_PHOTO') {
					$('#inputFilePhoto-link').removeClass('noscreen');
					$('#inputFilePhoto-downloading').addClass('noscreen');
				} else if (documentType == 'PAN_CARD') {
					$('#inputFilePan-link').removeClass('noscreen');
					$('#inputFilePan-downloading').addClass('noscreen');
				} else if (documentType == 'AADHAAR_CARD') {
					$('#inputFileAadhaarCard-link').removeClass('noscreen');
					$('#inputFileAadhaarCard-downloading').addClass('noscreen');
				}
			}, 8000)
}

function loadGrids() {
	callWebservice('/rest/tutor/registeredTutorsList', null, showRegisteredTutors);
}

function downloadReport() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/tutor/downloadReportRegisteredTutors';
	form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	form.action = ctxPath + '/rest/tutor/downloadRegisteredTutorProfilePdf';
	$('#downloadForm-tutorId').val(tutorNavigatorObject.getCurrentTutorRecord().tutorId);
	$('#downloadForm-name').val(tutorNavigatorObject.getCurrentTutorRecord().name);
	form.submit();
}