var user;
function showUserInfo(response) {
	user = response;
	$('#user-name').html($('#user-name').html() + user.name);
}

/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function toggleMenu() {
    document.getElementById('menu-Dropdown').classList.toggle('show');
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    if (null != dropdowns && dropdowns.length > 0) {
    	var i;
    	for (i = 0; i < dropdowns.length; i++) {
    		var openDropdown = dropdowns[i];
    		if (openDropdown.classList.contains('show')) {
    			openDropdown.classList.remove('show');
    		}
    	}
    }
  }
}

callWebservice('/rest/commons/getUser', null, showUserInfo);