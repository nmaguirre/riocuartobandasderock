angular.module('app')
.controller('SongsController', ['$scope', '$http','$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.songs = [];

	function load_songs() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/").then(function callback(response){
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--) {
					response.data[i].album_name = load_album(response.data[i].album, i);
				}
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
		
	load_songs();
	
	function load_album(id, index) { 
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/"+id).then(function callback(response) {
			if (response.status == 200){
				$scope.songs[index].album_name = response.data[0].title;
				$scope.songs[index].id_album = response.data[0].AlbumID;
				$scope.songs[index].id_band = response.data[0].band;
				load_songs_band(response.data[0].band,index);
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}


	function load_songs_band(id,index){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/getbands/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.songs[index].band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});
	}

	$scope.song_search = '';
}]);