angular.module('app')
.controller('AdminController', ['$scope', '$http', '$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.albums = [];
	function load_albums() {
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				console.log(response.data);
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

	load_albums();

	$scope.artists = [];

	function load_artists() {
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists").then(function callback(response) {
			if (response.status == 200) {
				$scope.artists = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/artists").then(function callback(response){
			if (response.status == 200){
				$scope.artists = response.data;
			}else{
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_artists();

		
	$scope.songs = [];

	function load_songs() {
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists").then(function callback(response) {
			if (response.status == 200) {
				$scope.artists = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/albums").then(function callback(response){
			var songs = [];
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--){
					for (var j = response.data[i].songs.length - 1; j >= 0; j--) {
						response.data[i].songs[j].album_name = response.data[i].title;
						songs.push(response.data[i].songs[j])
					}
				}
				$scope.songs = songs;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_songs();


	$scope.bands = [];

	function load_bands() {
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/").then(function callback(response){
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--) {
					response.data[i].album_name = load_album(response.data[i].album, i);
				}
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/bands").then(function callback(response){
			if (response.status == 200){
				$scope.bands = response.data;
			}else{
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
		
	load_bands();
}]);