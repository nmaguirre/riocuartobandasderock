package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

public interface ArtistDAO {

	
	public Session openCurrentSession();

	public Session openCurrentSessionwithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

	public Session getCurrentSession();

	public void setCurrentSession(Session currentSession);
	
	public Transaction getCurrentTransaction();

	public void setCurrentTransaction(Transaction currentTransaction);
	
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
	public List<Artist> findBySurname(String name);
	
	/**
	* 
	* @param String nickname
	* 
	* @return Artists that have a particular nickname
	*/
	public List<Artist> findByNickname(String nickname);
	
	/**
	* 
	* @param String surname
	* 
	* @return Artists that have a particular surname
	*/
	public List<Artist> findByName(String surname);

	/**
	 * 
	 * @param name
	 * @param surname
	 * @param nickname
	 * 
	 * @return true if this Artist exists in the database.
	 */
	public boolean existArtist(String name, String surname, String nickname);
	
	/**
	* 
	* @param name
	* @param nickname
	* @param surname
	* 
	* @return true if the create was successful
	*/
	public boolean createArtist(String name, String surname, String nickname);
	
}
