package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;


public interface BandDAO {

	public Session openCurrentSession();

	public Session openCurrentSessionwithTransaction();

	public void closeCurrentSession();

	public void closeCurrentSessionwithTransaction();

	public Session getCurrentSession();

	public void setCurrentSession(Session currentSession);

	public Transaction getCurrentTransaction();

	public void setCurrentTransaction(Transaction currentTransaction);
	
	   /**
	    * This method get all bands
	   * 
	   * @return List of Bands
	   */
	   public List<Band> getAllBands();
	   
	   /**
	    *
	    * @param String id
	    *
	    * @return Bands that have a particular id
	    */
	   public Band getBand(String id);

	   /**
	    *
	    * @param Band band
	    *
	    * @return true if the update was successful
	    */
	   public Boolean updateBand(Band band);

	   /**
	    *
	    * @param String id
	    *
	    * @return true if the delete was successful
	    */
	   public Boolean deleteBand(String id);

	   /**
	    *
	    * @param Band band
	    *
	    * @return true if the insert was successful
	    */
	   public Boolean createBand(String name, String genre);
	   
	   /**
	    * This method find a band by name
	    *
	    * @param String name
	    *
	    * @return bands with particular name
	    */
	   public List<Band> findBandByName(String name);
	   

	   /**
	    * This method find a band
	    * @param name
	    * @param genre
	    * @return true if a band exist
	    */
	   public boolean existBand(String name, String genre);
}

