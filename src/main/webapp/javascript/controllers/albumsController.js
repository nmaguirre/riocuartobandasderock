angular.module('app')
.controller('AlbumsController', ['$scope', '$http', '$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.albums = [];
	$scope.album = '';

	function load_albums() {
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				$scope.albums = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/albums").then(function callback(response){
			if (response.status == 200){
				$scope.albums = response.data;
			}else{
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
	
	function load_album(id){
		console.log(id);
		$http.get("http://localhost:4567/albums/"+id).then(function callback(response){
			if (response.status == 200){
				console.log(response.data);
				$scope.album = response.data[0];
				load_album_band($scope.album.band.id);
			} else {
				alert("Oops, something went wrong, try again later!");	
			}
		});
	}

	function load_album_band(id){
		$http.get("http://localhost:4567/bands/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.album.band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});
	}

	$scope.go_to = function(id){
		$location.path("/albums/"+id);
	}

	if ($location.path() == "/albums"){
		load_albums();
	} else {
		load_album($routeParams.id);
	}
	
	$scope.createAlbum = function(data) {
		data.release_date = moment(data.release_date).format("YYYY-MM-DD");
		console.log(data);
		$http.post("http://localhost:4567/albums?title="+$scope.dataAlbum.title+"&release_date="+$scope.dataAlbum.release_date+"&band_id="+$scope.dataAlbum.band_id).then(function callback(response) {
			if (response.status == 201) {
				$location.path("/admin");
			} else {
				alert("Creación de albúm fallida.");
			}
			
		});
	}

	$scope.updateAlbum = function(data){
		data.release_date = moment(data.release_date).format("YYYY-MM-DD");
		
		$http.put("http://localhost:4567/albums/"+$routeParams.id+"?title="+$scope.dataAlbum.title+"&release_date="+$scope.dataAlbum.release_date+"&band_id="+$scope.dataAlbum.band_id).then(function callback(response) {
			if (response.status == 200) {
				$location.path("/admin");
			} else {
				alert("Creación de albúm fallida.");
			}
			
		});
	}

	$scope.dataAlbum  = {};
/*{
    "BandID": "1",
    "name": "Banda-1",
    "genre": "Rock",
    "albums": [
      {
        "AlbumID": "1",
	"title": "Perdido",
	"releaseDate": "2012-01-01",
      },
      {
	"AlbumID": "2",
	"title": "Mañana",
	"releaseDate": "2013-01-01",
      }
    ]
},
{
    "BandID": "2",
    "name": "Banda-2",
    "genre": "Blues",
    "albums": [
      {
	"AlbumID": "9",
	"title": "Noche",
	"releaseDate": "2013-01-01",
      },
      {
	"AlbumID": "8",
	"title": "Eye in the sky",
	"releaseDate": "2013-01-01",
      }
    ]
}
];*/

	function load_bands() {
		$http.get("http://localhost:4567/bands").then(function callback(response) {
			if (response.status == 200) {
				$scope.bands = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_bands();
}]);