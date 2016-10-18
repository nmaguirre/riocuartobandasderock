package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.BandDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.AlbumDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.BandDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl.SongDaoImpl;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

import java.util.ArrayList;
import java.util.List;
import spark.Request;
import spark.Response;


/**
 * This Class implements the song controller
 **/


public class SongController {

    private SongDAO songDao;
    private AlbumDAO albumDao;
    private BandDAO bandDao;
    
    
    /**
     * Class constructor 
     */
    
    public SongController(){
    	songDao = new SongDaoImpl();
    	albumDao = new AlbumDaoImpl();
    	bandDao = new BandDaoImpl();
    }
    
    
    /**
     * Get all bands songs
     * @param req
     * @param res
     * @return a list of all bands songs
     */
    
    public List<Song> getAllSongs(Request req, Response res){    
        return songDao.getAllSongs();
    }
    

    
    /**
     * Get all artist songs
     * @param req
     * @param res
     * @return a list of all artist songs
     */  
    
    public List<Song> getArtistSongs (Request req, Response res){
        String artistName = req.params("name");
        return songDao.findByAuthor(artistName);
    }
    
    
    /**
     * Get a specific song by its id
     * @param req
     * @param res
     * @return a song
     */
  
    
    public Song getSongById (Request req, Response res){
    	String songId = req.params(":id");
        return songDao.findById(songId);
    }
    
    /**
     * Get a song by its name
     * @param req
     * @param res
     * @return a song
     */
    
    public List<Song> getSongByName (Request req, Response res){
    	String songName = req.params("name");
    	return songDao.findByName(songName);    	
    }
    
    
    /**
     * Add a new song
     * @param req
     * @param res 
     * @return
     */
  /*
    public Boolean addSong (Request req, Response res){
    	String songName = req.queryParams("name");    
    	
    	String dur = req.queryParams("duration");
    	int duration = Integer.parseInt(dur);
    	
    	Song song = new Song(songName, duration);
    	return songDao.addSong(song);
    }
    
    /*
    
    /**
     * Edit a song
     * @param req
     * @param res 
     * @return
     */
    public Boolean editSong (Request req, Response res){
    
		Song song = songDao.findById(req.params(":id"));
		
		song.setName(req.queryParams("name"));
    	
    	String dur = req.queryParams("duration");
    	song.setDuration(Integer.parseInt(dur));
    	return songDao.updateSong(song);
        
    }
    
    
    /**
     * Delete a song
     * @param req
     * @param res 
     * @return
     */
  
    public Boolean deleteSong (Request req, Response res){
    	Song song = songDao.findById(req.params(":id"));
    	return songDao.removeSong(song);
    }
    
    
	public String createSong(Request req,Response res){
		/*if((req.queryParams("name")=="") && (req.queryParams("duration")=="")){
			res.status(400);
			return "Request invalid";
		}
		Song song = new Song(req.queryParams("name"), Integer.parseInt(req.queryParams("duration")));
		boolean result = songDao.addSong(song);
		if (result) {
			res.status(201);
			return "Success";
		}
		else {
			return "Failed";
		}*/
		return "gorra resolve esto jaja";
	}
}