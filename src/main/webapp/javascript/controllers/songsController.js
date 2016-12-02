angular.module('app')
.controller('SongsController', ['$scope', '$http','$location','$routeParams', function($scope,$http,$location, $routeParams){
		
	$scope.songs = [];
	$scope.dataSong = {};
	$scope.albums = [];
	function load_songs() {
/*		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/").then(function callback(response){
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--) {
					response.data[i].album_name = load_album(response.data[i].album, i);
				}
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/songs").then(function callback(response){
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--) {
					alert(response.data[i].album)
					response.data[i].album_name = load_album(response.data[i].album, i);
				}
				$scope.songs = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}
	
	function load_album() { 
/*		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/"+id).then(function callback(response) {
		

	if ($location.path() == "/admin/songs/add"){
		load_albums();
	} else {
		load_songs();
	}

	function load_albums() { 
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums").then(function callback(response) {
			if (response.status == 200){
				$scope.albums = response.data;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}


	function load_album(id, index) { 
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/albums/"+id).then(function callback(response) {
			if (response.status == 200){
				$scope.songs[index].album_name = response.data[0].title;
				$scope.songs[index].id_album = response.data[0].AlbumID;
				$scope.songs[index].id_band = response.data[0].band.bandId;
				load_songs_band(response.data[0].band.bandId,index);
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});*/
		$http.get("http://localhost:4567/albums").then(function callback(response){
			var songs = [];
			if (response.status == 200){
				for (var i = response.data.length - 1; i >= 0; i--){
					for (var j = response.data[i].songs.length - 1; j >= 0; j--) {
						response.data[i].songs[j].album_name = response.data[i].title;
						songs.push(response.data[i].songs[j])
					}
				}
				$scope.songs = songs;
			} else {
				alert("Oops, something went wrong, try again later!")
			}
		});
	}

	load_album();

	function load_songs_band(id,index){
		$http.get("https://private-53163-riocuartobandasderock.apiary-mock.com/artists/getbands/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.songs[index].band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});
		/*$http.get("http://localhost:4567/bands/findbyname/"+id).then(function callback(response){
			if (response.status == 200){
				$scope.songs[index].band_name = response.data[0].name;
			} else {
				alert("Oops, something went wrong, try again later!");		
			}
		});*/
	}

	$scope.song_search = '';

	$scope.createSong = function(data){
		console.log(data);
		$http.post("https://private-53163-riocuartobandasderock.apiary-mock.com/songs/", data).then(function callback(response){
			console.log(response);
			if (response.status == 201){
				alert("Song created");
				$location.path("/admin");
			} else {
				alert("Oops, something went wrong, try again later!");
			}
		})
	}

	/*$scope.updateSong = function(data){
		$http.put("https://")
	}*/
}]);