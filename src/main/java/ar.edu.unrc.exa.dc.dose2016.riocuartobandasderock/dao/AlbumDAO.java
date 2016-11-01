package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
//import java.util.Date;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

/**
 * Interface AlbumDAO, specifies that methods 
 * should be implemented album.
 * @author Dose Team 2016
 */

public interface AlbumDAO {
	
	/**
	 * @param id
	 * @return 
	 */
	public Album findById(String id);
	
	/**
	 * @return All albums 
	 */
	public List<Album> getAllAlbums();	
	
	
	/**
	 * @param name
	 * @return  
	 */ 
	public List<Album> findByName(String name);
	
	
	/**
	 * @param releaseDate
	 * @return
	 */
	public List<Album> findByReleaseDate(Date releaseDate);
	
	
	/**
	 * @param title, releaseDate
	 * @return 
	 */
	public boolean createAlbum(String title, Date releaseDate);
	
}
