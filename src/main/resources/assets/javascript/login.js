/** Called when login.html page is loaded*/
$( document ).ready(function() {
	
	// placeholders localization
	$("#username").attr("placeholder", "Username (Bosch Active Directory ID)");
	$("#pass").attr("placeholder", "Password");
	
	if(sessionMgr.isSetUserContext()) { // prevent double login for the same device
		window.location.href = APP_BASE_URL;
	}		
	
	// draw navbar
	drawBasicNavbar("SALSSA2", APP_BASE_URL);

	// show placehodlers in IE9
	$(function() {
		$('input, text').placeholder();
	});
	
});

