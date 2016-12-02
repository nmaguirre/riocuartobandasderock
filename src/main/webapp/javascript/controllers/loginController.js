angular.module('app')
.controller('LoginController', ['$scope', '$http', '$location', function($scope,$http,$location){

	$scope.formData = {};
 
	$scope.validate = function (formData) {
		$location.path("/admin");
	};

}]);
