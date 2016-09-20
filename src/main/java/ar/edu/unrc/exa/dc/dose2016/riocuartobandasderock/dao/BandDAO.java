package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;


public interface BandDAO {
	
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
	   public Boolean addBand(Band band);
	   
	   
	
}

