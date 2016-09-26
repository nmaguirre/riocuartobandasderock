package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;

import java.util.List;
/**
 *the ArtistController class treats http requests referred to the Artist model 
 */
public class ArtistController {	
	/**
	 * one implementation ArtistDao to connect to db
	 */
	private ArtistDAO artistDAO;
	
	/**
	 * 
	 * @return ArtistDAO;
	 */
	public ArtistDAO getArtistDAO() {
		return artistDAO;
	}
    /**
     * Constructor
     */
	public ArtistController(){
		artistDAO = new ArtistDaoImpl();
	}

	/**
	 * get all Artist 
	 * @param req (Request)
	 * @param res (Response)
	 * @return  List of Artist
	 */
	public List<Artist> getAllArtists(Request req, Response res){
		return artistDAO.getAllArtists(); 
	}
	
	/**
	 * search artist by his Id
	 * @param req it contain id of the artist to search
	 * @param res
	 * @return one Artist with id parameters
	 */
	
	public Artist getArtistById (Request req, Response res){
		if (req==null) throw new IllegalArgumentException("Reques is null");
		return artistDAO.findById(req.params(":id")); 
	}
	
	/**
	 * search for artists by name
	 * @param req It contains the name to search for artists
	 * @param res
	 * @return List artist with name parameters
	 */
	public List<Artist> getArtistByName (Request req, Response res){
		return artistDAO.findByName(req.params("name"));
	}
		
	/**
	 * creates an artist 
	 * @param req    It contains the attributes of the new artist
	 * @param res
	 * @return True  when Artist It was created successfully
	 */
	public boolean createArtist(Request req,Response res){
		Artist artist = new Artist(req.queryParams("name"),req.queryParams("surname"),req.queryParams("nickname"));
		boolean status = artistDAO.createArtist(artist);
		return status;
	}
	
	/**
	 * update an artist 
	 * @param req	It contains the attributes of the artist to update
	 * @param res
	 * @return True when artist successfully updated
	 */
	public boolean updateArtist(Request req, Response res){
		Artist artist = artistDAO.findById(req.params(":id"));
		if (artist==null){
			return false;
		}
		artist.setName(req.queryParams("name"));
		artist.setNickname(req.queryParams("nickName"));
		artist.setSurname(req.queryParams("surName"));
		boolean status = artistDAO.updateArtist(artist);
		return status;
	}
	
	/**
	 * delete an artist by his Id
	 * @param req it contain id of the artist to delete
	 * @param res
	 * @return True when Artist successfully deleted
	 */
	public boolean deleteArtist(Request req, Response res){
	 	boolean status = artistDAO.deleteArtist(req.params(":id"));
	 	return status;
	}

}
