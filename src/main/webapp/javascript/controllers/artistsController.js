angular.module('app')
.controller('ArtistsController', ['$scope', '$http', function($scope,$http){
		
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
		
	load_artists();
}]);