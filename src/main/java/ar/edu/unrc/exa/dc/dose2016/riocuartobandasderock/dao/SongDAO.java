package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Song;

public interface SongDAO {


	/**
	    * This method get all songs
	   * 
	   * @return List of songs
	   */
	public List<Song> getAllSongs();

	/**
	    *
	    * @param String id
	    * @param String name
	    * @param Integer duration
	    * @param String albumTitle
	    *
	    * @return true if the update was successful
	    */

	public Boolean updateSong(String id, String name, Integer duration, String albumTitle);

	/**
	    *
	    * @param String id
	    *
	    * @return true if the delete was successful
	    */
	public Boolean removeSong(String id);


	   /**
	    *
	    * @param String name
	    * @param Integer duration
	    * @param String albumTitle
	    *
	    * @return true if the insert was successful
	    */

	public Boolean addSong(String name, Integer duration, String albumTitle);


	   /**
		 * 
		 * @param String id
		 * @return Song that have a particular id	
		 */
	public Song findById(String id);

	 /**
	    * This method find a song by name
	    *
	    * @param String name
	    *
	    * @return list of songs with particular name
	    */
	public List<Song> findByName(String name);
	 /**
	    * This method find a song by duration
	    * 
	    * @param Integer duration
	    * 
	    * @return list of songs with particular duration
	    */
	public List<Song> findByDuration(Integer duration);
  
    public List<Song> ilike(String name);

     /**
		* This method count the number of songs
		*
		*@return number of songs in database
		*/
	public int cantSongs();

}
