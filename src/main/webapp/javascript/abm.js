var host="localhost";
var port="4567"

	
// *********SONG ABM ********* 		
/**
* songAdd
* Description: This method allows to create a song in the database 
* with the data 'name' ,'duration' and 'albumTitle' filled in the form
* (the values 'name' and 'albumTitle' can't be empty).
*/	
function songAdd() { 
	   /*  post("/songs/",(req,res)->songController.create(req, res)); */
	
	   var name = encodeURIComponent(document.forms["addSong"]["name"].value);
	   var duration = encodeURIComponent(document.forms["addSong"]["duration"].value);
	   var titleAlb = encodeURIComponent(document.forms["addSong"]["albumTitle"].value);
	   
	   var params ="name="+name+"&duration="+duration+"&albumTitle="+titleAlb;
	   
	   var xhttp;
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/songs/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert("The song was successfully created");
	      }
	   };
	   xhttp.send(params);
}


/**
* songDelete
* Description: This method allows to remove a song from the database
* with the data 'songID' filled in the form
*/	
function songDelete() { 
     /* delete("/songs/:id",(req, res) -> songController.remove(req, res)); */	
	
	   window.alert("It does not work yet"); 
	/*   var name = encodeURIComponent(document.forms["deleteSong"]["name"].value);
	   var duration = encodeURIComponent(document.forms["deleteSong"]["duration"].value);
	   var titleAlb = encodeURIComponent(document.forms["deleteSong"]["albumTitle"].value);
	   
	   //I get the song(i need the id of the song)
	   var http;
	   http = new XMLHttpRequest();
	   http.open("GET", "http://"+host+":"+port+"/bands/findbyname"+name, true);
	   http.onreadystatechange = function() {
	      if (http.readyState == 4 && http.status == 200) {
			 var resp = JSON.parse(http.responseText);
			 if((resp == null)||(resp == "")){ 
				 window.alert("The song does not exist")
				 return;
			 }
			 //I remove the song
			 var params ="id="+resp.idsong;
			 var xhttp;
			 xhttp = new XMLHttpRequest();
			 //xhttp.open("DELETE", "http://"+host+":"+port+"/songs/"resp.idsong, true);
			 xhttp.open("POST", "http://"+host+":"+port+"/songs/"+"?"+params, true);
			 xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			 xhttp.setRequestHeader("Content-length", params.length);
			 xhttp.setRequestHeader("Connection", "close")
			 xhttp.onreadystatechange = function() {
			 if (xhttp.readyState == 4 && xhttp.status == 200) {
			    	  window.alert(xhttp.responseText);
			      }
			   };
			   xhttp.send(params);	 
	      }
	   };   */   
}


/**
* songModify
* Description: This method allows to modify a song in the database 
* with the data ''songID','name' ,'duration' and 'albumTitle' filled in the form
* (the value 'songID' can't be empty).
*/	
function songModify() {
	   /* put("/songs/:id",(req,res)->songController.update(req,res)); */
	/*
	   
	   var id = encodeURIComponent(document.forms["modifySong"]["id"].value);
	   var name = encodeURIComponent(document.forms["modifySong"]["name"].value);
	   var duration = encodeURIComponent(document.forms["modifySong"]["duration"].value);
	   var titleAlb = encodeURIComponent(document.forms["modifySong"]["albumTitle"].value);
	   
	   
	   window.alert("name");
	   var params ="id="+id+"&name="+name+"&duration="+duration+"&albumTitle="+titleAlb;
	   var xhttp;
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/songs/"+id, true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   
	   xhttp.onreadystatechange = function() {
	   	window.alert(xhttp.status);
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}

//**************ALBUM ABM*************
/**
 * albumAdd
 * Description: This method allows to create a album in the database 
 * with the data 'name' and 'release date' filled in the form (the value 'name' can't be empty).
 */
function albumAdd() {
	   /* post("/albums/", (req, res) -> albumController.create(req, res)); */
	
	   var title = encodeURIComponent(document.forms["addAlbum"]["title"].value);
	   var date = encodeURIComponent(document.forms["addAlbum"]["release_date"].value);
	   //falta banda
	   var params ="title="+title+"&release_date="+date;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/albums/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert("The album was successfully created");
	      }
	   };
	   xhttp.send(params);
}


/**
 * albumDelete
 * Description: This method allows to remove an album from the database
 * with the data 'AlbumID' filled in the form (the value 'AlbumID' can't be empty).
 */
function albumDelete() {
	   /* delete("/albums/:id", (req, res) -> albumController.delete(req, res)); */
	
	   window.alert("It does not work yet");
	  /* var title = encodeURIComponent(document.forms["deleteAlbum"]["title"].value);
	   var date = encodeURIComponent(document.forms["deleteAlbum"]["release_date"].value);
	   //falta banda
	   var params ="title="+title+"&release_date="+date;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/albums/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}

/**
 * albumModify
 * Description: This method allows to modify a album in the database 
 * with the data 'AlbumID','title' and 'release_date' filled in the form (the value 'AlbumID' can't be empty).
 */
function albumModify() {
	  /* put("/albums/:id", (req, res) -> albumController.update(req, res)); */
	
	   window.alert("It does not work yet");
	   /*var title = encodeURIComponent(document.forms["modifyAlbum"]["id"].value);
	   var title = encodeURIComponent(document.forms["modifyAlbum"]["title"].value);
	   var date = encodeURIComponent(document.forms["modifyAlbum"]["release_date"].value);
	   //falta banda
	   var params ="title="+title+"&release_date="+date;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/albums/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}


//*******************ARTIST ABM***************
/**
 * artistAdd
 * Description: This method allows to create a artist in the database 
 * with the data 'name','surname' and 'nickname' filled in the form (All values can not be empty at the same time).
 */
function artistAdd() {
	   /* post("/artists/",(req,res)->artistController.createArtist(req,res)); */
     
	   var name = encodeURIComponent(document.forms["addArtist"]["name"].value);
	   var surname = encodeURIComponent(document.forms["addArtist"]["surname"].value);
	   var nickname = encodeURIComponent(document.forms["addArtist"]["nickname"].value); 
	   if((name == "") && (surname == "") && (nickname == "")){
			window.alert("All fields can't be empty\nPlease insert a value");
			return;
	   }
	   var params;
	   if(name !=""){params ="name="+name+"&";}
	   else{params ="name="+null+"&";}
	   
	   if(surname !=""){params = params+"surname="+surname+"&";}
	   else{params = params+"surname="+null+"&";}
	   
	   if(nickname !=""){params = params+"nickname="+nickname;}
	   else{params = params+"nickname="+null;}
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/artists/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert("The artist was successfully created");
	      }
	   };
	   xhttp.send(params);
}


/**
 * artistDelete
 * Description: This method allows to remove an artist from the database
 * with the data 'artisID'filled in the form (the value 'artisID' can not be empty).
 */
function artistDelete() {
      /* delete("/artists/:id",(req,res)->artistController.deleteArtist(req,res));*/

	   window.alert("It does not work yet");
	 /*  var name = encodeURIComponent(document.forms["deleteArtist"]["name"].value);
	   var surname = encodeURIComponent(document.forms["deleteArtist"]["surname"].value);
	   var nickname = encodeURIComponent(document.forms["deleteArtist"]["nickname"].value); 
	   if((name == "") && (surname == "") && (nickname == "")){
			window.alert("All fields can't be empty\nPlease insert a value");
			return;
	   }
	   var params ="name="+name+"&surname="+surname+"&nickname="+nickname;

	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/albums/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}

/**
 * artistModify
 * Description: This method allows to modify an artist in the database 
 * with the data 'artisID','name','surname' and 'nickname' filled in the form (the value 'artisID' can not be empty).
 */
function artistModify() {
	   /*  put("/artists/:id",(req,res)->artistController.updateArtist(req,res)); */
	
	   /*
	   var id = encodeURIComponent(document.forms["modifyArtist"]["id"].value);
	   var name = encodeURIComponent(document.forms["modifyArtist"]["name"].value);
	   var surname = encodeURIComponent(document.forms["modifyArtist"]["surname"].value);
	   var nickname = encodeURIComponent(document.forms["modifyArtist"]["nickname"].value); 
	   if((name == "") && (surname == "") && (nickname == "")){
			window.alert("All fields can't be empty\nPlease insert a value");
			return;
	   }
	   var params ="id="+id+"&name="+name+"&surname="+surname+"&nickname="+nickname;

	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/artists/"+id, true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	   	window.alert(xhttp.status);
	      if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}

//**************BAND ABM************
/**
 * bandAdd
 * Description: This method allows to create a band in the database 
 * with the data 'name' and 'genre' filled in the form (no one of these values can be empty).
 */
function bandAdd() {
	   /*  post("/bands/",(req, res) -> bands.createBand(req, res));  */
	
	   var name = encodeURIComponent(document.forms["addBand"]["name"].value);
	   var genre = encodeURIComponent(document.forms["addBand"]["genre"].value); 
	
	   var params ="name="+name+"&genre="+genre;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/bands/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (this.readyState == 4 && this.status == 200) {
	    	  window.alert("The band was successfully created");
	      }
	   };
	   xhttp.send(params);
}

/**
 * bandDelete
 * Description: This method allows to remove a  band from the database 
 * with the data 'bandID', 'name' and 'genre' filled in the form (the value 'bandID' can't be empty).
 */
function bandDelete() {
	  /*  delete("/bands/:name",(req, res) -> bands.deleteBand(req, res));  */	
	
	  window.alert("It does not work yet");
	  
	  /* var name = encodeURIComponent(document.forms["deleteBand"]["name"].value);
	   var genre = encodeURIComponent(document.forms["deleteBand"]["genre"].value); 
	
	   var params ="name="+name+"&genre="+genre;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/bands/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (this.readyState == 4 && this.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}


/**
 * bandmodify
 * Description: This method allows to modify a band in the database 
 * with the data 'bandID','name' and 'genre' filled in the form (the value bandID can be empty).
 */
function bandmodify() {
	   /*  put("/bands",(req, res) -> bands.updateBand(req, res)); */
	
	   window.alert("It does not work yet");
	  /*var name = encodeURIComponent(document.forms["deleteBand"]["id"].value);
	   var name = encodeURIComponent(document.forms["modifyBand"]["name"].value);
	   var genre = encodeURIComponent(document.forms["modifyBand"]["genre"].value); 
	
	   var params ="name="+name+"&genre="+genre;
	   
	   var xhttp; 
	   xhttp = new XMLHttpRequest();
	   xhttp.open("POST", "http://"+host+":"+port+"/bands/", true);
	   xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xhttp.setRequestHeader("Content-length", params.length);
	   xhttp.setRequestHeader("Connection", "close")
	   xhttp.onreadystatechange = function() {
	      if (this.readyState == 4 && this.status == 200) {
	    	  window.alert(xhttp.responseText);
	      }
	   };
	   xhttp.send(params);*/
}


