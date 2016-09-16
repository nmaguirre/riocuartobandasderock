package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public interface ArtistDAO {

	
	public List<Artist> getAllArtists();
	
	public List<Artist> findByName(String name);
	
	public List<Artist> findByRole(String role); 

	public Artist findById(String id);
	
	public boolean createArtist(Artist artist);
	
	public boolean updateArtist(Artist artist);
	
	public boolean deleteArtist(String id);
	
}
