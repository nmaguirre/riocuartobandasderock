angular.module('app')
.controller('AlbumFormController', ['$scope', '$http', '$location','$routeParams', function($scope,$http,$location, $routeParams){
	$scope.dataAlbum  = {};
	$scope.bands = [
{
    "BandID": "1",
    "name": "Banda-1",
    "genre": "Rock",
    "albums": [
      {
        "AlbumID": "1",
	"title": "Perdido",
	"releaseDate": "2012-01-01",
      },
      {
	"AlbumID": "2",
	"title": "Mañana",
	"releaseDate": "2013-01-01",
      }
    ]
},
{
    "BandID": "2",
    "name": "Banda-2",
    "genre": "Blues",
    "albums": [
      {
	"AlbumID": "9",
	"title": "Noche",
	"releaseDate": "2013-01-01",
      },
      {
	"AlbumID": "8",
	"title": "Eye in the sky",
	"releaseDate": "2013-01-01",
      }
    ]
}
];
console.log($scope.dataAlbum.bands);


/*
	function load_bands() {
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/bands").then(function callback(response) {
			if (response.status == 200) {
				$scope.dataAlbum.bands = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}*/
}]);