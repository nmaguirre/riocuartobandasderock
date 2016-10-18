package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import spark.Request;
import spark.Response;

public class BandController {
	/***
	 * This class implements the communication layer between the persistence and frontend.
	 */

	private BandDAO bandDAO;
	/***
	 *
	 * @param bandPersistence
	 */
	public BandController(BandDAO bandPersistence){
		this.bandDAO = bandPersistence;
	}

	/***
	 * This method returns all bands 
	 * @param req
	 * @param res
	 * @return A list of all bands
	 */
	public List<Band> getBands(Request req ,Response res){
		return bandDAO.getAllBands();
	}

	/***
	 * This method takes a band id, and returns its data encapsulated in an object
	 * @param req
	 * @param res
	 * @return the data of a band, encapsulated in an object.
	 */
	public Band getBand(Request req,Response res){
		// we must define if we can use the "name" as unique attribute
		String name = req.params(":name");
		return bandDAO.getBand(name);
	}

	/***

	 * This method takes the data of a band from the frontend, and creates a band in database
	 * @param req
	 * @param res
	 * @return the object of the band created.
	 */
/*	public String createBand(Request req,Response res){
		// return new Band();
		if((req.queryParams("name")=="") && (req.queryParams("genre")=="")){
			res.status(400);
			return "Request invalid";
		}
		Band band = new Band(req.queryParams("name"),req.queryParams("genre"));
		bandDAO.openCurrentSessionwithTransaction();
		boolean status = bandDAO.createBand(band);
		bandDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(500);
		return "Fail";
*/
	public Band createBand(Request req,Response res){
		Band band = null ;
		String name = req.params("name");
		// String genereStr = req.params("genere");
		// genereDAO is not defined
		//Genere genere = genereDAO.getBand(genereStr);
		Genere genere = null; 
		String artistListStr = req.params("artist_list");
		// here we the data of artist_list will be processed.
		List<Artist> artistList = null;
		String release = req.params("release");
		String albumListStr = req.params("album_list");
		if (albumListStr == null){
			band = new Band(name, genere, artistList, release);
		}else{
			// here we the data of album_list will be processed.
			List<Album> albumList = null;
			band = new Band(name, genere, artistList, release, albumList);
		}
		bandDAO.addBand(band);
		return band;
	}

	/***
	 * This method takes the data of a band from the frontend, and updates a band in database
	 * @param req
	 * @param res
	 * @return the object of the band updated.
	 */
	public Band updateBand(Request req,Response res){
		Band band = bandDAO.getBand(req.params("id"));
		band.setName(req.params("name"));
		// Genere genere = genereDAO.getGenere(req.params("genere"));
		Genere genere = null;
		band.setGenere(genere);
		String artistListStr = req.params("artist_list");
		// here we the data of artist_list will be processed.
		List<Artist> artistList = null;
		band.setArtistList(artistList);
		band.setRelease(req.params("release"));
		String albumListStr = req.params("album_list");
		// here we the data of album_list will be processed.
		List<Album> albumList = null;
		band.setAlbumList(albumList);
		bandDAO.updateBand(band);
		return band;
	}

	/***
	 * This method takes the id of a band from the frontend, and delete this band in database
	 * @param req
	 * @param res
	 * @return true if the the band was created. Otherwise, false.
	 */
	public Boolean deleteBand(Request req,Response res){
		// we must define if we can use the "name" as unique attribute
		return bandDAO.deleteBand(req.params(":name"));
	}
}
