angular.module('app')
.controller('AlbumsController', ['$scope', '$http', function($scope,$http){
		
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
		
	load_albums();
}]);