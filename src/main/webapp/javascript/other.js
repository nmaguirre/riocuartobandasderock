//---AJAX Methods---

//********** Song **********
//GetAll
function songGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    	var resp = JSON.parse(xhttp.responseText);
	    }
	  }; 
	  xhttp.open("GET", "/songs", true);
	  xhttp.send();
}

//FindByName
function songFindByName() {
  var xhttp; 
  var str = document.getElementsByName("songName")[0].value; 
  if (str == "") {
    document.getElementById('show').innerHTML = "";
    return;
  }
  
  //AjaxJs
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    document.getElementById("show").innerHTML = this.responseText;
    var resp = JSON.parse(xhttp.responseText);
    }
  };
  xhttp.open("GET", "/songs/findbyname/"+str, true);
  xhttp.send();
  
  //ArborJs
  var sys = arbor.ParticleSystem(500, 40,1); // create the system with sensible repulsion/stiffness/friction
  sys.parameters({gravity:true}); // use center-gravity to make the graph settle nicely (ymmv)
  sys.renderer = Renderer("#viewport"); // our newly created renderer will have its .init() method called shortly by sys...
  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
  if(resp.duration != undefined){
	  var duration = sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
	  sys.addEdge(name, duration);
  }
}

//FindByDuration
function songFindByDuration() {
	  var xhttp; 
	  var srt = document.getElementsByName("songDuration")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/songs/findbyduration/"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport");
	  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
	  var duration = sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
	  sys.addEdge(name, duration);
}


//********** Artist **********
//GetAll
function artistGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist", true);
	  xhttp.send();
}

//FindByName
function artistFindByName() {
	  var xhttp; 
	  var srt = document.getElementsByName("artistName")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbyname/"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
	  if(resp.surname != undefined){
		  var surname = sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		  sys.addEdge(name, surname);
	  }
	  if(resp.nickname != undefined){
		  var nickname = sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		  sys.addEdge(name, nickname);
	  }
	  sys.addEdge(surname, nickname);
	  
}

//FindByNickname
function artistFindByNickname() {
	  var xhttp; 
	  var srt = document.getElementsByName("artistNickname")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbynickname/"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var nickname = sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
	  if(resp.surname != undefined){
		  var surname = sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		  sys.addEdge(nickname, surname);
	  }	 
	  if(resp.name != undefined){
		  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		  sys.addEdge(nickname, name);
	  }
	  sys.addEdge(surname, name);
}	  
	  

//FindBySurname
function artistFindBySurname() {
	  var xhttp; 
	  var srt = document.getElementsByName("artistSurname")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbysurname/"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var surname = sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
	  if(resp.nickname != undefined){
		  var nickname = sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		  sys.addEdge(surname, nickname);
	  }
	  if(resp.name != undefined){
		  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		  sys.addEdge(name, surname);
	  }
	  sys.addEdge(name, nickname);
}


//********** Album **********
//GetAll
function albumGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "/albums", true);
	  xhttp.send();
	}

//FindByReleaseDate
function albumFindByReleaseDate(str) {
	  var xhttp; 
	  var srt = document.getElementsByName("albumDate")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "albums/findByReleaseDate"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var title = sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
	  var releasedate = sys.addNode('releasedate',{'color':'red','shape':'dot','label':resp.releasedate}); 
	  sys.addEdge(title,releasedate);
	}

//FindByTitle
function albumFindByTitle() {
	  var xhttp; 
	  var srt = document.getElementsByName("albumTitle")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "/albums/findByTitle/"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var title = sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
	  if(resp.releasedate != undefined){
	  	var releasedate = sys.addNode('releasedate',{'color':'red','shape':'dot','label':resp.releasedate}); 
	  	sys.addEdge(title,releasedate);
	  }  
}

//********** Band **********
//GetAll
function bandGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "/bands", true);
	  xhttp.send();
}

//FindByName
function bandFyndByName() {
	  var xhttp; 
	  var srt = document.getElementsByName("bandName")[0].value;
	  var srt = str = document.getElementsByName("artistName")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/bands/findbyname"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
	  var genre = sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
	  sys.addEdge(name,genre);
}

//FindByTGenre
function bandFindByGenre() {
	  var xhttp; 
	  var srt = document.getElementsByName("bandGenre")[0].value;
	  if (str == "") {
	    document.getElementById("show").innerHTML = "";
	    return;
	  }
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    document.getElementById("show").innerHTML = this.responseText;
	    var resp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/bands/findbygenre"+str, true);
	  xhttp.send();
	  
	  var sys = arbor.ParticleSystem(500, 40,1); 
	  sys.parameters({gravity:true}); 
	  sys.renderer = Renderer("#viewport"); 
	  var name = sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
	  var genre = sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
	  sys.addEdge(name,genre);
}