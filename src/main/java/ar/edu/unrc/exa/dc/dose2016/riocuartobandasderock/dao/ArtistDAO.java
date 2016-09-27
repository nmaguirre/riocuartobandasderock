package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public interface ArtistDAO {

	/**
	* This method get all artists
	* 
	* @return List of artists
	*/
	public List<Artist> getAllArtists();
	
	/**
	* 
	* @param String name
	* 
	* @return Artists that have a particular name
	*/
	public List<Artist> findByName(String name);

	/**
	* 
	* @param String id
	* 
	* @return Artists that have a particular id
	*/
	public Artist findById(String id);
	
	/**
	* 
	* @param Artist artist
	* 
	* @return true if the create was successful
	*/
	public boolean createArtist(Artist artist);
	
	/**
	* 
	* @param Artist artist
	* 
	* @return true if the update was successful
	*/
	public boolean updateArtist(Artist artist);
	
	
	/**
	* 
	* @param String id
	* 
	* @return true if the delete was successful
	*/
	public boolean deleteArtist(String id);
	
}
