package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.impl;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao.AlbumDAO;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
/**
 * This class implements the AlbumDAO interface,
 * and contains the methods necessary 
 * for representation and management.
 * @author DOSE Team 2016
 *
 */
public class AlbumDaoImpl implements AlbumDAO{

	/**
	 * Find one album by id
	 * @param id
	 * @return Album iff this album exists by this id.
	 */
	public Album findById(String id){
		return null;
	}
	
	/**
	 * @return list of albums contained
	 */
	public List<Album> getAllAlbums(){
		return null;
	}	
	
	/**
	 * @param bandName
	 * @return List of albums found by name band
	 */
	public List<Album> findByBandName(String bandName){
		return null;
	}
	
	/**
	 * @param name
	 * @return Album found by name
	 */
	public Album findByName(String name){
		return null;
	}
	
	/**
	 * @param genere
	 * @return List of albums found by genere
	 */
	public List<Album> findByGenere(String genere){
		return null;
	}
	
	/**
	 * @param recordLabel
	 * @return List of albums found by record label
	 */
	public List<Album> findByRecordLabel(String recordLabel){
		return null;
	}
	
	/**
	 * @param producer
	 * @return List of albums found by producer
	 */
	public List<Album> findByProducer(String producer){
		return null;
	}
	
	/**
	 * @param duration
	 * @return List of albums found by duration
	 */
	public List<Album> findByDuration(int duration){
		return null;
	}
	
	/**
	 * @param song
	 * @return List of albums found by song
	 */
	public List<Album> findBySong(String song){
		return null;
	}
	
	/**
	 * @param producer
	 * @return List of albums found by producer
	 */
	public List<Album> findByReleaseDate(int year){
		return null;
	}
	
	/**
	 * @param album
	 * @return true if album was inserted into data base correctly
	 */
	public boolean createAlbum(Album album){
		return false;
	}
	
	/**
	 * @param album
	 * @return true if the album was updated correctly
	 */
	public boolean updateAlbum(Album album){
		return false;
	}
	
	/**
	 * @param id
	 * @return true if the album was removed from de data base correctly
	 */
	public boolean deleteAlbum(String id){
		return false;
	}
	
}
