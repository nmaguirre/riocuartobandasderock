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

	public List<Album> findByRecordLabel(String recordLabel){
		return null;
	}
	
	public List<Album> findByProducer(String producer){
		return null;
	}
	
	public List<Album> findByDuration(int duration){
		return null;
	}
	
	public List<Album> findBySong(String song){
		return null;
	}
	
	public List<Album> findByReleaseDate(int year){
		return null;
	}

	public boolean createAlbum(Album album){
		return false;
	}
	
	public boolean updateAlbum(Album album){
		return false;
	}
	
	public boolean deleteAlbum(String id){
		return false;
	}
	
}
