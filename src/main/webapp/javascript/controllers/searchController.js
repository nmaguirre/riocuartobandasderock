angular.module('app')
.controller('SearchController', ['$scope', '$http','$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	
	$scope.albums = [];
	$scope.artists = [];
	$scope.bands = [];
	$scope.songs = [];
	
	$scope.go_search = function(){
		$location.path("/search_results?value="+$scope.value);	
	}

	function search_all(value) {
		$scope.albums = [];
		$scope.artists = [];
		$scope.bands = [];
		$scope.songs = [];
		search_albums(value);
		search_artists(value);
		//search_bands(value);
		search_songs(value);
	}

	function search_albums(value){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/findByTitle/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.albums.push(response.data[0]);
			}
		});
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/findByReleaseDate/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.albums.push(response.data[0]);
			}
		})
	};

	function search_artists(value){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/findbyname/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.artists.push(response.data[0]);
			}
		});
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/findbysurname/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.artists.push(response.data[0]);
			}
		})
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/findbynickname/"+value).then(function callback(response){
			if (response.status == 200) {
				$scope.artists.push(response.data[0]);
			}
		})
	};

	function search_bands(value){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/bands/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.bands.push(response.data[0]);
			}
		});
	};

	function search_songs(value){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/findbyname/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.songs.push(response.data[0]);
			}
		});
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/findbyduration/"+value).then(function callback(response){
			if (response.status == 200){
				$scope.songs.push(response.data[0]);
			}
		})
	};

	console.log($location.path());
	console.log($location.search().value);
	if ($location.path() == "/search_results" && $location.search().value != undefined){
		search_all($location.search().value);
	} 

	/**
	* Scroller functions
	**/
	$scope.album_start = 0;
	$scope.artist_start = 0;
	$scope.previous = function (element) {
		if (element == 'albums') {
			if ($scope.album_start >= 3){
				$scope.album_start = $scope.album_start - 3;
			}
		} else if (element == 'artists'){
			if ($scope.artist_start >= 3){
				$scope.artist_start = $scope.artist_start - 3;
			}
		}
	}

	$scope.next = function (element){
		if (element == 'albums'){
			if ($scope.album_start + 3< $scope.albums.length){
				$scope.album_start = $scope.album_start + 3;
			}
		} else if (element == 'artists') {
			if ($scope.artist_start +3 < $scope.artists.length){
				$scope.artist_start = $scope.artist_start +3 ;
			}
		}
	}	
}]);