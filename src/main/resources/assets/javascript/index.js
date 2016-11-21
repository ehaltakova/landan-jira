/** Called when index.html page is loaded
*/

$( document ).ready(function() {
	
	// placeholders localization
	$("#title").attr("placeholder", "Title");
	$("#editTitle").attr("placeholder", "Title");
	$("#editFilename").attr("placeholder", "File Name");

	var authManager = new AuthManager(sessionMgr);

	// draw page navbar
	drawNavbar("SALSSA2", APP_BASE_URL, [], '.home-page-link', sessionMgr.getUserContext(), authManager);

	// prompt for changing password before accessing the application
	if(!sessionMgr.isAdminUser() && !sessionMgr.hasPermissions()) {
		window.location.href = APP_BASE_URL + "/user";
		return;
	}
	
	// show placehodlers in IE9
	$(function() {
		$('input, text').placeholder();
	});
			
	// load and display slide albums
	var slideAlbumsMgr = new SlideAlbumsManager();
	slideAlbumsMgr.getSlideAlbums();
	
});