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
				$scope.songs[index].album_name = response.data[index].title;
				$scope.songs[index].id_album = response.data[index].AlbumID;
				load_songs_band(response.data[index].band);
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}


	function load_songs_band(id){
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/bands/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.album.band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});*/
		$scope.songs[0].band_name = "The Strokes";
	}

	$scope.song_search = '';
}]);