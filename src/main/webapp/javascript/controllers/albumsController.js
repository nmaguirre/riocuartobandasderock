angular.module('app')
.controller('AlbumsController', ['$scope', '$http', '$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.albums = [];
	$scope.album = '';
	function load_albums() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				$scope.albums = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
	
	function load_album(id){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.album = response.data[0];
				load_album_band($scope.album.band);
			} else {
				alert("Oops, something went wrong, try again later!");	
			}
		});
	}

	function load_album_band(id){
		/*$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/bands/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.album.band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});*/
		$scope.album.band_name = "The Strokes";
	}

	$scope.go_to = function(id){
		$location.path("/albums/"+id);
	}

	if ($location.path() == "/albums"){
		load_albums();
	} else {
		load_album($routeParams.id);
	}


}]);