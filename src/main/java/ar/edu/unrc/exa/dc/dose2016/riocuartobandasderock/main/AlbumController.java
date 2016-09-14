package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import static spark.Spark.get;
import static spark.Spark.*; 
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import java.util.List;

public class AlbumController {
	
	public AlbumController(final AlbumDAOImpl albumDAO){
		
		get("/albums", (req,res) -> AlbumDAOImpl.getAllAlbums());
		get("/albums/:album_id", (req,res) -> {
			String id = req.params(":album_id");
			Album album = AlbumDAOImpl.findById(Integer.parseInt(id)); 
			if (album == null) res.status(400);
			return album;
		});
		post("/albums", (req, res) -> {
			Album album = new Album(); //Params should go here.
			int status = AlbumDAOImpl.createAlbum(album) ? 200 : 400;
			res.status(status);
		});
		put("/albums/:album_id", (req,res) -> {
			Album album = AlbumDAOImpl.findById(Integer.parseInt(req.params(":album_id")));
			album.set
			int status = AlbumDAOImpl.updateAlbum
		});
		/**Not sure if the rest of the searches are necessary,
		 * maybe we could return the whole collection and let 
		 * the client filter.
		 */
//		get("/albums/:album_name", (req,res) -> {
//			String album_name = req.params(":album_name");
//			Album album = AlbumDAOImpl.findByName(album_name);
//			if (album == null) res.status(400);
//			return album;
//		});
//		get("/albums/:album_year", (req, res) -> {
//			String album_year = req.params(":album_year");
//			List<Album> albums = AlbumDAOImpl.findByYear(Integer.parseInt(album_year));
//			if (albums == null) res.status(400);
//			return albums;
//		});
//		get("/albums/:genre", (req, res) -> {
//			String genre = req.params(":genre");
//			List<Album> albums = AlbumDAOImpl.findByGenere(genre);
//			if (albums == null) res.status(400);
//			return albums;
//		});
//		get("/albums/:record_label", (req, res) -> {
//			String record_label = req.params(":record_label");
//			List<Album> albums = AlbumDAOImpl.findByRecordLabel(record_label);
//			if (albums == null) res.status(400);
//			return albums;
//		});
//		get("/albums/:producer", (req, res) -> {
//			String producer = req.params(":producer");
//			List<Album> albums = AlbumDAOImpl.findByProducer(producer);
//			if (albums == null) res.status(400);
//			return albums;
//		});
//		get("/albums/:duration", (req, res) -> {
//			
//		})
//		get("/bands/:band_name/albums", (req,res) -> {
//			String band_name = req.params(":band_name");
//			List<Album> albums = AlbumDAOImpl.findByBandName(band_name);
//			if (albums == null) res.status(400);
//			return albums;
//		});
//		get("/bands/:band_id/albums", (req,res) -> AlbumDAOImpl.getBandAlbums());
//		get("/bands/:band_id/albums/:album_id", (req,res) -> {
//			String band_id = req.params(":band_id");
//			String album_id = req.params(":album_id");
//			Album album = AlbumDAOImpl.getBandAlbum(band_id, album_id);
//			if (album == null) res.status(400);
//			return album;
//		});
		
		
	}
}
