var customerListMap = new Object();
customerListMap.selectedGrid = 'none';

function createCustomerNavigatorObject(customerListResponse) {
	var obj =	{
		currentIndex 	: 0,
		customerList	: customerListResponse,
		
		getCurrentCustomerRecord	: function() {
			if (null != this.customerList && this.customerList.length > 0)
				return this.customerList[this.currentIndex];
			else 
				return null;
		},
		
		previousCustomerRecord		: function() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.customerList.length - 1;
			}
			return this.getCurrentCustomerRecord();
		},
		
		nextCustomerRecord			: function() {
			if (this.currentIndex < this.customerList.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0;
			}
			return this.getCurrentCustomerRecord();
		},
		
		getParticularCustomerRecord	: function(recordNumber) {
			if (recordNumber >= 0 && recordNumber < this.customerList.length) {
				this.currentIndex = recordNumber;
				return this.getCurrentCustomerRecord();
			}
			return null;
		},
		
		getList : function() {
			return this.customerList;
		},
		
		getRecordCount : function() {
			if (null != this.customerList && this.customerList.length > 0)
				return this.customerList.length;
			else 
				return 0;
		},
		
		getListItem : function(index) {
			if (null != this.customerList && this.customerList.length > 0)
				return this.customerList[index];
			else 
				return null;
		}
	}
	return obj;
}

function showCustomerWithPendingEnquiries(response) {
	window.pending = response;
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'customers-with-pending-enquiries');
}

function showCustomerWithMappedEnquiries(response) {
	window.mapped = response;
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'customers-with-mapped-enquiries');
}

function showCustomerWithAbandonedEnquiries(response) {
	window.abandoned = response;
	prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, 'customers-with-abandoned-enquiries');
}

function prepareHTMLToFillGridAndSetMapNavigatorObjectList(response, gridName) {
	customerListMap[gridName] = createCustomerNavigatorObject(response);
	var customerNavigatorObject = customerListMap[gridName];
	var html ='';
	for (var i=0;i < customerNavigatorObject.getRecordCount(); i++) {
		var customerObj = customerNavigatorObject.getListItem(i);
		html +='<tr>';
		html += '<td><a href="javascript:void(0);" onclick="getParticularCustomerRecord(\''+gridName+'\', '+i+')">'+showValue(customerObj.name)+ '</a></td>';
		html += '<td><a href="mailto:'+showValue(customerObj.emailId)+'">'+showValue(customerObj.emailId)+'</a></td>';
		html += '<td><a href="tel:'+showValue(customerObj.contactNumber)+'">'+showValue(customerObj.contactNumber)+'</a></td>';
		html +='</tr>';
	}
	$('#'+gridName+'-records').html(html);
	$('#'+gridName+'-total-records').html('Total Records = ' + customerNavigatorObject.getRecordCount());
}

function hideAllCustomersEnquiriesPage() {
	$('#particular-customer-all-enquiries-div').removeClass('noscreen');
	$('#all-customers-with-enquiries-div').addClass('noscreen');
}

function showAllCustomersEnquiriesPage() {
	$('#all-customers-with-enquiries-div').removeClass('noscreen');
	$('#particular-customer-all-enquiries-div').addClass('noscreen');
}

function openTutorRecord(customerObj) {
	hideAllCustomersEnquiriesPage();
	if (null != customerObj) {
		$('#NAME').html(showValue(customerObj.name));
		$('#CONTACT_NUMBER').html(showValue(customerObj.contactNumber));
		$('#EMAIL_ID').html(showValue(customerObj.emailId));
		$('#ADDRESS_DETAILS').html(showValue(customerObj.addressDetails));
		$('#ADDITIONAL_DETAILS').html(showValue(customerObj.additionalDetails));
		
		replacePlaceHoldersForEmailPanel(showValue(customerObj.emailId), showValue(customerObj.name));
	} 
}

function previousCustomerRecord() {
	openTutorRecord(customerListMap[customerListMap.selectedGrid].previousCustomerRecord());
}

function nextCustomerRecord() {
	openTutorRecord(customerListMap[customerListMap.selectedGrid].nextCustomerRecord());
}

function getParticularCustomerRecord(gridName, recordNumber) {
	customerListMap.selectedGrid = gridName;
	openTutorRecord(customerListMap[customerListMap.selectedGrid].getParticularCustomerRecord(recordNumber));
}

function loadGrids() {
	callWebservice('/rest/enquiry/displayCustomerWithPendingEnquiries', null, showCustomerWithPendingEnquiries);
	callWebservice('/rest/enquiry/displayCustomerWithMappedEnquiries', null, showCustomerWithMappedEnquiries);
	callWebservice('/rest/enquiry/displayCustomerWithAbandonedEnquiries', null, showCustomerWithAbandonedEnquiries);
}

function downloadReport() {
	var form = document.getElementById('downloadForm');
	//form.action = ctxPath + '/rest/admin/downloadAdminReportTutorRegistrations';
	//form.submit();
}

function downloadProfile() {
	var form = document.getElementById('downloadForm');
	//form.action = ctxPath + '/rest/admin/downloadAdminTutorRegistrationProfilePdf';
	$('#downloadForm-tentativeTutorId').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().tentativeTutorId);
	$('#downloadForm-name').val(tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().firstName + '_' + tutorListMap[tutorListMap.selectedGrid].getCurrentTutorRecord().lastName);
	//form.submit();
}