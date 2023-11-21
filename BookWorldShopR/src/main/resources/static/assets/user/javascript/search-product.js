function search() {
	var search = $("#search").val();
	var url = "/search" + "?namePrd=" + search;
	//if(search !== ""){
	//	url = url + "?q=" + search; 
	//}
	window.location.href = url;
}