angular["module"]("app", ["ngRoute"])
	["config"](['$locationProvider','$routeProvider', function($locationProvider, $routeProvider){
		$routeProvider
			["when"]("/",{
				templateUrl: "views/home.html",
				controller: "HomeController"
			})
			["when"]("/albums",{
				controller: "AlbumsController",
				templateUrl: "views/albums.html"
			})
			["when"]("/albums/:id",{
				controller: "AlbumsController",
				templateUrl: "views/album.html"
			})
			["when"]("/artists",{
				controller: "ArtistsController",
				templateUrl: "views/artists.html"
			})
			["when"]("/artists/:id",{
				controller: "ArtistsController",
				templateUrl: "views/artist.html"
			})
			["when"]("/bands",{
				controller: "BandsController",
				templateUrl: "views/bands.html"
			})
			["when"]("/bands/:id",{
				controller: "BandsController",
				templateUrl: "views/band.html"
			})
			["when"]("/search_results",{
				templateUrl: "views/results.html"
			})
			["when"]("/songs",{
				controller: "SongsController",
				templateUrl: "views/songs.html"
			})
			["when"]("/songs/:id",{
				controller: "SongsController",
				templateUrl: "views/song.html"
			})
}]);