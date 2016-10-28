package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public interface SongDAO {
	
	public List<Song> getAllSongs();
	   
	public Boolean updateSong(Song song);
	   
	public Boolean removeSong(Song song);
	   
	public Boolean addSong(String name, Integer duration);
	
	public Song findById(String id);
                    
    public List<Song> findByAuthor(String author);
                    
	public List<Song> findByName(String name);
	
	public List<Song> findByDuration(Integer duration);

}
