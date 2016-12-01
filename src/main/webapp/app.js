angular["module"]("app", ["ngRoute","angularSlideables"])
	["config"](['$locationProvider','$routeProvider', function($locationProvider, $routeProvider){
		$routeProvider
			["when"]("/",{
				templateUrl: "views/home.html",
				controller: "HomeController"
			})
			["when"]("/admin",{
				controller: "AdminController",
				templateUrl: "views/admin.html"
			})
			["when"]("/login",{
				controller: "LoginController",
				templateUrl: "views/login.html"
			})
			["when"]("/albums",{
				controller: "AlbumsController",
				templateUrl: "views/albums.html"
			})
			["when"]("/albums/:id",{
				controller: "AlbumsController",
				templateUrl: "views/album.html"
			})
			["when"]("/admin/albums/add",{
				templateUrl: "views/addAlbum.html",
				controller: "AlbumsController"
			})
			["when"]("/artists",{
				controller: "ArtistsController",
				templateUrl: "views/artists.html"
			})
			["when"]("/artists/:id",{
				controller: "ArtistsController",
				templateUrl: "views/artist.html"
			})
			["when"]("/admin/artists/add",{
				templateUrl: "views/addArtist.html",
				controller: "ArtistsController"
			})
			["when"]("/bands",{
				controller: "BandsController",
				templateUrl: "views/bands.html"
			})
			["when"]("/bands/:id",{
				controller: "BandsController",
				templateUrl: "views/band.html"
			})
			["when"]("/admin/bands/add",{
				templateUrl: "views/addBand.html",
				controller: "BandsController"
			})
			["when"]("/songs",{
				controller: "SongsController",
				templateUrl: "views/songs.html"
			})
			["when"]("/songs/:id",{
				controller: "SongsController",
				templateUrl: "views/song.html"
			})
			["when"]("/admin/songs/add",{
				templateUrl: "views/addSong.html",
				controller: "SongsController"
			})
}]);
