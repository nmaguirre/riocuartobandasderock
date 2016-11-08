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
	 * @return Album iff was found 
	 */
	public Album findById(String id);
	
	/**
	 * @return All albums 
	 */
	public List<Album> getAll();	
	
	
	/**
	 * @param title
	 * @return Albums list founds by title
	 */ 
	public List<Album> findByTitle(String title);
	
	
	/**
	 * @param releaseDate
	 * @return Albums list founds by release date
	 */
	public List<Album> findByReleaseDate(Date releaseDate);
	
	
	/**
	 * @param title 
	 * @param releaseDate
	 * @return true iff creation is successful 
	 */
	public boolean create(String title, Date releaseDate);
	
	/**
	 * This method deletes an album found by id
	 * @param id
	 * @return true iff album was delete
	 */
	public boolean delete(String id);
}
