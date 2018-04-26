var tutorListMap = new Object();
tutorListMap.selectedGrid = 'none';
function createTutorNavigatorObject(tutorListResponse) {
	var obj =	{
		currentIndex 	: 0,
		tutorList		: tutorListResponse,
		
		getCurrentTutorRecord	: function() {
			if (null != this.tutorList && this.tutorList.length > 0)
				return this.tutorList[this.currentIndex];
			else 
				null;
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
		}
	}
	return obj;
}

function showNonContactedTutorRecords(response) {
	tutorListMap['non_contacted'] = createTutorNavigatorObject(response);
	var html ='';
	for (var i=0;i < tutorListMap['non_contacted'].tutorList.length; i++) {
		var tutorObj = tutorListMap['non_contacted'].tutorList[i];
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularTutorRecord(\'non_contacted\', '+i+')">'+showValue(tutorObj.firstName)+' '+showValue(tutorObj.lastName)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#become-tutor-records').html(html);
}

function previousTutorRecord() {
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].previousTutorRecord());
}

function nextTutorRecord() {
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].nextTutorRecord());
}

function getParticularTutorRecord(gridName, recordNumber) {
	tutorListMap.selectedGrid = gridName;
	openTutorRecord(tutorListMap[tutorListMap.selectedGrid].getParticularTutorRecord(recordNumber));
}

function hideTutorAllRecordsPage() {
	$('#selected-record-div').removeClass('noscreen');
	$('#all-records-div').addClass('noscreen');
}

function showTutorAllRecordsPage() {
	$('#selected-record-div').addClass('noscreen');
	$('#all-records-div').removeClass('noscreen');
}

function openTutorRecord(tutorObj) {
	hideTutorAllRecordsPage();
	if (null != tutorObj) {
		$('#APPLICATION_DATE').html(showValue(tutorObj.applicationDate));
		$('#APPLICATION_STATUS').html(showValue(tutorObj.applicationStatus));
		$('#DATE_OF_BIRTH').html(showValue(tutorObj.dateOfBirth));
		$('#CONTACT_NUMBER').html('<a href="tel:'+showValue(tutorObj.contactNumber)+'">'+showValue(tutorObj.contactNumber)+'</a>');
		$('#EMAIL_ID').html('<a href="mailto:'+showValue(tutorObj.emailId)+'">'+showValue(tutorObj.emailId)+'</a>');
		$('#FIRST_NAME').html(showValue(tutorObj.firstName));
		$('#LAST_NAME').html(showValue(tutorObj.lastName));
		$('#GENDER').html(showValue(tutorObj.gender));
		$('#QUALIFICATION').html(showValue(tutorObj.qualification));
		$('#PRIMARY_PROFESSION').html(showValue(tutorObj.primaryProfession));
		$('#TRANSPORT_MODE').html(showValue(tutorObj.transportMode));
		$('#TEACHING_EXP').html(showValue(tutorObj.teachingExp));
		$('#STUDENT_GRADE').html(showValue(tutorObj.studentGrade));
		$('#SUBJECTS').html(showValue(tutorObj.subjects));
		$('#LOCATIONS').html(showValue(tutorObj.locations));
		$('#PREFERRED_TIME_TO_CALL').html(showValue(tutorObj.preferredTimeToCall));
		$('#ADDITIONAL_DETAILS').html(showValue(tutorObj.additionalDetails));
		$('#IS_CONTACTED').html(showValue(tutorObj.isContacted));
		$('#WHO_CONTACTED').html(showValue(tutorObj.whoContacted));
		$('#CONTACTED_DATE').html(showValue(tutorObj.contactedDate));
		$('#CONTACTED_REMARKS').html(showValue(tutorObj.contactedRemarks));
		$('#IS_AUTH_VERIFIED').html(showValue(tutorObj.isAuthenticationVerified));
		$('#WHO_VERIFIED').html(showValue(tutorObj.whoVerified));
		$('#VERIFICATION_DATE').html(showValue(tutorObj.verificationDate));
		$('#VERIFICATION_REMARKS').html(showValue(tutorObj.verificationRemarks));
		$('#IS_TO_BE_RECONTACTED').html(showValue(tutorObj.isToBeRecontacted));
		$('#WHO_SUGGESTED_FOR_RECONTACT').html(showValue(tutorObj.whoSuggestedForRecontact));
		$('#SUGGESTION_DATE').html(showValue(tutorObj.suggestionDate));
		$('#SUGGESTION_REMARKS').html(showValue(tutorObj.suggestionRemarks));
		$('#WHO_RECONTACTED').html(showValue(tutorObj.whoRecontacted));
		$('#RECONTACTED_DATE').html(showValue(tutorObj.recontactedDate));
		$('#RECONTACTED_REMARKS').html(showValue(tutorObj.recontactedRemarks));
		$('#IS_SELECTED').html(showValue(tutorObj.isSelected));
		$('#WHO_SELECTED').html(showValue(tutorObj.whoSelected));
		$('#SELECTION_DATE').html(showValue(tutorObj.selectionDate));
		$('#SELECTION_REMARKS').html(showValue(tutorObj.selectionRemarks));
		$('#IS_REJECTED').html(showValue(tutorObj.isRejected));
		$('#WHO_REJECTED').html(showValue(tutorObj.whoRejected));
		$('#REJECTION_DATE').html(showValue(tutorObj.rejectionDate));
		$('#REJECTION_REMARKS').html(showValue(tutorObj.rejectionRemarks));
		$('#REJECTION_COUNT').html(showValue(tutorObj.rejectionCount));
		$('#RE_APPLIED').html(showValue(tutorObj.reApplied));
		$('#PREVIOUS_APPLICATION_DATE').html(showValue(tutorObj.previousApplicationDate));
		$('#RECORD_LAST_UPDATED').html(showValue(tutorObj.recordLastUpdated));
	} 
}

function takeAction(button) {
	var data = { 
			gridName: tutorListMap.selectedGrid, 
			button : button, 
			tentativeTutorId : tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().tentativeTutorId,
			remarks : $('#remarks').val()
	}
	callWebservice('/rest/admin/takeActionOnRegisteredTutors', data, null, null, null, 'application/x-www-form-urlencoded');
}

callWebservice('/rest/admin/displayRejectedTutorRegistrations', null, showNonContactedTutorRecords);