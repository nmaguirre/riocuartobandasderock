package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;


import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public interface SongDAO {

	
	public List<Song> getAllSongs();
	   
	public Boolean updateSong(Song song);
	   
	public Boolean removeSong(Song song);
	   
	public Boolean addSong(Song song);
	
	public Song findById(int id);
	
	public List<Song> findByBandName(String BandName);
	
                    public List<Song> findByGenere(String genere);
                    
                    public List<Song> findByAuthor(String author);
                    
                    public List<Song> findByAlbum(String album);
                    
	public List<Song> findByName(String name);
	
	public List<Song> findByDuration(int duration);

}
