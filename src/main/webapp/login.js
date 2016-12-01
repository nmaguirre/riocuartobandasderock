function loginAdmin() {
	var host ='localhost';
	var port ='4567';
	var http = new XMLHttpRequest();
	var url = "/login";
	var params = "name"+name+"&password"+password;

//Send the proper header information along with the request
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	http.open("GET", "http://"+host+":"+port+"url", true);
	http.onreadystatechange = function() {//Call a function when the state changes.
    if(http.readyState == 4 && http.status == 200) {
        alert(http.responseText);
    }
}
http.send(params);
