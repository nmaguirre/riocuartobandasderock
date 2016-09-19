package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.SongDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;
import java.util.List;
import spark.Request;
import spark.Response;


/**
 * This Class implements the song controller
 **/


public class SongController {

    private SongDAO songDao;
    
    
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
        String bandName = req.params("name");
        return songDao.findByBandName(bandName);
    }
    
    
    /**
     * Get all album songs
     * @param req
     * @param res
     * @return a list of all ambum songs
     */
  
    public List<Song> getAlbumSongs (Request req, Response res){
       String albumName = req.params("title"); 
       return songDao.findByAlbum(albumName);
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
     * Get a specific song
     * @param req
     * @param res
     * @return a specific song
     */
  
    
    public Song getSong (Request req, Response res){
        return null;
    }
    
    
    /**
     * Add a new song
     * @param req
     * @param res 
     */
  
    public void addSong (Request req, Response res){
        Song song = new Song();
        songDao.addSong(song);
    }
    
    
    /**
     * Edit a song
     * @param req
     * @param res 
     */
    public void editSong (Request req, Response res){
        
    }
    
    
    /**
     * Delete a song
     * @param req
     * @param res 
     */
  
    public void deleteSong (Request req, Response res){
    
    }
    
}