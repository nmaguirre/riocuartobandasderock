package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;
import spark.Response;
import spark.Request;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.ArtistDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *the ArtistController class treats http requests referred to the Artist model 
 */
public class ArtistController {	
	/**
	 * one implementation ArtistDao to connect to db
	 */
    protected static ArtistController unique_instance = null;

    /**
     * Constructor
     */
	public static ArtistController getInstance() {
        if (unique_instance == null)
            unique_instance = new ArtistController();
        return unique_instance;
    }

	
	/**
	 * get all Artist 
	 * @param req (Request)
	 * @param res (Response)
	 * @return  List of Artist
	 */
	public List<Artist> getAllArtists(Request req, Response res){
		Session session = SessionManager.getInstance().openSession();
		ArtistDaoImpl adao = new ArtistDaoImpl(session);
		
		List<Artist> artists = adao.getAllArtists();
		session.close();
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
		Session session = SessionManager.getInstance().openSession();
		ArtistDaoImpl adao = new ArtistDaoImpl(session);

		List<Artist> artists = adao.findByName(req.params(":name"));
		session.close();
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
		Session session = SessionManager.getInstance().openSession();
		ArtistDaoImpl adao = new ArtistDaoImpl(session);

		List<Artist> artists = adao.findBySurname(req.params(":surname"));
		session.close();
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
		Session session = SessionManager.getInstance().openSession();
		ArtistDaoImpl adao = new ArtistDaoImpl(session);

		List<Artist> artists = adao.findByNickname(req.params(":nickname"));
		session.close();
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
		if((req.queryParams("name")=="") && (req.queryParams("surname")=="") && (req.queryParams("nickname")=="")){
			res.status(400);
			return "Request invalid";
		}
		Session session = SessionManager.getInstance().openSession();
		ArtistDaoImpl adao = new ArtistDaoImpl(session);

		Transaction transaction = session.beginTransaction();
		boolean status = adao.createArtist(name,surname,nickname);
		transaction.commit();
		session.close();
		if (status){
			res.status(201);
			return "Success";
		}
		res.status(409);
		return "Fail";
	}

}
