angular.module('app')
.controller('AdminController', ['$scope', '$http', '$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.albums = [];
	function load_albums() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				console.log(response.data);
				$scope.albums = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_albums();

	$scope.artists = [];

	function load_artists() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists").then(function callback(response) {
			if (response.status == 200) {
				$scope.artists = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_artists();

		$scope.bands = [
		{
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
	];


	$scope.songs = [];

	function load_songs() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/").then(function callback(response){
			if (response.status == 200){
				/*for (var i = response.data.length - 1; i >= 0; i--) {
					response.data[i].album_name = load_album(response.data[i].album, i);
				}*/
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
		
	load_songs();
}]);