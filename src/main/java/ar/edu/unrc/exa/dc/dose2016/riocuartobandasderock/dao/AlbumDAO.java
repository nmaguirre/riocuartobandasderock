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
 * @author Dose Team 2016
 */

public interface AlbumDAO {
	
	public Session openCurrentSession();
	public Session openCurrentSessionwithTransaction();
	public void closeCurrentSession();
	public void closeCurrentSessionwithTransaction();
	public Session getCurrentSession();
	public void setCurrentSession(Session currentSession);
	public Transaction getCurrentTransaction();
	public void setCurrentTransaction(Transaction currentTransaction);
	
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
	
//	/**
//	 * @param year
//	 * @return
//	 */
//	public List<Album> findByYear(int year);
	
	
	/**
	 * @param releaseDate
	 * @return
	 */
	public List<Album> findByReleaseDate(Date releaseDate);
	
	
	/**
	 * @param album
	 */
	public boolean createAlbum(Album album);
	
	/**
	 * @param album
	 */
	public boolean updateAlbum(Album album);
	
	/**
	 * @param album
	 */
	public boolean deleteAlbum(String id);
	
}
