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
