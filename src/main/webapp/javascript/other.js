/*The parameters of arborJs
repulsion 1,000 the force repelling nodes from each other
stiffness 600 the rigidity of the edges
friction 0.5 the amount of damping in the system
gravity false an additional force attracting nodes to the origin
fps 55 frames per second
dt 0.02 timestep to use for stepping the simulation
precision 0.6 accuracy vs. speed in force calculations

arbor.ParticleSystem({friction:.5, stiffness:600, repulsion:1000})
sys.parameters({gravity:true, dt:0.005})

Nodos
sys.addNode("mynode", {mass:2, myColor:"goldenrod"})
mass 1.0 the node’s resistance to movement and repulsive power
fixed false if true, the node will be unaffected by other particles

Edge
edge = sys.addEdge(node1, node2, {length:.75, pointSize:3})
*/



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
//******************** SONG ********************

//GetAll
function songGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "/songs", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     var resp = JSON.parse(xhttp.responseText);
	     var sys = arbor.ParticleSystem(500, 40,1); 
	     sys.parameters({gravity:true}); 
	     sys.renderer = Renderer("#viewport");
	     //var resp = [{name:'Juan',duration:'hola'},{name:'Franco',duration:'chau'},{name:'Pedro',duration:'adios'}];
	     for (var i=0; i<resp.length; i++){
	        sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
	        sys.addNode('duration'+i,{'color':'red','shape':'dot','label':resp[i].duration});
	        sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
	     }
      }
   }; 
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
   xhttp.open("GET", "http://localhost:4567/songs/findbyname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	  
		 for (elem in resp)
	    	  str = str + elem + ': ' + resp[elem]+'<br>';
	    	  
	    //window.alert(str);	  
	    document.getElementById('show').innerHTML = str;
		 
		 
		 /*ArborJs
	     var sys = arbor.ParticleSystem(500, 40,1); // create the system with sensible repulsion/stiffness/friction
		 sys.parameters({gravity:true}); // use center-gravity to make the graph settle nicely (ymmv)
		 sys.renderer = Renderer("#viewport"); // our newly created renderer will have its .init() method called shortly by sys...
		 if(Array.isArray(resp)){
			 for (var i=0; i<resp.length; i++){
			    sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			    sys.addNode('duration'+i,{'color':'red','shape':'dot','label':resp[i].duration});
			    sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
			 }
         }
		 else{	
	        sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    if(resp.duration != undefined){
			   sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
			   sys.addEdge(name, duration);
			}
		 }*/
      }
   };
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
   xhttp.open("GET", "/songs/findbyduration/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
		  
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport");
		 if(Array.isArray(resp)){
			 for (var i=0; i<resp.length; i++){
			    sys.addNode('name'+i,{'color':'blue','shape':'dot','label':resp[i].name});
			    sys.addNode('duration'+i,{'color':'red','shape':'dot','label':resp[i].duration});
			    sys.addEdge('name'+i, 'duration'+i,{'color':'black'});
			 }
         }
		 else{
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    sys.addNode('duration',{'color':'red','shape':'dot','label':resp.duration});
		    sys.addEdge(name, duration,{'color':'black'});
		 }   
      }
   };
   xhttp.send(); 
}


//******************** ARTIST ********************

//GetAll
function artistGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "/artist", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     var resp = JSON.parse(xhttp.responseText);
	  }
   };
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
   xhttp.open("GET", "/artist/findbyname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	    
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
	     sys.renderer = Renderer("#viewport"); 
		 sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		 if(resp.surname != undefined){
		    sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		    sys.addEdge(name, surname);
		 }
		 if(resp.nickname != undefined){
		    sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		    sys.addEdge(name, nickname);
		 }
		 sys.addEdge(surname, nickname); 
      }
   };
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
   xhttp.open("GET", "/artist/findbynickname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
			  
    	 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		 if(resp.surname != undefined){
		    sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		    sys.addEdge(nickname, surname);
		 }	 
		 if(resp.name != undefined){
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    sys.addEdge(nickname, name);
		 }
		 sys.addEdge(surname, name);
      }
   };
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
   xhttp.open("GET", "/artist/findbysurname/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
			  
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 sys.addNode('surname',{'color':'red','shape':'dot','label':resp.surname});
		 if(resp.nickname != undefined){
		    sys.addNode('nickname',{'color':'green','shape':'dot','label':resp.nickname});
		    sys.addEdge(surname, nickname);
		 }
		 if(resp.name != undefined){
		    sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		    sys.addEdge(name, surname);
		 }
		 sys.addEdge(name, nickname);
	  }
   };
   xhttp.send();	  
}


//******************** ALBUM ********************

//GetAll
function albumGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "/albums", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         var resp = JSON.parse(xhttp.responseText);	
      }
   };
   xhttp.send();
}

//FindByReleaseDate
function albumFindByReleaseDate() {
   var xhttp; 
   var srt = document.getElementsByName("albumDate")[0].value;
   if (str == "") {
      document.getElementById("show").innerHTML = "";
	  return;
   }
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "albums/findByReleaseDate"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
			  
	     var sys = arbor.ParticleSystem(500, 40,1); 
    	 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
		 sys.addNode('releasedate',{'color':'red','shape':'dot','label':resp.releasedate}); 
		 sys.addEdge(title,releasedate);
	  }
   };
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
   xhttp.open("GET", "/albums/findByTitle/"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
			  
	     var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 sys.addNode('title',{'color':'blue','shape':'dot','label':resp.title});
		 if(resp.releasedate != undefined){
		    sys.addNode('releasedate',{'color':'red','shape':'dot','label':resp.releasedate}); 
			sys.addEdge(title,releasedate);
		 }   
	  }
   };
   xhttp.send();
}


//******************** BAND ********************

//GetAll
function bandGetAll() {
   var xhttp; 
   xhttp = new XMLHttpRequest();
   xhttp.open("GET", "/bands", true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
         var resp = JSON.parse(xhttp.responseText);	
	  }
   };
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
   xhttp.open("GET", "/bands/findbyname"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	    
   	     var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
		 sys.renderer = Renderer("#viewport"); 
		 sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		 sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
		 sys.addEdge(name,genre);
	  }
   };
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
   xhttp.open("GET", "/bands/findbygenre"+str, true);
   xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("show").innerHTML = this.responseText;
		 var resp = JSON.parse(xhttp.responseText);
	    
		 var sys = arbor.ParticleSystem(500, 40,1); 
		 sys.parameters({gravity:true}); 
    	 sys.renderer = Renderer("#viewport"); 
	     sys.addNode('name',{'color':'blue','shape':'dot','label':resp.name});
		 sys.addNode('genre',{'color':'red','shape':'dot','label':resp.genre}); 
		 sys.addEdge(name,genre);
      }
   };
   xhttp.send();
}