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
		artistDAO.openCurrentSession();
		List<Artist> artists = artistDAO.getAllArtists();
		artistDAO.closeCurrentSession();
		int status = (artists.size()>0)? 200:404;
		res.status(status);
		return artists;
	}
	
	/**
	 * search artist by his Id
	 * @param req it contain id of the artist to search
	 * @param res
	 * @return one Artist with id parameters
	 */
	
	public Artist getArtistById (Request req, Response res){
		if (req.params(":id")==""){
			res.status(400);
		}
		artistDAO.openCurrentSession();
		Artist artist = artistDAO.findById(req.params(":id"));
		artistDAO.closeCurrentSession();
		int status = (artist==null)? 201:404;
		res.status(status);
		return artist;
	}
	
	/**
	 * search for artists by name
	 * @param req It contains the name to search for artists
	 * @param res
	 * @return List artist with name parameters
	 */
	public List<Artist> getArtistByName (Request req, Response res){
		if (req.params(":name")==""){
			res.status(400);
		}
		artistDAO.openCurrentSession();
		List<Artist> artists = artistDAO.findByName(req.params(":name"));
		artistDAO.closeCurrentSession();
		int status = (artists.size()==0)? 201:404;
		res.status(status);
		return artists;
	}
	
	/**
	 * search for artists by surname
	 * @param req It contains the surname to search for artists
	 * @param res
	 * @return List artist with surname parameters
	 */
	public List<Artist> getArtistBySurname (Request req, Response res){
		if (req.params(":surname")==""){
			res.status(400);
		}
		artistDAO.openCurrentSession();
		List<Artist> artists = artistDAO.findBySurname(req.params(":name"));
		artistDAO.closeCurrentSession();
		int status = (artists.size()==0)? 201:404;
		res.status(status);
		return artists;
	}
	
	/**
	 * search for artists by nickname
	 * @param req It contains the nickname to search for artists
	 * @param res
	 * @return List artist with nickname parameters
	 */
	public List<Artist> getArtistByNickname (Request req, Response res){
		if (req.params(":nickname")==""){
			res.status(400);
		}
		artistDAO.openCurrentSession();
		List<Artist> artists = artistDAO.findByNickname(req.params(":name"));
		artistDAO.closeCurrentSession();
		int status = (artists.size()==0)? 201:404;
		res.status(status);
		return artists;
	}
	
	/**
	 * creates an artist 
	 * @param req    It contains the attributes of the new artist
	 * @param res
	 * @return a string that describes the result of createArtist
	 */
	public String createArtist(Request req,Response res){
		if((req.queryParams("name")=="") && (req.queryParams("surname")=="") && (req.queryParams("nickname")=="")){
			res.status(400);
			return "Request invalid";
		}
		Artist artist = new Artist(req.queryParams("name"),req.queryParams("surname"),req.queryParams("nickname"));
		artistDAO.openCurrentSessionwithTransaction();
		boolean status = artistDAO.createArtist(artist);
		artistDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";	
	}
	
	/**
	 * update an artist 
	 * @param req	It contains the attributes of the artist to update
	 * @param res
	 * @return a string that describes the result of updateBandMember
	 */
	public String updateArtist(Request req, Response res){
		if((req.queryParams("name")=="") && (req.queryParams("surname")=="") && (req.queryParams("nickname")=="")){
			res.status(400);
			return "Request invalid";
		}
		artistDAO.openCurrentSession();
		Artist artist = artistDAO.findById(req.params(":id"));
		artistDAO.closeCurrentSession();
		if (artist==null){
			res.status(400);
			return "Request invalid";
		}
		artist.setName(req.queryParams("name"));
		artist.setNickname(req.queryParams("nickName"));
		artist.setSurname(req.queryParams("surName"));
		artistDAO.openCurrentSessionwithTransaction();
		boolean status = artistDAO.updateArtist(artist);
		artistDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(200);
			return "Success";
		}
		res.status(409);
		return "Fail";
	}
	
	/**
	 * delete an artist by his Id
	 * @param req it contain id of the artist to delete
	 * @param res
	 * @return a string that describes the result of deleteBandMember
	 */
	public String deleteArtist(Request req, Response res){
		if ((req.params(":id"))==""){
			res.status();
			return "Request invalid";
		}
		artistDAO.openCurrentSessionwithTransaction();
	 	boolean status = artistDAO.deleteArtist(req.params(":id"));
		artistDAO.closeCurrentSessionwithTransaction();
		if (status){
			res.status(200);
			return "Success";
		}
		res.status(404);
		return "Fail";
	}

}
