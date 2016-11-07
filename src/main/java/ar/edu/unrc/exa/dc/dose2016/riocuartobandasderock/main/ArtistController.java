package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.ArtistDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

import java.util.List;
/**
 *the ArtistController class treats http requests referred to the Artist model 
 */
public class ArtistController {	
	/**
	 * one implementation ArtistDao to connect to db
	 */
	private static ArtistDAO artistDAO;
	private SessionManager session;
	private static ArtistController instance;
    /**
     * Constructor
     */
	private static void ArtistController(){
		artistDAO = new ArtistDaoImpl();
		instance = new ArtistController();
	}
	
	/* Method return SingletonInstance of ArtistController */
	
	public static ArtistController getInstance() {
		if (instance==null)  ArtistController();
		return instance;
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
		session = SessionManager.getInstance();
		session.openCurrentSession();
		Artist artist = artistDAO.findById(req.params(":id"));
		session.closeCurrentSession();
		int status = (artist==null)? 200:204;
		res.status(status);
		return artist;
		}
	
	public Artist getOneArtist (Request req, Response res){
		String name = req.params(":name");
		if (name==null){
			name="";
		}
		String surname = req.params(":surname");
		if (surname==null){
			surname="";
		}
		String nickname = req.params(":nickname");
		if (nickname==null){
			nickname="";
		}
		if((name=="") && (surname=="") && (nickname=="")){
			res.status(400);
			return null;
		}
		session= SessionManager.getInstance();
		session.openCurrentSession();
		Artist artist = artistDAO.getArtist(name,surname,nickname);
		session.closeCurrentSession();
		int status = (artist!=null)? 200:204;
		res.status(status);
		return artist;
	}
	
	/**
	 * get all Artist 
	 * @param req (Request)
	 * @param res (Response)
	 * @return  List of Artist
	 */
	public List<Artist> getAllArtists(Request req, Response res){
		session= SessionManager.getInstance();
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
		session= SessionManager.getInstance();
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
		session= SessionManager.getInstance();
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
		session= SessionManager.getInstance();
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
		String name = req.queryParams("name");
		if (name==null){
			name="";
		}
		String surname = req.queryParams("surname");
		if (surname==null){
			surname="";
		}
		String nickname = req.queryParams("nickname");
		if (nickname==null){
			nickname="";
		}
		if((name=="") && (surname=="") && (nickname=="")){
			res.status(400);
			return "Request invalid";
		}
		session= SessionManager.getInstance();
		session.openCurrentSessionwithTransaction();
		boolean status = artistDAO.createArtist(name,surname,nickname);
		session.closeCurrentSessionwithTransaction();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";
	}
	
	 /*@param res
	 * @return a string that describes the result of updateBandMember
	 */
	public String updateArtist(Request req, Response res){
		 session.openCurrentSession();
		 Artist artist = artistDAO.findById(req.params(":id"));
		 session.closeCurrentSession();
		 if (artist==null){
			 res.status(400);
			 return "Request invalid";
		 }
		String name = req.queryParams("name");
		if (name==null){
			name=artist.getName();
		}
		String surname = req.queryParams("surname");
		if (surname==null){
			surname=artist.getSurname();
		}
		String nickname = req.queryParams("nickname");
		if (nickname==null){
			nickname=artist.getNickname();
		}
		if((name=="") && (surname=="") && (nickname=="")){
			res.status(400);
			return "Request invalid";
		}
		 session= SessionManager.getInstance();
		 session.openCurrentSessionwithTransaction();
		 boolean status = artistDAO.updateArtist(artist.getId(),name,surname,nickname);
		 session.closeCurrentSessionwithTransaction();
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
			 res.status(400);
			 return "Request invalid";
		 }
		 session= SessionManager.getInstance();
		 session.openCurrentSessionwithTransaction();
		 boolean status = artistDAO.deleteArtist(req.params(":id"));
		 session.closeCurrentSessionwithTransaction();
		 if (status){
			 res.status(200);
			 return "Success";
		 }
		 res.status(409);
		 return "Fail";
	 }

}
