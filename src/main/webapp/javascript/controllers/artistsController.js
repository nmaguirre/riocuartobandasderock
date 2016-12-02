angular.module('app')
.controller('ArtistsController', ['$scope', '$http', '$location','$routeParams', function($scope, $http, $location, $routeParams) {

	$scope.artists = [];
	$scope.artist = '';
	$scope.dataArtist = {};

	function load_artists() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists").then(function callback(response) {
			if (response.status == 200) {
				$scope.artists = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	function load_artist(id) {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/"+id).then(function callback(response) {
			if (response.status == 200) {
				$scope.artist = response.data[0];
				load_artist_bands($scope.artist.ArtistID);
			} else {
				alert("Oops, something went wrong, try again later!");
			}
		});
	}

	function load_artist_bands(id) {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/getbands/"+id).then(function callback(response) {
			if (response.status == 200) {
				$scope.artist.bands = response.data;
			} else {
				$scope.songs[index].album_name = "Placeholder name";
			}
		});
	}

	$scope.create = function createArtist(data) {
		$http.post("https://private-53163-riocuartobandasderock.apiary-mock.com/artists", data).then(function callback(response) {
			if (response.status == 201) {
				$location.path("/admin");
			} else {
				alert("Creación de artista fallida.");
			}
			
		});
	}

	$scope.create = function updateArtist(dataBand){
		$http.put("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/"+$routeParams.id, data).then(function callback(response) {
			if (response.status == 201) {
				$location.path("/admin");
			} else {
				alert("Actualización de artista fallida.");
			}
			
		});
	}
	
	
	$scope.go_to = function(id) {
		$location.path("/artists/"+id);
	}

	if ($location.path() == "/artists"){
		load_artists();
	} else {
		load_artist($routeParams.id);
	}

}]);