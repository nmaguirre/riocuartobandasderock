package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.model.Artist;

/**
 * 
 * @author Adrian Galfioni, Ezequiel Zensich.
 *
 * Artist DAO Interface.
 *
 */
public interface ArtistDAO {
	
	/**
	 * This method get all artists
	 * @return List of artists
	*/
	public List<Artist> getAllArtists();
	
	/**
	 * This method find an artist in the database, by name.
	 * @param String name
	 * @return Artists that have a particular name
	*/
	public List<Artist> findBySurname(String name);
	
	/**
	 * This method find an artist in the database, by nickname.
	 * @param String nickname.
	 * @return Artists that have a particular nickname
	*/
	public List<Artist> findByNickname(String nickname);
	
	/**
	 * This method find an artist in the database, by surname.
	 * @param String surname.
	 * @return Artists that have a particular surname
	*/
	public List<Artist> findByName(String surname);

	/**
	 * This method find an artist in the database, by id.
	 * @param String id.
	 * @return Artist that have a particular id	
	*/
	public List<Artist> findById(String id);
 
	/**
	 * @param String name.
	 * @param String surname.
	 * @param String nickname.
	 * @return true if this Artist exists in the database.
	*/
	public boolean existArtist(String name, String surname, String nickname);
	
	/**
	 * This method create an artist in the database.
	 * @param String name.
	 * @param String nickname.
	 * @param String surname.
	 * @return true if the create was successful
	*/
	public boolean createArtist(String name, String surname, String nickname);
	
	/**
	 * This method update an artist in database.
	 * @param String id.
	 * @param String name.
	 * @param String surname.
	 * @param String nickname.
	 * @return true if the update was successful.
	*/
	public boolean updateArtist(String id, String name, String surname, String nickname);		
	
	/**
	 * This method delete an artist in database.
	 * @param String id.
	 * @return true if the delete was successful.
	*/
	public boolean deleteArtist(String id);
	
	/**
	 * This method search for an artist by its parameters.
	 * @param String name.
	 * @param String surname.
	 * @param String nickname.
	 * @return list with the artist wanted, null if artist not found.
	*/
	public List<Artist> getArtist(String name, String surname, String nickname);

	/**
	* This method count the number of artist
	*
	*@return number of artist in database
	*/
	public int cantArtists();

	public List<Artist> ilike(String name);
}
