package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import java.util.List;
/**
 * 
 * this class represent one member banda
 *
 */
public class ArtistController {	
	/**
	 * one implementation  ArtistDao
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
     * 
     * @param artistDAO_a  over create object
     */
	public ArtistController(ArtistDAO artistDAO_a){
		if (artistDAO_a==null) throw new IllegalArgumentException("Argument is null");
		artistDAO = artistDAO_a;
	}

	/**
	 * get all Artist 
	 * @param req (Request 
	 * @param res (Response
	 * @return  List of Artist
	 */
	public List<Artist> getAllArtists(Request req, Response res){
		return artistDAO.getAllArtists(); 
	}
	
	/**
	 * 
	 * @param req search artist for id
	 * @param res
	 * @return one Artist with id parameters
	 */
	
	public Artist getArtistById (Request req, Response res){
		if (req==null) throw new IllegalArgumentException("Reques is null");
		return artistDAO.findById(req.params(":id")); 
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return List artist with name parameters
	 */
	public List<Artist> getArtistByName (Request req, Response res){
		return artistDAO.findByName(req.params("name"));
	}
		
	/**
	 * 
	 * @param req    Attribute of artist new
	 * @param res
	 * @return True  Artist It was created successfully
	 */
	public boolean createArtist(Request req,Response res){
		Artist artist = new Artist(req.queryParams("name"),req.queryParams("surname"),req.queryParams("nickname"));
		boolean status = artistDAO.createArtist(artist);
		return status;
	}
	
	/**
	 * 
	 * @param req
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
	 * 
	 * @param req
	 * @param res
	 * @return True when Artist successfully deleted
	 */
	public boolean deleteArtist(Request req, Response res){
	 	boolean status = artistDAO.deleteArtist(req.params(":id"));
	 	return status;
	}

}
