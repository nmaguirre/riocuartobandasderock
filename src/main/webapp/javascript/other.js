//---AJAX Methods---

//********** Song **********
//GetAll
function songGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    songResp = JSON.parse(xhttp.responseText);
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
    document.getElementById("show").innerHTML = "";
    return;
  }
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
    document.getElementById("show").innerHTML = this.responseText;
    songResp = JSON.parse(xhttp.responseText);
    }
  };
  xhttp.open("GET", "/songs/findbyname/"+str, true);
  xhttp.send();
  
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
	    songResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/songs/findbyduration/"+str, true);
	  xhttp.send();
	}


//********** Artist **********
//GetAll
function artistGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    artistResp = JSON.parse(xhttp.responseText);
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
	    artistResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbyname/"+str, true);
	  xhttp.send();
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
	    artistResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbynickname/"+str, true);
	  xhttp.send();
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
	    artistResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/artist/findbysurname/"+str, true);
	  xhttp.send();
	}


//********** Album **********
//GetAll
function albumGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    albumResp = JSON.parse(xhttp.responseText);	
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
	    albumResp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "albums/findByReleaseDate"+str, true);
	  xhttp.send();
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
	    albumResp = JSON.parse(xhttp.responseText);	
	    }
	  };
	  xhttp.open("GET", "/albums/findByTitle/"+str, true);
	  xhttp.send();
	}

//********** Band **********
//GetAll
function bandGetAll() {
	  var xhttp; 
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    //document.getElementById("show").innerHTML = this.responseText;
	    bandResp = JSON.parse(xhttp.responseText);	
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
	    bandResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/bands/findbyname"+str, true);
	  xhttp.send();
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
	    bandResp = JSON.parse(xhttp.responseText);
	    }
	  };
	  xhttp.open("GET", "/bands/findbygenre"+str, true);
	  xhttp.send();
	}