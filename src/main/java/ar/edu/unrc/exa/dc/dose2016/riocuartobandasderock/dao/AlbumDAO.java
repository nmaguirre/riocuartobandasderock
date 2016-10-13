package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

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
	 * @param bandName
	 * @return 
	 */
	public List<Album> findByBandName(String bandName);
	
	/**
	 * @param name
	 * @return  
	 */ 
	public Album findByName(String name);
	
//	/**
//	 * @param year
//	 * @return
//	 */
//	public List<Album> findByYear(int year);
	
	/**
	 * @param genere
	 * @return
	 */
	public List<Album> findByGenere(String genere);

	/**
	 * @param recordLabel
	 * @return
	 */
	public List<Album> findByRecordLabel(String recordLabel);
	
	/**
	 * @param producer
	 * @return
	 */
	public List<Album> findByProducer(String producer);
	
	/**
	 * @param duration
	 * @return
	 */
	public List<Album> findByDuration(int duration);
	
	/**
	 * @param song
	 * @return
	 */
	public List<Album> findBySong(String song);
	
	/**
	 * @param releaseDate
	 * @return
	 */
	public List<Album> findByReleaseDate(int year);
	
//	/**
//	 * @param recordDate
//	 * @return
//	 */
//	public List<Album> findByRecordDate(Date recordDate);
	
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
