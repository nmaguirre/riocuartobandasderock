package ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ar.edu.unrc.exa.dc.dose2016.riocuartobandasderock.main.ServerOptions;
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
	 * @param String id
	 * @return Artist that have a particular id	
	 */
	public Artist findById(String id);
 
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
	* Create an artist in the database
	* Data is stored in lowercase
	* 
	* @param name
	* @param nickname
	* @param surname
	* 
	* @return true if the create was successful
	*/
	public boolean createArtist(String name, String surname, String nickname);
	
	/**
	 * Update an artist in database
	 * Data is updated in lowercase
	 * 
	 * @param id
	 * @param name
	 * @param surname
	 * @param nickname
	 * @return true if the update was successful
	 */
	public boolean updateArtist(String id, String name, String surname, String nickname);		
	
	/**
	 * 
	 * @param String id
	 * @return true if the delete was successful
	 */
	public boolean deleteArtist(String id);
	
	/**
	 * Search for an artist by its parameters
	 * 
	 * @param name
	 * @param surname
	 * @param nickname
	 * @return list with the artist wanted, null if artist not found
	 */
	public List<Artist> getArtist(String name, String surname, String nickname);
}
