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
function songFindByName(str) {
  var xhttp; 
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
function songFindByDuration(str) {
	  var xhttp; 
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
function artistFindByName(str) {
	  var xhttp; 
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
function artistFindByNickname(str) {
	  var xhttp; 
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
function artistFindBySurname(str) {
	  var xhttp; 
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
function albumFindByTitle(str) {
	  var xhttp; 
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
function bandFyndByName(str) {
	  var xhttp; 
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
function bandFindByGenre(str) {
	  var xhttp; 
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