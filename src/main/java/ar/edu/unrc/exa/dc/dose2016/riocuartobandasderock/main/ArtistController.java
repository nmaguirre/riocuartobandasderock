package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManagerHibernate;

import java.util.List;
/**
 *the ArtistController class treats http requests referred to the Artist model 
 */
public class ArtistController {	
	/**
	 * one implementation ArtistDao to connect to db
	 */
	private ArtistDAO artistDAO;
	private SessionManager session;
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
		session= SessionManagerHibernate.getInstance();
		session.openCurrentSession();
		List<Artist> artists = artistDAO.getAllArtists();
		session.closeCurrentSession();
		int status = (artists.size()>0)? 200:204;
		res.status(status);
		return artists;
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
			return null;
		}
		session= SessionManagerHibernate.getInstance();
		session.openCurrentSession();
		List<Artist> artists = artistDAO.findByName(req.params(":name"));
		session.closeCurrentSession();
		int status = (artists.size()!=0)? 200:204;
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
			return null;
		}
		session= SessionManagerHibernate.getInstance();
		session.openCurrentSession();
		List<Artist> artists = artistDAO.findBySurname(req.params(":surname"));
		session.closeCurrentSession();
		int status = (artists.size()!=0)? 200:204;
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
			return null;
		}
		session= SessionManagerHibernate.getInstance();
		session.openCurrentSession();
		List<Artist> artists = artistDAO.findByNickname(req.params(":nickname"));
		session.closeCurrentSession();
		int status = (artists.size()!=0)? 200:204;
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
		session= SessionManagerHibernate.getInstance();
		session.openCurrentSessionwithTransaction();
		boolean status = artistDAO.createArtist(req.queryParams("name"),req.queryParams("surname"),req.queryParams("nickname"));
		session.closeCurrentSessionwithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";
	}

}
