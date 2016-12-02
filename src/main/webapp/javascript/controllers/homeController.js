angular.module('app')
.controller('HomeController', ['$scope', '$http', function($scope,$http){
	
	/*
	* Albums
	*/	
	$scope.albums = [];
	function load_albums() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				$scope.albums = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	/**
	* Artists
	*/
	$scope.artists = [];
	
	function load_artists() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists").then(function callback(response){
			if (response.status == 200){
				$scope.artists = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
	
	/**
	* Bands
	*/	
	$scope.bands = [];
	function load_bands() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/bands").then(function callback(response){
			if (response.status == 200){
				$scope.bands = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	/**
	* Songs
	*/
	$scope.songs = [];
	function load_songs() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/").then(function callback(response){
			if (response.status == 200){
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_albums();
	load_artists();
	//load_bands();
	load_songs();

	/**
	* Scroller functions
	**/
	$scope.album_start = 0;
	$scope.artist_start = 0;
	$scope.song_start = 0;
	$scope.previous = function (element) {
		if (element == 'albums') {
			if ($scope.album_start >= 3){
				$scope.album_start = $scope.album_start - 3;
			}
		} else if (element == 'artists'){
			if ($scope.artist_start >= 3){
				$scope.artist_start = $scope.artist_start - 3;
			}
		} else if (element == 'songs'){
			if ($scope.song_start >= 3){
				$scope.song_start = $scope.song_start - 3;
			}
		}

	}

	$scope.next = function (element){
		if (element == 'albums'){
			if ($scope.album_start + 3< $scope.albums.length){
				$scope.album_start = $scope.album_start + 3;
			}
		} else if (element == 'artists') {
			if ($scope.artist_start +3 < $scope.artists.length){
				$scope.artist_start = $scope.artist_start +3 ;
			}
		} else if (element == 'songs'){
			if ($scope.song_start >= 3){
				$scope.song_start = $scope.song_start - 3;
			}
		}
	}
}]);

