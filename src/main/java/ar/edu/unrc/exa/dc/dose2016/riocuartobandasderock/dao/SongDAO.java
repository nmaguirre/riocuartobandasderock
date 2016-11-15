package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public interface SongDAO {
	
	public List<Song> getAllSongs();
	   
	public Boolean updateSong(Song song);
	   
	public Boolean removeSong(String id);
	   
	public Boolean addSong(String name, Integer duration);
	
	public Song findById(String id);
                    
	public List<Song> findByName(String name);
	
	public List<Song> findByDuration(Integer duration);

}
