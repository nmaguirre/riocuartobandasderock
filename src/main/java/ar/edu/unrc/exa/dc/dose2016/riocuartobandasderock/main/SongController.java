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
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.TGenre;

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
     * Get all band songs
     * @param req
     * @param res
     * @return a list of all band songs
     */
    
    public List<Song> getBandSongs (Request req, Response res){   
        String bandName = req.params("name"); //review
        return songDao.findByBandName(bandName);
    }
    
    
    /**
     * Get all album songs
     * @param req
     * @param res
     * @return a list of all album songs
     */
  
    public List<Song> getAlbumSongs (Request req, Response res){
       String albumName = req.params("title"); //review
       return songDao.findByAlbum(albumName);
    }
    
    
    /**
     * Get songs by genre
     * @param req
     * @param res
     * @return
     */
    
    public List<Song> getSongsbyGenre (Request req, Response res){
    	String genre = req.params("genre"); 
    	return songDao.findByGenere(genre);
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
  
    public Boolean addSong (Request req, Response res){
    	String songName = req.queryParams("name");
    	
    	String genreName = req.queryParams("genre");
    	TGenre genre = TGenre.rock;
    	switch(genreName){
    	
    	case "pop":
    		genre = TGenre.pop;
    		break;
    		
    	case "blues":
    		genre = TGenre.blues;
    		break;
    	
    	case "reggae":
    		genre = TGenre.reggae;
    		break;
    		
    	case "folk":
    		genre = TGenre.folk;
    		break;
    		
    	case "electronic":
    		genre = TGenre.electronic;
    		break;
    		
    	case "metal":
    		genre = TGenre.metal;
    		break;
    	
    	case "punk":
    		genre = TGenre.punk;
    		break;
    	}
    	
    	String[] bands = req.queryParamsValues("bands");
    	List<Band> listOfBands = new ArrayList<>();
    	for(String b:bands){
    		listOfBands.add(bandDao.findBandByName(b));
    	}
    	
    	String dur = req.queryParams("duration");
    	int duration = Integer.parseInt(dur);
    	String author = req.queryParams("author");
    	String alb = req.queryParams("album");
    	Album album = albumDao.findByName(alb);
    	
    	Song song = new Song(0,songName,genre, listOfBands, duration,author,album);
    	return songDao.addSong(song);
    }
    
    
    /**
     * Edit a song
     * @param req
     * @param res 
     * @return
     */
    public Boolean editSong (Request req, Response res){
    
		Song song = songDao.findById(req.params(":id"));
		
		song.setName(req.queryParams("name"));
		
    	String genreName = req.queryParams("genre");
    	TGenre genre = TGenre.rock;
    	switch(genreName){
    	
    	case "pop":
    		genre = TGenre.pop;
    		break;
    		
    	case "blues":
    		genre = TGenre.blues;
    		break;
    	
    	case "reggae":
    		genre = TGenre.reggae;
    		break;
    		
    	case "folk":
    		genre = TGenre.folk;
    		break;
    		
    	case "electronic":
    		genre = TGenre.electronic;
    		break;
    		
    	case "metal":
    		genre = TGenre.metal;
    		break;
    	
    	case "punk":
    		genre = TGenre.punk;
    		break;
    	}
    	
    	song.setGenre(genre);
    	
    	String[] bands = req.queryParamsValues("bands");
    	List<Band> listOfBands = new ArrayList<>();
    	for(String b:bands){
    		listOfBands.add(bandDao.findBandByName(b));
    	}
    	
    	song.setBands(listOfBands);
    	
    	String dur = req.queryParams("duration");
    	song.setDuration(Integer.parseInt(dur));
    	song.setAuthor(req.queryParams("author"));
    	String alb = req.queryParams("album");
    	song.setAlbum(albumDao.findByName(alb));
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
    
}