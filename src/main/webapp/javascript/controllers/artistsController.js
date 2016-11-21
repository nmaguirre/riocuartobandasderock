angular.module('app')
.controller('ArtistsController', ['$scope', '$http', function($scope,$http){
		
		$scope.click = function(){
			$http.get("http://localhost:4567/bands").then(function callback(data){
				console.log(data);
			});
		}
}]);