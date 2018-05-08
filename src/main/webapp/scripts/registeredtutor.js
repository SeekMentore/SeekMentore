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
	} 
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