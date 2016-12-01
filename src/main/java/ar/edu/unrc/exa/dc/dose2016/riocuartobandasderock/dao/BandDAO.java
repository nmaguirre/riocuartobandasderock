package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Band;
import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Album;


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
	   public boolean updateBand(String id, String name, String genre); 
	   
	   /**
	    *
	    * @param String id
	    *
	    * @return true if the delete was successful
	    */
	   public boolean deleteBand(String id);

	   /**
	    *
	    * @param Band band
	    *
	    * @return true if the insert was successful
	    */
	   public boolean createBand(String name, String genre);
	   
	   /**
	    * This method find a band by name
	    *
	    * @param String name
	    *
	    * @return bands with particular name
	    */
	   public List<Band> findByName(String name);
	   
	   /**
	    * This method find a band by genre
	    * 
	    * @param String genre
	    * 
	    * @return list of bands with particular genre
	    */
	   public List<Band> findByGenre(String genre);
	   
	   /**
	    * This method find a band by genre and name
	    * 
	    * @param String name
	    * @param String genre
	    * 
	    * @return list of bands with particular name and particular genre
	    */
	   
	   public List<Band> findByNameAndGenre(String name,String genre);
	   

	   /**
	    * This method find a band
	    * @param name
	    * @param genre
	    * @return true if a band exist
	    */
	   public boolean existBand(String name);
	   
	   
	   /**
		 * 
		 * @param String id
		 * @return Band that have a particular id	
		 */
	   public Band findById(String id);


	   /**
		 * 
		 * @param String id
		 * @return Band that have a particular id	
		 */
	   public List<Band> ilike(String name);

	   public List<Album> findAlbums(String bandID);
}

