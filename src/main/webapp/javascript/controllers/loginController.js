angular.module('app')
.controller('LoginController', ['$scope', '$http', function($scope,$http){

	$scope.formData = {};
 
	$scope.validateSubmit = function (formData) {
		alert('Form submitted with' + JSON.stringify(formData));
	};

}]);
