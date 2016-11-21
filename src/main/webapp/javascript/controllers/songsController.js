angular.module('app')
.controller('SongsController', ['$scope', '$http', function($scope,$http){
		
		$scope.click = function(){
			console.log("A");
			$http.get("http://localhost:4567/hello").then(function callback(data){
				console.log(data);
			});
			$http.get("http://localhost:4567/bands").then(function callback(data){
				console.log(data);
			});
		}
}]);