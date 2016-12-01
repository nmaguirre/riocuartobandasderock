var host ='localhost';
var port ='4567';


function test(){
	var st = document.getElementsByName("songName")[0].value;
	window.alert(st);
	var sys = arbor.ParticleSystem(500, 40,1); 
    sys.parameters({gravity:true}); 
    sys.renderer = Renderer("#viewport");
	var resp = [{name:'Juan',duration:'hola'},{name:'Franco',duration:'chau'},{name:'Pedro',duration:'adios'}];
    for (var i=0; i<resp.length; i++){
       sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
       sys.addNode('duration'+i,{'color':'red','shape':'dot','label':resp[i].duration});
       sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
       
    }
    
    var resp ={name:'Juan',apellido:'Diaz',edad:'28'};
    var str = "";
    for (elem in resp)
    	  str = str + elem + ': ' + resp[elem]+'<br>';
    	  
    //window.alert(str);	  
    document.getElementById('show').innerHTML = str;	
    	  
}

//---AJAX Methods---
//**********************************************
//******************** SONG ********************
//**********************************************

/** 
 * songGetAll
 * This method allows to make a request to the database, searching and bringing all the songs found.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function songGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/songs", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     var resp = JSON.parse(xhttp.responseText);

	     //ArborJs
	     var sys = arbor.ParticleSystem(500, 40,1); // create the system with sensible repulsion/stiffness/friction
		 sys.parameters({gravity:true}); // use center-gravity to make the graph settle nicely (ymmv)
		 sys.renderer = Renderer("#viewport"); // our newly created renderer will have its .init() method called shortly by sys...
		 if(Array.isArray(resp)){  //there are many songs
			 for (var i=0; i<resp.length; i++){
			    sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			    if(resp.duration != undefined){
			       sys.addNode('duration'+i,{'color':'red','shape':'square','label':resp[i].duration});
			       sys.addEdge('name'+i, 'duration'+i,{'color':'black'});  
			    }   
			 }
         }
		 else{  //there is only one song	
	        sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    if(resp.duration != undefined){
			   sys.addNode('duration',{'color':'red','shape':'square','label':resp.duration});
			   sys.addEdge('name', 'duration',{'color':'black'});
			}
		 }
      }
   }; 
   xhttp.send();
}

/** 
 * songFindByName
 * This method allows to make a request to the database, searching and bringing all the songs found with the requested name.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function songFindByName() {
   var xhttp; 
   var str = document.getElementsByName("songName")[0].value; 
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
      return;
   }
   //AjaxJs
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/songs/findbyname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //request of album
		 var xhttp2 = new XMLHttpRequest();
		 xhttp2.open("GET", "http://"+host+":"+port+"/albums", true);
		 xhttp2.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200){var album = JSON.parse(xhttp2.responseText);}
		 };
		 xhttp2.send();
		 
		/*for (elem in resp)
	    	  str = str + elem + ': ' + resp[elem]+'<br>';
	    	  
	     window.alert(str);	  
	     document.getElementById('show').innerHTML = str;*/
		 
		 
		 //ArborJs
	     var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 if(Array.isArray(resp)){  //there are many songs
			 for (var i=0; i<resp.length; i++){
				    sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
				    if(resp.duration != undefined){
				       sys.addNode('duration'+i,{'color':'red','shape':'square','label':resp[i].duration});
				       sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
				    }   
				    if(Array.isArray(album)){  //there are many albums
				    	for (var j=0; (j<album.length); j++){
				    	   if(album[j].albumid == resp[i].albumid){
				    		   sys.addNode('album'+j,{'color':'green','shape':'dot','label':album[j].title});
				    		   sys.addEdge('name'+i, 'album'+j,{'color':'black'});
				    	   }
				    	}	
				    }
				    else{  //there is only one album
				       if(album[j].albumid == resp[i].albumid){
				          sys.addNode('album',{'color':'green','shape':'dot','label':album.title});
		    		      sys.addEdge('name'+i, 'album',{'color':'black'});
				       }
				    } 
			 }
         }
		 else{	//there is only one song			
			    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			    if(resp.duration != undefined){
					   sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
					   sys.addEdge('name', 'duration',{'color':'black'});
			    }	       
			    if(Array.isArray(album)){  //there are many albums
			    	for (var j=0; (j<album.length); j++){
			    	   if(album[j].albumid == resp[i].albumid){
			    		   sys.addNode('album'+j,{'color':'green','shape':'dot','label':album[j].title});
			    		   sys.addEdge('name', 'album'+j,{'color':'black'});
			    	   }
			    	}	
			    }
			    else{  //there is only one album
			       if(album[j].albumid == resp[i].albumid){
			          sys.addNode('album',{'color':'green','shape':'dot','label':album.title});
	    		      sys.addEdge('name', 'album',{'color':'black'});
			       }
			    }     
		}//else
      }     
   };
   xhttp.send();  
}

/** 
 * songFindByDuration
 * This method allows to make a request to the database, searching and bringing all the songs found with the requested duration(In seconds format).
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function songFindByDuration() {
   var xhttp; 
   var str = document.getElementsByName("songDuration")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
      return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/songs/findbyduration/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //request of album
		 var xhttp2 = new XMLHttpRequest();
		 xhttp2.open("GET", "http://"+host+":"+port+"/albums", true);
		 xhttp2.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200){var album = JSON.parse(xhttp2.responseText);}
		 };
		 xhttp2.send();
		 
		 //ArborJs 
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport");
		 if(Array.isArray(resp)){  //there are many songs
			 for (var i=0; i<resp.length; i++){
			    sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			    sys.addNode('duration'+i,{'color':'red','shape':'square','label':resp[i].duration});
			    sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
			    if(Array.isArray(album)){  //there are many albums
			    	for (var j=0; (j<album.length); j++){
			    	   if(album[j].albumid == resp[i].albumid){
			    		   sys.addNode('album'+j,{'color':'green','shape':'dot','label':album[j].title});
			    		   sys.addEdge('name'+i, 'album'+j,{'color':'black'});
			    	   }
			    	}	
			    }
			    else{  //there is only one album
			       if(album[j].albumid == resp[i].albumid){
			          sys.addNode('album',{'color':'green','shape':'dot','label':album.title});
	    		      sys.addEdge('name'+i, 'album',{'color':'black'});
			       }
			    }  
			 }
         }
		 else{  //there is only one song	
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
		    sys.addEdge('name', 'duration',{'color':'black'});
		    
		    if(Array.isArray(album)){  //there are many albums
		    	for (var j=0; (j<album.length); j++){
		    	   if(album[j].albumid == resp[i].albumid){
		    		   sys.addNode('album'+j,{'color':'green','shape':'dot','label':album[j].title});
		    		   sys.addEdge('name', 'album'+j,{'color':'black'});
		    	   }
		    	}	
		    }
		    else{  //there is only one album
		       if(album[j].albumid == resp[i].albumid){
		          sys.addNode('album',{'color':'green','shape':'dot','label':album.title});
    		      sys.addEdge('name', 'album',{'color':'black'});
		       }
		    }     
		 }   
      }
   };
   xhttp.send(); 
}

//************************************************
//******************** ARTIST ********************
//************************************************

/** 
 * artistGetAll
 * This method allows to make a request to the database, searching and bringing all the artists found.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function artistGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/artists", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     var resp = JSON.parse(xhttp.responseText);
	     
	     //ArborJs 
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport");
	     if(Array.isArray(resp)){  //there are many artists
		    for (var i=0; i<resp.length; i++){
			   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			   sys.addNode('nickname'+i,{'color':'red','shape':'dot','label':resp[i].nickname});
			   sys.addNode('surname'+i,{'color':'green','shape':'dot','label':resp[i].surname});
			   sys.addEdge('name'+i, 'nickname'+i,{'color':'black'});
			   sys.addEdge('name'+i, 'surname'+i,{'color':'black'});
			   sys.addEdge('nickname'+i, 'surname'+i,{'color':'black'});
			}
		 }
		 else{  //there is only one artist
		    sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
			sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
			sys.addEdge('nickname', 'surname',{'color':'black'});
			sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			sys.addEdge('nickname', 'name',{'color':'black'});
			sys.addEdge('surname', 'name',{'color':'black'});
		}     
	  }
   };
   xhttp.send();
}

/** 
 * artistFindByName
 * This method allows to make a request to the database, searching and bringing all the artists found with the requested name.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function artistFindByName() {
   var xhttp; 
   var str = document.getElementsByName("artistName")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/artists/findbyname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //ArborJs 
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
	     sys.renderer = Renderer("#viewport"); 
	     if(Array.isArray(resp)){  //there are many artists
		    for (var i=0; i<resp.length; i++){
			   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			   if(resp.nickname != undefined){
				   sys.addNode('nickname'+i,{'color':'red','shape':'dot','label':resp[i].nickname});
				   sys.addEdge('name'+i, 'nickname'+i,{'color':'black'});
			   }	   
			   if(resp.surname != undefined){
				   sys.addNode('surname'+i,{'color':'green','shape':'dot','label':resp[i].surname});
				   sys.addEdge('name'+i, 'surname'+i,{'color':'black'});
			   }	      
			}
         }
		 else{  //there is only one artist
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			if(resp.surname != undefined){
			   sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
			   sys.addEdge('name', 'surname',{'color':'black'});
			}
			if(resp.nickname != undefined){
			   sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
			   sys.addEdge('name', 'nickname',{'color':'black'});
			}
			sys.addEdge('surname', 'nickname',{'color':'black'});
		 }  
      }
   };
   xhttp.send();	  
}

/** 
 * artistFindByNickname
 * This method allows to make a request to the database, searching and bringing all the artists found with the requested nickname.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function artistFindByNickname() {
   var xhttp; 
   var str = document.getElementsByName("artistNickname")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/artists/findbynickname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //ArborJs 	  
    	 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 if(Array.isArray(resp)){  //there are many artists
		    for (var i=0; i<resp.length; i++){
		       sys.addNode('nickname'+i,{'color':'red','shape':'dot','label':resp[i].nickname});
		       if(resp.surname != undefined){	
		    	   sys.addNode('surname'+i,{'color':'green','shape':'dot','label':resp[i].surname});
		    	   sys.addEdge('nickname'+i, 'surname'+i,{'color':'black'});
		       } 
			   if(resp.name != undefined){
				   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
				   sys.addEdge('name'+i, 'nickname'+i,{'color':'black'});
			   }
			}
	     }
		 else{  //there is only one artist
		    sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		    if(resp.surname != undefined){
		       sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		       sys.addEdge('nickname', 'surname',{'color':'black'});
		    }	 
		    if(resp.name != undefined){
		       sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		       sys.addEdge('nickname', 'name',{'color':'black'});
		    }
		 }   
      }
   };
   xhttp.send();		  
}	  
	  

/** 
 * artistFindBySurname
 * This method allows to make a request to the database, searching and bringing all the artists found with the requested surname.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function artistFindBySurname() {
   var xhttp; 
   var str = document.getElementsByName("artistSurname")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/artists/findbysurname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //ArborJs   
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 if(Array.isArray(resp)){  //there are many artists 
		    for (var i=0; i<resp.length; i++){
		       sys.addNode('surname'+i,{'color':'green','shape':'dot','label':resp[i].surname});
		       if(resp.name != undefined){
		    	   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name}); 
		    	   sys.addEdge('name'+i, 'surname'+i,{'color':'black'});
		       }
		       if(resp.nickname != undefined){
		    	   sys.addNode('nickname'+i,{'color':'red','shape':'dot','label':resp[i].nickname});
		    	   sys.addEdge('nickname'+i, 'surname'+i,{'color':'black'});
		       }
		 	}
		 }
		 else{  //there is only one artist
		    sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		    if(resp.nickname != undefined){
		       sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		       sys.addEdge(surname, nickname,{'color':'black'});
		    }
		    if(resp.name != undefined){
		       sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		       sys.addEdge('name', 'surname',{'color':'black'});
		    }
		 }		    
	  }
   };
   xhttp.send();	  
}

//***********************************************
//******************** ALBUM ********************
//***********************************************

/** 
 * albumGetAll
 * This method allows to make a request to the database, searching and bringing all the albums found.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function albumGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/albums", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         var resp = JSON.parse(xhttp.responseText);	
         
         //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
    	 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
         if(Array.isArray(resp)){  //there are many albums 
 		    for (var i=0; i<resp.length; i++){
 		    	sys.addNode('title'+i,{'color':'blue','shape':'dot','label':resp[i].title});
 				sys.addNode('releasedate'+i,{'color':'red','shape':'square','label':resp[i].releasedate}); 
 				sys.addEdge('title'+i,'releasedate'+i);;
 			}
 		 }
 	     else{  //there is only one album
 	    	sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
 			sys.addNode('releasedate',{'color':'red','shape':'square','label':resp.releasedate}); 
 			sys.addEdge('title','releasedate',{'color':'black'});
 		}
      }
   };
   xhttp.send();
}

/** 
 * albumFindByReleaseDate
 * This method allows to make a request to the database, searching and bringing all the albums found with the requested release date.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function albumFindByReleaseDate() {
   var xhttp; 
   var str = document.getElementsByName("albumDate")[0].value;
   if (str == "") {
	   window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/albums/findByReleaseDate/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
    	 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 if(Array.isArray(resp)){  //there are many albums
		    for (var i=0; i<resp.length; i++){
		    	sys.addNode('title'+i,{'color':'blue','shape':'dot','label':resp[i].title});
				sys.addNode('releasedate'+i,{'color':'red','shape':'square','label':resp[i].releasedate}); 
				sys.addEdge('title'+i,'releasedate'+i,{'color':'black'});
			}
		 }
	     else{  //there is only one album
	    	sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
			sys.addNode('releasedate',{'color':'red','shape':'square','label':resp.releasedate}); 
			sys.addEdge('title','releasedate',{'color':'black'});
		}   
	  }
   };
   xhttp.send();
}

/** 
 * albumFindByTitle
 * This method allows to make a request to the database, searching and bringing all the albums found with the requested title.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function albumFindByTitle() {
   var xhttp; 
   var str = document.getElementsByName("albumTitle")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
      return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/albums/findByTitle/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		 
		 //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
    	 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 if(Array.isArray(resp)){  //there are many albums
		    for (var i=0; i<resp.length; i++){
		    	sys.addNode('title'+i,{'color':'blue','shape':'dot','label':resp[i].title});
				sys.addNode('releasedate'+i,{'color':'red','square':'dot','label':resp[i].releasedate}); 
				sys.addEdge('title'+i,'releasedate'+i,{'color':'black'});
			}
		 }
	     else{  //there is only one album
	        sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
		    if(resp.releasedate != undefined){
		       sys.addNode('releasedate',{'color':'red','shape':'square','label':resp.releasedate}); 
			   sys.addEdge('title','releasedate',{'color':'black'});
		    } 
		 }
      }
   };
   xhttp.send();
}

//**********************************************
//******************** BAND ********************
//**********************************************

/** 
 * bandGetAll
 * This method allows to make a request to the database, searching and bringing all the bands found.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function bandGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/bands", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         var resp = JSON.parse(xhttp.responseText);
         
         //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
    	 sys.renderer = Renderer("#viewport");
		 if(Array.isArray(resp)){  //there are many bands
		    for (var i=0; i<resp.length; i++){
			   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			   sys.addNode('genre'+i,{'color':'red','shape':'dot','label':resp[i].genre}); 
			   sys.addEdge('name'+i,'genre'+i,{'color':'black'});
			}
		 }
		 else{  //there is only one band
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
			sys.addEdge('name','genre',{'color':'black'});
		 }
	  }
   };
   xhttp.send();
}

/** 
 * bandFyndByName
 * This method allows to make a request to the database, searching and bringing all the bands found with the requested name.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function bandFyndByName() {
   var xhttp; 
   var str = document.getElementsByName("bandName")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/bands/findbyname"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	    
		 //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
    	 sys.renderer = Renderer("#viewport");
		 if(Array.isArray(resp)){  //there are many bands
		    for (var i=0; i<resp.length; i++){
			   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			   sys.addNode('genre'+i,{'color':'red','shape':'dot','label':resp[i].genre}); 
			   sys.addEdge('name'+i,'genre'+i,{'color':'black'});
			   
			   //request of band members
			   var xhttp2 = new XMLHttpRequest();
			   xhttp2.open("GET", "http://"+host+":"+port+"/bands/getbandmember/"+resp[i].bandid, true);
			   xhttp2.onreadystatechange = function() {
				   if (this.readyState == 4 && this.status == 200) {
					   var resp2 = JSON.parse(xhttp2.responseText);			   
					   if(Array.isArray(resp2)){  //there are many band members
						  sys.addNode('bmember'+i,{'color':'green','shape':'dot','label':'Band Members'});
						  sys.addEdge('bmember'+i,'name'+i,{'color':'black'}); 
					      for (var j=0; j<resp2.length; j++){
						      sys.addNode('member'+j,{'color':'orange','shape':'dot','label':resp2[j].name});
						      sys.addEdge('bmember'+i,'member'+j,{'color':'black'});   					      
					      }
					   }
					   else{  //there is only one band member 
					      sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
	                      sys.addNode('member',{'color':'orange','shape':'dot','label':resp2.name});
						  sys.addEdge('bmember'+i,'member',{'color':'black'}); 	   
					   }
				   }
	            };
	            xhttp2.send();
			}
		 }
		 else{  //there is only one band
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
			sys.addEdge('name','genre',{'color':'black'});
			
			//request of band members
			var xhttp2 = new XMLHttpRequest();
			xhttp2.open("GET", "http://"+host+":"+port+"/bands/getbandmember/"+resp.bandid, true);
			xhttp2.onreadystatechange = function() {
			   if (this.readyState == 4 && this.status == 200) {
				   var resp2 = JSON.parse(xhttp2.responseText);
				   sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
				   sys.addEdge('bmember','name',{'color':'black'});
				   if(Array.isArray(resp2)){  //there are many band members
				      for (var j=0; j<resp2.length; j++){
					      sys.addNode('member'+j,{'color':'orange','shape':'dot','label':resp2[j].name});
					      sys.addEdge('bmember','member'+j,{'color':'black'});   					      
				      }
				   }
				   else{  //there is only one band member 
				      sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
                      sys.addNode('member',{'color':'orange','shape':'dot','label':resp2.name});
					  sys.addEdge('bmember','member',{'color':'black'}); 	   
				   }
			   }
            };
            xhttp2.send();
		 }
	  }
   };
   xhttp.send();
}

/** 
 * bandFindByGenre
 * This method allows to make a request to the database, searching and bringing all the bands found with the requested genre.
 * Subsequently this information will be used for its representation (ArborJs, and more).
 */
function bandFindByGenre() {
   var xhttp; 
   var str = document.getElementsByName("bandGenre")[0].value;
   if (str == "") {
	  window.alert("The Field is Empty\nPlease Input A Value");
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "http://"+host+":"+port+"/bands/findbygenre"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	    
		 //ArborJs
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
    	 sys.renderer = Renderer("#viewport");
		 if(Array.isArray(resp)){  //there are many bands
		    for (var i=0; i<resp.length; i++){  
			   sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			   sys.addNode('genre'+i,{'color':'red','shape':'dot','label':resp[i].genre}); 
			   sys.addEdge('name'+i,'genre'+i,{'color':'black'});
			   
			   //request of band members
			   var xhttp2 = new XMLHttpRequest();
			   xhttp2.open("GET", "http://"+host+":"+port+"/bands/getbandmember/"+resp[i].bandid, true);
			   xhttp2.onreadystatechange = function() {
				   if (this.readyState == 4 && this.status == 200) {
					   var resp2 = JSON.parse(xhttp2.responseText);			   
					   if(Array.isArray(resp2)){  //there are many band members
						  sys.addNode('bmember'+i,{'color':'green','shape':'dot','label':'Band Members'});
						  sys.addEdge('bmember'+i,'name'+i,{'color':'black'}); 
					      for (var j=0; j<resp2.length; j++){
						      sys.addNode('member'+j,{'color':'orange','shape':'dot','label':resp2[j].name});
						      sys.addEdge('bmember'+i,'member'+j,{'color':'black'});   					      
					      }
					   }
					   else{  //there is only one band member 
					      sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
	                      sys.addNode('member',{'color':'orange','shape':'dot','label':resp2.name});
						  sys.addEdge('bmember'+i,'member',{'color':'black'}); 	   
					   }
				   }
	            };
	            xhttp2.send();
			}
		 }
		 else{  //there is only one band
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
			sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
			sys.addEdge(name,genre,{'color':'black'});
			
			//request of band members
			var xhttp2 = new XMLHttpRequest();
			xhttp2.open("GET", "http://"+host+":"+port+"/bands/getbandmember/"+resp.bandid, true);
			xhttp2.onreadystatechange = function() {
			   if (this.readyState == 4 && this.status == 200) {
			      var resp2 = JSON.parse(xhttp2.responseText);			   
				  if(Array.isArray(resp2)){  //there are many band members
				     sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
					 sys.addEdge('bmember','name'+i,{'color':'black'}); 
					 for (var j=0; j<resp2.length; j++){
					    sys.addNode('member'+j,{'color':'orange','shape':'dot','label':resp2[j].name});
						sys.addEdge('bmember','member'+j,{'color':'black'});   					      
					 }
				  }
				  else{  //there is only one band member 
				     sys.addNode('bmember',{'color':'green','shape':'dot','label':'Band Members'});
	                 sys.addNode('member',{'color':'orange','shape':'dot','label':resp2.name});
					 sys.addEdge('bmember','member',{'color':'black'}); 	   
				  }
			   }
	        };
	        xhttp2.send();
		 }
      }
   };
   xhttp.send();
}

