package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;



import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SessionManager;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SongDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import spark.Request;
import spark.Response;


/**
 * This Class implements the song controller
 **/


public class SongController {


    protected static SongController unique_instance = null;
    
    /**
     * Class constructor 
     */
    
    public static SongController getInstance() {
        if (unique_instance == null)
            unique_instance = new SongController();
        return unique_instance;
    }
    
    
    /**
     * Get all bands songs
     * @param req
     * @param res
     * @return a list of all bands songs
     */
    
    public List<Song> getAllSongs(Request req, Response res){    
        return null;
    }
 
    
    /**
     * Get a specific song by its id
     * @param req
     * @param res
     * @return a song
     */
  
    public Song getSongById (Request req, Response res){
    	return null;
    }
    
    /**
     * Get a song by its name
     * @param req
     * @param res
     * @return a song
     */
    
    public List<Song> getSongByName (Request req, Response res){
       	String songName = req.params("name");
    	
    	if (songName == null || songName == ""){
    		res.status(400);
    		return null;
    	};   	
    	Session session = SessionManager.getInstance().openSession();
    	SongDAO sdao = new SongDaoImpl(session);
    	Transaction transaction = session.beginTransaction();
    	List<Song> songs = sdao.findByName(songName);
    	res.status(songs.size() > 0 ? 200 : 204);
    	transaction.commit();
    	session.close();

    	return songs;    	
    }
  
    /**
     * Get list of songs by a specific duration  
     * @param req
     * @param res
     * @return
     */
    
    public List<Song> getSongByDuration (Request req, Response res){
    	String duration = req.params("duration");
    	
    	if (duration == null || duration == ""){
    		res.status(400);
    		return null;
    	}
    	Session session = SessionManager.getInstance().openSession();
    	SongDAO sdao = new SongDaoImpl(session);

    	List<Song> songs = sdao.findByDuration(Integer.parseInt(duration));
    	res.status(songs.size() > 0 ? 200 : 204);
    	session.close();

    	return songs;
    }
    
    
    /**
     * Add a new song
     * @param req
     * @param res 
     * @return
     */
  
    public String addSong (Request req, Response res){
    	
    	String songName = req.queryParams("name");    	
    	String dur = req.queryParams("duration");    	
    	Session session = SessionManager.getInstance().openSession();
    	SongDAO sdao = new SongDaoImpl(session);
    	Transaction transaction = session.beginTransaction();

    	if(( songName == null || songName == "" ) || dur == null) {
			res.status(400);
			res.body("Invalid content of parameters");
			return res.body();
		}
    	boolean result = sdao.addSong(songName,Integer.parseInt(dur));
    	transaction.commit();
    	session.close();
    	
    	if(result){
    	res.body("Song created");
    	res.status(201);
    	}
    	return res.body();    		
    }
    
    
    
    /***
	 * This method takes a song from the frontend, and delete this song in database
	 * @param req
	 * @param res
	 * @return true if the song was deleted. Otherwise, false.
	 */
	public String removeSong(Request req,Response res){	
		String id = req.params(":id");
		if ((id == "") ||(id == null)) {
			res.status(400);
			res.body("Invalid request");
			return res.body();
		}
		Session session = SessionManager.getInstance().openSession();
		SongDAO sdao = new SongDaoImpl(session);

		Transaction transaction = session.beginTransaction();
	 	boolean status = sdao.removeSong(id);
	 	transaction.commit();
		session.close();
	 	
		if (status){
			res.status(200);
			res.body("Success");
			res.body();
		}
		

		res.status(409);
		res.body("Fail");
		return res.body();

	}
    
   
    

    
}