angular.module('app')
.controller('BandsController', ['$scope', '$http', function($scope,$http){
		
	$scope.bands = [];

	function load_bands() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response){
			if (response.status == 200){
				$scope.bands = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
		
	load_bands();
}]);