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
				console.log(response);
			} else {
				alert("Oops, something went wrong, try again later!");	
			}
		});
	}

	console.log($location.path());
	if ($location.path() == "/albums"){
		load_albums();
	} else {
		load_album($routeParams.id);
	}


}]);