angular.module('app')
.controller('SongsController', ['$scope', '$http', function($scope,$http){
		
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
				$scope.songs[index].album_name = response.data.title;
			} else {
				$scope.songs[index].album_name = "Placeholder name";
			}
		})
	}
	

	$scope.song_search = '';
}]);